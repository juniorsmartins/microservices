package microservices.micro_customers.domain.tipos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"email"})
public final class CorreioEletronico {

    private String email;

}

