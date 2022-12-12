package com.DOTC.pageObjects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.DOTC.stepDefinitions.DOTCCalendarSteps;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;

public class DOTCCalendarScreen extends DOTCCommon {

	DOTCRestService dotc = new DOTCRestService();
	DOTCLogInScreen dotclogin = new DOTCLogInScreen();
	DOTCMyInfoScreen dotcMyInfo = new DOTCMyInfoScreen();
	
	String employeeID;
	public String jsonRespMonthwiseformonth = "";
	
	// String employeeID = "";

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//a[contains(text(), 'DOTC/RAS')]")

	})
	public static WebElement DOTC_RAS;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//a[contains(@href,'/home')]")

	})
	public WebElement PageHeader;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//li[@class='header-text active']")

	})
	WebElement Calendar;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'col-sm-12 header-pilot-info']//a[1]") })

	WebElement pilotName;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'fc-center']//h2[contains(.,'2020')]") })
	WebElement calMonthHeader;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class='fc-left']/button")

	})
	WebElement calPrevNavigationButton;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class='fc-right']/button")

	})
	WebElement calNextNavigationButton;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'pilot-info-popup']")

	})
	WebElement pilotInfoDisplay;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'pilot-info-popup']//h3[contains(.,'My Information')]")

	})
	WebElement pilotInfoHeader;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[@class = 'pilot-info-popup']//h3[contains(.,'My Qualifications')]")

	})
	WebElement pilotQualHeader;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[contains(@class,'future')]")

	})
	List<WebElement> calFutureDate;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[contains(@class,'past')]")

	})
	List<WebElement> calPastDate;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[contains(@class,'inactiveCrewMonth')]")

	})
	List<WebElement> calInactiveDates;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[contains(@data-date,'07')]")

	})
	WebElement calSelectedDate;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[contains(@class,'today')]")

	})
	WebElement calCurrentDate;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(text(),'253')]")

	})
	WebElement MySequence;
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(text(),'253')]/parent::div")

	})
	WebElement MySequencetime;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"sequenceNumber\"]")

	})
	WebElement MySeqtext;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//td[@class = 'fc-day fc-widget-content fc-fri fc-today activeCrewMonth']")

	})
	WebElement CalDeptTime;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//td[@class = 'fc-day fc-widget-content fc-sun fc-future activeCrewMonth']")

	})
	WebElement CalArriveTime;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[@class = 'sequenceDetailsNewFormat']/div[4]//table[@class = 'table']/tbody/tr/td[6]")

	})
	WebElement SeqDeptTime;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[@class = 'sequenceDetailsNewFormat']/div[5]//table[@class = 'table']/tbody/tr/td[9]")

	})
	WebElement SeqArriveTime;
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='myCalendar']//table") })
	WebElement calendar;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Pick Up Outside DOTC']") })
	WebElement pickUpOutsideDOTC;
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Pick Up DOTC']") })
	WebElement pickUpDOTC;
	
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Pick Up Outside DOTC']") })
	List<WebElement> pickUpOutsideDOTCs;
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Pick Up DOTC']") })
	List<WebElement> pickUpDOTCs;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/aabp-app/div/main/div[2]/aabp-home/div[2]/div[1]/div/div[2]/div[2]/aac-ballot-switch/div[2]/div[1]/label")
			// "//*[.='Pick Up DOTC']/../../..//*[.='On']")
	})
	WebElement OnBtnpickUpDOTC;
	
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Pick Up DOTC']/../../../div[2]//*[.='On']")
			// "//*[.='Pick Up DOTC']/../../..//*[.='Off']")
	})
	List <WebElement> OnBtnpickUpDOTCs;

	// this is for mobile
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Pick Up DOTC']/../div//*[.='On']")
			// "//*[.='Pick Up DOTC']/../../..//*[.='Off']")
	})
	List <WebElement> OnBtnpickUpDOTC_ms;
	

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/aabp-app/div/main/div[2]/aabp-home/div[2]/div[1]/div/div[2]/div[2]/aac-ballot-switch/div[2]/div[2]/label")
			// "//*[.='Pick Up DOTC']/../../..//*[.='Off']")
	})
	WebElement OffBtnpickUpDOTC;
	
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Pick Up DOTC']/../../../div[2]//*[.='Off']")
			// "//*[.='Pick Up DOTC']/../../..//*[.='Off']")
	})
	List <WebElement> OffBtnpickUpDOTCs;

	// this is for mobile
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Pick Up DOTC']/../div//*[.='Off']")
			// "//*[.='Pick Up DOTC']/../../..//*[.='Off']")
	})
	List <WebElement> OffBtnpickUpDOTC_ms;
	
	
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/aabp-app/div/main/div[2]/aabp-home/div[2]/div[1]/div/div[2]/div[3]/aac-ballot-switch/div[2]/div[1]/label")
			// "//*[.='Pick Up Outside DOTC']/../../..//*[.='On']")
	})
	WebElement OnBtnpickUpOutsideDOTC;

	
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using ="//*[.='Pick Up Outside DOTC']/../../../div[2]//*[.='On']" )
			// "//*[.='Pick Up Outside DOTC']/../../..//*[.='On']")
	})
	List<WebElement> OnBtnpickUpOutsideDOTCs;
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Pick Up Outside ']/../div//*[.='On']")
			// "//*[.='Pick Up Outside DOTC']/../../..//*[.='On']")
	})
	List<WebElement> OnBtnpickUpOutsideDOTC_ms;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/div/aabp-app/div/main/div[2]/aabp-home/div[2]/div[1]/div/div[2]/div[3]/aac-ballot-switch/div[2]/div[2]/label")
			// "//*[.='Pick Up Outside DOTC']/../../..//*[.='Off']")
	})
	WebElement OffBtnpickUpOutsideDOTC;


	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Pick Up Outside DOTC']/../../../div[2]//*[.='Off']")
			// "//*[.='Pick Up DOTC']/../../..//*[.='Off']")
	})
	List <WebElement> OffBtnpickUpOutsideDOTCs;
	//for mobile v
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Pick Up Outside DOTC']/../div//*[.='Off']")
			// "//*[.='Pick Up DOTC']/../../..//*[.='Off']")
	})
	List <WebElement> OffBtnpickUpOutsideDOTC_ms;

	// Elements used in the Calander page
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//a/i[contains(@class,'icon-info icon-large')]")

	})
	public static WebElement infoIcon;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Search']")

	})
	public static WebElement searchTab;
	
	//Slider  Pickup Outside  Dotc 
	@CacheLookup
	@FindAll({
					@FindBy(how = How.XPATH, using = "//*[.='Pick Up Outside DOTC']/../../../div[2]//*[@class='slider round'] ")
					
			})
	WebElement pickupOutsideDotcToggle;
			
	//Slider-Status  Pickup  Outside Dotc 
	@CacheLookup
	@FindAll({
						@FindBy(how = How.XPATH, using = "//*[.='Pick Up Outside DOTC']/../../../div[2]//*[@class='slider round']//span[contains(text(),'On')] ")
							
					})
	WebElement pickupOutsideDotcToggleStatus;	
			
	//Slider Pickup Dotc 
		@CacheLookup
		@FindAll({
				@FindBy(how = How.XPATH, using = "//*[.='Pick Up DOTC']/../../../div[2]//*[@class='slider round'] ")
				
		})
		WebElement pickupDotcToggle;
		
	//Slider- Status Pickup Dotc 
			@CacheLookup
			@FindAll({
					@FindBy(how = How.XPATH, using = "//*[.='Pick Up DOTC']/../../../div[2]//*[@class='slider round']//span[contains(text(),'On')] ")
					
			})
	WebElement pickupDotcToggleStatus;
			
			//Sick header
			@CacheLookup
			@FindAll({
					@FindBy(how = How.XPATH, using = "//div[contains (@class,'header-sick-info')]/span")
					
			})
	    WebElement sickHeader;
			
			@CacheLookup
			@FindAll({
					@FindBy(how = How.XPATH, using = "//div[contains(@class,'card toolTipCard')]/div/h4[contains(text(),'RO')]")
					
			})
	    WebElement roHeader;
			
			
			
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='History']")})
	public static WebElement historyTab;

	public DOTCCalendarScreen() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);

	}

	// This method is used to click on info icon
	public void clickOnIcon() {
		try {

			Util.ClickElement(driver, infoIcon);
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public boolean isUp4OnInDOTC() {
		return Util.waitForElementClickable(driver, Stream.concat(OnBtnpickUpDOTCs.stream(), OnBtnpickUpDOTC_ms.stream()).collect(Collectors.toList()));
	}

	public boolean isUp4OffInDOTC() {
		return Util.waitForElementClickable(driver, Stream.concat(OffBtnpickUpDOTCs.stream(), OffBtnpickUpDOTC_ms.stream()).collect(Collectors.toList()));
	}
	
	public boolean isUp4OnOutDOTC() {
		return Util.waitForElementClickable(driver, Stream.concat(OnBtnpickUpOutsideDOTCs.stream(), OnBtnpickUpOutsideDOTC_ms.stream()).collect(Collectors.toList()));
	}

	public boolean isUp4OffOutDOTC() {
		return Util.waitForElementClickable(driver,Stream.concat(OffBtnpickUpOutsideDOTCs.stream(), OffBtnpickUpOutsideDOTC_ms.stream()).collect(Collectors.toList())); 
				//OffBtnpickUpOutsideDOTCs);
	}

	public boolean isUp4Calendar() {
		 Util.waitFor(driver, calendar, 6);
		 ExtentTestManager.getTest().log(LogStatus.PASS, "Calendar grid is visible",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 
		 return true;
	}

	public boolean isUp4PickUpOutsideDOTC() {
		 Util.waitFor(driver, pickUpOutsideDOTCs, 6);
		 ExtentTestManager.getTest().log(LogStatus.PASS, "Pickup Outside DOTC Header is visible",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 return true;
	}

	public boolean isUp4PickUpDOTC() {
		 Util.waitFor(driver, pickUpDOTCs, 6);
		 ExtentTestManager.getTest().log(LogStatus.PASS, "Pickup DOTC Header is visible",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		  return true;
	}

	public boolean elementApear(By by) {
		if (null == Util.waitForObjToLoad(driver, by, 6))
			return false;
		return true;
	}

	// This method is used to verify Application (DOTC) page
	public void verifyPageHeader() {
		try {

			if (Util.waitFor(driver, PageHeader)) {
				Assert.assertTrue("Page Header DOTC/RAS is visible", true);
			} else
				Assert.assertFalse("Page Header DOTC/RAS is Not Visible", true);
			Util.waitForLoad(driver);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// This method is used to click the calendar tab
	public void clickOnCalendar() {
		try {
			Util.ClickElement(driver, Calendar);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Calendar button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Calendar button is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// This method is used to fetch the Pilot name from the service and comparing
	// with the UI
	public void emulateLH_Validation() {
		String pilotname = pilotName.getText();
		String pilotNameUI = pilotname.replace(" |", "").trim();
		String jsonresponseformonth = getCrewMemberJsonResponse(null);
		String firstname = DOTCRestService.readJson("$.firstName", jsonresponseformonth);
		String lastname = DOTCRestService.readJson("$.lastName", jsonresponseformonth);
		String pilotNameService = firstname + " " + lastname;
		if (pilotNameUI.equalsIgnoreCase(pilotNameService)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Pilot name from Service is matched with UI",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Pilot name from Service is matched with UI", true);
		}

		else {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Pilot name from Service is not matched with UI",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Pilot name from Service is not matched with UI", false);
		}
	}
	
	//18-01-2022
	public void clickOnPilotName() {
		try {
			Util.ClickElement(driver, pilotName);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on pilot name",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception while clicking on pilot name",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	public void PilotInfoDisplay() {
		try {
			if (pilotInfoDisplay.isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Pilot information is displayed",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			} else
				ExtentTestManager.getTest().log(LogStatus.PASS, "Pilot information is displayed",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Pilot information is not displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void navigateToPrevMonth() {
		try {

			Util.specialClick(driver, calPrevNavigationButton);
			Util.waitForLoad(driver);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Previous navigation button",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Calendar screen is not displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	public void navigateToNextMonth() throws Exception{
		try {

			Util.ClickButton(driver, calNextNavigationButton);
			Util.waitForLoad(driver);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Navigation to next month",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

	}

	public boolean verifyCurrentDateAsLastDateOfTheMonth() {
		String attributeOfDate = "";
		boolean lastDayFlag = false;

		try {
			attributeOfDate = calCurrentDate.getAttribute("data-date");
			String[] splitDate = attributeOfDate.split("-");
			String onlyDate = splitDate[2];
			Integer lastDayOfTheMonth = Util.getLastDayOfTheMonth();
			System.out.println(onlyDate);
			System.out.println(lastDayOfTheMonth);
			if (lastDayOfTheMonth.equals(Integer.parseInt(onlyDate))) {
				lastDayFlag = true;
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return lastDayFlag;
	}

	public boolean verifyCurrentDateAsFirstDateOfTheMonth() {
		String attributeOfDate = "";
		boolean lastDayFlag = false;

		try {
			attributeOfDate = calCurrentDate.getAttribute("data-date");
			String[] splitDate = attributeOfDate.split("-");
			String onlyDate = splitDate[2];
			Integer firstDayOfTheMonth = Util.getfirstDayOfTheMonth();
			if (firstDayOfTheMonth.equals(Integer.parseInt(onlyDate))) {
				lastDayFlag = true;
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return lastDayFlag;
	}

	public void validateCurrentContractualMonthHeader() {
		try {
			Month curmonth = Util.getCurrentMonth();
			String currentContractualMonth = curmonth.toString();
			System.out.println("The month is: " + calMonthHeader.getText());
			if (calMonthHeader.getText().contains(currentContractualMonth)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "My Info Header For current Month",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Valid contractual month", true);
			}

			else {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "My Info Header For current Month",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Invalid contractual month", true);
			}

			Util.waitForLoad(driver);

		} catch (Exception ex) {
			ex.getStackTrace();
		}

	}

	public void validateNextContractualMonthHeader() {
		try {
			Month curmonth = Util.getCurrentMonth();
			Month nextContractualMonth = curmonth.plus(1);
			String strNextContractualMonth = nextContractualMonth.toString();
			System.out.println("The month is: " + calMonthHeader.getText());
			if (calMonthHeader.getText().contains(strNextContractualMonth)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "My Info Header For Next Month",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Valid contractual month", true);
			}

			else {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "My Info Header For Next Month",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Invalid contractual month", true);
			}

			Util.waitForLoad(driver);

		} catch (Exception ex) {
			ex.getStackTrace();
		}

	}

	public void validatePrevContractualMonthHeader() {
		try {
			Month curmonth = Util.getCurrentMonth();
			Month prevContractualMonth = curmonth.minus(1);
			String strPrevContractualMonth = prevContractualMonth.toString();
			System.out.println("The month is: " + calMonthHeader.getText());
			if (calMonthHeader.getText().contains(strPrevContractualMonth)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "My Info Header For Previous Month",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Valid contractual month", true);
			}

			else {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "My Info Header For Next Month",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Invalid contractual month", true);
			}
			Util.waitForLoad(driver);

		} catch (Exception ex) {
			ex.getStackTrace();
		}

	}

	public void validatePilotInfoHeadersAndMonth() {
		try {

			String myInfoHeaderText = pilotInfoHeader.getText();
			String calMonthHeaderText = calMonthHeader.getText();
			String[] subCurrentMonth = calMonthHeaderText.split(" ");
			String onlyCurrentMonth = subCurrentMonth[0];
			String subStrMonth = onlyCurrentMonth.substring(0, 3);
			String onlyYear = subCurrentMonth[1];
			if (myInfoHeaderText.startsWith("My Information For") && myInfoHeaderText.contains(subStrMonth)
					&& myInfoHeaderText.contains(onlyYear))
				Assert.assertTrue("Valid My Information Header", true);

			else

				Assert.assertTrue("Invalid My Information Header", false);

			Util.waitForLoad(driver);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "My Info Header for current month",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "My Info Header for current month",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			ex.getStackTrace();
		}

	}

	public Date calDepartTime(Date date) throws Exception {
		String title = MySequencetime.getAttribute("title");
		String Departure = title.substring(13, 18);
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Date DepartureTime = dateFormat.parse(Departure);
		Date CalanderDepartureDate = Date.from(DepartureTime.toInstant().plus(Duration.ofHours(1)));
		System.out.println("caldepttime" + CalanderDepartureDate);
		return CalanderDepartureDate;
	}

	public Date calArriveTime(Date date) throws Exception {
		String title = MySequencetime.getAttribute("title");
		String Arrival = title.substring(28, 33);
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Date Arrivaltime = dateFormat.parse(Arrival);
		Date CalanderArrivalDate = Date.from(Arrivaltime.toInstant().minus(Duration.ofMinutes(30)));
		System.out.println("calarivetime" + CalanderArrivalDate);
		return CalanderArrivalDate;
	}

	public void clickOnMySequence() throws Exception {
		try {
			Util.ClickElement(driver, MySequence);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "My sequence is displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "My sequence is not displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	public void validateSequencePage() {
		try {

			if ((Util.waitFor(driver, MySeqtext) != null)) {
				Assert.assertTrue("Sequence page is loaded", true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence page is visible",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence page is not visible",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public static int convert_String_To_Number(String numStr) {
		char ch[] = numStr.toCharArray();
		int sum = 0;
		// get ascii value for zero
		int zeroAscii = (int) '0';
		for (char c : ch) {
			int tmpAscii = (int) c;
			sum = (sum * 10) + (tmpAscii - zeroAscii);
		}
		return sum;
	}

	public Date seqDepartTime(Date date) throws Exception {
		int a = convert_String_To_Number(SeqDeptTime.getText());
		int hours = a / 100; // since both are ints, you get an int
		int minutes = a % 100;
		String s1 = String.format("%d:%02d", hours, minutes);
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Date SeqDepartureTime = dateFormat.parse(s1);
		// System.out.println("seqdepttime" + SeqDepartureTime);
		return SeqDepartureTime;

	}

	public Date seqAriveTime(Date date) throws Exception {
		int a = convert_String_To_Number(SeqArriveTime.getText());
		int hours = a / 100; // since both are ints, you get an int
		int minutes = a % 100;
		String s2 = String.format("%d:%02d", hours, minutes);
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Date SeqArrivalTime = dateFormat.parse(s2);
		// System.out.println("seqarivetime" + SeqArrivalTime);
		return SeqArrivalTime;

	}

	public void ValidateDepartureDate(Date date) throws Exception {
		if (calDepartTime(date).equals(seqDepartTime(date))) {
			// System.out.println("Departure date is validated");
			Assert.assertTrue("Departure date is validated", true);
		} else {
			Util.waitForLoad(driver);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			Assert.assertFalse("Departure is not validated", true);
		}

	}

	public void ValidateArrivalDate(Date date) throws Exception {
		if (calArriveTime(date).equals(seqAriveTime(date))) {
			// System.out.println("Arrival date is validated");
			Assert.assertTrue("Arrival date is validated", true);
		} else {
			Util.waitForLoad(driver);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			Assert.assertFalse("Arrival date is not validated", true);
		}

	}

	public String getCrewMemberJsonResponse(String month) {
		String jsonresponseformonth = "";
		try {

			this.employeeID = DOTCLogInScreen.employeeID;
			String employeeNum = employeeID;
			System.out.println("The pilot id is:  " + employeeID);
			String payloadJson = "{\"crewMemberKeyDTO\": { \"employeeNumber\":" + employeeNum
					+ ", \"airlineCode\":\"AA\"}}";
			String endpoint = "CrewMemberService/getCrewMember";
			jsonresponseformonth = dotc.ccsService(payloadJson, endpoint);
			System.out.println("The json is: " + jsonresponseformonth);

			jsonRespMonthwiseformonth = jsonresponseformonth;

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return jsonresponseformonth;
	}

	public void validateCalInformationForCurrentMonth() {
		try {

			Month curmonth = Util.getCurrentMonth();
			Integer currentYear = Util.getCurrentYear();
			String CurrentMonth = curmonth.toString().substring(0, 3) + currentYear.toString();
			String jsonresponseformonth = getCrewMemberJsonResponse(null);
			String calCurrentMonth = DOTCRestService.readJson("$.bidStatuses[0].contractMonth", jsonresponseformonth);
			if (CurrentMonth.equalsIgnoreCase(calCurrentMonth)) {
				Assert.assertTrue("Current Contractual month is validated", true);
				if (verifyCurrentDateAsFirstDateOfTheMonth()) {
					Util.ClickElement(driver, calNextNavigationButton);
					validateCurrentContractualMonthHeader();
				} else {
					validateCurrentContractualMonthHeader();
				}
			} else {
				Assert.assertFalse("Current Contractual month is validated", true);
				Util.waitForLoad(driver);
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Current contractual month validation",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}

	}

	public void validateCalInformationForNextMonth() {
		try {

			// ******* Reading from Service
			Month curmonth = Util.getCurrentMonth();
			Month nextMonth = curmonth.plus(1);
			Integer currentYear = Util.getCurrentYear();
			String strNextMonth = nextMonth.toString().substring(0, 3) + currentYear.toString();
			String jsonRes = "{\r\n"
					+ "    \"contractMonths\": [\""+strNextMonth+"\"],\r\n"
					+ "    \"crewMemberKeyDTO\": {\r\n"
					+ "        \"employeeNumber\": "+DOTCLogInScreen.employeeID+",\r\n"
					+ "        \"airlineCode\": \"AA\"\r\n"
					+ "    }\r\n"
					+ "}";
			String jsonresponseformonth = dotc.ccsService(jsonRes, "/CrewMemberService/getCrewMember");//getCrewMemberJsonResponse(strNextMonth.toString());
			String calNextMonth = DOTCRestService.readJson("$.bidStatuses[0].contractMonth", jsonresponseformonth);
			if (strNextMonth.equalsIgnoreCase(calNextMonth)) {
				Assert.assertTrue("next Contractual month is validated", true);
				navigateToNextMonth();
				validateNextContractualMonthHeader();
			} else {
				Assert.assertFalse("next Contractual month is validated", true);
				Util.waitForLoad(driver);
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Next contractual month validation",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}

	}

	public void validateCalInformationForPrevMonth() {
		try {

			// ******* Reading from Service
			Month curmonth = Util.getCurrentMonth();
			Month previousMonth = curmonth.minus(1);
			Integer currentYear = Util.getCurrentYear();
			String jsonresponseformonth = getCrewMemberJsonResponse(null);
			String strPrevMonth = previousMonth.toString().substring(0, 3) + currentYear.toString();
			String calPrevMonth = DOTCRestService.readJson("$.bidStatuses[1].contractMonth", jsonresponseformonth);
			if (strPrevMonth.equalsIgnoreCase(calPrevMonth)) {
				Assert.assertTrue("previous Contractual month is validated", true);
				if (verifyCurrentDateAsFirstDateOfTheMonth()) {
					validatePrevContractualMonthHeader();
				} else {
					navigateToPrevMonth();
					validatePrevContractualMonthHeader();
				}

			} else {
				Assert.assertFalse("previous Contractual month is validated", true);
				Util.waitForLoad(driver);
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Previous contractual month validation",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}

	}

	public void clickOnSearchTab() throws InterruptedException {
		Util.ClickButton(driver, searchTab, 6);
	}

	//For verifying Pickup DOTC toggle buton
	 public boolean validatePickupDotcToggle() {
		 boolean toggle=false;
		if( Util.waitFor(driver,pickupDotcToggle ,2)) {
			toggle=true;
		 System.out.println("toggle pickup Dotc is visible");
		 ExtentTestManager.getTest().log(LogStatus.PASS, "Pick up DoTC Toggle is visible",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 
		 if(!Util.waitFor(driver, pickupDotcToggleStatus, 2)) {
			 System.out.println("Slider is off");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Pickup DOTC Slider is turned  off",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 }
		 else {
			 System.out.println("Slider is on");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Pickup DOTC Slider is turned  ON",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 }
		}
		return toggle;
		 
		 }
		 
		  
	//For verifying Pickup outside DOTC toggle button
	public boolean validatePickupOutsideDotcToggle() {
		 boolean toggle=false;
		 if( Util.waitFor(driver,pickupOutsideDotcToggle ,2)) {
			 toggle=true;
		System.out.println("toggle Pickup Outside  is visible");
		 ExtentTestManager.getTest().log(LogStatus.PASS, "Pickup Outside Toggle is visible",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 
		 if(!Util.waitFor(driver, pickupOutsideDotcToggleStatus, 2)) {
			 System.out.println("Slider is off");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Passed-Pickup Outside silder is turned off",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 }
		 else {
			 System.out.println("Slider is on");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Passed -Pickup Outside slider is turned ON",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 }
		 }
		return toggle;
		 
		 }
	
	//To get the Rap Activity date
	//16/03/22
	public String getRapActivitydate(String plannedDate){
		
		String rapActDate="";
			LocalDate curDate = LocalDate.now();
					if (plannedDate.equalsIgnoreCase("current")) {
						String currentDay = Integer.toString(curDate.getDayOfMonth());
						if (currentDay.length() < 2)
							currentDay = "0" + currentDay;
						Month currentMon = curDate.getMonth();
						Integer currentYr = curDate.getYear();
						rapActDate = currentDay + currentMon.toString().substring(0, 3) + currentYr.toString().substring(2, 4);
						
			}
					
					return rapActDate;		
		}
	
	    
		//to validate the color of Rap activity on current date
	    //16/03/22
		public void validateRAPActivityColor (String plannedDate) {
			try {
				String rapActDate="";
				LocalDate curDate = LocalDate.now();
				if (plannedDate.equalsIgnoreCase("current")) {
					String currentDay = Integer.toString(curDate.getDayOfMonth());
					if (currentDay.length() < 2)
						currentDay = "0" + currentDay;
					Month currentMon = curDate.getMonth();	
					rapActDate = currentDay +" "+ currentMon.toString().substring(0, 3) ;
			
					String RapTitle ="RAP(2000-0959) :"+" "+""+rapActDate+"";	
					String blueColor= "rgba(77, 180, 250, 1)";
			
					String backgroundColor = driver.findElement(By.xpath("//div[contains(@title,'" + RapTitle
						+ "')]/parent::a")).getCssValue("background-color");
					if (backgroundColor.equalsIgnoreCase(blueColor)) {
						System.out.println("Color matched");
						ExtentTestManager.getTest().log(LogStatus.PASS, " Rap activity color matched ");
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
							
						Assert.assertTrue("Rap activity color matched ", true);
					}
					else {
						System.out.println("exception - not matched");
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception - Rap activity color not matched",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
						Assert.assertFalse("Exception -Rap activity color not matched ", true);
					}
				}
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating Rap activity color",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
					Assert.assertFalse("Exception in validating Rap activity color", true);
			}
		}


		//to get the training activity date current and next day	
		//16/03/22
		public String[] getTrainingActivityDate(String plannedDate) {
			String startDate="";
			String endDate="";
			LocalDate curDate = LocalDate.now();
			if (plannedDate.equalsIgnoreCase("current")) {
				String currentDay = Integer.toString(curDate.getDayOfMonth());
				if (currentDay.length() < 2)
					currentDay = "0" + currentDay;
				Month currentMon = curDate.getMonth();
				Integer currentYr = curDate.getYear();
				startDate = currentDay + currentMon.toString().substring(0, 3) + currentYr.toString().substring(2, 4);
				

				LocalDate futureDt = curDate.plusDays(1);
				String futureDay = Integer.toString(futureDt.getDayOfMonth());
				if (futureDay.length() < 2)
					futureDay = "0" + futureDay;
				Month futureMon = futureDt.getMonth();
				Integer yr = futureDt.getYear();
				endDate = futureDay + futureMon.toString().substring(0, 3) + yr.toString().substring(2, 4);
			}

			String[] arr = new String[] { startDate, endDate };
			
			
			return (arr);
			
		}
		
		//to validate the color of training activity on current date
		//16-03-22
	  public void validateTrainingActivity (String plannedDate) {
		  try {
		  	String startDate="";
			String endDate="";
			LocalDate curDate = LocalDate.now();
			if (plannedDate.equalsIgnoreCase("current")) {
				String currentDay = Integer.toString(curDate.getDayOfMonth());
				if (currentDay.length() < 2)
					currentDay = "0" + currentDay;
				Month currentMon = curDate.getMonth();
				startDate = currentDay+ " "+ currentMon.toString().substring(0, 3);
				

				LocalDate futureDt = curDate.plusDays(1);
				String futureDay = Integer.toString(futureDt.getDayOfMonth());
				if (futureDay.length() < 2)
					futureDay = "0" + futureDay;
				Month futureMon = futureDt.getMonth();
				endDate = futureDay +" " + futureMon.toString().substring(0, 3) ;
				
				String trainingTitle= "1R :"+" "+startDate+" "+"00:00"+" -"+" "+endDate+" "+"23:59";
			
				String greenColor = "rgba(209, 213, 50, 1)"; 
			
				String backgroundColor = driver.findElement(By.xpath("//div[contains(@title,'"+trainingTitle+"')]/parent::a"))
					.getCssValue("background-color");
				if (backgroundColor.equalsIgnoreCase(greenColor)) {
					System.out.println("Color matched");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Passed- training activity color matched");
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
						Assert.assertTrue("passed- color matched", true);
				} else {
					System.out.println("Training Color not matched");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed- training activity not color matched");
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
					Assert.assertFalse("failed- color not matched", true);
				}
			}
		  } catch(Exception ex) {
			  ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating training activity color");
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver));
			Assert.assertFalse("Exception in validating training activity color", true);
		  }
	  }
	  
	  //To verify seq color code in calendar
	  public void validateSeqColor(String seqNum, String seqDate) throws ParseException {

			SimpleDateFormat seqDateFrmt = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat seqDtFormatUI = new SimpleDateFormat("ddMMM");
			String dateSeq = seqDtFormatUI.format(seqDateFrmt.parse(seqDate));
			String seqTitle = seqNum + " : " + dateSeq.substring(0, 2) + " " + dateSeq.substring(2, 5).toUpperCase();
			
			String seqColor = driver.findElement(By.xpath("//div[contains(@title,'" + seqTitle + "')]//parent::a"))
					.getCssValue("background-color");
			String colorGrey = "rgba(79, 101, 114, 1)";

			if (seqColor.equalsIgnoreCase(colorGrey)) {

				ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence activity color matches in calendar",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			} else {

				ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence activity color doesn't match in calendar",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Sequence activity color doesn't match in calendar", true);
			}
		}

	  
	   //To verify credited removal color code in calendar
		public void validateSeqRemovalColor(String seqNum, String seqDate) throws ParseException {
			try {
				SimpleDateFormat seqDateFrmt = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat seqDtFormatUI = new SimpleDateFormat("ddMMM");
				String dateSeq = seqDtFormatUI.format(seqDateFrmt.parse(seqDate));
				String seqTitle = seqNum + " : " + dateSeq.substring(0, 2) + " " + dateSeq.substring(2, 5).toUpperCase();
			
				String seqColor = driver.findElement(By.xpath("//div[contains(@title,'" + seqTitle + "')]//parent::a"))
						.getCssValue("background-color");
				String colorWhite = "rgba(255, 255, 255, 1)";

				if (seqColor.equalsIgnoreCase(colorWhite)) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "Credited removal color matches in calendar",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

				} else {

					ExtentTestManager.getTest().log(LogStatus.FAIL, "Credited removal color doesn't match in calendar",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse("Credited removal color doesn't match in calendar", true);
				}
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating credited removal color",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Exception in validating credited removal color", true);
			}

		}

		
		 //To verify DFP color code in calendar
		public void validateDFPColor(String DFPDate) throws ParseException {
			try {
				String dfpTitle = "D24 : " + DFPDate.substring(0, 2) + " " + DFPDate.substring(2, 5);
			
				String DFPColor = driver.findElement(By.xpath("//div[contains(@title,'" + dfpTitle + "')]//parent::a"))
						.getCssValue("background-color");
				String colorYellow = "rgba(250, 175, 0, 1)";

				if (DFPColor.equalsIgnoreCase(colorYellow)) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "DFP color matches in calendar",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

				} else {

					ExtentTestManager.getTest().log(LogStatus.FAIL, "DFP color doesn't match in calendar",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse("DFP color doesn't match in calendar", true);
				}
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating DFP color",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Exception in validating DFP color", true);
			}

		}

		 //To verify Absence color code in calendar
		public void validateAbsenceColor(String absenceDate) throws ParseException {
			try {
				String absTitle = "RL : " + absenceDate.substring(0, 2) + " " + absenceDate.substring(2, 5);
			
				String absenceColor = driver.findElement(By.xpath("//div[contains(@title,'" + absTitle + "')]//parent::a"))
						.getCssValue("background-color");
				String colorRed = "rgba(195, 0, 25, 1)";

				if (absenceColor.equalsIgnoreCase(colorRed)) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "Absence activity color matches in calendar",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

				} else {

					ExtentTestManager.getTest().log(LogStatus.FAIL, "Absence activity color doesn't match in calendar",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse("Absence activity color doesn't match in calendar", true);
				}
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating absence activity color",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Exception in validating absence activity color", true);
			}

		}

		
		 //To verify vacation color code in calendar
		public void validateVacationColor(String vacationDate) throws ParseException {
			try {
				String VCTitle = "VC : " + vacationDate.substring(0, 2) + " " + vacationDate.substring(2, 5);
			
				String vacationColor = driver.findElement(By.xpath("//div[contains(@title,'" + VCTitle + "')]//parent::a"))
						.getCssValue("background-color");
				String colorOrange = "rgba(255, 115, 24, 1)";

				if (vacationColor.equalsIgnoreCase(colorOrange)) {

					ExtentTestManager.getTest().log(LogStatus.PASS, "Vacation color matches in calendar",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

				} else {
					
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Vacation color doesn't match in calendar",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse("Vacation color doesn't match in calendar", true);
				}
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating vacation color",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Exception in validating vacation color", true);
			}

		}
		
		// function to click on history tab
		//22-03-2022
		public void clickOnHistoryTab() {
			try {
				Util.ClickElement(driver, historyTab);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on History tab",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Clicked on History tab", false);
			} catch (Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on History tab",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertFalse("Exception in clicking on History tab", true);
			}
		}

		//17May22
		//to get sick date
				public String[] getSickDate(String plannedDate) {
					String startDate="";
					String endDate="";
					LocalDate curDate = LocalDate.now();
					if (plannedDate.equalsIgnoreCase("current")) {
						String currentDay = Integer.toString(curDate.getDayOfMonth());
						if (currentDay.length() < 2)
							currentDay = "0" + currentDay;
						Month currentMon = curDate.getMonth();
						Integer currentYr = curDate.getYear();
						startDate = currentDay + currentMon.toString().substring(0, 3) + currentYr.toString().substring(2, 4);
						

						LocalDate futureDt = curDate.plusDays(1);
						String futureDay = Integer.toString(futureDt.getDayOfMonth());
						if (futureDay.length() < 2)
							futureDay = "0" + futureDay;
						Month futureMon = futureDt.getMonth();
						Integer yr = futureDt.getYear();
						endDate = futureDay + futureMon.toString().substring(0, 3) + yr.toString().substring(2, 4);
					}

					String[] arr = new String[] { startDate, endDate };
					
					
					return (arr);
					
				}

//17May22
	public void sickMsgHeader() {
	try {
					String sickMsg= "Currently SK";
					String header =  sickHeader.getText();
				if(header.equalsIgnoreCase(sickMsg)) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Sick header is present",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
				 else {
						
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Sick header is not present",
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertFalse("Sick header is not present", true);
					}
				} catch(Exception ex) {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception : Sick header is not present",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse("Exception : Sick header is not present", true);
				}
				}	
	
//22July22
	public void validateROWindow(String seqDate ,String seqEndDatee ) {
		try {
		
			System.out.println("date from cal  " +seqDate);
			
		   SimpleDateFormat seqDateORG = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat seqDtNeed = new SimpleDateFormat("dd MMM");
		 String dateSeqq = seqDtNeed.format(seqDateORG.parse(seqDate)).toUpperCase(); 
		 System.out.println("date needed  " +dateSeqq);
		 
		 WebElement SeqClick = this.driver.findElement(By.xpath("//div[contains(@title,'RO: "+dateSeqq+"')]"));
		 System.out.println("xpath for seq click  " +SeqClick);
		 Util.ClickElement(driver,SeqClick );
		 Util.waitFor(driver, roHeader);
		 if(roHeader.isDisplayed()) {
			 Assert.assertTrue("RO header is displayed ", true);
				currentScenario.embed(Util.takeScreenshot(this.driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "RO header is displayed",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						
				}
		 else {
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "RO header is not  displayed",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("RO header is not displayed", true);
			}
		 //start date 
		 WebElement startDate = this.driver.findElement(By.xpath("//div[contains(@class,'card-body')]/div[contains(text(),'Start: "+seqDate+"')]"));
		if(startDate.isDisplayed()){
			Assert.assertTrue("start date is displayed ", true);
			currentScenario.embed(Util.takeScreenshot(this.driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "start date is displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					
			}
	 else {
			
			ExtentTestManager.getTest().log(LogStatus.FAIL, "start date is not  displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("start date is not displayed", true);
		}
			
		//end date
		
		 //WebElement seqEndDate = this.driver.findElement(By.xpath("//div[contains(@class,'card-body')]/div[contains(text(),'End: "+seqEndDatee+"')]"));

		WebElement seqEndDate = this.driver.findElement(By.xpath("//div[contains(@class,'card-body')]/div[contains(text(),'End:')]"));

		if(seqEndDate.isDisplayed()){
				Assert.assertTrue("end date is displayed ", true);
				currentScenario.embed(Util.takeScreenshot(this.driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "end date is displayed",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						
				}
		 else {
				
				ExtentTestManager.getTest().log(LogStatus.FAIL, "end date is not  displayed",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("end date is not displayed", true);
			}
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception ", true);
		}
	}
}




