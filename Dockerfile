FROM openjdk:latest as Application
COPY ./target/semCW.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semCW.jar", "db:3306", "30000"]