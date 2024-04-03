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
@DisplayName("Unit - TelefoneVo")
class TelefoneVoUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private TelefoneVo telefoneVo1;

    private TelefoneVo telefoneVo2;

    @BeforeEach
    void setUp() {
        telefoneVo1 = factory.gerarTelefoneVoFixoBuilder().build();
        telefoneVo2 = factory.gerarTelefoneVoCelularBuilder().build();
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("diferentes")
        void dadoTelefoneVoDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(telefoneVo1, telefoneVo2);
        }

        @Test
        @DisplayName("iguais")
        void dadoTelefoneVoIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            telefoneVo2.setTelefone(telefoneVo1.getTelefone());
            Assertions.assertEquals(telefoneVo1, telefoneVo2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("dados diferentes")
        void dadoTelefoneVoComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(telefoneVo1.toString(), telefoneVo2.toString());
        }

        @Test
        @DisplayName("dados iguais")
        void dadoTelefoneVoComDadosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            telefoneVo2.setTelefone(telefoneVo1.getTelefone());
            telefoneVo2.setTipo(telefoneVo1.getTipo());
            Assertions.assertEquals(telefoneVo1.toString(), telefoneVo2.toString());
        }
    }

}

