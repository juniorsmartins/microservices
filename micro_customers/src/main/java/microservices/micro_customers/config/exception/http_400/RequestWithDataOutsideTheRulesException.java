package microservices.micro_customers.config.exception.http_400;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class RequestWithDataOutsideTheRulesException extends RuntimeException
        permits AttributeWithInvalidMaximumSizeException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String messageKey;

  private final String nomeAtributo;

  private final String valorAtributo;

  private final int tamanhoMaximo;

  protected RequestWithDataOutsideTheRulesException(String messageKey,
                                                    String nomeAtributo,
                                                    String valorAtributo,
                                                    int tamanhoMaximo) {
    super(messageKey);
    this.messageKey = messageKey;
    this.nomeAtributo = nomeAtributo;
    this.valorAtributo = valorAtributo;
    this.tamanhoMaximo = tamanhoMaximo;
  }

}

