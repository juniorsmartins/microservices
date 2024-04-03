package microservices.micro_customers.application.core.domain.tipos;

import microservices.micro_customers.config.exception.http_400.EmailInvalidException;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
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
@DisplayName("Unit - CorreioEletronico")
class CorreioEletronicoUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Nested
    @DisplayName("cpf")
    class CpfInvalido {

        @Test
        @DisplayName("inválido")
        void dadoEmailInvalido_quandoInstanciarCadastroPessoaFisica_entaoLancarException() {
            Executable acao = () -> factory.gerarCorreioEletronicoInvalidoBuilder().build();
            Assertions.assertThrows(EmailInvalidException.class, acao);
        }
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("emails diferentes")
        void dadoEmailsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            var email1 = factory.gerarCorreioEletronicoValidoBuilder().build();
            var email2 = factory.gerarCorreioEletronicoValidoBuilder().build();
            Assertions.assertNotEquals(email1, email2);
        }

        @Test
        @DisplayName("emails iguais")
        void dadoEmailsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var igual = "robert_martin@test.com";
            var email1 = new CorreioEletronico(igual);
            var email2 = new CorreioEletronico(igual);
            Assertions.assertEquals(email1, email2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("emails diferentes")
        void dadoEmailsDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            var email1 = factory.gerarCorreioEletronicoValidoBuilder().build();
            var email2 = factory.gerarCorreioEletronicoValidoBuilder().build();
            Assertions.assertNotEquals(email1.toString(), email2.toString());
        }

        @Test
        @DisplayName("emails iguais")
        void dadoEmailsIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var igual = "robert_martin@test.com";
            var email1 = new CorreioEletronico(igual);
            var email2 = new CorreioEletronico(igual);
            Assertions.assertEquals(email1.toString(), email2.toString());
        }
    }

}

