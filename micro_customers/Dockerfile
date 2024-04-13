# Estágio de construção - 1
FROM gradle:jdk21-alpine AS builder

LABEL authors="juniormartins"

WORKDIR /compile

# Copia os arquivos Gradle e o código-fonte
COPY ./micro_customers/. /compile

# Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# Estágio de execução - 2
FROM amazoncorretto:21.0.1-al2023-headful

WORKDIR /app
VOLUME /tmp

COPY --from=builder /compile/build/libs/micro_customers-0.0.1-SNAPSHOT.jar api.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/api.jar"]