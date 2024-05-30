# Variáveis de tempo de construção (com valor default - opcional)
ARG IMAGE_BUILD="gradle:8.7.0-jdk21-alpine"
ARG IMAGE_RUN="amazoncorretto:21-alpine3.16-jdk"

# 1 - Estágio de construção/build
FROM ${IMAGE_BUILD} AS builder

# Adicionar usuário no Sistema Operacional (neste caso, Linux - O parâmetro -D define usuário sem senha)
RUN adduser -D juniorsmartins

# Definir usuário responsável pelos próximos RUNs (o inicial/anterior é feito pelo usuário Root)
USER juniorsmartins

# Criar e entrar no diretório
WORKDIR /compilar

# Copiar todos os arquivos da raíz do projeto para o workdir
COPY . .

## Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# 2 - Estágio de execução
FROM ${IMAGE_RUN} AS runner

# Variáveis de tempo de construção (com valor default - opcional)
ARG APP_VERSION="1.0.0"

#Variáveis de ambiente
ENV APP="ConfigServer" \
    DEVELOPER="juniorsmartins" \
    MAINTAINER="juniorsmartins" \
    CONTATO="dev.juniorsmartins@gmail.com"

# Metadados
LABEL aplication=${APP} \
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

