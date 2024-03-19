ARG DIRETORIO_MICRO=micro_empresas

FROM amazoncorretto:21.0.1-al2023-headful AS builder

LABEL authors="juniormartins"

ARG DIRETORIO_MICRO
WORKDIR /app/$DIRETORIO_MICRO

COPY ./$DIRETORIO_MICRO/build.gradle ./settings.gradle /app/
COPY ./$DIRETORIO_MICRO/src ./src

RUN ./gradlew clean build -x test


#COPY ./$DIRETORIO_MICRO/src ./src
#
#RUN rm -rf /app/$DIRETORIO_MICRO/build


FROM amazoncorretto:21.0.1-al2023-headful

ARG DIRETORIO_MICRO
WORKDIR /app

ARG DIRETORIO_TARGET=/app/$DIRETORIO_MICRO/build/libs

COPY --from=builder $DIRETORIO_TARGET/micro_empresas-0.0.1-SNAPSHOT.jar .


ENTRYPOINT ["java", "-jar", "micro_empresas-0.0.1-SNAPSHOT.jar"]

