package microservices.micro_customers.adapter.dto;

import lombok.Builder;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;

@Builder
public record TelefoneDto(

    String numero,

    TipoTelefoneEnum tipo

) { }

