package microservices.micro_customers.adapter.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - TelefoneDto")
class TelefoneDtoUnitTest extends AbstractTestcontainersTest {

    @Autowired
    Validator validator;

    @Nested
    @DisplayName("Número")
    class NumeroTelefoneTest {

        @ParameterizedTest
        @ValueSource(strings = {"123456789", "123456789012"})
        @DisplayName("limites excedidos")
        void dadoTelefoneDtoComMaximoAndMininoCaracteresExcedido_quandoInstanciar_entaoLancarException(String valor) {
            var dto = new TelefoneDto(valor, TipoTelefoneEnum.FIXO);
            Set<ConstraintViolation<TelefoneDto>> violations = validator.validate(dto);
            Assertions.assertFalse(violations.isEmpty());
        }
    }
}

