
package com.DOTC.supportLibraries;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
//import java.util.NoSuchElementException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 * Class to encapsulate utility functions of the framework
 * 
 * @author Cognizant
 */
public class Util {
	static Logger log = Logger.getLogger(Util.class);
	private static Pattern pattern;
	private static Matcher matcher;

	private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
	private static final Integer PAGELOADTIME = 120;
	private static final Integer OBJLOADTIME = 30;
	private static final Integer OBJLOADTIMENOEXIST = 10;

	private Util() {
		// To prevent external instantiation of this class

	}

	/**
	 * Function to get the separator string to be used for directories and files
	 * based on the current OS
	 * 
	 * @return The file separator string
	 */
	public static String getFileSeparator() {
		System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
		return System.getProperty("file.separator");
	}

	public static String getAbsolutePath() {
		System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
		String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
		return relativePath;
	}

	public static String getResultsPath() {
		File path = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "Results");
		if (!path.isDirectory()) {
			path.mkdirs();
		}

		return path.toString();
	}

	public static String getOldResultPath() {

		File path = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "ResultsOld");

		if (!path.isDirectory()) {
			path.mkdirs();
		}

		return path.toString();
	}

	public static String getTargetPath() {

		File targetPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator()
				+ "cucumber-report");

		return targetPath.toString();
	}

	public static String getAllurePath() {

		File targetPath = new File(
				Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator() + "site");

		return targetPath.toString();
	}

	public static String getAllureSourcePath() {

		File targetPath = new File(Util.getAbsolutePath() + Util.getFileSeparator() + "target" + Util.getFileSeparator()
				+ "allure-results");

		return targetPath.toString();
	}

	public static String getAllureDestPath() {

		String targetPath = TimeStamp.getInstanceAllure();

		return targetPath.toString();
	}

	public static byte[] takeScreenshot(WebDriver driver) {
		if (driver == null) {
			throw new RuntimeException("Report.driver is not initialized!");
		}

		if (driver.getClass().getSimpleName().equals("HtmlUnitDriver") || driver.getClass().getGenericSuperclass()
				.toString().equals("class org.openqa.selenium.htmlunit.HtmlUnitDriver")) {
			return null; // Screenshots not supported in headless mode
		}

		if (driver.getClass().getSimpleName().equals("RemoteWebDriver")) {
			Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
			if (capabilities.getBrowserName().equals("htmlunit")) {
				return null; // Screenshots not supported in headless mode
			}
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
		} else {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
	}

	public static String base64Screenshot(WebDriver driver) {
		String strSnapshot = "";
		try {
			strSnapshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return strSnapshot;
	}

	/*
	 * public static byte[] takeScreenshot(WebDriver driver) throws IOException {
	 * 
	 * File scrFile = new File(driver.client.capture()); int i = 0; while
	 * (!scrFile.exists()) { try { Thread.sleep(1000); i++; if (i > 30) { break; } }
	 * catch (InterruptedException ex) { ex.printStackTrace(); } } byte[] bFile =
	 * Files.readAllBytes(new File(scrFile.toString()).toPath());
	 * 
	 * return bFile; }
	 */

	/**
	 * Function to return the current time
	 * 
	 * @return The current time
	 * @see #getCurrentFormattedTime(String)
	 */
	public static Date getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * Function to return the current month
	 * 
	 * @return The current month
	 * 
	 */
	public static Month getCurrentMonth() {
		LocalDate currentDate = LocalDate.now();
		Month m = currentDate.getMonth();
		return m;
	}

	/**
	 * Function to first Day Of the Month
	 * 
	 * @return first Day Of the Month
	
	 */
	public static int getfirstDayOfTheMonth() {
		Calendar cal = Calendar.getInstance();
		int firstDayOftheMonth = cal.getActualMinimum(Calendar.DATE);
		return firstDayOftheMonth;
	}
	
	/**
	 * Function to last Day Of the Month
	 * 
	 * @return last Day Of the Month
	 * 
	 */
	public static int getLastDayOfTheMonth() {
		Calendar cal = Calendar.getInstance();
		int lastDayOftheMonth = cal.getActualMaximum(Calendar.DATE);
		return lastDayOftheMonth;
	}

	/**
	 * Function to return the current year
	 * 
	 * @return The current year
	 * 
	 */
	public static int getCurrentYear() {
		LocalDate currentDate = LocalDate.now();
		int curYear = currentDate.getYear();
		return curYear;
	}

	/**
	 * Function to return the current time, formatted as per the DateFormatString
	 * setting
	 * 
	 * @param dateFormatString The date format string to be applied
	 * @return The current time, formatted as per the date format string specified
	 * @see #getCurrentTime()
	 * @see #getFormattedTime(Date, String)
	 */
	public static String getCurrentFormattedTime(String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime()).toUpperCase();
	}

	public static String getCurrentFormattedTimePlusOne(String dateFormatString, String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		if (Calendar.DATE <= 15) {
			calendar.set(Calendar.DATE, 16);
		} else {
			calendar.setTime(dateFormat.parse(date));
			calendar.add(Calendar.DATE, 1);
		}
		return dateFormat.format(calendar.getTime()).toUpperCase();
	}

	/**
	 * Function to format the given time variable as specified by the
	 * DateFormatString setting
	 * 
	 * @param time             The date/time variable to be formatted
	 * @param dateFormatString The date format string to be applied
	 * @return The specified date/time, formatted as per the date format string
	 *         specified
	 * @see #getCurrentFormattedTime(String)
	 */
	public static String getFormattedTime(Date time, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.format(time);
	}

	/**
	 * Function to get the time difference between 2 {@link Date} variables in
	 * minutes/seconds format
	 * 
	 * @param startTime The start time
	 * @param endTime   The end time
	 * @return The time difference in terms of hours, minutes and seconds
	 */
	public static String getTimeDifference(Date startTime, Date endTime) {
		long timeDifferenceSeconds = (endTime.getTime() - startTime.getTime()) / 1000; // to
		// convert
		// from
		// milliseconds
		// to
		// seconds
		long timeDifferenceMinutes = timeDifferenceSeconds / 60;

		String timeDifferenceDetailed;
		if (timeDifferenceMinutes >= 60) {
			long timeDifferenceHours = timeDifferenceMinutes / 60;

			timeDifferenceDetailed = Long.toString(timeDifferenceHours) + " hour(s), "
					+ Long.toString(timeDifferenceMinutes % 60) + " minute(s), "
					+ Long.toString(timeDifferenceSeconds % 60) + " second(s)";
		} else {
			timeDifferenceDetailed = Long.toString(timeDifferenceMinutes) + " minute(s), "
					+ Long.toString(timeDifferenceSeconds % 60) + " second(s)";
		}

		return timeDifferenceDetailed;
	}

	// Utility Methods for UA Application

	public static void enterText(WebDriver driver, WebElement element, String text) {
		try {

			if (Util.waitForElementClickable(driver, element)) {
				element.click();
				element.clear();
				element.sendKeys(text);
				// Thread.sleep(100);

			} else {
				throw new NoSuchElementException("Unable to find Input Element");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pressKey(WebDriver driver, WebElement element, Keys tab) {

		try {

			if (Util.waitFor(driver, element)) {
				element.sendKeys(tab);
				// Thread.sleep(100);
			} else {
				throw new NoSuchElementException("Unable to find Input Element");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void navigateBack(WebDriver driver) {
		try {
			driver.navigate().back();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitForLoad(WebDriver driver) {
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, PAGELOADTIME);
			wait.until(pageLoadCondition);
			Util.defaultwait(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// updated code 18th Oct 2022
	public static void waitForSpinnerLoad(WebDriver driver) {
		try {		
			while(driver.findElements(By.xpath("//*[contains(@class, 'dw-loading-active')]//*[@aria-role='progressbar']")).size()>0)
                Thread.sleep(2000); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static Boolean validateText(WebDriver driver, WebElement element, String strText, boolean blnExactMatch) {
		Boolean blnTextMatch = false;
		try {
			if (Util.waitFor(driver, element)) {
				String eleText = element.getText().trim();
				if (eleText.length() < 1) {
					eleText = element.getAttribute("value");
				}
				if ((blnExactMatch && eleText.equalsIgnoreCase(strText))
						|| (!blnExactMatch && eleText.contains(strText))) {
					blnTextMatch = true;
				} else {
					System.out.println("Expected text: " + strText + ". Actual text: " + eleText);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return blnTextMatch;
	}

	public static void ClickButton(WebDriver driver, WebElement ele) {
		try {

			if (Util.waitForElementClickable(driver, ele)) {
				ele.click();
				// log.info("Object clicked:" + ele.getText());
			} else {

				throw new NoSuchElementException("Unable to find Button to Tap");
			}
		} catch (Exception e) {
			try {
				ele.sendKeys(" ");
				ele.sendKeys("\n");
			} catch (Exception ex) {
				throw ex;
			}
		}
	}

	public static void ClickElement(WebDriver driver, WebElement ele) {
		try {

			Actions actn = new Actions(driver);
			actn.moveToElement(ele).click().build().perform();

		} catch (Exception e) {
			try {
				ele.sendKeys(" ");
				ele.sendKeys("\n");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public static void defaultwait(int intWaitVal) {
		try {
			Thread.sleep(intWaitVal);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void SelectFrmDropDown(WebDriver driver, By ele, String txtToBeSelected) {
		try {
			WebElement element = driver.findElement(ele);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Select select = new Select(element);
			select.selectByVisibleText(txtToBeSelected);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void SelectFrmDropDownIndex(WebDriver driver, WebElement element) {
		try {
			// WebElement element = driver.findElement(ele);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Select select = new Select(element);
			select.selectByIndex(1);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	

	

	public static void SelectFrmDropDownByVal(WebDriver driver, By ele, String txtToBeSelected) {
		try {
			WebElement element = driver.findElement(ele);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Select select = new Select(element);
			select.selectByValue(txtToBeSelected);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void SelectFrmDropDown(WebDriver driver, WebElement ele, String txtToBeSelected, boolean exactMatch) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(ele).build().perform();
			Select select = new Select(ele);
			if (exactMatch) {
				select.selectByVisibleText(txtToBeSelected);
			} else {
				List<WebElement> option = select.getOptions();
				for (int j = 0; j < option.size(); j++) {
					if (option.get(j).getText().toLowerCase().trim().contains(txtToBeSelected.trim().toLowerCase())) {
						select.selectByIndex(j);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		}

	}

	public static void ClickOnText(WebDriver driver, List<WebElement> elementList, String strText,
			boolean blnExactMatch) {
		try {
			WebElement element = null;

			for (int i = 0; i < elementList.size(); i++) {
				element = elementList.get(i);
				String elementTxt = element.getText();
				if (elementTxt.length() < 1) {
					elementTxt = element.getAttribute("value");
				}
				if ((blnExactMatch && elementTxt.equalsIgnoreCase(strText))
						|| (!blnExactMatch && elementTxt.contains(strText))) {

					if (Util.waitForElementClickable(driver, element)) {
						// Thread.sleep(500);
						log.info("Tap on text:" + strText);
						element.click();
					} else {
						throw new NoSuchElementException("Unable to find Button to Tap");
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static WebElement waitFor(WebDriver driver, By ele) {
		WebElement elmntRtrned = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIME);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			if (element != null) {
				elmntRtrned = element;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return elmntRtrned;
	}

	public void setClipboardText(String strData) {
		try {
			Thread.sleep(500);
			Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection selection = new StringSelection(strData);
			c.setContents(selection, selection);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getClipboardText() {
		String strData = "";
		try {
			Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
			strData = (String) c.getData(DataFlavor.stringFlavor);
			System.out.println("printed" + strData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strData;
	}

	public static String GetDate(String strDate) {

		try {
			String arrElmnt = null;
			Integer intDate = null;
			if (strDate.toUpperCase().contains("TODAY")) {
				Calendar cal = Calendar.getInstance();

				if (strDate.contains("+")) {
					arrElmnt = strDate.substring(strDate.indexOf("+") + 1);
					intDate = Integer.parseInt(arrElmnt);
					cal.add(Calendar.DATE, intDate);

				} else if (strDate.contains("-")) {

					arrElmnt = strDate.substring(strDate.indexOf("-") + 1);
					intDate = Integer.parseInt(arrElmnt);
					cal.add(Calendar.DATE, -intDate);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");
				strDate = sdf.format(cal.getTime());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strDate;
	}

	public static Boolean waitFor(WebDriver driver, WebElement ele) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIME);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			
			ex.printStackTrace();
			throw ex;
			
		}
		return objectPresence;
	}

	public static Boolean waitFor(WebDriver driver, WebElement ele, int intLoadingTime) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, intLoadingTime);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return objectPresence;
	}

	public static boolean waitFor(WebDriver driver, List<WebElement> els, int intLoadingTime) {
		for (WebElement we : els)
			try {
				waitFor(driver, we, intLoadingTime);
				return true;
			} catch (Exception e) {
			}
		return false;
	}

	public static Boolean waitForNoExist(WebDriver driver, WebElement ele) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIMENOEXIST);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(ele));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return !objectPresence;
	}

	public static Boolean waitForElementClickable(WebDriver driver, WebElement ele) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIME);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}

		} catch (Exception ex) {
			throw ex;
		}
		return objectPresence;
	}

	public static Boolean waitForElementClickable(WebDriver driver, WebElement ele, int waitInSec) {
		Boolean objectPresence = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitInSec);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));

			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}

		} catch (Exception ex) {
			throw ex;
		}
		return objectPresence;
	}

	public static boolean waitForElementClickable(WebDriver driver, List<WebElement> els) {
		for (WebElement we : els)
			try {
				return waitForElementClickable(driver, we, 2);
			} catch (Exception e) {
			}
		log.info("No such a element: " + els.toString());
		return false;
	}

	public static Boolean waitForElementClickable(WebDriver driver, String xpth) {
		Boolean objectPresence = false;
		try {

			WebDriverWait wait = new WebDriverWait(driver, OBJLOADTIME);
			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpth))));
			if (element != null) {
				objectPresence = true;
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			}

		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return objectPresence;
	}

	public static String objAttribute(WebDriver driver, String attribute, WebElement objName) {
		String objAttr = "";
		try {
			if (Util.waitFor(driver, objName)) {
				objAttr = objName.getAttribute(attribute);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return objAttr;
	}

	public static Boolean valObjEnabled(WebDriver driver, WebElement objName) {
		Boolean blnEnable = false;
		try {

			if (Util.waitFor(driver, objName)) {
				if (objName.isEnabled()) {
					blnEnable = true;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return blnEnable;
	}

	public static Boolean validate24HoursTimeFormat(String attrText) {
		try {

			pattern = Pattern.compile(TIME24HOURS_PATTERN);
			matcher = pattern.matcher(attrText);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return matcher.matches();
	}

	public static void specialClick(WebDriver driver, WebElement elmnt) {
		try {

			new Actions(driver).moveToElement(elmnt).click().build().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Boolean setDataSpecial(WebDriver driver, WebElement elmnt, String strText) {
		String strActualText = "";
		boolean blnDataSetStatus = false;
		try {
			if (Util.waitForElementClickable(driver, elmnt)) {
				elmnt.click();
				elmnt.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				elmnt.sendKeys(Keys.DELETE);
				elmnt.sendKeys(strText);
			}
			if (elmnt.getAttribute("value").equalsIgnoreCase(strText)) {
				blnDataSetStatus = true;
			}
			// elmnt.sendKeys(Keys.TAB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(blnDataSetStatus);
		return blnDataSetStatus;
	}

	public static WebElement waitForObjToLoad(WebDriver driver, By ObjName) {
		long t0, t1;
		long intTimeInMillis = 15000;
		boolean blnObjectExistFlag = false;
		WebElement elementReturned = null;
		try {
			t0 = System.currentTimeMillis();
			do {
				Thread.sleep(200);
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjName)));
				List<WebElement> ElementList = driver.findElements(ObjName);
				for (int i = 0; i < ElementList.size(); i++) {
					elementReturned = ElementList.get(i);
					if (elementReturned != null) {
						new Actions(driver).moveToElement(elementReturned).build().perform();
					}
					if (elementReturned.isDisplayed()) {
						blnObjectExistFlag = true;
						break;
					}
				}
				ElementList.clear();
				t1 = System.currentTimeMillis();
			} while (t1 - t0 < intTimeInMillis && !blnObjectExistFlag);
		} catch (Exception ex) {

		}
		return elementReturned;
	}

	public static WebElement waitForObjToLoad(WebDriver driver, By by, int intTimeInMillis) {
		long t0, t1;
		boolean blnObjectExistFlag = false;
		WebElement elementReturned = null;
		try {
			t0 = System.currentTimeMillis();
			do {
				Thread.sleep(200);
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjName)));
				List<WebElement> ElementList = driver.findElements(by);
				for (int i = 0; i < ElementList.size(); i++) {
					elementReturned = ElementList.get(i);
					if (elementReturned != null) {
						new Actions(driver).moveToElement(elementReturned).build().perform();
					}
					if (elementReturned.isDisplayed()) {
						blnObjectExistFlag = true;
						break;
					}
				}
				ElementList.clear();
				t1 = System.currentTimeMillis();
			} while (t1 - t0 < intTimeInMillis && !blnObjectExistFlag);
		} catch (Exception ex) {

		}
		return elementReturned;
	}

	public static WebElement findChildElement(WebDriver driver, WebElement element, By ObjName) {
		long t0, t1;
		long intTimeInMillis = 15000;
		boolean blnObjectExistFlag = false;
		WebElement elementReturned = null;
		try {
			t0 = System.currentTimeMillis();
			do {
				Thread.sleep(200);
				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjName)));
				List<WebElement> ElementList = element.findElements(ObjName);
				for (int i = 0; i < ElementList.size(); i++) {
					elementReturned = ElementList.get(i);
					if (elementReturned != null) {
						new Actions(driver).moveToElement(elementReturned).build().perform();
					}
					if (elementReturned.isDisplayed()) {
						blnObjectExistFlag = true;
						break;
					}
				}
				ElementList.clear();
				t1 = System.currentTimeMillis();
			} while (t1 - t0 < intTimeInMillis && !blnObjectExistFlag);
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return elementReturned;
	}

	public static void ClickButton(WebDriver driver, WebElement ele, int n) throws InterruptedException {
		for (int i = 0; i < n; i++)
			try {
				ele.click();
				break;
			} catch (Exception e) {
				Thread.sleep(200);
				if (i == n - 1)
					throw e;
			}
	}

	public static WebElement FindElementByXPath(WebDriver driver, String s, int n) throws InterruptedException {
		WebElement ele = null;
		for (int i = 0; i < n; i++)
			try {
				ele = driver.findElement(By.xpath(s));
				break;
			} catch (Exception e) {
				Thread.sleep(200);
				if (i == n - 1)
					throw e;
			}
		return ele;
	}

	public static List<WebElement> FindListOfElementsByXPath(WebDriver driver, String s, int n)
			throws InterruptedException {
		List<WebElement> ele = null;
		for (int i = 0; i < n; i++)
			try {
				ele = driver.findElements(By.xpath(s));
				break;
			} catch (Exception e) {
				Thread.sleep(200);
				if (i == n - 1)
					throw e;
			}
		return ele;
	}

// clicks <=n times until happen and quite if quite elemment apear any time
	public static void ClickButton(WebDriver driver, WebElement ele, ArrayList<WebElement> quitElements, int n)
			throws InterruptedException {
		for (int i = 0; i < n; i++)
			try {
				ele.click();
				break;
			} catch (Exception e) {
				for (WebElement we : quitElements)
					try {
						we.findElement(By.xpath("*"));
						log.error(String.format("Quit elemetn came up: %s", we));
						throw e;
					} catch (Exception e2) {
					}
				quitElements = new ArrayList<WebElement>() {
					{
					}
				};
				Thread.sleep(200);
				if (i == n - 1)
					throw e;
			}
	}

	// clicks <=n times until happen and quite if quite elemment apear any time
	public static void checkElementDisplayed(WebDriver driver, WebElement ele, ArrayList<WebElement> quitElements,
			int n) throws InterruptedException {
		for (int i = 0; i < n; i++)
			try {
				Thread.sleep(500);
				ele.isDisplayed();
				break;
			} catch (Exception e) {
				for (WebElement we : quitElements)
					try {
						we.findElement(By.xpath("*"));
						log.error(String.format("Quit elemetn came up: %s", we));
						throw e;
					} catch (Exception e2) {
					}
				quitElements = new ArrayList<WebElement>() {
					{
					}
				};
				Thread.sleep(200);
				if (i == n - 1)
					throw e;
			}
	}

	// returns a clmn set of a table
	public static HashSet<String> getClmnFromTableAsSet(WebDriver driver, WebElement table, int clmnN) {
		HashSet<String> ret = new HashSet<String>();
		String xp = "tbody/tr[%s]/td[%s]";
		int row = -1;
		int repeatedCount=0;
		String cell = null;
		while (true) {
			row++;
			cell = null;
			try {
				cell = table.findElement(By.xpath(String.format(xp, Integer.toString(row), Integer.toString(clmnN))))
						.getText();
				if(cell.length()>0) repeatedCount=0;
				else if(cell.length()<=0) repeatedCount++;
			} catch (Exception e) {
				repeatedCount++;
				if (row != 0 && repeatedCount>1)
					return ret;
			}
			if (cell == null || cell.length() < 1) {
				if (row != 0 && repeatedCount>1)
					return ret;
				continue;
			}
			ret.add(cell);
		}
	}

	// returns a clmn of a table
	public static ArrayList<String> getClmnFromTable(WebDriver driver, ArrayList<WebElement> candidTabels, int clmnN) {
		ArrayList<String> ret = new ArrayList<String>();
		String xp = "tbody/tr[%s]/td[%s]";
		WebElement tabel = null;
		for (WebElement we : candidTabels)
			try {
				we.findElement(By.xpath(String.format(xp, Integer.toString(1), Integer.toString(clmnN))));
				tabel = we;
				break;
			} catch (Exception e) {
			}
		if (tabel == null)
			return null;
		int row = -1;
		String cell = null;
		while (true) {
			row++;
			cell = null;
			try {
				cell = tabel.findElement(By.xpath(String.format(xp, Integer.toString(row), Integer.toString(clmnN))))
						.getText();
			} catch (Exception e) {
				if (row != 0)
					return ret;
			}
			if (cell == null || cell.length() < 1) {
				if (row != 0)
					return ret;
				continue;
			}
			ret.add(cell);
		}
	}

	public static void enterText(WebDriver driver, WebElement element, String text, int tryN)
			throws InterruptedException {
		for (int i = 0; i < tryN; i++)
			try {
				element.sendKeys(text);
				break;
			} catch (Exception e) {
				Thread.sleep(300);
				if (i == tryN - 1)
					throw e;
			}
	}

	public static List<WebElement> StaleElementHandleByIDList(WebDriver driver, String elementxpath, int n) {
		int count = 0;
		boolean clicked = false;
		List<WebElement> yourSlipperyElement = null;
		while (count < n && !clicked) {
			try {
				yourSlipperyElement = driver.findElements(By.xpath(elementxpath));

				clicked = true;
			} catch (StaleElementReferenceException e) {
				e.toString();
				count = count + 1;
			}
		}
		return yourSlipperyElement;
	}

	public static List<WebElement> FluentWaitForElementsList(WebDriver driver, String elementxpath, int n) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		List<WebElement> element = wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				List<WebElement> listOfElements = driver.findElements(By.xpath(elementxpath));
				return listOfElements;
			}

		});
		return element;
	}

	public static String url(WebDriver driver) {
		return driver.getCurrentUrl();
	}
}