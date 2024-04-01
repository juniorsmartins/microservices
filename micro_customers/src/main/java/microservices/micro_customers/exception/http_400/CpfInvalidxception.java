package microservices.micro_customers.exception.http_400;

import java.io.Serial;

public final class CpfInvalidxception extends PoorlyRequestFormulatedException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CpfInvalidxception(String cpf) {
        super("exception.request.format.invalid.cpf", cpf);
    }

}

