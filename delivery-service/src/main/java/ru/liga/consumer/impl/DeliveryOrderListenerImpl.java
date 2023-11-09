package ru.liga.consumer.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.client.OrderFeign;
import ru.liga.consumer.DeliveryOrderListener;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.deliveryService.courier.CourierStatus;
import ru.liga.domain.entity.orderService.order.DeliveryStatus;
import ru.liga.domain.entity.orderService.order.KitchenStatus;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;
import ru.liga.domain.exception.CourierNotFoundException;
import ru.liga.producer.RabbitMqProducerService;
import ru.liga.service.CourierService;
import ru.liga.util.DistanceCalculate;
import ru.liga.util.OrderWrapper;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryOrderListenerImpl implements DeliveryOrderListener, DistanceCalculate {
    private final Logger LOGGER = LoggerFactory.getLogger(DeliveryOrderListenerImpl.class);
    private final CourierService courierService;
    private final OrderFeign orderFeign;
    private final RabbitMqProducerService rabbitMqProducerService;
    private final RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = "delivery", returnExceptions = "true")
    public void receiveOrder(OrderWrapper<Order, OrderItem> order) {

        List<Courier> freeCouriers = null;

        try {
            //Пытаемся найти свободных курьеров
            LOGGER.info("Передаем заказ свободному курьеру");
            freeCouriers = courierService.getAllCourierByCourierStatus(CourierStatus.FREE.name());
            LOGGER.info("Кол-во курьеров = {}", freeCouriers.size());
        } catch (CourierNotFoundException e) {
            //Если нет свободных курьеров, то отменяем заказ, меняем статусы
            LOGGER.info("Устанавливаем kitchen статус отменен.");
            orderFeign.updateKitchenStatus(order.getOrder().getId().toString(), KitchenStatus.DENIED.name());
            LOGGER.info("Устанавливаем delivery статус отменен.");
            orderFeign.updateDeliveryStatus(order.getOrder().getId().toString(), DeliveryStatus.DENIED.name());
            LOGGER.warn("Доступных курьеров нет, отмена заказа.");
        }
        LOGGER.info("Поиск ближайшего курьера");
        //Поиск ближайшего курьера
        long courId = 0;
        double minDis = 0;
        for (Courier courier : freeCouriers) {
            double resDis = getDistance(
                    courier.getCoordinate().getLatitude(),
                    courier.getCoordinate().getLongitude(),
                    order.getOrder().getCustomer().getCoordinate().getLatitude(),
                    order.getOrder().getCustomer().getCoordinate().getLongitude());
            if (minDis == 0) {
                minDis = resDis;
                courId = courier.getId();
                continue;
            }
            if (minDis > Math.abs(resDis) && minDis != 0) {
                minDis = resDis;
                courId = courier.getId();
            }
        }
        LOGGER.info("Курьер найден с id = {}", courId);
        //Назначаем заказу свободного курьера и меняем статус у курьера
        Order delivOrder = order.getOrder();
        var courier = courierService.updateCourierStatusByCourierId(courId, CourierStatus.ON_ORDER.name());
        delivOrder.setCourier(courier);
        LOGGER.info("Заказ по id = " + delivOrder.getId() + " передан курьеру с id = " + courier.getId());
        orderFeign.updateOrderByCourier(delivOrder.getId().toString(), courier);
        //Обновляем заказ в БД
        LOGGER.info("Обновляем delivery статус заказа в БД.");
        orderFeign.updateDeliveryStatus(delivOrder.getId().toString(), DeliveryStatus.HURRIES_TO_ORDER.name());
        //Отправляем заказ в ресторан
        LOGGER.info("Отправляем пуш уведомление в ресторан.");
        order.setOrder(delivOrder);
        rabbitMqProducerService.sendOrderToRestaurant(order, "kitchen");
    }

}

