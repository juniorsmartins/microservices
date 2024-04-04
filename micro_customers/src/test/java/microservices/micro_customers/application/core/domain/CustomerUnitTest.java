package microservices.micro_customers.application.core.domain;

import microservices.micro_customers.application.core.domain.tipos.CadastroPessoaFisica;
import microservices.micro_customers.application.core.domain.tipos.CorreioEletronico;
import microservices.micro_customers.application.core.domain.tipos.DataNascimento;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - Customer")
class CustomerUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private Customer customer1;

    private Customer customer2;

    @BeforeEach
    void setUp() {
        customer1 = factory.gerarCustomerBuilder()
            .customerId(1L)
            .build();

        customer2 = factory.gerarCustomerBuilder()
            .customerId(1L)
            .build();
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("ids iguais")
        void dadoCustomerComIdsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            Assertions.assertEquals(customer1, customer2);
        }

        @Test
        @DisplayName("ids diferentes")
        void dadoCustomerComIdsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            var customer2 = factory.gerarCustomerBuilder()
                .customerId(2L)
                .build();
            Assertions.assertNotEquals(customer1, customer2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("dados diferentes")
        void dadoCustomerComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(customer1.toString(), customer2.toString());
        }

        @Test
        @DisplayName("dados iguais")
        void dadoCustomerComDadosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var customer2 = new Customer();
            customer2.setCustomerId(customer1.getCustomerId());
            customer2.setNomeCompleto(customer1.getNomeCompleto());
            customer2.setCpf(new CadastroPessoaFisica(customer1.getCpf().getCpf()));
            customer2.setDataNascimento(new DataNascimento(customer1.getDataNascimento().getDataNascimentoLocalDate()));
            customer2.setStatusCadastro(customer1.getStatusCadastro());
            customer2.setEmail(new CorreioEletronico(customer1.getEmail().getEmail()));
            customer2.setTelefones(customer1.getTelefones());
            customer2.setEnderecos(customer1.getEnderecos());

            Assertions.assertEquals(customer1.toString(), customer2.toString());
        }
    }

}

