package ru.liga.web.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.domain.entity.deliveryService.courier.Courier;
import ru.liga.domain.entity.orderService.customer.Customer;
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

    private Courier courier;

    private Customer customer;

    @Override
    public String toString() {
        return "OrderDto{" +
                "restaurantId=" + restaurantId +
                ", kitchenStatus=" + kitchenStatus +
                ", deliveryStatus=" + deliveryStatus +
                ", price=" + price +
                ", courier=" + courier +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDto orderDto = (OrderDto) o;

        if (restaurantId != orderDto.restaurantId) return false;
        if (kitchenStatus != orderDto.kitchenStatus) return false;
        if (deliveryStatus != orderDto.deliveryStatus) return false;
        if (!Objects.equals(price, orderDto.price)) return false;
        if (!Objects.equals(courier, orderDto.courier)) return false;
        return Objects.equals(customer, orderDto.customer);
    }

    @Override
    public int hashCode() {
        int result = (int) (restaurantId ^ (restaurantId >>> 32));
        result = 31 * result + (kitchenStatus != null ? kitchenStatus.hashCode() : 0);
        result = 31 * result + (deliveryStatus != null ? deliveryStatus.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (courier != null ? courier.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
    }
}
