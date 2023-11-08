package ru.liga.consumer;

import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;
import ru.liga.util.OrderWrapper;

public interface DeliveryOrderListener {

    void receiveOrder(OrderWrapper<Order, OrderItem> order);

}
