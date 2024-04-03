package microservices.micro_customers.util;

import microservices.micro_customers.adapter.in.dto.EnderecoDto;
import microservices.micro_customers.adapter.in.dto.TelefoneDto;
import microservices.micro_customers.adapter.in.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.entity.value_objects.EnderecoVo;
import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;
import microservices.micro_customers.application.core.domain.Customer;
import microservices.micro_customers.application.core.domain.enums.StatusCadastroEnum;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.application.core.domain.tipos.*;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.Random;
import java.util.Set;

// Padrão Object Mother
public final class FactoryObjectMother {

    private static FactoryObjectMother singletonFactory;

    private final Faker faker = new Faker();

    private FactoryObjectMother() { }

    // Padrão Singleton
    public static synchronized FactoryObjectMother singleton() {
        if (singletonFactory == null) {
            singletonFactory = new FactoryObjectMother();
        }
        return singletonFactory;
    }

    // Padrão Builder
    public CustomerEntity.CustomerEntityBuilder gerarCustomerEntityBuilder() {
        var telefoneVo1 = gerarTelefoneVoFixoBuilder().build();
        var telefoneVo2 = gerarTelefoneVoCelularBuilder().build();
        var enderecoVo1 = gerarEnderecoVoBuilder().build();
        var enderecoVo2 = gerarEnderecoVoBuilder().build();

        return CustomerEntity.builder()
            .nomeCompleto(faker.lorem().characters(20, 100))
            .cpf(faker.cpf().valid())
            .dataNascimento(LocalDate.of(2000, 10, 27))
            .statusCadastro(StatusCadastroEnum.INICIADO)
            .email(faker.internet().emailAddress())
            .telefones(Set.of(telefoneVo1, telefoneVo2))
            .enderecos(Set.of(enderecoVo1, enderecoVo2));
    }

    public TelefoneVo.TelefoneVoBuilder gerarTelefoneVoFixoBuilder() {
        return TelefoneVo.builder()
            .telefone(gerarNumeroAleatorioComOnzeDigitos())
            .tipo(TipoTelefoneEnum.FIXO);
    }

    public TelefoneVo.TelefoneVoBuilder gerarTelefoneVoCelularBuilder() {
        return TelefoneVo.builder()
            .telefone(gerarNumeroAleatorioComOnzeDigitos())
            .tipo(TipoTelefoneEnum.CELULAR);
    }

    private String gerarNumeroAleatorioComOnzeDigitos() {
        Random random = new Random();

        StringBuilder onzeDigitos = new StringBuilder();
        for (int i = 1; i <= 11; i++) {
            int digito = random.nextInt(9) + 1; // Gera um dígito aleatório de 0 a 9
            onzeDigitos.append(digito);
        }

        return onzeDigitos.toString();
    }

    public EnderecoVo.EnderecoVoBuilder gerarEnderecoVoBuilder() {
        return EnderecoVo.builder()
            .cep(faker.address().zipCode())
            .estado(faker.address().state())
            .cidade(faker.address().city())
            .bairro(faker.lorem().characters(10, 20))
            .logradouro(faker.address().streetAddress())
            .numero(faker.address().buildingNumber())
            .complemento(faker.lorem().characters(30, 200));
    }

    public Customer.CustomerBuilder gerarCustomerBuilder() {
        var telefone1 = gerarTelefoneFixoBuilder().build();
        var telefone2 = gerarTelefoneCelularBuilder().build();
        var endereco1 = gerarEnderecoBuilder().build();
        var endereco2 = gerarEnderecoBuilder().build();

        return Customer.builder()
            .nomeCompleto(faker.lorem().characters(20, 100))
            .cpf(new CadastroPessoaFisica(faker.cpf().valid()))
            .dataNascimento(new DataNascimento(LocalDate.of(2000, 10, 27)))
            .statusCadastro(StatusCadastroEnum.INICIADO)
            .email(new CorreioEletronico(faker.internet().emailAddress()))
            .telefones(Set.of(telefone1, telefone2))
            .enderecos(Set.of(endereco1, endereco2));
    }

    public Telefone.TelefoneBuilder gerarTelefoneFixoBuilder() {
        return Telefone.builder()
            .telefone(gerarNumeroAleatorioComOnzeDigitos())
            .tipo(TipoTelefoneEnum.FIXO);
    }

    public Telefone.TelefoneBuilder gerarTelefoneCelularBuilder() {
        return Telefone.builder()
            .telefone(gerarNumeroAleatorioComOnzeDigitos())
            .tipo(TipoTelefoneEnum.CELULAR);
    }

    public Endereco.EnderecoBuilder gerarEnderecoBuilder() {
        return Endereco.builder()
            .cep(faker.address().zipCode())
            .estado(faker.address().state())
            .cidade(faker.address().city())
            .bairro(faker.lorem().characters(10, 20))
            .logradouro(faker.address().streetAddress())
            .numero(faker.address().buildingNumber())
            .complemento(faker.lorem().characters(30, 200));
    }

    public CustomerCreateDtoRequest.CustomerCreateDtoRequestBuilder gerarCustomerCreateDtoRequestBuilder() {
        var telefone1 = gerarTelefoneDtoFixoBuilder().build();
        var telefone2 = gerarTelefoneDtoCelularBuilder().build();
        var endereco1 = gerarEnderecoDtoBuilder().build();
        var endereco2 = gerarEnderecoDtoBuilder().build();

        return CustomerCreateDtoRequest.builder()
            .nomeCompleto(faker.lorem().characters(20, 100))
            .cpf(faker.cpf().valid())
            .dataNascimento("27/10/2000")
            .email(faker.internet().emailAddress())
            .telefones(Set.of(telefone1, telefone2))
            .enderecos(Set.of(endereco1, endereco2));
    }

    public TelefoneDto.TelefoneDtoBuilder gerarTelefoneDtoFixoBuilder() {
        return TelefoneDto.builder()
            .telefone(gerarNumeroAleatorioComOnzeDigitos())
            .tipo(TipoTelefoneEnum.FIXO);
    }

    public TelefoneDto.TelefoneDtoBuilder gerarTelefoneDtoCelularBuilder() {
        return TelefoneDto.builder()
            .telefone(gerarNumeroAleatorioComOnzeDigitos())
            .tipo(TipoTelefoneEnum.CELULAR);
    }

    public EnderecoDto.EnderecoDtoBuilder gerarEnderecoDtoBuilder() {
        return EnderecoDto.builder()
            .cep(faker.address().zipCode())
            .estado(faker.address().state())
            .cidade(faker.address().city())
            .bairro(faker.lorem().characters(10, 20))
            .logradouro(faker.address().streetAddress())
            .numero(faker.address().buildingNumber())
            .complemento(faker.lorem().characters(30, 200));
    }

    public CadastroPessoaFisica.CadastroPessoaFisicaBuilder gerarCadastroPessoaFisicaValidoBuilder() {
        return CadastroPessoaFisica.builder()
            .cpf(faker.cpf().valid());
    }

    public CadastroPessoaFisica.CadastroPessoaFisicaBuilder gerarCadastroPessoaFisicaInvalidoBuilder() {
        return CadastroPessoaFisica.builder()
            .cpf(faker.cpf().invalid());
    }

    public CorreioEletronico.CorreioEletronicoBuilder gerarCorreioEletronicoValidoBuilder() {
        return CorreioEletronico.builder()
            .email(faker.internet().emailAddress());
    }

    public CorreioEletronico.CorreioEletronicoBuilder gerarCorreioEletronicoInvalidoBuilder() {
        return CorreioEletronico.builder()
            .email(faker.lorem().characters(15, 30));
    }

}

