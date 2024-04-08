package microservices.micro_customers.adapter.out.entity.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import microservices.micro_customers.application.core.constant.Constantes;

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

    @Column(name = "cep", length = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CEP)
    private String cep;

    @Column(name = "estado", length = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_ESTADO)
    private String estado;

    @Column(name = "cidade", length = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CIDADE)
    private String cidade;

    @Column(name = "bairro", length = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_BAIRRO)
    private String bairro;

    @Column(name = "logradouro", length = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_LOGRADOURO)
    private String logradouro;

    @Column(name = "numero", length = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_NUMERO)
    private String numero;

    @Column(name = "complemento", length = Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_COMPLEMENTO)
    private String complemento;

}

