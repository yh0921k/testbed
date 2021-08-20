FROM ghcr.io/graalvm/graalvm-ce:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djasypt.encryptor.password=[Input Password]","-jar","/app.jar"]
