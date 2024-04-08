package microservices.micro_customers.application.core.domain.tipos;

import lombok.*;
import microservices.micro_customers.application.core.constant.Constantes;
import microservices.micro_customers.config.exception.http_400.AttributeWithInvalidMaximumSizeException;

import java.util.Optional;

@Builder
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = {"cep", "estado", "cidade", "bairro", "logradouro", "numero", "complemento"})
public final class Endereco {

    private String cep;

    private String estado;

    private String cidade;

    private String bairro;

    private String logradouro;

    private String numero;

    private String complemento;

    public Endereco(String cep, String estado, String cidade, String bairro,
                    String logradouro, String numero, String complemento) {
        this.setCep(cep);
        this.setEstado(estado);
        this.setCidade(cidade);
        this.setBairro(bairro);
        this.setLogradouro(logradouro);
        this.setNumero(numero);
        this.setComplemento(complemento);
    }

    public void setCep(String cep) {
        Optional.ofNullable(cep)
            .ifPresent(postal -> {
                this.attributeValidator(Constantes.CEP, postal, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CEP);
                this.cep = postal;
            });
    }

    public void setEstado(String estado) {
        Optional.ofNullable(estado)
            .ifPresent(uf -> {
                this.attributeValidator(Constantes.ESTADO, uf, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_ESTADO);
                this.estado = uf;
            });
    }

    public void setCidade(String cidade) {
        Optional.ofNullable(cidade)
            .ifPresent(city -> {
                this.attributeValidator(Constantes.CIDADE, city, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_CIDADE);
                this.cidade = city;
            });
    }

    public void setBairro(String bairro) {
        Optional.ofNullable(bairro)
            .ifPresent(area -> {
                this.attributeValidator(Constantes.BAIRRO, area, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_BAIRRO);
                this.bairro = area;
            });
    }

    public void setLogradouro(String logradouro) {
        Optional.ofNullable(logradouro)
            .ifPresent(street -> {
                this.attributeValidator(Constantes.LOGRADOURO, street, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_LOGRADOURO);
                this.logradouro = street;
            });
    }

    public void setNumero(String numero) {
        Optional.ofNullable(numero)
            .ifPresent(location -> {
                this.attributeValidator(Constantes.NUMERO, location, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_NUMERO);
                this.numero = location;
            });
    }

    public void setComplemento(String complemento) {
        Optional.ofNullable(complemento)
            .ifPresent(complement -> {
                this.attributeValidator(Constantes.COMPLEMENTO, complement, Constantes.MAX_CARACTERES_CUSTOMER_ENDERECO_COMPLEMENTO);
                this.complemento = complement;
            });
    }

    private void attributeValidator(String nomeAtributo, String valorAtributo, int tamanhoMaximo) {
        if (valorAtributo.length() > tamanhoMaximo) {
            throw new AttributeWithInvalidMaximumSizeException(nomeAtributo, valorAtributo, tamanhoMaximo);
        }
    }

}

