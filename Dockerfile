FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ARG name
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
CMD ["--spring.profiles.active=secrets"]
