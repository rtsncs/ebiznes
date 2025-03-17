FROM sbtscala/scala-sbt:eclipse-temurin-21.0.6_7_1.10.10_3.3.5

RUN curl -sSL https://ngrok-agent.s3.amazonaws.com/ngrok.asc \
  | tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null \
  && echo "deb https://ngrok-agent.s3.amazonaws.com buster main" \
  | tee /etc/apt/sources.list.d/ngrok.list \
  && apt update \
  && apt install ngrok

WORKDIR /app
COPY . /app

RUN sbt compile

CMD ./start.sh
