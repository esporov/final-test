package liga.service.impl;

import liga.repository.OrderItemRepository;
import liga.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;
import ru.liga.domain.exception.OrderItemNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem getOrderItemById(long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new OrderItemNotFoundException(
                        "Позиция заказа по id = " + orderItemId + " не найдена."));
    }

    /*@Override
    public List<OrderItem> getAllOrderItemByOrderId(long orderId) {
        orderService.getOrderByOrderId(orderId);
        var orderList = orderItemRepository.getAllOrderItemByOrderId(orderId);
        if (orderList.size() == 0) {
            throw new OrderItemNotFoundException("Список заказа пуст");
        }
        return orderList;
    }*/

    @Transactional
    @Override
    public List<OrderItem> createOrderItems(List<OrderItem> items) {
        return orderItemRepository.saveAll(items);
    }
}
