package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.in.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.in.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.application.core.domain.Customer;

public interface MapperIn {

    Customer toCustomer(CustomerCreateDtoRequest customerCreateDtoRequest);

    CustomerCreateDtoResponse toCustomerCreateDtoResponse(Customer customer);

}

