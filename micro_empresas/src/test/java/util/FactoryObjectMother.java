package util;

import microservices.micro_empresas.adapter.in.controller.dto.request.EmpresaCreateDtoRequest;
import microservices.micro_empresas.adapter.out.repository.entity.EmpresaEntity;
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
    public EmpresaEntity.EmpresaEntityBuilder gerarEmpresaEntityBuilder() {
        return EmpresaEntity.builder()
            .nome(faker.lorem().characters(20, 70));
    }

    public EmpresaCreateDtoRequest.EmpresaCreateDtoRequestBuilder gerarEmpresaCreateDtoRequest() {
        return EmpresaCreateDtoRequest.builder()
            .nome(faker.lorem().characters(20, 70));
    }


}

