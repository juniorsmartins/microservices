# Variáveis de tempo de construção (com valor default - opcional)
ARG IMAGE="amazoncorretto:21-alpine3.16-jdk"

# 1 - Estágio de construção/build
FROM ${IMAGE} AS builder

# Copiar todos os arquivos da raíz do projeto (origem) para dentro da imagem (destino)
COPY . .

## Constrói o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# 2 - Estágio de execução
FROM ${IMAGE} AS runner

# Variáveis de tempo de construção (com valor default - opcional)
ARG APP_NAME
ARG APP_VERSION="v1.0.0"
ARG APP_DESCRIPTION="Microsserviço em Java e Spring Boot."

ARG DEVELOPER="juniorsmartins"
ARG MAIINTAINER="juniorsmartins"
ARG CONTATO="juniorsoaresmartins@gmail.com"

# Metadados
LABEL aplication=${APP_NAME} \
    version=${APP_VERSION} \
    description=${APP_DESCRIPTION} \
    authors=${DEVELOPER} \
    maintainer=${MAINTAINER} \
    contato=${CONTATO}

COPY --from=builder /build/libs/*.jar app.jar

# Comandos
ENTRYPOINT ["java","-jar","app.jar"]

