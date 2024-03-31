package microservices.micro_empresas.adapter.in.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmpresaListDtoResponse(

    Long empresaId,

    String nome,

    OffsetDateTime createdAt,

    String createdBy,

    OffsetDateTime updatedAt,

    String updatedBy

) { }

