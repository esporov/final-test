package ru.liga.producer.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;
import ru.liga.producer.RabbitMqProducerService;
import ru.liga.util.OrderWrapper;

@Service
@RequiredArgsConstructor
@Transactional
public class RabbitMqProducerServiceImpl implements RabbitMqProducerService {

    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducerServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrderToRestaurant(OrderWrapper<Order, OrderItem> order, String routingKey) {
        rabbitTemplate.convertAndSend("KitchenDeliveryExchange", routingKey, order);
        LOGGER.info("Заказ отправлен в очередь для курьеров. -> {}", order);
    }

}
