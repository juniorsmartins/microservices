package microservices.micro_customers.adapter.out;

import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration - CustomerUpdateAdapter")
class CustomerUpdateAdapterIntegrationTest extends AbstractTestcontainersTest {

    final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    CustomerUpdateAdapter customerUpdateAdapter;

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
    @DisplayName("cpf diferente")
    void dadoCustomerValidoComCpfDiferente_quandoUpdate_entaoRetornarCustomerSalvoSemModificarCpf() {
        var customer = factory.gerarCustomerBuilder()
            .customerId(entity1.getCustomerId())
            .build();

        var customerSalvo = this.customerUpdateAdapter.update(customer);

        Assertions.assertNotEquals(entity1.getCpf(), customer.getCpf().getCpf());
        Assertions.assertEquals(entity1.getCpf(), customerSalvo.getCpf().getCpf());
    }
}

