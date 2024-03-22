package microservices.micro_empresas.application.port.input;

import microservices.micro_empresas.application.core.domain.Empresa;

public interface EmpresaCreateInputPort {

    Empresa create(Empresa empresa);

}

