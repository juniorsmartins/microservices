package microservices.micro_empresas.adapter.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.AbstractTestcontainersTest;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - MapperIn")
class MapperInUnitTest extends AbstractTestcontainersTest {

    @Autowired
    private MapperIn mapperIn;

    @Test
    @DisplayName("toEmpresa null")
    void dadoEmpresaCreateDtoRequestNula_quandoConverterToEmpresa_entaoRetornarNulo() {
        var response = this.mapperIn.toEmpresa(null);
        Assertions.assertNull(response);
    }

    @Test
    @DisplayName("ToEmpresaCreateDtoResponse null")
    void dadoEmpresaNula_quandoConverterToEmpresaCreateDtoResponse_entaoRetornarNulo() {
        var response = this.mapperIn.toEmpresaCreateDtoResponse(null);
        Assertions.assertNull(response);
    }

    @Test
    @DisplayName("ToEmpresaListDtoResponse null")
    void dadoEmpresaNula_quandoConverterToEmpresaListDtoResponse_entaoRetornarNulo() {
        var response = this.mapperIn.toEmpresaListDtoResponse(null);
        Assertions.assertNull(response);
    }
}

