package com.DOTC.supportLibraries;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

//import com.experitest.selenium.MobileWebDriver;

import io.appium.java_client.AppiumDriver;

/**
 * A generic WebDriver manager, which handles multiple instances of WebDriver.
 * 
 * @author Cognizant
 */
public class DriverManager {

	/*
	 * Used for Multithreading of WebDriver Object
	 */
	@SuppressWarnings("rawtypes")
	private static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<AppiumDriver>();
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	//private static ThreadLocal<MobileWebDriver> seetestDriver = new ThreadLocal<MobileWebDriver>();
	public static ThreadLocal<SeleniumTestParameters> testParameters = new ThreadLocal<SeleniumTestParameters>();
	

	static Logger log;

	static {
		log = Logger.getLogger(DriverManager.class);
	}

	// WebDriver Object Creation

	public static WebDriver getWebDriver() {
		if (webDriver.get() == null) {
			// this is need when running tests from IDE
			log.info("Thread has no WedDriver, creating new one");
			setWebDriver(DriverFactory.createInstanceWebDriver(null));
		}
		log.debug("Getting instance of remote driver" + webDriver.get().getClass());
		return webDriver.get();
	}

	// AppiumDriver Object Creation

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver() {
		if (appiumDriver.get() == null) {
			// this is need when running tests from IDE
			log.info("Thread has no Appium driver, creating new one");
			setAppiumDriver(DriverFactory.createInstance(null));
		}
		log.debug("Getting instance of remote driver" + appiumDriver.get().getClass());
		return appiumDriver.get();
	}

	public static void setAppiumDriver(AppiumDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.appiumDriver.set(driver);
	}

	public static WebDriver getAnyAvailableWebDriver() {
		if (webDriver.get() != null) {
			log.debug("Getting instance of remote driver" + webDriver.get().getClass());
			return webDriver.get();
		}
		if (appiumDriver.get() != null) {
			log.debug("Getting instance of remote driver" + appiumDriver.get().getClass());
			return appiumDriver.get();
		}
		log.info("Thread has no Appium driver, returning null!");
		return null;
	}
	public static void setWebDriver(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.webDriver.set(driver);
	}

	/*public static void setSeetestDriver(MobileWebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.seetestDriver.set(driver);
	}*/
	
	public static void setTestParameters(SeleniumTestParameters testParameters) {
		DriverManager.testParameters.set(testParameters);

	}

	public static SeleniumTestParameters getTestParameters() {
		return testParameters.get();
	}
	
}