package microservices.micro_empresas.application.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_empresas.application.port.input.EmpresaDeleteInputPort;
import microservices.micro_empresas.application.port.output.EmpresaDeleteOutputPort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpresaDeleteUseCase implements EmpresaDeleteInputPort {

    private final EmpresaDeleteOutputPort empresaDeleteOutputPort;

    @Override
    public void delete(final Long id) {

        Optional.ofNullable(id)
            .ifPresentOrElse(this.empresaDeleteOutputPort::delete,
                () -> {throw new NoSuchElementException();}
            );
    }
}

