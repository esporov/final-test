package ru.liga.domain.entity.orderService.order;

import lombok.*;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.orderService.customer.Customer;
import ru.liga.domain.entity.restaurantService.restaurant.Restaurant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Builder
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private Restaurant restaurant;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "kitchen_status")
    private KitchenStatus kitchenStatus;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn
    private Courier courier;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                ", kitchenStatus=" + kitchenStatus +
                ", deliveryStatus=" + deliveryStatus +
                ", createDate=" + createDate +
                ", price=" + price +
                ", courier=" + courier +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!Objects.equals(customer, order.customer)) return false;
        if (!Objects.equals(restaurant, order.restaurant)) return false;
        if (kitchenStatus != order.kitchenStatus) return false;
        if (deliveryStatus != order.deliveryStatus) return false;
        if (!Objects.equals(createDate, order.createDate)) return false;
        if (!Objects.equals(price, order.price)) return false;
        return Objects.equals(courier, order.courier);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (kitchenStatus != null ? kitchenStatus.hashCode() : 0);
        result = 31 * result + (deliveryStatus != null ? deliveryStatus.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (courier != null ? courier.hashCode() : 0);
        return result;
    }
}
