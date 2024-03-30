package microservices.micro_empresas.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

    @Column(name = "created_at", nullable = false, insertable = true, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "created_by", nullable = false, insertable = true, updatable = false)
    private String createdBy;

    @Column(name = "updated_at", nullable = true, insertable = false, updatable = true)
    private OffsetDateTime updatedAt;

    @Column(name = "updated_by", nullable = true, insertable = false, updatable = true)
    private String updatedBy;

}

