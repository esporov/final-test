package liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.liga.domain.entity.orderService.orderItem.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    @Query("select oi " +
            "from " +
            "OrderItem oi " +
            "where oi.id = :id")
    List<OrderItem> getAllOrderItemByOrderId(@Param("id") long orderId);
}
