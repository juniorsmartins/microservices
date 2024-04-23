package microservices.micro_customers.application.core.domain.tipos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import microservices.micro_customers.application.core.constant.Constantes;
import microservices.micro_customers.config.exception.http_400.AttributeWithInvalidMaximumSizeException;
import microservices.micro_customers.config.exception.http_400.EmailInvalidException;
import microservices.micro_customers.config.exception.http_400.NullAttributeNotAllowedException;
import microservices.micro_customers.config.exception.http_400.ProhibitedEmptyOrBlankAttributeException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"email"})
public final class CorreioEletronico {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private String email;

    public CorreioEletronico(String correioEletronico) {
        Optional.ofNullable(correioEletronico)
            .ifPresentOrElse(email -> {
                    this.attributeValidator(Constantes.EMAIL, email, Constantes.MAX_CARACTERES_CUSTOMER_EMAIL);
                    if (!this.hasValidFormat(email)) {
                        throw new EmailInvalidException(email);
                    }
                    this.email = email;
                },
                () -> {throw new NullAttributeNotAllowedException(Constantes.EMAIL);}
            );
    }

    private void attributeValidator(String nomeAtributo, String valorAtributo, int tamanhoMaximo) {
        if (valorAtributo.isBlank()) {
            throw new ProhibitedEmptyOrBlankAttributeException(nomeAtributo);
        }
        if (valorAtributo.length() > tamanhoMaximo) {
            throw new AttributeWithInvalidMaximumSizeException(nomeAtributo, valorAtributo, tamanhoMaximo);
        }
    }

    public boolean hasValidFormat(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}

