package microservices.micro_customers.application.core.domain.tipos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import microservices.micro_customers.application.core.constant.Constants;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.config.exception.http_400.ProhibitedEmptyOrBlankAttributeException;
import microservices.micro_customers.config.exception.http_400.TelefoneInvalidException;
import microservices.micro_customers.config.exception.http_400.TelefoneWithoutTypeException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"numero"})
public final class Telefone {

    private String numero;

    private TipoTelefoneEnum tipo;

    private static final Pattern VALID_PHONE_NUMBER = Pattern.compile("^\\d{10,11}$");

    public Telefone(String numeroTelefone, TipoTelefoneEnum tipo) {
        Optional.ofNullable(numeroTelefone)
            .ifPresent(valor -> {
                this.attributeValidator(Constants.TELEFONE_NUMERO, valor);
                if (!this.hasValidFormat(valor)) {
                    throw new TelefoneInvalidException(valor);
                }
                if (tipo == null) {
                    throw new TelefoneWithoutTypeException();
                }
                this.numero = valor;
                this.tipo = tipo;
            }
        );
    }

    private void attributeValidator(String nomeAtributo, String valorAtributo) {
        if (valorAtributo.isBlank()) {
            throw new ProhibitedEmptyOrBlankAttributeException(nomeAtributo);
        }
    }

    public boolean hasValidFormat(String numeroTelefone) {
        Matcher matcher = VALID_PHONE_NUMBER.matcher(numeroTelefone);
        return matcher.find();
    }

}

