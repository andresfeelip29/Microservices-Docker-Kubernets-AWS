#File config docker

#Select de image to used
FROM amazoncorretto:18

#Set a route to copy app
WORKDIR /app

#Copy to jar file to the path of application folder, your can used the path or "."
COPY ./target/msvc-users-0.0.1-SNAPSHOT.jar .

#Set the port to connect externar
EXPOSE 8001

#Used de commad for start application
ENTRYPOINT ["java", "-jar","msvc-users-0.0.1-SNAPSHOT.jar"]