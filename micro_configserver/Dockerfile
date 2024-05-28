# 1 - Estágio de construção/build
FROM gradle:8.7.0-jdk21-alpine AS builder

# Autoria
LABEL authors="juniorsmartins"

# Criar volume para armazenar dados
#VOLUME [ "/app" ]

#RUN adduser -h /app \ -s /bin/bash \ -D app

# Criar e entrar no diretório
WORKDIR /compilar

# Copiar todos os arquivos da raíz do projeto para o diretório app
COPY . /compilar

# Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon

# 2 - Estágio de execução (Essa imagem foi a menor que achei com Java e com menos vulnerabilidades)
FROM amazoncorretto:21-alpine3.16-jdk

WORKDIR /executar

COPY --from=builder /compilar/build/libs/micro_configserver-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8888

# Comandos que serão executados quando a imagem gerar container
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/executar/app.jar"]

