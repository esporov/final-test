package ru.liga.domain.entity.restaurantService.restaurant;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Hidden
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "address")
    private String address;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "work_status")
    private WorkStatus workStatus;

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", workStatus=" + workStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (id != that.id) return false;
        if (!Objects.equals(address, that.address)) return false;
        return workStatus == that.workStatus;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (workStatus != null ? workStatus.hashCode() : 0);
        return result;
    }
}
