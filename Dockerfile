FROM openjdk:8-alpine
RUN mkdir demoA
COPY target/1__PostNotes-0.0.1-SNAPSHOT.jar /demoA
WORKDIR /demoA
ENTRYPOINT ["java","-jar","1__PostNotes-0.0.1-SNAPSHOT.jar"]