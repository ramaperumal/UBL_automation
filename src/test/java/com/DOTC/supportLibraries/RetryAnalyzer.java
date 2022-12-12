package com.DOTC.supportLibraries;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class RetryAnalyzer implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 0;

	public boolean retry(ITestResult result) {
		//System.out.println("Test Case Name:"+ result.getInstanceName());
		//System.out.println("Test Result:"+ result.getStatus());
		//System.out.println("Test Case Name:"+ result.getTestName());
		//System.out.println("Test Case Name:"+ result.getName());
		if (!result.isSuccess()) {                      //Check if test not succeed
            if (counter < retryLimit) {                            //Check if maxTry count is reached
            	counter++;                                     //Increase the maxTry count by 1
                result.setStatus(ITestResult.FAILURE);  //Mark test as failed
                											
                return true;                                 //Tells TestNG to re-run the test
            }
        }
        else {
        	result.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
	}
	
	
}
