package microservices.micro_empresas.adapter.in.controller.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.AbstractTestcontainersTest;

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - EmpresaCreateDtoRequest")
class EmpresaCreateDtoRequestUnitTest extends AbstractTestcontainersTest {

    @Autowired
    private Validator validator;

    @Nested
    @DisplayName("nome")
    class Nome {

        @Test
        @DisplayName("nulo")
        void dadoNomeNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new EmpresaCreateDtoRequest(null);
            Set<ConstraintViolation<EmpresaCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertEquals(1, violations.size());
        }
    }

}

