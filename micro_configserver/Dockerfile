# Variáveis de tempo de construção (com valor default - opcional)
ARG IMAGE_BUILD="gradle:8.7.0-jdk21-alpine"
ARG IMAGE_RUN="amazoncorretto:21-alpine3.16-jdk"
#ARG IMAGE_BUILD_VERSION="8.7.0-jdk21-alpine"
#ARG IMAGE_RUN_VERSION="21-alpine3.16-jdk"

# 1 - Estágio de construção/build
FROM ${IMAGE_BUILD} AS builder
#FROM gradle:${IMAGE_BUILD_VERSION} AS builder
#FROM gradle:8.7.0-jdk21-alpine AS builder
#FROM ubuntu:22.04 AS builder

# Criar e entrar no diretório
WORKDIR /compilar

# Copiar todos os arquivos da raíz do projeto para o workdir
COPY . .

## Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# 2 - Estágio de execução
FROM ${IMAGE_RUN} AS runner
#FROM amazoncorretto:${IMAGE_RUN_VERSION} AS runner
#FROM amazoncorretto:21-alpine3.16-jdk AS runner

# Variáveis de tempo de construção (com valor default - opcional)
ARG APP_VERSION="1.0.0"

#Variáveis de ambiente
ENV APPLICATION="ConfigServer" \
    DEVELOPER="juniorsmartins" \
    MAINTAINER="juniorsmartins" \
    CONTATO="dev.juniorsmartins@gmail.com"

# Metadados
LABEL aplication=${APPLICATION} \
    version=${APP_VERSION} \
    description="Centraliza e gerencia configurações de microsserviços." \
    authors=${DEVELOPER} \
    maintainer=${MAINTAINER} \
    contato=${CONTATO}

#EXPOSE 8888

#HEALTHCHECK CMD curl -sf http://localhost:8888/actuator/health/readiness | grep UP || exit 1

WORKDIR /executar

COPY --from=builder /compilar/build/libs/micro_configserver-0.0.1-SNAPSHOT.jar app.jar

# Comandos
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/executar/app.jar"]

