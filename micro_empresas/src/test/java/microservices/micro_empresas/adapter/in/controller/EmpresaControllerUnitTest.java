package microservices.micro_empresas.adapter.in.controller;

import microservices.micro_empresas.application.port.input.EmpresaCreateInputPort;
import microservices.micro_empresas.application.port.input.EmpresaDeleteInputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
@DisplayName("Unit - EmpresaController")
class EmpresaControllerUnitTest extends AbstractTestcontainersTest {

    @Mock
    private EmpresaDeleteInputPort empresaDeleteInputPort;

    @InjectMocks
    private EmpresaController empresaController;

    @Nested
    @DisplayName("Delete")
    class Create {

        @Test
        @DisplayName("nulo")
        void dadoIdNulo_quandoDelete_entaoLancarException() {
            Executable acao = () -> empresaController.delete(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
            Mockito.verifyNoInteractions(empresaDeleteInputPort);
        }
    }

}

