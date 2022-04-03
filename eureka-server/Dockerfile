FROM openjdk:11-jre-slim
COPY target/*.jar /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]