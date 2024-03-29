package microservices.micro_empresas.application.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.AbstractTestcontainersTest;
import util.FactoryObjectMother;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - Empresa")
class EmpresaUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Nested
    @DisplayName("Equals")
    class TesteDeEquals {

        @Test
        @DisplayName("ids diferentes")
        void dadoEmpresasComIdsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            var empresa1 = factory.gerarEmpresaBuilder().id(1L).build();
            var empresa2 = factory.gerarEmpresaBuilder().id(2L).build();
            Assertions.assertNotEquals(empresa1, empresa2);
        }

        @Test
        @DisplayName("ids iguais")
        void dadoEmpresasComIdsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var empresa1 = factory.gerarEmpresaBuilder().id(1L).build();
            var empresa2 = factory.gerarEmpresaBuilder().id(1L).build();
            Assertions.assertEquals(empresa1, empresa2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class TesteDeToString {

        @Test
        @DisplayName("nomes diferentes")
        void dadoEmpresasComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            var empresa1 = factory.gerarEmpresaBuilder().id(1L).build();
            var empresa2 = factory.gerarEmpresaBuilder().id(1L).build();
            Assertions.assertNotEquals(empresa1.toString(), empresa2.toString());
        }

        @Test
        @DisplayName("iguais")
        void dadoEmpresasIguais_quandoCompararToStrings_entaoRetornarEqualsTrue() {
            var empresa1 = factory.gerarEmpresaBuilder().id(1L).build();
            var empresa2 = factory.gerarEmpresaBuilder().id(1L).nome(empresa1.getNome()).build();
            Assertions.assertEquals(empresa1.toString(), empresa2.toString());
        }
    }
}

