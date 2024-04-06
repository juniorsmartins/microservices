package microservices.micro_customers.adapter.out;

import microservices.micro_customers.adapter.in.filters.CustomerFilter;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration - CustomerSearchAdapter")
class CustomerSearchAdapterIntegrationTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    CustomerSearchAdapter customerSearchAdapter;

    @Autowired
    CustomerRepository customerRepository;

    CustomerEntity customerEntity1;

    PageRequest pageRequest;

    @BeforeEach
    void setUp() {
        var entity1 = factory.gerarCustomerEntityBuilder().build();
        customerEntity1 = this.customerRepository.save(entity1);

        var entity2 = factory.gerarCustomerEntityBuilder()
            .statusCadastro(StatusCadastroEnum.CONCLUIDO)
            .build();
        this.customerRepository.save(entity2);

        pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "customerId"));
    }

    @AfterEach
    void tearDown() {
        this.customerRepository.deleteAll();
    }

    @Nested
    @DisplayName("")
    class CustomerFilterTest {

        @Test
        @DisplayName("vazio")
        void dadoCustomerFilterVazio_quandoSearch_entaoRetornarTodosOsCustomersPaginados() {
            var filter = CustomerFilter.builder().build();
            var response = customerSearchAdapter.search(filter, pageRequest);
            Assertions.assertEquals(2, response.getTotalElements());
        }

        @Test
        @DisplayName("customerId")
        void dadoCustomerFilterComCustomerId_quandoSearch_entaoRetornarUmCustomerPaginado() {
            var filter = CustomerFilter.builder().customerId(customerEntity1.getCustomerId().toString()).build();
            var response = customerSearchAdapter.search(filter, pageRequest);
            Assertions.assertEquals(1, response.getTotalElements());
            Assertions.assertEquals(customerEntity1.getCustomerId(), response.getContent().getFirst().customerId());
        }

        @Test
        @DisplayName("nomeCompleto")
        void dadoCustomerFilterComNomeCompleto_quandoSearch_entaoRetornarUmCustomerPaginado() {
            var filter = CustomerFilter.builder().nomeCompleto(customerEntity1.getNomeCompleto()).build();
            var response = customerSearchAdapter.search(filter, pageRequest);
            Assertions.assertEquals(1, response.getTotalElements());
            Assertions.assertEquals(customerEntity1.getNomeCompleto(), response.getContent().getFirst().nomeCompleto());
        }

        @Test
        @DisplayName("cpf")
        void dadoCustomerFilterCPF_quandoSearch_entaoRetornarUmCustomerPaginado() {
            var filter = CustomerFilter.builder().cpf(customerEntity1.getCpf()).build();
            var response = customerSearchAdapter.search(filter, pageRequest);
            Assertions.assertEquals(1, response.getTotalElements());
            Assertions.assertEquals(customerEntity1.getCpf(), response.getContent().getFirst().cpf());
        }

        @Test
        @DisplayName("statusCadastro")
        void dadoCustomerFilterStatusCadastro_quandoSearch_entaoRetornarUmCustomerPaginado() {
            var filter = CustomerFilter.builder().statusCadastro(customerEntity1.getStatusCadastro()).build();
            var response = customerSearchAdapter.search(filter, pageRequest);
            Assertions.assertEquals(1, response.getTotalElements());
            Assertions.assertEquals(customerEntity1.getStatusCadastro(), response.getContent().getFirst().statusCadastro());
        }

        @Test
        @DisplayName("email")
        void dadoCustomerFilterEmail_quandoSearch_entaoRetornarUmCustomerPaginado() {
            var filter = CustomerFilter.builder().email(customerEntity1.getEmail()).build();
            var response = customerSearchAdapter.search(filter, pageRequest);
            Assertions.assertEquals(1, response.getTotalElements());
            Assertions.assertEquals(customerEntity1.getEmail(), response.getContent().getFirst().email());
        }
    }

}

