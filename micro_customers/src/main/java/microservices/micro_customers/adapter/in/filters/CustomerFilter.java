package microservices.micro_customers.adapter.in.filters;

import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;

public record CustomerFilter(

    String customerId,

    String nomeCompleto,

    String cpf,

    StatusCadastroEnum statusCadastro,

    String email

) { }

