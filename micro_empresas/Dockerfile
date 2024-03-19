ARG BUILD_HOME=micro_empresas

# Estágio de construção
FROM gradle:jdk21-alpine AS build-image

LABEL authors="juniormartins"

ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

# Copia os arquivos Gradle e o código-fonte
COPY ./$BUILD_HOME/gradle ./gradle
COPY ./$BUILD_HOME/build.gradle ./$BUILD_HOME/settings.gradle ./$BUILD_HOME/gradlew ./
COPY ./$BUILD_HOME/src src

# Compila o projeto com o Gradle
RUN ./gradlew clean build -x test


# Estágio de execução
FROM gradle:jdk21-alpine

ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME

# Copia o arquivo JAR gerado pelo estágio anterior
COPY --from=build-image ./$APP_HOME/build/libs/micro_empresas-0.0.1-SNAPSHOT-plain.jar app.jar

# Define o comando de entrada para executar o aplicativo
ENTRYPOINT java -jar app.jar

