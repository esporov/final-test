package liga.controller;

import liga.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;
import ru.liga.util.OrderWrapper;
import ru.liga.web.dto.order.OrderDto;
import ru.liga.web.dto.order.OrderItemDto;
import ru.liga.web.mapper.order.OrderItemMapper;
import ru.liga.web.mapper.order.OrderMapper;

import java.util.List;

@RestController
@RequestMapping("/order-service/order-api")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @GetMapping("v1.0/order/id/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") String id) {
        var order = orderService.getOrderByOrderId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }

    @PatchMapping("/v1.0/order/id/{id}/kitchen-status/{status}")
    public ResponseEntity<Order> updateKitchenStatus(@PathVariable("id") String id,
                                                     @PathVariable("status") String status) {
        LOGGER.info("Обновляем Kitchen статус заказа по id = {}, на статус = {}.", id, status);
        var order = orderService.updateKitchenStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(order);

    }

    @PatchMapping("/v1.0/order/id/{id}/courier")
    public ResponseEntity<Order> updateOrderByCourier(@PathVariable("id") String id,
                                                      @RequestBody Courier courier) {
        LOGGER.info("Устанавливаем курьера с id = {}, на заказ с id = {}.", courier.getId(), id);
        var order = orderService.updateOrderByCourier(id, courier);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(order);
    }

    @PatchMapping("/v1.0/order/id/{id}/delivery-status/{status}")
    public ResponseEntity<Order> updateDeliveryStatus(@PathVariable("id") String id,
                                                      @PathVariable("status") String status) {
        LOGGER.info("Обновляем Delivery статус заказа по id = {}, на статус = {}.", id, status);
        var order = orderService.updateDeliveryStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(order);

    }

    @PostMapping("/v1.0/order")
    public ResponseEntity<OrderWrapper<OrderDto, OrderItemDto>> createOrderByCustomerIdAndRestaurantId(
            @RequestParam("customerId") long customerId,
            @RequestParam("restaurantId") long restaurantId,
            @Validated @RequestBody List<OrderItemDto> items) {
        LOGGER.info("Создание заказа для клеинта с id = {}, из ресторана с id = {}", customerId, restaurantId);
        OrderWrapper<Order, OrderItem> fullOrder =
                orderService.createOrderByCustomerIdAndRestaurantId(
                        customerId, restaurantId, orderItemMapper.toEntity(items));

        OrderWrapper<OrderDto, OrderItemDto> fullOrderDto = new OrderWrapper<>();
        fullOrderDto.setOrder(orderMapper.toDto(fullOrder.getOrder()));
        fullOrderDto.setItems(orderItemMapper.toDto(fullOrder.getItems()));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(fullOrderDto);
    }
}
