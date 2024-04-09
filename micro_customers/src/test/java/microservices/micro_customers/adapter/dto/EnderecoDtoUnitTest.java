package microservices.micro_customers.adapter.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import microservices.micro_customers.application.core.constant.Constantes;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
@DisplayName("Unit - EnderecoDto")
class EnderecoDtoUnitTest extends AbstractTestcontainersTest {

    @Autowired
    Validator validator;

    @Nested
    @DisplayName("Cep")
    class CepTest {

        @ParameterizedTest
        @ValueSource(strings = {"7800000", "78.000-0000"})
        @DisplayName("limites excedidos")
        void dadoTelefoneDtoComCepComMaximoAndMininoCaracteresExcedido_quandoInstanciar_entaoLancarException(String valor) {
            var dto = new EnderecoDto(valor, "SC", "Florianópolis", "Lagoa da Conceição", "Lateral Sul", "12", "Toque a campainha");
            Set<ConstraintViolation<EnderecoDto>> violations = validator.validate(dto);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Estado")
    class EstadoTest {

        @ParameterizedTest
        @ValueSource(strings = {"P", "PRR"})
        @DisplayName("limites excedidos")
        void dadoTelefoneDtoComEstadoComMaximoAndMininoCaracteresExcedido_quandoInstanciar_entaoLancarException(String valor) {
            var dto = new EnderecoDto("78.000-000", valor, "Florianópolis", "Lagoa da Conceição", "Lateral Sul", "12", "Toque a campainha");
            Set<ConstraintViolation<EnderecoDto>> violations = validator.validate(dto);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Cidade")
    class CidadeTest {

        @Test
        @DisplayName("limites excedidos")
        void dadoTelefoneDtoComCidadeComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dto = new EnderecoDto("78.000-000", "PR", FactoryObjectMother.faker.lorem().characters(Constantes.
                MAX_CARACTERES_CUSTOMER_ENDERECO_CIDADE + 1), "Lagoa da Conceição", "Lateral Sul", "12", "Toque a campainha");
            Set<ConstraintViolation<EnderecoDto>> violations = validator.validate(dto);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Bairro")
    class BairroTest {

        @Test
        @DisplayName("limites excedidos")
        void dadoTelefoneDtoComBairroComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dto = new EnderecoDto("78.000-000", "PR", "Curitiba", FactoryObjectMother.faker.lorem()
                .characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_BAIRRO + 1), "Rua Lateral Sul", "12",
                "Toque a campainha");
            Set<ConstraintViolation<EnderecoDto>> violations = validator.validate(dto);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Logradouro")
    class LogradouroTest {

        @Test
        @DisplayName("limites excedidos")
        void dadoTelefoneDtoComLogradouroComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dto = new EnderecoDto("78.000-000", "PR", "Curitiba", "Central Dois", FactoryObjectMother.faker.lorem()
                .characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_LOGRADOURO + 1), "12", "Toque a campainha");
            Set<ConstraintViolation<EnderecoDto>> violations = validator.validate(dto);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Número")
    class NumeroTest {

        @Test
        @DisplayName("limites excedidos")
        void dadoTelefoneDtoComNumeroComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dto = new EnderecoDto("78.000-000", "PR", "Curitiba", "Central Dois", "Rua X", FactoryObjectMother.faker
                .lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_NUMERO + 1), "Toque a campainha");
            Set<ConstraintViolation<EnderecoDto>> violations = validator.validate(dto);
            Assertions.assertFalse(violations.isEmpty());
        }
    }

    @Nested
    @DisplayName("Complemento")
    class ComplementoTest {

        @Test
        @DisplayName("limites excedidos")
        void dadoTelefoneDtoComComplementoComMaximoCaracteresExcedido_quandoInstanciar_entaoLancarException() {
            var dto = new EnderecoDto("78.000-000", "PR", "Curitiba", "Bairro Central Dois", "Rua X", "55",
                FactoryObjectMother.faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_COMPLEMENTO + 1));
            Set<ConstraintViolation<EnderecoDto>> violations = validator.validate(dto);
            Assertions.assertFalse(violations.isEmpty());
        }
    }


}

