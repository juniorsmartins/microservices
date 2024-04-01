package microservices.micro_customers.entity.value_objects;

import microservices.micro_customers.domain.enums.TipoTelefoneEnum;
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
@DisplayName("Unit - TelefoneVo")
class TelefoneVoUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("diferentes")
        void dadoTelefoneVoDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            var telefone1 = factory.gerarTelefoneVoFixoBuilder().build();
            var telefone2 = factory.gerarTelefoneVoCelularBuilder().build();
            Assertions.assertNotEquals(telefone1, telefone2);
        }

        @Test
        @DisplayName("iguais")
        void dadoTelefoneVoIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var telefone1 = factory.gerarTelefoneVoFixoBuilder().build();
            var telefone2 = factory.gerarTelefoneVoCelularBuilder()
                .telefone(telefone1.getTelefone()).build();
            Assertions.assertEquals(telefone1, telefone2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("dados diferentes")
        void dadoTelefoneVoComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            var telefone1 = factory.gerarTelefoneVoFixoBuilder().build();
            var telefone2 = factory.gerarTelefoneVoCelularBuilder().build();
            Assertions.assertNotEquals(telefone1.toString(), telefone2.toString());
        }

        @Test
        @DisplayName("dados iguais")
        void dadoTelefoneVoComDadosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var telefone1 = new TelefoneVo();
            telefone1.setTelefone("8899993333");
            telefone1.setTipo(TipoTelefoneEnum.FIXO);
            var telefone2 = new TelefoneVo(telefone1.getTelefone(), TipoTelefoneEnum.FIXO);
            Assertions.assertEquals(telefone1.toString(), telefone2.toString());
        }
    }

}

