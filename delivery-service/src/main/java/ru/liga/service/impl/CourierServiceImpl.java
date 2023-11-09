package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.consumer.impl.DeliveryOrderListenerImpl;
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
@Slf4j
public class CourierServiceImpl implements CourierService, IllegalStatusExceptionMessage {

    private final Logger LOGGER = LoggerFactory.getLogger(CourierServiceImpl.class);
    private final CourierRepository courierRepository;

    @Override
    public Courier getCourierByCourierId(long id) {
        LOGGER.info("Поиск курьера по id = {}",id);
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("Курьера с id = " + id + " не существует."));
    }

    @Override
    public List<Courier> getAllCourierByCourierStatus(String courierStatus) {
        try {
            LOGGER.info("Поиск курьеров со статусов {}",courierStatus);
            List<Courier> couriers = courierRepository.getCouriersByCourierStatus(CourierStatus.valueOf(courierStatus));
            if (couriers.size() == 0) {
                throw new CourierNotFoundException("Курьеров с указанным статусом = " + courierStatus + " не найдено.");
            }
            return couriers;
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Курьеры со статусом {} отсутствуют",courierStatus);
            throw new IllegalStatusException(exceptionMessage(CourierStatus.class), e);
        }
    }

    @Transactional
    @Override
    public Courier updateCourierStatusByCourierId(long id, String courierStatus) {
        try {
            var courier = getCourierByCourierId(id);
            LOGGER.info("Обновляем статус курьера на {}, id курьера = {}",courierStatus,id);
            courierRepository.updateCourierStatusByCourierId(id, CourierStatus.valueOf(courierStatus));
            courier.setCourierStatus(CourierStatus.valueOf(courierStatus));
            return courier;
        } catch (IllegalArgumentException e) {
            throw new IllegalStatusException(exceptionMessage(CourierStatus.class), e);
        }
    }
}
