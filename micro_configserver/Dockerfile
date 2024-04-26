# Estágio de construção - 1
FROM gradle:8.7.0-jdk21-alpine AS builder

# Autoria
LABEL authors="juniormartins"

WORKDIR /compile

# Copia os arquivos Gradle e o código-fonte
#COPY ./micro_configserver/. /compile
COPY . /compile

# Compila o projeto com o Gradle
RUN ./gradlew clean build -x test --no-daemon


# Estágio de execução - 2
FROM amazoncorretto:21.0.3-al2023-headless

WORKDIR /app
VOLUME /tmp

COPY --from=builder /compile/build/libs/micro_configserver-0.0.1-SNAPSHOT.jar api.jar

#Execute a aplicação
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/api.jar"]

