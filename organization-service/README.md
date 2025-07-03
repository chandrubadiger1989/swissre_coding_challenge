Goal: This project aims to read the Organization Employee data from given CSV file and compute the queries given.

Solution Approach followed:
- The data in CSV file is Organization hierarchy. can be represented as Tree Structure.
- I have used the BFS (Breadth First Search) traversal using the Queue data structure, starting from CEO as root.
- I have created few lists to track the distance of that employee from CEO.
- Initially CEO will be added to the Queue. Once we pull CEO from q, will add CEO's reportees. All immediate reportees of CEO distance will be tracked as distance of 1.
- While I am looping through the reportees, I am also checking their average salary, and comparing it with Salary of Manager.
- If the salary is not expected, then adding to final resulting lists (managerLessThanExptSalary, managerMoreThanExptSalary) 
- If the distance of any employee to CEO is more than 4, adding those employees to final resulting list (empsMoreThanExptReportees)

Below are the assumptions for the project:
1. Employee Salary is assumed to be within 2 billion (including CEO's, so I am assuming data type of Integer
2. CSV File will be correct format, it will have headers and values for each non blank row
3. Only CEO will have blank value for Manager, all other Employees will have complete details in the CSV. Otherwise Application will throw Empty fields expected since they are required
4. Please note that, if the output result contains managerId=0, please assume it is CEO's managerId. Since CEO wont have manager, the integer prints as 0


How to run:
- Step 1: Please review the configurations in the /src/main/resources/config.yaml file. Please update the csvPath to correct on your local machine
- Step 2: Option 1: This project can be imported into IDE using options like "Open Project" or "Import Maven project", and run the project a Java Application. Or, from IDE< right click on "com.swissre.organizationservice.App.java" class and "run as Java application".
- Step 2: Option 2: Build the jar file using "mvn clean install", which will generate jar file. Then run the jar file using "java -jar <jar_file_path>"
