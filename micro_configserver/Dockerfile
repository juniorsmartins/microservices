# 1 - Estágio de construção/build
FROM gradle:8.7.0-jdk21-alpine AS builder
#FROM ubuntu:22.04 AS builder

ENV DEVELOPER="juniorsmartins" \
    MAINTAINER="juniorsmartins" \
    APPLICATION="ConfigServer"

# Autoria
LABEL authors=${DEVELOPER} \
    maintainer=${MAINTAINER} \
    description="O ${APPICATION} centraliza e gerencia configurações de microsserviços."

# Criar e entrar no diretório
WORKDIR /compilar

# Copiar todos os arquivos da raíz do projeto para o diretório app
COPY . ./compilar

## Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# 2 - Estágio de execução (Essa imagem foi a menor que achei com Java e com menos vulnerabilidades)
FROM amazoncorretto:21-alpine3.16-jdk AS runner

#EXPOSE 8888

#HEALTHCHECK CMD curl -sf http://localhost:8888/actuator/health/readiness | grep UP || exit 1

WORKDIR /executar

#ARG VERSION
#ENV VERSION=${VERSION:-v1.0.0}

COPY --from=builder /compilar/build/libs/micro_configserver-0.0.1-SNAPSHOT.jar app.jar

# Comandos
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/executar/app.jar"]

