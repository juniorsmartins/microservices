package microservices.micro_customers.domain.tipos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"telefone"})
public final class Telefone {

    private String telefone;

}

