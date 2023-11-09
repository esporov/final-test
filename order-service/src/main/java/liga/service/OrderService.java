package liga.service;

import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;
import ru.liga.util.OrderWrapper;

import java.util.List;

public interface OrderService {

    OrderWrapper<Order, OrderItem> createOrderByCustomerIdAndRestaurantId(
            long customerId, long restaurantId, List<OrderItem> items);

    Order getOrderByOrderId(String orderId);

    Order updateKitchenStatus(String id, String status);

    Order updateDeliveryStatus(String id, String status);

    Order updateOrderByCourier(String orderId, Courier courier);
}
