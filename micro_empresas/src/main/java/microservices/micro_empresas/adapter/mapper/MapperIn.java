package microservices.micro_empresas.adapter.mapper;

import microservices.micro_empresas.adapter.in.controller.dto.request.EmpresaCreateDtoRequest;
import microservices.micro_empresas.adapter.in.controller.dto.response.EmpresaCreateDtoResponse;
import microservices.micro_empresas.adapter.in.controller.dto.response.EmpresaListDtoResponse;
import microservices.micro_empresas.application.core.domain.Empresa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperIn {

    Empresa toEmpresa(EmpresaCreateDtoRequest empresaCreateDtoRequest);

    EmpresaCreateDtoResponse toEmpresaCreateDtoResponse(Empresa empresa);

    EmpresaListDtoResponse toEmpresaListDtoResponse(Empresa empresa);

}


