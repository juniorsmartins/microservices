package microservices.micro_customers.config.exception.http_400;

import java.io.Serial;

public final class TelefoneWithoutTypeException extends RequestWithDataInIncorrectFormatException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TelefoneWithoutTypeException() {
        super("exception.request.format.invalid.telefone.tipo", null);
    }

}

