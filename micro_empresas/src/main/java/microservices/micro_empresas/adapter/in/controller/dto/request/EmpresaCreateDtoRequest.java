package microservices.micro_empresas.adapter.in.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaCreateDtoRequest(

    @NotBlank
    @Size(max = 200)
    String nome

) { }

