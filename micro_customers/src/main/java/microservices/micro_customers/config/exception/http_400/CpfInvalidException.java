package microservices.micro_customers.config.exception.http_400;

import java.io.Serial;

public final class CpfInvalidException extends PoorlyRequestFormulatedException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CpfInvalidException(String cpf) {
        super("exception.request.format.invalid.cpf", cpf);
    }

}

