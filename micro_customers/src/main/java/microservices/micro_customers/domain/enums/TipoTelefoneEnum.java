package microservices.micro_customers.domain.enums;

public enum TipoTelefoneEnum {

    FIXO("FIXO"),

    CELULAR("CELULAR");

    private final String value;

    TipoTelefoneEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}

