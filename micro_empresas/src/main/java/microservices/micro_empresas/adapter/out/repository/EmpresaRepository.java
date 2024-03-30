package microservices.micro_empresas.adapter.out.repository;

import microservices.micro_empresas.adapter.out.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> { }

