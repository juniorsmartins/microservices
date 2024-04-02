package microservices.micro_customers.application.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.port.input.CustomerCreateInputPort;
import microservices.micro_customers.application.port.output.CustomerSaveOutputPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerCreateUseCase implements CustomerCreateInputPort {

    private final CustomerSaveOutputPort customerSaveOutputPort;

    @Override
    public Customer create(Customer customer) {

        return Optional.ofNullable(customer)
            .map(this.customerSaveOutputPort::save)
            .orElseThrow();
    }

}

