package microservices.micro_customers.domain;

import lombok.*;
import microservices.micro_customers.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.domain.tipos.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    private Endereco endereco;

}

