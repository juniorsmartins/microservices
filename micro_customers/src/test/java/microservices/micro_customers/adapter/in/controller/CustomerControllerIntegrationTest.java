package microservices.micro_customers.adapter.in.controller;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import microservices.micro_customers.adapter.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.adapter.dto.response.CustomerUpdateDtoResponse;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.application.core.constant.Constantes;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import microservices.micro_customers.util.TestConfig;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration - CustomerController")
class CustomerControllerIntegrationTest extends AbstractTestcontainersTest {

    private static final String BASE_PATH = "/api/v1/customers";

    private static RequestSpecification requestSpecification;

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @LocalServerPort // Esta anotação injeta a porta selecionada pelo Spring Boot
    int port;

    @Autowired
    CustomerRepository customerRepository;

    CustomerEntity entity1;

    CustomerEntity entity2;

    @BeforeEach
    void setUp() {
        requestSpecification = new RequestSpecBuilder()
            .addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_BABYSTEPS)
            .setBasePath(BASE_PATH)
            .setPort(port)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        entity1 = factory.gerarCustomerEntityBuilder().build();
        entity2 = factory.gerarCustomerEntityBuilder().statusCadastro(StatusCadastroEnum.CONCLUIDO).build();
        var entity3 = factory.gerarCustomerEntityBuilder().build();

        entity1 = this.customerRepository.save(entity1);
        entity2 = this.customerRepository.save(entity2);
        this.customerRepository.save(entity3);
    }

    @AfterEach
    void tearDown() {
        this.customerRepository.deleteAll();
    }

    @Nested
    @DisplayName("PostCreate")
    class PostCreate {

        @Test
        @DisplayName("dados válidos")
        void dadoCustomerCreateDtoRequestCompletoAndValido_quandoCreate_entaoRetornarDadosPersistidos() {

            var dtoRequest = factory.gerarCustomerCreateDtoRequestBuilder().build();

            var response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("rastreamento-id", UUID.randomUUID())
                    .body(dtoRequest)
                .when()
                    .post()
                .then()
                    .log().all()
                    .statusCode(201)
                .extract()
                    .as(CustomerCreateDtoResponse.class);

            var persistido = customerRepository.findById(response.customerId()).orElseThrow();

            DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Assertions.assertEquals(response.customerId(), persistido.getCustomerId());
            Assertions.assertEquals(response.nomeCompleto(), persistido.getNomeCompleto());
            Assertions.assertEquals(response.cpf(), persistido.getCpf());
            Assertions.assertEquals(response.dataNascimento(), persistido.getDataNascimento().format(FORMATO_DATA));
            Assertions.assertEquals(response.statusCadastro(), persistido.getStatusCadastro());
            Assertions.assertEquals(response.email(), persistido.getEmail());

            Assertions.assertEquals(response.telefones().size(), persistido.getTelefones().size());
            Assertions.assertEquals(response.enderecos().size(), persistido.getEnderecos().size());

            Assertions.assertNotNull(response.createdAt());
            Assertions.assertNotNull(response.createdBy());
        }
    }

    @Nested
    @DisplayName("GetSearch")
    class GetSearch {

        @Test
        @DisplayName("XML")
        void dadoContentTypeComXML_quandoSearch_entaoRetornarHttp200ComTresCustomerSearchDtoResponseEmXML() {

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_XML)
                    .header("rastreamento-id", UUID.randomUUID())
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("totalElements", Matchers.equalTo(3))
                    .body("totalPages", Matchers.equalTo(1))
                    .body("size", Matchers.equalTo(Constantes.PAGE_SIZE))
                    .body("number", Matchers.equalTo(0));
        }

        @Test
        @DisplayName("filtro vazio")
        void dadoCustomerFiltroVazio_quandoSearch_entaoRetornarHttp200ComTresCustomerSearchDtoResponse() {

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("rastreamento-id", UUID.randomUUID())
                .when()
                    .get()
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("totalElements", Matchers.equalTo(3))
                    .body("totalPages", Matchers.equalTo(1))
                    .body("size", Matchers.equalTo(Constantes.PAGE_SIZE))
                    .body("number", Matchers.equalTo(0));
        }

        @Test
        @DisplayName("dois customerIds")
        void dadoCustomerFiltroComDoisCustomerIds_quandoSearch_entaoRetornarDoisCustomerSearchDtoResponse() {

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("rastreamento-id", UUID.randomUUID())
                    .queryParam("customerId", entity1.getCustomerId() + "," + entity2.getCustomerId())
                .when()
                    .get()
                .then()
                    .body("totalElements", Matchers.equalTo(2));

        }

        @Test
        @DisplayName("dois nomeCompleto")
        void dadoCustomerFiltroComDoisNomeCompleto_quandoSearch_entaoRetornarDoisCustomerSearchDtoResponse() {

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("rastreamento-id", UUID.randomUUID())
                    .queryParam("nomeCompleto", entity1.getNomeCompleto() + "," + entity2.getNomeCompleto())
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("totalElements", Matchers.equalTo(2));
        }

        @Test
        @DisplayName("dois cpfs")
        void dadoCustomerFiltroComDoisCpfs_quandoSearch_entaoRetornarDoisCustomerSearchDtoResponse() {

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("rastreamento-id", UUID.randomUUID())
                    .queryParam("cpf", entity1.getCpf() + "," + entity2.getCpf())
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("totalElements", Matchers.equalTo(2));
        }

        @Test
        @DisplayName("statusCadastro")
        void dadoCustomerFiltroComUmStatusCadastro_quandoSearch_entaoRetornarUmCustomerSearchDtoResponse() {

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("rastreamento-id", UUID.randomUUID())
                    .queryParam("statusCadastro", entity2.getStatusCadastro())
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("totalElements", Matchers.equalTo(1));
        }

        @Test
        @DisplayName("dois emails")
        void dadoCustomerFiltroComDoisEmails_quandoSearch_entaoRetornarDoisCustomerSearchDtoResponse() {

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("rastreamento-id", UUID.randomUUID())
                    .queryParam("email", entity1.getEmail() + "," + entity2.getEmail())
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("totalElements", Matchers.equalTo(2))
                    .extract()
                    .body()
                    .asString();
        }
    }

    @Nested
    @DisplayName("Delete")
    class DeleteTest {

        @Test
        @DisplayName("customerId válido")
        void dadoCustomerIdValido_quandoDeleteById_entaoRetornarHttp204AndApagarUmCustomer() {

            var entitiesAntes = customerRepository.findAll();
            Assertions.assertEquals(3, entitiesAntes.size());

            RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("rastreamento-id", UUID.randomUUID())
                .when()
                    .delete("/" + entity1.getCustomerId())
                .then()
                    .log().all()
                    .statusCode(204);

            var entitiesDepois = customerRepository.findAll();
            Assertions.assertEquals(2, entitiesDepois.size());
        }
    }

    @Nested
    @DisplayName("PutUpdate")
    class PutUpdate {

        @Test
        @DisplayName("dados válidos")
        void dadoCustomerUpdateDtoRequestValido_quandoUpdate_entaoRetornarDadosPersistidos() {

            var dtoRequest = factory.gerarCustomerUpdateDtoRequestBuilder()
                .customerId(entity1.getCustomerId())
                .build();

            var response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .header("rastreamento-id", UUID.randomUUID())
                    .body(dtoRequest)
                .when()
                    .put()
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .as(CustomerUpdateDtoResponse.class);

            var persistido = customerRepository.findById(response.customerId()).orElseThrow();

            DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Assertions.assertEquals(response.customerId(), persistido.getCustomerId());
            Assertions.assertEquals(response.nomeCompleto(), persistido.getNomeCompleto());

            Assertions.assertNotEquals(dtoRequest.cpf(), persistido.getCpf());
            Assertions.assertEquals(response.cpf(), persistido.getCpf());

            Assertions.assertEquals(response.dataNascimento(), persistido.getDataNascimento().format(FORMATO_DATA));
            Assertions.assertEquals(response.statusCadastro(), persistido.getStatusCadastro());
            Assertions.assertEquals(response.email(), persistido.getEmail());

            Assertions.assertEquals(response.telefones().size(), persistido.getTelefones().size());
            Assertions.assertEquals(response.enderecos().size(), persistido.getEnderecos().size());

            Assertions.assertNotNull(response.createdAt());
            Assertions.assertNotNull(response.createdBy());
//            Assertions.assertNotNull(response.updatedAt());
//            Assertions.assertNotNull(response.updatedBy());
        }
    }

}

