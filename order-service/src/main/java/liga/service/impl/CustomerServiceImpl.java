package liga.service.impl;

import liga.repository.CustomerRepository;
import liga.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.domain.entity.orderService.customer.Customer;
import ru.liga.domain.exception.CustomerNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Клиента с id = " + id + " не существует."));
    }


}
