package microservices.micro_empresas.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_empresas.adapter.out.repository.EmpresaRepository;
import microservices.micro_empresas.application.port.output.EmpresaDeleteOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmpresaDeleteAdapter implements EmpresaDeleteOutputPort {

    private final EmpresaRepository empresaRepository;

    @Transactional
    @Override
    public void delete(final Long id) {

        this.empresaRepository.findById(id)
            .ifPresentOrElse(this.empresaRepository::delete,
                () -> {throw new NoSuchElementException();}
            );
    }

}

