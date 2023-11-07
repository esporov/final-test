package ru.liga.web.dto.restaurant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.liga.domain.entity.restaurantService.restaurant.WorkStatus;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Schema(title = "Restaurant", description = "Ресторан")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    @Schema(description = "Адрес ресторана", example = "ул. Покровская 32")
    @NotNull
    @Length(max = 255, message = "Название должно быть не более 255 символов.")
    private String address;

    @Schema(description = "Статус ресторана", example = "ACTIVE")
    @NotNull(message = "Необходимо указать работает ли ресторан.")
    private WorkStatus workStatus;

    @Override
    public String toString() {
        return "RestaurantDto{" +
                "address='" + address + '\'' +
                ", workStatus=" + workStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantDto that = (RestaurantDto) o;

        if (!Objects.equals(address, that.address)) return false;
        return workStatus == that.workStatus;
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (workStatus != null ? workStatus.hashCode() : 0);
        return result;
    }
}