package ru.liga.web.dto.restaurant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.liga.domain.entity.restaurantService.item.ItemStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Schema(title = "Item", description = "Позиция меню")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    @Schema(description = "Название позиции меню", example = "Чизбургер")
    @NotNull(message = "Название позиции должно быть заполнено.")
    @Length(max = 255, message = "Название должно быть не более 255 символов.")
    private String name;

    @Schema(description = "Цена", example = "69")
    @NotNull(message = "Цена должна быть указана.")
    @Positive(message = "Цена должна быть больше нуля.")
    private int price;

    @Schema(description = "Стоп лист позиции", example = "AVAILABLE")
    @NotNull(message = "Необходимо указать находится ли позиция в стоп-листе.")
    private ItemStatus itemStatus;

    @Override
    public String toString() {
        return "ItemDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", itemStatus=" + itemStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDto itemDto = (ItemDto) o;

        if (price != itemDto.price) return false;
        if (!Objects.equals(name, itemDto.name)) return false;
        return itemStatus == itemDto.itemStatus;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + (itemStatus != null ? itemStatus.hashCode() : 0);
        return result;
    }
}
