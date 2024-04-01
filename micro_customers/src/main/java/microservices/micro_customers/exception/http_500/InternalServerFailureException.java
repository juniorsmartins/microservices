package microservices.micro_customers.exception.http_500;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract class InternalServerFailureException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageKey;

    protected InternalServerFailureException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }

}

