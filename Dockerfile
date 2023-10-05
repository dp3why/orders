# Stage 1: Build the WAR file using Maven
FROM maven:3.8.3-openjdk-8 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the WAR file
RUN mvn clean package -DskipTests

# Stage 2: Create the final image with Tomcat and the WAR file
FROM tomcat:9.0-jre8

# Copy the WAR file from the builder stage into the Tomcat webapps directory
COPY --from=builder /app/target/serverletpro.war /usr/local/tomcat/webapps/

# Expose the port that Tomcat listens on
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
