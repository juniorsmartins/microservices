package microservices.micro_empresas.adapter.in.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.AbstractTestcontainersTest;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration - EmpresaController")
class EmpresaControllerIntegrationTest extends AbstractTestcontainersTest {

    private static final String END_POINT = "/api/v1/empresas";


}

