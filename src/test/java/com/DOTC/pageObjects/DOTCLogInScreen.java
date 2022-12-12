package com.DOTC.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.DOTC.stepDefinitions.DOTCLogInSteps;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;
import java.util.List;
import junit.framework.Assert;




import java.util.ArrayList;
import java.util.Base64;

public class DOTCLogInScreen extends DOTCCommon {

	DOTCRestService dotc = new DOTCRestService();
	public static String employeeID = "";
	TestData testData = new TestData();

	// ******* Elements used for AA Login page *******
	@CacheLookup

	@FindAll({

			@FindBy(how = How.XPATH, using = "//input[@id='userID']")

	})
	WebElement username;

	@CacheLookup

	@FindAll({

			@FindBy(how = How.XPATH, using = "//input[@id='password']")

	})
	WebElement password;

	@CacheLookup

	@FindAll({

			@FindBy(how = How.XPATH, using = "//button[@id='login']")

	})
	WebElement loginButton;

	// ******* Elements for Emulate page *******


	@FindAll({ @FindBy(how = How.XPATH, using = "//input[@id='txtEmployeeNumber']")

	})
	WebElement employeeIDEditBox;;

	
	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@id='buttonEmulateSubmit']")

	})
	WebElement submitBtn;
	
	//
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = ("//a[@class = 'header-logout hidden-xs']")) })
	WebElement logOutLink;


	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Logout']")

	})
	WebElement logout;

// element in page with this text: HTTP Status 404 – Not Found
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(.,'HTTP Status 404 – Not Found')]")

	})
	WebElement error404;


	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(.,'You are not authorized to access this resource')]")

	})
	WebElement AutError;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[text() = 'Stop Emulate']")

	})
	WebElement stopEmulateBtn;

	//Constructor made for DOTCLogin Screen 
	public DOTCLogInScreen() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);

	}

// this method is used to enter user credentials for AA page
	public void setCredentials() {
		try {
			Util.ClickElement(driver, username);
			Util.enterText(driver, username, TestData.strUsername);
			Util.ClickElement(driver, password);
			Util.enterText(driver, password, decodePswd(TestData.strPassword));
			Util.ClickButton(driver, loginButton);
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

//this method is used to decode the password
	private String decodePswd(String pswd) {
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(pswd);
			pswd = new String(decodedBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pswd;
	}


	
	public void setCredential() throws Throwable {
		String curentUrl=Util.url(driver);
		String env="dev";
		
		if(curentUrl.contains("smlogin.stage.aa")) {
			env="stage";
			if(TestData.strGlobalURL.equals("https://pilot-dotc-nextgen-stage.aa.com/"))
				env="stage cloud";
		}
		else if(curentUrl.contains("smlogin.qtcorpaa")) {
			env="qa";
			if(TestData.strGlobalURL.equals("https://pilot-dotc-nextgen-test.aa.com/#/home"))
				env="qa cloud";
		}
		if (env != "dev") {
			DOTCLogInSteps loginStep = new DOTCLogInSteps();
			loginStep.i_do_SSO_login_as("itadmin", env);
		}
	}
	
	// this method is used to enter employee id in Emulate page
		public boolean login(String employeeID) throws Throwable {
			this.employeeID = employeeID;
			
			try {
				Util.ClickButton(driver, employeeIDEditBox);
				Util.enterText(driver, employeeIDEditBox, employeeID);
				Util.ClickButton(driver, submitBtn);
				Util.waitForLoad(driver);
				List<WebElement> submit = driver.findElements(By.xpath("//button[@id='buttonEmulateSubmit']"));
				
				if(submit.size()>0)
				{
					Util.ClickButton(driver, submitBtn);
					Util.waitForLoad(driver);
				}	
				if(driver.findElements(By.xpath("//span[text()='Please enter a valid Employee ID.']")).size() > 0)
					return false;
				
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Employee Log in with empid: "+employeeID,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			} catch (Exception ex) {
				ex.printStackTrace();
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Employee Log in with empid: "+employeeID,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.fail("Exception thrown:" + ex.getMessage());
			}
			return true;
		}

	public void loginMultipleEmployees(String arrEmp) throws Throwable {
		employeeID = DOTCRestService.empIdFromService.get(Integer.parseInt(arrEmp));
		System.out.println("employeeID is " + employeeID);
		ExtentTestManager.getTest().log(LogStatus.PASS, "EmployeeID for PilotList is:" + employeeID,
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		login(employeeID);
	}
	// Click on LogOut button
		public void clickOnLogOutLink() {
			try {
				Util.ClickElement(driver, logOutLink);
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Logout link is clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			} catch (Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "logout link is not clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue("logout link is not clicked", false);
			}
		}

	
// this method checks if it is this login page of dotc by chacking if logout button showsup!
	public boolean isThisLoginPage() throws InterruptedException {
		ArrayList<WebElement> quitElements= new ArrayList<WebElement>() {{add(error404);add(AutError);}};
		try {
			Util.checkElementDisplayed(driver, logout, quitElements ,3);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Passed -Navigated DOTC login page successfully!",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue(true);
			return true;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	//for login with reserve pilot ID
	//16/03/22
	public void loginReservePilot(String reserveEmpID) throws Throwable {
        try {
           Integer index = Integer.parseInt(reserveEmpID);
	
	       String reserveEmp = DOTCRestService.rsvempIdFromService.get(index);
	   System.out.println("reserve pilot ID is " +reserveEmp);
	     login(reserveEmp);
	    ExtentTestManager.getTest().log(LogStatus.PASS, "Reserve pilot ID " + reserveEmp);
			ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
	    Assert.assertTrue("Logged in with reserve pilot ID", true);
	    
        }
        catch (Exception ex) {
      	  ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception to login with reserve ID");
    				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
      	  Assert.assertFalse("Exception to login with reserve ID", true);
        }
}
	
	//for taking reserve pilot for rap activity
	//5-04-22
	public void loginReservePilotForRap(String reserveEmpID) throws Throwable {
        try {
           Integer index = Integer.parseInt(reserveEmpID);
	
	       String reserveEmp = DOTCRestService.rsvForRAP.get(index);
	   System.out.println("reserve pilot ID is " +reserveEmp);
	     login(reserveEmp);
	    ExtentTestManager.getTest().log(LogStatus.PASS, "Reserve pilot ID " + reserveEmp);
			ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
	    Assert.assertTrue("Logged in with reserve pilot ID", true);
	    
        }
        catch (Exception ex) {
      	  ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception to login with reserve ID");
    				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
      	  Assert.assertFalse("Exception to login with reserve ID", true);
        }
}
	
	// function to fetch SMSESSION cookie
	public void getCookieData() {
		try {

			for (Cookie cookie : driver.manage().getCookies()) {
				if (cookie.getName().equalsIgnoreCase("SMSESSION")) {
					DOTCRestService.SMSESSION = cookie.getValue();
					//System.out.println("Cookie: "+ DOTCRestService.SMSESSION);
					break;
				}
			}
			if (DOTCRestService.SMSESSION == null) {
				Assert.assertFalse("Failed to get SMSESSION value", true);
			}
		} catch (Exception ex) {

		}
	}
	
	// function to click on remind me later if credentials is about to expire
	//20 Apr 2022
	public void clickRemindMeLater() {
		try {
			Util.waitForLoad(driver);
			String remindMeLater_xpath = "//button[@id='remindBtn']";
			WebElement ele = driver.findElement(By.xpath(remindMeLater_xpath));
			if(ele.isDisplayed())
				Util.ClickElement(driver, ele);
		} catch(Exception e) {}
	}

	// function to stop emulate
	// 25 Apr, 2022
	public void stopEmulate() {
		try {
			Util.ClickElement(driver, stopEmulateBtn);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on stop emulate button");
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
			Assert.assertTrue("Clicked on stop emulate button", true);
		} catch(Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on stop emulate button");
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
			Assert.assertTrue("Exception in clicking on stop emulate button", false);
		}
	}
	
	//17May22
	//LH sick emp login
	public void loginLHPilotForSICK(String lhEmpID) throws Throwable {
        try {
           login(DOTCFOSTransactions.employeeIDForSick);
	    ExtentTestManager.getTest().log(LogStatus.PASS, "Logging in with sick emp "  );
			ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
	    Assert.assertTrue("Logging in with sick emp", true);
	    
        }
        catch (Exception ex) {
      	  ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception to login with LH ID");
    				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
      	  Assert.assertFalse("Exception to login with LH ID", true);
        }
}
	
	// 21 Sep 2022
	// check for time out
	public void timeOutValidation() throws Throwable{
		try {
			int size = driver.findElements(By.xpath("//button[normalize-space()='Yes, Keep me signed in']")).size();
			if(size > 0)
				Util.ClickElement(driver, driver.findElement(By.xpath("//button[normalize-space()='Yes, Keep me signed in']")));
		} catch(Exception ex) {}
	}
	
}

