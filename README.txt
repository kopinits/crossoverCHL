Crossover Challenge for Markus Kopinits

Server used:
JDK  version used:1.7
Maven version used: 3.1.1

All test cases can run trhough: mvn test

Tpo test the services, I recommend using GoogleChrome plugin RestEasy.

There are validantion related to field required. All fields are required
To register a new log, the input format is:
{
   "appCode": "gu4a",
    "timestamp": 1438133302135,
    "logType": "CUSTUMER_PRODUCT_VIEW",
    "dataLogged": "Iphone 6"
}

To make a query for geristered logdata, the input format is:
{
    "logType": "CUSTUMER_PRODUCT_VIEW",
}

The logType has a list of valid data. They are:
	CUSTUMER_PRODUCT_VIEW
	CUSTUME_SEARCH_DONE
	APPL_INFO_LOG
	APPL_WARN_LOG
	APPL_DEBUG_LOG
	APPL_ERROR_LOG
	APPL_FATAL_LOG

The logType CUSTUMER_PRODUCT_VIEW is for all products that was saw by the custumers
The logType CUSTUME_SEARCH_DONE is for all searches made by the custumers
The logType APPL_INFO_LOG is for all INFO logs raised by the application
The logType APPL_WARN_LOG is for all WARN logs raised by the application
The logType APPL_DEBUG_LOG is for all DEBUG logs raised by the application
The logType APPL_ERROR_LOG is for all ERROR logs raised by the application
The logType APPL_FATAL_LOG is for all FATAL logs raised by the application

To request the service for register a logData, the url is:
http://localhost:7004/crossover/ws/register

To request the service for query a logData for a specific logtype, the url is:
http://localhost:7004/m4umarkus/ws/query

The respose for a query will be empty, if no resuts found, or a json with all results founds