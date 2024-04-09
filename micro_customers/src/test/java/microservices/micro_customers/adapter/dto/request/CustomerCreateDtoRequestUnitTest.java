package microservices.micro_customers.adapter.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.*;
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
@DisplayName("Unit - CustomerCreateDtoRequest")
class CustomerCreateDtoRequestUnitTest extends AbstractTestcontainersTest {

    @Autowired
    private Validator validator;

    String cpfValido;

    String dataNascimentoValida;

    String emailValido;

    Set<TelefoneDto> telefonesValido;

    Set<EnderecoDto> enderecosValido;

    @BeforeEach
    void setUp() {
        cpfValido = FactoryObjectMother.faker.cpf().valid();
        dataNascimentoValida = "10/02/1977";
        emailValido = FactoryObjectMother.faker.internet().emailAddress();
        telefonesValido = Set.of(new TelefoneDto("77999994444", TipoTelefoneEnum.CELULAR));
        enderecosValido = Set.of(new EnderecoDto("78999999", "RS", "Porto Alegre", "Centro", "Avenida Central", "45", "Bata três vezes"));
    }

    @Nested
    @DisplayName("NomeCompleto")
    class NomeCompletoTest {

        @Test
        @DisplayName("nulo")
        void dadoCustomerCreateDtoRequestComNomeNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerCreateDtoRequest(null, cpfValido, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoCustomerCreateDtoRequestComNomeVazioOuEmBranco_quandoInstanciar_entaoLancarException(String valor) {
            var dtoRequest = new CustomerCreateDtoRequest(valor, cpfValido, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

}

