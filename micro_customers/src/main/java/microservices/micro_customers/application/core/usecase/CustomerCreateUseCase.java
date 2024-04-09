package microservices.micro_customers.application.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.port.input.CustomerCreateInputPort;
import microservices.micro_customers.application.port.output.CustomerFindByCpfOutputPort;
import microservices.micro_customers.application.port.output.CustomerSaveOutputPort;
import microservices.micro_customers.config.exception.http_409.CpfDuplicityException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerCreateUseCase implements CustomerCreateInputPort {

    private final CustomerSaveOutputPort customerSaveOutputPort;

    private final CustomerFindByCpfOutputPort customerFindByCpfOutputPort;

    @Override
    public Customer create(Customer customer) {

        return Optional.ofNullable(customer)
            .map(this::checkCpfDuplicity)
            .map(Customer::addStatusCadastroIniciado)
            .map(this.customerSaveOutputPort::save)
            .orElseThrow();
    }

    private Customer checkCpfDuplicity(Customer customer) {
        var cpf = customer.getCpf().getCpf();

        this.customerFindByCpfOutputPort.findByCpf(cpf)
            .filter(encontrado -> !encontrado.getCustomerId().equals(customer.getCustomerId()))
            .ifPresent(duplicado -> {throw new CpfDuplicityException(cpf);});

        return customer;
    }

}

