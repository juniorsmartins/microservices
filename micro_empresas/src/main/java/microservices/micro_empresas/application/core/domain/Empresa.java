package microservices.micro_empresas.application.core.domain;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class Empresa {

    private Long id;

    private String nome;

}

