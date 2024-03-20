ARG BUILD_HOME=micro_empresas

# Estágio de construção
FROM gradle:jdk21-alpine AS build-image

LABEL authors="juniormartins"

ARG BUILD_HOME
WORKDIR $BUILD_HOME

# Cria o diretório build/libs
RUN mkdir -p ./build/libs

# Copia os arquivos Gradle e o código-fonte
COPY ./$BUILD_HOME/gradle ./gradle
COPY ./$BUILD_HOME/build.gradle ./$BUILD_HOME/settings.gradle ./$BUILD_HOME/gradlew ./
COPY ./$BUILD_HOME/src src
#COPY ./$BUILD_HOME/. .

# Compila o projeto com o Gradle
RUN ./gradlew clean build -x test
#RUN ./gradlew clean bootJar

COPY ./$BUILD_HOME/build/libs/*.jar ./build/libs


# Estágio de execução
#FROM gradle:jdk21-alpine
FROM gradle:jdk21-alpine

ARG BUILD_HOME

# Copia o arquivo JAR gerado pelo estágio anterior
COPY --from=build-image $BUILD_HOME/build/libs/*.jar app.jar

# Define o comando de entrada para executar o aplicativo
ENTRYPOINT java -jar app.jar

