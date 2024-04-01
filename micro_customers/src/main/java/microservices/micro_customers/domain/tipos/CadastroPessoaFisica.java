package microservices.micro_customers.domain.tipos;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"cpf"})
public class CadastroPessoaFisica implements Serializable {

    private String cpf;

}

