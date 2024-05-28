# Estágio de construção - 1
FROM gradle:8.7.0-jdk21-alpine AS builder

# Autoria
LABEL authors="juniormartins"

# Criar e entrar no diretório
WORKDIR /compilar

# Copia os arquivos Gradle e o código-fonte
#COPY ./micro_configserver/. /compile
COPY . /compilar

# Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# Estágio de execução - 2
FROM amazoncorretto:21-alpine3.16-jdk

EXPOSE 8671

WORKDIR /executar

COPY --from=builder /compilar/build/libs/micro_eurekaserver-0.0.1-SNAPSHOT.jar app.jar

# Comandos
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/executar/app.jar"]

