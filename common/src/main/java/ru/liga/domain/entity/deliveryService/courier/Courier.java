package ru.liga.domain.entity.deliveryService.courier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.domain.entity.deliveryService.coordinate.CourierCoordinate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "couriers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "phone")
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private CourierStatus courierStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private CourierCoordinate coordinate;

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", courierStatus=" + courierStatus +
                ", coordinate=" + coordinate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Courier courier = (Courier) o;

        if (id != courier.id) return false;
        if (!Objects.equals(phone, courier.phone)) return false;
        if (courierStatus != courier.courierStatus) return false;
        return Objects.equals(coordinate, courier.coordinate);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (courierStatus != null ? courierStatus.hashCode() : 0);
        result = 31 * result + (coordinate != null ? coordinate.hashCode() : 0);
        return result;
    }
}
