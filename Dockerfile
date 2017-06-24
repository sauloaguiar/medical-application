
# chose a container
FROM openjdk:8-jre

# copy jar
COPY build/libs/demo-0.0.1-SNAPSHOT.jar /usr/local/bin/application.jar

# expose the port
EXPOSE 8080

# execute
ENTRYPOINT ["java", "-jar", "/usr/local/bin/application.jar"]

# check on array syntax for entrypoint