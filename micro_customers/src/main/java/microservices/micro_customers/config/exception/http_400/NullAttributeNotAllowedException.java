package microservices.micro_customers.config.exception.http_400;

import java.io.Serial;

public final class NullAttributeNotAllowedException extends RequestWithDataInIncorrectFormatException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NullAttributeNotAllowedException(String nomeAtributo) {
        super("exception.request.format.invalid.null", nomeAtributo);
    }

}

