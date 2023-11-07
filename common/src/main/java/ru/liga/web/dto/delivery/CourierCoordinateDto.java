package ru.liga.web.dto.delivery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(description = "Courier Coordinate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourierCoordinateDto {

    @NotNull
    private float latitude;

    @NotNull
    private float longitude;

    @Override
    public String toString() {
        return "CoordinateDto{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourierCoordinateDto that = (CourierCoordinateDto) o;

        if (Float.compare(that.latitude, latitude) != 0) return false;
        return Float.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        int result = (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        result = 31 * result + (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        return result;
    }
}