# Use a multi-stage build to separate test and build stages

# Stage 1: Test
FROM maven:3.8.5-openjdk-17 AS maven
COPY ./pom.xml ./pom.xml
# build all dependencies for offline use
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package

# Stage 2: Build
FROM maven:3.8.5-openjdk-17 AS build

# set deployment directory
WORKDIR /app

# copy over the built artifact from the maven image
COPY --from=maven target/fiar.jar ./

EXPOSE 8080

# set the startup command to run your binary
CMD ["java", "-jar", "./fiar.jar"]