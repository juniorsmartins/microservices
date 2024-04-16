package microservices.micro_customers.adapter.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import microservices.micro_customers.application.core.constant.Constantes;

@Builder
@Schema(name = "EnderecoDto", description = "Transportador de dados de Endereço.")
public record EnderecoDto(

    @Schema(name = "cep", description = "Código de Endereçamento Postal.", example = "2222")
    @Size(min = Constantes.MIN_CARACTERES_CUSTOMER_ENDERECO_CEP, max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CEP)
    String cep,

    @Schema(name = "estado", description = "Unidade Federativa.", example = "RS")
    @Size(min = Constantes.MIN_CARACTERES_CUSTOMER_ENDERECO_ESTADO, max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_ESTADO)
    String estado,

    @Schema(name = "cidade", description = "Área geográfica destinada à moradia e/ou outras atividades.", example = "Porto Alegre")
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CIDADE)
    String cidade,

    @Schema(name = "bairro", description = "Uma das partes em que se divide uma cidade.", example = "Centro")
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_BAIRRO)
    String bairro,

    @Schema(name = "logradouro", description = "Nome da avenida ou rua ou espaço público usado para serventia.", example = "Rua João Alfredo.")
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_LOGRADOURO)
    String logradouro,

    @Schema(name = "número", description = "Indicação da posição da edificação no logradouro.", example = "2222")
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_NUMERO)
    String numero,

    @Schema(name = "complemento", description = "Descrição de ponto de referência de localização ou informações pertinentes.", example = "Em frente à praça Dom Feliciano.")
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_COMPLEMENTO)
    String complemento

) { }

