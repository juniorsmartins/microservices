package microservices.micro_customers.domain.tipos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import microservices.micro_customers.domain.enums.TipoTelefoneEnum;
import microservices.micro_customers.exception.http_400.TelefoneInvalidException;
import microservices.micro_customers.exception.http_400.TelefoneWithoutTypeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"telefone"})
public final class Telefone {

    private String telefone;

    private TipoTelefoneEnum tipo;

    private static final Pattern VALID_PHONE_NUMBER = Pattern.compile("^\\d{10,11}$");

    public Telefone(String numeroTelefone, TipoTelefoneEnum tipo) {
        if (!this.ehValido(numeroTelefone)) {
            throw new TelefoneInvalidException(numeroTelefone);
        }
        if (tipo == null) {
            throw new TelefoneWithoutTypeException();
        }
        this.telefone = numeroTelefone;
        this.tipo = tipo;
    }

    public boolean ehValido(String numeroTelefone) {
        Matcher matcher = VALID_PHONE_NUMBER.matcher(numeroTelefone);
        return matcher.find();
    }

}

