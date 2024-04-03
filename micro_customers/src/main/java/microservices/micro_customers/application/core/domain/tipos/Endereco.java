package microservices.micro_customers.application.core.domain.tipos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"cep", "estado", "cidade", "bairro", "logradouro", "numero", "complemento"})
public final class Endereco {

    private String cep;

    private String estado;

    private String cidade;

    private String bairro;

    private String logradouro;

    private String numero;

    private String complemento;

}

