package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
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
@DisplayName("Unit - MapperInImpl")
class MapperInImplUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    MapperIn mapperIn;

    @Nested
    @DisplayName("ToCustomer")
    class TestToCustomer {

        @Test
        @DisplayName("customerCreateDtoRequest nulo")
        void dadoCustomerCreateDtoRequestNulo_quandoToCustomer_entaoLancarException() {
            Executable acao = () -> mapperIn.toCustomer(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }

        @Test
        @DisplayName("customerCreateDtoRequest válido")
        void dadoCustomerCreateDtoRequestValido_quandoToCustomer_entaoConverterNormal() {
            var dtoRequest = factory.gerarCustomerCreateDtoRequestBuilder().build();
            var customer = mapperIn.toCustomer(dtoRequest);
            Assertions.assertInstanceOf(Customer.class, customer);

            Assertions.assertNull(customer.getCustomerId());
            Assertions.assertEquals(dtoRequest.nomeCompleto(), customer.getNomeCompleto());
            Assertions.assertEquals(dtoRequest.cpf(), customer.getCpf().getCpf());
            Assertions.assertEquals(dtoRequest.dataNascimento(), customer.getDataNascimento().getDataNascimentoString());
            Assertions.assertNull(customer.getStatusCadastro());
            Assertions.assertEquals(dtoRequest.email(), customer.getEmail().getEmail());

            Assertions.assertEquals(dtoRequest.telefones().size(), customer.getTelefones().size());
            Assertions.assertEquals(dtoRequest.enderecos().size(), customer.getEnderecos().size());
        }

        @Test
        @DisplayName("telefones e endereços nulos")
        void dadoCustomerCreateDtoRequestValidoComTelefonesNuloAndEndereçosNulo_quandoToCustomer_entaoConverterNormal() {
            var dtoRequest = factory.gerarCustomerCreateDtoRequestBuilder()
                .telefones(null)
                .enderecos(null)
                .build();

            var customer = mapperIn.toCustomer(dtoRequest);
            customer.setCustomerId(1L);
            customer.setStatusCadastro(StatusCadastroEnum.INICIADO);
            Assertions.assertInstanceOf(Customer.class, customer);

            Assertions.assertTrue(customer.getCustomerId() > 0);
            Assertions.assertEquals(dtoRequest.nomeCompleto(), customer.getNomeCompleto());
            Assertions.assertEquals(dtoRequest.cpf(), customer.getCpf().getCpf());
            Assertions.assertEquals(dtoRequest.dataNascimento(), customer.getDataNascimento().getDataNascimentoString());
            Assertions.assertEquals(StatusCadastroEnum.INICIADO, customer.getStatusCadastro());
            Assertions.assertEquals(dtoRequest.email(), customer.getEmail().getEmail());

            Assertions.assertTrue(customer.getTelefones().isEmpty());
            Assertions.assertNull(dtoRequest.telefones());

            Assertions.assertTrue(customer.getEnderecos().isEmpty());
            Assertions.assertNull(dtoRequest.enderecos());
        }
    }

    @Nested
    @DisplayName("ToCustomerCreateDtoResponse")
    class TestToCustomerCreateDtoResponse {

        @Test
        @DisplayName("customer nulo")
        void dadoCustomerNulo_quandoToCustomerCreateDtoResponse_entaoLancarException() {
            Executable acao = () -> mapperIn.toCustomer(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }

        @Test
        @DisplayName("customer válido")
        void dadoCustomerValido_quandoToCustomerCreateDtoResponse_entaoConverterNormal() {
            var customer = factory.gerarCustomerBuilder().build();
            var dtoResponse = mapperIn.toCustomerCreateDtoResponse(customer);
            Assertions.assertInstanceOf(CustomerCreateDtoResponse.class, dtoResponse);

            Assertions.assertEquals(dtoResponse.customerId(), customer.getCustomerId());
            Assertions.assertEquals(dtoResponse.nomeCompleto(), customer.getNomeCompleto());
            Assertions.assertEquals(dtoResponse.cpf(), customer.getCpf().getCpf());
            Assertions.assertEquals(dtoResponse.dataNascimento(), customer.getDataNascimento().getDataNascimentoString());
            Assertions.assertEquals(dtoResponse.statusCadastro(), customer.getStatusCadastro());
            Assertions.assertEquals(dtoResponse.email(), customer.getEmail().getEmail());

            Assertions.assertEquals(dtoResponse.telefones().size(), customer.getTelefones().size());
            Assertions.assertEquals(dtoResponse.enderecos().size(), customer.getEnderecos().size());
        }

        @Test
        @DisplayName("telefones e endereços nulos")
        void dadoCustomerValidoComTelefonesNuloAndEndereçosNulo_quandoToCustomerCreateDtoResponse_entaoConverterNormal() {
            var customer = factory.gerarCustomerBuilder()
                .telefones(null)
                .enderecos(null)
                .build();

            var dtoResponse = mapperIn.toCustomerCreateDtoResponse(customer);
            Assertions.assertInstanceOf(CustomerCreateDtoResponse.class, dtoResponse);

            Assertions.assertEquals(dtoResponse.customerId(), customer.getCustomerId());
            Assertions.assertEquals(dtoResponse.nomeCompleto(), customer.getNomeCompleto());
            Assertions.assertEquals(dtoResponse.cpf(), customer.getCpf().getCpf());
            Assertions.assertEquals(dtoResponse.dataNascimento(), customer.getDataNascimento().getDataNascimentoString());
            Assertions.assertEquals(dtoResponse.statusCadastro(), customer.getStatusCadastro());
            Assertions.assertEquals(dtoResponse.email(), customer.getEmail().getEmail());

            Assertions.assertNull(customer.getTelefones());
            Assertions.assertTrue(dtoResponse.telefones().isEmpty());

            Assertions.assertNull(customer.getEnderecos());
            Assertions.assertTrue(dtoResponse.enderecos().isEmpty());
        }
    }

}

