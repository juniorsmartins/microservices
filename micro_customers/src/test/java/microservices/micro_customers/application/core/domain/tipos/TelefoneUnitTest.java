package microservices.micro_customers.application.core.domain.tipos;

import microservices.micro_customers.application.core.domain.tipos.Telefone;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.config.exception.http_400.TelefoneInvalidException;
import microservices.micro_customers.config.exception.http_400.TelefoneWithoutTypeException;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - Telefone")
class TelefoneUnitTest extends AbstractTestcontainersTest {

    @Nested
    @DisplayName("inválido")
    class TelefoneInvalido {

        @Test
        @DisplayName("número inválido < 10")
        void dadoTelefoneInvalidoMenorDezDigitos_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> new Telefone("123456789", TipoTelefoneEnum.FIXO);
            Assertions.assertThrows(TelefoneInvalidException.class, acao);
        }

        @Test
        @DisplayName("número inválido > 11")
        void dadoTelefoneInvalidoMaiorOnzeDigitos_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> new Telefone("123456789102", TipoTelefoneEnum.CELULAR);
            Assertions.assertThrows(TelefoneInvalidException.class, acao);
        }

        @Test
        @DisplayName("tipo nulo")
        void dadoTipoDeTelefoneNulo_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> new Telefone("12345678901", null);
            Assertions.assertThrows(TelefoneWithoutTypeException.class, acao);
        }
    }

}

