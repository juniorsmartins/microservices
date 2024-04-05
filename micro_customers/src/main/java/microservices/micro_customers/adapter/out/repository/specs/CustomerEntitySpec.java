package microservices.micro_customers.adapter.out.repository.specs;

import jakarta.persistence.criteria.Predicate;
import microservices.micro_customers.adapter.in.filters.CustomerFilter;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomerEntitySpec {

    public static Specification<CustomerEntity> consultarDinamicamente(CustomerFilter filtro) {

        return ((root, query, criteriaBuilder) -> {

            var predicados = new ArrayList<Predicate>();

            if (ObjectUtils.isNotEmpty(filtro.customerId())) {

                var predicadoIds = Arrays.asList(filtro.customerId().split(","))
                    .stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toSet())
                    .stream()
                    .map(id -> criteriaBuilder.equal(root.get("customerId"), id))
                    .collect(Collectors.toSet());

                predicados.add(criteriaBuilder.or(predicadoIds.toArray(new Predicate[0])));
            }

            if (ObjectUtils.isNotEmpty(filtro.nomeCompleto())) {

                var predicadoNomes = Arrays.asList(filtro.nomeCompleto().split(","))
                    .stream()
                        .map(nome -> criteriaBuilder.like(criteriaBuilder
                            .lower(root.get("nomeCompleto")), "%" + nome.toLowerCase() + "%"))
                        .collect(Collectors.toList());

                predicados.add(criteriaBuilder.or(predicadoNomes.toArray(new Predicate[0])));
            }

            return criteriaBuilder.and(predicados.toArray(new Predicate[0]));

        });
    }
}

