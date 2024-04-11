package microservices.micro_customers.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.adapter.mapper.MapperOut;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.port.output.CustomerSaveOutputPort;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerUpdateAdapter implements CustomerSaveOutputPort {

    private final CustomerRepository customerRepository;

    private final MapperOut mapperOut;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public Customer update(Customer customer) {

        return Optional.ofNullable(customer)
                .map(x -> {
                    System.out.println("\n\n\n---------- 4 ---------- " + x + "\n\n\n");
                    return x;
                })
            .map(this::replaceInformation)
                .map(x -> {
                    System.out.println("\n\n\n---------- 5 ---------- " + x + "\n\n\n");
                    return x;
                })
            .map(this.customerRepository::save)
                .map(x -> {
                    System.out.println("\n\n\n---------- 6 ---------- " + x + "\n\n\n");
                    return x;
                })
            .map(this.mapperOut::toCustomer)
                .map(x -> {
                    System.out.println("\n\n\n---------- 7 ---------- " + x + "\n\n\n");
                    return x;
                })
            .orElseThrow();
    }

    private CustomerEntity replaceInformation(Customer customer) {
        var customerEntity = this.searchEntity(customer.getCustomerId());
        BeanUtils.copyProperties(customer, customerEntity, "version", "cpf", "statusCadastro", "createdAt", "createdBy", "updatedAt", "updatedBy");
        return customerEntity;
    }

    private CustomerEntity searchEntity(final Long customerId) {
        return this.customerRepository.findById(customerId)
            .orElseThrow();
    }

}

