package microservices.micro_empresas.adapter.mapper;

import microservices.micro_empresas.adapter.out.entity.EmpresaEntity;
import microservices.micro_empresas.application.core.domain.Empresa;

public interface MapperOut {

    EmpresaEntity toEmpresaEntity(Empresa empresa);

    Empresa toEmpresa(EmpresaEntity empresaEntity);

}

