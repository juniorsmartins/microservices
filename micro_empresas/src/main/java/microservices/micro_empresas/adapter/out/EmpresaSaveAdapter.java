package microservices.micro_empresas.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_empresas.adapter.mapper.MapperOut;
import microservices.micro_empresas.adapter.out.repository.EmpresaRepository;
import microservices.micro_empresas.application.core.domain.Empresa;
import microservices.micro_empresas.application.port.output.EmpresaSaveOutputPort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaSaveAdapter implements EmpresaSaveOutputPort {

    private final EmpresaRepository empresaRepository;

    private final MapperOut mapperOut;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Modifying
    @Override
    public Empresa save(Empresa empresa) {

        return Optional.ofNullable(empresa)
            .map(this.mapperOut::toEmpresaEntity)
            .map(this.empresaRepository::save)
            .map(this.mapperOut::toEmpresa)
            .orElseThrow();
    }

}

