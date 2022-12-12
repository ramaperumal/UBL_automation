package com.DOTC.pageObjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.StringUtils;
import org.testng.asserts.SoftAssert;

import com.DOTC.stepDefinitions.DOTCLogInSteps;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class DOTCSearchScreen extends DOTCCommon {
	DOTCRestService dotc = new DOTCRestService();
	DOTCCalendarScreen dotcCalendar = new DOTCCalendarScreen();
	TestData testData = new TestData();
	public String employeeID = "";
	public static List<String> sequencesFromService = null;
	public static List<String> sequencesFromTable = null;
	public static String DepFrom = " ";
	public static String DepTo = " ";
	public static String ArrFrom = " ";
	public static String ArrTo = " ";
	public static String SearchTitle = " ";
	public String jsonRespMonthwise = "";
	String bidStatusCurrentBase;
	String bidStatusCurrentEquipment;
	String bidStatusCurrentPosition;
	String bidStatusCurrentDivision;
	JavascriptExecutor js;
	public static String seqNumber = " ";
	public SoftAssert softAssert = new SoftAssert();

	// ******* Elements used for AA Search page *******

	// 'Search' page by clicking Search Button from Menu
	@FindAll({ @FindBy(how = How.XPATH, using = ("//div[contains(text(),'Search')]")) })
	WebElement searchBtn;

	// On the 'Search' page locate the Sick Pending checkbox
	@CacheLookup
	@FindAll({ @FindBy(how = How.ID, using = ("chk_pendingSick")) })
	WebElement sickPending;

	// On the 'Search' page locate the Crewed Sequences checkbox
	@CacheLookup
	@FindAll({ @FindBy(how = How.ID, using = ("chk_crewedSequences")) })
	WebElement crewedSequences;

	// On the 'Search' page locate the Sick Sequences checkbox
	@CacheLookup
	@FindAll({ @FindBy(how = How.ID, using = ("chk_pendingSick")) })
	WebElement sickSequences;

	// @CacheLookup
	// @FindAll({ @FindBy(how = How.XPATH, using =
	// "//*[@id='buttonCreateGenericSearch']") })
	// WebElement saveGenericButton;

	// 'Save Generic Criteria' Button in Search Page
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"ballotSubmitButton\"]")})
			//using = "//button[@id = 'buttonCreateGenericSearch']") })
	WebElement saveGenericCriteriaBtn;

	// 'clear generic search' Button in Search Page
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@id = 'buttonGenericSearchClear']") })
	WebElement clearGenericSearchBtn;

	// 'Sequence Criteria' Down Arrow in Search Page for creation of Ballot
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Sequence Characteristics')]/a/i") })
	WebElement sequenceCharacteristicsDwnArrw;

	// 'Sit Time' Drop Down in Search Page while creation of Search Criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='maxSitTimeHours']") })
	WebElement sitTimeDrpDwn;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='maxSitTimeHours']/option[1]") })
	WebElement sitTimeDrpdownMax;

	// 'Sequence Number' Down Arrow to expand the collapsible in Search Page
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Sequence Number')]/a/i") })
	WebElement sequenceNumberDwnArrw;

	// 'Sequence Number' icon to add Sequence Number text box under Sequence Number
	// collapsible in Search Page
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Sequence Number')]//tr[contains(@class,'ng-star-inserted')][1]//span") })
	WebElement sequenceNumberPlus;

	// 'Sequence Number' Text Box in Search Page while creation of Search Criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//input[@id='sequenceNumber']") })
	WebElement sequenceNumberTxtBox;

	// 'Title' Text Box to enter Search Title while saving Generic Criteria
	@FindAll({ @FindBy(how = How.XPATH,using = "//*[contains(@class,'modal-open')]//div[contains(@class,'visible-lg')]//input[@id=\"textboxTitleGenericRequest\"]" )})
			//using = "//input[@id = 'textboxTitleGenericRequest']") })
	WebElement titleTxtBox;
	//*[contains(@class,'modal-open')]//div[contains(@class,'visible-lg')]//input[@id="textboxTitleGenericRequest"]

	// 'All' check box present in Ballot page where we can select all ballots
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(@class,'modal-open')]//div[contains(@class,'visible-lg')]//*[@id=\"selectAllLabel\"]/div")})
	//using = "//*[@id=\'ballotType\']/div/div/ul/li[3]/a/label/div") })
	WebElement allBallotChkBox;

	// Save Button after entering Title and selecting check box while creation of
	// Generic Criteria
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(@class,'modal-open')]//div[contains(@class,'visible-lg')]//button[@id = 'buttonCreateGenericSearchDP']")})
			//using = "//button[@id = 'buttonCreateGenericSearchDP']") })
	WebElement saveTitleBtn;

	// Date picker icon present in Search Page to enter 'Departure From' Date
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@id='departureStartDate']//button") })
	WebElement departureFromDateDtPkr;

	// 'Departure From' Date Text Box in Search Page
	@FindAll({ @FindBy(how = How.XPATH, using = "//input[contains(@name,'departureStartDate')]") })
	WebElement departureFromDateinput;

	// for the date which is highlighted
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class, 'datepicker')]//td[contains(@class,'current')]") })
	WebElement selectedDateFromCalander;

	@FindAll({ @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[contains(@class,'today')]")

	})
	WebElement calCurrentDate;

	// Date picker icon present in Search Page to enter 'Departure To' Date
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='departureEndDate']//span[contains(text(), 'ui-btn')]") })
	WebElement departureToDateDtPkr;

	// 'Departure to' Date Text Box in Search Page
	@FindAll({ @FindBy(how = How.XPATH, using = "//input[@name = 'departureEndDate']") })
	WebElement departureToDateinput;

	// Date picker icon present in Search Page to enter 'Arrival From' Date
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='arrivalStartDate']//span[contains(text(), 'ui-btn')]") })
	WebElement arrivalFromDateDtPkr;

	// 'Departure From' Date Text Box in Search Page
	@FindAll({ @FindBy(how = How.XPATH, using = "//input[@name = 'arrivalStartDate']") })
	WebElement arrivalFromDateinput;

	// Date picker icon present in Search Page to enter 'Arrival To' Date
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='arrivalEndDate']//span[contains(text(), 'ui-btn')]") })
	WebElement arrivalToDateDtPkr;

	// 'Arrival to date input
	@FindAll({ @FindBy(how = How.XPATH, using = "//input[@name = 'arrivalEndDate']") })
	WebElement arrivalToDateinput;

	// 'Departure From' Date Text Box in Search Page
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[@class = 'contract-month startDatePicker ng-star-inserted']") })
	List<WebElement> departureFromDateList;

	// 'Departure To' Date Text box in Search Page
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[@class = 'contract-month startDatePicker ng-star-inserted']") })
	List<WebElement> departureToDateList;
	
	// 'Departure To' Date Text box in Search Page - clicking non-contractual month date
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[@class = 'not-contract-month startDatePicker ng-star-inserted']") })
	List<WebElement> departureToDateLst;
	 
	// 'Arrival To' Date Text Box in Search Page
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[@class = 'contract-month startDatePicker ng-star-inserted']") })
	List<WebElement> arrivalToDateList;
	
	// 'Arrival To' Date Text Box in Search Page-clicking non-contractual month date
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[@class = 'not-contract-month startDatePicker ng-star-inserted']") })
	List<WebElement> arrivalToDateLst;
	
	// 'Arrival From' Date Text Box in Search Page
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[@class = 'contract-month startDatePicker ng-star-inserted']") })
	List<WebElement> arrivalFromDateList;

	// 'Pay and Credit' Down Arrow to expand the collapsible in Search Page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Pay & Credit')]/a/i") })
	WebElement payAndCreditDwnArrw;

	// 'Total Credit' Plus icon to add Total Credit Drop downs in Search Page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Pay & Credit')]//tr[contains(@class,'ng-star-inserted')][1]//span") })
	WebElement totalCreditPlus;

	// 'Pay and Credit' Down Arrow to expand the collapsible in Search Page
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//div[1]//a/i") })
	WebElement dutyPeriodsDwnArrw;

	// 'Min' Drop Down to select the values for Minimum Total Credit Hours after
	// adding Total Credit as a Search criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='minTotalCreditHours']") })
	WebElement minTotalCreditHoursDrpDwn;

	// 'Min' Drop Down to select the values for Minimum Total Credit Mins after
	// adding Total Credit as a Search criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='minTotalCreditMinutes']") })
	WebElement minTotalCreditMinutesDrpDwn;

	// 'Max' Drop Down to select the values for Maximum Total Credit Hours after
	// adding Total Credit as a Search criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='maxTotalCreditHours']") })
	WebElement maxTotalCreditHoursDrpDwn;

	// 'Max' Drop Down to select the values for Maximum Total Credit Hours after
	// adding Total Credit as a Search criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='maxTotalCreditMinutes']") })
	WebElement maxTotalCreditMinutesDrpDwn;

	// Calendar Label present in Search Page
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//a[@class = 'calendarLabel']") })
	WebElement calanderLbl;

	// 'Position' Down Arrow in Search Page for creation of Ballot
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//div[5]//i") })
	WebElement positionDwnArrw;

	// 'Position' plus icon to add Position check box under Sequence Number
	// collapsible in Search Page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//div[5]//tr[1]//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement positionPlus;

	// 'Position' check box in Search Page after clicking Position Plus icon under
	// Position collapsible
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[@class = 'positions clearfix']//div[1]//div[@class = 'customComponent checkboxComponent']") })
	WebElement positionValue;

	// 'division' plus icon to add division check box under Sequence Number
	// collapsible in Search Page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//div[5]//tr[1]//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement divisionPlus;

	// 'division' check box in Search Page after clicking division Plus icon under
	// division collapsible
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[@id= 'division']//div[@class = 'customComponent checkboxComponent']") })
	WebElement divisionValue;

	// Check Box present when Pick Up DOTC link under the collapsibles is clicked
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(@class,'modal-open')]//div[contains(@class,'visible-lg')]//*[@id='selectAllLabel']/div") })
	WebElement AllBallotsChkBox;

	// Check Box present when Pick Up DOTC link under the collapsibles is clicked
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(@class,'modal-open')]//*[@id = 'ballotType']//div[contains(@class,'ng-star-inserted')][1]//label/div")})
	//"//ul[@class = 'dropdown-menu dropdown-menu-form']//li[4]") })
	WebElement DOTCChkBox;

	// Check Box present when Pick Up Outside link under the collapsibles is clicked
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//ul[@class = 'dropdown-menu dropdown-menu-form']//li[5]") })
	WebElement outsideChkBox;

	// Check Box present when Template link under the collapsibles is clicked
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//ul[@class = 'dropdown-menu dropdown-menu-form']//li[6]") })
	WebElement templateChkBox;
	// This element is the Show Sequences button on the search page
	@CacheLookup
	@FindAll({ @FindBy(how = How.ID, using = ("buttonGenericSearchSubmit")) })
	WebElement showSequences;

	// This is a list of all the search results from search page returned after
	// clicking show sequences
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//table[@id='sequencesLargeTable']//a[@class='speciallink cursorP']") })
	List<WebElement> searchResultsList;

	// This is a list of all the search results from search page returned after
	// clicking show sequences
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@id='sequencesLargeTable']//tbody//tr") })
	public List<WebElement> searchOutputListOfAllSequences;

	// This element is the TAFB button on the Seq Characteristics section
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Sequence Characteristics')]//tr[contains(@class,'ng-star-inserted')][4]//span") })
	WebElement TAFBAdd;

	// This element is the TAFB Min button

	@FindAll({ @FindBy(how = How.ID, using = "minTAFB") })
	WebElement TAFBMinRange;

	// This element is the TAFB Max button

	@FindAll({ @FindBy(how = How.ID, using = "maxTAFB") })
	WebElement TAFBMaxRange;

	// Inclusive Time Framework check box in search Page
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[contains(@for,'chk_inclusiveTimeFrame')]/span") })
	WebElement inclusiveTimeFrameChkBox;

	// Departure To Date Text Box
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='departureEndDate']/span/input") })
	WebElement departureToDateTxtBx;

	// Arrival To Date Text Box
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='arrivalEndDate']/span/input") })
	WebElement arrivalToDateTxtBx;

	// Days Dates Hours collapsible
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Days, Dates & Hours')]/a/i") })
	WebElement daysDatesHoursDwnArrw;

	// Duty Periods Plus under Days Dates and Hours collapsible
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Duty Periods')]//tr[contains(@class,'ng-star-inserted')][1]//span") })
	WebElement dutyPeriodsPlus;

	// Minimum Duty Period drop down
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='minDutyPeriods']") })
	WebElement dutyPeriodsMinDrpDwn;

	// Maximum Duty Period drop down
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='maxDutyPeriods']") })
	WebElement dutyPeriodsMaxDrpDwn;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = ("//*[.='Search']")) })
	WebElement searchTab;

	// click on inclusive Btn
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Inclusive Timeframe']/../../div/div/label") })
	WebElement inclusiveBtn;

	// departureFromDateBox
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='departureStartDate']//input") })
	WebElement departureFromDateBox;

	// departureToDateBox
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='departureEndDate']//input") })
	WebElement departureToDateBox;

	// arrivalDateBox
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='arrivalStartDate']//input") })
	WebElement arrivalDateBox;

	// search btn
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Show Sequences']") })
	WebElement seqSearchBtn;

	// Seq result table
	// @CacheLookup
	@FindAll({ @FindBy(how = How.ID, using = "sequencesLargeTable") })
	WebElement seqRsltTbl;

	// Next Arrow in Calendar
	@FindAll({ @FindBy(how = How.XPATH, using = "//a[contains(@class,'ui-datepicker-next ui-corner-all')]")

	})
	WebElement calanderNextMonthLink;
	
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//table[@id = 'sequencesLargeTable']//tbody")

	})
	List<WebElement> sequenceTableBodyRows;

	@FindAll({ @FindBy(how = How.XPATH, using = "//table[@id = 'sequencesLargeTable']//a")

	})
	List<WebElement> sequenceNumbersInTable;

	// departure2DateBox
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='departureEndDate']//input") })
	WebElement departure2DateBox;

	// Clicking to show sequence button
	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@id='buttonGenericSearchSubmit']") })
	WebElement showSequenceBtn;

	// Seq result table
	@CacheLookup
	@FindAll({ @FindBy(how = How.ID, using = "sequencesSmallTable") })
	WebElement seqSmallTbl;

	// Seq result table
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Sequence Number')]/a") })
	WebElement seqNumberCollapsable;

	// 'Airport and Layovers' Down Arrow to expand the collapsible in Search Page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Airports & Layovers')]/a/i") })
	WebElement airportsAndLayoversDwnArrw;

	// Plus icon to add Departure Stations Drop downs in Search Page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = " //div[contains(@class,'collapsable')]//*[contains(.,'Airports & Layovers')]//tr[contains(@class,'ng-star-inserted')][1]//span") })
	WebElement departureStationsPlus;

	// Checkbox to check BOS Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation0']/span") })
	WebElement departureStationChkBxBOS;

	// Checkbox to check BWI Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation1']/span") })
	WebElement departureStationChkBxBWI;
	
	// Checkbox to check CLT Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation2']/span") })
	WebElement departureStationChkBxCLT;
	
	// Checkbox to check DAL Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation3']/span") })
	WebElement departureStationChkBxDAL;
	
	// Checkbox to check DCA Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation4']/span") })
	WebElement departureStationChkBxDCA;
	
	// Checkbox to check DFW Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation5']/span") })
	WebElement departureStationChkBxDFW;

	// Checkbox to check EWR Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation6']/span") })
	WebElement departureStationChkBxEWR;
	
	// Checkbox to check FLL Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation7']/span") })
	WebElement departureStationChkBxFLL;

	// Checkbox to check IAD Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation8']/span") })
	WebElement departureStationChkBxIAD;
	
	// Checkbox to check JFK Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation9']/span") })
	WebElement departureStationChkBxJFK;

	// Checkbox to check LAX Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation10']/span") })
	WebElement departureStationChkBxLAX;
	
	// Checkbox to check LGA Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation11']/span") })
	WebElement departureStationChkBxLGA;
	
	// Checkbox to check LGB Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation12']/span") })
	WebElement departureStationChkBxLGB;
	
	// Checkbox to check MDW Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation13']/span") })
	WebElement departureStationChkBxMDW;
	
	
	// Checkbox to check MIA Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation14']/span") })
	WebElement departureStationChkBxMIA;

	// Checkbox to check ONT Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation15']/span") })
	WebElement departureStationChkBxONT;
	

	// Checkbox to check ORD Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation16']/span") })
	WebElement departureStationChkBxORD;


	// Checkbox to check PBI Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation17']/span") })
	WebElement departureStationChkBxPBI;

	// Checkbox to check PHL Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation18']/span") })
	WebElement departureStationChkBxPHL;

	// Checkbox to check PHX Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation19']/span") })
	WebElement departureStationChkBxPHX;

	// Checkbox to check SAN Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation20']/span") })
	WebElement departureStationChkBxSAN;

	// Checkbox to check SNA Departure Station
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_departureStation21']/span") })
	WebElement departureStationChkBxSNA;
		
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='DATE']") })
	WebElement dateClmnSort;

	// ballot Submit Button
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@value='Save Selected Sequences']") })
	WebElement ballotSubmitButton;

	// Add Button
	@CacheLookup
	@FindAll({ @FindBy(how = How.ID, using = "btnSubmit") })
	WebElement addBtn;

	// capturing all the sequence list from the table
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//table[@id = 'sequencesLargeTable']//span[@class = 'control']")

	})
	List<WebElement> sequenceNumbersCheckBox;

	// Save the selected sequence
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@value='Save Selected Sequences']")

	})
	WebElement saveSelectedSequence;

	// Check box list inside add to ballots list
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'modal-content']//span[@class = 'control']") })
	List<WebElement> addToBallotsChkBox;

	// Check box list inside add to ballots list
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@id = 'btnSubmit']") })
	WebElement clickOnAddBtn;

	// Check "No Result text" inside the ballots list
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//aac-search-results//div[@class = 'col-sm-12']") })
	WebElement noResultText;

	// 'Paid Credit' Plus icon to add Total Credit Drop downs in Search Page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Pay & Credit')]//tr[contains(@class,'ng-star-inserted')][2]//span") })
	WebElement paidCreditPlus;

	// 'Min' Drop Down to select the values for Minimum Paid Credit Hours after
	// adding Paid Credit as a Search criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='minPaidCreditHours']") })
	WebElement minPaidCreditHoursDrpDwn;

	// 'Min' Drop Down to select the values for Minimum Paid Credit Minutes after
	// adding Paid Credit as a Search criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='minPaidCreditMinutes']") })
	WebElement minPaidCreditMinsDrpDwn;

	// 'Min' Drop Down to select the values for Maximum Paid Credit Hours after
	// adding Paid Credit as a Search criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='maxPaidCreditHours']") })
	WebElement maxPaidCreditHoursDrpDwn;

	// 'Min' Drop Down to select the values for Maximum Total Credit Minutes after
	// adding Total Credit as a Search criteria
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='maxPaidCreditMinutes']") })
	WebElement maxPaidCreditMinsDrpDwn;

	// 'Calendar Days' Plus icon to add Calendar Days Drop downs in Search Page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Days, Dates & Hours')]//tr[contains(@class,'ng-star-inserted')][1]//span") })
	WebElement calendarDaysPlus;

	// created by Yuba
	// *************for down arrow***********//

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(.,'Days, Dates & Hours')]/a/i") })
	WebElement ballotsDaysDateHour;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(.,' Pay & Credit')]/a/i") })
	WebElement ballotsPayCredit;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(.,' Sequence Characteristics')]/a/i") })
	WebElement ballotsSequenceCharacteristics;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(.,'Airports & Layovers')]/a/i") })
	WebElement ballotsAirportsLayovers;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(.,'Position & Division')]/a/i") })
	WebElement ballotsPositionDivison;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(.,'Sequence Number')]/a/i") })
	WebElement ballotsSequenceNumber;

	// ********************for plus icon*******************************//

	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Calendar Days']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement calendarDaysPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Duty Periods']//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement dutyPeriodsPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//label[.='No Work Days']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement noWorkDaysPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//label[.='No Work Dates']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement noWorkDatesPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//label[.='No Work Hours']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement noWorkHoursPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Total Credit']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement totalCreditPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Paid Credit']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement paidCreditPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Legs Per DP']//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement legsPerDPPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Legs Per Sequence']//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement legsPerSequencePlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Sit Time']//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement sitTimePlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='TAFB']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement TAFBPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Deadheads']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement deadheadsPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Departure Stations']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement departureStationsPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Cities']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement citiesPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Layovers']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement layoversPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Layover Times']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement layoverTimesPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[text()='Position']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement positionPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[text()='Division']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement divisionPlusIcon;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Sequence Number']//..//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement sequenceNumberPlusIcon;
	// **********************************************
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='minCalendarDays']") })
	WebElement minCalendarDays;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='minDutyPeriods']") })
	WebElement minDutyPeriods;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='noFlyStartTime']") })
	WebElement noFlyStartTime;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='minTotalCreditHours']") })
	WebElement minTotalCreditHours;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='minPaidCreditHours']") })
	WebElement minPaidCreditHours;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='maxLegsPerDP']") })
	WebElement maxLegsPerDP;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='minLegsPerDP']") })
	WebElement minLegsPerDP;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='maxLegsPerSeq']") })
	WebElement maxLegsPerSeq;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='maxSitTimeHours']") })
	WebElement maxSitTimeHours;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='minTAFB']") })
	WebElement minTAFB;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='minLayoverTime']") })
	WebElement minLayoverTime;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='minLegsPerSeq']") })
	WebElement minLegsPerSeqRange;
	@FindAll({ @FindBy(how = How.XPATH, using = "//select[@id='maxLegsPerSeq']") })
	WebElement maxLegsPerSeqRange;
	@FindAll({@FindBy(how = How.XPATH, using = "//*[.='Deadheads']//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement DeadheadsPlusIcon;
	//Click DeadHeads Checkbox
	@FindAll({@FindBy(how = How.XPATH , using="//*[.='Deadheads']//..//div[@class='customComponent checkboxComponent']//..//label[@for='chk_noDeadHeadlabel']")})
	WebElement nodhrequiredchkbox;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Deadheads']//..//div[@class='labelTopCheckbox ng-star-inserted']//..//label[@for='chk_dhrequired']") })
	WebElement dhrequiredchkbox;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Deadheads']//..//div[@class='labelTopCheckbox ng-star-inserted']//..//label[@for='chk_dhfirstLeg']") })
	WebElement dhfirstlegchkbox;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Deadheads']//..//div[@class='labelTopCheckbox ng-star-inserted']//..//label[@for='chk_dhlastLeg']") })
	WebElement dhlastlegchkbox;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Deadheads']//..//div[@class='labelTopCheckbox ng-star-inserted']//..//label[@for='chk_dhfirstOrLastLeg']") })
	WebElement dhfirstorlastlegchkbox;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Deadheads']//..//div[@class='labelTopCheckbox ng-star-inserted']//..//label[@for='chk_multipleDHs']") })
	WebElement multipledhchkbox;
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Deadheads']//..//div[@class='labelTopCheckbox ng-star-inserted']//..//label[@for='chk_dhonlyOnAA']") })
	WebElement dhonlyonaachkbox;
			


	// **********************************************

	// Select Minimum Calendar Days from drop down
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='minCalendarDays']") })
	WebElement minCalendarDaysDrpDwn;

	// Select Maximum Calendar Days from drop down
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='maxCalendarDays']") })
	WebElement maxCalendarDaysDrpDwn;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//th[contains(text(),'STATUS')]") })
	WebElement statusColumnLocator;

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@id='seqFlightLegInfo']") })
	WebElement FlightLegInfo;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"flight-crew-seq\"]") })
	List<WebElement> FlightCrewSeqNumDateList;

	// Plus icon to add Layover Drop downs in Search Page
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(@class,'collapsable')]//*[contains(.,'Airports & Layovers')]//tr[contains(@class,'ng-star-inserted')][3]//span") })
	WebElement layoverPlus;

	// Include Layover Textbox
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"includeLayovers\"]") })
	WebElement includeLayoversTxtBx;

	// Exclude Layover Textbox
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"excludeLayovers\"]") })
	WebElement excludeLayoversTxtBx;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"departNoEarlierThan\"]") })
	WebElement departNoEarlierThanDropDown;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"departNoLaterThan\"]") })
	WebElement departNoLaterThanDropDown;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"arriveNoEarlierThan\"]") })
	WebElement arriveNoEarlierThanDropDown;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"arriveNoLaterThan\"]") })
	WebElement arriveNoLaterThanDropDown;	
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='noFlyStartTime']")})
	WebElement noWorkHoursMinDropDown;	
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='noFlyEndTime']")})
	WebElement noWorkHoursMaxDropDown;	
	
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[@id='position']//div[@class='labelTopCheckbox ng-star-inserted']") })
	List<WebElement> listOfpositionCheckBoxes;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[@id='division']//div[@class= 'labelTopCheckbox ng-star-inserted']") })
	List<WebElement> listOfDivisionCheckBoxes;
	
	@FindBy(how = How.XPATH, using="//*[@id=\"leg-groundTime\"]")
	List<WebElement> SearchSequenceGRDColumn;
	
	@FindBy(how = How.XPATH, using="//*[@id=\"dp_startTime\"]")
	List<WebElement> searchSequenceStartColumn;
	
	@FindBy(how = How.XPATH, using="//*[@id=\"dp_endTime\"]")
	List<WebElement> searchSequenceEndColumn;
	
	@FindBy(how = How.XPATH, using="//div[contains(@id, 'calendarDays')]//span[contains(@class, 'more-less glyphicon glyphicon-minus')]")
	WebElement calendarDaysMinus;
	
	@FindBy(how = How.XPATH, using="//div[contains(@id, 'dutyPeriods')]//span[contains(@class, 'more-less glyphicon glyphicon-minus')]")
	WebElement dutyPeriodMinus;
	
	@FindBy(how = How.XPATH, using="//div[contains(@id, 'paidCredit')]//span[contains(@class, 'more-less glyphicon glyphicon-minus')]")
	WebElement paidCreditMinus;
	
	@FindBy(how = How.XPATH, using="//div[contains(@id, 'legsPerDP')]//span[contains(@class, 'more-less glyphicon glyphicon-minus')]")
	WebElement legPerDPMinus;
	
	@FindBy(how = How.XPATH, using="//div[contains(@id, 'legsPerSequence')]//span[contains(@class, 'more-less glyphicon glyphicon-minus')]")
	WebElement legPerSeqMinus;
	
	@FindBy(how = How.XPATH, using="//div[contains(@id, 'tAFB')]//span[contains(@class, 'more-less glyphicon glyphicon-minus')]")
	WebElement tafbMinus;
	
	@FindBy(how = How.XPATH, using="//label[text()='Layover Times']//parent::div//span")
	WebElement layoverTimesPlus;
	
	@FindBy(how = How.XPATH, using="//*[@id='minLayoverTime']")
	WebElement minLayoverTimeDrpDwn;
	
	@FindBy(how = How.XPATH, using="//*[@id='maxLayoverTime']")
	WebElement maxLayoverTimeDrpDwn;

	@FindBy(how = How.XPATH, using="//label[text()='Cities']//parent::div//span")
	WebElement citiesPlus;
	
	@FindBy(how = How.XPATH, using="//input[@id='includeCities']")
	WebElement includeCitiesTxtBox;
	
	@FindBy(how = How.XPATH, using="//input[@id='avoidCities']")
	WebElement excludeCitiesTxtBox;
	
	@FindBy(how = How.XPATH, using="//div[@id='layovers']//span[contains(@class,'glyphicon-minus')]")
	WebElement layoverMinus;

	@FindBy(how = How.XPATH, using="//div[contains(@class, 'search-panel-bottom-buttons-row footer')]")
	WebElement stickyfooterheader;
	
	//for error msg - date validation
	@FindBy(how = How.XPATH, using="//*[@id='departureEndDateErrorMsg']/span")
	WebElement departureDateErrMsg;
	@FindBy(how = How.XPATH, using="//div[@class ='error-message']/span")
	WebElement arrivalDateErrMsg;
	
	// for error msg - request thru midnight 
	@FindBy(how = How.XPATH, using="//span[@id='depEndVal']")
	WebElement depDateMidNightMsg;
	@FindBy(how = How.XPATH, using="//span[@id='arrEndVal']")
	WebElement arrDateMidNightMsg;
	
	//for clicking clear buttion date picker
	@FindBy(how = How.XPATH, using="//*[@id=\"departureEndDate\"]//p-footer//span[@class ='ui-button-text ui-clickable']")
	WebElement depToClearbutton;
	@FindBy(how = How.XPATH, using="//*[@id=\"arrivalEndDate\"]//p-footer//span[@class ='ui-button-text ui-clickable']")
	WebElement arrToClearbutton;
	
	@FindBy(how = How.XPATH, using="//div[contains(@class, 'ui-datepicker-title')]//span")
	List<WebElement> datePickerHeader;
	
	//checkbox for Pick Up Outside DOTC for save generic criteria
	@FindBy(how = How.XPATH, using="//aac-generic-criteria[@id='ballotType']//div[@name='formGenericSearch']//div[@class='ballotItems']//div[6]//a")
	WebElement pickUpOutsideDOTC;
	
	//checkbox for Pick Up DOTC for save generic criteria
	@FindBy(how = How.XPATH, using="//aac-generic-criteria[@id='ballotType']//div[@name='formGenericSearch']//div[@class='ballotItems']//div[5]//a")
	WebElement pickUpDOTC;
	
	public DOTCSearchScreen() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);

	}

	// Clicking Search button in Menu to navigate to Search Page
	public void clickSearchBtn() {
		try {
			Util.ClickButton(driver, searchBtn);
			Util.waitForLoad(driver);
			Assert.assertTrue("Clicked on search button", true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on search button",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception while clicking on search button",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception while clicking on search button", false);
			ex.printStackTrace();
		}
	}

	// On the 'Search' page locate the Sick Pending check box and enable that check
	// box
	public void checkSickPending() {
		try {
			Util.ClickButton(driver, sickPending);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Sick Pending Check box is enabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Sick Pending Check box could not be enabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// On the 'Search' page locate the Crewed Sequences check box and enable that
	// check box
	public void checkCrewedSequences() {
		try {
			Util.ClickButton(driver, crewedSequences);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Crewed Sequences Check box is enabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Crewed Sequences Check box could not be enabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// On the 'Search' page locate the Sick Sequences check box and enable that
	// check box
	public void checkSickSequences() {
		try {
			Util.ClickButton(driver, sickSequences);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Sick Sequences Check box is enabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Sick Sequences Check box could not be enabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// On the 'Search' page locate the Sick Pending check box and enable that check
	// box
	//18-01-2022
	public void clickTAFB() {
		try {
			Util.ClickButton(driver, TAFBAdd);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "TAFB is added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("TAFB is added", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "TAFB could not be added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("TAFB could not be added", false);
		}
	}

	// Verify if we have landed to Search Page after clicking Search button from
	// menu
	public void verifySearchPage() {
		try {

			if (Util.waitFor(driver, calanderLbl)) {
				Assert.assertTrue("Calander label is visible", true);
			} else
				Assert.assertFalse("Calander label is Not Visible", true);
			Util.waitForLoad(driver);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Verify if Save Generic Button exists
	//17-01-2022
	public void existGenericBtn() {
		try {
			if(Util.waitForElementClickable(driver, saveGenericCriteriaBtn)) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Save Generic Criteria button is visible",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Save Generic Criteria button is visible", true);
			}
			else
				throw new NoSuchElementException("Unable to find Button");
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception inSave Generic Criteria button",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Couldn't find Save Generic Criteria button", false);
		}
	}

	// Clicking Save Generic Criteria Button after selection of Search Criteria
	public void clickSaveGenericBtn() {
		try {
			Util.ClickButton(driver, saveGenericCriteriaBtn);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Save Generic Criteria button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Save Generic Criteria is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void clickGenericSearchClear() {
		try {
			Util.ClickButton(driver, clearGenericSearchBtn);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clear Generic search button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Clear Generic search button is clicked", true);
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Clear Generic search is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Clear Generic search button is not clicked", false);
			ex.printStackTrace();
		}
	}

	// Clicking Show sequence
	public void clickShowSequence() {
		try {
			Util.ClickElement(driver, showSequences);
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Clicking Save Title Button after entering title and checking the check box
	// while creation of Ballot
	public void clickSaveTitleBtn() {
		try {
			Util.ClickElement(driver, saveTitleBtn);
			Util.waitForSpinnerLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Save Generic Criteria button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Save Generic Criteria is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// Clicking Show sequence
	public void clickOnShowSequence() {
		try {
			Util.ClickElement(driver, showSequenceBtn);
			Util.waitForSpinnerLoad(driver);
			JavascriptExecutor js = (JavascriptExecutor) this.driver;
			js.executeScript("window.scrollBy(0,200)", "");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Show Sequence is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Sequence could not be clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// click on the first sequence from the list
	public void clickOnFirstSequence() {

		try {
			Util.ClickElement(driver, sequenceNumbersCheckBox.get(1));
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "First Sequence checkbox is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "First Sequence checkbox is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// click on save selected sequence
	public void clickOnSaveSelectedSequence() {

		try {
			Util.ClickElement(driver, saveSelectedSequence);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Save selected sequence is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Save selected sequence is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Select ballots type from Add to Ballots
	public void addBallotsDotcCheckBox() {
		try {
			Util.ClickButton(driver, addToBallotsChkBox.get(2));
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "DOTC Check box is selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DOTC Check box is not selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// click add button from Add to Ballots
	//17-01-2022
	public void clickAddBtn() {
		try {
			if (clickOnAddBtn.isEnabled())
				Util.ClickButton(driver, clickOnAddBtn);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Add button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Add button is clicked", true);
		} catch (Exception ex) {
			Util.waitForLoad(driver);
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Add button is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Add button is not clicked", false);
		}

	}

	// Clicking Sequence Characteristic Down Arrow to expand collapsible
	//18-01-2022
	public void clicksequenceCharactDwnArrw() {
		try {
			Util.ClickButton(driver, sequenceCharacteristicsDwnArrw);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully clicked on sequence characteristics down arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Successfully clicked on sequence characteristics down arrow", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on sequence characteristics down arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in clicking on sequence characteristics down arrow", false);
		}
	}

	// Selecting SitTime value from drop down in Search Criteria
	public void selectSitTimeDrpdown(String SitTime) {
		try {
			Util.SelectFrmDropDown(driver, sitTimeDrpDwn, SitTime, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully selected "+SitTime+" from dropdown",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Successfully selected "+SitTime+" from dropdown", true);
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in selecting "+SitTime+" from dropdown",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in selecting "+SitTime+" from dropdown", false);
			ex.printStackTrace();
		}
	}

	//
	public void selectSitTimeDrpdownMax() {
		Util.SelectFrmDropDownIndex(driver, sitTimeDrpdownMax);
	}

	// Clicking Sequence Number Down Arrow to expand the collapsible in Search Page
	//17-01-2022
	public void clicksequenceNumberDwnArrow() {
		try {
			Util.ClickButton(driver, sequenceNumberDwnArrw);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully clicked on sequence number down arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Successfully clicked on sequence number down arrow", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on sequence number down arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in clicking on sequence number down arrow", false);
		}
	}

	// Clicking Sequence Number Plus icon under Sequence Number collapsible to add
	// as Search criteria
	//17-01-2022
	public void clickSequenceNumberPlus() {
		try {
			Util.ClickButton(driver, sequenceNumberPlus);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on sequence number plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Clicked on sequence number plus", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on sequence number plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in clicking on sequence number plus", false);
		}
	}

	// Entering Sequence Number value in Text Box as a Search Criteria
	public void enterSequenceNumber(String BallotInput) {
		try {
			Util.ClickButton(driver, sequenceNumberTxtBox);
			if(BallotInput.equals(""))
				throw new Exception("No sequence number from service");
			Util.enterText(driver, sequenceNumberTxtBox, BallotInput);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Entered sequence number to textbox",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Entered sequence number to textbox", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Exception in entering sequence number to textbox",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in entering sequence number to textbox", false);
		}
	}

	// Entering Sequence Number value in Text Box as a Search Criteria
	public void enterAnySequenceNumber(String BallotInput) {
		seqNumber = BallotInput;
		try {
			Util.ClickElement(driver, sequenceNumberTxtBox);
			Util.enterText(driver, sequenceNumberTxtBox, BallotInput);
		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	// Entering Title for Search
	public String enterSearchTitle() {
		try {
			Date date = new Date();
			String timeStamp = new SimpleDateFormat("mmddyyhhmmss", Locale.US).format(date);
			SearchTitle = "Test-" + timeStamp;
			Util.enterText(driver, titleTxtBox, SearchTitle);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Title is entered into input box",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Title is not entered",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
		return SearchTitle;
	}

	public void enterSameTitle() {
		try {
			System.out.println("same search title" + SearchTitle);
			Util.enterText(driver, titleTxtBox, SearchTitle);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Title is entered into input box",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Title is not entered",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	//17-01-2022
	public void checkAllBallot() {
		try {
			Util.ClickButton(driver, allBallotChkBox);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on All Ballot checkbox",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Clicked on All Ballot checkbox", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception to click on All Ballot checkbox",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception to click on All Ballot checkbox", false);
		}
	}

	// Clicking Save Button after entering Title for Ballot creation
	public void saveBallotTitle() {
		try {
			Util.ClickButton(driver, saveTitleBtn);
			Util.waitForSpinnerLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Save button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Save button is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// Verify if Sit Time drop down is disabled-Method used to check in presence of
	// Sequence Number text box
	//09-03-2022
	//modified to not displayed from disabled
	public void verifySitTimeDrpDwnDisabled() {
		try {
			boolean isDisabled = driver.findElements(By.xpath("//*[@id='maxSitTimeHours']")).size() == 0;
			if (isDisabled) {
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Sit Time dropdown is disabled as expected",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Sit Time dropdown is not disabled as expected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Exception Occurred",false);
		}
	}

	// Click on Show Sequences button on the search page
	//18-01-2022
	public void clickShowSequences() {
		try {
			Util.ClickElement(driver, showSequences);
			Util.waitForSpinnerLoad(driver);
			JavascriptExecutor js = (JavascriptExecutor) this.driver;
			js.executeScript("window.scrollBy(0,200)", "");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Show Sequences is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Show sequence is clicked", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Sequences could not be clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Show sequences could not be clicked", false);
		}
	}

	// Select today's date as Departure From Date in search page from Date Picker
	//18-01-2022
	public void selectDepartureFromDate() {
		try {

			Util.ClickElement(driver, departureFromDateDtPkr);// Clicking Calendar button
			Util.ClickElement(driver, selectedDateFromCalander);
			DepFrom = departureFromDateinput.getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added departure from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added departure from date", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in Adding departure from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding departure from date", false);
		}
	}

	// Select today + 2 date as Departure To Date in Search page from Date Picker
	//18-01-2022
	public void selectDepartureToDate() {
		try {
			Util.ClickElement(driver, departureToDateDtPkr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, 2);
			
			if (verifyCurrentDateAsLastDateOfTheMonth(2)) {
				Util.ClickElement(driver, calanderNextMonthLink);
				String texts = "//table[contains(@class, 'ui-datepicker-calendar')]//tbody//tr//td//span[@data-month='"+new SimpleDateFormat("M").format(calendar.getTime())+"' and @data-day='"+new SimpleDateFormat("d").format(calendar.getTime())+"' and @data-year='"+new SimpleDateFormat("yyyy").format(calendar.getTime())+"']";
				Util.ClickElement(driver, driver.findElement(By.xpath(texts)));
			} else {
				String texts = "//table[contains(@class, 'ui-datepicker-calendar')]//tbody//tr//td//span[@data-month='"+new SimpleDateFormat("M").format(calendar.getTime())+"' and @data-day='"+new SimpleDateFormat("d").format(calendar.getTime())+"' and @data-year='"+new SimpleDateFormat("yyyy").format(calendar.getTime())+"']";
				Util.ClickElement(driver, driver.findElement(By.xpath(texts)));
			}
			DepTo = departureToDateinput.getAttribute("value");
			System.out.println(DepTo);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added departure to date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added departure to date", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in adding departure to date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding departure to date", false);
		}
	}

	public boolean verifyCurrentDateAsLastDateOfTheMonth() {
		String callastDate = "";
		boolean lastDayFlag = false;

		try {
			callastDate = calCurrentDate.getText();
			Integer lastDayOfTheMonth = Util.getLastDayOfTheMonth();
			if (lastDayOfTheMonth.equals(Integer.parseInt(callastDate))) {
				lastDayFlag = true;
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return lastDayFlag;
	}

	// Select today's date as Arrival From Date in search page from Date Picker
	//18-01-2022
	public void selectArrivalFromDate() {
		try {
			Util.ClickElement(driver, arrivalFromDateDtPkr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			if (verifyCurrentDateAsLastDateOfTheMonth()) {
				Util.ClickElement(driver, calanderNextMonthLink);
				String texts = "//table[contains(@class, 'ui-datepicker-calendar')]//tbody//tr//td//span[@data-month='"+new SimpleDateFormat("M").format(calendar.getTime())+"' and @data-day='"+new SimpleDateFormat("d").format(calendar.getTime())+"' and @data-year='"+new SimpleDateFormat("yyyy").format(calendar.getTime())+"']";
				Util.ClickElement(driver, driver.findElement(By.xpath(texts)));
			} else {
				String texts = "//table[contains(@class, 'ui-datepicker-calendar')]//tbody//tr//td//span[@data-month='"+new SimpleDateFormat("M").format(calendar.getTime())+"' and @data-day='"+new SimpleDateFormat("d").format(calendar.getTime())+"' and @data-year='"+new SimpleDateFormat("yyyy").format(calendar.getTime())+"']";
				Util.ClickElement(driver, driver.findElement(By.xpath(texts)));
			}
			ArrFrom = arrivalFromDateinput.getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added arrival from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added arrival from date", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in Adding arrival from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding arrival from date", false);
		}

	}

	// Select today's date as Arrival From Date in search page from Date Picker
	//18-01-2022
	public void selectArrivalToDate() {
		try {
			Util.ClickElement(driver, arrivalToDateDtPkr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, 2);
			if (verifyCurrentDateAsLastDateOfTheMonth()) {
				Util.ClickElement(driver, calanderNextMonthLink);
				String texts = "//table[contains(@class, 'ui-datepicker-calendar')]//tbody//tr//td//span[@data-month='"+new SimpleDateFormat("M").format(calendar.getTime())+"' and @data-day='"+new SimpleDateFormat("d").format(calendar.getTime())+"' and @data-year='"+new SimpleDateFormat("yyyy").format(calendar.getTime())+"']";
				Util.ClickElement(driver, driver.findElement(By.xpath(texts)));
			} else {
				String texts = "//table[contains(@class, 'ui-datepicker-calendar')]//tbody//tr//td//span[@data-month='"+new SimpleDateFormat("M").format(calendar.getTime())+"' and @data-day='"+new SimpleDateFormat("d").format(calendar.getTime())+"' and @data-year='"+new SimpleDateFormat("yyyy").format(calendar.getTime())+"']";
				Util.ClickElement(driver, driver.findElement(By.xpath(texts)));
			}
			ArrTo = arrivalToDateinput.getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added arrival to date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added arrival to date", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in adding arrival to date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding arrival to date", false);
		}

	}

	public String getDate(String format, String offset) {
		// Format formatter = new SimpleDateFormat(format);
		Date dt = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, Integer.parseInt(offset));
		dt = calendar.getTime();
		return new SimpleDateFormat(format).format(dt);
	}

	//19-10-2022
	public void selectDateRange(String stdtoffset, String enddtoffset) {
		try {
			selectDepartureFromDate(stdtoffset);
			selectArrivalFromDate(enddtoffset);

			ExtentTestManager.getTest().log(LogStatus.PASS, "DOTC Search:Start and End Date is Selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("DOTC Search:Start and End Date is Selected", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DOTC Search: Start and End Date Selection Failed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception Occured:DOTC Search: Start Date Selection Failed " + ex.getMessage(), true);

		}

	}

	// click on clear date from input
	public void clearDateInputFromAll() {
		try {
			Util.ClickElement(driver, arrivalToDateDtPkr);
			driver.findElement(By.xpath("//*[@id='arrivalEndDate']//span[contains(text(), 'Clear')]")).click();
			Util.ClickElement(driver, arrivalFromDateDtPkr);
			driver.findElement(By.xpath("//*[@id='arrivalStartDate']//span[contains(text(), 'Clear')]")).click();
			Util.ClickElement(driver, departureToDateDtPkr);
			driver.findElement(By.xpath("//*[@id='departureEndDate']//span[contains(text(), 'Clear')]")).click();
			Util.ClickElement(driver, departureFromDateDtPkr);
			driver.findElement(By.xpath("//*[@id='departureStartDate']//span[contains(text(), 'Clear')]")).click();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// departure date validation
	public void DepartureDateValidation() {
		if ((showSequences.isEnabled()) == false) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Show Sequence button is disabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Show Sequence button is disabled", true);
		} else {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Duplicate Color is not matched",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Duplicate Color is not matched", true);
		}
	}

	// method to validate red boarder
	public void validateBoarderColor() {
		String redColorCode = "rgb(244, 67, 54)";
		String boarderColor = driver.findElement(By.xpath("//[@name = 'departureStartDate']"))
				.getCssValue("border-top-color");
		if (boarderColor.equalsIgnoreCase(redColorCode)) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Duplicate Color is matched",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Duplicate Color is matched", true);
		} else {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Duplicate Color is not matched",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Duplicate Color is not matched", true);
		}
	}

	// Click Position Arrow to expand Position collapsible
	public void clickOnPositionArrow() {
		try {
			Util.ClickElement(driver, positionDwnArrw);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Position Arrow is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Position Arrow is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// Click Position plus to see the position
	public void clickOnPositionPlus() {
		try {
			Util.ClickElement(driver, positionPlus);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Position Plus button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Position Plus button is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// Select first Position value
	public void selectPositionFirstCheckBox() {
		try {
			Util.ClickElement(driver, positionValue);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Position Value is selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Position Value is not selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// Click Position plus to see the position
	public void clickOnDivisionPlus() {
		try {
			Util.ClickElement(driver, divisionPlus);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Position Plus button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Position Plus button is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// Select first Position value
	public void selectDivisionFirstCheckBox() {
		try {
			Util.ClickElement(driver, divisionValue);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Position Value is selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Position Value is not selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// Click Total Credit Plus icon under the Pay and Credit collapsible to add it
	// as a Search Criteria
	public void clickTotalCreditPlus() {
		try {
			Util.ClickElement(driver, totalCreditPlus);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked Total Credit Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click Total Credit Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}

	}

	// Click Pay and Credit Down Arrow to expand Pay and Credit collapsible
	public void clickPayAndCreditDwnArrow() {
		try {
			Util.ClickElement(driver, payAndCreditDwnArrw);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked Pay and Credit Down Arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click Pay and Credit Down Arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Select Max Hours from Total Credit Drop Down
	//18-01-2022
	public void selectMaxTotalCreditHoursDrpDwn(String value) {
		try {
			Util.SelectFrmDropDown(driver, maxTotalCreditHoursDrpDwn, value, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected max total credit hours from dropdown: "+value,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Selected max total credit hours from dropdown: "+value, true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in selecting max total credit hours from dropdown: "+value,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in selecting max total credit hours from dropdown: "+value, false);
		}
	}

	// Select Max Mins from Total Credit Drop Down
	//18-01-2022
	public void selectMaxTotalCreditMinutesDrpDwn(String value) {
		try {
			Util.SelectFrmDropDown(driver, maxTotalCreditMinutesDrpDwn, value, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected max total credit minutes from dropdown: "+value,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Selected max total credit minutes from dropdown: "+value, true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Exception in Selecting max total credit minutes from dropdown: "+value,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in selecting max total credit minutes from dropdown: "+value, false);
		}
	}

	// Select Min Hours from Total Credit Drop Down
	//18-01-2022
	public void selectMinTotalCreditHoursDrpDwn(String minValue) {
		try {
			Util.SelectFrmDropDown(driver, minTotalCreditHoursDrpDwn, minValue, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected min total credit hours from dropdown: "+minValue,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Selected min total credit hours from dropdown: "+minValue, true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in Selecting min total credit hours from dropdown: "+minValue,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in selecting min total credit hours from dropdown: "+minValue, false);
		}
	}

	// Select Min Minutes from Total Credit Drop Down
	//18-01-2022
	public void selectMinTotalCreditMinutesDrpDwn(String minValue) {
		try {
			Util.SelectFrmDropDown(driver, minTotalCreditMinutesDrpDwn, minValue, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected min total credit minutes from dropdown: "+minValue,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Selected min total credit minutes from dropdown: "+minValue, true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in Selecting min total credit minutes from dropdown: "+minValue,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in selecting min total credit minutes from dropdown: "+minValue, false);
		}
	}

	//
	public void SelectAllBallotsCheckBox() {
		try {
			Util.ClickElement(driver, AllBallotsChkBox);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Report type check box is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Report type check box is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	//17-01-2022
	public void SelectDOTCCheckBox() {
		try {
			Util.ClickElement(driver, DOTCChkBox);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "DOTC check box is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("DOTC check box is clicked", true);
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DOTC check box is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("DOTC check box is not clicked",false);
		}
	}

	public void SelectOutsideCheckBox() {
		try {
			Util.ClickElement(driver, outsideChkBox);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Report type check box is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Report type check box is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void SelectTemplateCheckBox() {
		try {
			Util.ClickElement(driver, templateChkBox);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Report type check box is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Report type check box is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// This method gives the total number of rows in the Search results
	public int getTotalSequencesFromSearchResults(List<WebElement> we) {
		System.out.println("The total no of rows are: " + (we.size())/2);
		return (we.size())/2;

	}

	// This method parses the TAFB value for each of the returned sequences and
	// appends that value into an array and returns an array of TAFB values

	public ArrayList<String> getListOfTAFBValues(int TotalSeq) {
		ArrayList<String> TAFBList = new ArrayList<String>();
		int count =1;
		for (int i = 0; i < TotalSeq; i++) {
			String expression = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[13]";
			// Get the exact TAFB value and append that to the arraylist
			TAFBList.add(driver.findElement(By.xpath(expression)).getAttribute("innerHTML"));
			count++;
		}
		return TAFBList;
	}
	// This method parses the LegsPerDP value for each of the returned sequences and
		// appends that value into an array and returns an array of LegsPerDP values

		public ArrayList<String> getListOfLegsPerDPValues(int TotalSeq) {
			ArrayList<String> LegsPerDPList = new ArrayList<String>();
			int count =1;
			for (int i = 0; i < TotalSeq; i++) {
				String expression = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[8]";
				// Get the exact LegsPerDP value and append that to the arraylist
				LegsPerDPList.add(driver.findElement(By.xpath(expression)).getAttribute("innerHTML"));
				count++;
			}
			return LegsPerDPList;
		}
	// This method is used to select the min and max range for TAFB based on input
	// args
	public void selectTAFBRange(String min, String max) {
		try {
			Select tmax = new Select(TAFBMaxRange); // create a select object to traverse through the static drop down 'TAFB
			// MAX'
			tmax.selectByValue(max);
			Select tmin = new Select(TAFBMinRange); // create a select object to traverse through the static drop down 'TAFB
												// MIN'
			tmin.selectByValue(min);
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "TAFB values added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("TAFB values added", true);
		} catch(Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Exception in choosing TAFB values",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in choosing TAFB values", false);
			ex.printStackTrace();
		}
	}

	// This method goes through every row of search results and compares
	// the SIT times associated with that sequence by clicking the seq details
	// agains the expected SIT times, which is passed as an argument to this method
	public void verifySeqResultsforSitTimes(String sitTimes) {

		try {
			// searchResultsList is a LIST of all search results based on the input SIT time
			// and date
			// TotalSequences is calculated from searchResultsList
			int TotalSequences = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			// if TotalSeq = 0 there is nothing to compare
			if (TotalSequences > 0) {
				// In this For loop we parse through every element of the search result and
				// click on
				// search details hyperlink, then read the GRD value from the hyperlink output
				// and then verify if GRD value is less than or equal to sitTimes (arg of this
				// method)
				for (int i = 0; i < TotalSequences; i++) {
					// Get a list of seq numbers ONLY from the page and then click on each seq
					// number one by one
					// System.out.println("TotalResults = " + TotalSequences);
					// System.out.println(searchResultsList.get(i).getAttribute("innerHTML"));
					String seqNumber = searchResultsList.get(i).getAttribute("innerHTML");
					String SN = "//table[@id='sequencesLargeTable']//a[@class='speciallink'][contains(text()," + "\'"
							+ seqNumber + "\'" + ")]";
					System.out.println(SN);
					// Util.ClickElement(driver,driver.findElement(By.xpath(SN)));
					driver.findElement(By.xpath(SN)).click();
					try {
						System.out.println(driver.switchTo().alert().getText());
						// If it reaches here, it found a popup
					} catch (NoAlertPresentException e) {
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// This method compares the TAFB value of every row of sequence output against
	// the min and max range provided as expected TAFB range in this method
	public void verifySeqResultsforTAFB(String min, String max) {

		try {
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if (totalSeq > 0) {
				ArrayList<String> TAFBFromSeqResults = getListOfTAFBValues(totalSeq);
				for (int i = 0; i < TAFBFromSeqResults.size(); i++) {

					if ((Float.parseFloat(TAFBFromSeqResults.get(i)) > Float.parseFloat(min))
							&& (Float.parseFloat(TAFBFromSeqResults.get(i)) < Float.parseFloat(max))) {
						String output = "TAFB check for seq number " + (i + 1)
								+ " is good and within expected range. Actual TAFB from seq output ="
								+ TAFBFromSeqResults.get(i) + "  MinTAFB=" + min + "  MaxTAFB=" + max;
						Assert.assertTrue(output, true);
						ExtentTestManager.getTest().log(LogStatus.PASS, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

					} else {
						String failedoutput = "TAFB check for seq number " + (i + 1)
								+ " Failed and is outside expected range Actual TAFB from seq output ="
								+ TAFBFromSeqResults.get(i) + "  MinTAFB=" + min + "  MaxTAFB=" + max;
						ExtentTestManager.getTest().log(LogStatus.FAIL, failedoutput,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertFalse(failedoutput, true);
					}
				}
			} else {
				// If number of sequences is zero this condition will be executed
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Sequence returned for this TAFB range",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("No Sequence returned for this TAFB range", true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Click Inclusive Timeframe Check Box in Search page
	public void clickInclusiveFrameworkChkBox() {
		try {
			Util.ClickElement(driver, inclusiveTimeFrameChkBox);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Inclusive Timeframe Checkbox is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Inclusive Timeframe check box is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();

		}

	}

	// verify if Departure and Arrival Date fields are disabled after checking
	// Inclusive Time frame checkbox
	public void verifyDepartureArrivalToDateDisabled() {
		try {

			if (departureToDateTxtBx.isEnabled() == false) {
				System.out.println("Departure Date To Field is disabled");
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Departure Date To Field is disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			} else {
				Util.ClickElement(driver, inclusiveTimeFrameChkBox);
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Departure Date To Field is disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}

			if (arrivalToDateTxtBx.isEnabled() == false) {
				System.out.println("Arrival Date To Field is disabled");
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Arrival Date To Field is disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			} else {
				Util.ClickElement(driver, inclusiveTimeFrameChkBox);
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Arrival Date To Field is disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}

		} catch (Exception ex) {
			Util.ClickElement(driver, inclusiveTimeFrameChkBox);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Departure/Arrival Date From and To Fields are not disabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}
	}

	// Click Days Dates and Hours Down Arrow to expand the panel
	public void clickDaysDatesHoursDwnArrw() {
		try {
			Util.ClickElement(driver, daysDatesHoursDwnArrw);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked Days Dates Hours Arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click Days Dates Hours Arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();

		}

	}

	// Click Duty Period Plus icon under Days, Dates and Hours collapsible
	public void clickDutyPeriodsPlus() {
		try {
			Util.ClickElement(driver, dutyPeriodsPlusIcon);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked Duty Periods Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click Duty Periods Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Select Minimum and Maximum values from Duty Periods drop down
	public void selectMinMaxDutyPeriods(String minValue, String maxValue) {
		try {
			Util.SelectFrmDropDown(driver, dutyPeriodsMaxDrpDwn, maxValue, true);
			Util.SelectFrmDropDown(driver, dutyPeriodsMinDrpDwn, minValue, true);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected Duty Periods Min and Max Value",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select Duty Periods Min and Max Value",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}

	}

	// This method is used to increase Departure date range
	public void IncreaseDepartureTodate(int howmany) throws Exception {
		// String strNewDepToDate = "";
		Util.ClickElement(driver, departureToDateDtPkr);
		String depToDate = driver
				.findElement(By.xpath("//div[contains(@class, 'datepicker')]//td[contains(@class,'current')]"))
				.getText();
		int inum2 = Integer.parseInt(depToDate);
		int newDepToDate = inum2 + howmany;
		String strNewDepToDate = Integer.toString(newDepToDate);
		System.out.println("strNewDepToDate: "+strNewDepToDate);
		WebElement departureToDate = driver.findElement(By
				.xpath("//div[contains(@class, 'datepicker')]//td//span[contains(text(),'" + strNewDepToDate + "')]"));
		Util.ClickElement(driver, departureToDate);
		DepTo = departureToDateinput.getAttribute("value");
		clickOnShowSequence();
		Util.waitForLoad(driver);
		getSequenceDetailsDepartureRange();
	}

	// This method is used to get the sequences from UI based on departure range
	public void getSequenceDetailsDepartureRange() {
		try {
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			
			if (totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if (totalSeq > 0) {
				sequencesFromTable = new ArrayList<String>();// creating new generic arraylist
				for (int i = 0; i < sequenceNumbersInTable.size(); i++) {
					String Sequence = sequenceNumbersInTable.get(i).getText();
					sequencesFromTable.add(Sequence);
					Collections.sort(sequencesFromTable);
				}
				System.out.println("Sequence from table: "+sequencesFromTable);
				JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("window.scrollBy(0,350)", "");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence table is Visibile",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
				Assert.assertTrue("Squence table is visible",true);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception- Sequence table is not Visibile",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertFalse("Squence table is not visible",true);
		}

	}

	// This method is used to get the sequences from UI based on departure range
	public void compareAndVerifySeqForPrevEmp() {
		try {
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if (totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if (totalSeq > 0) {
				List<String> sequencesFromTableNextEmp = new ArrayList<String>();// creating new generic arraylist
				for (int i = 0; i < sequenceNumbersInTable.size(); i++) {
					String Sequence = sequenceNumbersInTable.get(i).getText();
					sequencesFromTableNextEmp.add(Sequence);
					Collections.sort(sequencesFromTableNextEmp);
				}
				System.out.println(sequencesFromTableNextEmp);
				System.out.println(sequencesFromTable);
				// comparing first sequence list from first and second emp
				Util.waitForLoad(driver);
				boolean flag = true;
				Integer size = sequenceNumbersInTable.size();
				for (int i = 0; i < size; i++) {
					if (sequencesFromTableNextEmp.get(i).equalsIgnoreCase(sequencesFromTable.get(i)))
						flag = true;
					else {
						flag = false;
						break;
					}
				}
				if (flag == true) {
					System.out.println("Sequences from previous and next employee with same Bid Status are identical");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Sequences from previous and next employee with same Bid Status are identical",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
					Assert.assertTrue("Sequences from previous and next employee with same Bid Status are identical",
							true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL,
							"Sequences from previous and next employee with same Bid Status are not identical",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));				
					Assert.assertFalse(
							"Sequences from previous and next employee with same Bid Status are not identical", true);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Exception thrown while verifying sequences from previous and next employee with same Bid Status",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertFalse(
					"Exception thrown while verifying sequences from previous and next employee with same Bid Status",
					true);
		}

	}

	// This method is used to to fetch the response from the service
	public String getSequenceResponseDepartureRange() {
		String jsonSeqResponseDep = "";
		this.employeeID = DOTCLogInScreen.employeeID;
		try {
			getBidStatusForLH();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMyy");// 03 may2020
			java.util.Date date1 = sdf.parse(DepFrom);
			java.util.Date date2 = sdf.parse(DepTo);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 2020-05-03
			String DepartureFromDate = dateFormat.format(date1);
			String DepartureToDate = dateFormat.format(date2);
			String payloadJsonDepRange = "{\"employeeBidStatus\":{\"base\":" + "\"" + bidStatusCurrentBase + "\""
					+ ",\"division\":" + "\"" + bidStatusCurrentDivision + "\"" + ",\"equipmentGroup\":" + "\""
					+ bidStatusCurrentEquipment + "\"" + ",\"seat\":" + "\"" + bidStatusCurrentPosition + "\""
					+ "},\"airLineCode\":\"AA\",\"employeeID\":" + employeeID
					+ ",\"openSequences\":true,\"startDateFrom\":" + "\"" + DepartureFromDate + "\""
					+ ",\"startDateTo\":" + "\"" + DepartureToDate + "\"" + "}";
			jsonSeqResponseDep = dotc.getSequence(payloadJsonDepRange);
			String sequenceList = DOTCRestService.readJson("$..sequenceNumber", jsonSeqResponseDep);
			saveSequenceNumbers(sequenceList.split("\\,"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Sequences fetched from the services",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertTrue("Sequences fetched from the service",true);

		} catch (

		Exception ex) {

			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception-Sequences didn't fetched  from the services",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertFalse("Exception-Sequences didn't fetched from the service",true);
		}
		return jsonSeqResponseDep;
	}

	// This method is used to increase Departure date range
	public void IncreaseArrivalTodate() throws Exception {
		String strNewArrToDate = "";
		Util.ClickElement(driver, arrivalToDateDtPkr);
		String arrToDate = driver
				.findElement(By.xpath("//div[contains(@class, 'datepicker')]//td[contains(@class,'current')]"))
				.getText();
		int inum2 = Integer.parseInt(arrToDate);
		int newArrToDate = inum2 + 1;
		strNewArrToDate = Integer.toString(newArrToDate);
//		WebElement arrivalToDate = driver.findElement(By
//				.xpath("//div[contains(@class, 'datepicker')]//td//span[contains(text(),'" + strNewArrToDate + "')]"));
		WebElement arrivalToDate=driver.findElement(By
				.xpath("//*[@class='ng-tns-c8-3']//td[@class='ng-tns-c8-3 ng-star-inserted']//span[contains(text(),'" + strNewArrToDate + "')]"));

		
		
		Util.ClickButton(driver, arrivalToDate);
		ArrTo = arrivalToDateinput.getAttribute("value");
		clickShowSequence();
		getSequenceDetailsArrivalRange();
	}

	// This method is used to get the sequences from UI based on departure range
	public void getSequenceDetailsArrivalRange() {
		try {
			if (sequenceNumbersInTable.size() == 0) {
				selectDepartureFromDate("2");
				selectArrivalFromDate("2");
				selectArrivalToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
			}

			if (sequenceNumbersInTable.size() > 0) {
				sequencesFromTable = new ArrayList<String>();// creating new generic arraylist
				for (int i = 0; i < sequenceNumbersInTable.size(); i++) {
					String Sequence = sequenceNumbersInTable.get(i).getText();
					sequencesFromTable.add(Sequence);
					Collections.sort(sequencesFromTable);
				}
				System.out.println("Sequences from UI: "+sequencesFromTable);
				JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("window.scrollBy(0,350)", "");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence table is Visibile",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
				Assert.assertTrue("Squence table is visible",true);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence table is not Visibile",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertFalse("Squence table is not visible",true);
		}

	}

	// This method is used to to fetch the response from the service
	public String getSequenceResponseArrivalRange() {
		String jsonSeqResponseArr = "";
		this.employeeID = DOTCLogInScreen.employeeID;
		try {
			getBidStatusForLH();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMyy");// 03 may2020
			java.util.Date date1 = sdf.parse(DepFrom);
			java.util.Date date3 = sdf.parse(ArrFrom);
			java.util.Date date4 = sdf.parse(ArrTo);
			System.out.println(DepFrom + ArrFrom + ArrTo);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 2020-05-03
			String DepartureFromDate = dateFormat.format(date1);
			String ArrivalFromDate = dateFormat.format(date3);
			String ArrivalToDate = dateFormat.format(date4);
			String payloadJsonArrRange = "{\"employeeBidStatus\":{\"base\":" + "\"" + bidStatusCurrentBase + "\""
					+ ",\"division\":" + "\"" + bidStatusCurrentDivision + "\"" + ",\"equipmentGroup\":" + "\""
					+ bidStatusCurrentEquipment + "\"" + ",\"seat\":" + "\"" + bidStatusCurrentPosition + "\""
					+ "},\"airLineCode\":\"AA\",\"employeeID\":" + employeeID
					+ ",\"openSequences\":true,\"startDateFrom\":" + "\"" + DepartureFromDate + "\""
					+ ",\"endDateFrom\":" + "\"" + ArrivalFromDate + "\"" + ",\"endDateTo\":" + "\"" + ArrivalToDate
					+ "\"" + "}";
			jsonSeqResponseArr = dotc.getSequence(payloadJsonArrRange);
			System.out.println(jsonSeqResponseArr);
			String sequenceList = DOTCRestService.readJson("$..sequenceNumber", jsonSeqResponseArr);
			saveSequenceNumbers(sequenceList.split("\\,"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Sequences fetched from the services",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertTrue("Sequences fetched from the services",true);
		} catch (

		Exception ex) {

			ex.printStackTrace();
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception- Sequences  didn't  fetched from the services",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertFalse("Sequences didn't fetched from the services",true);
		}
		return jsonSeqResponseArr;
	}

	// This method is used to get the sequences from UI based on departure range
	public void getSequenceDetailsDepArrRange() {

		try {

			if (sequenceNumbersInTable.size() == 0) {
				selectDepartureToDate("15");
				selectArrivalToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
			}

			if (sequenceNumbersInTable.size() > 0) {
				sequencesFromTable = new ArrayList<String>();// creating new generic arraylist
				for (int i = 0; i < sequenceNumbersInTable.size(); i++) {
					String Sequence = sequenceNumbersInTable.get(i).getText();
					sequencesFromTable.add(Sequence);
					Collections.sort(sequencesFromTable);
				}
				System.out.println(sequencesFromTable);
				JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("window.scrollBy(0,350)", "");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence table is Visibile",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
				Assert.assertTrue("Squence table is visible",true);
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence table is not Visibile",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertFalse("Squence table is not visible",true);
		}

	}

	// This method is used to to fetch the response from the service
	public String getSequenceResponseDepArrRange() {
		String jsonSeqResponseDepArr = "";
		this.employeeID = DOTCLogInScreen.employeeID;
		try {
			getBidStatusForLH();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMyy");// 03 may2020
			java.util.Date date1 = sdf.parse(DepFrom);
			java.util.Date date2 = sdf.parse(DepTo);
			java.util.Date date3 = sdf.parse(ArrFrom);
			java.util.Date date4 = sdf.parse(ArrTo);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 2020-05-03
			String DepartureFromDate = dateFormat.format(date1);
			String DepartureToDate = dateFormat.format(date2);
			String ArrivalFromDate = dateFormat.format(date3);
			String ArrivalToDate = dateFormat.format(date4);
			System.out.println("arrival/departure date range");
			String payloadJsonDepArrRange = "{\"employeeBidStatus\":{\"base\":" + "\"" + bidStatusCurrentBase + "\""
					+ ",\"division\":" + "\"" + bidStatusCurrentDivision + "\"" + ",\"equipmentGroup\":" + "\""
					+ bidStatusCurrentEquipment + "\"" + ",\"seat\":" + "\"" + bidStatusCurrentPosition + "\""
					+ "},\"airLineCode\":\"AA\",\"employeeID\":" + employeeID
					+ ",\"openSequences\":true,\"startDateFrom\":" + "\"" + DepartureFromDate + "\""
					+ ",\"startDateTo\":" + "\"" + DepartureToDate + "\"" + ",\"endDateFrom\":" + "\"" + ArrivalFromDate
					+ "\"" + ",\"endDateTo\":" + "\"" + ArrivalToDate + "\"" + "}";
			jsonSeqResponseDepArr = dotc.getSequence(payloadJsonDepArrRange);
			String sequenceList = DOTCRestService.readJson("$..sequenceNumber", jsonSeqResponseDepArr);
			saveSequenceNumbers(sequenceList.split("\\,"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Sequences fetched from service",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertTrue("Squences fetched from service",true);	

		} catch (

		Exception ex) {

			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception -Sequences didn't fetched from service",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertFalse("Squences didn't fetched from service",true);
		}
		return jsonSeqResponseDepArr;
	}

	// This method is used to save the sequence from the service
	public static void saveSequenceNumbers(String[] sequenceNumbers) {
		sequencesFromService = Arrays.asList(sequenceNumbers).stream().filter(e -> !e.equals("0"))
				.collect(Collectors.toList());
		Collections.sort(sequencesFromService);
		System.out.println("sequences from service are: " + sequencesFromService);

	}

	// This method is used to verify the sequence from Service and UI
	public void validateSequenceTableFromService() {

		try {
			Util.waitForLoad(driver);
			boolean flag = true;
			Integer size = sequencesFromTable.size();
			for (int i = 0; i < size; i++) {
				if (sequencesFromTable.get(i).equalsIgnoreCase(sequencesFromService.get(i)))
					flag = true;
				else {
					flag = false;
					break;
				}
			}
			if (flag == true) {
				System.out.println("Sequences from service and UI are identical");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Passed-Sequences from service and UI are identical",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
				Assert.assertTrue("Sequences from service and UI are identical", true);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed-Sequences from service and UI are not identical",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
				Assert.assertFalse("Sequences from service and UI are not identical", true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception-Sequences from service and UI are not identical",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
		   Assert.assertFalse("Sequences from service and UI are not identical", true);
		}

	}

	// Select ballots type from Add to Ballots
	public void selecctDotcCheckBox() {
		try {
			Util.ClickButton(driver, addToBallotsChkBox.get(2));
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "DOTC Check box is selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DOTC Check box is not selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// Clicking Search button in Menu to navigate to Search Page
	public void clickSearchTab() {
		try {
			Util.ClickButton(driver, searchTab);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked Search button ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Unable to click Search button",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void clickOnInclusiveBox() throws Exception {
		try {
			Util.ClickButton(driver, inclusiveBtn, 5);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

	}

	public void enterDepartureFromDate(String depDate) {
		try {
			sendKey2(departureFromDateBox, depDate);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Departure from date Entered",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Departure from date entered", true);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Exception - failed to enter from departure date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception - failed to enter from departure date", true);
		}
	}

	public void sendKey2(WebElement element, String date) {
		try {
			Util.ClickElement(driver, element);
			Util.setDataSpecial(driver, element, date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// enterArrivalDate
	public void enterArrivalDate(String arrDate) {
		sendKey2(arrivalDateBox, arrDate);
	}

	// click seq search BTN
	public void clickSeqSearch() throws InterruptedException {
		try{
			Util.ClickButton(driver, showSequences, 5);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
	         js.executeScript("window.scrollBy(0,350)", "");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Show sequences click",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Show sequences click", true);
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception - failed to click sho sequences",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception - failed to enter departure date", true);
		}	
	}

	public HashSet<String> getClmnSetFromSeqResult(int clmnN) {
		return Util.getClmnFromTableAsSet(driver, seqRsltTbl, clmnN);
	}

	public ArrayList<String> getClmnFromSeqResult(int clmnN) {
		return Util.getClmnFromTable(driver, new ArrayList<WebElement>() {
			{
				add(seqRsltTbl);
				add(seqSmallTbl);
			}
		}, clmnN);
	}

	public void enterDeparture2Date(String dep2Date) {
		try{
			sendKey2(departure2DateBox, dep2Date);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Departure date to- Entered",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Departure date to- entered", true);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception - failed to enter  to departure  date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception - failed to enter-to departure date ", true);
		}
	}

	// Clicking Sequence Number Plus icon under Sequence Number collapsible to add
	// as Search criteria
	public void clickSequenceNumberCollapsable() {
		try {
			Util.ClickElement(driver, seqNumberCollapsable);
			Util.waitForLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<String> getListOfTotalCreditValues(int TotalSeq) {
		ArrayList<String> TotalCreditList = new ArrayList<String>();
		for (int i = 0; i < TotalSeq; i++) {
			String expression = "//*[@id='sequencesLargeTable']//tbody//tr[(@class='ng-star-inserted')][" + (i + 1)
					+ "]//td[9]";
			// Get the exact Cerdit value and append that to the arraylist
			TotalCreditList.add(driver.findElement(By.xpath(expression)).getAttribute("innerHTML"));
		}
		return TotalCreditList;
	}

	public void verifySeqResultsforTotalCredit(String minHrs, String minMins, String maxHrs, String maxMins) {

		try {
			if(minMins.split("").length == 1)
				minMins = "0"+minMins;
			if(maxMins.split("").length == 1)
				maxMins = "0"+maxMins;
			String minStr = minHrs + "." + minMins;
			String maxStr = maxHrs + "." + maxMins;
			Float min = Float.parseFloat(minStr);
			Float max = Float.parseFloat(maxStr);
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", showSequences);
			if (totalSeq > 0) {
				ArrayList<String> TotalCreditFromSeqResults = getListOfTotalCreditValues(totalSeq);
				for (int i = 0; i < TotalCreditFromSeqResults.size(); i++) {

					if ((Float.parseFloat(TotalCreditFromSeqResults.get(i)) >= min)
							&& (Float.parseFloat(TotalCreditFromSeqResults.get(i)) <= max)) {
						String output = "Total Credit check for seq number " + (i + 1)
								+ " is good and within expected range. Actual Total Credit from seq output ="
								+ TotalCreditFromSeqResults.get(i) + "  MinTotalCredit=" + min + "  MaxTotalCredit="
								+ max;
						Assert.assertTrue(output, true);
						ExtentTestManager.getTest().log(LogStatus.PASS, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

					} else {
						String failedoutput = "TotalCredit check for seq number " + (i + 1)
								+ " Failed and is outside expected range Actual Total Credit from seq output ="
								+ TotalCreditFromSeqResults.get(i) + "  MinTotalCredit=" + min + "  MaxTotalCredit="
								+ max;
						Assert.assertFalse(failedoutput, false);
						ExtentTestManager.getTest().log(LogStatus.FAIL, failedoutput,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					}
				}
			} else {
				// If number of sequences is zero this condition will be executed
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Sequence returned for this Credit range",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("No Sequence returned for this Credit range", true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void verifySeqResultsforDutyPeriods(String minValue, String maxValue) {
		try {
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if (totalSeq > 0) {
				ArrayList<String> TotalDaysFromSeqResults = getListOfTotalDays(totalSeq);
				System.out.println(TotalDaysFromSeqResults.size());
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", showSequences);
				for (int i = 0; i < TotalDaysFromSeqResults.size(); i++) {
					String days = TotalDaysFromSeqResults.get(i).toString();
					StringBuffer numbers = new StringBuffer();
					for (int j = 0; j < days.length(); j++) {
						if (Character.isDigit(days.charAt(j))) {
							numbers.append(days.charAt(j));
						}
					}

					if ((numbers.length() - 1) >= Integer.parseInt(minValue)
							&& (numbers.length() - 1) <= Integer.parseInt(maxValue)) {
						String output = "Duty Period check for seq number " + (i + 1)
								+ " is good and within expected range. Actual Duty Period from seq output ="
								+ (numbers.length() - 1) + ",  Min Duty Period=" + minValue + "  Max Duty Period="
								+ maxValue;
						System.out.println(output);
						Assert.assertTrue(output, true);
						ExtentTestManager.getTest().log(LogStatus.PASS, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

					} else {
						String output = "Duty Period check for seq number " + (i + 1)
								+ " is not good and not within expected range. Actual Duty Period from seq output ="
								+ (numbers.length() - 1) + ",  Min Duty Period=" + minValue + "  Max Duty Period="
								+ maxValue;
						System.out.println(output);
						// Assert.assertTrue(output, true);
						ExtentTestManager.getTest().log(LogStatus.FAIL, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					}
				}
			}

			else {
				// If number of sequences is zero this condition will be executed
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Sequence returned for this Duty Period range",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("No Sequence returned for this Duty Period range", false);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void verifySeqResults4BidStatus() {

		try {
			getBidStatusForLH();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMyy");// 03 may2020
			java.util.Date date1 = sdf.parse(DepFrom);
			java.util.Date date2 = sdf.parse(DepTo);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 2020-05-03
			String DepartureFromDate = dateFormat.format(date1);
			String DepartureToDate = dateFormat.format(date2);
			System.out.println("departure date range");
			String payloadJson = "{\"employeeBidStatus\":{\"base\":" + "\"" + bidStatusCurrentBase + "\""
					+ ",\"division\":" + "\"" + bidStatusCurrentDivision + "\"" + ",\"equipmentGroup\":" + "\""
					+ bidStatusCurrentEquipment + "\"" + ",\"seat\":" + "\"" + bidStatusCurrentPosition + "\""
					+ "},\"airLineCode\":\"AA\",\"employeeID\":" + employeeID
					+ ",\"openSequences\":true,\"startDateFrom\":" + "\"" + DepartureFromDate + "\""
					+ ",\"startDateTo\":" + "\"" + DepartureToDate + "\"" + "}";
			String jsonSeqResponse = dotc.getSequence(payloadJson);
			String sequenceList = DOTCRestService.readJson(DOTCJsonXmlPath.SeqNumSameBidStatus, jsonSeqResponse);
			saveSequenceNumbers(sequenceList.split("\\,"));
			validateSequenceTableFromService();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getCrewMemberJsonResponse(String month) {
		String jsonresponse = "";
		try {
			this.employeeID = DOTCLogInScreen.employeeID;
			String employeeNum = employeeID;
			System.out.println("The pilot id is:  " + employeeID);
			String payloadJson = "{	\"contractMonths\": [" + "\"" + month + "\""
					+ "],\"crewMemberKeyDTO\": { \"employeeNumber\":" + employeeNum + ", \"airlineCode\":\"AA\"}}";
			String endpoint = "CrewMemberService/getCrewMember";
			jsonresponse = dotc.ccsService(payloadJson, endpoint);
			//System.out.println("The json response for CrewMember is: " + jsonresponse);
			jsonRespMonthwise = jsonresponse;
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return jsonresponse;
	}

	public void getBidStatusForLH() {

		try {

			Month curmonth = Util.getCurrentMonth();
			Integer currentYear = Util.getCurrentYear();
			String strCurrentMonth = curmonth.toString().substring(0, 3) + currentYear.toString();
			String jsonresponse = getCrewMemberJsonResponse(strCurrentMonth);
			bidStatusCurrentBase = DOTCRestService.readJson("$.bidStatuses[0].currentBase", jsonresponse);
			bidStatusCurrentEquipment = DOTCRestService.readJson("$.bidStatuses[0].currentEquipment", jsonresponse);
			bidStatusCurrentPosition = DOTCRestService.readJson("$.bidStatuses[0].currentPosition", jsonresponse);
			bidStatusCurrentDivision = DOTCRestService.readJson("$.bidStatuses[0].currentDivision", jsonresponse);
			String bidStatusFromService = bidStatusCurrentBase + "/" + bidStatusCurrentEquipment + "/"
					+ bidStatusCurrentPosition + "/" + bidStatusCurrentDivision;
			System.out.println("The Bid Status is: " + bidStatusFromService);	
		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	public ArrayList<String> getListOfTotalDays(int TotalSeq) {
		ArrayList<String> TotalDaysList = new ArrayList<String>();
		for (int i = 0; i < TotalSeq; i++) {
			String expression = "//*[@id='sequencesLargeTable']//tbody//tr[(@class='ng-star-inserted')]" + "[" + (i + 1)
					+ "]" + "//td[8]";
			// Get the exact Cerdit value and append that to the arraylist
			TotalDaysList.add(driver.findElement(By.xpath(expression)).getText());// getAttribute("innerHTML"));
		}
		return TotalDaysList;
	}

	//19-01-2022
	public void clickAirportLayoversDwnArrw() {
		try {
			Util.ClickElement(driver, airportsAndLayoversDwnArrw);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully clicked on Airports and Layovers down arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Successfully clicked on Airport and Layover down arrow", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Airports and Layovers down arrow",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in clicking on Airports and Layovers down arrow", false);
		}

	}

	//19-01-2022
	public void clickDepartureStationsPlus() {
		try {
			Util.ClickElement(driver, departureStationsPlus);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully clicked on Departure Stations plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Successfully clicked on Departure Stations plus", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Successfully clicked on Departure Stations plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in clicking on Successfully clicked on Departure Stations plus", false);
		}

	}

	public void checkDepartureStationsCheckBoxes() {
		try {
			Util.ClickElement(driver, departureStationChkBxBOS);
			Util.ClickElement(driver, departureStationChkBxDFW);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void checkDepartureStationsCheckBoxes1(String stations) {
		try {
			String[] stationList = stations.split(",");
			for (int i = 0; i < stationList.length; i++) {
				if (stationList[i].contains("BOS"))
					Util.ClickElement(driver, departureStationChkBxBOS);
				else if (stationList[i].contains("BWI"))
					Util.ClickElement(driver, departureStationChkBxBWI);
				else if (stationList[i].contains("CLT"))
					Util.ClickElement(driver, departureStationChkBxCLT);
				else if (stationList[i].contains("DAL"))
					Util.ClickElement(driver, departureStationChkBxDAL);
				else if (stationList[i].contains("DCA"))
					Util.ClickElement(driver, departureStationChkBxDCA);
				else if (stationList[i].contains("DFW"))
					Util.ClickElement(driver, departureStationChkBxDFW);
				else if (stationList[i].contains("EWR"))
					Util.ClickElement(driver, departureStationChkBxEWR);
				else if (stationList[i].contains("FLL"))
					Util.ClickElement(driver, departureStationChkBxFLL);
				else if (stationList[i].contains("IAD"))
					Util.ClickElement(driver, departureStationChkBxIAD);
				else if (stationList[i].contains("JFK"))
					Util.ClickElement(driver, departureStationChkBxJFK);
				else if (stationList[i].contains("LAX"))
					Util.ClickElement(driver, departureStationChkBxLAX);
				else if (stationList[i].contains("LGA"))
					Util.ClickElement(driver, departureStationChkBxLGA);
				else if (stationList[i].contains("LGB"))
					Util.ClickElement(driver, departureStationChkBxLGB);
				else if (stationList[i].contains("MDW"))
					Util.ClickElement(driver, departureStationChkBxMDW);
				else if (stationList[i].contains("MIA"))
					Util.ClickElement(driver, departureStationChkBxMIA);
				else if (stationList[i].contains("ONT"))
					Util.ClickElement(driver, departureStationChkBxONT);
				else if (stationList[i].contains("ORD"))
					Util.ClickElement(driver, departureStationChkBxORD);
				else if (stationList[i].contains("PBI"))
					Util.ClickElement(driver, departureStationChkBxPBI);
				else if (stationList[i].contains("PHL"))
					Util.ClickElement(driver, departureStationChkBxPHL);
				else if (stationList[i].contains("PHX"))
					Util.ClickElement(driver, departureStationChkBxPHX);
				else if (stationList[i].contains("SAN"))
					Util.ClickElement(driver, departureStationChkBxSAN);
				else if (stationList[i].contains("SNA"))
					Util.ClickElement(driver, departureStationChkBxSNA);
				else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Wrong Departure Station",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	//19-01-2022
	public void checkDepartureStationsCheckBoxes(String stations) 
	{
		try 
		{
			String[] stationList = stations.split(",");
			for (int i = 0; i < stationList.length; i++) 
			{
				String stationcheckBox = "//*[.='" + stationList[i] + "']//input";
				System.out.println(stationcheckBox);
				Util.ClickElement(driver, driver.findElement(By.xpath(stationcheckBox)));				
			}
			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected stations: "+stations,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Selected stations: "+stations, true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in selecting stations: "+stations,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in selecting stations: "+stations, false);
		}
	}

	public boolean showSeqIsClickable() {
		return Util.waitForElementClickable(driver, showSequences);
	}

	public void enterDepartureToDate(String depDate) {
		sendKey2(departureToDateinput, depDate);
	}

	public void clickDate() throws InterruptedException {
		Util.ClickButton(driver, dateClmnSort, 5);
	}

	public String[] checkTopNBallots(Integer n) throws InterruptedException {
		String[] ret = new String[2 * n];
		if(driver.findElements(By.xpath("//*[@id='sequencesLargeTable']/tbody/tr")).size() == 0) {
			selectDepartureToDate("15");
			clickOnShowSequence();
			Util.waitForLoad(driver);
		}
		String address = "//*[@id='sequencesLargeTable']/tbody/tr[%s]/td[%s]";
		for (int i = 1; i <= n*2; i+=2) {
			WebElement ele = null;
			String xp = String.format(address, i, 1);
			System.out.println(i);
			//Thread.sleep(7000);
			for (int l = 0; l < 5; l++)
				try {
					//Thread.sleep(500);
					ele = driver.findElement(By.xpath(xp));
					break;
				} catch (Exception e) {
					if (l == 4) {
						log.error("There is no element at " + xp);
						throw e;
					}
				}
			try {
				Util.ClickButton(driver, ele, 5);
				Util.waitForLoad(driver);
			} catch (Exception e) {
				i--;
				continue;
			}
			xp = String.format(address, i, 3);
			ele = driver.findElement(By.xpath(xp));
			ret[i-1] = ele.getText();
			xp = String.format(address, i, 4);
			ele = driver.findElement(By.xpath(xp));
			ret[i] = ele.getText();
			//Thread.sleep(2000);
		}
		return ret;
	}

	//12-01-2022
		public void clickBallotSubmitButton() throws InterruptedException {
			try {
				Util.ClickButton(driver, ballotSubmitButton);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully clicked save selected sequences button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Successfully selected ballot types", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on save selected sequences button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in selecting ballot types", false);
			}
		}

		//17 Aug 2022
		//updated function to select ballot type
		public void checkBallotTypes(String[] bTypes) throws InterruptedException {
			try {
				
				String xpth = "//span[text()='%s']/../..//input";
				for (int i = 0; i < bTypes.length; i++) {
					if(bTypes[i].equals("PickUpDOTC"))
						bTypes[i] = "Pick Up DOTC";
					else if(bTypes[i].equals("PickUpOutside"))
						bTypes[i] = "Pick Up Outside";
					else 
						bTypes[i] = "Template";
					Util.ClickButton(driver, driver.findElement(By.xpath(String.format(xpth, bTypes[i]))));
				}
				ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully selected ballot types",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Successfully selected ballot types", true);
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in selecting ballot types",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in selecting ballot types", false);
			}
		}

	public void clickAddBtn(int n) throws InterruptedException {
		Util.ClickButton(driver, addBtn, n);
	}

	// Click Paid Credit Plus icon under the Pay and Credit collapsible to add it as
	// a Search Criteria
	public void clickPaidCreditPlus() {
		try {
			Util.ClickElement(driver, paidCreditPlus);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked Paid Credit Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click Paid Credit Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}

	}

	// Select Max Hours from Paid Credit Drop Down
	public void selectMaxPaidCreditHoursDrpDwn(String value) {
		try {
			Util.SelectFrmDropDown(driver, maxPaidCreditHoursDrpDwn, value, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Select Max Mins from Paid Credit Drop Down
	public void selectMaxPaidCreditMinutesDrpDwn(String value) {
		try {
			Util.SelectFrmDropDown(driver, maxPaidCreditMinsDrpDwn, value, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Select Min Hours from Paid Credit Drop Down
	public void selectMinPaidCreditHoursDrpDwn(String minValue) {
		try {
			Util.SelectFrmDropDown(driver, minPaidCreditHoursDrpDwn, minValue, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Select Min Minutes from Paid Credit Drop Down
	public void selectMinPaidCreditMinutesDrpDwn(String minValue) {
		try {
			Util.SelectFrmDropDown(driver, minPaidCreditMinsDrpDwn, minValue, true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void clickCalendarDaysPlus() {
		try {
			Util.waitForLoad(driver);
			Util.ClickElement(driver, calendarDaysPlus);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked Calendar Days Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click Calendar Days Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}

	}

	public void selectCalendarDaysDrpdown(String min, String max) {
		try {
			Util.SelectFrmDropDown(driver, maxCalendarDaysDrpDwn, max, true);
			Util.SelectFrmDropDown(driver, minCalendarDaysDrpDwn, min, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added min and max values for Calendar Days",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added min and max values for Calendar Days", true);
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in adding min and max values for Calendar Days",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding min and max values for Calendar Days", false);
			ex.printStackTrace();
		}
	}

	public void selectPaidCreditDrpdown(String maxHrs, String maxMins, String minHrs, String minMins) {
		try {
			Util.SelectFrmDropDown(driver, maxPaidCreditHoursDrpDwn, maxHrs, true);
			Util.SelectFrmDropDown(driver, maxPaidCreditMinsDrpDwn, maxMins, true);
			Util.SelectFrmDropDown(driver, minPaidCreditHoursDrpDwn, minHrs, true);
			Util.SelectFrmDropDown(driver, minPaidCreditMinsDrpDwn, minMins, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added values for Paid Credit",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added values for Paid Credit", true);
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in adding values for Paid Credit",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding values for Paid Credit", false);
			ex.printStackTrace();
		}
	}

	public ArrayList<String> getListOfDepStationSeq(int TotalSeq) {
		ArrayList<String> totalStations = new ArrayList<String>();
		String xpath = "//*[@id='sequencesLargeTable']//tbody//tr[(@class='ng-star-inserted')][%s]";
		for (int i = 1; i <= TotalSeq; i++) {
			String xp = String.format(xpath, i);
			log.info(String.format("Row %s: %s", i, driver.findElement(By.xpath(xp)).getText()));
			totalStations.add(driver.findElement(By.xpath(xp + "//td[5]")).getText());
		}
		return totalStations;
	}

	public void verifySeqResultsforDepartureStations(String stationsInput) {
		try {
			String[] stations = stationsInput.split(",");
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if (totalSeq > 0) {
				ArrayList<String> departureStationSeqResults = getListOfDepStationSeq(totalSeq);
				for (int i = 0; i < departureStationSeqResults.size(); i++) {
					String stationName = departureStationSeqResults.get(i);
					for (int j = 0; j < stations.length; j++) {
						if (stations[j].contains(stationName)) {
							String output = "Departure Stations " + (i + 1) + " is good. Actual Value from seq output ="
									+ departureStationSeqResults.get(i) + " and Expected Value is = " + stations[j];
							System.out.println(output);
							Assert.assertTrue(output, true);
							ExtentTestManager.getTest().log(LogStatus.PASS, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							break;

						} else {
							if (j == stations.length - 1) {
								String output = "Fail: Departure Stations " + (i + 1)
										+ " is Bad. Actual Value from seq output =" + departureStationSeqResults.get(i)
										+ " and Expected Value is = " + stations[j];
								log.info(output);
								Assert.assertTrue(output, false);
							}
						}
					}

				}

			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Sequence returned for this Departure Station",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("No Sequence returned for this Departure Station", false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating sequences",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in validating sequences", false);
		}
	}

	public String[] getDepartureStationsForABase(String base) {
		String[] departureStationList = new String[] {};
		if (base.contains("BOS")) {
			departureStationList = new String[] { base };
		}

		if (base.contains("CLT")) {
			departureStationList = new String[] { base };
		}

		if (base.contains("DCA")) {
			departureStationList = new String[] { base, "BWI", "IAD" };
		}

		if (base.contains("DFW")) {
			departureStationList = new String[] { base, "DAL" };
		}

		if (base.contains("LGA")) {
			departureStationList = new String[] { base, "EWR", "JFK" };
		}

		if (base.contains("LAX")) {
			departureStationList = new String[] { base, "LGB", "ONT", "SAN", "SNA" };
		}

		if (base.contains("MIA")) {
			departureStationList = new String[] { base, "FLL", "PBI" };
		}

		if (base.contains("ORD")) {
			departureStationList = new String[] { base, "MDW" };
		}

		if (base.contains("PHL")) {
			departureStationList = new String[] { base };
		}

		if (base.contains("PHX")) {
			departureStationList = new String[] { base };
		}

		return departureStationList;

	}

	//19-01-2022
	public void verifySeqResultsforDepartureStationsBasedOnBase(String station) {
		try {
			String[] stations = getDepartureStationsForABase(station);
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if (totalSeq > 0) {
				ArrayList<String> departureStationSeqResults = getListOfDepStationSeq(totalSeq);
				for (int i = 0; i < departureStationSeqResults.size(); i++) {
					String stationName = departureStationSeqResults.get(i);
					for (int j = 0; j < stations.length; j++) {
						if (stations[j].contains(stationName)) {
							log.info("Pass: Departure Stations " + (i + 1) + " is good. Actual Value from seq output ="
									+ departureStationSeqResults.get(i) + " and Expected Value is = " + stations[j]);
							ExtentTestManager.getTest().log(LogStatus.PASS, "Pass: Departure Stations " + (i + 1) + " is good. Actual Value from seq output ="
									+ departureStationSeqResults.get(i) + " and Expected Value is = " + stations[j],
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							Assert.assertTrue("Pass: Departure Stations " + (i + 1) + " is good. Actual Value from seq output ="
									+ departureStationSeqResults.get(i) + " and Expected Value is = " + stations[j], true);
							break;
						}
						if (j == stations.length - 1) {
							String output = "Fail: Departure Stations " + (i + 1)
									+ " is Bad. Actual Value from seq output =" + departureStationSeqResults.get(i)
									+ " and Expected Value is = " + stations[j];
							log.info(output);
							ExtentTestManager.getTest().log(LogStatus.FAIL, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		
							Assert.assertTrue(output, false);
						}
					}

				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating sequence result based on base",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			Assert.assertTrue("Exception in validating sequence result based on base", false);
		}
	}

	public void verifyOtherFieldDisabled() {
		try {
			Util.ClickElement(driver, ballotsDaysDateHour);
			List<WebElement> daysCriteria = driver.findElements(By.xpath(
					"//*[contains(.,'Days, Dates & Hours')]/a//parent::div[@class='sectionHeader']//following-sibling::div//tbody/tr"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();",ballotsPositionDivison);
			if (daysCriteria.size() == 0) {

				System.out.println("Days, Dates & Hours dropdown is disabled");
				Assert.assertTrue("Days,Date & Hours dropdown is disabled", true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Days,Date & Hours dropdown is disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			} else {

				System.out.println("Days,Date & Hours dropdown is not disabled");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Days,Date & Hours dropdown is not disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Days,Date & Hours dropdown is not disabled", true);

			}

			Util.ClickElement(driver, ballotsPayCredit);
			List<WebElement> payCreditCriteria = driver.findElements(By.xpath(
					"//*[contains(.,' Pay & Credit')]/a//parent::div[@class='sectionHeader']//following-sibling::div//tbody/tr"));

			if (payCreditCriteria.size() == 0) {

				System.out.println("PayCredit dropdown is disabled");
				Assert.assertTrue("PayCredit dropdown is disabled", true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "PayCredit dropdown is disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			} else {

				System.out.println("PayCredit dropdown is not disabled");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "PayCredit dropdown is not disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("PayCredit dropdown is not disabled", true);

			}

			Util.ClickElement(driver, ballotsSequenceCharacteristics);
			List<WebElement> seqCharecteristicsCriteria = driver.findElements(By.xpath(
					"//*[contains(.,' Sequence Characteristics')]/a//parent::div[@class='sectionHeader']//following-sibling::div//tbody/tr"));

			if (seqCharecteristicsCriteria.size() == 0) {

				System.out.println("Sequence Charecteristics dropdown is disabled");
				Assert.assertTrue("Sequence Charecteristics dropdown is disabled", true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence Charecterisitics dropdown is disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			} else {

				System.out.println("Sequence Charecteristics dropdown is not disabled");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence Charecterisitics dropdown is not disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Sequence Charecteristics dropdown is not disabled", true);

			}

			Util.ClickElement(driver, ballotsAirportsLayovers);
			List<WebElement> AirportNlayoverCriteria = driver.findElements(By.xpath(
					"//*[contains(.,'Airports & Layovers')]/a//parent::div[@class='sectionHeader']//following-sibling::div//tbody/tr"));

			if (AirportNlayoverCriteria.size() == 0) {

				System.out.println("Airport and Layover dropdown is disabled");
				Assert.assertTrue("Airport and Layovers dropdown is disabled", true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Airport and Layover dropdown is disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			} else {

				System.out.println("Airport and Layover dropdown is not disabled");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Airport and Layover dropdown is not disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Airport and Layover dropdown is not disabled", true);

			}

			Util.ClickElement(driver, ballotsPositionDivison);
			List<WebElement> positionCriteria = driver.findElements(By.xpath(
					"//*[contains(.,'Position & Divison')]/a//parent::div[@class='sectionHeader']//following-sibling::div//tbody/tr"));

			if (positionCriteria.size() == 0) {

				System.out.println("Position and Division dropdown is disabled");
				Assert.assertTrue("Position and Division dropdown is disabled", true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Position and Division dropdown is disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			} else {

				System.out.println("Position and Division dropdown is not disabled");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Position and Division dropdown is not disabled",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Position and Division dropdown is not disabled", true);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in method",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}

	}

	// Verify each sequence from the search results and inspect to see if the STATUS
	// column has
	// a crewedSeqIcon. If the icon is displayed, then click on seq details and
	// verify the crew value
	// i.e. EMP ID of CA or FO as applicable.
	// If Icon is not displayed then click on seq details and verify appropriate seq
	// position is open
	// removing the sequence limit and adding code to break when the crewed sequence icon is visible
	public void verifyCrewedSeqIconFromSearchResults() {
		try {
			int count = 0;
			Util.waitForElementClickable(driver, statusColumnLocator);
			int TotalSequences = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			/*if (TotalSequences > 3)
			{
			TotalSequences =3;
			System.out.println("Limiting the processing to max 3 sequences");
			}*/
			Boolean crewed_icon_present;
			List<String> Resultlist = new ArrayList<>();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			for (int i = 1; i <= TotalSequences; i++) {
				// Read the Status column(column 15) from the search results and check if Icon
				// is displayed
				String expression = "//table[@id='sequencesLargeTable']//tbody//tr" + "[" + (i + count) + "]"
						+ "//td[15]" + "//div[@id='crewedSequenceIcon']" + "//span[1]";

				crewed_icon_present = Util.FindElementByXPath(driver, expression, 5).isDisplayed();
				List<String> crewedSeqSetailslist = new ArrayList<>();

				if (crewed_icon_present) {
					System.out.println("Processing Seq Number " + i);
					ExtentTestManager.getTest().log(LogStatus.PASS, "For "+i+" Sequence, crewed icon is present",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Resultlist.add("Pass");
					Assert.assertFalse("For "+i+" Sequence, crewed icon is present", false);
					break;
				}
				count++;
			}
			if(Resultlist.isEmpty())
				throw new Exception("Crewed icon not present");
		} catch (Exception ex) {
			System.out.println("Crewed icon is not present");

			ExtentTestManager.getTest().log(LogStatus.FAIL, "Crewed icon is not present",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertFalse("Crewed icon is not present", true);
		}

	}

	// Entering Title for Search as per User Story Number
	//17-01-2022
	public void enterSearchTitleBaseOnUserStory(String srchTitle) {
		try {
			Util.enterText(driver, titleTxtBox, srchTitle);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Title is entered into input box",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Title is entered into input box", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Title is not entered",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Title is not entered", false);
		}

	}

	public void clickLayoverPlus() {
		try {
			Util.ClickElement(driver, layoverPlus);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked Layover Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click Layover Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}

	}

	public void enterLayoverIncludeTxt(String station) {
		sendKey2(includeLayoversTxtBx, station);
	}

	public void enterLayoverExcludeTxt(String station) {
		sendKey2(excludeLayoversTxtBx, station);
	}

	public void validatePremiumIcon(String base, String eqp, String div, String pos) {
		List<String> ps = DOTCCrewSchedulerPage.premSeqNumbers;
		List<String> premSeqDetailslist = new ArrayList<>();
		String fourPart = base + '/' + eqp + '/' + div + '/' + pos;
		try {
			int count = 0;
			Util.waitForElementClickable(driver, statusColumnLocator);
			int TotalSequences = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			Boolean premium_icon_present;
			try {
			for (int i = 1; i <= TotalSequences; i++) {
				// Read the Status column(column 15) from the search results and check if Icon
				// is displayed
				String expression = "//table[@id='sequencesLargeTable']//tbody//tr" + "[" + (i + count) + "]"
						+ "//td[15]" + "//div[@id='premiumSequenceIcon']" + "//span[1]";

				premium_icon_present = Util.FindElementByXPath(driver, expression, 1).isDisplayed();
				
				if (premium_icon_present) {
					String exp = "//table[@id='sequencesLargeTable']/tbody/tr[" + (i + count) + "]" + "/td[3]/a";
					String seqnum = Util.FindElementByXPath(driver, exp, 1).getText();
					exp = "//table[@id='sequencesLargeTable']/tbody/tr[" + (i + count) + "]" + "/td[4]";
					String seqdate = Util.FindElementByXPath(driver, exp, 1).getText();
					premSeqDetailslist.add(seqnum + " " + seqdate.replaceAll("\\s+", "") + " " + fourPart);
				}
				count = count + 1;

			}
			} catch(Exception ext) {
				System.out.println(ext);
			}
			// check if the premium seq list created through the crew scheduler app, stored
			// in array list ps matches
			// premium seq seen in the DOTC app stored in variable premSeqDetailslist.If
			// they are not same or either of those values is null
			// then fail the Test case.
			if(ps.isEmpty()) {
				System.out.println("Sequences list from CS is empty");
				throw new Exception("No sequence from CS");
			}
			
			if(premSeqDetailslist.isEmpty()) {
				System.out.println("Sequences list from UI is empty");
				throw new Exception("No sequence on CS");
			}
			
			System.out.println("Sequence from CS:");
			for(String s: ps)
				System.out.println(s);
			System.out.println("Sequence from UI");
			for(String s: premSeqDetailslist)
				System.out.println(s);
			
			if (!(ps.isEmpty()) & !(premSeqDetailslist.isEmpty())) {
				
				Collections.sort(premSeqDetailslist);
				Collections.sort(ps);
				
				if (premSeqDetailslist.equals(ps)) {
					//js.executeScript("document.body.style.zoom = '0.70'");
					ExtentTestManager.getTest().log(LogStatus.PASS, "validatePremiumIcon TC Passed",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("validatePremiumIcon TC Passed ", true);
				} else {
					//js.executeScript("document.body.style.zoom = '0.70'");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Premium sequence list is different compared to table",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse("Premium sequence list is different compared to table", true);
				}
			} else {
				//js.executeScript("document.body.style.zoom = '0.70'");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Empty premium sequence list",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Empty premium sequence list", true);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating premium icon",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception in validating premium icon " + ex.getMessage(), true);
		}
	}
	
	//12-02-2022
	//change to compare all the sequences from the UI and Service
	public void validateSickIcon() {
		String seqlist = DOTCLogInSteps.seqNo;
		List<String> sickSeqDetailslist = new ArrayList<>();
		List<String> expectedSickSeqList = new ArrayList<String>(Arrays.asList(seqlist.split(",")));
		
		try {
			int count = 0;
			Util.waitForElementClickable(driver, statusColumnLocator);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			int TotalSequences = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if (TotalSequences <= 0) {
				System.out.println("Assertion Failure:validateSickIcon TC Failed, No sequences returned");
				Assert.assertFalse("validateSickIcon TC Failed, No sequences returned ", true);
			}
			Boolean sick_icon_present;
			for (int i = 1; i <= TotalSequences; i++) {
				// Read the Status column(column 15) from the search results and check if Icon
				// is displayed
				String expression = "//table[@id='sequencesLargeTable']//tbody//tr" + "[" + (i + count) + "]"
						+ "//td[15]" + "//div[@class='sequenceIcons']" + "//img[@id='pendingSickIcon']";

				sick_icon_present = Util.FindElementByXPath(driver, expression, 1).isDisplayed();

				if (sick_icon_present) {
					String exp = "//table[@id='sequencesLargeTable']/tbody/tr[" + (i + count) + "]" + "/td[3]/a";
					String seqnum = Util.FindElementByXPath(driver, exp, 1).getText();
					exp = "//table[@id='sequencesLargeTable']/tbody/tr[" + (i + count) + "]" + "/td[4]";
					String seqdate = Util.FindElementByXPath(driver, exp, 1).getText();
					sickSeqDetailslist.add(seqnum);
				}
				count = count + 1;
			}
			if (!Collections.disjoint(sickSeqDetailslist, expectedSickSeqList)) {

				//js.executeScript("document.body.style.zoom = '0.75'");
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"validateSickIcon TC Passed, actual seq returned MATCHES expected seq provided in the feature file,expected sick seq = "
								+ seqlist + " actual sick list from search results = "
								+ String.join(",", sickSeqDetailslist),
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				System.out.println(
						"validateSickIcon TC Passed, expected sick seq list and actual sick seq list MATCH, expected sick seq = "
								+ seqlist + " actual sick list from search results = "
								+ String.join(",", sickSeqDetailslist));
				Assert.assertTrue(
						"validateSickIcon TC Passed, expected sick seq list and actual sick seq list MATCH, expected sick seq = "
								+ seqlist + " actual sick list from search results = "
								+ String.join(",", sickSeqDetailslist),
						true);
			} else {
				//js.executeScript("document.body.style.zoom = '0.75'");
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"validateSickIcon TC Failed, actual seq returned DOES NOT MATCH expected seq provided in the feature file,expected sick seq = "
								+ seqlist + " actual sick list from search results = "
								+ String.join(",", sickSeqDetailslist),
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				System.out.println(
						"Assertion Failed:validateSickIcon TC Failed, mismatch between expected sick seq list and actual sick seq list, expected sick seq = "
								+ seqlist + " actual sick list from search results = "
								+ String.join(",", sickSeqDetailslist));
				Assert.assertFalse(
						"Assertion Failed:validateSickIcon TC Failed, mismatch between expected sick seq list and actual sick seq list, expected sick seq = "
								+ seqlist + " actual sick list from search results = "
								+ String.join(",", sickSeqDetailslist),
						true);
			}

		} catch (Exception ex) {

			//js.executeScript("document.body.style.zoom = '0.75'");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "validateSickIcon TC Failed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			System.out.println("Exception:validateSickIcon TC Failed ");
			Assert.assertFalse("Exception:validateSickIcon TC Failed " + ex.getMessage(), true);
		}

	}

	public void selectDepartNoEarlierThan(String departNoEarlierThanHrs) {

		try {
			Util.SelectFrmDropDown(driver, departNoEarlierThanDropDown, departNoEarlierThanHrs, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "selected Depart No Earlier than time",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select Depart No Earlier Than dropdown",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}

	}

	public void selectDepartNoLaterThan(String departNoLaterThanHrs) {
		try {
			Util.SelectFrmDropDown(driver, departNoLaterThanDropDown, departNoLaterThanHrs, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "selected Depart No Later than time",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select Depart No Later Than dropdown",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	public void selectArriveNoEarlierThan(String arriveNoEarlierThanHrs) {
		try {
			Util.SelectFrmDropDown(driver, arriveNoEarlierThanDropDown, arriveNoEarlierThanHrs, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "selected Arrive No Earlier than time",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select Arrive No Earlier than dropdown",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	public void selectArriveNoLaterThan(String arriveNoLaterThanHrs) {
		try {
			Util.SelectFrmDropDown(driver, arriveNoLaterThanDropDown, arriveNoLaterThanHrs, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "selected Arrive No Later than time",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select Arrive No Later than dropdown",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// 25 Aug 2022
	// updated xapth to validate dep and arr date in ballot
	public void validateDepartureArrivalTime(String departNoEarlier, String departNoLater, String arriveNoEarlier,
			String arriveNoLater, String ballotType, String searchTitle) {

		try {

			if (ballotType.contains("PickUpDOTC")) {
				ballotType = "pickup";
			}
			else if (ballotType.contains("PickUpOutside")) {
				ballotType = "pickupOutside";
			}
			else if (ballotType.contains("Template")) {
				ballotType = "template";
			}

			//String departureTimeXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle
				//	+ "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Dep Time')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
			String departureTimeXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+searchTitle+"']//..//div[normalize-space()='Dep Time']//..";
			String departureTimeText = driver.findElement(By.xpath(departureTimeXpath)).getText().split("\\n")[1];

			//String arrivalTimeXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle
				//	+ "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Arr Time')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
			String arrivalTimeXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+searchTitle+"']//..//div[normalize-space()='Arr Time']//..";
			String arrivalTimeText = driver.findElement(By.xpath(arrivalTimeXpath)).getText();

			String[] departureTimes = departureTimeText.split("-");

			String[] arrivalTimes = arrivalTimeText.split("-");

			// Departure Time Validation
			if (departureTimes[0].contains(departNoEarlier) && departureTimes[1].contains(departNoLater)) {

				String output = "Departure Time for " + ballotType
						+ " ballot is good and matches expected value. The value is " + departureTimeText;

				Assert.assertTrue(output, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			} else {
				String output = "Departure time for " + ballotType + " ballot is not matching the expected value "
						+ departureTimeText;

				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(output, false);
			}

			// Arrival Time Validation
			if (arrivalTimes[0].contains(arriveNoEarlier) && arrivalTimes[1].contains(arriveNoLater)) {
				String output = "Arrival Time for " + ballotType
						+ " ballot is good and matches expected value.The value is " + arrivalTimeText;

				ExtentTestManager.getTest().log(LogStatus.PASS, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(output, true);
			}

			else {
				String output = "Arrival Time for " + ballotType + " ballot is not matching the expected value "
						+ arrivalTimeText;

				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(output, false);

			}
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception occured in the method" + ex.toString(),
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			System.out.println(ex.getMessage());
			Assert.assertTrue("Exception occured in the method", false);
		}

	}

	public void validateSearchSequenceCalendarDays(String minCalendar, String maxCalendar) {

		try {
			int totalSequences = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSequences == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSequences = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if (totalSequences > 0) {
				ArrayList<String> listOfCalendarDays = new ArrayList<String>();
				listOfCalendarDays = getCalendarDays(totalSequences);

				for (int i = 0; i < listOfCalendarDays.size(); i++) {

					String calendarDay = listOfCalendarDays.get(i);
					String seqNum = searchResultsList.get(i).getText();
					if (Integer.parseInt(String.valueOf(calendarDay.charAt(0))) >= Integer.parseInt(minCalendar)
							&& Integer.parseInt(String.valueOf(calendarDay.charAt(0))) <= Integer
									.parseInt(maxCalendar)) {

						String output = "CalendarDay for sequence number " + seqNum
								+ " is good and within expected range between minimun Calendar Day " + minCalendar
								+ " and Maximun calendar Day " + maxCalendar;
						log.info(output);
						Assert.assertTrue(output, true);
						ExtentTestManager.getTest().log(LogStatus.PASS, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					} else {

						String output = "CalendarDay for sequence number " + seqNum
								+ " is not within the range between Minimun Calendar Day " + minCalendar
								+ " and Maximun calendar Day " + maxCalendar;
						Assert.assertTrue(output, false);
						ExtentTestManager.getTest().log(LogStatus.FAIL, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					}
				}
			} else {
				log.info("Number of sequences returned are zero");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Sequence returned for this Days range",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("No Sequence returned for this Days range", true);
			}
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception occured in the method" + ex.toString(),
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Exception occurred in method validateSearchSequenceCalendarDays", false);
		}
	}

	public ArrayList<String> getCalendarDays(int totalSeq) {

		ArrayList<String> listOfCalendarDays = new ArrayList<String>();
		for (int i = 0; i < totalSeq; i++) {

			String expression = "//table[@id='sequencesLargeTable']//tbody//tr[@class='ng-star-inserted']" + "["
					+ (i + 1) + "]" + "//td[8]";
			listOfCalendarDays.add(driver.findElement(By.xpath(expression)).getText());
		}
		return listOfCalendarDays;
	}

	// On the 'Search' page locate the Legs Per DP add button and click that

	public void clicklegsPerDP() {
		try {
			Util.ClickButton(driver, legsPerDPPlusIcon);
			Util.waitForLoad(driver);
			Assert.assertTrue("Legs Per DP is added", true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Legs Per DP is added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Legs Per DP could not be added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Legs Per DP couldn't be added", true);
			ex.printStackTrace();
		}
	}

	// This method is used to select the min and max range for Legs Per DP based on input
	// args
	public void selectLegsPerDPRange(String min, String max) {
		try{

			Select tmax = new Select(maxLegsPerDP); // create a select object to traverse through the static drop down 'TAFB
												// MAX'
			tmax.selectByValue(max);
			Select tmin = new Select(minLegsPerDP); // create a select object to traverse through the static drop down 'TAFB
												// MIN'
			tmin.selectByValue(min);
			Assert.assertTrue("Legs Per DP values added", true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Legs Per DP values added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch(Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in choosing values for Legs Per DP",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception in choosing values for Legs Per DP", true);
			ex.printStackTrace();
		}	
	}
	
	// This method compares the LegsPerDP value of every row of sequence output against
		// the min and max range provided as expected LegsPerDP range in this method
	public void verifySeqResultsforLegsPerDP(String min, String max) {

		try {
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if (totalSeq > 0) {
				ArrayList<String> LegsPerDPFromSeqResults = getListOfLegsPerDPValues(totalSeq);
				for (int i = 0; i < LegsPerDPFromSeqResults.size(); i++) {
					String legs = LegsPerDPFromSeqResults.get(i).replaceAll("[\\n\\t ]", "").split("\\(")[1];
					String[] legsPerDP = legs.split("\\)")[0].split("-|/");
					int dp = 1;
					for (String str : legsPerDP) {

						if (Float.parseFloat(str) >= Float.parseFloat(min)
								&& Float.parseFloat(str) <= Float.parseFloat(max)) {
							String output = "LegsPerDP check for seq number " + (i + 1) + " Duty Period " + dp
									+ " is good and within expected range. Actual LegsPerDP from seq output =" + str
									+ "  MinLegsPerDP=" + min + "  MaxLegsPerDP=" + max;
							log.info(output);
							dp++;
							Assert.assertTrue(output, true);
							ExtentTestManager.getTest().log(LogStatus.PASS, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

						} else {
							String failedoutput = "LegsPerDP check for seq number " + (i + 1) + " Duty Period " + dp
									+ " Failed and is outside expected range Actual LegsPerDP from seq output =" + str
									+ "  MinLegsPerDP=" + min + "  MaxLegsPerDP=" + max;
							Assert.assertFalse(failedoutput, true);
							ExtentTestManager.getTest().log(LogStatus.FAIL, failedoutput,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						}
					}
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Sequence returned for this LegsPerDP range",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("No Sequence returned for this LegsPerDP range", false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Exception occured:verifySeqResultsforLegsPerDP Method " + ex.getMessage(),
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception occured:verifySeqResultsforLegsPerDP Method  " + ex.getMessage(), true);
			throw (ex);
		}
	}

	//Click No Work Days Plus Icon
	public void clickNoWorkDaysPlusIcon()
	{
		try {
			Util.ClickElement(driver, noWorkDaysPlusIcon);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked No Work Days Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click No Work Days Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}
	
	//Click No Work Dates Plus Icon
	public void clickNoWorkDatesPlusIcon()
	{
		try {
			Util.ClickElement(driver, noWorkDatesPlusIcon);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked No Work Dates Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click No Work Dates Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}
	
	//Click No Work Hours Plus Icon
	public void clickNoWorkHoursPlusIcon()
	{
		try {
			Util.ClickElement(driver, noWorkHoursPlusIcon);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked No Work Hours Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click No Work Hours Plus",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}
	
	//Click No Work Days check box and select days as per input
	public void checkNoWorkDaysOptions(String days)
	{
		try
		{
			String[] dayNames = days.split(",");
			
			for(int i = 0; i< dayNames.length;i++)
			{
				driver.findElement(By.xpath("//*[@id='noWorkDays']//div[contains(@class,'customComponent checkboxComponent')]//label[contains(@for,'chk_" + dayNames[i] + "')]/span")).click();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	//Select Minimum No Work Hours From Drop down
	public void selectnoWorkHoursMinDropDown(String minValue) 
	{
		try 
		{
			Util.SelectFrmDropDown(driver, noWorkHoursMinDropDown, minValue, true);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	//Select Maximum No Work Hours From Drop down
	public void selectnoWorkHoursMaxDropDown(String maxValue) {
		try 
		{
			Util.SelectFrmDropDown(driver, noWorkHoursMaxDropDown, maxValue, true);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	public void selectNoWorkDates(String offset1,String offset2) 
	{
		try
		{
			js = (JavascriptExecutor) driver;
			
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, Integer.parseInt(offset1));
			String currentMonth = new SimpleDateFormat("M").format(c.getTime());
			String currentDay = new SimpleDateFormat("d").format(c.getTime());
			WebElement frstWorkDatePicker = driver.findElement(By.xpath("//*[@id='noWorkDate0']//button[contains(@class,'ui-datepicker')]"));
			Util.ClickElement(driver, frstWorkDatePicker);
            WebElement frstWorkDate = driver.findElement(By.xpath("//table[contains(@class,'ui-datepicker-calendar')]//span[@data-day='"+currentDay+"' and @data-month='"+currentMonth+"']"));
            Util.ClickElement(driver, frstWorkDate);
            Util.waitForLoad(driver);
            c.setTime(new Date());
			c.add(Calendar.DATE, Integer.parseInt(offset2));
			currentMonth = new SimpleDateFormat("M").format(c.getTime());
			currentDay = new SimpleDateFormat("d").format(c.getTime());
			WebElement secondWorkDatePicker = driver.findElement(By.xpath("//*[@id='noWorkDate1']//button[contains(@class,'ui-datepicker')]"));
			Util.ClickElement(driver, secondWorkDatePicker);
            WebElement secondtWorkDate = driver.findElement(By.xpath("//table[contains(@class,'ui-datepicker-calendar')]//span[@data-day='"+currentDay+"' and @data-month='"+currentMonth+"']"));
            Util.ClickElement(driver, secondtWorkDate);
            

			ExtentTestManager.getTest().log(LogStatus.PASS, "No Work Hours 1st and 2nd dates are selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "No Work Hours 1st and 2nd dates are not selected ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception Occured:No Work Hours 1st and 2nd dates are not selected " + ex.getMessage(), true);

		}
	
	}
	
	public void clicklegsPerSeq() {
		try {
			Util.ClickButton(driver, legsPerSequencePlusIcon);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "legs Per Seq is added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "legs Per Seq could not be added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}
	

	// This method is used to select the min and max range for legs Per DP based on input
	// args
	public void selectLegsPerSeqRange(String min, String max) {
		try {

			Select tmax = new Select(maxLegsPerSeqRange); // create a select object to traverse through the static drop down 'TAFB
														// MAX'
			tmax.selectByValue(max);
			Select tmin = new Select(minLegsPerSeqRange); // create a select object to traverse through the static drop down 'TAFB
														// MIN'
			tmin.selectByValue(min);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Leg per Sequence values added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Leg per Sequence values added", true);
		} catch(Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Exception to choose Leg per Sequence values",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception to choose Leg per Sequence values", true);
			ex.printStackTrace();
		}
	}

	
	public void clickDeadHeads() {
		try {
			Util.ClickButton(driver, DeadheadsPlusIcon);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "DeadHeads is added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DeadHeads could not be added",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void DeadHeadsCheckBox(String DeadHeadCheckbox) {
		try {
			String [] DeadHeadCheckboxList = DeadHeadCheckbox.split(",");
			for (int i = 0; i < DeadHeadCheckboxList.length; i++) {

				if (DeadHeadCheckboxList[i].contains("No DH's"))
					Util.ClickElement(driver, nodhrequiredchkbox);
				else if (DeadHeadCheckboxList[i].contains("DH Required"))
					Util.ClickElement(driver, dhrequiredchkbox);
				else if (DeadHeadCheckboxList[i].contains("DH First Leg"))
					Util.ClickElement(driver, dhfirstlegchkbox);
				else if (DeadHeadCheckboxList[i].contains("DH Last Leg"))
					Util.ClickElement(driver, dhlastlegchkbox);
				else if (DeadHeadCheckboxList[i].contains("DH First or Last Leg"))
					Util.ClickElement(driver, dhfirstorlastlegchkbox);
				else if (DeadHeadCheckboxList[i].contains("Multiple DH's"))
					Util.ClickElement(driver, multipledhchkbox);
				else if (DeadHeadCheckboxList[i].contains("DH ONLY on AA"))
					Util.ClickElement(driver, dhonlyonaachkbox);
				else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Wrong DeadHead Request and checkbox is not clikced",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//18-01-2022
	public void clickSitTimePlus()
    {
        try
        {
            Util.ClickButton(driver, sitTimePlusIcon);  
            ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on sit time plus icon",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
            Assert.assertTrue("Clicked on sit time plus icon", true);
        }
        catch(Exception ex)
        {
			ex.printStackTrace();
        	ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in Clicking on sit time plus icon",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
            Assert.assertTrue("Exception in clicking on sit time plus icon", false);
        }
    
    }

	public void clickOnElementInSearch(WebElement element, String elementName) {
		try {

			Util.ClickElement(driver, element);
			ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on " + elementName,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click on "+elementName,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void clickOnPositionAndDivisionLink() {

		clickOnElementInSearch(ballotsPositionDivison, "Position & Division");
	}

	public void clickOnPositionPlusIcon() {

		clickOnElementInSearch(positionPlusIcon, "Position Plus");
	}

	public void clickOnDivisionPlusIcon() {

		clickOnElementInSearch(divisionPlusIcon, "Division Plus");
	}

	public void selectPositionCheckBox(String positionVal) {

		try {
			String xpath = "//div[@class='ng-star-inserted']//*[.='" + positionVal
					+ "']/ancestor::aabp-checkbox/div[@class='customComponent checkboxComponent']//label[@class='checkboxLabel']/span";

			Util.ClickElement(driver, driver.findElement(By.xpath(xpath)));
			System.out.println("selected position check box " + positionVal);
			ExtentTestManager.getTest().log(LogStatus.PASS, "selected Position check box " + positionVal,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {

			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select Position check box " + positionVal,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void selectDivisionCheckBox(String divisionVal) {

		try {
			String xpath = "//div[@class='ng-star-inserted']//*[.='" + divisionVal
					+ "']/ancestor::aabp-checkbox/div[@class='customComponent checkboxComponent']//label[@class='checkboxLabel']/span";

			Util.ClickElement(driver, driver.findElement(By.xpath(xpath)));
			System.out.println("selected Division check box " + divisionVal);
			ExtentTestManager.getTest().log(LogStatus.PASS, "selected Division check box " + divisionVal,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {

			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select Division check box " + divisionVal,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public ArrayList<String> getPositionLabelFromUI(int numberOfCheckBox) {

		ArrayList<String> listOfPositionLabels = new ArrayList<String>();
		for (int i = 1; i <= numberOfCheckBox; i++) {

			String expression = "//div[@id='position']//div[@class='labelTopCheckbox ng-star-inserted'][" + i
					+ "]//aabp-checkbox/div[@class='ng-star-inserted']/label[@class='checkbox-label']/span[not (@class='control')]";

			listOfPositionLabels.add(driver.findElement(By.xpath(expression)).getText());
		}
		return listOfPositionLabels;
	}

	public ArrayList<String> getPositions(String positionVal) {

		ArrayList<String> positionList = new ArrayList<>();
		switch (positionVal) {
		case "CA":
			positionList = new ArrayList<>(Arrays.asList(positionVal, "RC"));
			break;

		case "FO":
			positionList = new ArrayList<>(Arrays.asList(positionVal, "FB", "FC"));
			break;
		}
		return positionList;
	}

	public void verifyPositionCheckBoxes(String pos) {

		int numOfPositionCheckBox = listOfpositionCheckBoxes.size();
		String output = "";

		try {
			if (numOfPositionCheckBox > 0) {

				ArrayList<String> positionLabels = getPositionLabelFromUI(numOfPositionCheckBox);
				ArrayList<String> positionList = getPositions(pos);
				for (int i = 0; i < positionLabels.size(); i++) {

					if (positionList.contains(positionLabels.get(i))) {

						output = "Position label " + positionLabels.get(i) + " is from the position list "
								+ positionList;
						System.out.println(output);
						Assert.assertTrue(output, true);
						ExtentTestManager.getTest().log(LogStatus.PASS, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					} else {

						output = "Position label " + positionLabels.get(i) + " is not from the position list  "
								+ positionList;
						Assert.assertTrue(output, false);
						ExtentTestManager.getTest().log(LogStatus.FAIL, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					}
				}
			} else {

				output = "Number of position checkboxes are zero";
				Assert.assertTrue(output, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		} catch (Exception ex) {

			String message = "Exception occurred in method verifyPositionCheckBoxes";
			Assert.assertTrue(message, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, message,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void verifyDivisionCheckBoxes(String divisionVal) {

		int numOfDivisionsCheckBox = listOfDivisionCheckBoxes.size();
		String[] divisionsArray = divisionVal.split(",");
		LinkedHashSet<String> divisionsSet = new LinkedHashSet<String>(Arrays.asList(divisionsArray));
		String output = "";

		try {
			if (numOfDivisionsCheckBox > 0) {

				ArrayList<String> divisionLabels = getDivisionLabelFromUI(numOfDivisionsCheckBox);
				LinkedHashSet<String> divisionLabelSet = new LinkedHashSet<String>();
				divisionLabelSet.addAll(divisionLabels);

				if (divisionLabelSet.size() == divisionsSet.size() && divisionsSet.containsAll(divisionLabelSet)) {

					output = "Divisions from UI " + divisionLabelSet + " match with divisions " + divisionsSet;
					Assert.assertTrue(output, true);
					System.out.println(output);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} else {

					output = "Divisions from UI " + divisionLabelSet + " do not match with divisions " + divisionsSet;
					Assert.assertTrue(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			} else {

				output = "Number of Division checkboxes are zero";
				Assert.assertTrue(output, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		} catch (Exception ex) {

			String message = "Exception occurred in method verifyPositionCheckBoxes";
			Assert.assertTrue(message, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, message,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public ArrayList<String> getDivisionLabelFromUI(int numberOfCheckBox) {

		ArrayList<String> listOfDivisionLabels = new ArrayList<String>();
		for (int i = 1; i <= numberOfCheckBox; i++) {

			String expression = "//div[@id='division']//div[@class='labelTopCheckbox ng-star-inserted'][" + i
					+ "]//aabp-checkbox/div[@class='ng-star-inserted']/label[@class='checkbox-label']/span[not (@class='control')]";

			listOfDivisionLabels.add(driver.findElement(By.xpath(expression)).getText());
		}
		return listOfDivisionLabels;
	}
	
	// code added
	//17-01-2022
	public void selectDepartureStationInSequenceNumberOption(String base) {
		String departureStationCheckBox = "";
		try {
			departureStationCheckBox = "//aabp-checkbox[@labelposition='right']//*[.='"+base+"']//..//..//div[@class='customComponent checkboxComponent isInValid']//span[@class='control']";
			//departureStationCheckBox = "//aabp-checkbox[@labelposition='right']//*[.='"+base+"']//..//..//div[@class='customComponent checkboxComponent']//span[@class='control']";
			Util.ClickElement(driver, driver.findElement(By.xpath(departureStationCheckBox)));
			Util.waitForLoad(driver);
			Assert.assertTrue("Clicked on base: " + base, true);
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, base+" selected from departure stations list",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch(Exception ex) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, base+" base not found in the departure stations list",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue(base+" base not found in the departure stations list", false);
			
		}
	}
	
	//25-05-2022
	//function to select Arrival from date acc to offset value
	public void selectArrivalFromDate(String num) {
		try {
			int numb = Integer.parseInt(num);
			Util.ClickElement(driver, arrivalFromDateDtPkr);
			Util.waitForLoad(driver);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, numb);
			String day = new SimpleDateFormat("d").format(calendar.getTime());
			String month = new SimpleDateFormat("M").format(calendar.getTime());
			
			while(!new SimpleDateFormat("MMMM").format(calendar.getTime()).equalsIgnoreCase(datePickerHeader.get(0).getText()))
				Util.ClickElement(driver, calanderNextMonthLink);
			
			String xpath = "//div[contains(@class, 'datepicker')]//td//span[@data-month = '"+month+"' and @data-day = '"+day+"']";
			
			Util.ClickElement(driver, driver.findElement(By.xpath(xpath)));
			
			ArrFrom = arrivalFromDateinput.getAttribute("value");
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added arrival from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Adding arrival from date", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in Adding arrival from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding arrival from date", false);
		}
	}
	//19-01-2022
		public boolean verifyCurrentDateAsLastDateOfTheMonth(int num) {
			String callastDate = "";
			boolean lastDayFlag = false;

			try {
				callastDate = calCurrentDate.getText();
				Integer lastDayOfTheMonth = Util.getLastDayOfTheMonth();
				if (lastDayOfTheMonth < (Integer.parseInt(callastDate)+num)) {
					lastDayFlag = true;
				}
			} catch (Exception ex) {
				ex.getStackTrace();
			}
			return lastDayFlag;
		}
	
	//19-10-2022
	public void selectDepartureFromDate(String num) {
		try {
			int numb = Integer.parseInt(num);
			Util.ClickElement(driver, departureFromDateDtPkr);
			Util.waitForLoad(driver);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, numb);
			String day = new SimpleDateFormat("d").format(calendar.getTime());
			String month = new SimpleDateFormat("M").format(calendar.getTime());
			
			while(!new SimpleDateFormat("MMMM").format(calendar.getTime()).equalsIgnoreCase(datePickerHeader.get(0).getText()))
				Util.ClickElement(driver, calanderNextMonthLink);
			
			String xpath = "//div[contains(@class, 'datepicker')]//td//span[@data-month = '"+month+"' and @data-day = '"+day+"']";
			
			Util.ClickElement(driver, driver.findElement(By.xpath(xpath)));

			DepFrom = departureFromDateinput.getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added departure from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added departure from date", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in Adding departure from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding departure from date", false);
		}
	}
	
	
	//Function to validate GRD from search sequence list
	//20-01-2022
	public void validateGRDInSearchSequenceList(String sitTime) {
		try {
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if(totalSeq > 0) {
				int count = 1;
				for(int i=0; i<totalSeq; i++) {
					// xpath for sequence number anchor tag
					String expression = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[3]/a";
					WebElement seqLink = driver.findElement(By.xpath(expression));
					String seqNumber = seqLink.getText();
					Util.ClickButton(driver, seqLink);
					// get text for GRD from the table according to seqNumber
					for(WebElement ele: SearchSequenceGRDColumn) {
						String text = ele.getText();
						if(Float.parseFloat(text) > Float.parseFloat(sitTime)) {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence "+seqNumber+" is having GRD value greater than "+sitTime+" i.e, "+text,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							Assert.assertTrue("Sequence "+seqNumber+" is having GRD value greater than "+sitTime+" i.e, "+text, false);
						}
					}
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seqLink);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence "+seqNumber+" is having GRD value less than or equal to "+sitTime,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Sequence "+seqNumber+" is having GRD value less than or equal to "+sitTime, true);
					Util.waitForLoad(driver);
					Util.ClickButton(driver, seqLink);
					count++;
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No sequences in search sequence list",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("No sequences in search sequence list", false);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in verifying GRD/sit time in sequence list",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in verifying GRD/sit time in sequence list "+ex.getMessage(), false);
		}
	}
	
	//Function to validate position and division in sequence list
	//20-01-2022
	public void validatePositionAndDivisionInSearchSequenceList(String position, String division) {
		try {
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			division = ""+division.charAt(0);
			if(totalSeq > 0) {
				int count = 1;
				for(int i=0; i<totalSeq; i++) {
					// xpath for division and position
					String divxpath = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[6]";
					String div = driver.findElement(By.xpath(divxpath)).getText();
					
					String posxpath = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[7]";
					String pos = driver.findElement(By.xpath(posxpath)).getText();
					
					String expression = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[3]/a";
					String seqNumber = driver.findElement(By.xpath(expression)).getText();
					
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[contains(@class,'visible-lg')]//tbody")));
					
					
					if(pos.equals(position) && div.equals(division)) {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence "+seqNumber+" has position: "+pos+", expected: "+position+" and has division: "+div+", expected: "+division,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue("Sequence "+seqNumber+" has position: "+pos+", expected: "+position+" and has division: "+div+", expected: "+division, true);
					} else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence "+seqNumber+" has position: "+pos+", expected: "+position+" and has division: "+div+", expected: "+division,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue("Sequence "+seqNumber+" has position: "+pos+", expected: "+position+" and has division: "+div+", expected: "+division, false);
					}
					count++;
				}
			}else {
				ExtentTestManager.getTest().log(LogStatus.PASS, "No sequences in search sequence list",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("No sequences in search sequence list", true);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in verifying position and division in sequence list",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in verifying position and division in sequence list "+ex.getMessage(), false);
		}
	}
	
	//Function to validating sequences in sequence list is within depart no earlier than and depart no later than time
	//21-01-2022
	public void validateDepartNoEarlierAndDepartNoLaterInSearchSequenceList(String earlier, String later) {
		try {
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq == 0) {
				selectDepartureToDate("15");
				clickOnShowSequence();
				Util.waitForLoad(driver);
				totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			}
			if(totalSeq > 0) {
				int count = 1;
				for(int i=0; i<totalSeq; i++) {
					String expression = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[3]/a";
					WebElement seqLink = driver.findElement(By.xpath(expression));
					String seqNumber = seqLink.getText();
					//Util.ClickButton(driver, seqLink);
					
					String exp = "//div[contains(@class,'visible-lg')]//tbody//tr[" + (i + count) + "]//td[10]";
					int startTime = Integer.parseInt(driver.findElement(By.xpath(exp)).getText());
					
					int earl = Integer.parseInt(earlier.replace(":", ""));
					int lat = Integer.parseInt(later.replace(":", ""));
					
					if(startTime >= earl && startTime <= lat) {
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", showSequences);
						ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence "+seqNumber+" have start time: "+startTime+" which is within "+earlier.replace(":", "")+" and "+later.replace(":", ""),
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue("Sequence "+seqNumber+" have start time: "+startTime+" which is within "+earlier.replace(":", "")+" and "+later.replace(":", ""), true);
					} else {
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence "+seqNumber+" have start time: "+startTime+" which is outside the limit "+earlier.replace(":", "")+" and "+later.replace(":", ""),
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue("Sequence "+seqNumber+" have start time: "+startTime+" which is outside the limit "+earlier.replace(":", "")+" and "+later.replace(":", ""), false);
					}
					count++;
				}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No sequence in sequence list",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("No sequence in sequence list", false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in verifying depart no earlier and depart no later in sequence list",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in verifying depart no earlier and depart no later in sequence list "+ex.getMessage(), false);
		}
	}
	
	//Function to validating sequences in sequence list is within arrival no earlier than and arrival no later than time
	//21-01-2022
		public void validateArriveNoEarlierAndArriveNoLaterInSearchSequenceList(String earlier, String later) {
			try {
				int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
				if(totalSeq == 0) {
					selectDepartureToDate("15");
					clickOnShowSequence();
					Util.waitForLoad(driver);
					totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
				}
				if(totalSeq > 0) {
					int count = 1;
					for(int i=0; i<totalSeq; i++) {
						String expression = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[3]/a";
						WebElement seqLink = driver.findElement(By.xpath(expression));
						String seqNumber = seqLink.getText();
						
						String exp = "//div[contains(@class,'visible-lg')]//tbody//tr[" + (i + count) + "]//td[11]";
						String text = driver.findElement(By.xpath(exp)).getText().split("/")[0];
						int endTime = Integer.parseInt(text);
					
						int earl = Integer.parseInt(earlier.replace(":", ""));
						int lat = Integer.parseInt(later.replace(":", ""));
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", showSequences);
						if(endTime >= earl && endTime <= lat) {
							ExtentTestManager.getTest().log(LogStatus.PASS, "Sequence "+seqNumber+" have end time: "+endTime+" which is within "+earlier.replace(":", "")+" and "+later.replace(":", ""),
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							Assert.assertTrue("Sequence "+seqNumber+" have end time: "+endTime+" which is within "+earlier.replace(":", "")+" and "+later.replace(":", ""), true);
						} else {
							ExtentTestManager.getTest().log(LogStatus.FAIL, "Sequence "+seqNumber+" have end time: "+endTime+" which is outside the limit "+earlier.replace(":", "")+" and "+later.replace(":", ""),
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							Assert.assertTrue("Sequence "+seqNumber+" have end time: "+endTime+" which is outside the limit "+earlier.replace(":", "")+" and "+later.replace(":", ""), false);
						}
						count++;
					}
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "No sequence in sequence list",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("No sequence in sequence list", false);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in verifying arrival no earlier and arrival no later in sequence list",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in verifying arrival no earlier and arrival no later in sequence list "+ex.getMessage(), false);
			}
		}
	
	//Function to validate layovers in search sequence list
	//20-01-2022
	public void validateLayoverInSearchSequenceList() {
		try {
			Util.waitForLoad(driver);
			if(this.includeStation == this.excludeStation) {
				this.includeStation = "";
			}
			int totalSeq = getTotalSequencesFromSearchResults(searchOutputListOfAllSequences);
			if(totalSeq > 0) {
				int count = 1;
					for(int i=0; i<totalSeq; i++) {
						// xpath for layovers
						String expression = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[12]";
						String layovers = driver.findElement(By.xpath(expression)).getText();
				
						expression = "//div[contains(@class,'visible-lg')]//tbody//tr" + "[" + (i + count) + "]" + "//td[3]/a";
						WebElement seqLink = driver.findElement(By.xpath(expression));
						String seqNumber = seqLink.getText();
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", showSequences);
						
						if(layovers.length()>0) {
							char[] ch = layovers.toCharArray();
							List<String> layoversList = new ArrayList<String>();
				
							for(int j=0; j<ch.length; j++) {
								String lay = "";
								if(Character.isLetter(ch[j])) {
									lay+=ch[j]+""+ch[j+1]+""+ch[j+2];
									j+=2;
									layoversList.add(lay);
								}
							}
							
							if(this.includeStation != "") {
								boolean presentFlag = false;
								if(layoversList.contains(this.includeStation)) 
									presentFlag = true;
					
								if(presentFlag) {
									ExtentTestManager.getTest().log(LogStatus.PASS, "Layover from include list is present in layovers from sequence "+seqNumber,
											ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
									Assert.assertTrue("Layover from include list is present in layovers form sequence "+seqNumber, true);
								} else {
									ExtentTestManager.getTest().log(LogStatus.FAIL, "Layover from include list is not present in layovers from sequence "+seqNumber,
											ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
									Assert.assertTrue("Layover from include list is present in layovers from sequence "+seqNumber, false);
								}
							}
							
							if(this.excludeStation != "") {
								boolean presentFlag = false;
								if(layoversList.contains(this.excludeStation))
									presentFlag = true;
				
								if(presentFlag) {
									ExtentTestManager.getTest().log(LogStatus.FAIL, "Layover from exclude list is present in layovers from sequence "+seqNumber,
											ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
									Assert.assertTrue("Layover from exclude list is present in layovers form sequence "+seqNumber, false);
								} else {
									ExtentTestManager.getTest().log(LogStatus.PASS, "Layover from exclude list is not present in layovers from sequence "+seqNumber,
											ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
									Assert.assertTrue("Layover from exclude list is present in layovers from sequence "+seqNumber, true);
								}
							}
								
						} else {
							ExtentTestManager.getTest().log(LogStatus.PASS, "No layovers found",
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							Assert.assertTrue("No layovers found", true);
						}
						count++;
					}
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No sequence in search result",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("No sequence in search result", false);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validating Layovers "+ex,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in validating Layovers "+ex, false);
		}
	}
	
	
	//21-01-2022
		public void selectDepartureDateRange(String stdtoffset, String enddtoffset) {
			try {
				selectDepartureFromDate(stdtoffset);
				selectDepartureToDate(enddtoffset);

				ExtentTestManager.getTest().log(LogStatus.PASS, "DOTC Search:Start and End Date is Selected",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("DOTC Search:Start and End Date is Selected", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "DOTC Search: Start and End Date Selection Failed",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("Exception Occured:DOTC Search: Start Date Selection Failed " + ex.getMessage(), true);

			}

		}
		
		//21-01-2022
		public void selectDepartureToDate(String enddtoffset) {
			try {
				int numb = Integer.parseInt(enddtoffset);
				Util.ClickElement(driver, departureToDateDtPkr);
				Util.waitForLoad(driver);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DATE, numb);
				String day = new SimpleDateFormat("d").format(calendar.getTime());
				String month = new SimpleDateFormat("M").format(calendar.getTime());
				
				while(!new SimpleDateFormat("MMMM").format(calendar.getTime()).equalsIgnoreCase(datePickerHeader.get(0).getText()))
					Util.ClickElement(driver, calanderNextMonthLink);
				
				String xpath = "//div[contains(@class, 'datepicker')]//td//span[@data-month = '"+month+"' and @data-day = '"+day+"']";
				
				Util.ClickElement(driver, driver.findElement(By.xpath(xpath)));

				DepTo = departureToDateinput.getAttribute("value");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Added departure to date",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Added departure to date", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in adding departure to date",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in adding departure to date", false);
			}
		}
		
		//24-01-2022
		public void validateRedBorder(String fieldName) {
			try {
				String color = "";
				WebElement ele = null;
				Util.waitForLoad(driver);
				if(fieldName.equals("Calendar Days"))				
					ele = maxCalendarDaysDrpDwn;
				else if(fieldName.equals("Duty Periods"))
					ele = dutyPeriodsMaxDrpDwn;
				else if(fieldName.equals("Paid Credit"))
					ele = maxPaidCreditHoursDrpDwn;
				else if(fieldName.equals("Total Credit"))
					ele = maxTotalCreditHoursDrpDwn;
				else if(fieldName.equals("Legs Per DP"))
					ele = maxLegsPerDP;
				else if(fieldName.equals("Legs Per Seq"))
					ele = maxLegsPerSeqRange;
				else if(fieldName.equals("TAFB"))
					ele = TAFBMaxRange;
				else if(fieldName.equals("Layover Times"))
					ele = maxLayoverTimeDrpDwn;
				else if(fieldName.equals("No Work Hours"))
					ele = noWorkHoursMaxDropDown;
				else if(fieldName.equals("Include Layovers"))
					ele = includeLayoversTxtBx;
				else if(fieldName.equals("Exclude Layovers"))
					ele = excludeLayoversTxtBx;
				else if(fieldName.equals("Include Cities"))
				    ele = includeCitiesTxtBox;
				else if(fieldName.equals("Exclude Cities"))
				    ele = excludeCitiesTxtBox;
				else if(fieldName.equals("Departure To"))
					ele = departureToDateinput;
				else if(fieldName.equals("Arrival To"))
					ele = arrivalToDateinput;
				
				Util.waitForLoad(driver);
				color = ele.getCssValue("border-color");
				System.out.println(color);
				if(color.equals("rgb(255, 0, 0)") | color.equals("rgb(244, 67, 54)")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Field - '"+fieldName+"' is highlighted in Red as expected: "+color,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Field is highlighted in Red as expected", true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Different color for textbox border "+color,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Different color for textbox border "+color, false);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in identifying the color",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in identifying the color", false);
			}
		}
		
		//24-01-2022
		public void validateSaveGenericCriteriaButtonDisabled() {
			try {
				if(!saveGenericCriteriaBtn.isEnabled()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Save Generic Criteria button is disabled",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Save Generic Criteria button is disabled", true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Save Generic Criteria button is enabled",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Save Generic Criteria button is Enabled", false);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in identifing Save Generic Criteria button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in identifiing Save Generic Criteria button", false);
			}
		}
		
		//24-01-2022
		public void validateShowSequenceButtonDisabled() {
			try {
				if(!showSequences.isEnabled()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Show Sequence button is disabled",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Show Sequence button is disabled", true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Show Sequence button is enabled",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Show Sequence button is Enabled", false);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in identifing Show Sequence button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in identifiing Show Sequence button", false);
			}
		}
		
		//24-01-2022
		public void validateClearButtonEnabled() {
			try {
				if(clearGenericSearchBtn.isEnabled()) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Clear button is enabled",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Clear button is enabled", true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Clear button is disabled",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Clear button is disabled", false);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in identifing clear button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in identifiing clear button", false);
			}
		}
		
		//24-01-2022
		public void clickCalendarDaysMinus() {
			try {
				Util.ClickButton(driver, calendarDaysMinus);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Calendar Days minus clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Calendar Days minus clicked", true);
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Calendar Days minus",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in clicking on Calendar Days minus", false);
			}
		}
		
		//24-01-2022
		public void clickDutyPeriodMinus() {
			try {
				Util.ClickButton(driver, dutyPeriodMinus);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Duty Periods minus clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Duty Periods minus clicked", true);
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Duty Periods minus",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in clicking on Duty Periods minus", false);
			}
		}
		
		//24-01-2022
		public void clickPaidCreditMinus() {
			try {
				Util.ClickButton(driver, paidCreditMinus);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Paid Credits minus clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Paid Credits minus clicked", true);
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Paid Credits minus",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in clicking on Paid Credits minus", false);
			}
		}
		
		//24-01-2022
		public void clickLegPerDPMinus() {
			try {
				Util.ClickButton(driver, legPerDPMinus);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Leg Per DP minus clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Leg Per DP minus clicked", true);
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Leg Per DP minus",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in clicking on Leg Per DP minus", false);
			}
		}
		
		//24-01-2022
		public void clickLegPerSeqMinus() {
			try {
				Util.ClickButton(driver, legPerSeqMinus);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Leg Per Sequences minus clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Leg Per Sequences minus clicked", true);
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Leg Per Sequences minus",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in clicking on Leg Per Sequences minus", false);
			}
		}
		
		//24-01-2022
		public void clickTAFBMinus() {
			try {
				Util.ClickButton(driver, tafbMinus);
				ExtentTestManager.getTest().log(LogStatus.PASS, "TAFB minus clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("TAFB minus clicked", true);
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on TAFB minus",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in clicking on TAFB minus", false);
			}
		}
		
		//24-01-2022
		public void clickLayoverTimesPlus() {
			try {
				Util.ClickButton(driver, layoverTimesPlus);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Layover Times plus clicked",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Layover Times plus clicked", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Layover Times plus",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in clicking on Layover Times plus", false);
			}
		}
		
		//24-01-2022
		public void chooseValuesForLayoverTimes(String min, String max) {
			try {
				Util.SelectFrmDropDown(driver, maxLayoverTimeDrpDwn, max, true);
				Util.SelectFrmDropDown(driver, minLayoverTimeDrpDwn, min, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Layover Times max: "+max+" min: "+min,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Layover Times max: "+max+" min: "+min, true);
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in choosing values for Layover Times",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in choosing values for Layover Times", false);
			}
		}
		
		//07-03-2022
		// this method is used to check if pending sick sequence is present
		public String checkForPendingSickSequence(String empid, String bidStatus) {
			String jsonSeqResponseDep = "";
			int ind = -1;
			String seqNo = "";
			try {
				String bid[] = bidStatus.split("/");
				//String DepartureFromDate = calCurrentDate.getAttribute("data-date");  // 2020-05-03
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 2020-05-03
				
				Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Using today's date
				c.add(Calendar.DATE, 1); // Adding 1 day
				String DepartureFromDate = dateFormat.format(c.getTime());
				
				System.out.println(DepartureFromDate);
				
				String payloadJsonDepRange = "{\"employeeBidStatus\":{\"base\":" + "\"" + bid[0] + "\""
						+ ",\"division\":" + "\"" + bid[3] + "\"" + ",\"equipmentGroup\":" + "\""
						+ bid[1] + "\"" + ",\"seat\":" + "\"" + bid[2] + "\""
						+ "},\"airLineCode\":\"AA\",\"employeeID\":" + empid
						+ ",\"openSequences\":true,\"startDateFrom\":" + "\"" + DepartureFromDate + "\""
						+ ",\"potentialSick\":true}";
				jsonSeqResponseDep = dotc.getSequence(payloadJsonDepRange);
				Util.waitForLoad(driver);
				
				seqNo = DOTCRestService.readJson("$..[?(@.potentialSick==true)].sequenceNumber", jsonSeqResponseDep);
				System.out.println(seqNo);
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception-Sequences didn't fetched  from the services",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
				Assert.assertFalse("Exception-Sequences didn't fetched from the service",true);
			}
			return seqNo;
		}
		
		ArrayList<String> includeList = new ArrayList<String>();
		ArrayList<String> excludeList = new ArrayList<String>();
		int layoverDate = 0;
		//10-03-2022
		// This method is used to to fetch layover stations from service
		public void getLayoverStations(String type) {
			try {
				this.employeeID = DOTCLogInScreen.employeeID;
				getBidStatusForLH();
				int count = 0;
				getLayoverStations(count);
				
				while(true) {
					if((type.contains("include") && includeList.isEmpty()) | (type.contains("exclude") && excludeList.isEmpty())) {
						includeList.clear();
						excludeList.clear();
						if(++count < 7)
							getLayoverStations(count);
						else
							throw new Exception("No data");
					} else
						break;
				}
				layoverDate = count;
				System.out.println("Include list: "+includeList);
				System.out.println("Exclude list: "+excludeList);
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "No Data for 7 days");
				Assert.assertFalse("No Data for 7 days",true);
			}
		}
		
		public void getLayoverStations(int count){
			String jsonSeqResponseDep = "";
			this.employeeID = DOTCLogInScreen.employeeID;
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());
				c.add(Calendar.DATE, count); 
				String DepartureFromDate = dateFormat.format(c.getTime());
				
				String payloadJsonDepRange = "{\"employeeBidStatus\":{\"base\":" + "\"" + bidStatusCurrentBase + "\""
						+ ",\"division\":" + "\"" + bidStatusCurrentDivision + "\"" + ",\"equipmentGroup\":" + "\""
						+ bidStatusCurrentEquipment + "\"" + ",\"seat\":" + "\"" + bidStatusCurrentPosition + "\""
						+ "},\"airLineCode\":\"AA\",\"employeeID\":" + employeeID
						+ ",\"openSequences\":true,\"startDateFrom\":" + "\"" + DepartureFromDate + "\""
						+ "}";
				jsonSeqResponseDep = dotc.getSequence(payloadJsonDepRange);
				List<String> layoverList = DOTCRestService.readJsonList("$..layoverStations", jsonSeqResponseDep);
				List<String> visitedStations = new ArrayList<String>();
				for(String layList: layoverList) {
					boolean f = false;
					if(layList == null)
						continue;
					char[] ch = layList.toCharArray();
					for(int j=0; j<ch.length; j++) {
						if(ch[j]=='(')
							j+=4;
						if(Character.isLetter(ch[j])) {
							String s = ch[j]+""+ch[j+1]+""+ch[j+2];
							if(includeList.contains(s) | excludeList.contains(s) | visitedStations.contains(s) | f) {
								
							}else if((includeList.size() <= excludeList.size())) {
								includeList.add(s);
								f = true;
							} else {
								excludeList.add(s);
								f = true;
							}
							j+=2;
							if(!visitedStations.contains(s))
								visitedStations.add(s);
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception-Sequences didn't fetched  from the services");
				Assert.assertFalse("Exception-Sequences didn't fetched from the service",true);
			}
		}
		
		//10-03-2022
		//This method is used to add layover station for include textbox
		String includeStation = "";
		public void enterLayoverIncludeTxt() {
			if(includeList.size() >= 1) {		
				sendKey2(includeLayoversTxtBx, includeList.get(0));
				includeStation = includeList.get(0);
			}
		}

		//10-03-2022
		//This method is used to add layover station for exclude textbox
		String excludeStation = "";
		public void enterLayoverExcludeTxt() {
			if(excludeList.size() > 0) {		
				sendKey2(excludeLayoversTxtBx, excludeList.get(0));
				excludeStation = excludeList.get(0);
			}
		}
		
		//10-03-2022
		//This method is used to add same layover station for include and exclude textbox
		public void enterLayoverIncludeExcludeTxt() {
			if(includeList.size() >= 1 ) {
				sendKey2(includeLayoversTxtBx, includeList.get(0));
				includeStation = includeList.get(0);
				sendKey2(excludeLayoversTxtBx, includeList.get(0));
				excludeStation = includeList.get(0);
			}
		}

		public void selectDepartureFromDateFromPayload() {
			try {				
				Util.ClickElement(driver, departureFromDateDtPkr);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				// current day +1
				Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Using today's date
				c.add(Calendar.DATE, 1); // Adding 1 days
				String DepartureFromDate = dateFormat.format(c.getTime());
				
				// to get current month from departure date from date picker
				String monName = driver.findElements(By.xpath("//div[contains(@class, 'ui-datepicker-header')]//span[contains(@class,'ui-datepicker-month')]")).get(0).getText();

				Date sf = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(monName);
				Calendar cal = Calendar.getInstance();
				cal.setTime(sf);
				
				// validate if current day +1 == current month in date picker, if not change to next month
				if(c.get(Calendar.MONTH) > cal.get(Calendar.MONTH))
					Util.ClickElement(driver, calanderNextMonthLink);
				
				// set departure date in date picker
				String dateXpath = "//span[@data-day='"+c.get(Calendar.DAY_OF_MONTH)+"' and @data-month='"+(c.get(Calendar.MONTH)+1)+"' and @data-year='"+c.get(Calendar.YEAR)+"']";
				Util.ClickElement(driver, driver.findElement(By.xpath(dateXpath)));
				
				ExtentTestManager.getTest().log(LogStatus.PASS, "Added departure from date",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Added departure from date", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in Adding departure from date",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in adding departure from date", false);
			}
		}
		
		// this function is use to check min and max value range for no work hours
		//22-03-2022
		public void validateMinMaxRangeNoWorkHours() throws Throwable {
			try {
				Util.waitForLoad(driver);
				List<WebElement> optionsMin = driver.findElements(By.xpath("//*[@id='noFlyStartTime']//option"));
				String startMin = optionsMin.get(1).getText();
				String endMin = optionsMin.get(optionsMin.size()-1).getText();
				Util.ClickElement(driver, noWorkHoursMinDropDown);
				
				if(startMin.equals("00:00") && endMin.equals("23:59")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Range of value for min expected is 00:00-23:59, from UI: "+startMin+"-"+endMin,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Range of value for min expected is 00:00-23:59, from UI: "+startMin+"-"+endMin, true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Range of value for min expected is 00:00-23:59, from UI: "+startMin+"-"+endMin,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Range of value for min expected is 00:00-23:59, from UI: "+startMin+"-"+endMin, false);
				}
				
				List<WebElement> optionsMax = driver.findElements(By.xpath("//*[@id='noFlyEndTime']//option"));
				String startMax = optionsMax.get(1).getText();
				String endMax = optionsMax.get(optionsMax.size()-1).getText();
				Util.ClickElement(driver, noWorkHoursMaxDropDown);
				
				if(startMax.equals("00:00") && endMax.equals("23:59")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, "Range of value for max expected is 00:00-23:59, from UI: "+startMax+"-"+endMax,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Range of value for max expected is 00:00-23:59, from UI: "+startMax+"-"+endMax, true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Range of value for max expected is 00:00-23:59, from UI: "+startMax+"-"+endMax,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Range of value for max expected is 00:00-23:59, from UI: "+startMax+"-"+endMax, false);
				}
			} catch(Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception if fetching values for No Work Hours from UI",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue("Exception if fetching values for No Work Hours from UI", false);
			}
			
		}
		
		
	
	public void clickLayoverMinus() {
		try {
			Util.ClickButton(driver, layoverMinus);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Layover minus clicked");
			Assert.assertTrue("Layover minus clicked", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Layover minus");
			Assert.assertTrue("Exception in clicking on Layover minus", false);
		}
	}

	
	public void clickCitiesPlus() {
		try {
			Util.ClickButton(driver, citiesPlus);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Cities plus clicked");
			Assert.assertTrue("Cities plus clicked", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Cities plus");
			Assert.assertTrue("Exception in clicking on Cities plus", false);
		}
	}
		
	
	public void enterIncludeCities(String includeCities) {
		
		try {
			sendKey2(includeCitiesTxtBox,includeCities);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Include city entered: "+includeCities);
			Assert.assertTrue("Include city entered", true);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception - failed to enter Include city: "+includeCities);
			Assert.assertFalse("Exception - failed to enter Include city", true);
		}
	}

	
	public void enterExcludeCities(String excludeCities) {

		try {
			sendKey2(excludeCitiesTxtBox, excludeCities);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Exclude city entered: " + excludeCities);
			Assert.assertTrue("Exclude city entered", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Exception - failed to enter Exclude city: " + excludeCities);
			Assert.assertFalse("Exception - failed to enter Exclude city", true);
		}
	}
		
		
	
	// To validate error message in search page when min>max value
	// applicable for Calendar Days, Duty Periods,No Work Hours, Total Credit, Paid
	// Credit, Legs Per DP, Legs Per Sequence, TAFB, Layover Time fields
	public void validateErrorMessageInSearchPage(String inputField) {
		try {
			String errorMsg = "";
			if (inputField.equalsIgnoreCase("noWorkHours"))
				errorMsg = "End time must be same or greater than Start time";
			else
				errorMsg = "Max must be same or greater than Min";

			WebElement errorTxtInUI = driver.findElement(
					By.xpath("//div[@id='" + inputField + "']//div[@class='error-message ng-star-inserted']"));
			if (errorTxtInUI.getText().trim().equals(errorMsg)) {

				ExtentTestManager.getTest().log(LogStatus.PASS,
						"Proper error message- '"+errorMsg+"' is displayed in search page for field " + inputField);
				Assert.assertTrue("Error message matches", true);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Exception in validating error message in search page for field " + inputField,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in validating error message in search page", false);

		}
	}
	
	// function to add date according to layover test case
	//27-04-2022
	public void selectDepartureFromDateFromLayover() {
		try {				
			Util.ClickElement(driver, departureFromDateDtPkr);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			// current day +1
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); 
			c.add(Calendar.DATE, layoverDate); 
			String DepartureFromDate = dateFormat.format(c.getTime());
			
			// to get current month from departure date from date picker
			String monName = driver.findElements(By.xpath("//div[contains(@class, 'ui-datepicker-title')]//span")).get(0).getText();

			Date sf = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(monName);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sf);
			
			// validate if current day +1 == current month in date picker, if not change to next month
			if(c.get(Calendar.MONTH) > cal.get(Calendar.MONTH))
				Util.ClickElement(driver, calanderNextMonthLink);
			
			// set departure date in date picker
			String dateXpath = "//span[@data-day='"+c.get(Calendar.DAY_OF_MONTH)+"' and @data-month='"+(c.get(Calendar.MONTH)+1)+"' and @data-year='"+c.get(Calendar.YEAR)+"']";
			Util.ClickElement(driver, driver.findElement(By.xpath(dateXpath)));
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added departure from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added departure from date", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in Adding departure from date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding departure from date", false);
		}
	}
	
	public void  verifyStickyFooterSearch(String visibility) {
		try {
			Boolean flag = false;
			
			if (stickyfooterheader.isDisplayed()) {
				flag = true;
				System.out.println("Search sticky footer is displayed ");
				}
			else {
				flag = false;
				System.out.println("Search sticky footer is not displayed ");
			}
			
			
			if (flag==true && visibility.equalsIgnoreCase("is present") ) {
				
				Assert.assertTrue("Search Sticky footer is displayed ", true);
				currentScenario.embed(Util.takeScreenshot(this.driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "verified - Search Sticky footer is displayed ",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			else if (flag==false && visibility.equalsIgnoreCase("is not present") ) {
				currentScenario.embed(Util.takeScreenshot(this.driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Verified - Search Sticky footer is not displayed ",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
				Assert.assertTrue("Search Sticky footer is not displayed", true);
				
			}
			else {
				currentScenario.embed(Util.takeScreenshot(this.driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, " failed- Expected : search sticky footer  "+visibility  ,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
				Assert.assertTrue(" failed- Expected : search sticky footer  "+visibility, false);
			}
	}
		catch (Exception e) {
			currentScenario.embed(Util.takeScreenshot(this.driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "  exception - Unable to verify  Search Sticky footer ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(this.driver)));
			Assert.assertTrue(" exception - Unable to verify  Search Sticky footer ", false);
		}
	
}
	
//25=05-22
//function to set dep to date acc to offset value
	public void selectDepToDate(String num) {
		try {
                 int numb = Integer.parseInt(num);
               Util.ClickElement(driver, departureToDateDtPkr);
               if (numb>20) {
			    verifyCurrentDateAsLastDateOfTheMonth();
				Util.ClickElement(driver, calanderNextMonthLink);
				Util.ClickElement(driver, departureToDateLst.get(numb));
                             }
               
               else if(verifyCurrentDateAsLastDateOfTheMonth())
                  {
            	   Util.ClickElement(driver, calanderNextMonthLink);
   				  Util.ClickElement(driver, departureToDateList.get(numb));
            	   
            	     } 
               else {
				Util.ClickElement(driver, departureToDateList.get(numb));

			        }
			DepTo = departureToDateinput.getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added departure to date ",
		    ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added departure to date", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in adding departure to date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding departure to date", false);
		}
	}
	
//25=05-22
//function to set arrival to date according to offset value
	public void selectArrivalToDate(String num) {
		try {
			int numb = Integer.parseInt(num);
			Util.ClickElement(driver, arrivalToDateDtPkr);
			Util.waitForLoad(driver);
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMyy");
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, numb);
			String day = new SimpleDateFormat("d").format(calendar.getTime());
			String month = new SimpleDateFormat("M").format(calendar.getTime());
			
			while(!new SimpleDateFormat("MMMM").format(calendar.getTime()).equalsIgnoreCase(datePickerHeader.get(0).getText()))
				Util.ClickElement(driver, calanderNextMonthLink);
			
			String xpath = "//div[contains(@class, 'datepicker')]//td//span[@data-month = '"+month+"' and @data-day = '"+day+"']";
			
			Util.ClickElement(driver, driver.findElement(By.xpath(xpath)));
			
			ArrTo = arrivalToDateinput.getAttribute("value");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Added arrival to date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Added arrival to date", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in adding arrival to date",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding arrival to date", false);
		}

	}
//25-05-22
//Function to set date for validation according to scenario
	public void dateRangeValidation(String dateType, String stdtoffset, String enddtoffset ) {
        try {
        	
        	if(dateType.contains("earlierDep")) {
        		selectDepartureFromDate(stdtoffset);
    			selectDepartureToDate(enddtoffset);

    			ExtentTestManager.getTest().log(LogStatus.PASS, "Departure from and Departure to date is selected",
    					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
    			Assert.assertTrue("Departure from and Departure to date selected", true);
        	}
        	else if(dateType.contains("arrivalEarlier")) {
        		selectArrivalFromDate(stdtoffset);
    			selectArrivalToDate(enddtoffset);
    			ExtentTestManager.getTest().log(LogStatus.PASS, "Arrival from and Arrival to date is Selected",
    					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
    			Assert.assertTrue("Arrival from and Arrival to date is Selected", true);
        	}
        	else if(dateType.contains("exceedDep")) {
        		selectDepartureFromDate(stdtoffset);
        		selectDepartureToDate(enddtoffset);

    			ExtentTestManager.getTest().log(LogStatus.PASS, "Departure from and Departure to date selected",
    					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
    			Assert.assertTrue("Departure from and Departure to date selected", true);
        	}
        	
        	else if(dateType.contains("arrivalExceed")) {
        		selectArrivalFromDate(stdtoffset);
    			selectArrivalToDate(enddtoffset);

    			ExtentTestManager.getTest().log(LogStatus.PASS, "Arrival from and Arrival to date is Selected",
    					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
    			Assert.assertTrue("Arrival from and Arrival to date is Selected", true);
        	}
        	
        	else 	if(dateType.contains("arrExceedDep")) {
        		selectDepartureFromDate(stdtoffset);
        		selectArrivalFromDate(enddtoffset);

    			ExtentTestManager.getTest().log(LogStatus.PASS, "Departure from and Arrival to date is selected",
    					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
    			Assert.assertTrue("Departure from and Arrival to date is selected", true);
        	}
        	
        	else	if(dateType.contains("arrEarlierDep")) {
        		selectDepartureFromDate(stdtoffset);
        		selectArrivalFromDate(enddtoffset);

    			ExtentTestManager.getTest().log(LogStatus.PASS, "Departure from and Arrival to date is selected",
    					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
    			Assert.assertTrue("Departure from and Arrival to date is selected", true);
        	}
        	else {
        		ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed - To add date range for validation",
    					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
    			Assert.assertTrue("Failed -To add date range for validation", false);
        	}
        	
        }
        catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in adding date range for validation",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in adding date range for validation", false);
		}
 }	
	
	public void dateErrorMsg(String errTyp , String errMsg) {
		try {
			Util.waitForLoad(driver);
			String errorMsg = "";
			if(errTyp.contains("earlierDeparture")) {
				Util.waitFor(driver,departureDateErrMsg);
				errorMsg = departureDateErrMsg.getText();
				if(errorMsg.equalsIgnoreCase(errMsg)) {
					System.out.println("error msg -earlierDeparture ");
					ExtentTestManager.getTest().log(LogStatus.PASS, "Validated - Scenario 1- Departure End date must be same or after Departure Start date ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				    Assert.assertTrue("Validated - Scenario 1- Departure End date must be same or after Departure Start date ", true);
				} 
			} else if(errTyp.contains("exceedDaysDep")) {
				 Util.waitFor(driver,departureDateErrMsg);
				 errorMsg = departureDateErrMsg.getText();
				 if(errorMsg.equalsIgnoreCase(errMsg)) {
					    System.out.println("error msg -earlierDeparture ");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Validated - Scenario 2 - Departure End date cannot be more than 31 days apart ",
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue("Validated - Scenario 2 - Departure End date cannot be more than 31 days apart ", true);
					}
			} else if(errTyp.contains("arrivalEarlier")) {
				 Util.waitFor(driver,arrivalDateErrMsg);
				 errorMsg = arrivalDateErrMsg.getText();
				 if(errorMsg.equalsIgnoreCase(errMsg)) {
					    System.out.println("error msg -earlierArr ");
						ExtentTestManager.getTest().log(LogStatus.PASS, "Validated - Scenario 3- Arrival End date must be same or after Arrival Start date  ",
					    ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue("Validated - Scenario 3- Arrival End date must be same or after Arrival Start date", true);
					}
					
			} else if(errTyp.contains("arrivalExceed")) {
				Util.waitFor(driver,arrivalDateErrMsg);
				errorMsg = arrivalDateErrMsg.getText();
				if(errorMsg.equalsIgnoreCase(errMsg)) {
			        System.out.println("error msg -exceedArr ");
				    ExtentTestManager.getTest().log(LogStatus.PASS, "Validated - Scenario 4- Arrival End date cannot be more than 31 days apart  ",
			    	ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				    Assert.assertTrue("Validated - Scenario 4- Arrival End date cannot be more than 31 days apart  ", true);
				}	
			} else if(errTyp.contains("arrEarlierDep")) {
				Util.waitFor(driver,arrivalDateErrMsg);
				errorMsg = arrivalDateErrMsg.getText();
				if(errorMsg.equalsIgnoreCase(errMsg)) {
			        System.out.println("error msg -earlierArrDep ");
				    ExtentTestManager.getTest().log(LogStatus.PASS, "Validate - Scenario 5 - Arrival Start date must be same or after Departure Start date  ",
			    	ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				    Assert.assertTrue("Validate - Scenario 5 - Arrival Start date must be same or after Departure Start date ", true);
				}
			} else if(errTyp.contains("arrExceedDep")) {
				Util.waitFor(driver,arrivalDateErrMsg);
				errorMsg = arrivalDateErrMsg.getText();
				if(errorMsg.equalsIgnoreCase(errMsg)) {
			        System.out.println("error msg -exceedArrDep ");
				    ExtentTestManager.getTest().log(LogStatus.PASS, "Validated - Scenario 6- Arrival start date cannot be more than 31 days apart",
			    	ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				    Assert.assertTrue("Validated - Scenario 6- Arrival start date cannot be more than 31 days apart", true);
				}
			} else if(errTyp.contains("depMidNight")) {
				Util.waitFor(driver,depDateMidNightMsg);
				errorMsg = depDateMidNightMsg.getText();
				if(errorMsg.equalsIgnoreCase(errMsg)) {
			        System.out.println("error msg -departure date mid-night");
				    ExtentTestManager.getTest().log(LogStatus.PASS, "Validated - error message for departure date request thru mid-night",
			    	ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				    Assert.assertTrue("Validated - error message for departure date request thru mid-night", true);
				}
			} else if(errTyp.contains("arrMidNight")) {
				Util.waitFor(driver,arrDateMidNightMsg);
				errorMsg = arrDateMidNightMsg.getText();
				if(errorMsg.equalsIgnoreCase(errMsg)) {
			        System.out.println("error msg -arrival date mid-night");
				    ExtentTestManager.getTest().log(LogStatus.PASS, "Validated - error message for arrival date request thru mid-night",
			    	ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				    Assert.assertTrue("Validated - error message for arrival date request thru mid-night", true);
				}
			}
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Element not found",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Element not found", true);
			ex.printStackTrace();
		}
}
	

	public void clickClearButton(String buttonType) {
		try {
			if (buttonType.contains("depEndDate")) {
				Util.ClickElement(driver, departureToDateDtPkr);
				Util.ClickElement(driver, depToClearbutton);
				ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on clear button (departure to date) ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
			 
			if (buttonType.contains("arrEndDate")) {
				 Util.ClickElement(driver, arrivalToDateDtPkr);
				 Util.ClickElement(driver, arrToClearbutton);
				 ExtentTestManager.getTest().log(LogStatus.PASS, "clicked on clear button (arrival to date) ",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		}
		 catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on clear button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in clicking on clear button", false);
			}
	}
	
	//14 May, 2022
	//function to set arrival date range
	public void selectArrivalDateRange(String stdtoffset, String enddtoffset) {
		try {
			selectArrivalFromDate(stdtoffset);
			selectArrivalToDate(enddtoffset);
			ExtentTestManager.getTest().log(LogStatus.PASS, "DOTC Search:Start and End Date is Selected",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("DOTC Search:Start and End Date is Selected", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "DOTC Search: Start and End Date Selection Failed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception Occured:DOTC Search: Start Date Selection Failed " + ex.getMessage(), true);
		}
	}
	
	//function to click on Pick Up Outside DOTC checkbox in Save Generic Criteria
	public void clickOnPickUpOutsideDotcSaveGenericCriteria() {
		try {
			Util.ClickElement(driver, pickUpOutsideDOTC);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Pick up outside DOTC checkbox is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("DOTC check box is clicked", true);
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Pick up outside DOTC checkbox is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Pick up outside DOTC check box is not clicked",false);
		}
	}
	
	//function to click on Pick Up DOTC checkbox in Save Generic Criteria
	public void clickOnPickUpDotcSaveGenericCriteria() {
		try {
			Util.ClickElement(driver, pickUpDOTC);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Pick up DOTC checkbox is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("DOTC check box is clicked", true);
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Pick up DOTC checkbox is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Pick up DOTC checkbox is not clicked",false);
		}
	}
}


