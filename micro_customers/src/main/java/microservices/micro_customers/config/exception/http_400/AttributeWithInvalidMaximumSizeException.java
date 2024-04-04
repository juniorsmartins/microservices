package microservices.micro_customers.config.exception.http_400;

import java.io.Serial;

public final class AttributeWithInvalidMaximumSizeException extends RequestWithDataOutsideTheRulesException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AttributeWithInvalidMaximumSizeException(String nomeAtributo,
                                                    String valorAtributo,
                                                    int tamanhoMaximo) {
        super("exception.request.attribute.invalid.maximum_size", nomeAtributo, valorAtributo, tamanhoMaximo);
    }

}

