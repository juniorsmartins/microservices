package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.adapter.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.application.core.domain.tipos.Telefone;
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
            Executable acao = () -> mapperIn.toCustomerCreate(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }

        @Test
        @DisplayName("customerCreateDtoRequest válido")
        void dadoCustomerCreateDtoRequestValido_quandoToCustomer_entaoConverterNormal() {
            var dtoRequest = factory.gerarCustomerCreateDtoRequestBuilder().build();
            var customer = mapperIn.toCustomerCreate(dtoRequest);
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
        void dadoCustomerCreateDtoRequestValidoComTelefonesNuloAndEnderecosNulo_quandoToCustomer_entaoConverterNormal() {
            var dtoRequest = factory.gerarCustomerCreateDtoRequestBuilder()
                    .telefones(null)
                    .enderecos(null)
                    .build();

            var customer = mapperIn.toCustomerCreate(dtoRequest);
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

        @Test
        @DisplayName("dados de telefone")
        void dadoCustomerCreateDtoRequestValidoComDoisTelefones_quandoToCustomer_entaoRetornarDadosCorretosDeTelefones() {
            var createDtoRequest = factory.gerarCustomerCreateDtoRequestBuilder().build();
            var response = mapperIn.toCustomerCreate(createDtoRequest);

            Assertions.assertEquals(createDtoRequest.telefones().size(), response.getTelefones().size());

            for (TelefoneDto dto : createDtoRequest.telefones()) {
                Telefone fone = response.getTelefones()
                    .stream()
                    .filter(tel -> tel.getNumero().equals(dto.numero()) && tel.getTipo().equals(dto.tipo()))
                    .findFirst()
                    .orElse(null);

                Assertions.assertEquals(dto.numero(), fone.getNumero());
                Assertions.assertEquals(dto.tipo(), fone.getTipo());
            }
        }
    }

    @Nested
    @DisplayName("ToCustomerCreateDtoResponse")
    class TestToCustomerCreateDtoResponse {

        @Test
        @DisplayName("customer nulo")
        void dadoCustomerNulo_quandoToCustomerCreateDtoResponse_entaoLancarException() {
            Executable acao = () -> mapperIn.toCustomerCreate(null);
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
        void dadoCustomerValidoComTelefonesNuloAndEnderecosNulo_quandoToCustomerCreateDtoResponse_entaoConverterNormal() {
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

        @Test
        @DisplayName("dados de telefone")
        void dadoCustomerValidoComDoisTelefones_quandoToCustomerCreateDtoResponse_entaoRetornarDadosCorretosDeTelefones() {
            var customer = factory.gerarCustomerBuilder().build();
            var response = mapperIn.toCustomerCreateDtoResponse(customer);

            Assertions.assertEquals(customer.getTelefones().size(), response.telefones().size());

            for (Telefone fone : customer.getTelefones()) {
                TelefoneDto dto = response.telefones()
                    .stream()
                    .filter(telDto -> telDto.numero().equals(fone.getNumero()) && telDto.tipo().equals(fone.getTipo()))
                    .findFirst()
                    .orElse(null);

                Assertions.assertEquals(dto.numero(), fone.getNumero());
                Assertions.assertEquals(dto.tipo(), fone.getTipo());
            }
        }
    }

}

