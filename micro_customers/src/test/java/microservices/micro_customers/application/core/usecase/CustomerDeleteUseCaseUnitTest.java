package microservices.micro_customers.application.core.usecase;

import microservices.micro_customers.adapter.out.CustomerDeleteAdapter;
import microservices.micro_customers.util.AbstractTestcontainersTest;
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

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - CustomerDeleteUse")
class CustomerDeleteUseCaseUnitTest extends AbstractTestcontainersTest {

    @Mock
    CustomerDeleteAdapter customerDeleteAdapter;

    @InjectMocks
    CustomerDeleteUseCase customerDeleteUseCase;

    @Test
    @DisplayName("nulo")
    void dadoCustomerIdNulo_quandoDelete_entaoLancarException() {
        Executable acao = () -> this.customerDeleteUseCase.delete(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
        Mockito.verifyNoInteractions(this.customerDeleteAdapter);
    }
}

