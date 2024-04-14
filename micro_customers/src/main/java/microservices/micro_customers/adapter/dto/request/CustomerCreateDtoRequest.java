package microservices.micro_customers.adapter.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.application.core.constant.Constantes;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Builder
@Schema(name = "CustomerCreateDtoRequest", description = "Transportador de dados em requisições do usuário.")
public record CustomerCreateDtoRequest(

    @Schema(name = "nomeCompleto", description = "Nome e sobrenome.", example = "Robert Martin")
    @NotBlank
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO)
    String nomeCompleto,

    @Schema(name = "cpf", description = "Número do Cadastro de Pessoa Física.", example = "22076325042")
    @NotBlank
    @CPF
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_CPF)
    String cpf,

    @Schema(name = "dataNascimento", description = "Data oficial do nascimento.", example = "22/10/2024")
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}")
    String dataNascimento,

    @Schema(name = "email", description = "Endereço de Correio Eletrônico.", example = "teste@teste.com")
    @NotBlank
    @Email
    @Size(max = Constantes.MAX_CARACTERES_CUSTOMER_EMAIL)
    String email,

    @Schema(name = "telefones", description = "Coleção de números telefônicos.")
    @Valid
    Set<TelefoneDto> telefones,

    @Schema(name = "endereços", description = "Coleção de endereços.")
    @Valid
    Set<EnderecoDto> enderecos

) { }

