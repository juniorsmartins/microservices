package microservices.micro_customers.domain.tipos;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"dataNascimento"})
public final class DataNascimento {

    private LocalDate dataNascimento;

}

