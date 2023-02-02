# Password-Storage
This program is designed to allow the user to add, delete, update and view passwords in the database. The code will establish a link between the java source code and Azure database.
***
***
## About
The programming language is in Java (version 18 or 19) and developed in Eclipse.
Before using the code and accessing the databases, the user must install the microsoft jdbc driver jar file and mysql connector jar file to the course path of the project.
In order to add the jars from Files, click on Project -> Properties -> Course Path -> Add External Jars. You may also need to delete your empty packages file if you are unable to connect to the database still.

Microsoft JDBC driver - https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16

Mysql connector - https://dev.mysql.com/downloads/connector/j/
(Operating system - source code)
***
***
## Functionality
The program will ask the user for an integer input to determine whether the user would want to add, delete, update or view all the passwords. All methods will create
a null connection first before trying to establish a connection using the given database link. If the connection is established, the code will either executeQuery() or
executeUpdate() using the given SQL string. For clarity, executeQuery() will return a Result Set object while executeUpdate() will return an integer. The return values
would then be displayed to the console.
***
***
## Potential
The user interface at the moment is only using input and output on the console. It would be best if the user interface could have a web application using frontend-based
languages such as react.js.
