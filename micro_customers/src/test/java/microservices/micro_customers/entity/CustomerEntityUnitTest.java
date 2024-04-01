package microservices.micro_customers.entity;

import microservices.micro_customers.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - CustomerEntity")
class CustomerEntityUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("ids diferentes")
        void dadoCustomerEntityComIdsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            var customer1 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .statusCadastro(StatusCadastroEnum.INICIADO)
                .build();

            var customer2 = factory.gerarCustomerEntityBuilder()
                .customerId(2L)
                .statusCadastro(StatusCadastroEnum.INICIADO)
                .build();

            Assertions.assertNotEquals(customer1, customer2);
        }

        @Test
        @DisplayName("ids iguais")
        void dadoCustomerEntityComIdsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var customer1 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .statusCadastro(StatusCadastroEnum.INICIADO)
                .build();

            var customer2 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .statusCadastro(StatusCadastroEnum.INICIADO)
                .build();

            Assertions.assertEquals(customer1, customer2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("dados diferentes")
        void dadoCustomerComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            var customer1 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .statusCadastro(StatusCadastroEnum.INICIADO)
                .build();

            var customer2 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .statusCadastro(StatusCadastroEnum.INICIADO)
                .build();

            Assertions.assertNotEquals(customer1.toString(), customer2.toString());
        }

        @Test
        @DisplayName("iguais")
        void dadoCustomerComDadosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var nomeIgual = "Martin Fowler";
            var cpfIgual = "29879485068";
            var emailIgual = "fowler@teste.com";

            var customer1 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .nomeCompleto(nomeIgual)
                .cpf(cpfIgual)
                .statusCadastro(StatusCadastroEnum.INICIADO)
                .email(emailIgual)
                .build();

            var customer2 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .nomeCompleto(nomeIgual)
                .cpf(cpfIgual)
                .statusCadastro(StatusCadastroEnum.INICIADO)
                .email(emailIgual)
                .build();

            Assertions.assertEquals(customer1.toString(), customer2.toString());
        }
    }
}

