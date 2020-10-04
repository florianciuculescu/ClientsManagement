# Getting Started
These instructions will get you a copy of the project up and running on your local machine:

* You will need to clone the project from [here](https://github.com/florianciuculescu/ClientsManagement)  
* After step 1 has been completed, open terminal and navigate to the directory where you have cloned the project with "cd" commands  
* Once reached the directory, run the following commands:  

&nbsp;&nbsp;&nbsp;&nbsp; 1. docker build -t clientsmanagement.jar .  
&nbsp;&nbsp;&nbsp;&nbsp; 2. docker image ls (this will show you all your images in docker)  
&nbsp;&nbsp;&nbsp;&nbsp; 3. docker run -p 9090:8080 clientsmanagement.jar

* Once the Spring Boot app has been completed, you should be able to access it at http://localhost:9090