package ru.liga.web.mapper.delivery;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.liga.domain.entity.deliveryService.coordinate.CourierCoordinate;
import ru.liga.web.dto.delivery.CourierCoordinateDto;
import ru.liga.web.mapper.Mappable;


@Component
@Mapper(componentModel = "spring")
public interface CourierCoordinateMapper extends Mappable<CourierCoordinate, CourierCoordinateDto> {
}
