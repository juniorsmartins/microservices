package microservices.micro_empresas.adapter.in.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.micro_empresas.adapter.in.controller.dto.request.EmpresaCreateDtoRequest;
import microservices.micro_empresas.adapter.in.controller.dto.response.EmpresaCreateDtoResponse;
import microservices.micro_empresas.adapter.mapper.MapperIn;
import microservices.micro_empresas.application.port.input.EmpresaCreateInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = {"/api/v1/empresas"})
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaCreateInputPort empresaCreateInputPort;

    private final MapperIn mapperIn;

    @PostMapping
    public ResponseEntity<EmpresaCreateDtoResponse> create(@RequestBody @Valid EmpresaCreateDtoRequest empresaCreateDtoIn) {

        var response = Optional.ofNullable(empresaCreateDtoIn)
            .map(this.mapperIn::toEmpresa)
            .map(this.empresaCreateInputPort::create)
            .map(this.mapperIn::toEmpresaCreateDtoResponse)
            .orElseThrow();

        return ResponseEntity
            .created(URI.create("/api/v1/empresas" + response.id()))
            .body(response);
    }

}

