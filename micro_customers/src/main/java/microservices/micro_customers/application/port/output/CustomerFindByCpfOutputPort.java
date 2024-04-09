package microservices.micro_customers.application.port.output;

import microservices.micro_customers.application.core.domain.Customer;

import java.util.Optional;

public interface CustomerFindByCpfOutputPort {

    Optional<Customer> findByCpf(String cpf);

}

