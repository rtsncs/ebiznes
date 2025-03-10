FROM ubuntu:22.04

RUN apt-get update
RUN apt-get install -y python3 openjdk-8-jdk kotlin

WORKDIR /app

COPY . .

RUN ./gradlew build

CMD ["./gradlew", "run"]
