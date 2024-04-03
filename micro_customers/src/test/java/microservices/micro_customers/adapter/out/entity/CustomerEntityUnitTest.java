package microservices.micro_customers.adapter.out.entity;

import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - CustomerEntity")
class CustomerEntityUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private CustomerEntity customer1;

    private CustomerEntity customer2;

    @BeforeEach
    void setUp() {
        customer1 = factory.gerarCustomerEntityBuilder()
            .customerId(1L)
            .statusCadastro(StatusCadastroEnum.INICIADO)
            .build();

        customer2 = factory.gerarCustomerEntityBuilder()
            .customerId(1L)
            .statusCadastro(StatusCadastroEnum.INICIADO)
            .build();
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("ids iguais")
        void dadoCustomerEntityComIdsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            Assertions.assertEquals(customer1, customer2);
        }

        @Test
        @DisplayName("ids diferentes")
        void dadoCustomerEntityComIdsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            customer2.setCustomerId(2L);
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

            var createdAt = OffsetDateTime.now();
            var createdBy = "anônimo";

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
            customer1.setCreatedAt(createdAt);
            customer1.setCreatedBy(createdBy);

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
            customer2.setCreatedAt(createdAt);
            customer2.setCreatedBy(createdBy);

            Assertions.assertEquals(customer1.toString(), customer2.toString());
        }
    }

}

