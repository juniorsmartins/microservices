# Estágio de construção - 1
FROM gradle:jdk21-alpine AS builder

LABEL authors="juniormartins"

WORKDIR /compile

# Copia os arquivos Gradle e o código-fonte
#COPY ./micro_emails/. /compile
COPY . /compile

# Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# Estágio de execução - 2
FROM amazoncorretto:21.0.3-al2023-headless

WORKDIR /app
VOLUME /tmp

# Copia o arquivo JAR gerado pelo estágio anterior
COPY --from=builder /compile/build/libs/micro_emails-0.0.1-SNAPSHOT.jar api.jar

# Define o comando de entrada para executar o aplicativo
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/api.jar"]

