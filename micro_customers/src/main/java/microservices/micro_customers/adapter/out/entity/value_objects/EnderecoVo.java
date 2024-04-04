package microservices.micro_customers.adapter.out.entity.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
@EqualsAndHashCode(of = {"cep", "estado", "cidade", "bairro", "logradouro", "numero", "complemento"})
public final class EnderecoVo implements Serializable {

    @Column(name = "cep")
    private String cep;

    @Column(name = "estado", length = 2)
    private String estado;

    @Column(name = "cidade", length = 150)
    private String cidade;

    @Column(name = "bairro", length = 150)
    private String bairro;

    @Column(name = "logradouro", length = 150)
    private String logradouro;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "complemento", length = 250)
    private String complemento;

}

