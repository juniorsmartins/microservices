package microservices.micro_customers.adapter.out.entity.value_objects;

import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - EnderecoVo")
class EnderecoVoUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private EnderecoVo enderecoVo1;

    private EnderecoVo enderecoVo2;

    @BeforeEach
    void setUp() {
        enderecoVo1 = factory.gerarEnderecoVoBuilder().build();
        enderecoVo2 = factory.gerarEnderecoVoBuilder().build();
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("diferentes")
        void dadoEnderecoVoDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(enderecoVo1, enderecoVo2);
        }

        @Test
        @DisplayName("iguais")
        void dadoEnderecoVoIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            enderecoVo2.setCep(enderecoVo1.getCep());
            enderecoVo2.setEstado(enderecoVo1.getEstado());
            enderecoVo2.setCidade(enderecoVo1.getCidade());
            enderecoVo2.setBairro(enderecoVo1.getBairro());
            enderecoVo2.setLogradouro(enderecoVo1.getLogradouro());
            enderecoVo2.setNumero(enderecoVo1.getNumero());
            enderecoVo2.setComplemento(enderecoVo1.getComplemento());

            Assertions.assertEquals(enderecoVo1, enderecoVo2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("diferentes")
        void dadoEnderecoVoComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(enderecoVo1.toString(), enderecoVo2.toString());
        }

        @Test
        @DisplayName("iguais")
        void dadoEnderecoVoComDadosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            enderecoVo2.setCep(enderecoVo1.getCep());
            enderecoVo2.setEstado(enderecoVo1.getEstado());
            enderecoVo2.setCidade(enderecoVo1.getCidade());
            enderecoVo2.setBairro(enderecoVo1.getBairro());
            enderecoVo2.setLogradouro(enderecoVo1.getLogradouro());
            enderecoVo2.setNumero(enderecoVo1.getNumero());
            enderecoVo2.setComplemento(enderecoVo1.getComplemento());

            Assertions.assertEquals(enderecoVo1.toString(), enderecoVo2.toString());
        }
    }

}

