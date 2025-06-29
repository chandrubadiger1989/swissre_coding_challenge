package com.swissre.organizationservice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.swissre.organizationservice.entity.Employee;

/**
 * Application to Improve the Organization Structure, in terms of Average Salary Checks, Reportees length
 *
 */
public class App 
{
	//Store the raw data read from CSV file
	private static List<Employee> rawData; 
	
	//Store the raw data into Employee Map
	private static Map<Integer, Employee> empMap;
	
	private static int ceoID;
	
	//Map to represent the hierarchy of data into a Adjacency List of relationships (Manager-to-Employee)
	private static Map<Integer, List<Integer>> map;
	
    public static void main( String[] args )
    {
    	//Method to read the csv raw data 
    	readCSV();
    	
    	//Represent the data in a form so we can compute the queries easily
    	representHiararchy();
    	
    	enhanceEmployeeData();
    }
    
    private static void enhanceEmployeeData() {
		
	}

	private static void representHiararchy() {
    	
    	map = new HashMap<>();
		
		//Populate the map
		for(Employee e : rawData) {
			
			empMap.put(e.getId(), e);
			
			//CEO Case
			if(e.getManagerId() == 0) {
				ceoID = e.getId();
				map.put(e.getId(), new ArrayList<>());
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
			FileReader fileReader = new FileReader("D:\\STS_WS\\organization-service\\src\\main\\java\\com\\swissre\\organizationservice\\company_org_structure.csv");
			CSVReader csvReader = new CSVReader(fileReader);
			
			CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader)
                    .withType(Employee.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
			
			rawData = csvToBean.parse();
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
    }
}