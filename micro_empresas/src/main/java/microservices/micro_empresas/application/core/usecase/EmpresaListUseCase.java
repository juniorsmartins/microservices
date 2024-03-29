package microservices.micro_empresas.application.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_empresas.application.core.domain.Empresa;
import microservices.micro_empresas.application.port.input.EmpresaListInputPort;
import microservices.micro_empresas.application.port.output.EmpresaListOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpresaListUseCase implements EmpresaListInputPort {

    private final EmpresaListOutputPort empresaListOutputPort;

    @Override
    public List<Empresa> list() {

        return this.empresaListOutputPort.list();
    }

}

