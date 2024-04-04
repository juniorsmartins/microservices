package microservices.micro_customers.adapter.mapper;

import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.adapter.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.dto.response.CustomerCreateDtoResponse;
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
        var enderecos = this.toEndereco(dto);
        var telefones = this.toTelefone(dto);

        var customer = new Customer();
        customer.setNomeCompleto(dto.nomeCompleto());
        customer.setCpf(new CadastroPessoaFisica(dto.cpf()));
        customer.setDataNascimento(new DataNascimento(dto.dataNascimento()));
        customer.setEmail(new CorreioEletronico(dto.email()));
        customer.setTelefones(telefones);
        customer.setEnderecos(enderecos);

        return customer;
//        return Customer.builder()
//            .nomeCompleto(dto.nomeCompleto())
//            .cpf(new CadastroPessoaFisica(dto.cpf()))
//            .dataNascimento(new DataNascimento(dto.dataNascimento()))
//            .email(new CorreioEletronico(dto.email()))
//            .telefones(telefones)
//            .enderecos(enderecos)
//            .build();
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

    private Set<Endereco> toEndereco(CustomerCreateDtoRequest dto) {
        if (dto.enderecos() == null || dto.enderecos().isEmpty()) {
            return Collections.emptySet();
        }

        return dto.enderecos()
            .stream()
            .map(address -> Endereco.builder()
                .cep(address.cep())
                .estado(address.estado())
                .cidade(address.cidade())
                .bairro(address.bairro())
                .logradouro(address.logradouro())
                .numero(address.numero())
                .complemento(address.complemento())
                .build())
            .collect(Collectors.toSet());
    }

    @Override
    public CustomerCreateDtoResponse toCustomerCreateDtoResponse(final Customer customer) {

        return Optional.ofNullable(customer)
            .map(this::customerCreateDtoResponse)
            .orElseThrow();
    }

    private CustomerCreateDtoResponse customerCreateDtoResponse(Customer customer) {
        var telefonesDto = this.toTelefoneDto(customer);
        var enderecosDto = this.toEnderecoDto(customer);

        return CustomerCreateDtoResponse.builder()
            .customerId(customer.getCustomerId())
            .nomeCompleto(customer.getNomeCompleto())
            .cpf(customer.getCpf().getCpf())
            .dataNascimento(customer.getDataNascimento().getDataNascimentoString())
            .statusCadastro(customer.getStatusCadastro())
            .email(customer.getEmail().getEmail())
            .telefones(telefonesDto)
            .enderecos(enderecosDto)
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

    private Set<EnderecoDto> toEnderecoDto(Customer customer) {
        if (customer.getEnderecos() == null || customer.getEnderecos().isEmpty()) {
            return Collections.emptySet();
        }

        return customer.getEnderecos()
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

