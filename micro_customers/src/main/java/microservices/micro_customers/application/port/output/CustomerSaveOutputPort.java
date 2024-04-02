package microservices.micro_customers.application.port.output;

import microservices.micro_customers.application.core.domain.Customer;

public interface CustomerSaveOutputPort {

    Customer save(Customer customer);

}

