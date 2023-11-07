package ru.liga.domain.entity.restaurantService.item;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;
import ru.liga.domain.entity.restaurantService.restaurant.Restaurant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "menu_items")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Hidden
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private ItemStatus itemStatus;

    @ManyToOne
    @JoinColumn
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", itemStatus=" + itemStatus +
                ", restaurant=" + restaurant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (!Objects.equals(name, item.name)) return false;
        if (!Objects.equals(price, item.price)) return false;
        if (!Objects.equals(image, item.image)) return false;
        if (!Objects.equals(description, item.description)) return false;
        if (itemStatus != item.itemStatus) return false;
        return Objects.equals(restaurant, item.restaurant);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (itemStatus != null ? itemStatus.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        return result;
    }
}
