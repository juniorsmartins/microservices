package microservices.micro_customers.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;
import microservices.micro_customers.adapter.out.entity.value_objects.EnderecoVo;
import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;
import microservices.micro_customers.application.core.constant.Constantes;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "customers", indexes = {
    @Index(name = "idx_customers_nome_completo", columnList = "nome_completo"),
    @Index(name = "idx_customers_cpf", columnList = "cpf"),
    @Index(name = "idx_customers_email", columnList = "email")
})
@SecondaryTable(name = "customer_metadados", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customer_id"),
    foreignKey = @ForeignKey(name = "fk_customer_metadados"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"customerId"})
@SoftDelete(strategy = SoftDeleteType.DELETED)
public final class CustomerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "nome_completo", nullable = false, length = Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO)
    private String nomeCompleto;

    @Column(name = "cpf", nullable = false, updatable = false, length = Constantes.MAX_CARACTERES_CUSTOMER_CPF)
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_cadastro", nullable = false)
    private StatusCadastroEnum statusCadastro;

    @Column(name = "email", nullable = false, length = Constantes.MAX_CARACTERES_CUSTOMER_EMAIL)
    private String email;


    // ----- Element Collection Telefones ----- //
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_telefones",
        joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "telefones")
    private Set<TelefoneVo> telefones;


    // ----- Element Collection Endereços ----- //
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_enderecos",
        joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "enderecos")
    private Set<EnderecoVo> enderecos;


    // ----- Secondary Table de Metadados para Auditoria ----- //
    @Column(name = "created_at", nullable = false, insertable = true, updatable = false, table = "customer_metadados")
    private OffsetDateTime createdAt;

    @Column(name = "created_by", nullable = false, insertable = true, updatable = false, table = "customer_metadados")
    private String createdBy;

    @Column(name = "updated_at", nullable = true, insertable = false, updatable = true, table = "customer_metadados")
    private OffsetDateTime updatedAt;

    @Column(name = "updated_by", nullable = true, insertable = false, updatable = true, table = "customer_metadados")
    private String updatedBy;

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

