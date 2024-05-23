# 1 - Estágio de construção
FROM gradle:8.7.0-jdk21-alpine AS builder

# Autoria
LABEL authors="juniormartins"

WORKDIR /compile

# Copia os arquivos Gradle e o código-fonte
#COPY ./micro_configserver/. /compile
COPY . /compile

# Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon

# 2 - Estágio de execução
FROM openjdk:23-oraclelinux9

WORKDIR /app
#VOLUME /tmp

COPY --from=builder /compile/build/libs/micro_configserver-0.0.1-SNAPSHOT.jar app.jar

#Execute a aplicação
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]

