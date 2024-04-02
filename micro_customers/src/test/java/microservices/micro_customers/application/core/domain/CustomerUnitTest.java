package microservices.micro_customers.application.core.domain;

import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.application.core.domain.tipos.*;
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
@DisplayName("Unit - Customer")
class CustomerUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("ids diferentes")
        void dadoCustomerComIdsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            var customer1 = factory.gerarCustomerBuilder()
                .customerId(1L)
                .build();

            var customer2 = factory.gerarCustomerEntityBuilder()
                .customerId(2L)
                .build();

            Assertions.assertNotEquals(customer1, customer2);
        }

        @Test
        @DisplayName("ids iguais")
        void dadoCustomerComIdsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var customer1 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
                .build();

            var customer2 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
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
                .build();

            var customer2 = factory.gerarCustomerEntityBuilder()
                .customerId(1L)
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
            var telefones = Set.of(new Telefone("1199995555", TipoTelefoneEnum.FIXO));

            var cep = "78000000";
            var estado = "SP";
            var cidade = "São Paulo";
            var bairro = "Centro";
            var logradouro = "Avenida Paulista";
            var numero = "5577";
            var complemento = "Entrada pela porta amarela da direita.";

            var endereco = new Endereco();
            endereco.setCep(cep);
            endereco.setEstado(estado);
            endereco.setCidade(cidade);
            endereco.setBairro(bairro);
            endereco.setLogradouro(logradouro);
            endereco.setNumero(numero);
            endereco.setComplemento(complemento);

            var customer1 = factory.gerarCustomerBuilder()
                .customerId(1L)
                .nomeCompleto(nomeIgual)
                .cpf(new CadastroPessoaFisica(cpfIgual))
                .dataNascimento(new DataNascimento(data))
                .statusCadastro(status)
                .email(new CorreioEletronico(emailIgual))
                .telefones(telefones)
                .endereco(endereco)
                .build();

            var customer2 = new Customer();
            customer2.setCustomerId(1L);
            customer2.setNomeCompleto(nomeIgual);
            customer2.setCpf(new CadastroPessoaFisica(cpfIgual));
            customer2.setDataNascimento(new DataNascimento(data));
            customer2.setStatusCadastro(status);
            customer2.setEmail(new CorreioEletronico(emailIgual));
            customer2.setTelefones(telefones);
            customer2.setEndereco(endereco);

            Assertions.assertEquals(customer1.toString(), customer2.toString());
        }
    }

}

