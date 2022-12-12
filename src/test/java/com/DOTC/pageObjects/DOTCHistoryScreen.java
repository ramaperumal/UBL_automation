package com.DOTC.pageObjects;

import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import com.DOTC.supportLibraries.*;

import com.relevantcodes.extentreports.LogStatus;

public class DOTCHistoryScreen extends DOTCCommon {
	@FindAll({ @FindBy(how = How.XPATH, using = "//h2[contains(@class, 'historyLabel page-title')]")})
	public static WebElement historyHeading;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(@class, 'history-form-wrapper')]//div[contains(@class, 'visible-lg')]//p-calendar[contains(@name, 'startDate')]")})
	public static WebElement historyStartDate;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(@class, 'history-form-wrapper')]//div[contains(@class, 'visible-lg')]//p-calendar[contains(@name, 'endDate')]")})
	public static WebElement historyEndDate;
	
	// function to validate history page
	// 22-03-2022
	public void validateHistoryPage() {
		try {
			Util.waitForSpinnerLoad(driver);
			Util.ClickElement(driver, historyHeading);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Verified history page",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Verified history page", false);
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in verifying History page",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertFalse("Exception in verifing in History page", true);
		}
	}
		
	// function to validate start date has current -1 date as default date
	//22-03-2022
	public void validateHistoryStartDateHasYestedayDate() {
		try {
			Util.waitForLoad(driver);
			Util.ClickElement(driver, driver.findElements(By.xpath("//p-calendar[@name='startDate']/span/button")).get(0));
			WebElement ele = driver.findElement(By.xpath("//a[contains(@class,'ui-state-active')]//span"));
			String day = ele.getAttribute("data-day");
			day = (day.length()==1?"0"+day:day);
			
			String month = ele.getAttribute("data-month");
			month = (month.length()==1?"0"+month:month);
			
			String year = ele.getAttribute("data-year");
			String date = year+"-"+month+"-"+day;
			
			SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
					
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, -1);
			String calDate = smf.format(c.getTime());
					
			if(date.equals(calDate)) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Validated default start date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Validated default start date", false);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Different default start date, from UI: "+date+", expected: "+calDate,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse("Different default start date, from UI: "+date+", expected: "+calDate, true);
			}
		} catch(Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating start date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertFalse("Exception in validating start date", true);
		}
	}
	
	// function to validate end date has current date as default date
	//22-03-2022
	public void validateHistoryEndDateHasYestedayDate() {
		try {
			Util.ClickElement(driver, driver.findElements(By.xpath("//p-calendar[@name='endDate']/span/button")).get(0));
			Util.waitForLoad(driver);
			WebElement ele = driver.findElement(By.xpath("//a[contains(@class,'ui-state-active')]//span"));
			String day = ele.getAttribute("data-day");
			day = (day.length()==1?"0"+day:day);
			
			String month = ele.getAttribute("data-month");
			month = (month.length()==1?"0"+month:month);
			
			String year = ele.getAttribute("data-year");
			String date = year+"-"+month+"-"+day;
			
			SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
					
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			String calDate = smf.format(c.getTime());
						
			if(date.equals(calDate)) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Validated default end date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Validated default end date", false);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Different default end date, from UI: "+date+", expected: "+calDate,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Different default end date, from UI: "+date+", expected: "+calDate, true);
			}
		} catch(Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating start date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertFalse("Exception in validating start date", true);
		}
	}
}