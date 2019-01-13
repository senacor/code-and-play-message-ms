FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG DEPENDENCY=target/dependency

RUN addgroup -S app \
  && adduser -S -G app app \

USER app

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

EXPOSE 8080

ENTRYPOINT ["java",
  "-cp",
  "app:app/lib/*",
  "com.senacor.cap.service.message.Application"]
