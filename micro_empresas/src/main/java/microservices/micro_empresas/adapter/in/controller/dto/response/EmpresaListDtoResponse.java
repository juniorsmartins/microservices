package microservices.micro_empresas.adapter.in.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmpresaListDtoResponse(Long id, String nome) { }

