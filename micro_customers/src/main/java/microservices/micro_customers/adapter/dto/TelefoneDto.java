package microservices.micro_customers.adapter.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import microservices.micro_customers.application.core.constant.Constantes;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;

@Builder
public record TelefoneDto(

    @Size(min = Constantes.MIN_CARACTERES_CUSTOMER_TELEFONE_NUMERO, max = Constantes.MAX_CARACTERES_CUSTOMER_TELEFONE_NUMERO)
    String numero,

    TipoTelefoneEnum tipo

) { }

