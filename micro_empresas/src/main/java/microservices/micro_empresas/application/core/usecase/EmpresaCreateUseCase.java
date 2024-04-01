package microservices.micro_empresas.application.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_empresas.application.core.domain.Empresa;
import microservices.micro_empresas.application.port.input.EmpresaCreateInputPort;
import microservices.micro_empresas.application.port.output.EmpresaSaveOutputPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpresaCreateUseCase implements EmpresaCreateInputPort {

    private final EmpresaSaveOutputPort empresaSaveOutputPort;

    /**
     *
     * @param empresa
     * @return
     */
    @Override
    public Empresa create(Empresa empresa) {

        return Optional.ofNullable(empresa)
            .map(this.empresaSaveOutputPort::save)
            .orElseThrow();
    }

}

