package microservices.micro_customers.adapter.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.application.core.constant.Constantes;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Builder
public record CustomerUpdateDtoRequest(

    @NotNull
    @Positive
    Long customerId,

    @NotBlank
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO)
    String nomeCompleto,

    @NotBlank
    @CPF
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_CPF)
    String cpf,

    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
    String dataNascimento,

    @NotBlank
    @Email
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_EMAIL)
    String email,

    @Valid
    Set<TelefoneDto> telefones,

    @Valid
    Set<EnderecoDto> enderecos

) { }
