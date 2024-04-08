package microservices.micro_customers.application.core.domain.tipos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import microservices.micro_customers.application.core.constant.Constantes;
import microservices.micro_customers.application.core.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.config.exception.http_400.ProhibitedEmptyOrBlankAttributeException;
import microservices.micro_customers.config.exception.http_400.RequestWithTypeAndWithoutNumberException;
import microservices.micro_customers.config.exception.http_400.TelefoneInvalidException;
import microservices.micro_customers.config.exception.http_400.TelefoneWithoutTypeException;
import org.springframework.util.ObjectUtils;

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
            .ifPresentOrElse(valor -> {
                this.attributeValidator(Constantes.TELEFONE_NUMERO, valor);
                if (!this.hasValidFormat(valor)) {
                    throw new TelefoneInvalidException(valor);
                }
                if (ObjectUtils.isEmpty(tipo)) {
                    throw new TelefoneWithoutTypeException(valor);
                }
                this.numero = valor;
                this.tipo = tipo;
            },
            () -> {
                if (ObjectUtils.isEmpty(numeroTelefone) && !ObjectUtils.isEmpty(tipo)) {
                    throw new RequestWithTypeAndWithoutNumberException(tipo.getValue());
                }
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

