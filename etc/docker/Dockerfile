# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER Arpan Khandelwal

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/person-management-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} person-management-app.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/person-management.jar"]

# TODOs:
# Execute script to start application instead of running jar
# Take env variable and arguments for jar
# Store application dependencies at different location
# Use docker composer

#ENTRYPOINT ["/entrypoint.sh"]