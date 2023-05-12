### Tests if everything works on docker container ###

# Use a Linux base image
FROM ubuntu:latest

# Install Java 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk

# Install Maven
RUN apt-get install -y maven

# Copy directory to container's app folder
COPY . /app
WORKDIR /app

# Make your scripts executable
RUN chmod +x /app/docker-scripts-wrapper.sh

# Set the entrypoint to execute both scripts
ENTRYPOINT ["/app/docker-scripts-wrapper.sh"]