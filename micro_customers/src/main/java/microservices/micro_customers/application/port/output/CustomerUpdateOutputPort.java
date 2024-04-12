package microservices.micro_customers.application.port.output;

import microservices.micro_customers.application.core.domain.Customer;

public interface CustomerUpdateOutputPort {

    Customer update(Customer customer);

}

