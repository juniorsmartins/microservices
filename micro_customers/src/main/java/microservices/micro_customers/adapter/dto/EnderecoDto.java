package microservices.micro_customers.adapter.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import microservices.micro_customers.application.core.constant.Constantes;

@Builder
public record EnderecoDto(

    @Size(min = Constantes.MIN_CARACTERES_CUSTOMER_ENDERECO_CEP, max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CEP)
    String cep,

//    @Size(min = Constantes.MIN_CARACTERES_CUSTOMER_ENDERECO_ESTADO, max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_ESTADO)
    String estado,

//    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CIDADE)
    String cidade,

//    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_BAIRRO)
    String bairro,

//    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_LOGRADOURO)
    String logradouro,

//    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_NUMERO)
    String numero,

//    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_COMPLEMENTO)
    String complemento

) { }

