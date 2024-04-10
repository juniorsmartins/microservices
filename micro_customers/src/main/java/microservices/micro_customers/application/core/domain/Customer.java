package microservices.micro_customers.application.core.domain;

import lombok.*;
import microservices.micro_customers.application.core.constant.Constantes;
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
@Getter
@ToString
@EqualsAndHashCode(of = {"customerId"})
public final class Customer {

    private Long version;

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

    public Customer(Long version, Long id, String nomeCompleto, CadastroPessoaFisica cpf, DataNascimento dataNascimento,
                    StatusCadastroEnum statusCadastro, CorreioEletronico email, Set<Telefone> telefones,
                    Set<Endereco> enderecos, OffsetDateTime createdAt, String createdBy, OffsetDateTime updatedAt,
                    String updatedBy) {
        this.setCustomerId(id);
        this.setNomeCompleto(nomeCompleto);
        this.setCpf(cpf);
        this.setDataNascimento(dataNascimento);
        this.setStatusCadastro(statusCadastro);
        this.setEmail(email);
        this.setTelefones(telefones);
        this.setEnderecos(enderecos);
        this.setCreatedAt(createdAt);
        this.setCreatedBy(createdBy);
        this.setUpdatedAt(updatedAt);
        this.setUpdatedBy(updatedBy);
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setCustomerId(Long id) {
        this.customerId = id;
    }

    public void setNomeCompleto(String nomeCompleto) {
        Optional.ofNullable(nomeCompleto)
            .ifPresentOrElse(nome -> {
                this.attributeValidator(Constantes.NOME_COMPLETO, nome, Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO);
                this.nomeCompleto = nome;
            },
            () -> {throw new NullAttributeNotAllowedException(Constantes.NOME_COMPLETO);}
        );
    }

    public void setCpf(CadastroPessoaFisica cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(DataNascimento data) {
        this.dataNascimento = data;
    }

    public void setStatusCadastro(StatusCadastroEnum status) {
        this.statusCadastro = status;
    }

    public void setEmail(CorreioEletronico email) {
        this.email = email;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    private void attributeValidator(String nomeAtributo, String valorAtributo, int tamanhoMaximo) {
        if (valorAtributo.isBlank()) {
            throw new ProhibitedEmptyOrBlankAttributeException(nomeAtributo);
        }
        if (valorAtributo.length() > tamanhoMaximo) {
            throw new AttributeWithInvalidMaximumSizeException(nomeAtributo, valorAtributo, tamanhoMaximo);
        }
    }

    public Customer addStatusCadastroIniciado() {
        this.statusCadastro = StatusCadastroEnum.INICIADO;
        return this;
    }

    public Customer addStatusCadastroConcluido() {
        this.statusCadastro = StatusCadastroEnum.CONCLUIDO;
        return this;
    }

}

