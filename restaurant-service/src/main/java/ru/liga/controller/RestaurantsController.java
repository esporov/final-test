package ru.liga.controller;

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
import ru.liga.web.dto.order.OrderDto;
import ru.liga.web.mapper.order.OrderMapper;


@Tag(name = "Restaurant Controller")
@RestController
@RequestMapping("/restaurant-api")
@RequiredArgsConstructor
@Validated
public class RestaurantsController {

    private final Logger LOGGER = LoggerFactory.getLogger(RestaurantsController.class);
    private final OrderFeign orderFeign;
    private final OrderMapper orderMapper;

    @PutMapping("/v1.0/restaurant/{id}/accept")
    public ResponseEntity<OrderDto> acceptOrder(@PathVariable("id") String orderId){
        var order = orderFeign.updateKitchenStatus(orderId, KitchenStatus.ACCEPTED.name());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }

    @PutMapping("/v1.0/restaurant/{id}/decline")
    public ResponseEntity<OrderDto> declineOrder(@PathVariable("id") String orderId){
        var order = orderFeign.updateKitchenStatus(orderId, KitchenStatus.DENIED.name());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }

    @PutMapping("/v1.0/restaurant/{id}/working")
    public ResponseEntity<OrderDto> workingOnOrder(@PathVariable("id") String orderId){
        var order = orderFeign.updateKitchenStatus(orderId, KitchenStatus.IN_WORK.name());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }

    @PutMapping("/v1.0/restaurant/{id}/done")
    public ResponseEntity<OrderDto> doneOrder(@PathVariable("id") String orderId){
        var order = orderFeign.updateKitchenStatus(orderId, KitchenStatus.DONE.name());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderMapper.toDto(order));
    }


}
