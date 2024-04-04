package microservices.micro_customers.application.core.domain.tipos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import microservices.micro_customers.config.exception.http_400.CpfInvalidException;

import java.io.Serializable;

@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"cpf"})
public class CadastroPessoaFisica implements Serializable {

    private String cpf;

    public CadastroPessoaFisica(String cpf) {
        if (!this.hasValidFormat(cpf)) {
            throw new CpfInvalidException(cpf);
        }
        this.cpf = cpf;
    }

    public boolean hasValidFormat(String cpf) {

        // Remove qualquer caractere que não seja dígito (0-9)
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se o CPF possui 11 dígitos após a remoção de caracteres não numéricos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (não é um CPF válido)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int remainder = sum % 11;
        int firstVerifier = (remainder < 2) ? 0 : 11 - remainder;

        // Verifica o primeiro dígito verificador
        if (Character.getNumericValue(cpf.charAt(9)) != firstVerifier) {
            return false;
        }

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        remainder = sum % 11;
        int secondVerifier = (remainder < 2) ? 0 : 11 - remainder;

        // Verifica o segundo dígito verificador
        return Character.getNumericValue(cpf.charAt(10)) == secondVerifier;
    }

}

