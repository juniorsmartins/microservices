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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unit - MapperOut")
class MapperOutUnitTest extends AbstractTestcontainersTest {

    @Autowired
    private MapperOut mapperOut;

    @Test
    @DisplayName("ToEmpresaEntity null")
    void dadoEmpresaNula_quandoConverterToEmpresaEntity_entaoRetornarNulo() {
        var response = this.mapperOut.toEmpresaEntity(null);
        Assertions.assertNull(response);
    }

    @Test
    @DisplayName("ToEmpresa null")
    void dadoEmpresaEntityNula_quandoConverterToEmpresa_entaoRetornarNulo() {
        var response = this.mapperOut.toEmpresa(null);
        Assertions.assertNull(response);
    }

}

