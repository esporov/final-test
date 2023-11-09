package ru.liga.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.orderService.order.Order;

@Component
@FeignClient(name = "order-service", url = "http://localhost:8083/order-service/order-api")
public interface OrderFeign {

    @PostMapping("/v1.0/order/id/{id}/kitchen-status/{status}")
    Order updateKitchenStatus(@PathVariable("id") String orderId, @PathVariable("status") String status);

    @PostMapping("/v1.0/order/id/{id}/delivery-status/{status}")
    Order updateDeliveryStatus(@PathVariable("id") String orderId, @PathVariable("status") String status);

    @PostMapping("/v1.0/order/id/{id}/courier")
    Order updateOrderByCourier(@PathVariable("id") String orderId, @RequestBody Courier courier);

}
