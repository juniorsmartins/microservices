package microservices.micro_customers.application.core.usecase;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.port.input.CustomerUpdateInputPort;
import microservices.micro_customers.application.port.output.CustomerUpdateOutputPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerUpdateUseCase implements CustomerUpdateInputPort {

    private final CustomerUpdateOutputPort customerUpdateOutputPort;

    @Override
    public Customer update(@NonNull Customer customer) {

        return Optional.ofNullable(customer)
            .map(this.customerUpdateOutputPort::update)
            .orElseThrow();
    }

}

