package microservices.micro_customers.application.core.domain.tipos;

import microservices.micro_customers.config.exception.http_400.DataNascimentoInvalidException;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - DataNascimento")
class DataNascimentoUnitTest extends AbstractTestcontainersTest {

    private DataNascimento data1;

    private DataNascimento data2;

    @BeforeEach
    void setUp() {
        data1 = new DataNascimento("10/10/2000");
        data2 = new DataNascimento("05/05/1990");
    }

    @Nested
    @DisplayName("data nascimento")
    class DataNascimentoInvalida {

        @Test
        @DisplayName("data inválida")
        void dadoDataDeNascimentoInvalida_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> new DataNascimento("06/20/2000");
            Assertions.assertThrows(DataNascimentoInvalidException.class, acao);
        }

        @Test
        @DisplayName("formato inválido")
        void dadoDataDeNascimentoInvalidaPorFormato_quandoInstanciar_entaoLancarException() {
            Executable acao = () -> new DataNascimento("06-10-2000");
            Assertions.assertThrows(DataNascimentoInvalidException.class, acao);
        }
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("datas diferentes")
        void dadoDatasDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(data1, data2);
        }

        @Test
        @DisplayName("datas iguais")
        void dadoDatasIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var data2 = new DataNascimento(data1.getDataNascimentoString());
            Assertions.assertEquals(data1, data2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("datas diferentes")
        void dadoDatasDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(data1.toString(), data2.toString());
        }

        @Test
        @DisplayName("datas iguais")
        void dadoDatasIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var data2 = new DataNascimento(data1.getDataNascimentoString());
            Assertions.assertEquals(data1.toString(), data2.toString());
        }
    }

}

