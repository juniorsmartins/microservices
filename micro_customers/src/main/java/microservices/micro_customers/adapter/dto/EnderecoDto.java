package microservices.micro_customers.adapter.dto;

import lombok.Builder;

@Builder
public record EnderecoDto(

    String cep,

    String estado,

    String cidade,

    String bairro,

    String logradouro,

    String numero,

    String complemento

) { }

