package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.in.dto.response.CustomerCreateDtoResponse;
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
    private MapperIn mapperIn;

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
            Assertions.assertTrue(customer instanceof Customer);

            Assertions.assertTrue(customer.getCustomerId() == null);
            Assertions.assertEquals(dtoRequest.nomeCompleto(), customer.getNomeCompleto());
            Assertions.assertEquals(dtoRequest.cpf(), customer.getCpf().getCpf());
            Assertions.assertEquals(dtoRequest.dataNascimento(), customer.getDataNascimento().getDataNascimentoString());
            Assertions.assertTrue(customer.getStatusCadastro() == null);
            Assertions.assertEquals(dtoRequest.email(), customer.getEmail().getEmail());

            Assertions.assertEquals(dtoRequest.telefones().size(), customer.getTelefones().size());

            Assertions.assertEquals(dtoRequest.endereco().cep(), customer.getEndereco().getCep());
            Assertions.assertEquals(dtoRequest.endereco().estado(), customer.getEndereco().getEstado());
            Assertions.assertEquals(dtoRequest.endereco().cidade(), customer.getEndereco().getCidade());
            Assertions.assertEquals(dtoRequest.endereco().bairro(), customer.getEndereco().getBairro());
            Assertions.assertEquals(dtoRequest.endereco().logradouro(), customer.getEndereco().getLogradouro());
            Assertions.assertEquals(dtoRequest.endereco().numero(), customer.getEndereco().getNumero());
            Assertions.assertEquals(dtoRequest.endereco().complemento(), customer.getEndereco().getComplemento());
        }

        @Test
        @DisplayName("telefones nulo")
        void dadoCustomerCreateDtoRequestValidoComTelefonesNulo_quandoToCustomer_entaoConverterNormal() {
            var dtoRequest = factory.gerarCustomerCreateDtoRequestBuilder().telefones(null).build();
            var customer = mapperIn.toCustomer(dtoRequest);
            customer.setCustomerId(1L);
            customer.setStatusCadastro(StatusCadastroEnum.INICIADO);
            Assertions.assertTrue(customer instanceof Customer);

            Assertions.assertTrue(customer.getCustomerId() > 0);
            Assertions.assertEquals(dtoRequest.nomeCompleto(), customer.getNomeCompleto());
            Assertions.assertEquals(dtoRequest.cpf(), customer.getCpf().getCpf());
            Assertions.assertEquals(dtoRequest.dataNascimento(), customer.getDataNascimento().getDataNascimentoString());
            Assertions.assertEquals(StatusCadastroEnum.INICIADO, customer.getStatusCadastro());
            Assertions.assertEquals(dtoRequest.email(), customer.getEmail().getEmail());

            Assertions.assertTrue(customer.getTelefones().isEmpty());
            Assertions.assertNull(dtoRequest.telefones());

            Assertions.assertEquals(dtoRequest.endereco().cep(), customer.getEndereco().getCep());
            Assertions.assertEquals(dtoRequest.endereco().estado(), customer.getEndereco().getEstado());
            Assertions.assertEquals(dtoRequest.endereco().cidade(), customer.getEndereco().getCidade());
            Assertions.assertEquals(dtoRequest.endereco().bairro(), customer.getEndereco().getBairro());
            Assertions.assertEquals(dtoRequest.endereco().logradouro(), customer.getEndereco().getLogradouro());
            Assertions.assertEquals(dtoRequest.endereco().numero(), customer.getEndereco().getNumero());
            Assertions.assertEquals(dtoRequest.endereco().complemento(), customer.getEndereco().getComplemento());
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
            Assertions.assertTrue(dtoResponse instanceof CustomerCreateDtoResponse);

            Assertions.assertEquals(dtoResponse.customerId(), customer.getCustomerId());
            Assertions.assertEquals(dtoResponse.nomeCompleto(), customer.getNomeCompleto());
            Assertions.assertEquals(dtoResponse.cpf(), customer.getCpf().getCpf());
            Assertions.assertEquals(dtoResponse.dataNascimento(), customer.getDataNascimento().getDataNascimentoString());
            Assertions.assertEquals(dtoResponse.statusCadastro(), customer.getStatusCadastro());
            Assertions.assertEquals(dtoResponse.email(), customer.getEmail().getEmail());

            Assertions.assertEquals(dtoResponse.telefones().size(), customer.getTelefones().size());

            Assertions.assertEquals(dtoResponse.endereco().cep(), customer.getEndereco().getCep());
            Assertions.assertEquals(dtoResponse.endereco().estado(), customer.getEndereco().getEstado());
            Assertions.assertEquals(dtoResponse.endereco().cidade(), customer.getEndereco().getCidade());
            Assertions.assertEquals(dtoResponse.endereco().bairro(), customer.getEndereco().getBairro());
            Assertions.assertEquals(dtoResponse.endereco().logradouro(), customer.getEndereco().getLogradouro());
            Assertions.assertEquals(dtoResponse.endereco().numero(), customer.getEndereco().getNumero());
            Assertions.assertEquals(dtoResponse.endereco().complemento(), customer.getEndereco().getComplemento());
        }

        @Test
        @DisplayName("telefones nulo")
        void dadoCustomerValidoComTelefonesNulo_quandoToCustomerCreateDtoResponse_entaoConverterNormal() {
            var customer = factory.gerarCustomerBuilder().telefones(null).build();
            var dtoResponse = mapperIn.toCustomerCreateDtoResponse(customer);
            Assertions.assertTrue(dtoResponse instanceof CustomerCreateDtoResponse);

            Assertions.assertEquals(dtoResponse.customerId(), customer.getCustomerId());
            Assertions.assertEquals(dtoResponse.nomeCompleto(), customer.getNomeCompleto());
            Assertions.assertEquals(dtoResponse.cpf(), customer.getCpf().getCpf());
            Assertions.assertEquals(dtoResponse.dataNascimento(), customer.getDataNascimento().getDataNascimentoString());
            Assertions.assertEquals(dtoResponse.statusCadastro(), customer.getStatusCadastro());
            Assertions.assertEquals(dtoResponse.email(), customer.getEmail().getEmail());

            Assertions.assertNull(customer.getTelefones());
            Assertions.assertTrue(dtoResponse.telefones().isEmpty());

            Assertions.assertEquals(dtoResponse.endereco().cep(), customer.getEndereco().getCep());
            Assertions.assertEquals(dtoResponse.endereco().estado(), customer.getEndereco().getEstado());
            Assertions.assertEquals(dtoResponse.endereco().cidade(), customer.getEndereco().getCidade());
            Assertions.assertEquals(dtoResponse.endereco().bairro(), customer.getEndereco().getBairro());
            Assertions.assertEquals(dtoResponse.endereco().logradouro(), customer.getEndereco().getLogradouro());
            Assertions.assertEquals(dtoResponse.endereco().numero(), customer.getEndereco().getNumero());
            Assertions.assertEquals(dtoResponse.endereco().complemento(), customer.getEndereco().getComplemento());
        }
    }

}

