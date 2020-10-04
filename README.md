# Emkan Customer
This service is used to communicate with the USER Database. It manages the CRUD operations like create, update, reset password and more, on the database.

# Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
These are the prerequisites you need to install in order to have the project up and running on your local machine:
 - IntelliJ-IDEA (or any other related IDE)
 - JDK 1.8
 - Maven

### Versioning
We use  **BitBucket** for versioning. The project can be cloned from [here](https://arb-bitbucket.devops.alrajhi.bank/projects/EMKAN/repos/emkan-id/browse).
 
### Deployment
This application is packaged as a WAR file that can be deployed on IBM Websphere. There are currently two servers where the app can be deployed: DEV and SIT

###Packaging
In order to run the build run the following command:
 - Generating the war for the DEV server run:  ```mvn clean install -Pdev```
 - Generating the war for the SIT server run:  ```mvn clean install -Psit```

### Connection details
The connection details are set in the `.yml` files.

### Testing
In order to run the tests for the application run the following command : ```mvn test```