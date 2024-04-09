package microservices.micro_customers.config.exception.http_409;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class BusinessRuleViolationException extends RuntimeException permits CpfDuplicityException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String messageKey;

  private final String valor;

  protected BusinessRuleViolationException(String messageKey) {
    super(messageKey);
    this.messageKey = messageKey;
    this.valor = null;
  }

  protected BusinessRuleViolationException(String messageKey, String valor) {
    super(messageKey);
    this.messageKey = messageKey;
    this.valor = valor;
  }

}

