package microservices.micro_customers.config.exception.http_400;

import java.io.Serial;

public final class DataNascimentoInvalidException extends RequestWithDataInIncorrectFormatException {

  @Serial
  private static final long serialVersionUID = 1L;

  public DataNascimentoInvalidException(String dataNascimento) {
    super("exception.request.format.invalid.data.nascimento", dataNascimento);
  }

}

