FROM openjdk:latest as Application
COPY ./target/cw-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "cw-0.1.0.2-jar-with-dependencies.jar"]