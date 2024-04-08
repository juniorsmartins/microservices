package microservices.micro_customers.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.port.output.CustomerDeleteOutputPort;
import microservices.micro_customers.config.exception.http_404.CustomerNotFoundException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerDeleteAdapter implements CustomerDeleteOutputPort {

    private final CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Modifying
    @Override
    public void delete(final Long id) {

        this.customerRepository.findById(id)
            .ifPresentOrElse(this.customerRepository::delete,
                () -> {throw new CustomerNotFoundException(id);}
            );
    }

}

