package microservices.micro_customers.application.core.domain.tipos;

import microservices.micro_customers.config.exception.http_400.AttributeWithInvalidMaximumSizeException;
import microservices.micro_customers.config.exception.http_400.CpfInvalidException;
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
@DisplayName("Unit - CadastroPessoaFisica")
class CadastroPessoaFisicaUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    private CadastroPessoaFisica cpf1;

    private CadastroPessoaFisica cpf2;

    @BeforeEach
    void setUp() {
        cpf1 = factory.gerarCadastroPessoaFisicaValidoBuilder().build();
        cpf2 = factory.gerarCadastroPessoaFisicaValidoBuilder().build();
    }

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
        @DisplayName("nulo")
        void dadoCpfNulo_quandoInstanciarCadastroPessoaFisica_entaoLancarException() {
            Executable acao = () -> new CadastroPessoaFisica(null);
            Assertions.assertThrows(NullAttributeNotAllowedException.class, acao);
        }

        @Test
        @DisplayName("excedido tamanho máximo")
        void dadoCpfTamanhoMaximoExcedido_quandoInstanciarCadastroPessoaFisica_entaoLancarException() {
            Executable acao = () -> new CadastroPessoaFisica("123456789012345");
            Assertions.assertThrows(AttributeWithInvalidMaximumSizeException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoCpfVazioOuEmBranco_quandoInstanciarCadastroPessoaFisica_entaoLancarException(String valor) {
            Executable acao = () -> CadastroPessoaFisica.builder().cpf(valor).build();
            Assertions.assertThrows(ProhibitedEmptyOrBlankAttributeException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"1234567890", "54682866098", "54682866029", "11111111111"})
        @DisplayName("dez dígitos, primeiro e segundo dígitos inválidos e dígitos iguais")
        void dadoCpfComTresErrosDistintos_quandoInstanciarCadastroPessoaFisica_entaoLancarException(String valor) {
            Executable acao = () -> CadastroPessoaFisica.builder().cpf(valor).build();
            Assertions.assertThrows(CpfInvalidException.class, acao);
        }
    }

    @Nested
    @DisplayName("Equals")
    class EqualsTest {

        @Test
        @DisplayName("cpfs diferentes")
        void dadoCadastroPessoaFisicaComIdsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(cpf1, cpf2);
        }

        @Test
        @DisplayName("ids iguais")
        void dadoCadastroPessoaFisicaComIdsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var cpf2 = new CadastroPessoaFisica(cpf1.getCpf());
            Assertions.assertEquals(cpf1, cpf2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class ToStringTest {

        @Test
        @DisplayName("dados diferentes")
        void dadoCadastroPessoaFisicaComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            Assertions.assertNotEquals(cpf1.toString(), cpf2.toString());
        }

        @Test
        @DisplayName("dados iguais")
        void dadoCadastroPessoaFisicaComDadosIguais_quandoCompararToString_entaoRetornarEqualsTrue() {
            var cpf2 = new CadastroPessoaFisica(cpf1.getCpf());
            Assertions.assertEquals(cpf1.toString(), cpf2.toString());
        }
    }

}

