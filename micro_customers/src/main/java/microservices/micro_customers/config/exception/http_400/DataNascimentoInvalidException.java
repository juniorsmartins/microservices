package microservices.micro_customers.config.exception.http_400;

public final class DataNascimentoInvalidException extends PoorlyRequestFormulatedException {

  public static final long serialVersionUID = 1L;

  public DataNascimentoInvalidException(String dataNascimento) {
    super("exception.request.format.invalid.data.nascimento", dataNascimento);
  }

}

