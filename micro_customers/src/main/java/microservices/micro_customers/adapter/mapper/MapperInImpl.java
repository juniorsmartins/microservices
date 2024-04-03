package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.in.dto.EnderecoDto;
import microservices.micro_customers.adapter.in.dto.TelefoneDto;
import microservices.micro_customers.adapter.in.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.in.dto.response.CustomerCreateDtoResponse;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.core.domain.tipos.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MapperInImpl implements MapperIn {

    @Override
    public Customer toCustomer(final CustomerCreateDtoRequest customerCreateDtoRequest) {

        return Optional.ofNullable(customerCreateDtoRequest)
            .map(this::customer)
            .orElseThrow();
    }

    private Customer customer(CustomerCreateDtoRequest dto) {
        var endereco = this.toEndereco(dto);
        var telefones = this.toTelefone(dto);

        return Customer.builder()
            .nomeCompleto(dto.nomeCompleto())
            .cpf(new CadastroPessoaFisica(dto.cpf()))
            .dataNascimento(new DataNascimento(dto.dataNascimento()))
            .email(new CorreioEletronico(dto.email()))
            .telefones(telefones)
            .endereco(endereco)
            .build();
    }

    private Set<Telefone> toTelefone(CustomerCreateDtoRequest dto) {
        if (dto.telefones() == null || dto.telefones().isEmpty()) {
            return Collections.emptySet();
        }

        return dto.telefones()
            .stream()
            .map(fone -> new Telefone(fone.telefone(), fone.tipo()))
            .collect(Collectors.toSet());
    }

    private Endereco toEndereco(CustomerCreateDtoRequest dto) {
        return Endereco.builder()
            .cep(dto.endereco().cep())
            .estado(dto.endereco().estado())
            .cidade(dto.endereco().cidade())
            .bairro(dto.endereco().bairro())
            .logradouro(dto.endereco().logradouro())
            .numero(dto.endereco().numero())
            .complemento(dto.endereco().complemento())
            .build();
    }

    @Override
    public CustomerCreateDtoResponse toCustomerCreateDtoResponse(final Customer customer) {

        return Optional.ofNullable(customer)
            .map(this::customerCreateDtoResponse)
            .orElseThrow();
    }

    private CustomerCreateDtoResponse customerCreateDtoResponse(Customer customer) {
        var telefonesDto = this.toTelefoneDto(customer);
        var enderecoDto = this.toEnderecoDto(customer);

        return CustomerCreateDtoResponse.builder()
            .customerId(customer.getCustomerId())
            .nomeCompleto(customer.getNomeCompleto())
            .cpf(customer.getCpf().getCpf())
            .dataNascimento(customer.getDataNascimento().getDataNascimentoString())
            .statusCadastro(customer.getStatusCadastro())
            .email(customer.getEmail().getEmail())
            .telefones(telefonesDto)
            .endereco(enderecoDto)
            .createdAt(customer.getCreatedAt())
            .createdBy(customer.getCreatedBy())
            .updatedAt(customer.getUpdatedAt())
            .updatedBy(customer.getUpdatedBy())
            .build();
    }

    private Set<TelefoneDto> toTelefoneDto(Customer customer) {
        if (customer.getTelefones() == null || customer.getTelefones().isEmpty()) {
            return Collections.emptySet();
        }

        return customer.getTelefones()
            .stream()
            .map(fone -> new TelefoneDto(fone.getTelefone(), fone.getTipo()))
            .collect(Collectors.toSet());
    }

    private EnderecoDto toEnderecoDto(Customer customer) {
        return EnderecoDto.builder()
            .cep(customer.getEndereco().getCep())
            .estado(customer.getEndereco().getEstado())
            .cidade(customer.getEndereco().getCidade())
            .bairro(customer.getEndereco().getBairro())
            .logradouro(customer.getEndereco().getLogradouro())
            .numero(customer.getEndereco().getNumero())
            .complemento(customer.getEndereco().getComplemento())
            .build();
    }

}

