package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.dto.request.CustomerUpdateDtoRequest;
import microservices.micro_customers.adapter.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.adapter.dto.response.CustomerUpdateDtoResponse;
import microservices.micro_customers.application.core.domain.Customer;

public interface MapperIn {

    Customer toCustomerCreate(CustomerCreateDtoRequest customerCreateDtoRequest);

    CustomerCreateDtoResponse toCustomerCreateDtoResponse(Customer customer);

    Customer toCustomerUpdate(CustomerUpdateDtoRequest customerUpdateDtoRequest);

    CustomerUpdateDtoResponse toCustomerUpdateDtoResponse(Customer customer);

}

