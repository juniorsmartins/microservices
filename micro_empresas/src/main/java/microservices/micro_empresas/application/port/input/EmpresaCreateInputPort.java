package microservices.micro_empresas.application.port.input;

import microservices.micro_empresas.application.core.domain.Empresa;

public interface EmpresaCreateInputPort {

    /**
     *
     * @param empresa
     * @return
     */
    Empresa create(Empresa empresa);

}

