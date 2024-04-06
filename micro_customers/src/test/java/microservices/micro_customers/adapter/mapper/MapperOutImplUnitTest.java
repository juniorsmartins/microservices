package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.adapter.dto.response.CustomerSearchDtoResponse;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;
import microservices.micro_customers.application.core.domain.Customer;
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

import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - MapperOutImpl")
class MapperOutImplUnitTest extends AbstractTestcontainersTest {

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    MapperOut mapperOut;

    @Nested
    @DisplayName("ToCustomerEntity")
    class TestToCustomerEntity {

        @Test
        @DisplayName("customer nulo")
        void dadoCustomerNulo_quandoToCustomerEntity_entaoLancarException() {
            Executable acao = () -> mapperOut.toCustomerEntity(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }

        @Test
        @DisplayName("customer válido")
        void dadoCustomerValido_quandoToCustomerEntity_entaoConverterNormal() {
            var customer = factory.gerarCustomerBuilder().build();
            var entity = mapperOut.toCustomerEntity(customer);
            Assertions.assertInstanceOf(CustomerEntity.class, entity);

            Assertions.assertEquals(customer.getCustomerId(), entity.getCustomerId());
            Assertions.assertEquals(customer.getNomeCompleto(), entity.getNomeCompleto());
            Assertions.assertEquals(customer.getCpf().getCpf(), entity.getCpf());
            Assertions.assertEquals(customer.getDataNascimento().getDataNascimentoLocalDate(), entity.getDataNascimento());
            Assertions.assertEquals(customer.getStatusCadastro(), entity.getStatusCadastro());
            Assertions.assertEquals(customer.getEmail().getEmail(), entity.getEmail());

            Assertions.assertEquals(customer.getTelefones().size(), entity.getTelefones().size());
            Assertions.assertEquals(customer.getEnderecos().size(), entity.getEnderecos().size());
        }

        @Test
        @DisplayName("telefones e endereços nulos")
        void dadoCustomerValidoComTelefonesNuloAndEnderecosNulo_quandoToCustomerEntity_entaoConverterNormal() {
            var customer = factory.gerarCustomerBuilder()
                .telefones(null)
                .enderecos(null)
                .build();

            var entity = mapperOut.toCustomerEntity(customer);
            Assertions.assertInstanceOf(CustomerEntity.class, entity);

            Assertions.assertEquals(customer.getCustomerId(), entity.getCustomerId());
            Assertions.assertEquals(customer.getNomeCompleto(), entity.getNomeCompleto());
            Assertions.assertEquals(customer.getCpf().getCpf(), entity.getCpf());
            Assertions.assertEquals(customer.getDataNascimento().getDataNascimentoLocalDate(), entity.getDataNascimento());
            Assertions.assertEquals(customer.getStatusCadastro(), entity.getStatusCadastro());
            Assertions.assertEquals(customer.getEmail().getEmail(), entity.getEmail());

            Assertions.assertTrue(entity.getTelefones().isEmpty());
            Assertions.assertNull(customer.getTelefones());

            Assertions.assertTrue(entity.getEnderecos().isEmpty());
            Assertions.assertNull(customer.getEnderecos());
        }

        @Test
        @DisplayName("dados de telefone")
        void dadoCustomerValidoComDoisTelefones_quandoToCustomerEntity_entaoRetornarDadosCorretosDeTelefones() {
            var customer = factory.gerarCustomerBuilder().build();
            var response = mapperOut.toCustomerEntity(customer);

            Assertions.assertEquals(customer.getTelefones().size(), response.getTelefones().size());

            for (Telefone fone : customer.getTelefones()) {
                TelefoneVo telVo = response.getTelefones()
                    .stream()
                    .filter(telDto -> telDto.getNumero().equals(fone.getNumero()) && telDto.getTipo().equals(fone.getTipo()))
                    .findFirst()
                    .orElse(null);

                Assertions.assertEquals(telVo.getNumero(), fone.getNumero());
                Assertions.assertEquals(telVo.getTipo(), fone.getTipo());
            }
        }
    }

    @Nested
    @DisplayName("ToCustomer")
    class TestToCustomer {

        @Test
        @DisplayName("customerEntity nulo")
        void dadoCustomerEntityNulo_quandoToCustomer_entaoLancarException() {
            Executable acao = () -> mapperOut.toCustomer(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }

        @Test
        @DisplayName("customerEntity válido")
        void dadoCustomerEntityValido_quandoToCustomer_entaoConverterNormal() {
            var entity = factory.gerarCustomerEntityBuilder().build();
            var customer = mapperOut.toCustomer(entity);
            Assertions.assertInstanceOf(Customer.class, customer);

            Assertions.assertEquals(customer.getCustomerId(), entity.getCustomerId());
            Assertions.assertEquals(customer.getNomeCompleto(), entity.getNomeCompleto());
            Assertions.assertEquals(customer.getCpf().getCpf(), entity.getCpf());
            Assertions.assertEquals(customer.getDataNascimento().getDataNascimentoLocalDate(), entity.getDataNascimento());
            Assertions.assertEquals(customer.getStatusCadastro(), entity.getStatusCadastro());
            Assertions.assertEquals(customer.getEmail().getEmail(), entity.getEmail());

            Assertions.assertEquals(customer.getTelefones().size(), entity.getTelefones().size());
            Assertions.assertEquals(customer.getEnderecos().size(), entity.getEnderecos().size());
        }

        @Test
        @DisplayName("telefones e endereços nulos")
        void dadoCustomerEntityValidoComTelefonesNuloAndEnderecosNulo_quandoToCustomer_entaoConverterNormal() {
            var entity = factory.gerarCustomerEntityBuilder()
                .telefones(null)
                .enderecos(null)
                .build();

            var customer = mapperOut.toCustomer(entity);
            Assertions.assertInstanceOf(Customer.class, customer);

            Assertions.assertEquals(customer.getCustomerId(), entity.getCustomerId());
            Assertions.assertEquals(customer.getNomeCompleto(), entity.getNomeCompleto());
            Assertions.assertEquals(customer.getCpf().getCpf(), entity.getCpf());
            Assertions.assertEquals(customer.getDataNascimento().getDataNascimentoLocalDate(), entity.getDataNascimento());
            Assertions.assertEquals(customer.getStatusCadastro(), entity.getStatusCadastro());
            Assertions.assertEquals(customer.getEmail().getEmail(), entity.getEmail());

            Assertions.assertNull(entity.getTelefones());
            Assertions.assertTrue(customer.getTelefones().isEmpty());

            Assertions.assertNull(entity.getEnderecos());
            Assertions.assertTrue(customer.getEnderecos().isEmpty());
        }

        @Test
        @DisplayName("dados de telefone")
        void dadoCustomerEntityValidoComDoisTelefones_quandoToCustomer_entaoRetornarDadosCorretosDeTelefones() {
            var entity = factory.gerarCustomerEntityBuilder().build();
            var response = mapperOut.toCustomer(entity);

            Assertions.assertEquals(entity.getTelefones().size(), response.getTelefones().size());

            for (TelefoneVo foneVo : entity.getTelefones()) {
                Telefone fone = response.getTelefones()
                    .stream()
                    .filter(tel -> tel.getNumero().equals(foneVo.getNumero()) && tel.getTipo().equals(foneVo.getTipo()))
                    .findFirst()
                    .orElse(null);

                Assertions.assertEquals(foneVo.getNumero(), fone.getNumero());
                Assertions.assertEquals(foneVo.getTipo(), fone.getTipo());
            }
        }
    }

    @Nested
    @DisplayName("ToCustomerSearchDtoResponse")
    class TestToCustomerSearchDtoResponse {

        @Test
        @DisplayName("customerEntity nulo")
        void dadoCustomerEntityNulo_quandoToCustomerSearchDtoResponse_entaoLancarException() {
            Executable acao = () -> mapperOut.toCustomerSearchDtoResponse(null);
            Assertions.assertThrows(NoSuchElementException.class, acao);
        }

        @Test
        @DisplayName("customerEntity válido")
        void dadoCustomerEntityValido_quandoToCustomerSearchDtoResponse_entaoConverterNormal() {
            var entity = factory.gerarCustomerEntityBuilder().build();
            var response = mapperOut.toCustomerSearchDtoResponse(entity);
            Assertions.assertInstanceOf(CustomerSearchDtoResponse.class, response);

            Assertions.assertEquals(response.customerId(), entity.getCustomerId());
            Assertions.assertEquals(response.nomeCompleto(), entity.getNomeCompleto());
            Assertions.assertEquals(response.cpf(), entity.getCpf());
            Assertions.assertEquals(response.dataNascimento(), entity.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            Assertions.assertEquals(response.statusCadastro(), entity.getStatusCadastro());
            Assertions.assertEquals(response.email(), entity.getEmail());

            Assertions.assertEquals(response.telefones().size(), entity.getTelefones().size());
            Assertions.assertEquals(response.enderecos().size(), entity.getEnderecos().size());
        }

        @Test
        @DisplayName("telefones e endereços nulos")
        void dadoCustomerEntityValidoComTelefonesNuloAndEnderecosNulo_quandoToCustomerSearchDtoResponse_entaoConverterNormal() {
            var entity = factory.gerarCustomerEntityBuilder()
                .telefones(null)
                .enderecos(null)
                .build();

            var response = mapperOut.toCustomerSearchDtoResponse(entity);
            Assertions.assertInstanceOf(CustomerSearchDtoResponse.class, response);

            Assertions.assertEquals(response.customerId(), entity.getCustomerId());
            Assertions.assertEquals(response.nomeCompleto(), entity.getNomeCompleto());
            Assertions.assertEquals(response.cpf(), entity.getCpf());
            Assertions.assertEquals(response.dataNascimento(), entity.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            Assertions.assertEquals(response.statusCadastro(), entity.getStatusCadastro());
            Assertions.assertEquals(response.email(), entity.getEmail());

            Assertions.assertNull(entity.getTelefones());
            Assertions.assertTrue(response.telefones().isEmpty());

            Assertions.assertNull(entity.getEnderecos());
            Assertions.assertTrue(response.enderecos().isEmpty());
        }

        @Test
        @DisplayName("dados de telefone")
        void dadoCustomerEntityValidoComDoisTelefones_quandoToCustomerSearchDtoResponse_entaoRetornarDadosCorretosDeTelefones() {
            var entity = factory.gerarCustomerEntityBuilder().build();
            var response = mapperOut.toCustomerSearchDtoResponse(entity);

            Assertions.assertEquals(entity.getTelefones().size(), response.telefones().size());

            for (TelefoneVo foneVo : entity.getTelefones()) {
                TelefoneDto foneDto = response.telefones()
                    .stream()
                    .filter(telDto -> telDto.numero().equals(foneVo.getNumero()) && telDto.tipo().equals(foneVo.getTipo()))
                    .findFirst()
                    .orElse(null);

                Assertions.assertEquals(foneVo.getNumero(), foneDto.numero());
                Assertions.assertEquals(foneVo.getTipo(), foneDto.tipo());
            }
        }
    }

}

