package com.swissre.organizationservice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.swissre.organizationservice.entity.Employee;

/**
 * Application to Improve the Organization Structure, in terms of Average Salary Checks, Reportees length
 */
public class App 
{
	//Configuration: CSV File Path
	private static final String CONFIG_CSV_PATH = "C:\\Chandru\\Official\\swissre_coding_challenge\\organization-service\\src\\main\\java\\com\\swissre\\organizationservice\\company_org_structure.csv";
	//Configuration to check if the manager's salary is at least 20% more than average salary of employees. Configuration can be externalized as well, as well as Environmentalized as well.
	private static final int CONFIG_MIN_PRCT = 20;
	//configuration to check if the manager's salary is  at most 50% more than average salary of employees
	private static final int CONFIG_MAX_PRCT = 50;
	//Configuration to check if the Employee is having more than 4 reportees in between from CEO and employee
	private static final int CONFIG_MAX_REPORTEES = 4;
	
	//To store the raw data from CSV file
	private static List<Employee> rawData; 
	
	//To store the raw data into Employee Map for faster access
	private static Map<Integer, Employee> empMap;
	
	//Capturing starting point, i.e ID of CEO
	private static int ceoID;
	
	//Map to represent the hierarchy of data into a Adjacency List of relationships (Manager-to-Employee).
	private static Map<Integer, List<Integer>> map;
	
	//To store the list of Managers whose salary is less than expected  
	private static List<Employee> managerLessThanExptSalary;
	
	//To store the list of Managers whose salary is more than expected
	private static List<Employee> managerMoreThanExptSalary;
	
	//To store the list of Employee who is having lengthier reports
	private static List<Employee> empsMoreThanExptReports;
	
	//Entry point of the application
    public static void main(String[] args)
    {
    	//Method to read the csv raw data 
    	readCSV();
    	
    	//Represent the data in a form/structure so we can compute the queries easily
    	representHiararchy();
    	
    	//Enhance the data to address the query requirements
    	enhanceEmployeeData();
    	
    	//Queries
    	printManagerMoreThanExptSalary();
    	printManagerLessThanExptSalary();
    	printEmpsMoreThanExptReports();
    }
    
	private static void printManagerMoreThanExptSalary() {
		System.out.println("\nManagers having MORE than Expected Salary");
		for(Employee e: managerMoreThanExptSalary)
			System.out.println(e.toString());
	}

	private static void printManagerLessThanExptSalary() {
		System.out.println("\nManagers having LESS than Expected Salary");
		for(Employee e: managerLessThanExptSalary)
			System.out.println(e.toString());
	}
	
	private static void printEmpsMoreThanExptReports() {
		System.out.println("\nEmployees having Lengthier Reporting");
		for(Employee e: empsMoreThanExptReports)
			System.out.println(e.toString());
	}
	
	/**
	 * This method is used to traverse through the hierarchy starting from CEO (root). 
	 * Captures their salary comparison outcome
	 * Capture their distances 
	 */
	private static void enhanceEmployeeData() {
		//Initialize distance list, to track how far each empooyee from CEO
    	Map<Integer, Integer> distanceMap = new HashMap<>();
    	
    	managerMoreThanExptSalary = new ArrayList<>();
    	managerLessThanExptSalary = new ArrayList<>();
    	empsMoreThanExptReports = new ArrayList<>();
    	
    	Deque<Integer> q = new ArrayDeque<>();
    	q.add(ceoID);
    	distanceMap.put(ceoID, 0);
    	
    	while(!q.isEmpty()) {
    		int curEmployee = q.pollFirst();
    		List<Integer> reportees = map.get(curEmployee);
    		if(reportees != null && !reportees.isEmpty()) {
    			double sumOfReporteesSalary = 0;
    			for(int r: reportees) {
    				sumOfReporteesSalary += empMap.get(r).getSalary();
    				
    				//Calculate distance from CEO
    				int dist = distanceMap.get(curEmployee) + 1;
    				distanceMap.put(r, dist);
    				
    				//Now lets ad this employee to back of the queue
    				q.add(r);
    				
    				//If the distance of this Employee is already more than expected reporting length, capture and store into final list
    				if(dist > CONFIG_MAX_REPORTEES)
    					empsMoreThanExptReports.add(empMap.get(r));
    			}
    			
    			//Now lets check the Managers's salary in terms of comparision with their reportees average
    			double avg = sumOfReporteesSalary / reportees.size(); //Calculate average of reportees
    			
    			double curEmpSalary = empMap.get(curEmployee).getSalary();
    			double salaryRatio = (curEmpSalary/avg)*100 - 100; //Percentage difference
    			
    			if(salaryRatio < CONFIG_MIN_PRCT)
    				managerLessThanExptSalary.add(empMap.get(curEmployee));
    			else if(salaryRatio > CONFIG_MAX_PRCT)
    				managerMoreThanExptSalary.add(empMap.get(curEmployee));
    		}
    	}
	}

	private static void representHiararchy() {
    	
    	map = new HashMap<>();
    	empMap = new HashMap<>();
		
		//Populate the map
		for(Employee e : rawData) {
			
			empMap.put(e.getId(), e);
			
			//CEO Case
			if(e.getManagerId() == 0) {
				ceoID = e.getId();
				map.put(e.getId(), null);
			}
			else {
				List<Integer> reportees = map.get(e.getManagerId());
				if(reportees == null || reportees.isEmpty()) {
					reportees = new ArrayList<>();
				}
				reportees.add(e.getId());
				map.put(e.getManagerId(), reportees);
			}
		}	
	}

	private static void readCSV() {
    	try {
			FileReader fileReader = new FileReader(CONFIG_CSV_PATH);
			CSVReader csvReader = new CSVReader(fileReader);
			
			CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader)
                    .withType(Employee.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
			
			rawData = csvToBean.parse();
						
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found, please check the path and re-execute the application!!");
		}
    }
}