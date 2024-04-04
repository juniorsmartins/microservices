package microservices.micro_customers.adapter.in.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import microservices.micro_customers.adapter.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.adapter.out.repository.CustomerRepository;
import microservices.micro_customers.util.AbstractTestcontainersTest;
import microservices.micro_customers.util.FactoryObjectMother;
import microservices.micro_customers.util.TestConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration -CustomerController")
class CustomerControllerIntegrationTest extends AbstractTestcontainersTest {

    private static final String BASE_PATH = "/api/v1/customers";

    private static RequestSpecification requestSpecification;

    private static ObjectMapper objectMapper;

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @LocalServerPort // Esta anotação injeta a porta selecionada pelo Spring Boot
    int port;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper(); // Precisei mudar o import do ObjectMapper
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Usar somente nos testes para manter a segurança da API - Isso é usado quanto temos Hateoas
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());

        requestSpecification = new RequestSpecBuilder()
            .addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_BABYSTEPS)
            .setBasePath(BASE_PATH)
            .setPort(port)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();
    }

    @AfterEach
    void tearDown() {
        this.customerRepository.deleteAll();
    }

    @Nested
    @DisplayName("Post")
    class PostCreate {

        @Test
        @DisplayName("dados válidos")
        void dadoCustomerCompletoValido_quandoCreate_entaoRetornarDadosPersistidos() throws IOException {

            var dtoIn = factory.gerarCustomerCreateDtoRequestBuilder().build();

            var response = RestAssured
                .given().spec(requestSpecification)
                    .contentType(TestConfig.CONTENT_TYPE_JSON)
                    .body(dtoIn)
                .when()
                    .post()
                .then()
                    .log().all()
                    .statusCode(201)
                .extract()
                    .body()
                        .asString();

            var dtoOut = objectMapper.readValue(response, CustomerCreateDtoResponse.class);
            var persistido = customerRepository.findById(dtoOut.customerId()).orElseThrow();

            DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Assertions.assertEquals(dtoOut.customerId(), persistido.getCustomerId());
            Assertions.assertEquals(dtoOut.nomeCompleto(), persistido.getNomeCompleto());
            Assertions.assertEquals(dtoOut.cpf(), persistido.getCpf());
            Assertions.assertEquals(dtoOut.dataNascimento(), persistido.getDataNascimento().format(FORMATO_DATA));
            Assertions.assertEquals(dtoOut.statusCadastro(), persistido.getStatusCadastro());
            Assertions.assertEquals(dtoOut.email(), persistido.getEmail());

            Assertions.assertEquals(dtoOut.telefones().size(), persistido.getTelefones().size());
            Assertions.assertEquals(dtoOut.enderecos().size(), persistido.getEnderecos().size());

            Assertions.assertNotNull(dtoOut.createdAt());
            Assertions.assertNotNull(dtoOut.createdBy());
        }
    }

}

