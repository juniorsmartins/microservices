package microservices.micro_customers.application.core.domain.tipos;

import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - Endereco")
class EnderecoUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private Endereco endereco1;

    private Endereco endereco2;

    @BeforeEach
    void setUp() {
        endereco1 = factory.gerarEnderecoBuilder().build();
        endereco2 = factory.gerarEnderecoBuilder().build();
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("diferente")
        void dadoEnderecosDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(endereco1, endereco2);
        }

        @Test
        @DisplayName("iguais")
        void dadoEnderecosIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var endereco2 = new Endereco();
            endereco2.setCep(endereco1.getCep());
            endereco2.setEstado(endereco1.getEstado());
            endereco2.setCidade(endereco1.getCidade());
            endereco2.setBairro(endereco1.getBairro());
            endereco2.setLogradouro(endereco1.getLogradouro());
            endereco2.setNumero(endereco1.getNumero());
            endereco2.setComplemento(endereco1.getComplemento());
            Assertions.assertEquals(endereco1, endereco2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("diferentes")
        void dadoEnderecosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(endereco1.toString(), endereco2.toString());
        }

        @Test
        @DisplayName("iguais")
        void dadoEnderecosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var endereco2 = new Endereco();
            endereco2.setCep(endereco1.getCep());
            endereco2.setEstado(endereco1.getEstado());
            endereco2.setCidade(endereco1.getCidade());
            endereco2.setBairro(endereco1.getBairro());
            endereco2.setLogradouro(endereco1.getLogradouro());
            endereco2.setNumero(endereco1.getNumero());
            endereco2.setComplemento(endereco1.getComplemento());
            Assertions.assertEquals(endereco1.toString(), endereco2.toString());
        }
    }

}

