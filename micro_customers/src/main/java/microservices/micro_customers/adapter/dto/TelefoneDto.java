package microservices.micro_customers.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import microservices.micro_customers.application.core.constant.Constantes;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;

@Builder
@Schema(name = "TelefoneDto", description = "Transportador de dados de Telefone.", example = "2222")
public record TelefoneDto(

    @Schema(name = "número", description = "Sequência de números decimais para identificar um ponto na rede.", example = "11999885522")
    @Size(min = Constantes.MIN_CARACTERES_CUSTOMER_TELEFONE_NUMERO, max = Constantes.MAX_CARACTERES_CUSTOMER_TELEFONE_NUMERO)
    String numero,

    @Schema(name = "tipo", description = "Classificação do tipo de número telefônico.", example = "CELULAR")
    TipoTelefoneEnum tipo

) { }

