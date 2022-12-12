package com.DOTC.pageObjects;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DOTC.stepDefinitions.DOTCMasterSteps;
import com.DOTC.supportLibraries.DriverManager;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;

public class DOTCCommon extends DOTCMasterSteps {
	static Logger log = Logger.getLogger(DOTCCommon.class);
	WebDriver driver;

	public DOTCCommon() {
		try {
			driver = DriverManager.getAnyAvailableWebDriver();
		} catch (Exception e) {
			driver = DriverManager.getAppiumDriver();
		}

	}

	public DOTCCommon(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Function to validate Object Existence
	 * 
	 * @param ObjName
	 */
	public void launchApplication() {
		try {
			driver.get(TestData.strGlobalURL);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Launch Application",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			// ExtentTestManager.startTest("Hello", "Hello World");
			try {
				driver.manage().window().maximize();
			} catch (Exception e) {
			}
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void launchCSApplication() {
		try {
			driver.get(TestData.csURL);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Launched CrewScheduler Application",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			// ExtentTestManager.startTest("Hello", "Hello World");
			try {
				driver.manage().window().maximize();
			} catch (Exception e) {
			}
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void clickBtn(WebElement ele) {
		try {
			Util.ClickButton(driver, ele);
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public void clickElemnt(WebElement ele) {
		try {
			Util.ClickElement(driver, ele);
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail("exception thrown during click" + ex.getMessage());
		}
	}

	public void verifyObjExistence(WebElement ele, String strObjectName) {
		try {
			Util.waitForSpinnerLoad(driver);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			if (!Util.waitFor(driver, ele)) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Validate object " + strObjectName + " existence",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.fail("Expected object '" + strObjectName + "' not displayed.");
			}
			ExtentTestManager.getTest().log(LogStatus.PASS, "Validate object " + strObjectName + " existence",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Validate object " + strObjectName + " Not present",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.fail("exception thrown during verification" + ex.getMessage());
		}
	}

	/* Method to encode password */

	public String encode(String stringToEncode) {
		byte[] bytesEncoded = Base64.encodeBase64(stringToEncode.getBytes());
		return new String(bytesEncoded);

	}

	/* Method to decode password */
	public String decode(String stringToDecode) {
		byte[] bytesDecoded = Base64.decodeBase64(stringToDecode.getBytes());
		return new String(bytesDecoded);
	}

	public void waitForObjLoad(By obj) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 240);
			// wait.until(ExpectedConditions.elementToBeClickable(obj));
			wait.until(ExpectedConditions.visibilityOfElementLocated(obj));

		} catch (Exception ex) {
			// System.out.println("Object was not found or clickable. Error Message:
			// "+ex.getStackTrace());
			Assert.fail("Object was not found or clickable");
		}

	}

	/**
	 * Function to launch dotc app: url/page base on env url address!
	 */
	public void launchApplication(String env, String page) {
		String url = TestData.strGlobalURL;
		try {
			switch (env) {
			case "qa cloud":
				url = TestData.qaCloudAdd;
				break;
			case "stage cloud":
				url = TestData.stageCloudAdd;
				break;
			case "qa":
				url = TestData.qaAdd;
				break;
			case "uat":
				url = TestData.uatAdd;
				break;	
			case "stage":
				url = TestData.stgAdd;
				break;	
			case "dev":
				url = TestData.strGlobalAdd;
				break;
			default:
				url = TestData.strGlobalAdd;
			}
			driver.get(String.format("%s/%s", url, page));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Launch Application",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			try {
				driver.manage().window().maximize();
			} catch (Exception e) {
			}
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void refreshApplication() {
		driver.get(TestData.strGlobalURL);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Refresh Done");
		Util.waitForLoad(driver);
		Util.defaultwait(10000);
	}

}
