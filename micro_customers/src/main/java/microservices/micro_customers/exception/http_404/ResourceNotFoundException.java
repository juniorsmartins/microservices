package microservices.micro_customers.exception.http_404;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract class ResourceNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final Long id;

  private final String messageKey;

  protected ResourceNotFoundException(String messageKey, final Long id) {
    super(messageKey);
    this.messageKey = messageKey;
    this.id = id;
  }

}

