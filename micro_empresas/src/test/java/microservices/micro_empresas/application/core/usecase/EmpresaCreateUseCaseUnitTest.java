package microservices.micro_empresas.application.core.usecase;

import microservices.micro_empresas.adapter.out.EmpresaSaveAdapter;
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
@DisplayName("Unit - EmpresaCreateUseCase")
class EmpresaCreateUseCaseUnitTest extends AbstractTestcontainersTest {

    @Mock
    private EmpresaSaveAdapter empresaSaveAdapter;

    @InjectMocks
    private EmpresaCreateUseCase empresaCreateUseCase;

    @Test
    @DisplayName("nulo")
    void dadoEmpresaNula_quandoCreate_entaoLancarException() {
        Executable acao = () -> this.empresaCreateUseCase.create(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
        Mockito.verifyNoInteractions(this.empresaSaveAdapter);
    }
}

