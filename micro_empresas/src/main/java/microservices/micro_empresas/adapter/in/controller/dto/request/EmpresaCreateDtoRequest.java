package microservices.micro_empresas.adapter.in.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record EmpresaCreateDtoRequest(

    @NotBlank
    @Size(max = 200)
    String nome

) { }

