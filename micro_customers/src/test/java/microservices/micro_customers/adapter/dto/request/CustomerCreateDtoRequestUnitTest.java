package microservices.micro_customers.adapter.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.application.core.constant.Constantes;
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

    String nomeCompletoValido;

    String cpfValido;

    String dataNascimentoValida;

    String emailValido;

    Set<TelefoneDto> telefonesValido;

    Set<EnderecoDto> enderecosValido;

    @BeforeEach
    void setUp() {
        nomeCompletoValido = FactoryObjectMother.faker.artist().name();
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

        @Test
        @DisplayName("máximo caracteres excedido")
        void dadoCustomerCreateDtoRequestComNomeComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerCreateDtoRequest(FactoryObjectMother.faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO + 1), cpfValido,
                dataNascimentoValida, emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("CPF")
    class CpfTest {

        @Test
        @DisplayName("nulo")
        void dadoCustomerCreateDtoRequestComCpfNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerCreateDtoRequest(nomeCompletoValido, null, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoCustomerCreateDtoRequestComCpfVazioOuEmBranco_quandoInstanciar_entaoLancarException(String valor) {
            var dtoRequest = new CustomerCreateDtoRequest(nomeCompletoValido, valor, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @Test
        @DisplayName("máximo caracteres excedido")
        void dadoCustomerCreateDtoRequestComCpfComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerCreateDtoRequest(nomeCompletoValido, "123456789012345",
                dataNascimentoValida, emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("DataNascimento")
    class DataNascimentoTest {

        @ParameterizedTest
        @ValueSource(strings = {"20-20-2020", "2020/10/10", "05/125/1990"})
        @DisplayName("formatos inválidos")
        void dadoCustomerCreateDtoRequestComDataNascimentoEmFormatoInvalido_quandoInstanciar_entaoLancarException(String valor) {
            var dtoRequest = new CustomerCreateDtoRequest(nomeCompletoValido, cpfValido, valor, emailValido,
                telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Email")
    class EmailTest {

        @Test
        @DisplayName("nulo")
        void dadoCustomerCreateDtoRequestComEmailNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerCreateDtoRequest(nomeCompletoValido, cpfValido, dataNascimentoValida,
                null, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoCustomerCreateDtoRequestComEmailVazioOuEmBranco_quandoInstanciar_entaoLancarException(String valor) {
            var dtoRequest = new CustomerCreateDtoRequest(nomeCompletoValido, cpfValido, dataNascimentoValida,
                valor, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @Test
        @DisplayName("máximo caracteres excedido")
        void dadoCustomerCreateDtoRequestComEmailComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerCreateDtoRequest(nomeCompletoValido, cpfValido, dataNascimentoValida,
                FactoryObjectMother.faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_EMAIL + 1),
                telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerCreateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

}

