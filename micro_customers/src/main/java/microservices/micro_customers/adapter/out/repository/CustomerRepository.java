package microservices.micro_customers.adapter.out.repository;

import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> { }

