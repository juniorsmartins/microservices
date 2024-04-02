package microservices.micro_customers.domain.tipos;

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
@DisplayName("Unit - Endereco")
class EnderecoUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("diferente")
        void dadoEnderecosDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            var valor1 = factory.gerarEnderecoBuilder().build();
            var valor2 = factory.gerarEnderecoBuilder().build();
            Assertions.assertNotEquals(valor1, valor2);
        }

        @Test
        @DisplayName("iguais")
        void dadoEnderecosIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var valor1 = factory.gerarEnderecoBuilder().build();
            var valor2 = new Endereco();
            valor2.setCep(valor1.getCep());
            valor2.setEstado(valor1.getEstado());
            valor2.setCidade(valor1.getCidade());
            valor2.setBairro(valor1.getBairro());
            valor2.setLogradouro(valor1.getLogradouro());
            valor2.setNumero(valor1.getNumero());
            valor2.setComplemento(valor1.getComplemento());
            Assertions.assertEquals(valor1, valor2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("diferentes")
        void dadoEnderecosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            var valor1 = factory.gerarEnderecoBuilder().build();
            var valor2 = factory.gerarEnderecoBuilder().build();
            Assertions.assertNotEquals(valor1.toString(), valor2.toString());
        }

        @Test
        @DisplayName("iguais")
        void dadoEnderecosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var valor1 = factory.gerarEnderecoBuilder().build();
            var valor2 = new Endereco();
            valor2.setCep(valor1.getCep());
            valor2.setEstado(valor1.getEstado());
            valor2.setCidade(valor1.getCidade());
            valor2.setBairro(valor1.getBairro());
            valor2.setLogradouro(valor1.getLogradouro());
            valor2.setNumero(valor1.getNumero());
            valor2.setComplemento(valor1.getComplemento());
            Assertions.assertEquals(valor1.toString(), valor2.toString());
        }
    }

}

