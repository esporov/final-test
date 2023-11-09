package ru.liga.domain.entity.orderService.orderItem;

import lombok.*;
import ru.liga.domain.entity.restaurantService.item.Item;
import ru.liga.domain.entity.orderService.order.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_items")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_items_seq")
    @SequenceGenerator(name = "order_items_seq", sequenceName = "order_items_seq", allocationSize = 20)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn
    private Order order;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private Item item;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", item=" + item +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (quantity != orderItem.quantity) return false;
        if (!Objects.equals(order, orderItem.order)) return false;
        if (!Objects.equals(item, orderItem.item)) return false;
        return Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}
