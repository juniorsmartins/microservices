package microservices.micro_customers.application.core.usecase;

import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.core.domain.tipos.CadastroPessoaFisica;
import microservices.micro_customers.config.exception.http_409.CpfDuplicityException;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration - CustomerCreateUseCase")
class CustomerCreateUseCaseIntegrationTest extends AbstractTestcontainersTest {

    final FactoryObjectMother factory = FactoryObjectMother.singleton();
    
    @Autowired
    CustomerCreateUseCase customerCreateUseCase;

    @Autowired
    CustomerRepository customerRepository;

    CustomerEntity entity1;

    @BeforeEach
    void setUp() {
        entity1 = factory.gerarCustomerEntityBuilder().build();
        this.customerRepository.save(entity1);
    }

    @AfterEach
    void tearDown() {
        this.customerRepository.deleteAll();
    }

    @Test
    @DisplayName("CPF duplicado")
    void dadoCpfDuplicado_quandoCreate_entaoLancarException() {
        var customer = factory.gerarCustomerBuilder().cpf(new CadastroPessoaFisica(entity1.getCpf())).build();
        Executable acao = () -> this.customerCreateUseCase.create(customer);
        Assertions.assertThrows(CpfDuplicityException.class, acao);
    }

}

