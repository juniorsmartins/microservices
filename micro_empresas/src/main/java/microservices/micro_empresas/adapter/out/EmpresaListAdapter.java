package microservices.micro_empresas.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_empresas.adapter.mapper.MapperOut;
import microservices.micro_empresas.adapter.out.repository.EmpresaRepository;
import microservices.micro_empresas.application.core.domain.Empresa;
import microservices.micro_empresas.application.port.output.EmpresaListOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaListAdapter implements EmpresaListOutputPort {

    private final EmpresaRepository empresaRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public List<Empresa> list() {

        return this.empresaRepository.findAll()
            .stream()
            .map(this.mapperOut::toEmpresa)
            .toList();
    }

}

