package microservices.micro_customers.application.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.dto.response.CustomerSearchDtoResponse;
import microservices.micro_customers.adapter.in.filters.CustomerFilter;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.port.input.CustomerSearchOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerSearchAdapter implements CustomerSearchOutputPort {

    private final CustomerRepository customerRepository;

    @Override
    public Page<CustomerSearchDtoResponse> search(CustomerFilter customerFilter, Pageable pagination) {
        return null;
    }

}

