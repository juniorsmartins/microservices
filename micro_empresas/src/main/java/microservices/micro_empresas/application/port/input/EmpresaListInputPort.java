package microservices.micro_empresas.application.port.input;

import microservices.micro_empresas.application.core.domain.Empresa;

import java.util.List;

public interface EmpresaListInputPort {

    List<Empresa> list();

}

