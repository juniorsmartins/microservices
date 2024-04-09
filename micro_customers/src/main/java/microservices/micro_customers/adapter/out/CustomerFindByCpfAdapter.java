package microservices.micro_customers.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.mapper.MapperOut;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.port.output.CustomerFindByCpfOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerFindByCpfAdapter implements CustomerFindByCpfOutputPort {

    private final CustomerRepository customerRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Customer findByCpf(final String cpf) {

        return this.customerRepository.findByCpf(cpf)
            .map(this.mapperOut::toCustomer)
            .orElseThrow();
    }

}

