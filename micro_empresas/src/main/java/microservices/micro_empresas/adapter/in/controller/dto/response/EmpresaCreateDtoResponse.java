package microservices.micro_empresas.adapter.in.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class EmpresaCreateDtoResponse {

    private Long id;

    private String nome;

}

