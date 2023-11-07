package ru.liga.domain.entity.deliveryService.coordinate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.domain.entity.deliveryService.courier.Courier;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "courier_coordinates")
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

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @MapsId
    private Courier courier;

    @Override
    public String toString() {
        return "Coordinate{" +
                "courierId=" + courierId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", courier=" + courier +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourierCoordinate that = (CourierCoordinate) o;

        if (courierId != that.courierId) return false;
        if (Float.compare(that.latitude, latitude) != 0) return false;
        if (Float.compare(that.longitude, longitude) != 0) return false;
        return Objects.equals(courier, that.courier);
    }

    @Override
    public int hashCode() {
        int result = (int) (courierId ^ (courierId >>> 32));
        result = 31 * result + (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        result = 31 * result + (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        result = 31 * result + (courier != null ? courier.hashCode() : 0);
        return result;
    }
}
