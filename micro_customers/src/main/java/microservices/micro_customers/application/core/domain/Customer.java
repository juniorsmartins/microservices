package microservices.micro_customers.application.core.domain;

import lombok.*;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.application.core.domain.tipos.*;

import java.time.OffsetDateTime;
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

}

