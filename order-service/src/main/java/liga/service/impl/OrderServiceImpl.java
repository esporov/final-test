package liga.service.impl;

import liga.client.RestaurantFeign;
import liga.repository.OrderRepository;
import liga.service.CustomerService;
import liga.service.OrderItemService;
import liga.service.OrderService;
import liga.service.RabbitMqProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.orderService.order.DeliveryStatus;
import ru.liga.domain.entity.orderService.order.KitchenStatus;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;
import ru.liga.domain.entity.restaurantService.item.Item;
import ru.liga.domain.exception.IllegalStatusException;
import ru.liga.domain.exception.OrderNotFoundException;

import ru.liga.util.IllegalStatusExceptionMessage;
import ru.liga.util.OrderWrapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService, IllegalStatusExceptionMessage {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final RestaurantFeign restaurantFeign;
    private final RabbitMqProducerService rabbitMqProducerService;
    private final OrderItemService orderItemService;


    @Override
    public Order getOrderByOrderId(String orderId) {
        var uuid = UUID.fromString(orderId);
        LOGGER.info("Поиск заказа по id = {}.",orderId);
        return orderRepository.findById(uuid)
                .orElseThrow(() -> new OrderNotFoundException(
                        "Заказ по id = " + orderId + " не найден."));
    }

    public Order updateKitchenStatus(String id, String status) {
        var order = getOrderByOrderId(id);
        try {
            var validStatus = KitchenStatus.valueOf(status);
            order.setKitchenStatus(validStatus);
            LOGGER.info("Обновляем Kitchen статус заказа по id = {}, на статус = {}.",id, validStatus);
            return orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new IllegalStatusException(exceptionMessage(KitchenStatus.class), e);
        }
    }

    public Order updateDeliveryStatus(String id, String status) {
        var order = getOrderByOrderId(id);
        try {
            var validStatus = DeliveryStatus.valueOf(status);
            order.setDeliveryStatus(validStatus);
            LOGGER.info("Обновляем Delivery статус заказа по id = {}, на статус = {}.",id, validStatus);
            return orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new IllegalStatusException(exceptionMessage(DeliveryStatus.class), e);
        }
    }

    @Override
    public Order updateOrderByCourier(String orderId, Courier courier) {
        var order = getOrderByOrderId(orderId);
        order.setCourier(courier);
        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public OrderWrapper<Order,OrderItem>  createOrderByCustomerIdAndRestaurantId(
            long customerId, long restaurantId, List<OrderItem> items) {

        LOGGER.info("Поиск клиента по id = {}.",customerId);
        var customer = customerService.getCustomerById(customerId);

        //Преобразование OrderItem
        List<OrderItem> newItems = new ArrayList<>();
        BigDecimal price = BigDecimal.ZERO;
        for (OrderItem orderItem : items) {
            long itemId = orderItem.getItem().getId();
            LOGGER.info("Поиск позиции меню по id = {}", itemId);
            Item menuItem = restaurantFeign.getItemById(itemId);
            OrderItem newItem = OrderItem.builder()
                    .item(menuItem)
                    .quantity(orderItem.getQuantity())
                    .price(menuItem.getPrice())
                    .build();
            newItems.add(newItem);
            //Вычисляем суммарную стоимость заказа
            price = price.add(menuItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
        }

        //Создание Order
        var order = Order.builder()
                .customer(customer)
                .restaurantId(restaurantId)
                .kitchenStatus(KitchenStatus.ACCEPTED)
                .deliveryStatus(DeliveryStatus.COURIER_SEARCH)
                .createDate(Date.from(Instant.now()))
                .price(price)
                .build();
        LOGGER.info("Сохранение заказа в БД = {}", order);
        order = orderRepository.save(order);
        //Добавление к позициям заказа - заказ Order (связываем с его ид)
        for (OrderItem item : newItems) {
            item.setOrder(order);
        }

        //Создание orderItem
        LOGGER.info("Сохранение позиций меню в заказе в БД");
        newItems = orderItemService.createOrderItems(newItems);

        //Обертка
        OrderWrapper<Order,OrderItem> fullOrder = new OrderWrapper<>();
        fullOrder.setOrder(order);
        fullOrder.setItems(newItems);

        rabbitMqProducerService.sendMessage(fullOrder, "delivery");

        return fullOrder;
    }
}
