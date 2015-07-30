Crossover Challenge for Markus Kopinits

The development environment configuration was:

JDK7 (http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk7-downloads-1880260.html)
Apache Maven 3.1.1 (https://maven.apache.org/download.cgi)
MongoDB (https://www.mongodb.org/downloads)

About MongoDB:

It's necessary to install mongoDB.
Linux: https://docs.mongodb.org/manual/administration/install-on-linux/
Windows: https://docs.mongodb.org/manual/tutorial/install-mongodb-on-windows/

Install it on root path.

After installation, create this structure:
/data/db

It's where all data will be stored by mongodb.

After instalation, start the mongodb server
/{installDir}/bin/mongod.exe

Commands to run and install the application:

git clone https://github.com/kopinits/crossoverCHL
cd .\crossoverCHL
mvn clean install
mvn spring-boot:run

Services:
To test the services, I recommend using GoogleChrome plugin Advanced RestClient.
All fields are required to save the logdata

Save LogData
http://localhost:8034/markusCHL/saveLog

Input format:
{"appCode":"gu4a2","timestamp":1438204527704,"logType":"CSTM_PRDT_VIEW","dataLogged":"Iphone 6","custumerID":"10023FA34"}

Query LogData
http://localhost:8034/markusCHL/queryLog

Input format:
{"timestampFrom":1438204527704,"timestampTo":1438204527704,"logType":"CSTM_PRDT_VIEW","custumerID":"10023FA34"}

The logType has a list of valid data. They are:
	CSTM_PRDT_VIEW
	CSTM_SRCH_DONE
	APPL_INFO_LOG
	APPL_WARN_LOG
	APPL_DEBUG_LOG
	APPL_ERROR_LOG
	APPL_FATAL_LOG

The logType CSTM_PRDT_VIEW is for all products that was saw by the custumers
The logType CSTM_SRCH_DONE is for all searches made by the custumers
The logType APPL_INFO_LOG is for all INFO logs raised by the application
The logType APPL_WARN_LOG is for all WARN logs raised by the application
The logType APPL_DEBUG_LOG is for all DEBUG logs raised by the application
The logType APPL_ERROR_LOG is for all ERROR logs raised by the application
The logType APPL_FATAL_LOG is for all FATAL logs raised by the application


Final Consideratioins:

 The file /crossoverCHL/src/main/resources/application.properties
 and /crossoverCHL/src/test/resources/application.properties, has informations
 related mongodb configurations and the application server port used.

Any extra different settings of the following must be changed
  the files reported above:

 server.port = 8034
 server.contextPath=/markusCHL
 mongodb.host = localhost
 mongodb.port = 27017
 mongodb.database = crossover