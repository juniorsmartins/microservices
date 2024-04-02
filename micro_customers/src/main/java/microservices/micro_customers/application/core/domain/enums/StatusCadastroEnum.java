package microservices.micro_customers.application.core.domain.enums;

public enum StatusCadastroEnum {

    INICIADO("INICIADO"),

    CONCLUIDO("CONCLUIDO");

    private final String value;

    StatusCadastroEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}

