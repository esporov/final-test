package ru.liga.consumer.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import ru.liga.client.OrderFeign;
import ru.liga.consumer.RestaurantOrderListener;
import ru.liga.domain.entity.orderService.order.KitchenStatus;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;

import ru.liga.util.OrderWrapper;



@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantOrderListenerImpl implements RestaurantOrderListener {
    private final OrderFeign orderFeign;

    private final Logger LOGGER = LoggerFactory.getLogger(RestaurantOrderListenerImpl.class);

    @RabbitListener(queues = "kitchen")
    public void receiveOrder(OrderWrapper<Order, OrderItem> order) {
        orderFeign.updateKitchenStatus(order.getOrder().getId().toString(), KitchenStatus.IN_WORK.name());
        LOGGER.info("Заказ принят, меняем статус на IN_WORK");
    }
}
