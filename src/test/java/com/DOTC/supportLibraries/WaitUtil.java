/**
 * 
 */
package com.DOTC.supportLibraries;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author 375669
 *
 */

public class WaitUtil {

	static long time = WebDriverFactory.getImplicitlyWait();

	//private static WebDriver driver;

	public WaitUtil(WebDriver driver) {

		//WaitUtil.driver = driver;
	}

	/**
	 * 
	 * Wait for element to be located
	 */
	public static void waitForPressenceOfElementLocated(WebElement element,WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	/**
	 * Implicit wait for application
	 */
	public static void implicitWait(WebDriver driver) {
		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	

}
