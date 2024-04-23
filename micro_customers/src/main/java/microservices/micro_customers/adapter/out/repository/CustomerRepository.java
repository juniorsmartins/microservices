package microservices.micro_customers.adapter.out.repository;

import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>,
    JpaSpecificationExecutor<CustomerEntity> {

    Optional<CustomerEntity> findByCpf(String cpf);

}

