package ru.liga.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.liga.domain.entity.deliveryService.courier.Courier;

@Component
@FeignClient(name = "order-service", url = "http://localhost:8083/order-service/order-api")
public interface OrderFeign {

    @PutMapping("/v1.0/order/id/{id}/delivery-status/{status}")
    void updateDeliveryStatus(@PathVariable("id") String orderId, @PathVariable String status);

    @PutMapping("/v1.0/order/id/{id}/kitchen-status/{status}")
    void updateKitchenStatus(@PathVariable("id") String orderId, @PathVariable String status);

    @PatchMapping("/v1.0/order/id/{id}/courier")
    void updateOrderByCourier(@PathVariable("id") String orderId, @RequestBody Courier courier);

}
