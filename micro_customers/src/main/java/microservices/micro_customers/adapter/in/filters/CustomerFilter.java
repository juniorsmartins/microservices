package microservices.micro_customers.adapter.in.filters;

import lombok.Builder;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;

@Builder
public record CustomerFilter(

    String customerId,

    String nomeCompleto,

    String cpf,

    StatusCadastroEnum statusCadastro,

    String email

) { }

