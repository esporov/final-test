package ru.liga.producer;

import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;
import ru.liga.util.OrderWrapper;

public interface RabbitMqProducerService {

    void sendOrderToRestaurant(OrderWrapper<Order, OrderItem> order, String routingKey);

}
