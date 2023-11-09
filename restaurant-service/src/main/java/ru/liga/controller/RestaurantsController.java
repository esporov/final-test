package ru.liga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.liga.client.OrderFeign;
import ru.liga.domain.entity.orderService.order.KitchenStatus;
import ru.liga.domain.exception.ExceptionBody;
import ru.liga.web.dto.order.OrderDto;
import ru.liga.web.mapper.order.OrderMapper;


@Tag(name = "Restaurant Controller")
@RestController
@RequestMapping("/restaurant-service")
@RequiredArgsConstructor
@Validated
public class RestaurantsController {

    private final Logger LOGGER = LoggerFactory.getLogger(RestaurantsController.class);
    private final OrderFeign orderFeign;
    private final OrderMapper orderMapper;

    @Operation(summary = "Обновление статуса по кухне на ACCEPTED.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    @PatchMapping("/v1.0/restaurant/{id}/accept")
    public ResponseEntity<OrderDto> acceptOrder(@PathVariable("id") String orderId){
        LOGGER.info("Обновление статуса по кухне на ACCEPTED для заказа с id = {}", orderId);
        var order = orderFeign.updateKitchenStatus(orderId, KitchenStatus.ACCEPTED.name());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }

    @Operation(summary = "Обновление статуса по кухне на DENIED.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    @PatchMapping("/v1.0/restaurant/{id}/decline")
    public ResponseEntity<OrderDto> declineOrder(@PathVariable("id") String orderId){
        LOGGER.info("Обновление статуса по кухне на DENIED для заказа с id = {}", orderId);
        var order = orderFeign.updateKitchenStatus(orderId, KitchenStatus.DENIED.name());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }

    @Operation(summary = "Обновление статуса по кухне на IN_WORK.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    @PatchMapping("/v1.0/restaurant/{id}/working")
    public ResponseEntity<OrderDto> workingOnOrder(@PathVariable("id") String orderId){
        LOGGER.info("Обновление статуса по кухне на IN_WORK для заказа с id = {}", orderId);
        var order = orderFeign.updateKitchenStatus(orderId, KitchenStatus.IN_WORK.name());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }

    @Operation(summary = "Обновление статуса по кухне на DONE.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный запрос",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Некорректный запрос",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionBody.class)))
            })
    @PatchMapping("/v1.0/restaurant/{id}/done")
    public ResponseEntity<OrderDto> doneOrder(@PathVariable("id") String orderId){
        LOGGER.info("Обновление статуса по кухне на DONE для заказа с id = {}", orderId);
        var order = orderFeign.updateKitchenStatus(orderId, KitchenStatus.DONE.name());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }
}
