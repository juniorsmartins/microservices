package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.adapter.dto.response.CustomerSearchDtoResponse;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.entity.value_objects.EnderecoVo;
import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.core.domain.tipos.*;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MapperOutImpl implements MapperOut {

    @Override
    public CustomerEntity toCustomerEntity(Customer customer) {
        return Optional.ofNullable(customer)
            .map(this::entity)
            .orElseThrow();
    }

    private CustomerEntity entity(Customer customer) {
        var telefonesVo = this.toTelefoneVo(customer);
        var enderecosVo = this.toEnderecoVo(customer);

        return CustomerEntity.builder()
            .customerId(customer.getCustomerId())
            .nomeCompleto(customer.getNomeCompleto())
            .cpf(customer.getCpf().getCpf())
            .dataNascimento(customer.getDataNascimento().getDataNascimentoLocalDate())
            .statusCadastro(customer.getStatusCadastro())
            .email(customer.getEmail().getEmail())
            .telefones(telefonesVo)
            .enderecos(enderecosVo)
            .build();
    }

    private Set<TelefoneVo> toTelefoneVo(Customer customer) {
        if (customer.getTelefones() == null || customer.getTelefones().isEmpty()) {
            return Collections.emptySet();
        }

        return customer.getTelefones()
            .stream()
            .map(fone -> new TelefoneVo(fone.getNumero(), fone.getTipo()))
            .collect(Collectors.toSet());
    }

    private Set<EnderecoVo> toEnderecoVo(Customer customer) {
        if (customer.getEnderecos() == null || customer.getEnderecos().isEmpty()) {
            return Collections.emptySet();
        }

        return customer.getEnderecos()
            .stream()
            .map(address -> EnderecoVo.builder()
                .cep(address.getCep())
                .estado(address.getEstado())
                .cidade(address.getCidade())
                .bairro(address.getBairro())
                .logradouro(address.getLogradouro())
                .numero(address.getNumero())
                .complemento(address.getComplemento())
                .build())
            .collect(Collectors.toSet());
    }

    @Override
    public Customer toCustomer(CustomerEntity entity) {
        return Optional.ofNullable(entity)
            .map(this::customer)
            .orElseThrow();
    }

    private Customer customer(CustomerEntity entity) {
        var telefones = this.toTelefone(entity);
        var enderecos = this.toEndereco(entity);

        return Customer.builder()
            .customerId(entity.getCustomerId())
            .nomeCompleto(entity.getNomeCompleto())
            .cpf(new CadastroPessoaFisica(entity.getCpf()))
            .dataNascimento(new DataNascimento(entity.getDataNascimento()))
            .statusCadastro(entity.getStatusCadastro())
            .email(new CorreioEletronico(entity.getEmail()))
            .telefones(telefones)
            .enderecos(enderecos)
            .createdAt(entity.getCreatedAt())
            .createdBy(entity.getCreatedBy())
            .updatedAt(entity.getUpdatedAt())
            .updatedBy(entity.getUpdatedBy())
            .build();
    }

    private Set<Telefone> toTelefone(CustomerEntity entity) {
        if (entity.getTelefones() == null || entity.getTelefones().isEmpty()) {
            return Collections.emptySet();
        }

        return entity.getTelefones()
            .stream()
            .map(fone -> new Telefone(fone.getNumero(), fone.getTipo()))
            .collect(Collectors.toSet());
    }

    private Set<Endereco> toEndereco(CustomerEntity entity) {
        if (entity.getEnderecos() == null || entity.getEnderecos().isEmpty()) {
            return Collections.emptySet();
        }

        return entity.getEnderecos()
            .stream()
            .map(address -> Endereco.builder()
                .cep(address.getCep())
                .estado(address.getEstado())
                .cidade(address.getCidade())
                .bairro(address.getBairro())
                .logradouro(address.getLogradouro())
                .numero(address.getNumero())
                .complemento(address.getComplemento())
                .build())
            .collect(Collectors.toSet());
    }

    @Override
    public CustomerSearchDtoResponse toCustomerSearchDtoResponse(CustomerEntity entity) {
        return Optional.ofNullable(entity)
            .map(this::customerSearchDtoResponse)
            .orElseThrow();
    }

    private CustomerSearchDtoResponse customerSearchDtoResponse(CustomerEntity entity) {
        var telefonesDto = this.toTelefoneDto(entity);
        var enderecosDto = this.toEnderecoDto(entity);

        return CustomerSearchDtoResponse.builder()
            .customerId(entity.getCustomerId())
            .nomeCompleto(entity.getNomeCompleto())
            .cpf(entity.getCpf())
            .dataNascimento(entity.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            .statusCadastro(entity.getStatusCadastro())
            .email(entity.getEmail())
            .telefones(telefonesDto)
            .enderecos(enderecosDto)
            .createdAt(entity.getCreatedAt())
            .createdBy(entity.getCreatedBy())
            .updatedAt(entity.getUpdatedAt())
            .updatedBy(entity.getUpdatedBy())
            .build();
    }

    private Set<TelefoneDto> toTelefoneDto(CustomerEntity entity) {
        if (entity.getTelefones() == null || entity.getTelefones().isEmpty()) {
            return Collections.emptySet();
        }

        return entity.getTelefones()
            .stream()
            .map(fone -> new TelefoneDto(fone.getNumero(), fone.getTipo()))
            .collect(Collectors.toSet());
    }

    private Set<EnderecoDto> toEnderecoDto(CustomerEntity entity) {
        if (entity.getEnderecos() == null || entity.getEnderecos().isEmpty()) {
            return Collections.emptySet();
        }

        return entity.getEnderecos()
            .stream()
            .map(address -> EnderecoDto.builder()
                .cep(address.getCep())
                .estado(address.getEstado())
                .cidade(address.getCidade())
                .bairro(address.getBairro())
                .logradouro(address.getLogradouro())
                .numero(address.getNumero())
                .complemento(address.getComplemento())
                .build())
            .collect(Collectors.toSet());
    }

}

