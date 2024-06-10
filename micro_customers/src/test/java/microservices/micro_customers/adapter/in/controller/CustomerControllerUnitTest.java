package microservices.micro_customers.adapter.in.controller;

import microservices.micro_customers.adapter.mapper.MapperIn;
import microservices.micro_customers.adapter.out.CustomerSearchAdapter;
import microservices.micro_customers.application.core.usecase.CustomerCreateUseCase;
import microservices.micro_customers.application.core.usecase.CustomerDeleteUseCase;
import microservices.micro_customers.util.AbstractTestcontainersTest;
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

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - CustomerController")
class CustomerControllerUnitTest extends AbstractTestcontainersTest {

    @Mock
    CustomerCreateUseCase customerCreateUseCase;

    @Mock
    CustomerSearchAdapter customerSearchAdapter;

    @Mock
    CustomerDeleteUseCase customerDeleteUseCase;

    @Mock
    MapperIn mapperIn;

    @InjectMocks
    CustomerController customerController;

    @Nested
    @DisplayName("Delete")
    class DeleteTest {

        @Test
        @DisplayName("customerId nulo")
        void dadoCustomerIdNulo_quandoDeleteByID_entaoLancarException() {
            Executable acao = () -> customerController.deleteById(null, null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
            Mockito.verifyNoInteractions(customerCreateUseCase);
            Mockito.verifyNoInteractions(customerSearchAdapter);
            Mockito.verifyNoInteractions(customerDeleteUseCase);
            Mockito.verifyNoInteractions(mapperIn);
        }
    }

}

