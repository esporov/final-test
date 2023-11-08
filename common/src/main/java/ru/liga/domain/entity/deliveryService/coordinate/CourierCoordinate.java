package ru.liga.domain.entity.deliveryService.coordinate;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.domain.entity.deliveryService.courier.Courier;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "courier_coordinate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourierCoordinate {

    @Id
    @Column(name = "courier_id")
    private long courierId;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @OneToOne
    @MapsId
    @JoinColumn(name = "courier_id")
    @JsonIgnore
    private Courier courier;

    @Override
    public String toString() {
        return "CourierCoordinate{" +
                "courierId=" + courierId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourierCoordinate that = (CourierCoordinate) o;

        if (courierId != that.courierId) return false;
        if (Float.compare(that.latitude, latitude) != 0) return false;
        return Float.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        int result = (int) (courierId ^ (courierId >>> 32));
        result = 31 * result + (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        result = 31 * result + (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        return result;
    }
}
