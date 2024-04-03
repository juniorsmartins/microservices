package microservices.micro_customers.adapter.out;

import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - CustomerSaveAdapter")
class CustomerSaveAdapterIntegrationTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private CustomerSaveAdapter customerSaveAdapter;

    @Test
    @DisplayName("customer válido")
    void dadoCustomerValidoSemId_quandoSave_entaoRetornarComId() {
        var customer = factory.gerarCustomerBuilder().build();
        Assertions.assertNull(customer.getCustomerId());

        var customerSalvo = this.customerSaveAdapter.save(customer);
        Assertions.assertTrue(customerSalvo.getCustomerId() >= 1);
    }

}

