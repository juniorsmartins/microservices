package microservices.micro_empresas.adapter.mapper;

import microservices.micro_empresas.adapter.out.entity.EmpresaEntity;
import microservices.micro_empresas.application.core.domain.Empresa;
import org.springframework.stereotype.Service;

@Service
public final class MapperOutImpl implements MapperOut {

    @Override
    public EmpresaEntity toEmpresaEntity(final Empresa empresa) {
        if (empresa == null) {
            return null;
        }

        return EmpresaEntity.builder()
            .empresaId(empresa.getEmpresaId())
            .nome(empresa.getNome())
            .build();
    }

    @Override
    public Empresa toEmpresa(final EmpresaEntity empresaEntity) {
        if (empresaEntity == null) {
            return null;
        }

        return Empresa.builder()
            .empresaId(empresaEntity.getEmpresaId())
            .nome(empresaEntity.getNome())
            .createdAt(empresaEntity.getCreatedAt())
            .createdBy(empresaEntity.getCreatedBy())
            .updatedAt(empresaEntity.getUpdatedAt())
            .updatedBy(empresaEntity.getUpdatedBy())
            .build();
    }

}

