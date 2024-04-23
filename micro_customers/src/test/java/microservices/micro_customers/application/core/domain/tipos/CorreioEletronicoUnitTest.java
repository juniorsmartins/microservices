package microservices.micro_customers.application.core.domain.tipos;

import microservices.micro_customers.config.exception.http_400.AttributeWithInvalidMaximumSizeException;
import microservices.micro_customers.config.exception.http_400.EmailInvalidException;
import microservices.micro_customers.config.exception.http_400.NullAttributeNotAllowedException;
import microservices.micro_customers.config.exception.http_400.ProhibitedEmptyOrBlankAttributeException;
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
@DisplayName("Unit - CorreioEletronico")
class CorreioEletronicoUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private CorreioEletronico email1;

    private CorreioEletronico email2;

    @BeforeEach
    void setUp() {
        email1 = factory.gerarCorreioEletronicoValidoBuilder().build();
        email2 = factory.gerarCorreioEletronicoValidoBuilder().build();
    }

    @Nested
    @DisplayName("email")
    class EmailInvalido {

        @Test
        @DisplayName("inválido")
        void dadoEmailInvalido_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> factory.gerarCorreioEletronicoInvalidoBuilder().build();
            Assertions.assertThrows(EmailInvalidException.class, acao);
        }

        @Test
        @DisplayName("nulo")
        void dadoEmailNulo_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> new CorreioEletronico(null);
            Assertions.assertThrows(NullAttributeNotAllowedException.class, acao);
        }

        @Test
        @DisplayName("excedido tamanho máximo")
        void dadoEmailTamanhoMaximoExcedido_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> new CorreioEletronico("abcdefghijlmnopqrstuvxz12345678901234567890abcdefghijlmnopqrstuvxz12345678901234567890abcdefghijlmnopqrstuvxz123456789012345678901234567890@teste.com.br");
            Assertions.assertThrows(AttributeWithInvalidMaximumSizeException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoEmailVazioOuEmBranco_quandoInstanciar_entaoLancarException(String valor) {
            Executable acao = () -> CorreioEletronico.builder().email(valor).build();
            Assertions.assertThrows(ProhibitedEmptyOrBlankAttributeException.class, acao);
        }
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("emails diferentes")
        void dadoEmailsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(email1, email2);
        }

        @Test
        @DisplayName("emails iguais")
        void dadoEmailsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var email2 = new CorreioEletronico(email1.getEmail());
            Assertions.assertEquals(email1, email2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("emails diferentes")
        void dadoEmailsDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(email1.toString(), email2.toString());
        }

        @Test
        @DisplayName("emails iguais")
        void dadoEmailsIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var email2 = new CorreioEletronico(email1.getEmail());
            Assertions.assertEquals(email1.toString(), email2.toString());
        }
    }

}

