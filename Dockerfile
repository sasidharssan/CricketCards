FROM amazoncorretto:17-alpine3.22

RUN mkdir -p /app/cricAttacks

WORKDIR /app/cricAttacks

COPY src/ ./src/
COPY pom.xml .

RUN apk add --no-cache maven \
    net-tools \
    netcat-openbsd \
    curl

EXPOSE 8080

ENTRYPOINT ["mvn", "spring-boot:run"]

