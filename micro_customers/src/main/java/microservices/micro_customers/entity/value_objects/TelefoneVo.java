package microservices.micro_customers.entity.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import microservices.micro_customers.domain.enums.TipoTelefoneEnum;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
@EqualsAndHashCode(of = {"telefone"})
public final class TelefoneVo implements Serializable {

    @Column(name = "telefone")
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoTelefoneEnum tipo;

}

