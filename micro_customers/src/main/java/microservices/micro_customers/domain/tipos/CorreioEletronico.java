package microservices.micro_customers.domain.tipos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import microservices.micro_customers.exception.http_400.EmailInvalidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"email"})
public final class CorreioEletronico {

    public static final long serialVersionUID = 1L;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private String email;

    public CorreioEletronico(String email) {
        if (!this.ehValido(email)) {
            throw new EmailInvalidException(email);
        }
        this.email = email;
    }

    public boolean ehValido(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}

