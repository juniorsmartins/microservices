package microservices.micro_empresas.application.port.output;

import microservices.micro_empresas.application.core.domain.Empresa;

import java.util.List;

public interface EmpresaListOutputPort {

    List<Empresa> list();

}

