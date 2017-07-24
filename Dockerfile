
# chose a container
FROM openjdk:8-jre

# mount volume to host application data
VOLUME ["/usr/local/application-data"]

# copy jar file
COPY build/libs/medicalApp-0.0.1.jar /usr/local/application-data/medicalApp.jar

# expose application port
EXPOSE 8080

# execute the script
ENTRYPOINT ["java", "-jar" ,"/usr/local/application-data/medicalApp.jar"]