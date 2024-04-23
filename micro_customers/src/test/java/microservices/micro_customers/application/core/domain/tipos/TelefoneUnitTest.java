package microservices.micro_customers.application.core.domain.tipos;

import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.config.exception.http_400.ProhibitedEmptyOrBlankAttributeException;
import microservices.micro_customers.config.exception.http_400.RequestWithTypeAndWithoutNumberException;
import microservices.micro_customers.config.exception.http_400.TelefoneInvalidException;
import microservices.micro_customers.config.exception.http_400.TelefoneWithoutTypeException;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - Telefone")
class TelefoneUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private Telefone telefone1;

    private Telefone telefone2;

    @BeforeEach
    void setUp() {
        telefone1 = factory.gerarTelefoneCelularBuilder().build();
        telefone2 = factory.gerarTelefoneFixoBuilder().build();
    }

    @Nested
    @DisplayName("exceções")
    class TelefoneInvalido {

        @ParameterizedTest
        @ValueSource(strings = {"123456789", "123456789102"})
        @DisplayName("número inválido < 10 e número inválido > 11")
        void dadoTelefoneInvalidoMenorDezAndMaiorOnzeDigitos_quandoInstanciar_entaoLancarException(String numero) {
            Executable acao = () -> new Telefone(numero, TipoTelefoneEnum.FIXO);
            Assertions.assertThrows(TelefoneInvalidException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoTelefoneComNumeroVazioOuEmBranco_quandoInstanciar_entaoLancarException(String numero) {
            Executable acao = () -> new Telefone(numero, TipoTelefoneEnum.FIXO);
            Assertions.assertThrows(ProhibitedEmptyOrBlankAttributeException.class, acao);
        }

        @Test
        @DisplayName("tipo nulo")
        void dadoTelefoneComTipoNulo_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> new Telefone("12345678901", null);
            Assertions.assertThrows(TelefoneWithoutTypeException.class, acao);
        }

        @Test
        @DisplayName("com tipo, mas sem número ")
        void dadoTelefoneComTipoMasSemNumero_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> new Telefone(null, TipoTelefoneEnum.FIXO);
            Assertions.assertThrows(RequestWithTypeAndWithoutNumberException.class, acao);
        }
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("diferente")
        void dadoTelefonesDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(telefone1, telefone2);
        }

        @Test
        @DisplayName("iguais")
        void dadoTelefonesIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var telefone2 = new Telefone(telefone1.getNumero(), telefone1.getTipo());
            Assertions.assertEquals(telefone1, telefone2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("diferentes")
        void dadoTelefonesDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(telefone1.toString(), telefone2.toString());
        }

        @Test
        @DisplayName("iguais")
        void dadoTelefonesIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var telefone2 = new Telefone(telefone1.getNumero(), telefone1.getTipo());
            Assertions.assertEquals(telefone1.toString(), telefone2.toString());
        }
    }

}

