package microservices.micro_customers.util;

import microservices.micro_customers.entity.CustomerEntity;
import net.datafaker.Faker;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

// Padrão Object Mother
public final class FactoryObjectMother {

    private static FactoryObjectMother singletonFactory;

    private final Faker faker = new Faker();

    private FactoryObjectMother() { }

    // Padrão Singleton
    public static synchronized FactoryObjectMother singleton() {
        if (singletonFactory == null) {
            singletonFactory = new FactoryObjectMother();
        }
        return singletonFactory;
    }

    public static String gerarString(int numeroCaracteres) {
        return RandomStringUtils.randomAlphabetic(numeroCaracteres);
    }

    // Padrão Builder
    public CustomerEntity.CustomerEntityBuilder gerarCustomerEntityBuilder() {
        return CustomerEntity.builder()
            .nomeCompleto(faker.lorem().characters(20, 100))
            .cpf(faker.cpf().valid())
            .email(faker.internet().emailAddress());
    }

}

