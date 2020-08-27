FROM ubuntu:bionic

RUN apt-get update && \
    apt-get install -y \
    openjdk-8-jre

COPY target/vending-machine-application-0.0.1-SNAPSHOT.jar ./vending-machine.jar

CMD java -Dserver.port=8080 -jar ./vending-machine.jar
