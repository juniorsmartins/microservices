package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.application.core.domain.Customer;

public interface MapperOut {

    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomer(CustomerEntity customerEntity);

}

