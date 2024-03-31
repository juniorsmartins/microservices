package microservices.micro_empresas.adapter.mapper;

import microservices.micro_empresas.adapter.in.controller.dto.request.EmpresaCreateDtoRequest;
import microservices.micro_empresas.adapter.in.controller.dto.response.EmpresaCreateDtoResponse;
import microservices.micro_empresas.adapter.in.controller.dto.response.EmpresaListDtoResponse;
import microservices.micro_empresas.application.core.domain.Empresa;
import org.springframework.stereotype.Service;

@Service
public final class MapperInImpl implements MapperIn {

    @Override
    public Empresa toEmpresa(final EmpresaCreateDtoRequest empresaCreateDtoRequest) {
        if (empresaCreateDtoRequest == null) {
            return null;
        }

        return Empresa.builder()
            .nome(empresaCreateDtoRequest.nome())
            .build();
    }

    @Override
    public EmpresaCreateDtoResponse toEmpresaCreateDtoResponse(final Empresa empresa) {
        if (empresa == null) {
            return null;
        }

        return EmpresaCreateDtoResponse.builder()
            .empresaId(empresa.getEmpresaId())
            .nome(empresa.getNome())
            .createdAt(empresa.getCreatedAt())
            .createdBy(empresa.getCreatedBy())
            .updatedAt(empresa.getUpdatedAt())
            .updatedBy(empresa.getUpdatedBy())
            .build();
    }

    @Override
    public EmpresaListDtoResponse toEmpresaListDtoResponse(Empresa empresa) {
        if (empresa == null) {
            return null;
        }

        return EmpresaListDtoResponse.builder()
            .empresaId(empresa.getEmpresaId())
            .nome(empresa.getNome())
            .createdAt(empresa.getCreatedAt())
            .createdBy(empresa.getCreatedBy())
            .updatedAt(empresa.getUpdatedAt())
            .updatedBy(empresa.getUpdatedBy())
            .build();
    }

}

