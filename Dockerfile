FROM khipu/openjdk17-alpine
ARG JAR_FILE=target/letters-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]