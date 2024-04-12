package microservices.micro_customers.application.core.usecase;

import microservices.micro_customers.adapter.out.CustomerFindByIdAdapter;
import microservices.micro_customers.adapter.out.CustomerSaveAdapter;
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
@DisplayName("Unit - CustomerUpdateUseCase")
class CustomerUpdateUseCaseUnitTest extends AbstractTestcontainersTest {

    @Mock
    CustomerSaveAdapter customerSaveAdapter;

    @Mock
    CustomerFindByIdAdapter customerFindByIdAdapter;

    @InjectMocks
    CustomerUpdateUseCase customerUpdateUseCase;

    @Test
    @DisplayName("nulo")
    void dadoCustomerNulo_quandoUpdate_entaoLancarException() {
        Executable acao = () -> this.customerUpdateUseCase.update(null);
        Assertions.assertThrows(NullPointerException.class, acao);
        Mockito.verifyNoInteractions(this.customerFindByIdAdapter);
        Mockito.verifyNoInteractions(this.customerSaveAdapter);
    }

}

