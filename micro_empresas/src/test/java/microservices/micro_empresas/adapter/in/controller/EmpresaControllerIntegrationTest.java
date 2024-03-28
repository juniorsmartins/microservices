package microservices.micro_empresas.adapter.in.controller;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import microservices.micro_empresas.adapter.in.controller.dto.response.EmpresaCreateDtoResponse;
import microservices.micro_empresas.adapter.out.repository.EmpresaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import util.AbstractTestcontainersTest;
import util.FactoryObjectMother;
import util.TestConfig;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integration - EmpresaController")
class EmpresaControllerIntegrationTest extends AbstractTestcontainersTest {

    private static final String BASE_PATH = "/api/v1/empresas";

    private static RequestSpecification requestSpecification;

    private static ObjectMapper objectMapper;

    private final FactoryObjectMother factory = FactoryObjectMother.singleton();

    @Autowired
    private EmpresaRepository empresaRepository;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // Usar somente nos testes para manter a segurança da API - Isso é usado quanto temos Hateoas

        requestSpecification = new RequestSpecBuilder()
//            .addHeader(TestConfig.HEADER_PARAM_ORIGIN, null)
            .setBasePath(BASE_PATH)
            .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();
    }

    @AfterEach
    void tearDown() {
        this.empresaRepository.deleteAll();
    }

    @Nested
    @DisplayName("Post Create")
    class PostCreate {

        @Test
        @DisplayName("persistência")
        void dadoEmpresaValida_quandoCreate_entaoRetornarDadosPersistidos() throws IOException {

            var dtoIn = factory.gerarEmpresaCreateDtoRequest().build();

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

            var dtoOut = objectMapper.readValue(response, EmpresaCreateDtoResponse.class);
            var persistido = empresaRepository.findById(dtoOut.getId()).orElseThrow();

            Assertions.assertEquals(dtoOut.getId(), persistido.getId());
            Assertions.assertEquals(dtoOut.getNome(), persistido.getNome());
        }
    }

}

