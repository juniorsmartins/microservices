package microservices.micro_customers.adapter.in.dto;

import lombok.Builder;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;

@Builder
public record TelefoneDto(

    String telefone,

    TipoTelefoneEnum tipo

) { }

