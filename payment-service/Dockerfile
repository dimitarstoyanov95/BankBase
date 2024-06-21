# Build the artifact
FROM bellsoft/liberica-openjdk-alpine:21 AS build

## Copy project
COPY . ./

## Build the jar file
RUN ./gradlew clean bootJar -Pprod

# Run the application
FROM bellsoft/liberica-openjre-alpine:21
COPY --from=build /payment-service/build/libs/payment-service*.jar payment-service.jar
ENTRYPOINT exec java $JAVA_OPTS -jar payment-service.jar $ARGS