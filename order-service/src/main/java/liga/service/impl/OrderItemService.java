package liga.service.impl;


import ru.liga.domain.entity.orderService.orderItem.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem getOrderItemById(long orderItemId);

    List<OrderItem> createOrderItems(List<OrderItem> items);
}
