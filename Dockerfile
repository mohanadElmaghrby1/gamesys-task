FROM openjdk:11

LABEL Name="gamesys-task"
LABEL Maintainer="https://www.linkedin.com/in/mohanadelmaghrby1/"

COPY ./target/gamesys-task.jar gamesys-task.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/gamesys-task.jar"]
