package microservices.micro_customers.config.exception.http_400;

import java.io.Serial;

public final class EmailInvalidException extends PoorlyRequestFormulatedException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailInvalidException(String email) {
        super("exception.request.format.invalid.email", email);
    }

}

