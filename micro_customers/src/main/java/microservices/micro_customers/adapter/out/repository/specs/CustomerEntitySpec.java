package microservices.micro_customers.adapter.out.repository.specs;

import jakarta.persistence.criteria.Predicate;
import microservices.micro_customers.adapter.in.filters.CustomerFilter;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomerEntitySpec {

    private CustomerEntitySpec() { }

    public static Specification<CustomerEntity> consultarDinamicamente(CustomerFilter filtro) {

        return ((root, query, criteriaBuilder) -> {

            var parametros = new ArrayList<Predicate>();

            if (ObjectUtils.isNotEmpty(filtro.customerId())) {

                var predicados = Arrays.stream(filtro.customerId().split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet())
                    .stream()
                    .map(id -> criteriaBuilder.equal(root.get("customerId"), id))
                    .collect(Collectors.toSet());

                parametros.add(criteriaBuilder.or(predicados.toArray(new Predicate[0])));
            }

            if (ObjectUtils.isNotEmpty(filtro.nomeCompleto())) {

                var predicados = Arrays.stream(filtro.nomeCompleto().split(","))
                    .map(nome -> criteriaBuilder.like(criteriaBuilder
                        .lower(root.get("nomeCompleto")), "%" + nome.toLowerCase() + "%"))
                    .collect(Collectors.toSet());

                parametros.add(criteriaBuilder.or(predicados.toArray(new Predicate[0])));
            }

            if (ObjectUtils.isNotEmpty(filtro.cpf())) {

                var predicados = Arrays.stream(filtro.cpf().split(","))
                    .map(cadastroPF -> criteriaBuilder.like(root.get("cpf"), "%" + cadastroPF + "%"))
                    .collect(Collectors.toSet());

                parametros.add(criteriaBuilder.or(predicados.toArray(new Predicate[0])));
            }

            if (ObjectUtils.isNotEmpty(filtro.statusCadastro())) {
                parametros.add(criteriaBuilder.equal(root.get("statusCadastro"), filtro.statusCadastro()));
            }

            if (ObjectUtils.isNotEmpty(filtro.email())) {

                var predicados = Arrays.stream(filtro.email().split(","))
                    .map(correio -> criteriaBuilder.like(root.get("email"), "%" + correio + "%"))
                    .collect(Collectors.toSet());

                parametros.add(criteriaBuilder.or(predicados.toArray(new Predicate[0])));
            }

            return criteriaBuilder.and(parametros.toArray(new Predicate[0]));

        });
    }
}

