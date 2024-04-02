package microservices.micro_customers.application.core.domain.tipos;

import microservices.micro_customers.config.exception.http_400.CpfInvalidException;
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
@DisplayName("Unit - CadastroPessoaFisica")
class CadastroPessoaFisicaUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Nested
    @DisplayName("cpf")
    class CpfInvalido {

        @Test
        @DisplayName("inválido")
        void dadoCpfInvalido_quandoInstanciarCadastroPessoaFisica_entaoLancarException() {
            Executable acao = () -> factory.gerarCadastroPessoaFisicaInvalidoBuilder().build();
            Assertions.assertThrows(CpfInvalidException.class, acao);
        }

        @Test
        @DisplayName("10 digitos")
        void dadoCpfComDezDigitos_quandoInstanciarCadastroPessoaFisica_entaoLancarException() {
            Executable acao = () -> CadastroPessoaFisica.builder().cpf("1234567890").build();
            Assertions.assertThrows(CpfInvalidException.class, acao);
        }

        @Test
        @DisplayName("primeiro digito verificador inválido")
        void dadoCpfComPrimeiroDigitoVerificadorInvalido_quandoInstanciarCadastroPessoaFisica_entaoLancarException() {
            Executable acao = () -> CadastroPessoaFisica.builder().cpf("71970296011").build();
            Assertions.assertThrows(CpfInvalidException.class, acao);
        }

        @Test
        @DisplayName("digitos iguais")
        void dadoCpfComDigitosIguais_quandoInstanciarCadastroPessoaFisica_entaoLancarException() {
            Executable acao = () -> CadastroPessoaFisica.builder().cpf("11111111111").build();
            Assertions.assertThrows(CpfInvalidException.class, acao);
        }
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("cpfs diferentes")
        void dadoCadastroPessoaFisicaComIdsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            var cpf1 = factory.gerarCadastroPessoaFisicaValidoBuilder().build();
            var cpf2 = factory.gerarCadastroPessoaFisicaValidoBuilder().build();
            Assertions.assertNotEquals(cpf1, cpf2);
        }

        @Test
        @DisplayName("ids iguais")
        void dadoCadastroPessoaFisicaComIdsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var igual = "67817568006";
            var cpf1 = new CadastroPessoaFisica(igual);
            var cpf2 = new CadastroPessoaFisica(igual);
            Assertions.assertEquals(cpf1, cpf2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("dados diferentes")
        void dadoCadastroPessoaFisicaComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            var cpf1 = factory.gerarCadastroPessoaFisicaValidoBuilder().build();
            var cpf2 = factory.gerarCadastroPessoaFisicaValidoBuilder().build();
            Assertions.assertNotEquals(cpf1.toString(), cpf2.toString());
        }

        @Test
        @DisplayName("dados iguais")
        void dadoCadastroPessoaFisicaComDadosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var igual = "67817568006";
            var cpf1 = new CadastroPessoaFisica(igual);
            var cpf2 = new CadastroPessoaFisica(igual);
            Assertions.assertEquals(cpf1.toString(), cpf2.toString());
        }
    }

}

