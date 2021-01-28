# Choonz - Team 4
This repo contains a springboot system which allows the end user to create an API and test it using the front end system provided. The user can create, update, read and delete records from the system.

Tools used:
* Java
* Maven
* Spring Boot
* JUnit
* MySQL
* HTML / CSS / JS

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run the file you will need to install the latest version of Java & Maven at the link bellow.

The Software you will need to install

```
IDE - Spring Tools 4 (https://spring.io/tools)
JAVA - Java SE Development Kit (https://www.oracle.com/uk/java/technologies/javase/javase-jdk8-downloads.html)
Database - MySQL (https://dev.mysql.com/downloads/mysql/5.7.html)
Maven - Apache Maven (https://maven.apache.org/download.cgi?Preferred=ftp://ftp.osuosl.org/pub/apache/)
Performance Testing - JMeter (https://jmeter.apache.org/download_jmeter.cgi)
```

### Installing

Navigate to where you have downloaded the jar file then right click and open bash

![open bash screenshot](https://github.com/FayazPanda/To-Do-List/blob/main/images/tut1.png)

Then type **java -jar fat.jar** replacing the fat with whatever you might have renamed it to

![run command screenshot](https://github.com/FayazPanda/To-Do-List/blob/main/images/tut2.png)

Once running, 'Springboot' should appear in ascII text at the start and the system will be fully booted once you see the line ''To-Do List API Initialised''

![Entity usage Screenshot](https://github.com/FayazPanda/To-Do-List/blob/main/images/tut3.png)

## Running the tests

The following section will explain the tests, what they are and how you can run them.

### Unit Tests 

Units tests are to check the functionality of code in a vaccum to see if they work on their own. These tests are set up in `...\src\test\com\qa\demo\rest` and can be run using JUnit with Maven.

### Integration Tests 

Integration tests are to check the functionality of code when used with other aspects. These tests are setup in `...\src\test\com\qa\demo\rest` directory. These use JUnit and Mockito with Maven to run.

### Website Tests

These tests are conducted using selenium and an appropriate driver for the web browser you are testing on. In my case I am using Google Chrome. The testing will check all intractable aspects of the webpage to verify it is working correctly. This can be found at : `...\src\test\com\qa\demo\selenium\pages\demosite`

### Non-Functional Tests

JMeter tests are present at `\JMeter Tests` folder. There are batch files present which will run the respective tests, please note the time in minutes it could take
in the filename when running the tests. 

JMeter needs to be installed and added to windows system environment variables for the batch files to work. The batch files are created 
for utility. Tests can also be run and edited manually using JMeter GUI and runnin .jsx test files.

## Deployment

Deployment to live system requires constant update for the fat.jar system with every update of the system. Otherwise deployment is the same as installation.

## Future Development

For future development you will need to install maven, java and IDE for Java(Spring Tool Suite or Intellij recommended). For website development I recommend an IDE for Websites(Visual Studio or Webstorm) 

```
https://www.java.com/en/download/
http://maven.apache.org/download.cgi
https://www.eclipse.org/
```

## ERD

TBD

## UML

TBD

## Authors

### Training Team

- **Client** - [**Angelica Charry**](https://github.com/acharry) - **Software Delivery Manager**
- **Product Owner** - [**Nick Johnson**](https://github.com/nickrstewarttds) - **Initial work (backend & frontend development, specification)**
- **Product Owner** - [**Edward Reynolds**](https://github.com/Edrz-96) - **Initial work (testing, specification)**
- [**Jordan Harrison**](https://github.com/JHarry444) - **General Java wizardry**
- [**Alan Davies**](https://github.com/MorickClive)
- [**Savannah Vaithilingham**](https://github.com/savannahvaith)
- [**Vinesh Ghela**](https://github.com/vineshghela)
- [**Piers Barber**](https://github.com/PCMBarber)

### Development Team

- Fayaz Sheikh **UX Lead**
- Usama Malik **Integration Developer**
- Harry Fresco **Spring Developer**

## Acknowledgements

Spring Boot Tutorials - https://www.baeldung.com/
