package microservices.micro_customers.application.core.domain;

import lombok.*;
import microservices.micro_customers.application.core.constant.Constants;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.application.core.domain.tipos.*;
import microservices.micro_customers.config.exception.http_400.AttributeWithInvalidMaximumSizeException;
import microservices.micro_customers.config.exception.http_400.NullAttributeNotAllowedException;
import microservices.micro_customers.config.exception.http_400.ProhibitedEmptyOrBlankAttributeException;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"customerId"})
public final class Customer {

    private Long customerId;

    private String nomeCompleto;

    private CadastroPessoaFisica cpf;

    private DataNascimento dataNascimento;

    private StatusCadastroEnum statusCadastro;

    private CorreioEletronico email;

    private Set<Telefone> telefones;

    private Set<Endereco> enderecos;

    private OffsetDateTime createdAt;

    private String createdBy;

    private OffsetDateTime updatedAt;

    private String updatedBy;

    public Customer addStatusCadastroIniciado() {
        this.statusCadastro = StatusCadastroEnum.INICIADO;
        return this;
    }

    public Customer addStatusCadastroConcluido() {
        this.statusCadastro = StatusCadastroEnum.CONCLUIDO;
        return this;
    }

    public void setNomeCompleto(String nomeCompleto) {
        Optional.ofNullable(nomeCompleto)
            .ifPresentOrElse(nome -> {
                this.attributeValidator(Constants.NOME_COMPLETO, nome, Constants.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO);
                this.nomeCompleto = nome;
            },
            () -> {throw new NullAttributeNotAllowedException(Constants.NOME_COMPLETO);}
        );
    }

    private void attributeValidator(String nomeAtributo, String valorAtributo, int tamanhoMaximo) {
        if (valorAtributo.isBlank()) {
            throw new ProhibitedEmptyOrBlankAttributeException(nomeAtributo);
        }
        if (valorAtributo.length() > tamanhoMaximo) {
            throw new AttributeWithInvalidMaximumSizeException(nomeAtributo, valorAtributo, tamanhoMaximo);
        }
    }

}

