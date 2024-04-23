package microservices.micro_customers.application.core.domain.tipos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import microservices.micro_customers.config.exception.http_400.DataNascimentoInvalidException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Getter
@ToString
@EqualsAndHashCode(of = {"dataNascimentoString", "dataNascimentoLocalDate"})
public final class DataNascimento {

    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String dataNascimentoString;

    private LocalDate dataNascimentoLocalDate;

    public DataNascimento(String dataNascimentoString) {
        Optional.ofNullable(dataNascimentoString)
            .ifPresent(data -> {
                if (!this.hasValidFormat(data)) {
                    throw new DataNascimentoInvalidException(data);
                }
                this.dataNascimentoLocalDate = LocalDate.parse(data, FORMATO_DATA);
                this.dataNascimentoString = data;
            }
        );
    }

    public DataNascimento(LocalDate dataNascimentoLocalDate) {
        Optional.ofNullable(dataNascimentoLocalDate)
            .ifPresent(data -> {
                this.dataNascimentoString = data.format(FORMATO_DATA);
                this.dataNascimentoLocalDate = data;
            }
        );
    }

    public boolean hasValidFormat(String dataNascimentoString) {
        try {
            LocalDate.parse(dataNascimentoString, FORMATO_DATA);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}

