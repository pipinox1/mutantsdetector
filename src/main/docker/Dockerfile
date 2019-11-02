FROM openjdk:8-jdk-alpine
MAINTAINER eduardohernanpena

ADD maven/mutantsdetector-0.0.1-SNAPSHOT.jar mutantsdetector.jar
CMD sh -c 'touch /myapp.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/mutantsdetector.jar"]

