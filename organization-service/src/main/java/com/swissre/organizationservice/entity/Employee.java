package com.swissre.organizationservice.entity;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;

public class Employee {
	
	@CsvBindByName
	private int id;
	
	@CsvBindByName
	private String firstName;
	
	@CsvBindByName
	private String lastName;
	
	@CsvBindByName
	private Long salary;
	
	@CsvBindByName
	private int managerId;	
	
	//To store the list of direct subordinates details
	private List<Employee> subordinates = new ArrayList<>();
	
	private double subordinatesAvg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public double getSubordinatesAvg() {
		return subordinatesAvg;
	}

	public void setSubordinatesAvg(double subordinatesAvg) {
		this.subordinatesAvg = subordinatesAvg;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", salary=" + salary
				+ ", managerId=" + managerId + "]";
	}
	
	
}