package microservices.micro_empresas.adapter.out.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.AbstractTestcontainersTest;
import util.FactoryObjectMother;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration - EmpresaRepository")
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
            Assertions.assertTrue(response.getEmpresaId() > 0);
            Assertions.assertEquals(entidade.getNome(), response.getNome());
        }

    }

}

