package microservices.micro_customers.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.dto.response.CustomerSearchDtoResponse;
import microservices.micro_customers.adapter.in.filters.CustomerFilter;
import microservices.micro_customers.adapter.mapper.MapperOut;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.adapter.out.repository.specs.CustomerEntitySpec;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.port.output.CustomerSearchOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerSearchAdapter implements CustomerSearchOutputPort {

    private final CustomerRepository customerRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Page<CustomerSearchDtoResponse> search(final CustomerFilter customerFilter, final Pageable pagination) {

        return Optional.ofNullable(customerFilter)
            .map(parametros -> this.customerRepository
                .findAll(CustomerEntitySpec.consultarDinamicamente(parametros), pagination))
            .map(pagina -> pagina.map(this.mapperOut::toCustomerSearchDtoResponse))
            .orElseThrow();
    }

}

