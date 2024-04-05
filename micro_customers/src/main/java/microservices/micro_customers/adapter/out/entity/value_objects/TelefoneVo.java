package microservices.micro_customers.adapter.out.entity.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import microservices.micro_customers.application.core.constant.Constants;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
@EqualsAndHashCode(of = {"numero"})
public final class TelefoneVo implements Serializable {

    @Column(name = "numero", length = Constants.MAX_CARACTERES_CUSTOMER_TELEFONE_NUMERO)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = Constants.MAX_CARACTERES_CUSTOMER_TELEFONE_TIPO)
    private TipoTelefoneEnum tipo;

}

