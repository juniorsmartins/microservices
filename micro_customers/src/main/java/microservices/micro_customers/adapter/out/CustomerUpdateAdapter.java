package microservices.micro_customers.adapter.out;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.mapper.MapperOut;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.port.output.CustomerUpdateOutputPort;
import microservices.micro_customers.config.exception.http_404.CustomerNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerUpdateAdapter implements CustomerUpdateOutputPort {

    private final CustomerRepository customerRepository;

    private final MapperOut mapperOut;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public Customer update(@NonNull Customer customer) {
        var id = customer.getCustomerId();

        return this.customerRepository.findById(id)
            .map(entityDatabase -> {
                var entityUpdate = this.mapperOut.toCustomerEntity(customer);
                BeanUtils.copyProperties(entityUpdate, entityDatabase, "version", "customerId", "cpf", "statusCadastro",
                    "createdAt", "createdBy", "updatedAt", "updatedBy");
                return entityDatabase;
            })
            .map(this.mapperOut::toCustomer)
            .orElseThrow(() -> new CustomerNotFoundException(id));
    }

}

