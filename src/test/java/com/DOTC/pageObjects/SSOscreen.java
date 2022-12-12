package com.DOTC.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

import java.util.Base64;

public class SSOscreen extends DOTCCommon {
	public static String employeeID = "";
	TestData testData = new TestData();

	// ******* Elements used for AA Login page *******
	@FindBy(id = "userID")
	@CacheLookup
	private WebElement aaId;

	/*
	 * @FindBy(css = "#loginForm div:nth-of-type(6) div:nth-of-type(1) a")
	 * 
	 * @CacheLookup private WebElement firstTimeUser;
	 */
	@FindBy(css = "a.forgot-password")
	@CacheLookup
	private WebElement forgotPassword;

	@FindBy(id = "login")
	@CacheLookup
	private WebElement login;

	@FindBy(id = "password")
	@CacheLookup
	private WebElement password;

	/*
	 * @FindBy(css =
	 * "a[href='https://sam.qtcorpaa.aa.com/identity-ftur/user/FirstTimeLogin']")
	 * 
	 * @CacheLookup private WebElement registerNow;
	 * 
	 * @FindBy(css = "#loginForm div:nth-of-type(6) div:nth-of-type(2) a")
	 * 
	 * @CacheLookup private WebElement usageTerms;
	 */
	// Constructor made for DOTCLogin Screen
	public SSOscreen() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);

	}

// this method is used to enter user credentials for AA page
	public boolean isThisSSOpage(int tryN) throws InterruptedException {
		for (int i = 0; i < tryN; i++)
			try {
				Util.waitForElementClickable(driver, aaId);
				Util.waitForElementClickable(driver, password);
				return true;
			} catch (Exception ex) {
				if (i == tryN-1) {
					log.error("Failure: expected aaId or Password element is not present!");
					ex.printStackTrace();
					throw ex;
					}
				Thread.sleep(200);
			}
		return false;
	}

	// this method is used to enter employee id and login SSO
	public boolean login(String employeeID, String pass, int tryNo) throws InterruptedException {
		for (int i = 0; i < tryNo; i++)
			try {
				Util.enterText(driver, aaId, employeeID, 3);
				Util.enterText(driver, password, pass, 3);
				Util.ClickButton(driver, login, 3);
				return true;
			} catch (Exception ex) {
				if (i == tryNo - 1)
					throw ex;
				Thread.sleep(200);
			}
		return false;
	}

}
