#File config Docker

#Global ARG in multi-stage: we needs declared again the arg variable before de FROM in the all stage for constructeded the images
ARG MSVC_NAME=msvc-users

#Select de image base to used adn rename image
FROM amazoncorretto:18-alpine as builder

#The arguments are used as variables but only in the construction of the image
ARG MSVC_NAME

#Set a route to copy app
WORKDIR /app/$MSVC_NAME

#Copy to jar file to the path of application folder, your can used the path or "." for selected the workdir

#first copy is for the parem pom.xml
COPY ./pom.xml /app
#the second copy is maven dependency in the app to root workdir
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

#Run de maven builder jar in the root wordirk image, skip the test and compiled mvn and solo download depedency of the project
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

#Another run for builder maven dependecy is:
#RUN ./mvnw dependency:go-offline

#Run copy for src code
COPY ./$MSVC_NAME/src ./src

#Generated the jar
RUN ./mvnw clean package -DskipTests


FROM amazoncorretto:18-alpine as image-app

ARG MSVC_NAME

WORKDIR /app/

#Create folder for the copy logs from appications
RUN mkdir ./logs
#The arguments are used as variables but only in the construction of the image

ARG TARGET_FOLDER=/app/$MSVC_NAME/target
ARG PORT_APP=8001

#Copy jar from image as builder
COPY --from=builder $TARGET_FOLDER/msvc-users-0.0.1-SNAPSHOT.jar .

#Environment variables are added for the execution of the application in the container: --env or --e and
ENV PORT $PORT_APP

#Set the port to connect externar
EXPOSE $PORT

#Used de commad for start application: ENTRYPOINT is more stricter and does not allow to overwrite the execution commands.
#CMD if it allows to overwrite the startup commands
#ENTRYPOINT ["java", "-jar","msvc-users-0.0.1-SNAPSHOT.jar"]
#CMD ["java", "-jar","msvc-users-0.0.1-SNAPSHOT.jar"]
CMD sleep 20 && java -jar msvc-users-0.0.1-SNAPSHOT.jar