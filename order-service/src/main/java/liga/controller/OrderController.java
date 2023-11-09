package liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import ru.liga.domain.exception.ExceptionBody;
import ru.liga.util.OrderWrapper;
import ru.liga.web.dto.order.OrderDto;
import ru.liga.web.dto.order.OrderItemDto;
import ru.liga.web.mapper.order.OrderItemMapper;
import ru.liga.web.mapper.order.OrderMapper;

import java.util.List;

@Tag(name = "Order controller")
@RestController
@RequestMapping("/order-service/order-api")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

//    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_RESTAURANT','ROLE_COURIER')")
    @Operation(summary = "Получить заказ по UUID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    @GetMapping("v1.0/order/id/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") String id) {
        var order = orderService.getOrderByOrderId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }

//    @PreAuthorize("hasRole('ROLE_RESTAURANT')")
    @PostMapping("/v1.0/order/id/{id}/kitchen-status/{status}")
    @Operation(summary = "Обновить статус заказа по ресторану (Kitchen status).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    public ResponseEntity<Order> updateKitchenStatus(@PathVariable("id") String id,
                                                     @PathVariable("status") String status) {
        LOGGER.info("Обновляем Kitchen статус заказа по id = {}, на статус = {}.", id, status);
        var order = orderService.updateKitchenStatus(id, status);
        LOGGER.info("Обновили Kitchen статус заказа по id = {}, на статус = {}.", id, status);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(order);

    }
//    @PreAuthorize("hasRole('ROLE_COURIER')")
    @PostMapping("/v1.0/order/id/{id}/delivery-status/{status}")
    @Operation(summary = "Обновить статус заказа по доставке (Delivery status).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    public ResponseEntity<Order> updateDeliveryStatus(@PathVariable("id") String id,
                                                      @PathVariable("status") String status) {
        LOGGER.info("Обновляем Delivery статус заказа по id = {}, на статус = {}.", id, status);
        var order = orderService.updateDeliveryStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(order);
    }

//    @PreAuthorize("hasRole('ROLE_COURIER')")
    @PostMapping("/v1.0/order/id/{id}/courier")
    @Operation(summary = "Установить курьера для заказа.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    public ResponseEntity<Order> updateOrderByCourier(@PathVariable("id") String id,
                                                      @RequestBody Courier courier) {
        LOGGER.info("Устанавливаем курьера с id = {}, на заказ с id = {}.", courier.getId(), id);
        var order = orderService.updateOrderByCourier(id, courier);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(order);
    }

//    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/v1.0/order")
    @Operation(summary = "Создать заказ.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    public ResponseEntity<OrderWrapper<OrderDto, OrderItemDto>> createOrderByCustomerIdAndRestaurantId(
            @RequestParam("customerId") long customerId,
            @RequestParam("restaurantId") long restaurantId,
            @Validated @RequestBody List<OrderItemDto> items) {
        LOGGER.info("Создание заказа для клиента с id = {}, из ресторана с id = {}", customerId, restaurantId);
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
