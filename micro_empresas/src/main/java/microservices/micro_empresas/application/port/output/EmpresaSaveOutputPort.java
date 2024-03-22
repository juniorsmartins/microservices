package microservices.micro_empresas.application.port.output;

import microservices.micro_empresas.application.core.domain.Empresa;

public interface EmpresaSaveOutputPort {

    Empresa save(Empresa empresa);

}

