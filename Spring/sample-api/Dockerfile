# Arquivo criado com multistage, a versao final será somente o runner

# Cria imagem da API a partir de build local do DEV
FROM openjdk:11.0.13-jdk AS runnerComCache
ARG VERSION
COPY /build/libs/ /opt/artifacts
WORKDIR /opt/artifacts
ENV APP_VERSION=${VERSION}

CMD ["sh", "-c", "java -Dspring.profiles.active=prod -jar sample-api-$APP_VERSION.jar"]

# Cria imagem da API
FROM gradle:jdk11 AS builder
COPY . /app/
WORKDIR /app
RUN echo "org.gradle.daemon=false" > /home/gradle/.gradle/gradle.properties
RUN gradle -Dorg.gradle.jvmargs="-Xmx2g -XX:MaxMetaspaceSize=1g -XX:+HeapDumpOnOutOfMemoryError" \
    -Dfile.encoding=UTF-8 \
    build

## Versao final da imagem
FROM openjdk:11.0.13-jdk AS runner
ARG VERSION
COPY --from=builder /app/build/libs/ /opt/artifacts
WORKDIR /opt/artifacts
ENV APP_VERSION=${VERSION}

CMD ["sh", "-c", "java -Dspring.profiles.active=prod -jar sample-api-$APP_VERSION.jar"]
