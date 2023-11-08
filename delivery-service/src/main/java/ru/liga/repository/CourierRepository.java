package ru.liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.deliveryService.courier.CourierStatus;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier, Long> {

    @Query("select c " +
            "from Courier c " +
            "where c.courierStatus = :status")
    List<Courier> getCouriersByCourierStatus(@Param("status") CourierStatus status);

    @Modifying
    @Query("update Courier c " +
            "set c.courierStatus = :status " +
            "where c.id = :id")
    void updateCourierStatusByCourierId(@Param("id") long courierId, @Param("status") CourierStatus status);
}
