package ru.liga.web.mapper.kitchen;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.liga.domain.entity.restaurantService.item.Item;
import ru.liga.web.dto.restaurant.ItemDto;
import ru.liga.web.mapper.Mappable;

@Component
@Mapper(componentModel = "spring")
public interface ItemMapper extends Mappable<Item, ItemDto> {
}
