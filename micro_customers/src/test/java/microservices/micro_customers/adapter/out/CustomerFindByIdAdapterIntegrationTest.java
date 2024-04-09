package microservices.micro_customers.adapter.out;

import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration - CustomerFindByIdAdapter")
class CustomerFindByIdAdapterIntegrationTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    CustomerFindByIdAdapter customerFindByIdAdapter;

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
    @DisplayName("customerId válido")
    void dadoCustomerIdValido_quandoFindById_entaoRetornarCustomer() {
        var encontrado = this.customerFindByIdAdapter.findById(entity1.getCustomerId());
        Assertions.assertInstanceOf(Customer.class, encontrado);
        Assertions.assertNotNull(encontrado);
    }

    @Test
    @DisplayName("customerId inexistente")
    void dadoCustomerIdInexistente_quandoFindById_entaoLancarException() {
        Executable acao = () -> this.customerFindByIdAdapter.findById(0L);
        Assertions.assertThrows(NoSuchElementException.class, acao);
    }

}

