# Variáveis de tempo de construção (com valor default - opcional)
#ARG IMAGE_BUILD="ubuntu:22.04"
ARG IMAGE="amazoncorretto:21-alpine3.16-jdk"

# 1 - Estágio de construção/build
FROM ${IMAGE} AS builder

## Adicionar repositório para OpenJDK 21 e instalar
#RUN apt-get update \
#    && apt-get install -y software-properties-common \
#    && add-apt-repository ppa:openjdk-r/ppa \
#    && apt-get update \
#    && apt-get install -y openjdk-21-jdk \
#    && apt-get clean \
#    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* # Remove arquivos temporários
#
## Configurar variáveis de ambiente para o Java
#ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk-amd64
#ENV PATH $JAVA_HOME/bin:$PATH

# Criar e entrar no diretório
WORKDIR /workspace/app

# Copiar todos os arquivos da raíz do projeto para o workdir
COPY . .

## Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# 2 - Estágio de execução
FROM ${IMAGE} AS runner

# Variáveis de tempo de construção (com valor default - opcional)
ARG APP
ARG APP_VERSION="v1.0.0"
ARG APP_DESCRIPTION="Microsserviço em Java e Spring Boot."

#Variáveis de ambiente
ENV DEVELOPER="juniorsmartins" \
    MAINTAINER="juniorsmartins" \
    CONTATO="juniorsoaresmartins@gmail.com"

# Metadados
LABEL aplication=${APP} \
    version=${APP_VERSION} \
    description=${APP_DESCRIPTION} \
    authors=${DEVELOPER} \
    maintainer=${MAINTAINER} \
    contato=${CONTATO}

WORKDIR /workspace/app

COPY --from=builder /workspace/app/build/libs/*.jar app.jar

# Comandos
ENTRYPOINT ["java","-jar","/workspace/app/app.jar"]

