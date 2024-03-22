package microservices.micro_empresas.adapter.mapper;

import microservices.micro_empresas.adapter.out.repository.entity.EmpresaEntity;
import microservices.micro_empresas.application.core.domain.Empresa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    EmpresaEntity toEmpresaEntity(Empresa empresa);

    Empresa toEmpresa(EmpresaEntity empresaEntity);

}

