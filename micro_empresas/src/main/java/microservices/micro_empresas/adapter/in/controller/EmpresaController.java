package microservices.micro_empresas.adapter.in.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_empresas.adapter.in.controller.dto.request.EmpresaCreateDtoRequest;
import microservices.micro_empresas.adapter.in.controller.dto.response.EmpresaCreateDtoResponse;
import microservices.micro_empresas.adapter.in.controller.dto.response.EmpresaListDtoResponse;
import microservices.micro_empresas.adapter.mapper.MapperIn;
import microservices.micro_empresas.application.port.input.EmpresaCreateInputPort;
import microservices.micro_empresas.application.port.input.EmpresaDeleteInputPort;
import microservices.micro_empresas.application.port.input.EmpresaListInputPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaCreateInputPort empresaCreateInputPort;

    private final EmpresaDeleteInputPort empresaDeleteInputPort;

    private final EmpresaListInputPort empresaListInputPort;

    private final MapperIn mapperIn;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmpresaCreateDtoResponse> create(@RequestBody @Valid EmpresaCreateDtoRequest empresaCreateDtoIn) {

        var response = Optional.ofNullable(empresaCreateDtoIn)
            .map(this.mapperIn::toEmpresa)
            .map(this.empresaCreateInputPort::create)
            .map(this.mapperIn::toEmpresaCreateDtoResponse)
            .orElseThrow();

        return ResponseEntity
            .created(URI.create("/api/v1/empresas" + response.getEmpresaId()))
            .body(response);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable(name = "id") final Long empresaId) {

        Optional.ofNullable(empresaId)
            .ifPresentOrElse(this.empresaDeleteInputPort::delete,
                () -> {throw new NoSuchElementException();}
            );

        return ResponseEntity
            .noContent()
            .build();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<EmpresaListDtoResponse>> list() {

        var response = this.empresaListInputPort.list()
            .stream()
            .map(this.mapperIn::toEmpresaListDtoResponse)
            .toList();

        return ResponseEntity
            .ok()
            .body(response);
    }

}

