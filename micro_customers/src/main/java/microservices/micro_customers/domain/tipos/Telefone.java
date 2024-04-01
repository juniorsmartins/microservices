package microservices.micro_customers.domain.tipos;

import lombok.*;
import microservices.micro_customers.domain.enums.TipoTelefoneEnum;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"telefone"})
public final class Telefone {

    private String telefone;

    private TipoTelefoneEnum tipo;

}

