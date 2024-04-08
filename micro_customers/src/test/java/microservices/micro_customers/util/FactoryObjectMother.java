package microservices.micro_customers.util;

import microservices.micro_customers.adapter.dto.EnderecoDto;
import microservices.micro_customers.adapter.dto.TelefoneDto;
import microservices.micro_customers.adapter.dto.request.CustomerCreateDtoRequest;
import microservices.micro_customers.adapter.out.entity.CustomerEntity;
import microservices.micro_customers.adapter.out.entity.value_objects.EnderecoVo;
import microservices.micro_customers.adapter.out.entity.value_objects.TelefoneVo;
import microservices.micro_customers.application.core.constant.Constantes;
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
            .nomeCompleto(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO))
            .cpf(faker.cpf().valid())
            .dataNascimento(LocalDate.of(2000, 10, 27))
            .statusCadastro(StatusCadastroEnum.INICIADO)
            .email(faker.internet().emailAddress())
            .telefones(Set.of(telefoneVo1, telefoneVo2))
            .enderecos(Set.of(enderecoVo1, enderecoVo2));
    }

    public TelefoneVo.TelefoneVoBuilder gerarTelefoneVoFixoBuilder() {
        return TelefoneVo.builder()
            .numero(gerarNumerosAleatorios())
            .tipo(TipoTelefoneEnum.FIXO);
    }

    public TelefoneVo.TelefoneVoBuilder gerarTelefoneVoCelularBuilder() {
        return TelefoneVo.builder()
            .numero(gerarNumerosAleatorios())
            .tipo(TipoTelefoneEnum.CELULAR);
    }

    private String gerarNumerosAleatorios() {
        Random random = new Random();

        StringBuilder onzeDigitos = new StringBuilder();
        for (int i = 1; i <= Constantes.MAX_CARACTERES_CUSTOMER_TELEFONE_NUMERO; i++) {
            int digito = random.nextInt(9) + 1; // Gera um dígito aleatório de 0 a 9
            onzeDigitos.append(digito);
        }

        return onzeDigitos.toString();
    }

    public EnderecoVo.EnderecoVoBuilder gerarEnderecoVoBuilder() {
        return EnderecoVo.builder()
            .cep(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CEP))
            .estado(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_ESTADO))
            .cidade(faker.lorem().characters(1, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CIDADE))
            .bairro(faker.lorem().characters(1, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_BAIRRO))
            .logradouro(faker.lorem().characters(1, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_LOGRADOURO))
            .numero(faker.lorem().characters(1, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_NUMERO))
            .complemento(faker.lorem().characters(1, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_COMPLEMENTO));
    }

    public Customer.CustomerBuilder gerarCustomerBuilder() {
        var telefone1 = gerarTelefoneFixoBuilder().build();
        var telefone2 = gerarTelefoneCelularBuilder().build();
        var endereco1 = gerarEnderecoBuilder().build();
        var endereco2 = gerarEnderecoBuilder().build();

        return Customer.builder()
            .nomeCompleto(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO))
            .cpf(new CadastroPessoaFisica(faker.cpf().valid()))
            .dataNascimento(new DataNascimento(LocalDate.of(2000, 10, 27)))
            .statusCadastro(StatusCadastroEnum.INICIADO)
            .email(new CorreioEletronico(faker.internet().emailAddress()))
            .telefones(Set.of(telefone1, telefone2))
            .enderecos(Set.of(endereco1, endereco2));
    }

    public Telefone.TelefoneBuilder gerarTelefoneFixoBuilder() {
        return Telefone.builder()
            .numero(gerarNumerosAleatorios())
            .tipo(TipoTelefoneEnum.FIXO);
    }

    public Telefone.TelefoneBuilder gerarTelefoneCelularBuilder() {
        return Telefone.builder()
            .numero(gerarNumerosAleatorios())
            .tipo(TipoTelefoneEnum.CELULAR);
    }

    public Endereco.EnderecoBuilder gerarEnderecoBuilder() {
        return Endereco.builder()
            .cep(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CEP))
            .estado(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_ESTADO))
            .cidade(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CIDADE))
            .bairro(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_BAIRRO))
            .logradouro(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_LOGRADOURO))
            .numero(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_NUMERO))
            .complemento(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_COMPLEMENTO));
    }

    public CustomerCreateDtoRequest.CustomerCreateDtoRequestBuilder gerarCustomerCreateDtoRequestBuilder() {
        var telefone1 = gerarTelefoneDtoFixoBuilder().build();
        var telefone2 = gerarTelefoneDtoCelularBuilder().build();
        var endereco1 = gerarEnderecoDtoBuilder().build();
        var endereco2 = gerarEnderecoDtoBuilder().build();

        return CustomerCreateDtoRequest.builder()
            .nomeCompleto(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_NOMECOMPLETO))
            .cpf(faker.cpf().valid())
            .dataNascimento("27/10/2000")
            .email(faker.internet().emailAddress())
            .telefones(Set.of(telefone1, telefone2))
            .enderecos(Set.of(endereco1, endereco2));
    }

    public TelefoneDto.TelefoneDtoBuilder gerarTelefoneDtoFixoBuilder() {
        return TelefoneDto.builder()
            .numero(gerarNumerosAleatorios())
            .tipo(TipoTelefoneEnum.FIXO);
    }

    public TelefoneDto.TelefoneDtoBuilder gerarTelefoneDtoCelularBuilder() {
        return TelefoneDto.builder()
            .numero(gerarNumerosAleatorios())
            .tipo(TipoTelefoneEnum.CELULAR);
    }

    public EnderecoDto.EnderecoDtoBuilder gerarEnderecoDtoBuilder() {
        return EnderecoDto.builder()
            .cep(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CEP))
            .estado(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_ESTADO))
            .cidade(faker.lorem().characters(1, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CIDADE))
            .bairro(faker.lorem().characters(1, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_BAIRRO))
            .logradouro(faker.lorem().characters(1, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_LOGRADOURO))
            .numero(faker.lorem().characters(Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_NUMERO))
            .complemento(faker.lorem().characters(1, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_COMPLEMENTO));
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

