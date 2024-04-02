package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - MapperOutImpl")
class MapperOutImplUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private MapperOut mapperOut;

    @Nested
    @DisplayName("ToCustomerEntity")
    class TestToCustomerEntity {

        @Test
        @DisplayName("nulo")
        void dadoCustomerNulo_quandoToCustomerEntity_entaoLancarException() {
            Executable acao = () -> mapperOut.toCustomerEntity(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }


    }

}

