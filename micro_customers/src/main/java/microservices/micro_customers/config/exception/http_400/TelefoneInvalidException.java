package microservices.micro_customers.config.exception.http_400;

import java.io.Serial;

public final class TelefoneInvalidException extends RequestWithDataInIncorrectFormatException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TelefoneInvalidException(String telefone) {
        super("exception.request.format.invalid.telefone", telefone);
    }

}

