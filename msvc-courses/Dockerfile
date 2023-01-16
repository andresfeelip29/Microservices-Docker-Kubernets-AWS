#File config docker

#Select de image base to used adn rename image
FROM amazoncorretto:18-alpine as builder

#Set a route to copy app
WORKDIR /app/msvc-courses

#Copy to jar file to the path of application folder, your can used the path or "." fro selected the workdir

#first copy is for the parem pom.xml
COPY ./pom.xml /app
#the second copy is maven dependency in the app to root workdir
COPY ./msvc-courses/.mvn ./.mvn
COPY ./msvc-courses/mvnw .
COPY ./msvc-courses/pom.xml .

#Run de maven builder jar in the root wordirk image, skip the test and compiled mvn and solo download depedency of the project
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

#Another run for builder maven dependecy is:
#RUN ./mvnw dependency:go-offline

#Run copy for src code
COPY ./msvc-courses/src ./src

#Generated the jar
RUN ./mvnw clean package -DskipTests


FROM amazoncorretto:18-alpine as image-app

WORKDIR /app/

#Create folder for the copy logs from appications
RUN mkdir ./logs

#Copy jar from image as builder
COPY --from=builder /app/msvc-courses/target/msvc-courses-0.0.1-SNAPSHOT.jar .

#Set the port to connect externar
EXPOSE 8001

#Used de commad for start application: ENTRYPOINT is more stricter and does not allow to overwrite the execution commands.
#CMD if it allows to overwrite the startup commands
#ENTRYPOINT ["java", "-jar","msvc-courses-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar","msvc-courses-0.0.1-SNAPSHOT.jar"]