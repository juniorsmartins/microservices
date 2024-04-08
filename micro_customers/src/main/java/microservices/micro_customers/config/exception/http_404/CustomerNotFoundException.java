package microservices.micro_customers.config.exception.http_404;

import java.io.Serial;

public final class CustomerNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(final Long customerId) {
        super("exception.resource.not_found.customer", customerId);
    }

}

