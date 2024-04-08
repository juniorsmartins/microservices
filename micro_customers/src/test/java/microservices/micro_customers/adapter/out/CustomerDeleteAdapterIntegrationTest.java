package microservices.micro_customers.adapter.out;

import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.config.exception.http_404.CustomerNotFoundException;
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
@DisplayName("Integration - CustomerDeleteAdapter")
class CustomerDeleteAdapterIntegrationTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    CustomerDeleteAdapter customerDeleteAdapter;

    @Autowired
    CustomerRepository customerRepository;

    CustomerEntity entity1;

    @BeforeEach
    void setUp() {
        entity1 = factory.gerarCustomerEntityBuilder().build();
        this.customerRepository.save(entity1);

        var entity2 = factory.gerarCustomerEntityBuilder()
            .statusCadastro(StatusCadastroEnum.CONCLUIDO)
            .build();
        this.customerRepository.save(entity2);
    }

    @AfterEach
    void tearDown() {
        this.customerRepository.deleteAll();
    }

    @Nested
    @DisplayName("Delete")
    class DeleteTest {

        @Test
        @DisplayName("id válido")
        void dadoCustomerIdValido_quandoDelete_entaoDeletar() {
            var customersAntes = customerRepository.findAll();
            Assertions.assertEquals(2, customersAntes.size());

            customerDeleteAdapter.delete(entity1.getCustomerId());

            var customersDepois = customerRepository.findAll();
            Assertions.assertEquals(1, customersDepois.size());
        }

        @Test
        @DisplayName("id inexistente")
        void dadoCustomerIdInexistente_quandoDelete_entaoLancarException() {
            Executable acao = () -> customerDeleteAdapter.delete(1000L);
            Assertions.assertThrows(CustomerNotFoundException.class, acao);
        }
    }

}

