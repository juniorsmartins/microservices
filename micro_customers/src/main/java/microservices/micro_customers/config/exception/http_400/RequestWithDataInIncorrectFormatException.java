package microservices.micro_customers.config.exception.http_400;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class RequestWithDataInIncorrectFormatException extends RuntimeException permits CpfInvalidException,
      EmailInvalidException, DataNascimentoInvalidException, TelefoneInvalidException, TelefoneWithoutTypeException,
      ProhibitedEmptyOrBlankAttributeException, NullAttributeNotAllowedException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String valor;

  private final String messageKey;

  protected RequestWithDataInIncorrectFormatException(String messageKey, final String valor) {
    super(messageKey);
    this.messageKey = messageKey;
    this.valor = valor;
  }

}

