package microservices.micro_customers.config.exception.http_400;

import java.io.Serial;

public final class RequestWithTypeAndWithoutNumberException extends RequestWithDataInIncorrectFormatException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RequestWithTypeAndWithoutNumberException(String tipo) {
        super("exception.request.format.invalid.telefone.tipo-e-numero", tipo);
    }

}

