package liga.producer.impl;

import liga.producer.RabbitMqProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;
import ru.liga.util.OrderWrapper;

@Service
@RequiredArgsConstructor
@Transactional
public class RabbitMqProducerServiceImpl implements RabbitMqProducerService {

    private final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducerServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrderToCourier(OrderWrapper<Order, OrderItem> order, String routingKey) {
        String str = String.format("Заказ отправлен в очередь для курьеров. ->%s", order);
        rabbitTemplate.convertAndSend("KitchenDeliveryExchange", routingKey, order);
        LOGGER.info(str);
    }

}
