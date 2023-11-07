package ru.liga.web.mapper.order;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.web.dto.order.OrderDto;
import ru.liga.web.mapper.Mappable;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper extends Mappable<Order, OrderDto> {
}
