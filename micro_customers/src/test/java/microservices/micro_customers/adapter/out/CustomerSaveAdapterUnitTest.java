package microservices.micro_customers.adapter.out;

import microservices.micro_customers.adapter.mapper.MapperOut;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
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
@DisplayName("Unit - CustomerSaveAdapter")
class CustomerSaveAdapterUnitTest extends AbstractTestcontainersTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MapperOut mapperOut;

    @InjectMocks
    private CustomerSaveAdapter customerSaveAdapter;

    @Test
    @DisplayName("nulo")
    void dadoCustomerNulo_quandoSave_entaoLancarException() {
        Executable acao = () -> this.customerSaveAdapter.save(null);
        Assertions.assertThrows(NoSuchElementException.class, acao);
        Mockito.verifyNoInteractions(this.customerRepository);
        Mockito.verifyNoInteractions(this.mapperOut);
    }

}

