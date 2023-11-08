package ru.liga.web.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.liga.domain.entity.orderService.order.DeliveryStatus;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDto {

    @NotNull
    private long restaurantId;
    @NotNull
    private DeliveryStatus deliveryStatus;

    @NotNull
    private BigDecimal price;

    @Override
    public String toString() {
        return "CustomerOrderDto{" +
                "restaurantId=" + restaurantId +
                ", deliveryStatus=" + deliveryStatus +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerOrderDto that = (CustomerOrderDto) o;

        if (restaurantId != that.restaurantId) return false;
        if (deliveryStatus != that.deliveryStatus) return false;
        return Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        int result = (int) (restaurantId ^ (restaurantId >>> 32));
        result = 31 * result + (deliveryStatus != null ? deliveryStatus.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
