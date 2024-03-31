package microservices.micro_empresas.config.exception.http_404;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract class RecursoNotFoundException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final Long id;

  private final String messageKey;

  protected RecursoNotFoundException(String messageKey, final Long id) {
    super(messageKey);
    this.messageKey = messageKey;
    this.id = id;
  }

}

