FROM openjdk:8
EXPOSE 8080
ADD target/ClientsManagement.jar ClientsManagement.jar
ENTRYPOINT ["java","-jar","/ClientsManagement.jar"]