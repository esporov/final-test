package liga.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.liga.domain.entity.restaurantService.item.Item;

@Component
@FeignClient(name = "restaurant-service", url = "http://localhost:8081/restaurant-service")
public interface RestaurantFeign {

    @GetMapping("/v1.0/item-feign/id/{id}")
    Item getItemById(@PathVariable("id") long id);
}
