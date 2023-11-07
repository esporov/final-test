package ru.liga.web.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.orderService.order.DeliveryStatus;
import ru.liga.domain.entity.orderService.order.KitchenStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private long restaurantId;

    private KitchenStatus kitchenStatus;

    private DeliveryStatus deliveryStatus;

    private BigDecimal price;

    private long courierId;

    @Override
    public String toString() {
        return "OrderDto{" +
                "restaurantId=" + restaurantId +
                ", kitchenStatus=" + kitchenStatus +
                ", deliveryStatus=" + deliveryStatus +
                ", price=" + price +
                ", courierId=" + courierId +
                '}';
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
