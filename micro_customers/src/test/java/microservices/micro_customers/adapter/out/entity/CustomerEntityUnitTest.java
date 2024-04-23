package microservices.micro_customers.adapter.out.entity;

import microservices.micro_customers.adapter.out.entity.value_objects.EnderecoVo;
import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - CustomerEntity")
class CustomerEntityUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private CustomerEntity entity1;

    private CustomerEntity entity2;

    @BeforeEach
    void setUp() {
        entity1 = factory.gerarCustomerEntityBuilder()
            .customerId(1L)
            .statusCadastro(StatusCadastroEnum.INICIADO)
            .build();

        entity2 = factory.gerarCustomerEntityBuilder()
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
            Assertions.assertEquals(entity1, entity2);
        }

        @Test
        @DisplayName("ids diferentes")
        void dadoCustomerEntityComIdsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            entity2.setCustomerId(2L);
            Assertions.assertNotEquals(entity1, entity2);
        }

    }

    @Nested
    @DisplayName("Hash")
    class HashTest {

        @Test
        @DisplayName("diferentes")
        void dadoCustomerEntityDiferentes_quandoCompararComHash_entaoRetornarNotEqualsTrue() {
            entity2.setCustomerId(2L);
            Assertions.assertNotEquals(entity1.hashCode(), entity2.hashCode());
        }

        @Test
        @DisplayName("iguais")
        void dadoCustomerEntityIguais_quandoCompararComHash_entaoRetornarEqualsTrue() {
            BeanUtils.copyProperties(entity1, entity2);
            Assertions.assertEquals(entity1.hashCode(), entity2.hashCode());
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("dados diferentes")
        void dadoCustomerComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(entity1.toString(), entity2.toString());
        }

        @Test
        @DisplayName("dados iguais")
        void dadoCustomerComDadosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var nomeIgual = "Martin Fowler";
            var cpfIgual = "29879485068";
            var data = LocalDate.now();
            var status = StatusCadastroEnum.CONCLUIDO;
            var emailIgual = "fowler@teste.com";

            var cep = "78000000";
            var estado = "SP";
            var cidade = "São Paulo";
            var bairro = "Centro";
            var logradouro = "Avenida Paulista";
            var numero = "5577";
            var complemento = "Entrada pela porta amarela da direita.";

            var telefonesVo = Set.of(new TelefoneVo("1199995555", TipoTelefoneEnum.FIXO));
            var enderecosVo = Set.of(EnderecoVo.builder()
                .cep(cep)
                .estado(estado)
                .cidade(cidade)
                .bairro(bairro)
                .logradouro(logradouro)
                .numero(numero)
                .complemento(complemento)
                .build());

            var createdAt = LocalDateTime.now();
            var createdBy = "anônimo";

            var customer1 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .nomeCompleto(nomeIgual)
                .cpf(cpfIgual)
                .dataNascimento(data)
                .statusCadastro(status)
                .email(emailIgual)
                .telefones(telefonesVo)
                .enderecos(enderecosVo)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .build();

            var customer2 = new CustomerEntity();
            customer2.setCustomerId(1L);
            customer2.setNomeCompleto(nomeIgual);
            customer2.setCpf(cpfIgual);
            customer2.setDataNascimento(data);
            customer2.setStatusCadastro(status);
            customer2.setEmail(emailIgual);
            customer2.setTelefones(telefonesVo);
            customer2.setEnderecos(enderecosVo);
            customer2.setCreatedAt(createdAt);
            customer2.setCreatedBy(createdBy);

            Assertions.assertEquals(customer1.toString(), customer2.toString());
        }
    }

}

