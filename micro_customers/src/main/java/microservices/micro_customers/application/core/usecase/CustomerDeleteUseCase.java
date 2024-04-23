package microservices.micro_customers.application.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.application.port.input.CustomerDeleteInputPort;
import microservices.micro_customers.application.port.output.CustomerDeleteOutputPort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerDeleteUseCase implements CustomerDeleteInputPort {

    private final CustomerDeleteOutputPort customerDeleteOutputPort;

    @Override
    public void delete(final Long customerId) {

        Optional.ofNullable(customerId)
            .ifPresentOrElse(this.customerDeleteOutputPort::delete,
                () -> {throw new NoSuchElementException();}
            );
    }

}

