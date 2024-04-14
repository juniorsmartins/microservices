package microservices.micro_customers.adapter.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "CustomerSearchDtoResponse", description = "Transportador de dados para resposta ao usuário.")
public record CustomerSearchDtoResponse(

        @Schema(name = "customerId", description = "Chave de identificação no banco de dados.", example = "22")
        Long customerId,

        @Schema(name = "nomeCompleto", description = "Nome e sobrenome.", example = "Robert Martin")
        String nomeCompleto,

        @Schema(name = "cpf", description = "Número do Cadastro de Pessoa Física.", example = "22076325042")
        String cpf,

        @Schema(name = "dataNascimento", description = "Data oficial do nascimento.", example = "22/10/2024")
        String dataNascimento,

        @Schema(name = "statusCadastro", description = "Fase do processo de cadastramento.", example = "INICIADO")
        StatusCadastroEnum statusCadastro,

        @Schema(name = "email", description = "Endereço de Correio Eletrônico.", example = "teste@teste.com")
        String email,

        @Schema(name = "telefones", description = "Coleção de números telefônicos.")
        Set<TelefoneDto> telefones,

        @Schema(name = "endereços", description = "Coleção de endereços.")
        Set<EnderecoDto> enderecos,

        @Schema(name = "createdAt", description = "Data e horário da operação de cadastrar.")
        LocalDateTime createdAt,

        @Schema(name = "createdBy", description = "Identificador do titular da operação de cadastrar.")
        String createdBy,

        @Schema(name = "updatedAt", description = "Data e horário da operação de atualizar.")
        LocalDateTime updatedAt,

        @Schema(name = "updatedBy", description = "Identificador do titular da operação de atualizar.")
        String updatedBy

) { }

