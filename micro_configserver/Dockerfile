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

# Atualizar os repositórios e instalar dependências básicas
#RUN apt-get update && apt-get install -y \
#    curl \
#    unzip \
#    wget \
#    gnupg \
#    && rm -rf /var/lib/apt/lists/*

# Adicionar repositório para OpenJDK 21 e instalar
#RUN apt-get update && \
#    apt-get install -y software-properties-common && \
#    add-apt-repository ppa:openjdk-r/ppa && \
#    apt-get update && \
#    apt-get install -y openjdk-21-jdk

#RUN <<EOF apt-get update &&
#    apt-get install -y software-properties-common
#    add-apt-repository ppa:openjdk-r/ppa
#    apt-get update
#    apt-get install -y openjdk-21-jdk
#EOF

# Configurar variáveis de ambiente para o Java
#ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
#ENV PATH $JAVA_HOME/bin:$PATH

# Verificar a instalação do Java
#RUN java -version

# Instalar o Gradle
#RUN wget https://services.gradle.org/distributions/gradle-8.0-bin.zip -P /tmp \
#    && unzip /tmp/gradle-8.0-bin.zip -d /opt \
#    && rm /tmp/gradle-8.0-bin.zip
#
#ENV GRADLE_HOME /opt/gradle-8.0
#ENV PATH $GRADLE_HOME/bin:$PATH

# Verificar a instalação do Gradle
#RUN gradle -v

# Criar volume para armazenar dados
#VOLUME [ "/app" ]

#RUN adduser -h /app \ -s /bin/bash \ -D app

# Criar e entrar no diretório
WORKDIR /compilar

# Copiar todos os arquivos da raíz do projeto para o diretório app
COPY . ./compilar
#COPY ./src ./compilar
#COPY ./build.gradle ./compilar
#COPY ./build.gradle .

## Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon
#RUN gradle build


# 2 - Estágio de execução (Essa imagem foi a menor que achei com Java e com menos vulnerabilidades)
FROM amazoncorretto:21-alpine3.16-jdk AS runner

#ARG PORT
#ENV PORT=${PORT:8888}

EXPOSE 8888

HEALTHCHECK CMD curl -sf http://localhost:8888/actuator/health/readiness | grep UP || exit 1

WORKDIR /executar

ARG VERSION
ENV VERSION=${VERSION:-v1.0.0}

COPY --from=builder /compilar/build/libs/micro_configserver-0.0.1-SNAPSHOT.jar app.jar

# Comandos
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/executar/app.jar"]

