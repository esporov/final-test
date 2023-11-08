package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.deliveryService.courier.CourierStatus;
import ru.liga.domain.exception.CourierNotFoundException;
import ru.liga.domain.exception.IllegalStatusException;
import ru.liga.repository.CourierRepository;
import ru.liga.service.CourierService;
import ru.liga.util.IllegalStatusExceptionMessage;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService, IllegalStatusExceptionMessage {

    private final CourierRepository courierRepository;

    @Override
    public Courier getCourierByCourierId(long id) {
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("Курьера с id = " + id + " не существует."));
    }

    @Override
    public List<Courier> getAllCourierByCourierStatus(String courierStatus) {
        try {
            CourierStatus validStatus = CourierStatus.valueOf(courierStatus);
            List<Courier> couriers = courierRepository.getCouriersByCourierStatus(validStatus);
            if (couriers.size() == 0) {
                throw new CourierNotFoundException("Курьеров с указанным статусом = " + courierStatus + " не найдено.");
            }
            return couriers;
        } catch (IllegalArgumentException e) {
            throw new IllegalStatusException(exceptionMessage(CourierStatus.class), e);
        }
    }

    @Transactional
    @Override
    public Courier updateCourierStatusByCourierId(long id, String courierStatus) {
        try {
            CourierStatus validStatus = CourierStatus.valueOf(courierStatus);
            var courier = getCourierByCourierId(id);
            courierRepository.updateCourierStatusByCourierId(id, validStatus);
            courier.setCourierStatus(validStatus);
            return courier;
        } catch (IllegalArgumentException e) {
            throw new IllegalStatusException(exceptionMessage(CourierStatus.class), e);
        }
    }
}
