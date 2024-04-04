package microservices.micro_customers.adapter.dto.request;

import lombok.Builder;
import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;

import java.util.Set;

@Builder
public record CustomerCreateDtoRequest(

    String nomeCompleto,

    String cpf,

    String dataNascimento,

    String email,

    Set<TelefoneDto> telefones,

    Set<EnderecoDto> enderecos

) { }

