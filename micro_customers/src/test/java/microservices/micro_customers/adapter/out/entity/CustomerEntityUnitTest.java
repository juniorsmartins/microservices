package microservices.micro_customers.adapter.out.entity;

import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;
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

import java.time.LocalDate;
import java.util.Set;

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
        @DisplayName("dados iguais")
        void dadoCustomerComDadosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var nomeIgual = "Martin Fowler";
            var cpfIgual = "29879485068";
            var data = LocalDate.now();
            var status = StatusCadastroEnum.CONCLUIDO;
            var emailIgual = "fowler@teste.com";
            var telefones = Set.of(new TelefoneVo("1199995555", TipoTelefoneEnum.FIXO));
            var cep = "78000000";
            var estado = "SP";
            var cidade = "São Paulo";
            var bairro = "Centro";
            var logradouro = "Avenida Paulista";
            var numero = "5577";
            var complemento = "Entrada pela porta amarela da direita.";

            var customer1 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .nomeCompleto(nomeIgual)
                .cpf(cpfIgual)
                .dataNascimento(data)
                .statusCadastro(status)
                .email(emailIgual)
                .telefones(telefones)
                    .cep(cep)
                    .estado(estado)
                    .cidade(cidade)
                    .bairro(bairro)
                    .logradouro(logradouro)
                    .numero(numero)
                    .complemento(complemento)
                .build();

            var customer2 = new CustomerEntity();
            customer2.setCustomerId(1L);
            customer2.setNomeCompleto(nomeIgual);
            customer2.setCpf(cpfIgual);
            customer2.setDataNascimento(data);
            customer2.setStatusCadastro(status);
            customer2.setEmail(emailIgual);
            customer2.setTelefones(telefones);
            customer2.setCep(cep);
            customer2.setEstado(estado);
            customer2.setCidade(cidade);
            customer2.setBairro(bairro);
            customer2.setLogradouro(logradouro);
            customer2.setNumero(numero);
            customer2.setComplemento(complemento);

            Assertions.assertEquals(customer1.toString(), customer2.toString());
        }
    }

}

