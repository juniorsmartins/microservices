package microservices.micro_customers.entity.value_objects;

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
@EqualsAndHashCode(of = {"telefone"})
public final class TelefoneVo implements Serializable {

    @Column(name = "telefone")
    private String telefone;

}

