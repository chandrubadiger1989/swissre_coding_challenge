package com.swissre.organizationservice;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for organization-service which aims for improving the Organization Structure
 * 
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Validate How many Managers are earning MORE than expected salary
     */
    public void testManagerMoreThanExptSalary()
    {
    	App.loadConfigs();
    	App.readCSV();
    	App.representHiararchy();
    	App.enhanceEmployeeData();
    	assertEquals(5, App.printManagerMoreThanExptSalary().size());
    }
    
    /**
     * Validate How many Managers are earning LESS than expected salary
     */
    public void testManagerLessThanExptSalary()
    {
    	App.loadConfigs();
    	App.readCSV();
    	App.representHiararchy();
    	App.enhanceEmployeeData();
    	assertEquals(3, App.printManagerLessThanExptSalary().size());
    }
    
    /**
     * Validate how many Employees are having lengthier reporting queue
     */
    public void testEmpsMoreThanExptReports()
    {
    	App.loadConfigs();
    	App.readCSV();
    	App.representHiararchy();
    	App.enhanceEmployeeData();
    	assertEquals(6, App.printEmpsMoreThanExptReports().size());
    
    }
}
