# LibraryManagement
This is a java application to manage a library system. This utility runs as a java application and connects to a remote SQL for data management. This utility contains the follow features,
	1. Student enrollment
	2. Viewing enrolled students
	3. Add new user
	4. View list of available users
	5. Delete user
	6. Add book
	7. View added books
	8. Delete books
	9. Issue book
	10. View issued books
	11. Return books
	12. View Returned books
	
# Application Setup
Clone & Import Project
	Clone the project from GitHub and import the project using eclipse IDE as a java project. Dependency jar files are available in /lib folder. To configure the dependency jar files in your class path,
	1. Right click on your project
	2. Select properties
	3. Select Java build path
	4. Select Libraries
	5. Select the below jars and update its location,
		a) hamcrest-core-1.3.jar				-	/Your Project Location/lib/hamcrest-core-1.3.jar
		b) junit-4.13.2.jar					-	/Your Project Location/lib/junit-4.13.2.jar
		c) mysql-connector-java-8.0.28.jar	-	/Your Project Location/lib/mysql-connector-java-8.0.28.jar
	6. Click "Apply & Close" 

MySQL Database Setup
	Database connection details are available in /src/com/app/driver/MySQLDriver.java file. Update the hostname name of your remote database, database name, username and password on line no. 9, 10, 11 respectively. On your remote PHP admin, import the sql file available in /resources/library.sql to create required tables.

Note : MySQL remote database is fully configured

# Run Application
Select /src/com/app/ui/Library.java file, right click and choose run as Java Application. Library management application will be launched and use the below default credentials,
	User 		: admin
	Password 	: admin
	
# White-box Testing using JUnit
Select /src/test/fun package, right click and choose coverage as Junit Test. This will run the test execution.

# Black-box Testing using TSL
Install TSL generator on your local system by referring the below link,
https://github.com/alexorso/tslgenerator

1. Clone the above mentioned git repository into your local system
2. Navigate to /tslgenerator/Binaries directory
3. Execute below command to generate the tsl test case file,

Windows:
./TSLgenerator-win32.exe -c BlackBoxTest.rtf

Mac:
./TSLgenerator-mac -c BlackBoxTest.rtf





