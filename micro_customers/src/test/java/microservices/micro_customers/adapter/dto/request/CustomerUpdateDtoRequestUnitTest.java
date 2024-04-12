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
@DisplayName("Unit - CustomerUpdateDtoRequest")
class CustomerUpdateDtoRequestUnitTest extends AbstractTestcontainersTest {

    @Autowired
    private Validator validator;

    Long customerId;

    String nomeCompletoValido;

    String cpfValido;

    String dataNascimentoValida;

    String emailValido;

    Set<TelefoneDto> telefonesValido;

    Set<EnderecoDto> enderecosValido;

    @BeforeEach
    void setUp() {
        customerId = 1L;
        nomeCompletoValido = FactoryObjectMother.faker.artist().name();
        cpfValido = FactoryObjectMother.faker.cpf().valid();
        dataNascimentoValida = "10/02/1977";
        emailValido = FactoryObjectMother.faker.internet().emailAddress();
        telefonesValido = Set.of(new TelefoneDto("77999994444", TipoTelefoneEnum.CELULAR));
        enderecosValido = Set.of(new EnderecoDto("78999999", "RS", "Porto Alegre", "Centro", "Avenida Central", "45", "Bata três vezes"));
    }

    @Nested
    @DisplayName("CustomerId")
    class CustomerIdTest {

        @Test
        @DisplayName("nulo")
        void dadoCustomerUpdateDtoRequestComCustomerIdNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerUpdateDtoRequest(null, nomeCompletoValido, cpfValido,
                dataNascimentoValida, emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(longs = {0L, -1L})
        void dadoCustomerUpdateDtoRequestComCustomerIdNaoPositivo_quandoInstanciar_entaoLancarException(Long valor) {
            var dtoRequest = new CustomerUpdateDtoRequest(valor, nomeCompletoValido, cpfValido, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("NomeCompleto")
    class NomeCompletoTest {

        @Test
        @DisplayName("nulo")
        void dadoCustomerUpdateDtoRequestComNomeNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, null, cpfValido, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoCustomerUpdateDtoRequestComNomeVazioOuEmBranco_quandoInstanciar_entaoLancarException(String valor) {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, valor, cpfValido, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @Test
        @DisplayName("máximo caracteres excedido")
        void dadoCustomerUpdateDtoRequestComNomeComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, FactoryObjectMother.faker.lorem()
                .characters(Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO + 1), cpfValido, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("CPF")
    class CpfTest {

        @Test
        @DisplayName("nulo")
        void dadoCustomerUpdateDtoRequestComCpfNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, nomeCompletoValido, null, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoCustomerUpdateDtoRequestComCpfVazioOuEmBranco_quandoInstanciar_entaoLancarException(String valor) {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, nomeCompletoValido, valor, dataNascimentoValida,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @Test
        @DisplayName("máximo caracteres excedido")
        void dadoCustomerUpdateDtoRequestComCpfComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, nomeCompletoValido, "123456789012345",
                dataNascimentoValida, emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("DataNascimento")
    class DataNascimentoTest {

        @ParameterizedTest
        @ValueSource(strings = {"20-20-2020", "2020/10/10", "05/125/1990"})
        @DisplayName("formatos inválidos")
        void dadoCustomerUpdateDtoRequestComDataNascimentoEmFormatoInvalido_quandoInstanciar_entaoLancarException(String valor) {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, nomeCompletoValido, cpfValido, valor,
                emailValido, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Email")
    class EmailTest {

        @Test
        @DisplayName("nulo")
        void dadoCustomerUpdateDtoRequestComEmailNulo_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, nomeCompletoValido, cpfValido,
                dataNascimentoValida, null, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoCustomerUpdateDtoRequestComEmailVazioOuEmBranco_quandoInstanciar_entaoLancarException(String valor) {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, nomeCompletoValido, cpfValido,
                dataNascimentoValida, valor, telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }

        @Test
        @DisplayName("máximo caracteres excedido")
        void dadoCustomerUpdateDtoRequestComEmailComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dtoRequest = new CustomerUpdateDtoRequest(customerId, nomeCompletoValido, cpfValido,
                dataNascimentoValida, FactoryObjectMother.faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_EMAIL + 1),
                telefonesValido, enderecosValido);
            Set<ConstraintViolation<CustomerUpdateDtoRequest>> violations = validator.validate(dtoRequest);
            Assertions.assertFalse(violations.isEmpty());
        }
    }
}

