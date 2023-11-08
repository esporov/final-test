package ru.liga.service;




import ru.liga.domain.entity.deliveryService.courier.Courier;

import java.util.List;

public interface CourierService {

    Courier getCourierByCourierId(long courierId);

    List<Courier> getAllCourierByCourierStatus(String courierStatus);

    Courier updateCourierStatusByCourierId(long courierId, String status);
}
