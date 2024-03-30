package microservices.micro_empresas.adapter.out;

import microservices.micro_empresas.adapter.out.repository.EmpresaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.AbstractTestcontainersTest;

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - EmpresaDeleteAdapter")
class EmpresaDeleteAdapterUnitTest extends AbstractTestcontainersTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaDeleteAdapter empresaDeleteAdapter;

    @Test
    @DisplayName("id nulo")
    void dadoIdNulo_quandoDelete_entaoLancarException() {
        Executable acao = () -> this.empresaDeleteAdapter.delete(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
        Mockito.verifyNoInteractions(empresaRepository);
    }
}

