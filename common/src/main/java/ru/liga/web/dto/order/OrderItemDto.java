package ru.liga.web.dto.order;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto implements Serializable {

    private long itemId;

    private int quantity;

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "itemId=" + itemId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItemDto itemDto = (OrderItemDto) o;

        if (itemId != itemDto.itemId) return false;
        return quantity == itemDto.quantity;
    }

    @Override
    public int hashCode() {
        int result = (int) (itemId ^ (itemId >>> 32));
        result = 31 * result + quantity;
        return result;
    }
}
