package microservices.micro_customers.adapter.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomerCreateDtoResponse(

        Long customerId,

        String nomeCompleto,

        String cpf,

        String dataNascimento,

        StatusCadastroEnum statusCadastro,

        String email,

        Set<TelefoneDto> telefones,

        Set<EnderecoDto> enderecos,

        LocalDateTime createdAt,

        String createdBy,

        LocalDateTime updatedAt,

        String updatedBy

) { }

