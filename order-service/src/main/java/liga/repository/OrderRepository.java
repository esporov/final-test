package liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.domain.entity.orderService.order.Order;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

//    List<Order> findOrdersByCustomerCustomer_Id(long customerId);
//    List<Order> findOrdersByCustomer_Id(long customerId);
//
//    List<Order> findOrdersByCourier_Id(long courierId);
//    List<Order> findOrdersByRestaurantId(long restaurantId);




}
