package microservices.micro_customers.application.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.port.input.CustomerUpdateInputPort;
import microservices.micro_customers.application.port.output.CustomerFindByIdOutputPort;
import microservices.micro_customers.application.port.output.CustomerSaveOutputPort;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerUpdateUseCase implements CustomerUpdateInputPort {

    private final CustomerFindByIdOutputPort customerFindByIdOutputPort;

    private final CustomerSaveOutputPort customerSaveOutputPort;

    @Override
    public Customer update(Customer customer) {

        return Optional.ofNullable(customer)
            .map(this::replaceInformation)
            .map(this.customerSaveOutputPort::save)
            .orElseThrow();
    }

    private Customer replaceInformation(Customer customer) {
        var customerDatabase = this.customerFindByIdOutputPort.findById(customer.getCustomerId());
        BeanUtils.copyProperties(customer, customerDatabase, "version", "cpf", "statusCadastro", "createdAt", "createdBy", "updatedAt", "updatedBy");
        return customerDatabase;
    }

}

