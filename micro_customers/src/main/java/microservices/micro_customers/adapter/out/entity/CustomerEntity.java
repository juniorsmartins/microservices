package microservices.micro_customers.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "customers", indexes = {
    @Index(name = "idx_customers_nome_completo", columnList = "nome_completo"),
    @Index(name = "idx_customers_cpf", columnList = "cpf")
})
@SecondaryTable(name = "customer_endereco", pkJoinColumns = @PrimaryKeyJoinColumn(name = "customer_id"),
    foreignKey = @ForeignKey(name = "fk_customer_endereco"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"customerId"})
public final class CustomerEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @Column(name = "cpf", unique = true, nullable = false, updatable = false)
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_cadastro", nullable = false)
    private StatusCadastroEnum statusCadastro;

    @Column(name = "email", nullable = false)
    private String email;

    // ----- Element Collection Telefone ----- //
    @ElementCollection
    @CollectionTable(name = "customer_telefones",
        joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "telefones")
    private Set<TelefoneVo> telefones;

    // ----- Secondary Table Endereço ----- //
    @Column(name = "cep", table = "customer_endereco")
    private String cep;

    @Column(name = "estado", table = "customer_endereco")
    private String estado;

    @Column(name = "cidade", table = "customer_endereco")
    private String cidade;

    @Column(name = "bairro", table = "customer_endereco")
    private String bairro;

    @Column(name = "logradouro", table = "customer_endereco")
    private String logradouro;

    @Column(name = "numero", table = "customer_endereco")
    private String numero;

    @Column(name = "complemento", table = "customer_endereco")
    private String complemento;

}

