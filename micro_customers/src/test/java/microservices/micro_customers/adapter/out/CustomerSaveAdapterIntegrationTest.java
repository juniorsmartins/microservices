package microservices.micro_customers.adapter.out;

import microservices.micro_customers.util.AbstractTestcontainersTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - CustomerSaveAdapter")
class CustomerSaveAdapterIntegrationTest extends AbstractTestcontainersTest {

}

