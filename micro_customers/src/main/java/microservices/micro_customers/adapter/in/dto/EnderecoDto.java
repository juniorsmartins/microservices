package microservices.micro_customers.adapter.in.dto;

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

