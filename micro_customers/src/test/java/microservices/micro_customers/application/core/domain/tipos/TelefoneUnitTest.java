package microservices.micro_customers.application.core.domain.tipos;

import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.config.exception.http_400.ProhibitedEmptyOrBlankAttributeException;
import microservices.micro_customers.config.exception.http_400.TelefoneInvalidException;
import microservices.micro_customers.config.exception.http_400.TelefoneWithoutTypeException;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
    }

}

