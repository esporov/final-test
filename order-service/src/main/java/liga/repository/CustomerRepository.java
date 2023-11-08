package liga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.liga.domain.entity.orderService.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
