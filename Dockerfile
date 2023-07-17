FROM gradle:8.2.1-jdk11-alpine as java-build

MAINTAINER Wenderson

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

EXPOSE 9001
ENTRYPOINT ["java","-jar","build/libs/desafio-dev-0.0.1-SNAPSHOT.jar"]