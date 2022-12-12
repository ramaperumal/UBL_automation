package com.DOTC.pageObjects;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.DOTC.stepDefinitions.DOTCLogInSteps;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class DOTCListScreen extends DOTCCommon {
	DOTCCommon dotcCmn = new DOTCCommon();
	DOTCRestService dotc = new DOTCRestService();
	DOTCLogInScreen dotclogin = new DOTCLogInScreen();
	DOTCLogInSteps dotcsteps = new DOTCLogInSteps();
	DOTCSearchScreen dotcSrchPg = new DOTCSearchScreen();
	DOTCBallotScreen dotcBlltPg = new DOTCBallotScreen();
	
	String employeeID;
	HashMap<String, List<String>> data; 
	String position;
	String division;
	String equipment;
	String date;
	String base;
	int nextDayCount = 0;
	
	// clicking on list tab in landing page
	@FindAll({ @FindBy(how = How.XPATH, using = ("//div[contains(text(),'Lists')]")) })
	WebElement listTab;

	// clicking on Submit button
	@FindAll({ @FindBy(how = How.XPATH, using = ("//button[contains(text(),'Submit')]")) })
	WebElement buttonSubmit;

	// pilot list table
	@FindAll({ @FindBy(how = How.XPATH, using = ("//table[@id = 'pickupDisplayGrid']")) })
	WebElement ListTable;

	//
	
	@FindAll({ @FindBy(how = How.XPATH, using = ("//table[@id = 'pickupDisplayGrid']//tbody")) })
	WebElement listTableBody;

	//
	@FindAll({ @FindBy(how = How.XPATH, using = ("//table[@id = 'pickupDisplayGrid']//tbody//tr")) })
	List<WebElement> totalRows;

	//
	@FindAll({ @FindBy(how = How.XPATH, using = ("//table[@id = 'pickupDisplayGrid']//thead//tr//th")) })
	List<WebElement> totalClms;
	
	@FindAll({ @FindBy(how = How.XPATH, using = ("//label[normalize-space(text())='Award Date']")) })
	WebElement awardDate;
	
	@FindAll({ @FindBy(how = How.XPATH, using = ("//select[@id='aircraft']")) })
	WebElement aircraftDropdown;
	
	@FindAll({ @FindBy(how = How.XPATH, using = ("//select[@id='aircraft']//option")) })
	List<WebElement> aircraftOptions;
	
	@FindAll({ @FindBy(how = How.XPATH, using = ("//select[@id='positions']")) })
	WebElement positionsDropdown;
	
	@FindAll({ @FindBy(how = How.XPATH, using = ("//select[@id='positions']//option")) })
	List<WebElement> positionsOptions;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'col-sm-12 header-pilot-info']//a[1]") })

	WebElement pilotName;
	
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@name,'listDate')]") })
	WebElement dateValue;
	
	@FindAll({ @FindBy(how = How.XPATH, using = ("//*[.='Pick Up Outside']")) })
	WebElement pickUpOutsideTab;
	
	@FindAll({ @FindBy(how = How.XPATH, using = ("//a[.='Open Time']")) })
	WebElement openTimeTab;
	
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@name,'listDate')]/..//button//span[.='ui-btn']") })
	WebElement datePicker;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//a[@class = 'ui-datepicker-next ui-corner-all']")})
	WebElement calanderNextMonthLink;

	@FindAll({ @FindBy(how = How.XPATH, using = "//span[@class = 'calendarDate contract-month startDatePicker ng-star-inserted']") })
	List<WebElement> dateList;
	
	public DOTCListScreen() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);

	}

	// Click on Show Sequences button on the search page
	public void clickOnListTab() {
		try {
			Util.ClickElement(driver, listTab);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "List Tab is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "List Tab is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// Click on Submit button from List screen
	public void clickOnSubmitBtn() {
		try {
			Util.waitForLoad(driver);
			Util.ClickElement(driver, buttonSubmit);
			Util.waitForSpinnerLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Submit button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Submit button is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// This method is used to verify that the pilot is listed in Pick Up DOTC List
	// updated xpath 
	// 21-02-2022
	public void verifyExistanceOfPilotInList() {

		try {
			this.employeeID = DOTCLogInScreen.employeeID;
			Util.waitForSpinnerLoad(driver);
			if (listTableBody.getText().equalsIgnoreCase("No data available in table")) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "The pilot is not seen in pick Up DOTC List",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				System.out.println("No data available in table");
				Assert.assertTrue("The pilot is not seen in pick Up DOTC List", false);

			} else {
				// Get all web elements by tag name 'tr'
				//List<WebElement> rowVals = ListTable.findElements(By.tagName("tr"));
				String xpath = "//table[@id = 'pickupDisplayGrid']//tr[contains(@role, 'row') and contains(@class, 'odd') or contains(@class, 'even')]";
				List<WebElement> rowVals = driver.findElements(By.xpath(xpath));
				// Loop through the header values and print them to console
				System.out.println("Header values:");
				// Get total number of rows
				int rowNum = rowVals.size();				
				System.out.println("Total number of rows = " + rowNum);			
				// Loop through the remaining rows				
				for (int i = 0; i < rowNum ; i++) 
				{
					// Get each row's column values by tag name
					Util.waitForLoad(driver);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					List<WebElement> colVals = rowVals.get(i).findElements(By.tagName("td"));					
					System.out.println(colVals.get(5).getText());
					if (colVals.get(5).getText().contains(employeeID)) 
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "The Pilot is seen in pick Up DOTC List",
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						System.out.println("The pilot is seen inside the list");
						Assert.assertTrue("The pilot is seen in pick Up DOTC List", true);
						break;
					}					
					else if((i == rowNum) && !(colVals.get(5).getText().contains(employeeID)))
					{					
						ExtentTestManager.getTest().log(LogStatus.FAIL, "The Pilot is not seen pick Up DOTC List",
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertFalse("The pilot is not seen in pick Up DOTC List", true);
					}
				}
			}
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Sequences could not be clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Exceptions",false);
		}
	}
	
	//24 Aug 2022
	// updated validation
	public void verifyExistanceOfOldPilotInList() {

		try {
			String oldEmployeeID = DOTCLogInSteps.employeeID;
			Util.waitForSpinnerLoad(driver);
			if (listTableBody.getText().equalsIgnoreCase("No data available in table")) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "The pilot is not seen in pick Up DOTC List",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				System.out.println("No data available in table");
				Assert.assertTrue("The pilot is not seen in pick Up DOTC List", false);

			} else {
				// Get all web elements by tag name 'tr'
				//List<WebElement> rowVals = ListTable.findElements(By.tagName("tr"));
				String xpath = "//table[@id = 'pickupDisplayGrid']//tr[contains(@role, 'row') and contains(@class, 'odd') or contains(@class, 'even')]";
				List<WebElement> rowVals = driver.findElements(By.xpath(xpath));
				// Loop through the header values and print them to console
				System.out.println("Header values:");
				// Get total number of rows
				int rowNum = rowVals.size();				
				System.out.println("Total number of rows = " + rowNum);	
				if(rowNum == 0)
					throw new Exception("No pilots found");
				// Loop through the remaining rows				
				for (int i = 0; i < rowNum ; i++) 
				{
					// Get each row's column values by tag name
					Util.waitForLoad(driver);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					List<WebElement> colVals = rowVals.get(i).findElements(By.tagName("td"));					
					System.out.println(colVals.get(5).getText());
					if (colVals.get(5).getText().contains(oldEmployeeID)) 
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, "The Pilot is seen in pick Up DOTC List",
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						System.out.println("The pilot is seen inside the list");
						Assert.assertTrue("The pilot is seen in pick Up DOTC List", true);
						break;
					}					
					else if((i == rowNum-1) && !(colVals.get(5).getText().contains(oldEmployeeID)))
					{					
						ExtentTestManager.getTest().log(LogStatus.FAIL, "The Pilot is not seen pick Up DOTC List",
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertFalse("The pilot is not seen in pick Up DOTC List", true);
					}
				}
			}
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating pilot ID",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Exceptions",false);
		}
	}

	// This method is used to verify that the pilot is listed in Pick Up DOTC List
	// updated xpath 
	// 21-02-2022
		public void verifyRemovalOfPilotFromList() {

			try {
				this.employeeID = DOTCLogInScreen.employeeID;
				Util.waitForSpinnerLoad(driver);
				if (listTableBody.getText().equalsIgnoreCase("No data available in table")) {
					currentScenario.embed(Util.takeScreenshot(driver), "image/png");
					ExtentTestManager.getTest().log(LogStatus.PASS, "The pilot is not seen in pick Up DOTC List",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					System.out.println("No data available in table");
					Assert.assertTrue("The pilot is not seen in pick Up DOTC List", true);

				} else {
					// Get all web elements by tag name 'tr'
					//List<WebElement> rowVals = ListTable.findElements(By.tagName("tr"));
					// Get column header values from first row
					//List<WebElement> colHeader = rowVals.get(0).findElements(By.tagName("th"));
					Util.waitForLoad(driver);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
					String xpath = "//table[@id = 'pickupDisplayGrid']//tr[contains(@role, 'row') and contains(@class, 'odd') or contains(@class, 'even')]";
					List<WebElement> rowVals = driver.findElements(By.xpath(xpath));
					// Loop through the header values and print them to console
					System.out.println("Header values:");
					// Get total number of rows
					int rowNum = rowVals.size();					
					System.out.println("Total number of rows = " + rowNum);					
					// Loop through the remaining rows				
					for (int i = 0; i < rowNum; i++) {
						// Get each row's column values by tag name
						List<WebElement> colVals = rowVals.get(i).findElements(By.tagName("td"));
						 //Loop through each column						
						System.out.println(colVals.get(5).getText());
						if (colVals.get(5).getText() == employeeID){
							ExtentTestManager.getTest().log(LogStatus.FAIL, "The Pilot is not removed from pick Up DOTC List",
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							Assert.assertFalse("The Pilot is not removed from pick Up DOTC List", false);
						}
					}
					ExtentTestManager.getTest().log(LogStatus.PASS, "The Pilot is removed from pick Up DOTC List",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					System.out.println("The Pilot is removed from pick Up DOTC List");
					Assert.assertTrue("The Pilot is removed from pick Up DOTC List", true);
				}
				
			} catch (Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Sequences could not be clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue("Exception", false);
			}
		}
		
		// function to validate if fieldname Award Date is present in Lists tab
		//22-03-2022
		public void validateAwardDateField() throws Throwable {
			try {
				Util.waitForLoad(driver);
				if(awardDate.isDisplayed()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Award Date fieldname is displayed in Lists tab",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Award Date field name is displayed in Lists tab", true);
				} 
				
				if(driver.findElements(By.xpath("//label[normalize-space(text())='Date']")).size() == 0) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Date fieldname is not displayed in Lists tab",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("ate fieldname is not displayed in Lists tab", true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Date fieldname is displayed in Lists tab",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Date fieldname is displayed in Lists tab", false);
				}
				
			} catch (Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Award Date fieldname is not displayed in Lists tab",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue("Award Date fieldname is not displayed in Lists tab", false);
			}
		}
		
	//function to validate options in aircraft dropdown
	//08-Apr-2022
	public void validateAircraftOptions(String epqIds) throws Throwable{
		try {
			Util.waitForLoad(driver);
			List<String> eqpVal = new ArrayList<String>(Arrays.asList(epqIds.split(",")));
			List<String> eqp = new ArrayList<String>();
			for(int i=0; i<aircraftOptions.size(); i++)
				eqp.add(aircraftOptions.get(i).getText());
			
			System.out.println(eqp);
			
			if(eqpVal.size() != eqp.size()) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, eqp+" doesn't match expected aircraft "+eqpVal,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(eqp+" doesn't match expected aircraft "+eqpVal, false);
			}
			int flag = 0;
			for(String e: eqpVal)
				flag = eqp.contains(e) ? 0: 1;
			
			if(flag == 1) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, eqp+" doesn't match expected aircraft "+eqpVal,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(eqp+" doesn't match expected aircraft "+eqpVal, false);
			} else {
				ExtentTestManager.getTest().log(LogStatus.PASS, eqp+" match expected aircraft "+eqpVal,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(eqp+" match expected aircraft "+eqpVal, true);
			}
		} catch(Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in identifying aircraft dropdown",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in identifying aircraft dropdown", false);
		}
	}
	
	//function to validate options in positions dropdown
		//08-Apr-2022
		public void validatePositionsOptions(String epqIds) throws Throwable{
			try {
				Util.waitForLoad(driver);
				List<String> eqpVal = new ArrayList<String>(Arrays.asList(epqIds.split(",")));
				List<String> eqp = new ArrayList<String>();
				for(int i=0; i<positionsOptions.size(); i++)
					eqp.add(positionsOptions.get(i).getText());
				
				System.out.println(eqp);
				
				if(eqpVal.size() != eqp.size()) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, eqp+" doesn't match expected positions "+eqpVal,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue(eqp+" doesn't match expected positions "+eqpVal, false);
				}
				int flag = 0;
				for(String e: eqpVal)
					flag = eqp.contains(e) ? 0: 1;
				
				if(flag == 1) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, eqp+" doesn't match expected positions "+eqpVal,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue(eqp+" doesn't match expected positions "+eqpVal, false);
				} else {
					ExtentTestManager.getTest().log(LogStatus.PASS, eqp+" match expected positions "+eqpVal,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue(eqp+" match expected positions "+eqpVal, true);
				}
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in identifying positions dropdown",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in identifying positions dropdown", false);
			}
		}
		
		// function to extract data from UI fields
		//20 May 2022
		public void getDataFromFields() {
			try {
				Select select = new Select(driver.findElement(By.xpath("//select[@id='bases']")));
				base = select.getFirstSelectedOption().getText();
				System.out.println("Base: "+base);
				
				select = new Select(driver.findElement(By.xpath("//select[@id='divisions']")));
			    division = select.getFirstSelectedOption().getText();
			    if(division.equalsIgnoreCase("International"))
			    	division = "I";
			    else
			    	division = "D";
			    System.out.println("Division: "+division);
			    
			    select = new Select(driver.findElement(By.xpath("//select[@id='positions']")));
			    position = select.getFirstSelectedOption().getText();
			    if(position.equalsIgnoreCase("Captain"))
			    	position = "CA";
			    else
			    	position = "FO";
			    System.out.println("Position: "+position);
			    
			    select = new Select(driver.findElement(By.xpath("//select[@id='aircraft']")));
			    equipment = select.getFirstSelectedOption().getText();
			    System.out.println("Equipment: "+equipment);		    
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception to fetching data from fields",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue("Exception to fetching data from fields", false);
			}
		}
		
		//function is used to extract data from pick up service response
		// 17 May 2022
		public void getDataFromPickUpServices(String lhs) {
			data = new HashMap<String, List<String>>();
			data.put("EMP Number", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..employeeId", lhs));
			data.put("Sen", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..seniorityNumber", lhs));
			data.put("Div", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..bidStatus.division", lhs));
			data.put("PROJ", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..projection", lhs));
			data.put("IMAX", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..imax", lhs));
			data.put("VMAX", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..volMax", lhs));
			data.put("firstName", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..firstName", lhs));
			data.put("lastName", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..lastName", lhs));
			data.put("Days Avail", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..daysAvaialble", lhs));
			data.put("Quals", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..quals", lhs));
			data.put("Type", DOTCRestService.readJsonList("$..pickupPilots.pickupPilot..classification", lhs));
		}
		
		// function to validate values in pick up services
		// 20 May 2022
		public void valueValidationPickUpService(String path) {
			try {
				
				// validating employee number
				String uiValue = driver.findElement(By.xpath(path+"[6]")).getText();
				int j;
				for(j=0; j<data.get("EMP Number").size(); j++)
					if(uiValue.equalsIgnoreCase(String.valueOf(data.get("EMP Number").get(j))))
							break;
				String serviceValue = String.valueOf(data.get("EMP Number").get(j));
				if(uiValue.equalsIgnoreCase(serviceValue))
					System.out.println("Employee number is equal from service: "+serviceValue+", UI: "+uiValue);
				else {
					System.out.println("Employee number is not equal from service: "+serviceValue+", UI: "+uiValue);
					throw new Exception("Value Missmatch");
				}
							
				// validating seniority number
				uiValue = driver.findElement(By.xpath(path+"[1]")).getText();
				serviceValue = String.valueOf(data.get("Sen").get(j));
				if(uiValue.equalsIgnoreCase(serviceValue))
					System.out.println("Seniority number is equal from service: "+serviceValue+", UI: "+uiValue);
				else {
					System.out.println("Seniority number is not equal from service: "+serviceValue+", UI: "+uiValue);
					throw new Exception("Value Missmatch");
				}
				
				// validating division
				uiValue = driver.findElement(By.xpath(path+"[3]")).getText();
				serviceValue = String.valueOf(data.get("Div").get(j));
				if(uiValue.equalsIgnoreCase(serviceValue))
					System.out.println("Division is equal from service: "+serviceValue+", UI: "+uiValue);
				else {
					System.out.println("Division is not equal from service: "+serviceValue+", UI: "+uiValue);
					throw new Exception("Value Missmatch");
				}
				
				//validating employee name
				uiValue = driver.findElement(By.xpath(path+"[5]")).getText();
				serviceValue = String.valueOf(String.valueOf(data.get("lastName").get(j)+", "+data.get("firstName").get(j)));
				if(uiValue.equalsIgnoreCase(serviceValue))
					System.out.println("Employee name is equal from service: "+serviceValue+", UI: "+uiValue);
				else {
					System.out.println("Employee name is not equal from service: "+serviceValue+", UI: "+uiValue);
					throw new Exception("Value Missmatch");
				}
				
				// validating projection
				uiValue = driver.findElement(By.xpath(path+"[7]")).getText();
				double uiValueD = Double.parseDouble(uiValue);
				// convert from min to hrs
				float projService = Float.parseFloat(String.valueOf(data.get("PROJ").get(j)));
				int hourProj = (int) (projService / 60);
				float minutesProj = (projService % 60)/100;
				
				double serviceValueD =Double.parseDouble(String.format("%.2f", hourProj+minutesProj));
								
				if(serviceValueD == uiValueD)
					System.out.println("Projection is equal from service: "+serviceValueD+", UI: "+uiValueD);
				else {
					System.out.println("Projection is not equal from service: "+serviceValueD+", UI: "+uiValueD);
					throw new Exception("Value Missmatch");
				}
				
				// validating IMAX
				uiValue = driver.findElement(By.xpath(path+"[8]")).getText();
				projService = Float.parseFloat(String.valueOf(data.get("IMAX").get(j)));
				hourProj = (int) (projService / 60);
				minutesProj = (projService % 60)/100;
				
				if(hourProj+minutesProj < 0)
					serviceValue = "";
				else
					serviceValue = String.format("%.02f", hourProj+minutesProj);
				
				if(uiValue.equalsIgnoreCase(serviceValue))
					System.out.println("IMAX is equal from service: "+serviceValue+", UI: "+uiValue);
				else {
					System.out.println("IMAX is not equal from service: "+serviceValue+", UI: "+uiValue);
					throw new Exception("Value Missmatch");
				}
				
				// validating VMAX
				uiValue = driver.findElement(By.xpath(path+"[9]")).getText();
				uiValueD = Double.parseDouble(uiValue);
				projService = Float.parseFloat(String.valueOf(data.get("VMAX").get(j)));
				hourProj = (int) (projService / 60);
				minutesProj = (projService % 60)/100;
				//serviceValue = String.format("%.02f", hourProj+minutesProj);
				serviceValueD = hourProj+minutesProj;
				if(serviceValueD == uiValueD)
					System.out.println("VMAX is equal from service: "+serviceValueD+", UI: "+uiValueD);
				else {
					System.out.println("VMAX is not equal from service: "+serviceValueD+", UI: "+uiValueD);
					throw new Exception("Value Missmatch");
				}
				
				// validating Days avail
				uiValue = driver.findElement(By.xpath(path+"[10]")).getText();
				serviceValue = String.valueOf(data.get("Days Avail").get(j));
				if(uiValue.equalsIgnoreCase(serviceValue))
					System.out.println("Days avail is equal from service: "+serviceValue+", UI: "+uiValue);
				else {
					System.out.println("Days avail is not equal from service: "+serviceValue+", UI: "+uiValue);
					throw new Exception("Value Missmatch");
				}
				
				// validating Quals
				uiValue = driver.findElement(By.xpath(path+"[11]")).getText();
				String[] ui = uiValue.split(" ");
				serviceValue = String.valueOf(data.get("Quals").get(j)).replace("[", "").replace("]", "");
				String[] serv = serviceValue.split(" ");
				for(String s:ui) {
					if(s.equals(""))
						continue;
				
					if(!Arrays.asList(serv).contains(s)) {
						System.out.println("Quals is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
				}
				System.out.println("Quals from service: "+serviceValue+", quals from UI: "+uiValue);
			} catch(Exception ex) {
				if(ex.getMessage().equals("Value Missmatch")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Data miss-match",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Data miss-match", false);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating pick up dotc",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					ex.printStackTrace();
					Assert.assertTrue("Exception in validating pick up dotc", false);
				}
			}
		}
		
		//function to validate data on UI with respect to service for pick up dotc
		//17 May 2022
		
		public void validatePickUpDOTC() {
			System.out.println("-------Pick up DOTC---------");
			Util.waitForLoad(driver);
			try {
				Util.waitForSpinnerLoad(driver);
				String exp = "//table[@id='pickupDisplayGrid']//td[@class='dataTables_empty']";
				if(driver.findElements(By.xpath(exp)).size()!=0)
					throw new Exception("No data in Pick up DOTC list");
				getDataFromFields();	
				date = dateValue.getAttribute("value");
				SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMyy");
		        Date date1 = DateFor.parse(date);
				DateFor = new SimpleDateFormat("yyyy-MM-dd");
				date = DateFor.format(date1)+" 00:00";
			    System.out.println("Date: "+date);
				String lhs = dotc.pickUpDotcService("pickUpDOTC", base, division, equipment, position, date);
				
				getDataFromPickUpServices(lhs);
				//System.out.println(data);
				
				if(data.get("EMP Number").isEmpty())
					throw new Exception("No Data");
				
				exp = "//table[@id='pickupDisplayGrid']//tbody//tr[@role='row']";
				int size = driver.findElements(By.xpath(exp)).size();
				Util.waitForLoad(driver);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath(exp)));
				for(int i=1;i<=size;i++) {
					String path = "//table[@id='pickupDisplayGrid']//tbody//tr[@role='row']["+i+"]//td";
					valueValidationPickUpService(path);
				}	
				ExtentTestManager.getTest().log(LogStatus.PASS, "Validated all data in pick up dotc and values match with service",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Validated all data in pick up dotc and values match with service", true);
			} catch(Exception ex) {
				if(ex.getMessage().equalsIgnoreCase("No Data")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No data in service for pick up dotc",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					ex.printStackTrace();
					Assert.assertTrue("No data in service for pick up dotc", false);
				} else if(ex.getMessage().equalsIgnoreCase("No data in Pick up DOTC list")) { 
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No data in UI for pick up dotc",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					ex.printStackTrace();
					Assert.assertTrue("No data in UI for pick up dotc", false);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating pick up dotc",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					ex.printStackTrace();
					Assert.assertTrue("Exception in validating pick up dotc", false);
				}
			}
		}

		//function to validate data on UI with respect to service for pick up outside dotc
		//18 May 2022 
		public void validatePickUpOutsideDOTC() {
			System.out.println("-------Pick up Outside---------");
			Util.waitForLoad(driver);
			try {
				String exp = "//table[@id='pickupDisplayGrid']//td[@class='dataTables_empty']";
				if(driver.findElements(By.xpath(exp)).size()!=0)
					throw new Exception("No data in Pick up Outside list");
				getDataFromFields();
				date = dateValue.getAttribute("value");
				SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMyy");
		        Date date1 = DateFor.parse(date);
				DateFor = new SimpleDateFormat("yyyy-MM-dd");
				date = DateFor.format(date1)+" 00:00";
			    System.out.println("Date: "+date);
				String lhs = dotc.pickUpDotcService("pickUpOutside", base, division, equipment, position, date);
				
				getDataFromPickUpServices(lhs);
				System.out.println(data);
				
				if(data.get("EMP Number").isEmpty())
					throw new Exception("No Data");
				exp = "//table[@id='pickupOutsideDisplayGrid']//tbody//tr[@role='row']";
				int size = driver.findElements(By.xpath(exp)).size();
				
				Util.waitForLoad(driver);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath(exp)));
				for(int i=1;i<=size;i++) {
					String path = "//table[@id='pickupOutsideDisplayGrid']//tbody//tr[@role='row']["+i+"]//td";
					valueValidationPickUpService(path);
				}	
				ExtentTestManager.getTest().log(LogStatus.PASS, "Validated all data in pick up dotc and values match with service",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Validated all data in pick up dotc and values match with service", true);
			} catch(Exception ex) {
				if(ex.getMessage().contains("No Data")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No data in service for pick up outside",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					ex.printStackTrace();
					Assert.assertTrue("No data in service for pick up outside", false);
				} else if(ex.getMessage().equalsIgnoreCase("No data in Pick up Outside list")) { 
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No data in UI for pick up outside",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					ex.printStackTrace();
					Assert.assertTrue("No data in UI for pick up outside", false);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating pick up dotc",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					ex.printStackTrace();
					Assert.assertTrue("Exception in validating pick up dotc", false);
				}
			}
		}
		
		// function to click on pick up outside tab
		// 18 May 2022
		public void clickPickUpOutsideTab() {
			try {
				Util.ClickElement(driver, pickUpOutsideTab);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on pick up outside tab",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Clicked on pick up outside tab", true);
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception to click on pick up outside tab",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue("Exception to click on pick up outside tab", false);
			}
		}
		
		// function to click on Open Time Tab
		//18 May 2022
		public void clickOpenTimeTab() {
			Util.waitForLoad(driver);
			try {
				Util.waitForSpinnerLoad(driver);
				Util.ClickElement(driver, openTimeTab);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on open time tab",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Clicked on open time tab", true);
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception to click on open time tab",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue("Exception to click on open time tab", false);
			}
		}
		
		//function to validate open time
		// 18 May 2022
		public void validateOpenTime() {
			System.out.println("----------Open Time-----------");
			try {
				Util.waitForLoad(driver);
				
				String noResExp = "//*[normalize-space(text())='No results']";
				while(driver.findElements(By.xpath(noResExp)).size() != 0) {
					changeDate(++nextDayCount);
					clickOnSubmitBtn();
					Util.waitForSpinnerLoad(driver);
					if(nextDayCount == 3)
						break;
				}
				getDataFromFields();
				
				date = dateValue.getAttribute("value");
				SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMyy");
		        Date date1 = DateFor.parse(date);
				DateFor = new SimpleDateFormat("yyyy-MM-dd");
				date = DateFor.format(date1);
			    System.out.println("Date: "+date);
			    
			    String departureStations = "";
			    for(int i = 0; i <TestData.departrueStations.get(base).size(); i++) {
			    	departureStations += "\""+TestData.departrueStations.get(base).get(i)+"\"";
			    	if(i!=TestData.departrueStations.get(base).size()-1)
			    		departureStations += ",";
			    }
			    
			    String positionList = "";
			    for(int i=0; i<TestData.positions.get(position).size(); i++) {
			    	positionList += "\""+TestData.positions.get(position).get(i)+"\"";
			    	if(i!=TestData.positions.get(position).size()-1)
			    		positionList += ",";
			    }
				
				String payload = "{\"airLineCode\":\"AA\","
						+ "\"employeeBidStatus\":{"
							+ "\"base\":\""+base+"\","
							+ "\"seat\":\""+position+"\","
							+ "\"division\":\""+division+"\","
							+ "\"equipmentGroup\":\""+equipment+"\"},"
						+ "\"noFlyDays\":[],\"noWorkDates\":[],\"dhpreferences\":{},"
						+ "\"departureStations\":["+departureStations+"],"
						+ "\"divisions\":[\""+division+"\"],\"positions\":["+positionList+"],"
						+ "\"maxCalendarDays\":\"7\",\"minCalendarDays\":\"1\","
						+ "\"openSequences\":true,\"startDateFrom\":\""+date+"\","
						+ "\"endDate\":\""+date+"\"}";
				String lhs = dotc.getSequence(payload);
				//System.out.println(lhs);
				
				List<String> seqNo = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].sequenceNumber", lhs);
				List<String> startDate = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].sequenceStartDateTime", lhs);
				List<String> sta = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].firstLegDepartureAirport", lhs);
				List<String> div = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].division", lhs);
				List<String> pos = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].seat", lhs);
				List<String> durationInDays = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].durationInDays", lhs);
				List<String> legsPerDP = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].legsPerDutyPeriod", lhs);
				List<String> credit = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].totalCredit", lhs);
				List<String> dep = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].firstLegDepartureDateTime", lhs);
				List<String> arr = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].lastLegArrivalDateTime", lhs);
				List<String> layovers = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].layoverStations", lhs);
				List<String> tfbs = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].timeAwayFromBase", lhs);
				List<String> quals = DOTCRestService.readJsonList("$.sequence..[?(@.employeeID==0)].quals", lhs);
				
				String exp = "//table[@id='sequencesLargeTable']//tbody//tr[@class='ng-star-inserted']";
				int size = driver.findElements(By.xpath(exp)).size();
				System.out.println("Size of rows in table: "+size);
				
				Util.waitForLoad(driver);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath(exp)));
				int j = 0;
				for(int i=1;i<=size;i+=2) {
					String path = "//table[@id='sequencesLargeTable']//tbody//tr["+i+"]//td";
					
					// validate sequence number
					String uiValue = driver.findElement(By.xpath(path+"[3]")).getText();
					String serviceValue = String.valueOf(seqNo.get(j));
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Sequence number is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Sequence number is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate date
					uiValue = driver.findElement(By.xpath(path+"[4]")).getText();
					serviceValue = String.valueOf(startDate.get(j)).toUpperCase();
					DateFor = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
			        date1 = DateFor.parse(serviceValue);
					DateFor = new SimpleDateFormat("dd MMM");
					serviceValue= DateFor.format(date1);
					
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Date is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Date is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate station
					uiValue = driver.findElement(By.xpath(path+"[5]")).getText();
					serviceValue = String.valueOf(sta.get(j));
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Station is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Station is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate division
					uiValue = driver.findElement(By.xpath(path+"[6]")).getText();
					serviceValue = String.valueOf(div.get(j));
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Division is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Diviaion is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate Position
					uiValue = driver.findElement(By.xpath(path+"[7]")).getText();
					serviceValue = String.valueOf(pos.get(j));
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Position is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Position is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate Days
					uiValue = driver.findElement(By.xpath(path+"[8]")).getText();
					serviceValue = String.valueOf(durationInDays.get(j))+" ("+String.valueOf(legsPerDP.get(j))+")";
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Days is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Days is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate Credit
					uiValue = driver.findElement(By.xpath(path+"[9]")).getText();
					Float projService = Float.parseFloat(String.valueOf(credit.get(j)));
					Integer hourProj = (int) (projService / 60);
					Float minutesProj = (projService % 60)/100;
					serviceValue = String.format("%.02f", hourProj+minutesProj);
					
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Credit is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Credit is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate dep date
					uiValue = driver.findElement(By.xpath(path+"[10]")).getText();
					serviceValue = String.valueOf(dep.get(j)).toUpperCase();
					DateFor = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
			        date1 = DateFor.parse(serviceValue);
					DateFor = new SimpleDateFormat("HHmm");
					serviceValue= DateFor.format(date1);
					
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Departure Date is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Departure Date is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate arr date
					uiValue = driver.findElement(By.xpath(path+"[11]")).getText();
					serviceValue = String.valueOf(arr.get(j)).toUpperCase();
					DateFor = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
			        date1 = DateFor.parse(serviceValue);
					DateFor = new SimpleDateFormat("HHmm");
					serviceValue= DateFor.format(date1);
					DateFor = new SimpleDateFormat("dd");
					serviceValue += "/"+DateFor.format(date1);
					
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Arrival Date is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Arrival Date is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate layover stations date
					uiValue = driver.findElement(By.xpath(path+"[12]")).getText();
					serviceValue = String.valueOf(layovers.get(j)).equals("null")?"":String.valueOf(layovers.get(j));
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("Layovers is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("Layovers is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validate TAFB date
					uiValue = driver.findElement(By.xpath(path+"[13]")).getText();
					projService = Float.parseFloat(String.valueOf(tfbs.get(j)));
					hourProj = (int) (projService / 60);
					minutesProj = (projService % 60)/100;
					serviceValue = String.format("%.02f", hourProj+minutesProj);
					
					if(uiValue.equalsIgnoreCase(serviceValue))
						System.out.println("TAFB is equal from service: "+serviceValue+", UI: "+uiValue);
					else {
						System.out.println("TAFB is not equal from service: "+serviceValue+", UI: "+uiValue);
						throw new Exception("Value Missmatch");
					}
					
					// validating Quals
					uiValue = driver.findElement(By.xpath(path+"[14]")).getText();
					String[] ui = uiValue.split(" ");
					serviceValue = quals.get(j);
					if(serviceValue == null)
						serviceValue = "";
					String[] serv = serviceValue.split(" ");
					for(String s:ui)
						if(!Arrays.asList(serv).contains(s)) {
							System.out.println("Quals is not equal from service: "+serviceValue+", UI: "+uiValue);
							throw new Exception("Value Missmatch");
						}
					System.out.println("Quals from service: "+serviceValue+", quals from UI: "+uiValue);		
					j++;
				}				
			} catch(Exception ex) {
				if(ex.getMessage().equalsIgnoreCase("Value Missmatch")) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Data miss-match",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Data miss-match", false);
				}
			}
		}
		
		//function to change date
		//23 May 2022
		public void changeDate(int numb) {
			try {
				Util.ClickElement(driver, datePicker);
				Util.waitForLoad(driver);
				System.out.println(numb);
				if (dotcSrchPg.verifyCurrentDateAsLastDateOfTheMonth(numb)) {
					Util.ClickElement(driver, calanderNextMonthLink);
					Util.ClickElement(driver, dateList.get(numb));
				} else {
					Util.ClickElement(driver, dateList.get(numb));
				}
				ExtentTestManager.getTest().log(LogStatus.PASS, "Change date to: "+dateValue.getAttribute("value"),
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Change date to: "+dateValue.getAttribute("value"), true);
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in changing date",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in changing date", false);
			}
		}
		
		// function to change parameters
		// 23 May 2022
		public void changeValues(String parameters) {
			try {
				List<String> listParameters = Arrays.asList(parameters.split(",")); 
				if(listParameters.contains("position")) {
					Util.waitForLoad(driver);
					List<String> eqp = new ArrayList<String>();
					for(int i=0; i<positionsOptions.size(); i++)
						eqp.add(positionsOptions.get(i).getText());
				
					eqp.remove(position);
				
					Select s = new Select(positionsDropdown);
					s.selectByVisibleText(eqp.get(0));
				} 
				if(listParameters.contains("aircraft")) {
					Util.waitForLoad(driver);
					List<String> eqp = new ArrayList<String>();
					for(int i=0; i<aircraftOptions.size(); i++)
						eqp.add(aircraftOptions.get(i).getText());
				
					eqp.remove(equipment);
				
					Select s = new Select(aircraftDropdown);
					s.selectByVisibleText(eqp.get(0));
				}
				getDataFromFields();
				ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully changed values to "+base+"/"+equipment+"/"+position+"/"+division,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Successfully changed values to "+base+"/"+equipment+"/"+position+"/"+division, true);
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in changing values",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue("Exception in changing values", false);
			}
		}
		
		// function to validate position and aircraft group
		// 24 May 2022
		public void validateAircraftAndPositionGroup() {
			try {
				Util.waitForLoad(driver);
				
				String noResExp = "//*[normalize-space(text())='No results']";
				String exp = "//table[@id='sequencesLargeTable']//tbody//tr[@role='row']";
				while(driver.findElements(By.xpath(noResExp)).size() == 0 && driver.findElements(By.xpath(exp)).size() == 0) {
					Util.waitForLoad(driver);
				}
				while(driver.findElements(By.xpath(noResExp)).size() != 0) {
					changeDate(++nextDayCount);
					clickOnSubmitBtn();
					Util.waitForSpinnerLoad(driver);
					while(driver.findElements(By.xpath(noResExp)).size() == 0 && driver.findElements(By.xpath(exp)).size() == 0) {
						Util.waitForLoad(driver);
					}
					
					if(nextDayCount == 3)
						break;
				}
				
				getDataFromFields();
				
				String positionList = "";
			    for(int i=0; i<TestData.positions.get(position).size(); i++) {
			    	positionList += "\""+TestData.positions.get(position).get(i)+"\"";
			    	if(i!=TestData.positions.get(position).size()-1)
			    		positionList += ",";
			    }
			    
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath(exp)));
				
				int size = driver.findElements(By.xpath(exp)).size();
				for(int i=1;i<=size;i++) {
					String path = "//table[@id='sequencesLargeTable']//tbody//tr["+i+"]//td";
					
					String seqNumber = driver.findElement(By.xpath(path+"[3]")).getText();
					
					String pos = driver.findElement(By.xpath(path+"[7]")).getText();
					if(!positionList.contains(pos)) {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence: "+seqNumber+" have different position "+pos+" expected "+positionList,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue("Sequence: "+seqNumber+" have different position "+pos+" expected "+positionList, false);
					}
					
					driver.findElement(By.xpath(path+"[3]//a")).click();
					List<WebElement> eqpGroup = driver.findElements(By.xpath("//div[@id='leg-equipment']"));
					for(WebElement s: eqpGroup) {
						System.out.println(s.getText());
						if(!TestData.equipments.get(equipment).contains(s.getText())) {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence: "+seqNumber+" have different equipment group "+s.getText()+" expected "+equipment,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							Assert.assertTrue("Sequence: "+seqNumber+" have different equipment group "+s.getText()+" expected "+equipment, false);
						}
					}
					driver.findElement(By.xpath(path+"[3]//a")).click();
				}
				ExtentTestManager.getTest().log(LogStatus.PASS, "Validated position and equipment group",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Validated position and equipment group", true);
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating position and equipment group",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in validating position and equipment group", false);
			}
		}
}
