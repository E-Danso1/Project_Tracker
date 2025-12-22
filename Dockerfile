FROM openjdk:26-jdk
ADD target/Project_Tracker-0.0.1-SNAPSHOT.jar Project_Tracker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/Project_Tracker-0.0.1-SNAPSHOT.jar"]