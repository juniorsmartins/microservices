package microservices.micro_customers.exception.http_409;

import lombok.Getter;
import microservices.micro_customers.exception.http_400.CpfInvalidxception;

import java.io.Serial;

@Getter
public abstract class BusinessRuleViolationException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String messageKey;

  protected BusinessRuleViolationException(String messageKey) {
    super(messageKey);
    this.messageKey = messageKey;
  }

}

