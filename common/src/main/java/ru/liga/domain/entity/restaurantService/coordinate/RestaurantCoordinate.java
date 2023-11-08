package ru.liga.domain.entity.restaurantService.coordinate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.domain.entity.restaurantService.restaurant.Restaurant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "restaurant_coordinate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantCoordinate implements Serializable {

    @Id
    @Column(name = "restaurant_id")
    private long restaurantId;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @OneToOne
    @MapsId
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "RestaurantCoordinate{" +
                "restaurantId=" + restaurantId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantCoordinate that = (RestaurantCoordinate) o;

        if (restaurantId != that.restaurantId) return false;
        if (Float.compare(that.latitude, latitude) != 0) return false;
        return Float.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        int result = (int) (restaurantId ^ (restaurantId >>> 32));
        result = 31 * result + (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        result = 31 * result + (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        return result;
    }
}
