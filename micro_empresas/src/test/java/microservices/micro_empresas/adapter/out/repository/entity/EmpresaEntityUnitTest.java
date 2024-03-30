package microservices.micro_empresas.adapter.out.repository.entity;

import microservices.micro_empresas.adapter.out.entity.EmpresaEntity;
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

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - EmpresaEntity")
class EmpresaEntityUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Nested
    @DisplayName("Equals")
    class TesteDeEquals {

        @Test
        @DisplayName("ids diferentes")
        void dadoEmpresasEntityComIdsDiferentes_quandoCompararComEquals_entaoRetornarNotEqualsTrue() {
            var empresa1 = factory.gerarEmpresaEntityBuilder().id(1L).build();
            var empresa2 = factory.gerarEmpresaEntityBuilder().id(2L).build();
            Assertions.assertNotEquals(empresa1, empresa2);
        }

        @Test
        @DisplayName("ids iguais")
        void dadoEmpresasEntityComIdsIguais_quandoCompararComEquals_entaoRetornarEqualsTrue() {
            var empresa1 = factory.gerarEmpresaEntityBuilder().id(1L).build();
            var empresa2 = factory.gerarEmpresaEntityBuilder().id(1L).build();
            Assertions.assertEquals(empresa1, empresa2);
        }
    }

    @Nested
    @DisplayName("ToString")
    class TesteDeToString {

        @Test
        @DisplayName("nomes diferentes")
        void dadoEmpresasComDadosDiferentes_quandoCompararToStrings_entaoRetornarNotEqualsTrue() {
            var empresa1 = factory.gerarEmpresaEntityBuilder().id(1L).build();
            var empresa2 = factory.gerarEmpresaEntityBuilder().id(1L).build();
            Assertions.assertNotEquals(empresa1.toString(), empresa2.toString());
        }

        @Test
        @DisplayName("iguais")
        void dadoEmpresasIguais_quandoCompararToStrings_entaoRetornarEqualsTrue() {
            var nomeIgual = "Petróleo Brasileiro S/A";

            var empresa1 = new EmpresaEntity();
            empresa1.setId(1L);
            empresa1.setNome(nomeIgual);

            var empresa2 = new EmpresaEntity();
            empresa2.setId(1L);
            empresa2.setNome(nomeIgual);

            Assertions.assertEquals(empresa1.toString(), empresa2.toString());
        }
    }

}

