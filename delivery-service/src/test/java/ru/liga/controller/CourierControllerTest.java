package ru.liga.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.deliveryService.courier.CourierStatus;
import ru.liga.service.CourierService;
import ru.liga.web.dto.delivery.CourierDto;
import ru.liga.web.mapper.delivery.CourierMapper;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CourierControllerTest {

    private final Logger LOGGER = LoggerFactory.getLogger(CourierControllerTest.class);
    @Mock
    CourierService courierService;

    @Mock
    CourierMapper courierMapper;

    @InjectMocks
    CourierController courierController;

    @Test
    @DisplayName("PATCH /v1.0/courier/{id}/status/{status} Возвращает HTTP-OK и обновленного курьера")
    void updateCourierStatusByCourierId_validIdAndStatus_ReturnHttpOk() {

        //given
        long id = new Random().nextLong();
        String status = CourierStatus.FREE.toString();
        var courier = new Courier();
        courier.setCourierStatus(CourierStatus.valueOf(status));
        courier.setId(id);
        courier.setPhone("+790845566685");
        var courierDto = new CourierDto();
        courierDto.setCourierStatus(courier.getCourierStatus());
        courierDto.setPhone(courier.getPhone());
        when(courierService.updateCourierStatusByCourierId(id, status))
                .thenReturn(courier);
        when(courierMapper.toDto(courier)).thenReturn(courierDto);

        //when
        var responseEntity = courierController.updateCourierStatusByCourierId(id, status);

        //then
        assertNotNull(responseEntity);
        LOGGER.debug("Проверка на NotNul пройдена");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        LOGGER.debug("Проверка на cоответсвие статусов");
        assertEquals(courierDto.getCourierStatus(), responseEntity.getBody().getCourierStatus());
        LOGGER.debug("Проверка на HTTP OK пройдена");
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        LOGGER.debug("Проверка на MediaType пройдена");
        assertEquals(courierDto,responseEntity.getBody());
        LOGGER.debug("Проверка на ResponseBody пройдена");
    }
}