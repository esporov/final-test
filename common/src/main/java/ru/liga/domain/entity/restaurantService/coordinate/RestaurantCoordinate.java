package ru.liga.domain.entity.restaurantService.coordinate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.domain.entity.restaurantService.restaurant.Restaurant;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "coordinates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantCoordinate {

    @Id
    @Column(name = "restaurant_id")
    private long restaurantId;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @MapsId
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "RestaurantCoordinate{" +
                "restaurantId=" + restaurantId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", restaurant=" + restaurant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantCoordinate that = (RestaurantCoordinate) o;

        if (restaurantId != that.restaurantId) return false;
        if (Float.compare(that.latitude, latitude) != 0) return false;
        if (Float.compare(that.longitude, longitude) != 0) return false;
        return Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        int result = (int) (restaurantId ^ (restaurantId >>> 32));
        result = 31 * result + (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        result = 31 * result + (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        return result;
    }
}
