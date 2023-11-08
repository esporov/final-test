package ru.liga.service;




import ru.liga.domain.entity.restaurantService.item.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

    Item getItemById(long itemId);

}
