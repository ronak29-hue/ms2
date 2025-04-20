#imagr informtion

#base image
FROM openjdk:21

#maintainer name
#MAINTAINER krishna@creator.com

#set working directory

#cpoy the jar fiele to container
COPY target/notification.service-0.0.1-SNAPSHOT.jar notification.service-0.0.1-SNAPSHOT.jar

#run the application-- container run from entry point
ENTRYPOINT ["java","-jar","notification.service-0.0.1-SNAPSHOT.jar"]
