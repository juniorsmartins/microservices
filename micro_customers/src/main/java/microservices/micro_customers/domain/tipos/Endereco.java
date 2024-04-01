package microservices.micro_customers.domain.tipos;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"cep", "estado", "cidade", "bairro", "logradouro", "numero"})
public final class Endereco {

    private String cep;

    private String estado;

    private String cidade;

    private String bairro;

    private String logradouro;

    private String numero;

    private String complemento;

}

