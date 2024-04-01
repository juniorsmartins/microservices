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

    private CadastroPessoaFisica cpf; // 1

    private DataNascimento dataNascimento; // 1

    private StatusCadastroEnum statusCadastro;

    private CorreioEletronico email; // 1

    private Set<Telefone> telefones; // N

    private Endereco endereco; // 1

}

