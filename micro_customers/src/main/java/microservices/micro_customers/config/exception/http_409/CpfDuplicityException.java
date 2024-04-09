package microservices.micro_customers.config.exception.http_409;

import java.io.Serial;

public final class CpfDuplicityException extends BusinessRuleViolationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CpfDuplicityException(String cpf) {
        super("exception.business.rule.violation.cpf-duplicity", cpf);
    }

}

