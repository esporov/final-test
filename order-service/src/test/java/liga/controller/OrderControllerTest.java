package liga.controller;

import liga.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.http.ResponseEntity;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.exception.ExceptionBody;
import ru.liga.domain.exception.OrderNotFoundException;
import ru.liga.web.dto.order.OrderDto;
import ru.liga.web.mapper.order.OrderItemMapper;
import ru.liga.web.mapper.order.OrderMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class OrderControllerTest {

    @Mock
    OrderService orderService;
    @Mock
    OrderMapper orderMapper;

    @InjectMocks
    OrderController orderController;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderControllerTest.class);

    @Test
    @DisplayName("GET /v1.0/order/id/{id} Возвращает HTTP-OK и заказ")
    void getOrderById_validId_returnValidResponseEntity() {

        //given
        var uuid = UUID.randomUUID();
        var validOrder = new Order();
        var validOrderDto = new OrderDto();
        validOrderDto.setRestaurantId(2);
        when(orderService.getOrderByOrderId(uuid.toString())).thenReturn(validOrder);
        when(orderMapper.toDto(validOrder)).thenReturn(validOrderDto);

        //when
        var responseEntity = orderController.getOrderById(uuid.toString());

        //then
        assertNotNull(responseEntity);
        LOGGER.debug("Проверка на NotNul пройдена");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        LOGGER.debug("Проверка на HTTP OK пройдена");
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        LOGGER.debug("Проверка на MediaType пройдена");
        assertEquals(validOrderDto,responseEntity.getBody());
        LOGGER.debug("Проверка на ResponseBody пройдена");
    }
}