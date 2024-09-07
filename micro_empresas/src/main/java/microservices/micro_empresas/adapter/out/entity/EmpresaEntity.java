package microservices.micro_empresas.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "empresas", indexes = {@Index(name = "idx_empresas_nome", columnList = "nome")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"empresaId"}, callSuper = false)
public final class EmpresaEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Long empresaId;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @PrePersist
    private void prePersist() {
        this.setCreatedAt(OffsetDateTime.now());
        this.setCreatedBy("anônimo");
    }

    @PreUpdate
    private void preUpdate() {
        this.setUpdatedAt(OffsetDateTime.now());
        this.setUpdatedBy("anônimo");
    }

}

