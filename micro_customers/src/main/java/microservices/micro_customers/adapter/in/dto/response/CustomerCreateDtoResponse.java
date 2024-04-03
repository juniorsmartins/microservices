package microservices.micro_customers.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import microservices.micro_customers.adapter.in.dto.EnderecoDto;
import microservices.micro_customers.adapter.in.dto.TelefoneDto;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;

import java.time.OffsetDateTime;
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

        EnderecoDto endereco,

        OffsetDateTime createdAt,

        String createdBy,

        OffsetDateTime updatedAt,

        String updatedBy

) { }

