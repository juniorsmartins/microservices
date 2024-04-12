package microservices.micro_customers.application.port.input;

import microservices.micro_customers.application.core.domain.Customer;

public interface CustomerUpdateInputPort {

    Customer update(Customer customer);

}

