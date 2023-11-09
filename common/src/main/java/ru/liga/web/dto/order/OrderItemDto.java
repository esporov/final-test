package ru.liga.web.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Schema(title = "Позиция меню", description = "Блюдо")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto implements Serializable {

    @Schema(title = "Идентификатор блюда", description = "1")
    @NotNull
    private long itemId;

    @Schema(title = "Количество", description = "4")
    @NotNull
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
