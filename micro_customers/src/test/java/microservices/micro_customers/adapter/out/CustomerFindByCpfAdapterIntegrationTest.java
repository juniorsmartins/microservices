package microservices.micro_customers.adapter.out;

import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.core.domain.Customer;
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
@DisplayName("Integration - CustomerFindByCpfAdapter")
class CustomerFindByCpfAdapterIntegrationTest extends AbstractTestcontainersTest {

    final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    CustomerFindByCpfAdapter customerFindByCpfAdapter;

    @Autowired
    CustomerRepository customerRepository;

    CustomerEntity entity1;

    @BeforeEach
    void setUp() {
        entity1 = factory.gerarCustomerEntityBuilder().build();
        this.customerRepository.save(entity1);

        var entity2 = factory.gerarCustomerEntityBuilder().build();
        this.customerRepository.save(entity2);
    }

    @AfterEach
    void tearDown() {
        this.customerRepository.deleteAll();
    }

    @Test
    @DisplayName("CPF válido")
    void dadoCPFValido_quandoFindByCpf_entaoRetornarCustomer() {
        var encontrado = this.customerFindByCpfAdapter.findByCpf(entity1.getCpf());
        Assertions.assertInstanceOf(Customer.class, encontrado.get());
        Assertions.assertNotNull(encontrado);
    }

}

