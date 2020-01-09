FROM openjdk:8-alpine
RUN mkdir demoA
COPY target/1-postnotes-v1.1.jar /demoA
WORKDIR /demoA
ENTRYPOINT ["java","-jar","1-postnotes-v1.1.jar"]