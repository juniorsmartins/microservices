package microservices.micro_empresas.application.core.usecase;

import microservices.micro_empresas.adapter.out.EmpresaDeleteAdapter;
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
@DisplayName("Unit - EmpresaDeleteUseCase")
class EmpresaDeleteUseCaseUnitTest extends AbstractTestcontainersTest {

    @Mock
    private EmpresaDeleteAdapter empresaDeleteAdapter;

    @InjectMocks
    private EmpresaDeleteUseCase empresaDeleteUseCase;

    @Test
    @DisplayName("nulo")
    void dadoIdNulo_quandoDelete_EntaoLancarException() {
        Executable acao = () -> this.empresaDeleteUseCase.delete(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
        Mockito.verifyNoInteractions(this.empresaDeleteAdapter);
    }

}

