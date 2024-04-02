package microservices.micro_customers.domain.tipos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import microservices.micro_customers.exception.http_400.DataNascimentoInvalidException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@ToString
@EqualsAndHashCode(of = {"dataNascimentoString", "dataNascimentoLocalDate"})
public final class DataNascimento {

    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String dataNascimentoString;

    private LocalDate dataNascimentoLocalDate;

    public DataNascimento(String dataNascimentoString) {
        if (!this.ehValido(dataNascimentoString)) {
            throw new DataNascimentoInvalidException(dataNascimentoString);
        }
        this.dataNascimentoLocalDate = LocalDate.parse(dataNascimentoString, FORMATO_DATA);
    }

    public DataNascimento(LocalDate dataNascimentoLocalDate) {
        this.dataNascimentoString = dataNascimentoLocalDate.format(FORMATO_DATA);
    }

    public boolean ehValido(String dataNascimentoString) {
        try {
            LocalDate.parse(dataNascimentoString, FORMATO_DATA);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}

