package ru.liga.web.mapper.customer;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.liga.domain.entity.orderService.order.Order;
import ru.liga.web.dto.customer.CustomerOrderDto;
import ru.liga.web.mapper.Mappable;

@Mapper(componentModel = "spring")
@Component
public interface CustomerOrderMapper extends Mappable<Order, CustomerOrderDto> {
}
