# Build the artifact
FROM bellsoft/liberica-openjdk-alpine:21 AS build

# Set the working directory within the container
WORKDIR /app

COPY . ./

# Ensure gradlew has execute permissions
RUN chmod +x ./gradlew

# Build the jar file
RUN ./gradlew clean bootJar -Pprod

# Run the application
FROM bellsoft/liberica-openjre-alpine:21
WORKDIR /app
COPY --from=build /app/build/libs/deposit-service*.jar deposit-service.jar
ENTRYPOINT ["java", "-jar", "deposit-service.jar"]
