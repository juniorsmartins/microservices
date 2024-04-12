package microservices.micro_customers.application.core.usecase;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.port.input.CustomerUpdateInputPort;
import microservices.micro_customers.application.port.output.CustomerSaveOutputPort;
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
                .map(x -> {
                    System.out.println("\n\n\n---------- 3 ---------- " + x + "\n\n\n");
                    return x;
                })
            .map(this.customerUpdateOutputPort::save)
                .map(x -> {
                    System.out.println("\n\n\n---------- 8 ---------- " + x + "\n\n\n");
                    return x;
                })
            .orElseThrow();
    }

}

