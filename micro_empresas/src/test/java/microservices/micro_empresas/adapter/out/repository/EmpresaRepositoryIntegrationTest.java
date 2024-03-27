package microservices.micro_empresas.adapter.out.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import util.AbstractTestcontainersTest;
import util.FactoryObjectMother;

@DataJpaTest
@DisplayName("Integration - Empresa")
class EmpresaRepositoryIntegrationTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private EmpresaRepository empresaRepository;

    @BeforeEach
    void tearDown() {
        this.empresaRepository.deleteAll();
    }

    @Nested
    @DisplayName("Save")
    class SaveEmpresa {

        @Test
        @DisplayName("dados completos")
        void dadoEmpresaValida_quandoSave_entaoRetornarDadosCompletos() {
            var entidade = factory.gerarEmpresaEntityBuilder().build();
            var response = empresaRepository.save(entidade);
            Assertions.assertTrue(response.getId() > 0);
            Assertions.assertEquals(entidade.getNome(), response.getNome());
        }

    }

}

