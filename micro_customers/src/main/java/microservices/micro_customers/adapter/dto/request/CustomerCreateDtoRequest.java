package microservices.micro_customers.adapter.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.application.core.constant.Constantes;

import java.util.Set;

@Builder
public record CustomerCreateDtoRequest(

    @NotBlank
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO)
    String nomeCompleto,

    String cpf,

    String dataNascimento,

    String email,

    Set<TelefoneDto> telefones,

    Set<EnderecoDto> enderecos

) { }

