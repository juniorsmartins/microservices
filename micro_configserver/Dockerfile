# Variáveis de tempo de construção (com valor default - opcional)
ARG IMAGE_BUILD="ubuntu:22.04"
#ARG IMAGE_RUN="amazoncorretto:21-alpine3.16-jdk"
ARG IMAGE_RUN="ubuntu:22.04"

# 1 - Estágio de construção/build
FROM ${IMAGE_BUILD} AS builder

# Adicionar repositório para OpenJDK 21 e instalar
RUN apt-get update \
    && apt-get install -y software-properties-common \
    && add-apt-repository ppa:openjdk-r/ppa \
    && apt-get update \
    && apt-get install -y openjdk-21-jdk \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* # Remove arquivos temporários

# Configurar variáveis de ambiente para o Java
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
ENV PATH $JAVA_HOME/bin:$PATH

# Criar e entrar no diretório
WORKDIR /compilar

# Copiar todos os arquivos da raíz do projeto para o workdir
COPY ./gradle ./gradle
COPY ./build.gradle .
COPY ./gradlew .
COPY ./settings.gradle .
COPY ./src ./src

## Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# 2 - Estágio de execução
FROM ${IMAGE_BUILD} AS runner

# Variáveis de tempo de construção (com valor default - opcional)
ARG APP="configserver"
ARG APP_VERSION="1.0.0"

#Variáveis de ambiente
ENV DEVELOPER="juniorsmartins" \
    MAINTAINER="juniorsmartins" \
    CONTATO="juniorsoaresmartins@gmail.com"

# Metadados
LABEL aplication=${APP} \
    version=${APP_VERSION} \
    description="Centraliza e gerencia configurações de microsserviços." \
    authors=${DEVELOPER} \
    maintainer=${MAINTAINER} \
    contato=${CONTATO}

RUN apt-get update \
    && apt-get install -y openjdk-21-jdk

WORKDIR /executar

COPY --from=builder /compilar/build/libs/micro_configserver-0.0.1-SNAPSHOT.jar app.jar

# Comandos
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/executar/app.jar"]

