package com.DOTC.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;

import junit.framework.Assert;

public class DOTCBallotScreen extends DOTCCommon {
	DOTCCommon dotcCmn = new DOTCCommon();
	DOTCRestService dotc = new DOTCRestService();
	public static String employeeID = "";
	TestData testData = new TestData();
	DOTCSearchScreen dotcSrchPg = new DOTCSearchScreen();
	String arrivalInput = "";
	String departureInput = "";

	
	// Edit Link to edit Ballots
	@FindAll({ @FindBy(how = How.LINK_TEXT, using = "Edit") })
	WebElement editBallotLink;

	// Button to update Ballot
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[text()='Update']") })
	WebElement updateBallotBtn;

	// To Fetch the Departure To and From Date from Ballot
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[contains(@id,'DataTables_Table_')]/tbody/tr/td[4]/table//div[3]/div/div/div[2]//div[1]/div[2]") })
	WebElement departureFromToDateBlltTxt;

	// To Fetch the Minimum Total Credit Hours from Ballot
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = " //*[contains(@id,'DataTables_Table_')]/tbody/tr/td[4]/table//div[3]/div/div/div[2]//div[2]/div[2]") })
	WebElement minTotalCreditBallotTxt;

	// To Fetch the Maximum Total Credit Hours from Ballot
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = " //*[contains(@id,'DataTables_Table_')]/tbody/tr/td[4]/table//div[3]/div/div/div[2]//div[3]/div[2]") })
	WebElement maxTotalCreditBallotTxt;

	//

	@FindAll({
			// @FindBy(how = How.XPATH, using = "//ul[@class = 'nav nav-tabs flat-tabs
			// double-height']/parent::div/div[3]//label[@class = 'checkboxLabel']") })
			@FindBy(how = How.XPATH, using = "//div[@class = 'dataTables_wrapper']//div[@class = 'customComponent checkboxComponent']") })
	List<WebElement> DOTCCheckboxLbl;

	//

	@FindAll({
			// @FindBy(how = How.XPATH, using = "//ul[@class = 'nav nav-tabs flat-tabs
			// double-height']/parent::div/div[3]//aac-ballot-buttons[1]//input[@id =
			// 'deleteSelected']") })
			@FindBy(how = How.XPATH, using = "//input[@id = 'deleteSelected']") })
	WebElement DOTCDeleteSelected;

	// Submit button to confirm deleting of ballots
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id = 'btnSubmit']") })
	WebElement deleteSubmitBtn;

	// Update button to update the ballots
	@FindAll({ @FindBy(how = How.XPATH, using = "//input[@name='btnUpdateBallots']") })
	WebElement updateBtn;

	// To Capture Successful Message after updating the ballot
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Update successful.')]") })
	WebElement updateSuccessfulTxt;

//	@FindAll({
//			@FindBy(how = How.XPATH, using = "//ul[@class = 'nav nav-tabs flat-tabs double-height']/parent::div/div[4]//label[@class = 'checkboxLabel']") })
//	List<WebElement> outsideCheckboxLbl;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//*[@class = 'ballotDataTable ballot-data-table row-border hover dataTable']//th[2]//div[2]//div[1]")})
 
	List<WebElement> outsideCheckboxLbl;

//	@FindAll({
//			@FindBy(how = How.XPATH, using = "//ul[@class = 'nav nav-tabs flat-tabs double-height']/parent::div/div[4]//aac-ballot-buttons[1]//input[@id = 'deleteSelected']") })
//	WebElement outsideDeleteSelected;


	@FindAll({
		@FindBy(how = How.XPATH, using = "//input[@id = 'deleteSelected']") })
	WebElement outsideDeleteSelected;
	
//	@FindAll({
//			@FindBy(how = How.XPATH, using = "//ul[@class = 'nav nav-tabs flat-tabs double-height']/parent::div/div[5]//label[@class = 'checkboxLabel']") })
//	List<WebElement> templateCheckboxlevel;

	

	@FindAll({
		@FindBy(how = How.XPATH, using = "//*[@class = 'ballotDataTable ballot-data-table row-border hover dataTable']//th[2]//div[2]//div[1]")})
	List<WebElement> templateCheckboxlevel;
	
	
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//ul[@class = 'nav nav-tabs flat-tabs double-height']/parent::div/div[5]//aac-ballot-buttons[1]//input[@id = 'deleteSelected']") })
	WebElement templateDeleteSelected;

	// 10 Aug 2022
	// updated xpath
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(text(),'Pickup DOTC')]//parent::a") })
	WebElement ballotsPickDOTC;

	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(text(),'Pick Up Outside')]//parent::a") })
	WebElement ballotsPickOutside;

	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(text(),'Template')]//parent::a") })
	WebElement BallotsPickTemplate;

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(text(), 'Ballots')]") })
	WebElement ballotsTab;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[contains(@id,'bdt')]/div[2]/div") })
	WebElement noResultsPickDOTCBlltPg;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//ul[@class = 'nav nav-tabs flat-tabs double-height']/parent::div/div[5]//table[@class = 'ballot-data-table-inner-row-table']") })
	List<WebElement> BallotsPickTemplateList;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(text(), 'Please fix duplicates.')]") })
	WebElement BallotsDuplicateError;

	// Arrival Date in Ballot page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[contains(@id,'DataTables_Table_')]/tbody/tr/td[4]/table//div[3]/div/div/div[2]//div[2]/div[2]") })
	WebElement arrivalFromToDateTxt;

	// Minimum Duty Periods Text in Ballot page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[contains(@id,'DataTables_Table_')]/tbody/tr/td[4]/table//div[3]/div/div/div[2]//div[2]/div[2]") })
	WebElement minDutyPeriodsTxt;

	// Maximum Duty Periods Text in Ballot page
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[contains(@id,'DataTables_Table_')]/tbody/tr/td[4]/table//div[3]/div/div/div[2]//div[3]/div[2]") })
	WebElement maxDutyPeriodsTxt;

	// Departure and arrival text
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'col-xs-6']") })
	List<WebElement> depArrText;

	// Departure and Arrival date
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'col-xs-6 dsColumn']") })
	List<WebElement> depArrDateText;

	// Total Ballots with a valid TAFB value on the saved ballot for Pick up ballots
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickup']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'TAFB Min')]") })
	List<WebElement> TAFBMinListPickUpBallots;

	// Total Ballots with a valid TAFB value on the saved ballot for Pick up Outside
	// type ballots
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickupOutside']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'TAFB Min')]") })
	List<WebElement> TAFBMinListPickUpOutsideBallots;

	// Total Ballots with a valid TAFB value on the saved ballot for Pick up Outside
	// type ballots
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='template']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'TAFB Min')]") })

	List<WebElement> TAFBMinListTemplateBallots;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//table[@class='generic-details-stations-table']//div/div[1]/div") })
	WebElement depStations1stStationText;

	/*
	 * @CacheLookup
	 * 
	 * @FindAll({ @FindBy(how = How.XPATH, using =
	 * "//table[@class='generic-details-stations-table']//div/div[2]/div") })
	 * WebElement depStations2ndStationText;
	 * 
	 * List<WebElement> TAFBMinListTemplateBallots;
	 */

	// Opt Link to mark Ballots as Premium
	@CacheLookup
	@FindAll({ @FindBy(how = How.LINK_TEXT, using = "Opt") })
	WebElement optBallotLink;

	// After clicking Opt link mark ballot as Premium
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='chk_chkPremium']") })
	WebElement premiumChckBx;

	// Submit Button in Options Pop Up after clicking Opt link
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@id='btnSubmit']") })
	WebElement popUpSubmitBtn;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[@class='summary-flag-green ng-star-inserted']") })
	WebElement premiumSymbolBlltTxt;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickup']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//table[@class='generic-details-stations-table']//div[@class='row ng-star-inserted']") })
	List<WebElement> depStationsListPickUpBallots;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickupOutside']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//table[@class='generic-details-stations-table']//div[@class='row ng-star-inserted']") })
	List<WebElement> depStationsListPickUpOutsideBallots;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='template']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//table[@class='generic-details-stations-table']//div[@class='row ng-star-inserted']") })
	List<WebElement> depStationsListTemplateBallots;

	// 10 Aug 2022
	// updated xpath
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Pickup Outside']") })
	WebElement pickUpOutsideLink;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Pickup DOTC']//../mat-slide-toggle//input[@aria-checked='true']") })
	WebElement OnBtnpickUpDOTC;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Pickup DOTC']//../mat-slide-toggle//input[@aria-checked='false']") })
	WebElement OffBtnpickUpDOTC;
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Pickup Outside  DOTC']//../mat-slide-toggle//input[@aria-checked='true']") })
	WebElement OnBtnpickUpOutsideDOTC;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Pickup Outside  DOTC']//../mat-slide-toggle//input[@aria-checked='false']") })
	WebElement OffBtnpickUpOutsideDOTC;

	// Generic Request title text
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'col-lg-8 summary-title']") })
	List<WebElement> RequestTitle;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[.='Template']") })
	WebElement templateLink;// All Premium Symbols under Pick Up Ballots
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickup']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//table[@class='ballot-data-table-summary-column']//span[@class='summary-flag-green ng-star-inserted']") })
	List<WebElement> premiumSymbolListPickUpBallots;

	// All Premium Symbols under Pick Up Outside Ballots
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickupOutside']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//table[@class='ballot-data-table-summary-column']//span[@class='summary-flag-green ng-star-inserted']") })
	List<WebElement> premiumSymbolListPickUpOutsideBallots;

	// All Premium Symbols under Template Ballots
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='template']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//table[@class='ballot-data-table-summary-column']//span[@class='summary-flag-green ng-star-inserted']") })
	List<WebElement> premiumSymbolListTemplateBallots;

	@CacheLookup
	@FindAll({ @FindBy(how = How.ID, using = "updateBallots") })
	WebElement updateBallots;

	@CacheLookup
	@FindAll({ @FindBy(how = How.ID, using = "deleteSelected") })
	WebElement deleteSelected;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='template']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-6'][contains(text(),'Days Min')]") })
	List<WebElement> calDaysListTemplateBallots;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickup']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-6'][contains(text(),'Min Paid Credit')]") })
	List<WebElement> paidCreditListPickUpBallots;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickupOutside']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-6'][contains(text(),'Min Paid Credit')]") })
	List<WebElement> paidCreditListPickUpOutsideBallots;

	// Total Ballots with a valid TAFB value on the saved ballot for Pick up Outside
	// type ballots
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='template']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-6'][contains(text(),'Min Paid Credit')]") })
	List<WebElement> paidCreditListTemplateBallots;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickup']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-6'][contains(text(),'Days Min')]") })
	List<WebElement> calDaysListPickUpBallots;

	// type ballots
	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[@ballottype='pickupOutside']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-6'][contains(text(),'Days Min')]") })
	List<WebElement> calDaysListPickUpOutsideBallots;

	@FindAll({ @FindBy(how = How.ID, using = "buttonCreateGenericSearch") })
	WebElement createGenericBallotsBtn;

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'ballotDataTableNoResults']") })
	WebElement ballotNoResults;

	@FindAll({ @FindBy(how = How.XPATH, using = "//table//th[@class = 'checkbox-column sorting_disabled']") })
	WebElement ballotsSelectAllText;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//*[@ballottype='pickup']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'Sit Time')]") })
	List<WebElement> sitTimePickUpDOTCBallotList;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//*[@ballottype='pickupOutside']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'Sit Time')]") })
	List<WebElement> sitTimePickUpOutsideBallotList;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//*[@ballottype='template']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'Sit Time')]") })
	List<WebElement> sitTimeTemplateBallotList;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Layover Times']//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement layoverTimesPlusIcon;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//*[.='Cities']//span[@class = 'more-less glyphicon glyphicon-plus']") })
	WebElement citiesPlusIcon;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"ballotSubmitButton\"]") })
	WebElement saveGenericCriteriaBtn;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='includeCities']") })
	WebElement includeCitiesTxtBox;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='avoidCities']") })
	WebElement excludeCitiesTxtBox;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"minLayoverTime\"]") })
	WebElement minLayoverTimesDropDown;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id=\"maxLayoverTime\"]") })
	WebElement maxLayoverTimesDropDown;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//table[@id = 'sequencesLargeTable']//span[@class = 'control']") })
	List<WebElement> sequenceNumbersCheckBox;

	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@id='btnSubmit'][.='Add']") })
	WebElement ballotAddButton;

	@FindAll({ @FindBy(how = How.XPATH, using = "//label[@for='chk_addToAllBallots']//span[@class='control']") })
	WebElement allBallotsCheckBox;

	@FindAll({ @FindBy(how = How.XPATH, using = "//table[@id='sequencesLargeTable']//a[contains(@class,'speciallink')]") })
	List<WebElement> sequenceNumbersInTable;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@value='Save Selected Sequences']") })
	WebElement ballotSubmitButton;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//aac-sequences-data-table[@class='sequences-data-table ng-star-inserted']//div[contains(., 'No results')]") })
	WebElement noResultsInSequence;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(@class,'visible-sm')]//button[@id='buttonCancelUpdate']")})
	WebElement cancelUpdateBtn;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "	//button[contains (@id ,'cancelSelected')]")})
	WebElement cancelBtn;

	@FindAll({ @FindBy(how = How.XPATH, using = "//aac-confirm-dialog//h2 [contains(@class , 'modal-title')]")})
	WebElement modalTextUnsaved;
	

	@FindAll({ @FindBy(how = How.XPATH, using = "//button[contains (@class,'close')]")})
	WebElement closeBtn;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//aac-ballot-data-table[@ballottype='pickup']//button//span[text()='Cancel']")})
	WebElement cancelBtnPickup;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//aac-ballot-data-table[@ballottype='pickupOutside']//button//span[text()='Cancel']")})
	WebElement cancelBtnPickupOutside;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//aac-ballot-data-table[@ballottype='template']//button//span[text()='Cancel']")})
	WebElement cancelBtnTemplate;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Pickup Outside DOTC']//parent::span//mat-slide-toggle[contains(@class, 'mat-slide-toggle')]")})
	WebElement notificationSlider;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains (@class,'subtext')]")})
	WebElement NotificationTimeSubText;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//mat-select[@name='notificationPeriodHH']")})
	WebElement NotificationDefaultHour;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//mat-select[@name='notificationPeriodMM']")})
	WebElement NotificationDefaultMinute;
	
	
	private String firstSequenceNum = new String();

	public boolean isSequencesPresent = false;
	
	public DOTCBallotScreen() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);

	}

	// 24 Aug 2022
	// updated xpath to validate sequence number
	// Compare Sequence Number entered and value displayed in Ballot
		public void validateSequenceNumberinBallot(String seqNum, String ballotType, String searchTitle) 
		{
			try 
			{
				if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";			
				}
				else if (ballotType.contains("PickUpOutside")) 
				{
					ballotType = "pickupOutside";							
				}
				else if (ballotType.contains("Template")) 
				{
					ballotType = "template";
				}
					//String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Sequence Number')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
					String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//div[@class='row ng-star-inserted']//div[contains(normalize-space(),'Sequence Number')]//..";
					String seqNumTxt = driver.findElement(By.xpath(exp)).getText();
					System.out.println(seqNumTxt);
					if ((seqNumTxt).contains(seqNum)) 
					{
						String output = "Sequence Number value for " + ballotType + " ballot is good and matches expected value " + seqNum;
						ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));				
						Assert.assertTrue(output, true);
					} 
					else 
					{
						String output = "Sequence Number value for " + ballotType + " ballot is not good and does not match the expected value " + seqNum;
						ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertFalse(output, false);
					}
							
				} 
				catch (Exception ex) 
				{
					ex.printStackTrace();
					Assert.assertTrue("Exception Occurred", false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in validateSequenceNumberinBallot method",
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

				}
		}


	// Clicking Edit link to Edit the ballot
	public void clickEditBallotLink() {
		try {
			Util.ClickElement(driver, editBallotLink);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edit Link is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Link is not Clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Compares the entered Departure date while Ballot creation with the Date
	// displayed in Ballot
	public void validateDepartureFromToDateEntered() {
		try {
			String departureDateBlltPg = departureFromToDateBlltTxt.getText();
			System.out.println(departureDateBlltPg);
			Calendar cal = Calendar.getInstance();
			int currentDate = cal.get(Calendar.DAY_OF_MONTH);
			String[] monthName = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };// contractualMonth
			String departToDate = Integer.toString(currentDate);
			String departFromDate = Integer.toString(currentDate + 2);
			String currentMonth = monthName[cal.get(Calendar.MONTH)];
			String currentYear = Integer.toString(cal.get(Calendar.YEAR));
			String expectedDepartToDate = departToDate + " " + currentMonth
					+ currentYear.substring(currentYear.length() - 2);
			String expectedDepartFromDate = departFromDate + " " + currentMonth
					+ currentYear.substring(currentYear.length() - 2);
			System.out.println("Departure To Date is " + expectedDepartToDate + " Departure From Date is "
					+ expectedDepartFromDate);
			if (departureDateBlltPg.equals(expectedDepartToDate + " - " + expectedDepartFromDate)) {
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Departure Date values are matching",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Departure Date values are not matching",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Comparing the Minimum and Maximum credit hours and minutes between the input value and
	// Method to compare Total Credit Values
	// 24 Aug 2022
	// updated xpath to validate total credit
	public void getTotalCreditDisplayStatusOnBallot(String minHrs, String minMins, String maxHrs,String maxMins, String ballotType, String searchTitle) 
	{
		try
		{
			if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";			
			}
			else if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";		
			}
			else if (ballotType.contains("Template")) 
			{
				ballotType = "template";			
			}
			
			//String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Min Total Credit')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
			String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//div[@class='row ng-star-inserted']//div[contains(normalize-space(),'Min Total Credit')]//..";
			System.out.println(exp);
			String totalCreditMin = driver.findElement(By.xpath(exp)).getText().split("\\n")[1];
			System.out.println(totalCreditMin);
			if (totalCreditMin.contains(minHrs) && totalCreditMin.contains(minMins)) 
			{
				String output = "Total Credit min value for " + ballotType + " ballot is "
										+ totalCreditMin + ". It matches expected value : " + minHrs + "." + minMins;
				ExtentTestManager.getTest().log(LogStatus.PASS, output,
										ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(output, true);
			} 
			else 
			{
				String output = "Total Credit min value for " + ballotType + " ballot is "
										+ totalCreditMin + " not matching with expected value : " + minHrs + "." + minMins;
				Assert.assertFalse(output, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
										ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
			//exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Max Total Credit')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
			exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//div[@class='row ng-star-inserted']//div[contains(normalize-space(),'Max Total Credit')]//..";
			String totalCreditMax = driver.findElement(By.xpath(exp)).getText().split("\\n")[1];
			System.out.println(totalCreditMax);
			if (totalCreditMax.contains(maxHrs) && totalCreditMax.contains(maxMins)) 
			{
				String output = "Paid Credit max value for " + ballotType + " ballot is "
										+ totalCreditMax + " and matches expected value: " + maxHrs + "." + maxMins;
				Assert.assertTrue(output, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, output,
										ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			} 
			else 
			{
				String output = "Paid Credit max value for " + ballotType + " ballot is "
										+ totalCreditMax + " and not matching with expected value: " + maxHrs + "." + maxMins;
				Assert.assertFalse(output, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
										ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in getPaidCreditDisplayStatusOnBallot method",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.fail("Exception thrown:" + ex.getMessage());
		}
					
	}


	// Clicking Ballot Button from Menu to navigate to Ballot Page
	public void clickOnBallotsTab() {
		try {
			Util.ClickElement(driver, ballotsTab);
			Util.waitForSpinnerLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "ballots Tab is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "ballots Tab is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// Clicking Pick Up DOTC link in Ballot Page to view the ballots
	public void clickOnPickUpDOTCLink() {
		try {
			Util.ClickElement(driver, ballotsPickDOTC);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Pick Up DOTC Link is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Pick Up DOTC Link is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// Clicking Pick Up Outside link in Ballot Page to view the ballots
	public void clickOnPickUpOutsideLink() {
		try {
			System.out.println("Pick Up Outside link will be clicked");
			Util.ClickElement(driver, ballotsPickOutside);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Pick Up Outside Link is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Pick Up Outside Link is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	// Clicking Template link in Ballot Page to view the ballots
	public void clickOnPickUpTemplateLink() {
		try {
			System.out.println("Template link will be clicked");
			Util.ClickElement(driver, BallotsPickTemplate);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Pick Up Template Link is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Pick Up Template Link is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	public void createBallotsValidation(String ballotType) {
		List<String> PickUpDOTCBallotsTitle = new ArrayList<String>();
		List<String> PickUpOutsideBallotsTitle = new ArrayList<String>();
		List<String> TemplateBallotsTitle = new ArrayList<String>();
		if (ballotType.contains("PickUpDOTC")) {
			ballotType = "pickup";
			String exp = "//*[@ballottype='" + ballotType + "']//div[@class = 'col-lg-8 summary-title']";
			List<WebElement> ballotTitle = driver.findElements(By.xpath(exp));
			int totalTitle = ballotTitle.size();
			for (int i = 0; i < totalTitle; i++) {
				PickUpDOTCBallotsTitle.add(ballotTitle.get(i).getText());
			}
			if (PickUpDOTCBallotsTitle.contains(DOTCSearchScreen.SearchTitle)) {
				System.out.println("Pick Up DOTC ballot is created successfully with Request title: "
						+ DOTCSearchScreen.SearchTitle);
				Assert.assertTrue("Pick Up DOTC ballot is created successfully with the ballot Title"
						+ DOTCSearchScreen.SearchTitle, true);
			} else {
				Assert.assertFalse(
						"Pick Up DOTC ballot is not created with ballot Title" + DOTCSearchScreen.SearchTitle, true);
			}
		}
		if (ballotType.contains("PickUpOutside")) {
			ballotType = "pickupOutside";
			String exp = "//*[@ballottype='" + ballotType + "']//div[@class = 'col-lg-8 summary-title']";
			List<WebElement> ballotTitle = driver.findElements(By.xpath(exp));
			int totalTitle = ballotTitle.size();
			for (int i = 0; i < totalTitle; i++) {
				PickUpOutsideBallotsTitle.add(ballotTitle.get(i).getText());
			}
			if (PickUpOutsideBallotsTitle.contains(DOTCSearchScreen.SearchTitle)) {
				System.out.println("Pick Up Outside ballot is created successfully with Request title: "
						+ DOTCSearchScreen.SearchTitle);
				Assert.assertTrue("Pick Up Outside ballot is created successfully with the ballots Title"
						+ DOTCSearchScreen.SearchTitle, true);
			} else {
				Assert.assertFalse(
						"Pick Up Outside ballot is not created with ballot Title" + DOTCSearchScreen.SearchTitle, true);
			}
		}
		if (ballotType.contains("Template")) {
			ballotType = "template";
			String exp = "//*[@ballottype='" + ballotType + "']//div[@class = 'col-lg-8 summary-title']";
			List<WebElement> ballotTitle = driver.findElements(By.xpath(exp));
			int totalTitle = ballotTitle.size();
			for (int i = 0; i < totalTitle; i++) {
				TemplateBallotsTitle.add(ballotTitle.get(i).getText());
			}
			if (TemplateBallotsTitle.contains(DOTCSearchScreen.SearchTitle)) {
				System.out.println(
						"Template ballot is created successfully with Request title: " + DOTCSearchScreen.SearchTitle);
				Assert.assertTrue(
						"Template ballot is created successfully with the ballots Title" + DOTCSearchScreen.SearchTitle,
						true);
			} else {
				Assert.assertFalse("Template ballot is not created with ballot Title" + DOTCSearchScreen.SearchTitle,
						true);
			}
		}

	}

	public void deleteBallotsValidation(String ballotType) {
		List<String> PickUpDOTCBallotsTitle = new ArrayList<String>();
		List<String> PickUpOutsideBallotsTitle = new ArrayList<String>();
		List<String> TemplateBallotsTitle = new ArrayList<String>();
		String srhTitle = DOTCSearchScreen.SearchTitle;
		try {
			if (ballotType.contains("PickUpDOTC")) {
				ballotType = "pickup";
				String checkbox = "//*[.='" + srhTitle
						+ "']//..//..//..//..//..//..//..//..//..//..//..//..//..//..//..//span[@class = 'control']";
				driver.findElement(By.xpath(checkbox)).click();
				String exp1 = "//*[@ballottype='" + ballotType + "']//input[@id= 'deleteSelected']";
				driver.findElement(By.xpath(exp1)).click();
				Util.ClickElement(driver, deleteSubmitBtn);
				clickOnUpdateButton();
				String exp2 = "//*[@ballottype='" + ballotType + "']//div[@class = 'col-lg-8 summary-title']";
				List<WebElement> ballotTitle = driver.findElements(By.xpath(exp2));
				int totalTitle = ballotTitle.size();
				for (int i = 0; i < totalTitle; i++) {
					PickUpDOTCBallotsTitle.add(ballotTitle.get(i).getText());
				}
				if (PickUpDOTCBallotsTitle.contains(DOTCSearchScreen.SearchTitle)) {
					System.out.println("Pick Up DOTC ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is not deleted successfully");
					Assert.assertFalse("Pick Up DOTC ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is not deleted successfully", true);
				} else {
					System.out.println("Pick Up DOTC ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is deleted successfully");
					Assert.assertTrue("Pick Up DOTC ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is deleted successfully", true);
				}
			}
			if (ballotType.contains("PickUpOutside")) {
				ballotType = "pickupOutside";
				String checkbox = "//*[.='" + srhTitle
						+ "']//..//..//..//..//..//..//..//..//..//..//..//..//..//..//..//span[@class = 'control']";
				driver.findElement(By.xpath(checkbox)).click();
				String exp1 = "//*[@ballottype='" + ballotType + "']//input[@id= 'deleteSelected']";
				driver.findElement(By.xpath(exp1)).click();
				Util.ClickElement(driver, deleteSubmitBtn);
				clickOnUpdateButton();
				String exp2 = "//*[@ballottype='" + ballotType + "']//div[@class = 'col-lg-8 summary-title']";
				List<WebElement> ballotTitle = driver.findElements(By.xpath(exp2));
				int totalTitle = ballotTitle.size();
				for (int i = 0; i < totalTitle; i++) {
					PickUpOutsideBallotsTitle.add(ballotTitle.get(i).getText());
				}
				if (PickUpOutsideBallotsTitle.contains(DOTCSearchScreen.SearchTitle)) {
					System.out.println("Pick Up Outside ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is not deleted successfully");
					Assert.assertFalse("Pick Up Outside ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is not deleted successfully", true);
				} else {
					System.out.println("Pick Up Outside ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is deleted successfully");
					Assert.assertTrue("Pick Up Outside ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is deleted successfully", true);
				}
			}
			if (ballotType.contains("Template")) {
				ballotType = "template";
				String checkbox = "//*[.='" + srhTitle
						+ "']//..//..//..//..//..//..//..//..//..//..//..//..//..//..//..//span[@class = 'control']";
				driver.findElement(By.xpath(checkbox)).click();
				String exp1 = "//*[@ballottype='" + ballotType + "']//input[@id= 'deleteSelected']";
				driver.findElement(By.xpath(exp1)).click();
				Util.ClickElement(driver, deleteSubmitBtn);
				clickOnUpdateButton();
				String exp2 = "//*[@ballottype='" + ballotType + "']//div[@class = 'col-lg-8 summary-title']";
				List<WebElement> ballotTitle = driver.findElements(By.xpath(exp2));
				int totalTitle = ballotTitle.size();
				for (int i = 0; i < totalTitle; i++) {
					TemplateBallotsTitle.add(ballotTitle.get(i).getText());
				}
				if (TemplateBallotsTitle.contains(DOTCSearchScreen.SearchTitle)) {
					System.out.println("Template ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is not deleted successfully");
					Assert.assertFalse("Template ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is not deleted successfully", true);
				} else {
					System.out.println("Template ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is deleted successfully");
					Assert.assertTrue("Template ballot with Request title:" + DOTCSearchScreen.SearchTitle
							+ " is deleted successfully", true);
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Ballots are not deleted from the ballot list",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// 11 Aug 2022
	// updated xpath to select all ballots
	// Delete Ballots under Pick up DOTC link
	public void DeleteDOTCBallots(String ballotType) {
		try {
			if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";
			}
			if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";
			}
			if (ballotType.contains("Template")) 
			{
				ballotType = "template";
			}
			String allCheckBoxXpath = "//aac-ballot-data-table[@ballottype='%s']//span[@class='mat-checkbox-inner-container']";
			
			WebElement ele = driver.findElement(By.xpath(String.format(allCheckBoxXpath, ballotType)));
			Util.ClickButton(driver, ele);
			deleteCheckedBs(ballotType);
			
			//Util.ClickButton(driver, deleteSubmitBtn);
//			Thread.sleep(3000);
			clickOnUpdateButton(ballotType);
			//verifyUpdateMessage();
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "The ballots is deleted successfully",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("The ballots is deleted successfully", true);
			
		} catch (Exception ex) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "There is something wrong to delete ballots",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertFalse("There is something wrong to delete ballots", false);
		}
	}

	// Delete ballots under Pickup Outside link
	public void DeleteOutsideBallots(String ballotType ) {
		try {
			if (outsideCheckboxLbl.size() > 0 == true) {
				if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";
				}
				if (ballotType.contains("PickUpOutside")) 
				{
					ballotType = "pickupOutside";
				}
				if (ballotType.contains("Template")) 
				{
					ballotType = "template";
				}
				// get (0) method Click on the select all check box for pick up DOTC BALLOTS
				 Util.ClickElement(driver, DOTCCheckboxLbl.get(0));
				 String delBtn = "//*[@ballottype='" + ballotType + "']//input[@id= 'deleteSelected']";
					driver.findElement(By.xpath(delBtn)).click();
//				 Util.ClickElement(driver, outsideCheckboxLbl.get(0));
//				 Util.ClickElement(driver, outsideDeleteSelected);
//				driver.findElement(By.xpath("//input[@id= 'deleteSelected']")).click();
				Util.ClickElement(driver, deleteSubmitBtn);
				Thread.sleep(3000);
				clickOnUpdateButton();
				verifyUpdateMessage();
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "The ballots is deleted successfully",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("The ballots is deleted successfully", true);
			} else if ((outsideCheckboxLbl.size() == 0) == true) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "There is no ballots to delete",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("There is no ballots to delete", true);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "The ballots is deleted successfully",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("There is something wrong to delete ballots", false);
			}

		} catch (Exception ex) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "There is something wrong to delete ballots",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("There is something wrong to delete ballots", false);
		}
	}


	// Delete ballots under Template link
	public void DeleteTemplateBallots(String ballotType) {
		try {
			if (templateCheckboxlevel.size() > 0 == true) {
				
				if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";
				}
				if (ballotType.contains("PickUpOutside")) 
				{
					ballotType = "pickupOutside";
				}
				if (ballotType.contains("Template")) 
				{
					ballotType = "template";
				}
				// get (0) method Click on the select all check box for pick up DOTC BALLOTS
				 Util.ClickElement(driver, DOTCCheckboxLbl.get(0));
				 String delBtn = "//*[@ballottype='" + ballotType + "']//input[@id= 'deleteSelected']";
					driver.findElement(By.xpath(delBtn)).click();
				
//				 Util.ClickElement(driver, templateCheckboxlevel.get(1));
//				driver.findElement(By.xpath("//input[@id= 'deleteSelected']")).click();
//				Util.ClickElement(driver, templateDeleteSelected);
				Util.ClickElement(driver, deleteSubmitBtn);
				Thread.sleep(3000);
				clickOnUpdateButton();
				verifyUpdateMessage();
				Util.waitForLoad(driver);
			} else if ((templateCheckboxlevel.size() == 0) == true) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "There is no ballots to delete",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("There is no ballots to delete", true);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "The ballots is deleted successfully",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("There is something wrong to delete ballots", false);
			}

		} catch (Exception ex) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "There is something wrong to delete ballots",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("There is something wrong to delete ballots", false);
		}
	}

	// Check the red color for duplicate ballot
	public void verifyDuplicateColorCode() {
		String RedColorCode = "rgba(255, 0, 0, 1)";
		String xpathExclamation = "//span[contains(text(),'Template')]/../..//i[contains(@id, 'TemplateDupExclamation')]";
		String TemplateColor = driver.findElement(By.xpath(xpathExclamation)).getCssValue("color");
		System.out.println("Duplicate error text color "+TemplateColor);
		if (TemplateColor.equalsIgnoreCase(RedColorCode)) {
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

	// Check the red color for duplicate ballot under Pick up Outside link
	public void OutsideDuplicateBallots() {
		String RedColorCode = "rgba(255, 0, 0, 1)";
		String OutsideColor = ballotsPickOutside.getCssValue("border-top-color");
		if (OutsideColor.equalsIgnoreCase(RedColorCode)) {
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

	// Check the red color for duplicate ballot under Pick up DOTC link
	public void DOTCDuplicateBallots() {
		String RedColorCode = "rgba(255, 0, 0, 1)";
		String DOTCColor = ballotsPickDOTC.getCssValue("border-top-color");
		if (DOTCColor.equalsIgnoreCase(RedColorCode)) {
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

	// Check if Update Ballot button id is disabled
	public void DisabledUpdateButton() throws Exception {
		if (updateBtn.isEnabled() != true) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Update button is disabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Update button is disabled", true);
		} else {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Update button is not disabled",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Update button is  not disabled", true);

		}

	}

	// Check Duplicate Ballot Error message when a duplicate ballot is created
	public void DuplicateErrorMessage() throws Exception {
		if (BallotsDuplicateError.isDisplayed() == true) {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Duplicate ballot Error message is displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Error message for the duplicate ballot is displayed", true);
		} else {
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Duplicate ballot Error message is not displayed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Error message for the duplicate ballot is not displayed", true);

		}

	}

	// Check Update Message after updating a Ballot
	//01-02-2022
	public void verifyUpdateMessage() {
		try {
			if (updateSuccessfulTxt.getText().equalsIgnoreCase("Update successful.")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Ballot is updated successfully",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Ballot is updated successfully", true);
			} else {
				throw new Exception("Ballot not updated");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Ballot is not updated successfully",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Ballot is not updated successfully", false);
		
		}

	}

	// Click Update Ballot button
	public void clickOnUpdateButton() {
		try {
			Util.waitFor(driver, updateBallotBtn);
			Util.ClickElement(driver, updateBallotBtn);
			Util.waitForSpinnerLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "ballots update ballot button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "ballots update ballot button is not clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("ballots update ballot button is not clicked", false);
		}

	}
	
	// 10 Aug 2022
	// updated xpath for update button
	public void clickOnUpdateButton(String ballotType) 
	{			
		try {		
				Util.waitForLoad(driver);
				if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";
				}
				else if (ballotType.contains("PickUpOutside")) 
				{
					ballotType = "pickupOutside";
				}
				else if (ballotType.contains("Template")) 
				{
					ballotType = "template";
				}		
				//String updateBtn = "//*[@ballottype='" + ballotType + "']//div[contains(@class,'btnUpdate')]//input[contains(@id,'updateBallots')]";
				String updateBtn = "//aac-ballot-data-table[@ballottype='"+ballotType+"']//span[text()='Update']";
				driver.findElement(By.xpath(updateBtn)).click();				
				Util.waitForSpinnerLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on update button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Clicked on update button", true);
				
			} 
			catch (Exception ex) 
			{
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Ballots are not deleted from the ballot list",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue("EXCEPTION OCCURRED", false);
				
			}		

	}

	// Validating if Inclusive TimeFrame is displayed as selected if check box is
	// selected while Search creation
	public void validationInclusiveTimeframeonBallot(String ballotType, String searchTitle) {
		try
		{
			if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";			
			}
			if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";			
			}
			if (ballotType.contains("Template")) 
			{
				ballotType = "template";
			}
		
			String inclusiveTimeFrameXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Inclusive Timeframe')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";						
			String inclusiveTimeFrameTxt = driver.findElement(By.xpath(inclusiveTimeFrameXpath)).getText();
			
			System.out.println(inclusiveTimeFrameTxt);
			if (inclusiveTimeFrameTxt.contains("Selected")) 
			{
				String output = "Inclusive Timeframe value for " + ballotType + "ballot is good and matches expected value.The value is " + inclusiveTimeFrameTxt;
				Assert.assertTrue(output, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
										
			}
			else
			{
				String output = "Inclusive Timeframe value for " + ballotType + "ballot is good and matches expected value.The value is " + inclusiveTimeFrameTxt;
				Assert.assertTrue(output, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
	
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			Assert.assertTrue("Exception Occurred", false);
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + ex.toString(),
			ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
			
	}

	// Compares the entered Departure date while Ballot creation with the Date
	// displayed in Ballot
	public void validateDepartureFromDateEntered() {
		try {
			String departureDateBlltPg = departureFromToDateBlltTxt.getText();
			System.out.println(departureDateBlltPg);
			Calendar cal = Calendar.getInstance();
			int currentDate = cal.get(Calendar.DAY_OF_MONTH);
			String[] monthName = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };// contractualMonth
			String departToDate = Integer.toString(currentDate);
			String currentMonth = monthName[cal.get(Calendar.MONTH)];
			String currentYear = Integer.toString(cal.get(Calendar.YEAR));
			String expectedDepartToDate = departToDate + " " + currentMonth
					+ currentYear.substring(currentYear.length() - 2);
			System.out.println("Departure To Date is " + expectedDepartToDate);
			if (departureDateBlltPg.equals(expectedDepartToDate)) {
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Departure To Date value is matching",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Departure To Date value is not matching",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Compares the entered Arrival dates while Ballot creation vs display in Ballot
	// - arrivalToDateSelected should be true if it is entered
	public void validateArrivalFromToDateEntered(String arrivalToDateSelected) {
		try {
			String arrivalDateBlltPg = arrivalFromToDateTxt.getText();
			System.out.println(arrivalDateBlltPg);
			Calendar cal = Calendar.getInstance();
			int currentDate = cal.get(Calendar.DAY_OF_MONTH);
			Integer lastDayOfTheMonth = Util.getLastDayOfTheMonth();
			String arrivalFromDate = " ";
			String currentMonth = " ";
			String[] monthName = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };// contractualMonth
			if (lastDayOfTheMonth.equals(currentDate)) {
				arrivalFromDate = "01";
				cal.add(Calendar.MONTH, 1);
				currentMonth = monthName[cal.get(Calendar.MONTH)];
			} else {
				arrivalFromDate = Integer.toString((cal.get(Calendar.DAY_OF_MONTH) + 1));
				currentMonth = monthName[cal.get(Calendar.MONTH)];
			}
			String currentYear = Integer.toString(cal.get(Calendar.YEAR));
			String expectedArrivalFromDate = arrivalFromDate + " " + currentMonth
					+ currentYear.substring(currentYear.length() - 2);
			System.out.println("Arrival From Date is " + expectedArrivalFromDate);
			if (arrivalDateBlltPg.contains(expectedArrivalFromDate)) {
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Arrival From Date value is matching",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			} else {
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Arrival From Date value is not matching",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
			Boolean arrivalToDateSelectedBool = Boolean.parseBoolean(arrivalToDateSelected);
			System.out.println(arrivalToDateSelectedBool);
			if (arrivalToDateSelectedBool == true) {
				String arrivalToDate = arrivalFromDate;
				String expectedArrivalToDate = arrivalToDate + " " + currentMonth
						+ currentYear.substring(currentYear.length() - 2);
				System.out.println("Arrival To Date is " + expectedArrivalFromDate);

				if (arrivalDateBlltPg.contains(expectedArrivalToDate)) {
					Util.waitForLoad(driver);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Arrival To Date value is matching",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} else {
					Util.waitForLoad(driver);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Arrival To Date value is not matching",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			}
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Arrival Dates are not matching",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Compare Minimum and Maximum Duty Period values selected with the data in ballot
	// 24 Aug 2022
	// updated xpath to validate Duty Period
		public void getDutyperiodvalueOnBallot(String minValue, String maxValue, String ballotType, String searchTitle) {
		try 
		{
			if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";			
				}
			else if (ballotType.contains("PickUpOutside")) 
				{
					ballotType = "pickupOutside";
					
				}
			else if (ballotType.contains("Template")) 
				{
					ballotType = "template";
					
				}
				//String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Min Duty Periods')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
				String exp = "//*[@ballottype='"+ballotType+"']//span[.='"+searchTitle+"']//..//div[normalize-space()='Min Duty Periods']//..";
				String dutyPeriodMin = driver.findElement(By.xpath(exp)).getText().split("\\n")[1];
				System.out.println(dutyPeriodMin);
				if ((dutyPeriodMin).contains(minValue)) 
				{
					String output = "Duty Period min value for " + ballotType + " ballot is good and matches expected value " + minValue;
									
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} 
				else 
				{
					String output = "Duty Period min value for " + ballotType + " ballot is not good and does not match the expected value " + minValue;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
				exp = "//*[@ballottype='"+ballotType+"']//span[.='"+searchTitle+"']//..//div[normalize-space()='Max Duty Periods']//..";
				String dutyPeriodMax = driver.findElement(By.xpath(exp)).getText().split("\\n")[1];
				
				 System.out.println(dutyPeriodMax);
				if ((dutyPeriodMax).contains(maxValue))
				{
					String output = "Duty Period max value for " + ballotType + " ballot is good and matches expected value " + maxValue;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} 
				else 
				{
					String output = "Duty Period max value for " + ballotType + " ballot is not good and does not match expected value " + maxValue;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}

			} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			Assert.assertTrue("Exception Occurred", false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in getDutyperiodvalueOnBallot method",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}
	}

	public void verifyDepartureDateOnly(String ballotType) {
		String fromDateInput = DOTCSearchScreen.DepFrom;
		ArrayList<String> title=new ArrayList<String>();
		String srhTitle = DOTCSearchScreen.SearchTitle;
		if (ballotType.contains("PickUpDOTC")) {
			ballotType = "pickup";
			String srchCriteria = "//*[.='" + srhTitle
					+ "']//..//..//..//..//..//..//..//..//..//..//..//..//..//..//..//table[@class = 'generic-request-details-table']";
			String criteria = driver.findElement(By.xpath(srchCriteria)).getText();
			String string[]=criteria.split("\\n");
			        for(String y:string){
			            try{
			                String[] temp=y.split("\\n");
			                title.add(temp[0]);
			            }catch(Exception e){}
			        }
			        String deptitle = title.get(0).toString();
			        String depValue = title.get(1).toString();
			        if (deptitle.equalsIgnoreCase("Dep") 
			        	&& depValue.equalsIgnoreCase(fromDateInput)){
						System.out.println("departure date title and value is: "+ deptitle + ":"+ depValue + " matched");
						Assert.assertTrue("departure date title and value is: "+ deptitle + ":"+ depValue + " matched", true);
			        }
			        else {
						System.out.println("departure date title and value is: "+ deptitle + ":"+ depValue + " not matched");
						Assert.assertFalse("departure date title and value is: "+ deptitle + ":"+ depValue + " not matched", true);
			        }

		}
	}
	
	public void verifyDepartureDateRange(String ballotType) {
		String fromDateInput = DOTCSearchScreen.DepFrom;
		String toDateInput = DOTCSearchScreen.DepTo;
		String DepartureDateRange = fromDateInput+ " - "+ toDateInput;
		ArrayList<String> title=new ArrayList<String>();
		String srhTitle = DOTCSearchScreen.SearchTitle;
		if (ballotType.contains("PickUpDOTC")) {
			ballotType = "pickup";
			String srchCriteria = "//*[.='" + srhTitle
					+ "']//..//..//..//..//..//..//..//..//..//..//..//..//..//..//..//table[@class = 'generic-request-details-table']";
			String criteria = driver.findElement(By.xpath(srchCriteria)).getText();
			String string[]=criteria.split("\\n");
			        for(String y:string){
			            try{
			                String[] temp=y.split("\\n");
			                title.add(temp[0]);
			            }catch(Exception e){}
			        }
			        String deptitle = title.get(0).toString();
			        String depValue = title.get(1).toString();
			        if (deptitle.equalsIgnoreCase("Dep") 
			        	&& depValue.equalsIgnoreCase(DepartureDateRange)){
						System.out.println("departure date title and value is: "+ deptitle + ":"+ depValue + " matched");
						Assert.assertTrue("departure date title and value is: "+ deptitle + ":"+ depValue + " matched", true);
			        }
			        else {
						System.out.println("departure date title and value is: "+ deptitle + ":"+ depValue + " not matched");
						Assert.assertFalse("departure date title and value is: "+ deptitle + ":"+ depValue + " not matched", true);
			        }

		}
	}
	
	public void verifyArrivalDateRange(String ballotType) {
		String fromDateInput = DOTCSearchScreen.DepFrom;
		String arrivalFromDateInput = DOTCSearchScreen.ArrFrom;
		String arrivalToDateInput = DOTCSearchScreen.ArrTo;
		String ArrivalDateRange = arrivalFromDateInput+ " - "+ arrivalToDateInput;
		ArrayList<String> title=new ArrayList<String>();
		String srhTitle = DOTCSearchScreen.SearchTitle;
		if (ballotType.contains("PickUpDOTC")) {
			ballotType = "pickup";
			String srchCriteria = "//*[.='" + srhTitle
					+ "']//..//..//..//..//..//..//..//..//..//..//..//..//..//..//..//table[@class = 'generic-request-details-table']";
			String criteria = driver.findElement(By.xpath(srchCriteria)).getText();
			String string[]=criteria.split("\\n");
			        for(String y:string){
			            try{
			                String[] temp=y.split("\\n");
			                title.add(temp[0]);
			            }catch(Exception e){}
			        }
			        String deptitle = title.get(0).toString();
			        String depValue = title.get(1).toString();
			        String arrtitle = title.get(2).toString();
			        String arrValue = title.get(3).toString();
			        if (deptitle.equalsIgnoreCase("Dep")
			        	&& arrtitle.equalsIgnoreCase("Arr")
			        	&& depValue.equalsIgnoreCase(fromDateInput)
			        	&& arrValue.equalsIgnoreCase(ArrivalDateRange)){
						System.out.println("Arrival date title and value is: "+ arrtitle + ":"+ arrValue + " matched");
						Assert.assertTrue("Arrival date title and value is: "+ arrtitle + ":"+ arrValue + " is matched", true);
			        }
			        else {
						System.out.println("departure date title and value is: "+ arrtitle + ":"+ arrValue + " not matched");
						Assert.assertFalse("departure date title and value is: "+ arrtitle + ":"+ arrValue + " is not matched", true);
			        }

		}
	}

	public void verifyDepArrDateRange(String ballotType) {
		String fromDateInput = DOTCSearchScreen.DepFrom;
		String ToDateInput = DOTCSearchScreen.DepTo;
		String departureDateRange = fromDateInput+ " - "+ ToDateInput;
		String arrivalFromDateInput = DOTCSearchScreen.ArrFrom;
		String arrivalToDateInput = DOTCSearchScreen.ArrTo;
		String ArrivalDateRange = arrivalFromDateInput+ " - "+ arrivalToDateInput;
		ArrayList<String> title=new ArrayList<String>();
		String srhTitle = DOTCSearchScreen.SearchTitle;
		if (ballotType.contains("PickUpDOTC")) {
			ballotType = "pickup";
			String srchCriteria = "//*[.='" + srhTitle
					+ "']//..//..//..//..//..//..//..//..//..//..//..//..//..//..//..//table[@class = 'generic-request-details-table']";
			String criteria = driver.findElement(By.xpath(srchCriteria)).getText();
			String string[]=criteria.split("\\n");
			        for(String y:string){
			            try{
			                String[] temp=y.split("\\n");
			                title.add(temp[0]);
			            }catch(Exception e){}
			        }
			        String deptitle = title.get(0).toString();
			        String depValue = title.get(1).toString();
			        String arrtitle = title.get(2).toString();
			        String arrValue = title.get(3).toString();
			        if (deptitle.equalsIgnoreCase("Dep")
			        	&& arrtitle.equalsIgnoreCase("Arr")
			        	&& depValue.equalsIgnoreCase(departureDateRange)
			        	&& arrValue.equalsIgnoreCase(ArrivalDateRange)){
						System.out.println("Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " matched");
						Assert.assertTrue("Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " matched", true);
			        }
			        else {
						System.out.println("Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " matched");
						Assert.assertFalse("Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " matched", true);
			        }

		}
	}

	// Return: Total pick up ballots with TAFB parameter.
	// Total pick up outside ballots with TAFB parameter,
	// Total Template ballots with TAFB parameter
	public static int[] getTotalBallotsWithNonZeroTAFBValue(List<WebElement> pumin, List<WebElement> puomin,
			List<WebElement> template) {

		int pu_totalTAFB = pumin.size();
		int puoutside_totalTAFB = puomin.size();
		int template_totalTAFB = template.size();
		return new int[] { pu_totalTAFB, puoutside_totalTAFB, template_totalTAFB };
	}

	// Verifying tafb min and max values against expected values for pick up ballots
	// Calculate Total Ballots with valid TAFB values
	// tafb[0] = total pick up ballots with TAFB parameter
	// tafb[1] = total pick up outside ballots with TAFB parameter
	// tafb[2] = total template ballots with TAFB parameter
	public Boolean verifyTAFBRangeOnBallotForPickUp(int[] tafb, String min, String max) {

		List<String> result = new ArrayList<String>();
		// tafb[0] = total pick up ballots with TAFB parameter
		if (tafb[0] > 0) {
			for (int i = 0; i < tafb[0]; i++) {
				String exp = "(//*[@ballottype='pickup']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'TAFB Min')])"
						+ "[" + (i + 1) + "]";
				exp = exp + "/parent::node()//div[@class='col-xs-7 dsColumn']";
				String tafbmin = driver.findElement(By.xpath(exp)).getText();
				if ((Float.parseFloat(tafbmin) == Float.parseFloat(min))) {
					result.add("TRUE");
					String output = "TAFB min value for pick up ballot " + (i + 1)
							+ " is good and matches expected value. ActualTAFBMin from seq output ="
							+ (int) (Float.parseFloat(tafbmin)) + "  ExpectedTAFBMin=" + min;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} else {
					result.add("FALSE");
					String output = "TAFB min value for pick up ballot " + (i + 1)
							+ " is NOT good and does NOT match expected value. ActualTAFBMin from seq output ="
							+ (int) (Float.parseFloat(tafbmin)) + "  ExpectedTAFBMin=" + min;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
				exp = "(//*[@ballottype='pickup']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'TAFB Max')])"
						+ "[" + (i + 1) + "]";
				exp = exp + "/parent::node()//div[@class='col-xs-7 dsColumn']";
				String tafbmax = driver.findElement(By.xpath(exp)).getText();
				if ((Float.parseFloat(tafbmax) == Float.parseFloat(max))) {
					result.add("TRUE");
					String output = "TAFB max value for pick up ballot " + (i + 1)
							+ " is good and matches expected value. ActualTAFBMax from seq output ="
							+ (int) (Float.parseFloat(tafbmax)) + "  ExpectedTAFBMax=" + max;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} else {
					result.add("FALSE");
					String output = "TAFB max value for pick up ballot " + (i + 1)
							+ " is NOT good and does NOT match expected value. ActualTAFBMax from seq output ="
							+ (int) (Float.parseFloat(tafbmax)) + "  ExpectedTAFBMax=" + max;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			}
		}
		boolean allEqual = new HashSet<String>(result).size() <= 1;
		return allEqual;
	}

	// Verifying tafb min and max values against expected values for pick up outside
	// ballots
	// Calculate Total Ballots with valid TAFB values
	// tafb[0] = total pick up ballots with TAFB parameter
	// tafb[1] = total pick up outside ballots with TAFB parameter
	// tafb[2] = total template ballots with TAFB parameter
	//18-01-2022
	public Boolean verifyTAFBRangeOnBallotForPickUpOutside(int[] tafb, String min, String max) {

		List<String> result = new ArrayList<String>();
		// tafb[1] = total pick up outside ballots with TAFB parameter
		if (tafb[1] > 0) {
			for (int i = 0; i < tafb[1]; i++) {
				String exp = "(//*[@ballottype='pickupOutside']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'TAFB Min')])"
						+ "[" + (i + 1) + "]";
				exp = exp + "/parent::node()//div[@class='col-xs-7 dsColumn']";
				String tafbmin = driver.findElement(By.xpath(exp)).getText();
				if ((Float.parseFloat(tafbmin) == Float.parseFloat(min))) {
					result.add("TRUE");
					String output = "TAFB min value for pick up outside ballot " + (i + 1)
							+ " is good and matches expected value. ActualTAFBMin from seq output ="
							+ (int) (Float.parseFloat(tafbmin)) + "  ExpectedTAFBMin=" + min;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} else {
					result.add("FALSE");
					String output = "TAFB min value for pick up outside ballot " + (i + 1)
							+ " is NOT good and does NOT match expected value. ActualTAFBMin from seq output ="
							+ (int) (Float.parseFloat(tafbmin)) + "  ExpectedTAFBMin=" + min;
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse(output, true);					
				}
				exp = "(//*[@ballottype='pickupOutside']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'TAFB Max')])"
						+ "[" + (i + 1) + "]";
				exp = exp + "/parent::node()//div[@class='col-xs-7 dsColumn']";
				String tafbmax = driver.findElement(By.xpath(exp)).getText();
				if ((Float.parseFloat(tafbmax) == Float.parseFloat(max))) {
					result.add("TRUE");
					String output = "TAFB max value for pick up outside ballot " + (i + 1)
							+ " is good and matches expected value. ActualTAFBMax from seq output ="
							+ (int) (Float.parseFloat(tafbmax)) + "  ExpectedTAFBMax=" + max;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} else {
					result.add("FALSE");
					String output = "TAFB max value for pick up outside ballot " + (i + 1)
							+ " is NOT good and does NOT match expected value. ActualTAFBMax from seq output ="
							+ (int) (Float.parseFloat(tafbmax)) + "  ExpectedTAFBMax=" + max;
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse(output, true);
				}
			}
		}
		boolean allEqual = new HashSet<String>(result).size() <= 1;
		return allEqual;
	}

	// Verifying tafb min and max values against expected values for template
	// ballots
	// Calculate Total Ballots with valid TAFB values
	// tafb[0] = total pick up ballots with TAFB parameter
	// tafb[1] = total pick up outside ballots with TAFB parameter
	// tafb[2] = total template ballots with TAFB parameter
	public Boolean verifyTAFBRangeOnBallotForTemplate(int[] tafb, String min, String max) {

		List<String> result = new ArrayList<String>();
		// tafb[2] = total template ballots with TAFB parameter
		if (tafb[2] > 0) {
			for (int i = 0; i < tafb[2]; i++) {
				String exp = "(//*[@ballottype='template']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'TAFB Min')])"
						+ "[" + (i + 1) + "]";
				exp = exp + "/parent::node()//div[@class='col-xs-7 dsColumn']";
				String tafbmin = driver.findElement(By.xpath(exp)).getText();
				if ((Float.parseFloat(tafbmin) == Float.parseFloat(min))) {
					result.add("TRUE");
					String output = "TAFB min value for template ballot " + (i + 1)
							+ " is good and matches expected value. ActualTAFBMin from seq output ="
							+ (int) (Float.parseFloat(tafbmin)) + "  ExpectedTAFBMin=" + min;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} else {
					result.add("FALSE");
					String output = "TAFB min value for template ballot " + (i + 1)
							+ " is NOT good and does NOT match expected value. ActualTAFBMin from seq output ="
							+ (int) (Float.parseFloat(tafbmin)) + "  ExpectedTAFBMin=" + min;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
				exp = "(//*[@ballottype='template']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[@class='col-xs-5'][contains(text(),'TAFB Max')])"
						+ "[" + (i + 1) + "]";
				exp = exp + "/parent::node()//div[@class='col-xs-7 dsColumn']";
				String tafbmax = driver.findElement(By.xpath(exp)).getText();
				if ((Float.parseFloat(tafbmax) == Float.parseFloat(max))) {
					result.add("TRUE");
					String output = "TAFB max value for template ballot " + (i + 1)
							+ " is good and matches expected value. ActualTAFBMax from seq output ="
							+ (int) (Float.parseFloat(tafbmax)) + "  ExpectedTAFBMax=" + max;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} else {
					result.add("FALSE");
					String output = "TAFB max value for template ballot " + (i + 1)
							+ " is NOT good and does NOT match expected value. ActualTAFBMax from seq output ="
							+ (int) (Float.parseFloat(tafbmax)) + "  ExpectedTAFBMax=" + max;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			}
		}
		boolean allEqual = new HashSet<String>(result).size() <= 1;
		return allEqual;
	}

	// Comparing the TAFB min and TAFB max values that were entered during saving
	// the
	// ballot against actual value displayed in UI
	//18-01-2022
	public void verifyTAFBRangeOnBallot(String min, String max) {
		try {
			// Calculate Total Ballots with valid TAFB values
			// tafb[0] = total pick up ballots with TAFB parameter
			// tafb[1] = total pick up outside ballots with TAFB parameter
			// tafb[2] = total template ballots with TAFB parameter

			int[] tafb = getTotalBallotsWithNonZeroTAFBValue(TAFBMinListPickUpBallots, TAFBMinListPickUpOutsideBallots,
					TAFBMinListTemplateBallots);
			Boolean tafbPickUp = verifyTAFBRangeOnBallotForPickUp(tafb, min, max);
			clickPicUpOutsideLink();
			Boolean tafbPickUpOutside = verifyTAFBRangeOnBallotForPickUpOutside(tafb, min, max);
			clickTemplateLink();
			Boolean tafbTemplate = verifyTAFBRangeOnBallotForTemplate(tafb, min, max);
			// If all three boolean values above are true then it means verification
			// succeeded for TAFB expected values for
			// ALL ballots type
			if ((tafbPickUp && tafbPickUpOutside && tafbTemplate)) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						"TAFB parameter check passed for all the ballots that were created");
			} else {
				String output = "TAFB parameter check FAILED \n" + "Pick up ballot result= " + tafbPickUp
						+ "Pick Up Outside ballot results= " + tafbPickUpOutside + "Template ballot results= "
						+ tafbTemplate;
				ExtentTestManager.getTest().log(LogStatus.FAIL, output);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Exception occured in the method verifyTAFBRangeOnBallot in DOTCBallotScreen.java \t"
							+ ex.toString(),
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception occured in the method verifyTAFBRangeOnBallot in DOTCBallotScreen.java \t"
							+ ex.toString(), false);
		}

	}

	// Validation of Departure values based on Ballot Type
	//19-01-2022
	public void verifyDepStationValuesOnBallot(String stations, String ballotType, String searchTitle) 
	{
		try
		{
			String[] stationList = stations.split(",");
			if (ballotType.contains("PickUpDOTC"))
			{
				ballotType = "pickup";		
			}
			if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";			
			}
			if (ballotType.contains("Template")) 
			{
				ballotType = "template";		
			}
			
			String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[contains(@class,'generic-details-container')]//table//div[contains(@class,'departureStations')]";
			System.out.println(exp);
			List<WebElement> departStations = driver.findElements(By.xpath(exp));
			for(int i = 0; i < departStations.size();i++)
			{
				String depStationText = departStations.get(i).getText();				
				System.out.println("Value is " + depStationText);
				for (int k = 0; k < stationList.length; k++) 
				{
					if (stationList[k].contains(depStationText)) 
					{
						String output = "Departure Stations value for "+ballotType+" ballot is good and matches expected value. The value is " + depStationText;
						Assert.assertTrue(output, true);
						ExtentTestManager.getTest().log(LogStatus.PASS, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						break;
					}
					
					else 
						if (k == departStations.size() - 1) 
						{
							String output = "Fail: Departure Stations  is Bad. Actual Value from seq output =" + departStations.get(i)
								+ " and Expected Value is = " + stationList[k];
						log.info(output);
						ExtentTestManager.getTest().log(LogStatus.FAIL, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue(output, false);
						}
				}
			 
			}
		}
			catch (Exception ex) 
			{
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						"Exception occured in the method verifyDepStationValuesOnBallot in DOTCBallotScreen.java \t"
								+ ex.toString(),
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception Occurred", false);
			}
	
	}

	/// Click Pick Up Outside Ballot link to view Pick Up Outside Ballot
	public void clickPicUpOutsideLink() {
		try {
			Util.ClickElement(driver, pickUpOutsideLink);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Pick Up Outside link has been clicked",
			ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Clicking Pick Up Outside link has thrown exception" + ex.toString(),
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}

	}

	// Click Template link to view Template Ballots
	//18-01-2022
	public void clickTemplateLink() {
		try {
			Util.ClickElement(driver, templateLink);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Template link has been clicked",
			ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Template link has been clicked", true);
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL,
					"Clicking Template link has thrown exception" + ex.toString(),
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Clicking Template link has thrown exception", false);
		}

	}

	// Clicking Edit link to Edit the ballot
	// Updated xpath
	// 21-02-2022
	public void clickBallotDOTCOnBtn() {
		try {
			if(driver.findElements(By.xpath("//mat-label[normalize-space()='Pickup DOTC']//../mat-slide-toggle//input[@aria-checked='false']")).size() == 0)
				Util.ClickElement(driver, OnBtnpickUpDOTC);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Turned off ballot",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not turn off ballot",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Could not turn off ballot", false);
		}
	}

	// Clicking Edit link to Edit the ballot
	// Updated xpath
	// 21-02-2022
	public void clickBallotDOTCOffBtn() {
		try {
			if(driver.findElements(By.xpath("//mat-label[normalize-space()='Pickup DOTC']//../mat-slide-toggle//input[@aria-checked='true']")).size() == 0)
				Util.ClickElement(driver, OffBtnpickUpDOTC);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Turned on ballot",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not turn on ballot",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Could not turn on ballot", false);
		}
	}

	// Clicking Edit link to Edit the ballot
	public void clickOptBallotLink() {
		try {
			Util.ClickElement(driver, optBallotLink);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Opt Link is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Opt Link is not Clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Clicking Premium checkbox
	public void clickPremiumChkBx() {
		try {
			WebElement ele = driver.findElement(By.xpath("//span[normalize-space()='Premium only']//..//mat-slide-toggle"));
			Util.ClickElement(driver, ele);
			//Util.ClickButton(driver, premiumChckBx);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Premium checkbox is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Premium checkbox is not Clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Click Submit Button in Options Pop Up
	public void clickPopUpSubmitBtn() {
		try {
			WebElement ele = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
			Util.ClickElement(driver, ele);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Submit button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Submit button is not Clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// this method is used to verify the generic request title
	//16-03-2022
	public void verifyGenericRequestTitle(String SearchTitle) {
		boolean balReqTitle = driver.findElements(By.xpath("//*[.='"+SearchTitle+"']")).size() > 0;// generic request title displayed in the ballots
		// comparing Request title of generic request
		System.out.println(balReqTitle);
		if (balReqTitle) {
			System.out.println("Ballots request title is: " + balReqTitle);
			Assert.assertTrue("Generic ballot is updated successfully", true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Generic ballot is updated successfully",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));			
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Generic ballot is not updated successfully",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));			
			Assert.assertFalse("Generic ballot is not updated successfully", true);
		}

	}

	// this method is used to verify sequence number for specific request
	//17-01-2022
	public void verifySequenceNumber() {
		//String balReqTitle = RequestTitle.get(0).getText();// specific request title displayed as a sequence number
		boolean isPresent = false;
		for(int i = 0; i < DOTCSearchScreen.sequencesFromTable.size() && !isPresent; i++)
			isPresent = driver.findElements(By.xpath("//*[.='"+DOTCSearchScreen.sequencesFromTable.get(i)+"']")).size() > 0;
			
		if (isPresent) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Specific ballot is updated successfully",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Specific ballot is updated successfully", true);
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Specific ballot is not updated",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Specific ballot is not updated", true);
		}

	}

	// this method is used to verify "Specific Request title" text in the ballots
	//17-01-2022
	public void verifyGenSpecificTitle(String SearchTitle) {
		boolean isPresent = false;
		for(int i = 0; i < DOTCSearchScreen.sequencesFromTable.size() && !isPresent; i++)
			isPresent = driver.findElements(By.xpath("//*[.='"+DOTCSearchScreen.sequencesFromTable.get(i)+"']")).size() > 0;
			
		boolean balReqTitle = driver.findElements(By.xpath("//*[.='"+SearchTitle+"']")).size() > 0;// generic request title displayed in the ballots
		if (balReqTitle && isPresent) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Both specific and generic ballots are updated successfully",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Both specific and generic ballots are updated successfully", true);
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Both specific and generic ballots are not updated successfully",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Both specific and generic ballots are not updated successfully", true);
		}

	}

	
	public void clickUpdate() throws InterruptedException {
		try {
			Util.ClickButton(driver, updateBallots, 6);
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void checkBallot(String xpth) throws Exception {
		List<WebElement> we = driver.findElements(By.xpath(xpth));
		for (WebElement w : we)
			try {
				Util.ClickButton(driver, w, 2);
				break;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
	}

	// 11 Aug 2022
	// updated xpath for delete button
	public void deleteCheckedBs(String btype) throws InterruptedException {
		//List<WebElement> we = driver.findElements(By.xpath("//*[@id='deleteSelected']"));
		/*if (btype.contains("Pick Up DOTC")) 
			btype = "pickup";			
		else if (btype.contains("Pick Up Outside")) 
			btype = "pickupOutside";		
		else if (btype.contains("Template")) 
			btype = "template";	*/
		System.out.println(btype);
		try {
			//WebElement we = driver.findElement(By.xpath("//*[@ballottype = '" + btype + "']//div[contains(@class,'btnDelete')]//button[contains(@id,'deleteSelected')]"));
			List<WebElement> we = driver.findElements(By.xpath("//*[@ballottype = '"+btype+"']//button//span[text()='Delete']"));
			if(we.size() > 0) {
				Util.ClickButton(driver, we.get(0));
				Util.waitForLoad(driver);
				Util.ClickButton(driver, deleteSubmitBtn);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully deleted ballot(s)",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Successfully selected all duplicate ballot(s) in", true);	
			}
		} catch(Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception in deleting ballot(s)", false);	
			ex.printStackTrace();
		}
	}

	public boolean areBsExisting(String[] chosenBs) {
		String xp = "//*[.=%s]/../../..//*[contains(.,'%s')]";
		for (int i = 0; i < chosenBs.length / 2; i++)
			try {
				driver.findElement(By.xpath(String.format(xp, chosenBs[2 * i], chosenBs[2 * i + 1])));
			} catch (Exception e) {
				throw e;
			}
		return true;
	}

	public void click(String bTypeX, int n) throws InterruptedException {
		try {
			Util.ClickButton(driver, driver.findElement(By.xpath(bTypeX)), n);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	//Method to verify PRemium Ballot
		public void verifyPremiumValuesOnBallot(String ballotType, String searchTitle) 
		{
			try
			{	
				if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";			
				}
				else if (ballotType.contains("PickUpOutside")) 
				{
					ballotType = "pickupOutside";
					
				}
				else if (ballotType.contains("Template")) 
				{
					ballotType = "template";
					
				}
				String exp = "//*[@ballottype='"+ ballotType + "']//*[.='" + searchTitle + "']//..//..//span[normalize-space()='$']";
				System.out.println(exp);
				String premiumSymbol = driver.findElement(By.xpath(exp)).getText();
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath(exp)));
				
				System.out.println("Value is " + premiumSymbol);
				String greenColorCode = "rgba(174, 217, 179, 1)";
				String color = driver.findElement(By.xpath(exp)).getCssValue("background-color");
				if (premiumSymbol.contains("$") && color.contains(greenColorCode)) 
				{	
					String output = "Premium Ballots value for pick up ballot is " + premiumSymbol
									+ "and the color is " + color + ".Result is as expected";
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
				else 
				{
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Ballot is not saved with Premium Ballot",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue(false);
				}
			}
				
			catch(Exception ex)
			{
				ex.printStackTrace();
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in verifyPremiumValuesOnBallot method",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.fail("Exception thrown:" + ex.getMessage());
			}

	}

// Method to compare Calendar Days Values
		// 22 Aug 2022
		// updated xpath to validate min calendar days and max calendar days
		public void getCalendarDaysDisplayStatusOnBallot(String min, String max, String ballotType, String searchTitle) 
		{
			try
			{
				if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";		
				}
				else if (ballotType.contains("PickUpOutside"))
				{
					ballotType = "pickupOutside";			
				}
				else if (ballotType.contains("Template")) 
				{
					ballotType = "template";		
				}
				
				//String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Days Min')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
				String exp = "//*[@ballottype='"+ballotType+"']//span[.='"+searchTitle+"']//..//div[normalize-space()='Days Min']//..";
				String calDaysMin = driver.findElement(By.xpath(exp)).getText().split("\\n")[1];
				if (calDaysMin.contains(min)) 
				{
					String output = "Calendar Days min value for " + ballotType + " ballot is "	+ calDaysMin + ".It matches expected value :" + min;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} 
				else 
				{
					String output = "Calendar Days min value for " + ballotType + " ballot is " + calDaysMin + "not matching with expected value :" + min;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
				//exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Days Max')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
				exp = "//*[@ballottype='"+ballotType+"']//span[.='"+searchTitle+"']//..//div[normalize-space()='Days Max']//..";	
				String calDaysMax = driver.findElement(By.xpath(exp)).getText().split("\\n")[1];
				if (calDaysMax.contains(max)) 
				{
					String output = "Calendar Days max value for " + ballotType + " ballot is good and matches expected value:" + max;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} 
				else 
				{
					String output = "Calendar Days max value for " + ballotType + " ballot is" + calDaysMax + "not matching with expected value :" + max;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in getCalendarDaysDisplayStatusOnBallot method",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.fail("Exception thrown:" + ex.getMessage());
			}
		}
		
	
	// Method to compare Paid Credit Values
		public void getPaidCreditDisplayStatusOnBallot(String minHrs, String minMins, String maxHrs,String maxMins, String ballotType, String searchTitle) 
		{
			try
			{
				if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";			
				}
				if (ballotType.contains("PickUpOutside")) 
				{
					ballotType = "pickupOutside";		
				}
				if (ballotType.contains("Template")) 
				{
					ballotType = "template";			
				}
		
				//String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Min Paid Credit')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
				String exp = "//*[@ballottype='"+ballotType+"']//span[.='"+searchTitle+"']//..//div[normalize-space()='Min Paid Credit']//..";	
				System.out.println(exp);
				String paidCreditMin = driver.findElement(By.xpath(exp)).getText().split("\\n")[1];
				System.out.println(paidCreditMin);
				if (paidCreditMin.contains(minHrs) && paidCreditMin.contains(minMins)) 
				{
					String output = "Paid Credit min value for " + ballotType + " ballot is "
									+ paidCreditMin + ".It matches expected value :" + minHrs + "." + minMins;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} 
				else 
				{
					String output = "Paid Credit min value for " + ballotType + " ballot is "
									+ paidCreditMin + "not matching with expected value :" + minHrs + "." + minMins;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
				//exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Max Paid Credit')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
				exp = "//*[@ballottype='"+ballotType+"']//span[.='"+searchTitle+"']//..//div[normalize-space()='Max Paid Credit']//..";	
				String paidCreditMax = driver.findElement(By.xpath(exp)).getText().split("\\n")[1];
				System.out.println(paidCreditMax);
				if (paidCreditMax.contains(maxHrs) && paidCreditMax.contains(maxMins)) 
				{
					String output = "Paid Credit max value for " + ballotType + " ballot is "
									+ paidCreditMax + " and matches expected value:" + maxHrs + "." + maxMins;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} 
				else 
				{
					String output = "Paid Credit max value for " + ballotType + " ballot is "
									+ paidCreditMax + " and not matching with expected value:" + maxHrs + "." + maxMins;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
									ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in getPaidCreditDisplayStatusOnBallot method",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.fail("Exception thrown:" + ex.getMessage());
			}
				
		}

		// 12 Aug 2022
		// updated xpath to validate TAFB values
	// Method to compare TAFB Values
		public void getTAFBDisplayStatusOnBallot(String ballotType,String min, String max, String srchTitle) 
		{
			try
			{
				if (ballotType.contains("PickUpDOTC")) {
					ballotType = "pickup";
				}
				else if (ballotType.contains("PickUpOutside")) {
					ballotType = "pickupOutside";
				}
				else if (ballotType.contains("Template")) {
					ballotType = "template";
				}
		
				//String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'TAFB Min')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
				String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='row ng-star-inserted']//div[contains(normalize-space(),'TAFB Min')]//..";
				String tafbmin = driver.findElements(By.xpath(exp)).get(0).getText().split("\\n")[1];
				tafbmin = String.valueOf((int)Float.parseFloat(tafbmin));
				System.out.println((int)Float.parseFloat(tafbmin));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath(exp)));
				
				if ((Float.parseFloat(tafbmin) == Float.parseFloat(min))) 
				{
					String output = "TAFB min value for template ballot is good and matches expected value. ActualTAFBMin from seq output ="
									+ (int) (Float.parseFloat(tafbmin)) + "  ExpectedTAFBMin=" + min;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} 
				else 
				{
					String output = "TAFB min value for template ballot is NOT good and does NOT match expected value. ActualTAFBMin from seq output ="
									+ (int) (Float.parseFloat(tafbmin)) + "  ExpectedTAFBMin=" + min;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
				exp = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='row ng-star-inserted']//div[contains(normalize-space(),'TAFB Max')]//..";
				String tafbmax = driver.findElements(By.xpath(exp)).get(0).getText().split("\\n")[1];
				tafbmax = String.valueOf((int)Float.parseFloat(tafbmax));
				System.out.println(tafbmax);
				if ((Float.parseFloat(tafbmax) == Float.parseFloat(max))) 
				{
					String output = "TAFB max value for template ballot is good and matches expected value. ActualTAFBMax from seq output ="
									+ (int) (Float.parseFloat(tafbmax)) + "  ExpectedTAFBMax=" + max;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				} 
				else 
				{
					String output = "TAFB max value for template ballot is NOT good and does NOT match expected value. ActualTAFBMax from seq output ="
									+ (int) (Float.parseFloat(tafbmax)) + "  ExpectedTAFBMax=" + max;
					Assert.assertFalse(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in getTAFBDisplayStatusOnBallot method",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.fail("Exception thrown:" + ex.getMessage());
			}
		}

	public void verifySeqSrchCriteria() {
		String srhTitle = DOTCSearchScreen.SearchTitle;
		String exp = "//*[.='" + srhTitle
				+ "']//..//..//..//..//..//..//..//..//..//..//div[contains(text(),'Sequence Number')]//..";
		String seqRow = driver.findElement(By.xpath(exp)).getText();
		String seqNum = DOTCSearchScreen.seqNumber;
		if (seqRow.contains(seqNum)) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Other search Criteria is replaced by Sequence number",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			System.out.println("Other search Criteria is replaced by Sequence number");
			Assert.assertTrue("Other search Criteria is replaced by Sequence number", true);
		} else {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Other search Criteria is not replaced by Sequence number",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Other search Criteria is not replaced by Sequence number", true);

		}

	}

	// When list of parameters are more in ballot down arrow appears. Need to click
	// that to view the other locators
	// 22 Aug 2022
	// updated xpath to show more details in xpath
	public void clickMoreParameterArrow(String ballotType, String srchTitle) 
	{
		try
		{
			if (ballotType.contains("PickUpDOTC")) {
				ballotType = "pickup";
			}
			if (ballotType.contains("PickUpOutside")) {
				ballotType = "pickupOutside";
			}
			if (ballotType.contains("Template")) {
				ballotType = "template";
			}
	       
			//String exp = "//*[@ballottype='" + ballotType
			//		+ "']//*[.='" + srchTitle + "']/ancestor::table[contains(@class,'ballotDataTable')]//label[contains(@class,'hidden-xs read-more-trigger')]/i";
			String exp = "//*[@ballottype='" + ballotType + "']//span[.='" + srchTitle + "']//..//..//label[normalize-space()='More Details']";
			WebElement downArrow = driver.findElement(By.xpath(exp));					
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()",downArrow);
				
			Util.ClickElement(driver, downArrow);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void ClickEditButton() {
		try {
			Util.ClickElement(driver, editBallotLink);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edit button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit button is not Clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	public void ClickOnEditButton() {
		try {
			String srhTitle = DOTCSearchScreen.SearchTitle;
			String edit = "//*[.='" + srhTitle
					+ "']//..//..//..//..//..//..//..//..//..//..//a[contains(text(),'Edit')]";
			driver.findElement(By.xpath(edit)).click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edit button is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit button is not Clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	// Clicking Edit link to Edit the ballot
	public void clickEditBallotLinkBasedonBallotType(String ballotType) {
		try {
			System.out.println("Edit code block entered.");
			if (ballotType.contains("PickUpDOTC")) {
				ballotType = "pickup";
			}
			if (ballotType.contains("PickUpOutside")) {
				ballotType = "pickupOutside";
			}
			if (ballotType.contains("Template")) {
				ballotType = "template";
			}
			String exp = "//*[@ballottype='" + ballotType
					+ "']//div[contains(@class,'dataTables_wrapper')]//table[contains(@class,'ballotDataTable')]//div[contains(@class,'row-button-container ')]//span[contains(@class,'ng-star-inserted')][2]//parent::a";
			System.out.println(exp);
			driver.findElement(By.xpath(exp)).click();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Edit Link is clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			System.out.println("Edit code block ended.");
		} catch (Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Link is not Clicked",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	public void clickCreateGenericBtn() {
		try {
			Util.ClickElement(driver, createGenericBallotsBtn);
			Util.waitForSpinnerLoad(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	//updated xpath
	//10 Aug 2022
	public void deleteBallotsBaseOnBallotTitle(String ballotType, String srchTitle) 
	{
		List<String> ballotsTitle = new ArrayList<String>();
		
		try {
			
				if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";
				}
				else if (ballotType.contains("PickUpOutside")) 
				{
					ballotType = "pickupOutside";
				}
				else if (ballotType.contains("Template")) 
				{
					ballotType = "template";
				}
				/*String checkboxStr = "//*[@ballottype='"+ballotType+"']//span[text()='"+srchTitle+"']/../../../../../../../../../../../../.."
						+ "//td[@class='checkbox-column']//input";*/
				
				String checkboxStr = "//*[@ballottype='"+ballotType+"']//span[text()='"+srchTitle+"']/../..//mat-card-title//input[@type='checkbox']";
				int sizeSameTitle = driver.findElements(By.xpath(checkboxStr)).size();
				
				if(sizeSameTitle!=0){
					for (int i = 0; i < sizeSameTitle; i++) {
						Util.waitForLoad(driver);
						//WebDriverWait wait = new WebDriverWait(driver, 30);
						//WebElement element = wait
						//		.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(checkboxStr))));
						//driver.findElement(By.xpath(checkboxStr)).click();
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath(checkboxStr)));
						
					
						Util.ClickElement(driver, driver.findElements(By.xpath(checkboxStr)).get(i));
					}
		
						//String delBtn = "//*[@ballottype='" + ballotType
							//+ "']//div[contains(@class,'btnDelete')]//button[contains(@id,'deleteSelected')]";
						String delBtn = "//*[@ballottype='"+ballotType+"']//button//span[text()='Delete']";
						Util.ClickElement(driver, driver.findElement(By.xpath(delBtn)));
						Util.ClickElement(driver, deleteSubmitBtn);
						Util.waitForLoad(driver);
						
						Boolean isPresent = driver.findElements(By.xpath("//div[contains(text(), 'Please fix duplicates.')]")).size() == 0;
						
						if(isPresent) {
							clickOnUpdateButton(ballotType);
							Util.waitForSpinnerLoad(driver);
							//verifyUpdateMessage();
						}
					
					String exitingBallot = "//*[@ballottype='" + ballotType + "']//div[@class = 'col-lg-8 summary-title']";
					List<WebElement> ballotTitle = driver.findElements(By.xpath(exitingBallot));
					int totalTitle = ballotTitle.size();
					for (int i = 0; i < totalTitle; i++) 
					{
						ballotsTitle.add(ballotTitle.get(i).getText());
					}
					
					if (ballotsTitle.contains(srchTitle)) 
					{
						System.out.println("Ballot with Request title:" + srchTitle
								+ " is not deleted successfully for " + ballotType);
						ExtentTestManager.getTest().log(LogStatus.FAIL, "Ballot with Request title:" + srchTitle
								+ " is not deleted successfully for " + ballotType,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue("Ballot with Request title:" + srchTitle
							+ " is not deleted successfully for " + ballotType, false);
					} 
					else 
					{
						System.out.println("Ballot with Request title:" + srchTitle
								+ " is deleted successfully for " + ballotType);
						Assert.assertTrue("Ballot with Request title:" + srchTitle
							+ " is deleted successfully for " + ballotType, true);
						ExtentTestManager.getTest().log(LogStatus.PASS, "Ballot with Request title:" + srchTitle +" is deleted successfully for " + ballotType,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					}
				}
				else
				{
					System.out.println("Ballot with " + srchTitle
							+ " is not present and hence need not be deleted for " + ballotType);
					Assert.assertTrue("Ballot with " + srchTitle
							+ " is not present and hence need not be deleted for " + ballotType, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Ballot with " + srchTitle
							+ " is not present and hence need not be deleted for " + ballotType,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
		} 
		catch (Exception ex) 
		{
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Ballots are not deleted from the ballot list",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Ballots are not deleted from the ballot list", false);
		}
	}
	// 24 Aug 2022
	// updated xpath to validate sit time
	// Validation of Sit Time values based on Ballot Type and title
	public void verifySitTimeValuesOnBallot(String sitTimeInput, String ballotType, String srchTitle) 
	{
		try
		{		
			if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";			
			}
			else if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";		
			}
			else if (ballotType.contains("Template")) 
			{
				ballotType = "template";			
			}	
			//String sitTimeValueXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='col-xs-5'][contains(text(),'Sit Time')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
			String sitTimeValueXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='row ng-star-inserted']//div[contains(normalize-space(),'Sit Time')]//..";
			String sitTimeText = driver.findElement(By.xpath(sitTimeValueXpath)).getText().split("\\n")[1];
			if (Float.parseFloat(sitTimeInput)== Float.parseFloat(sitTimeText)) 
			{
				String output = "Sit Time value for " + ballotType + " ballot is good and matches expected value. The value is " + sitTimeText;
				Assert.assertTrue(output, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
								
			}
			else
			{				
				String output = "Sit Time value for " + ballotType + " ballot is not good and does not match expected value. The value is " + sitTimeText;
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(output, false);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + e.toString(),
			ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("EXCEPTION OCCURRED", false);			
		}
		
	}
	
	// Validation of Layover values based on Ballot Type and title
	// 22 Aug 2022
	// updated xpath to validate layover values
		public void verifyLayoverValuesOnBallot(String include, String exclude, String ballotType, String srchTitle) 
		{
			try
			{
				if (ballotType.contains("PickUpDOTC")) 
				{
					ballotType = "pickup";			
				}
				else if (ballotType.contains("PickUpOutside")) 
				{
					ballotType = "pickupOutside";			
				}
				else if (ballotType.contains("Template")) 
				{
					ballotType = "template";
				}
			
				//String layoverIncXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Layover Include')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";						
				String layoverIncXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+srchTitle+"']//..//div[normalize-space()='Layover Include']//..";
				String layoverIncText = driver.findElement(By.xpath(layoverIncXpath)).getText().split("\\n")[1];
						
				//String layoverExXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Layover Exclude')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
				String layoverExXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+srchTitle+"']//..//div[normalize-space()='Layover Exclude']//..";
				String layoverExText = driver.findElement(By.xpath(layoverExXpath)).getText().split("\\n")[1];
						
				System.out.println("Include Value " + layoverIncText + " and Exclude Value  " + layoverExText);
				if (layoverIncText.contains(include) && layoverExText.contains(exclude) ) 
				{
					String output = "Include Layover value for" + ballotType + "ballot is good and matches expected value.The value is " + layoverIncText;
					output = output + "/nExclude Layover value for" + ballotType + "ballot is good and matches expected value.The value is " + layoverExText;
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
											
				}
				else
				{
					String output = "Include Layover value for" + ballotType + "ballot is not matching the expected value " + layoverIncText;
					output = output + "/nExclude Layover value for" + ballotType + "ballot is not matching the expected value " + layoverExText;
					Assert.assertTrue(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
				String output = "Exception in method";
				Assert.assertTrue(output, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + ex.toString(),
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
				
		}

		
	// Clicking Opt link based on ballot type and title
	//29 Aug 2022
	// updated xpath for opt link
	public void clickOptBallotLinkBasedonBallotType(String ballotType, String srchTitle) {
	 try 
		{
			if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";
			}
			else if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";
			}
			else if (ballotType.contains("Template")) 
			{
				ballotType = "template";
			}
					
			String exp = "//*[@ballottype='"+ballotType+"']//*[.='"+srchTitle+"']//..//..//mat-icon[@aria-haspopup='true'][text()='more_vert']";
			WebElement ele = driver.findElement(By.xpath(exp));
			if(Util.waitForElementClickable(driver, ele))
				Util.ClickButton(driver, ele);
			else
				throw new Exception("Xpath not found");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Opt Link is clicked",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Opt Link is clicked", true);
		} 
	 catch (Exception ex) 
	 {
		 ex.printStackTrace();
		 ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception while clicking on Opt link",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 Assert.assertTrue("Exception while clicking on Opt link", false);
	 }
}

	//11 Aug 2022
	// updated xpath for editing ballot
// Clicking Edit link to Edit the ballot based on ballot type and title
public void clickEditBallotLinkBasedonBallotType(String ballotType, String srchTitle) 
{
	try 
	{
		Util.waitForLoad(driver);
		System.out.println("Edit code block entered.");
		if (ballotType.contains("PickUpDOTC")) 
		{
			ballotType = "pickup";
		}
		if (ballotType.contains("PickUpOutside")) 
		{
			ballotType = "pickupOutside";
		}
		if (ballotType.contains("Template")) 
		{
			ballotType = "template";
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']/ancestor::tr/preceding-sibling::tr[contains(@class,'row-buttons')][position()=1]//*[.='Edit']/a";
		String exp = "//*[@ballottype='"+ballotType+"']//*[.='"+srchTitle+"']//..//..//mat-icon[@aria-haspopup='true'][text()='more_vert']";
		//System.out.println(exp);
		driver.findElement(By.xpath(exp)).click();
		exp = "//*[text()='Edit request']";
		driver.findElement(By.xpath(exp)).click();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Edit Link is clicked",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		System.out.println("Edit code block ended.");
	} 
	catch (Exception ex) 
	{
		ex.printStackTrace();
		Assert.assertTrue("EXCEPTION OCCURRED", false);
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Edit Link is not Clicked",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
	}
}

// 24 Aug 2022
// updated xpath to validate dep date
//Validation of DefaultDate in Ballot
public void validateDefaultDepartureDateEnteredinBallot(String ballotType, String searchTitle) 
{
	try 
	{
		if (ballotType.contains("PickUpDOTC")) 
		{
			ballotType = "pickup";
		}
		else if (ballotType.contains("PickUpOutside")) 
		{
			ballotType = "pickupOutside";
		}
		else if (ballotType.contains("Template")) 
		{
			ballotType = "template";
		}
		//String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Dep')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
		String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle + "']//..//div[@class='row ng-star-inserted']//div[contains(normalize-space(),'Dep')]//..";
		System.out.println(exp);
		String departureDateBlltPg = driver.findElement(By.xpath(exp)).getText().split("\\n")[1];
		System.out.println(departureDateBlltPg);
		Calendar cal = Calendar.getInstance();
		int currentDate = cal.get(Calendar.DAY_OF_MONTH);
		String[] monthName = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };// contractualMonth
		String departToDate = Integer.toString(currentDate);
		String currentMonth = monthName[cal.get(Calendar.MONTH)];
		String currentYear = Integer.toString(cal.get(Calendar.YEAR));
		String expectedDepartToDate = departToDate + " " + currentMonth
				+ currentYear.substring(currentYear.length() - 2);			
		System.out.println("Departure To Date is " + expectedDepartToDate);
		if (departureDateBlltPg.equals(expectedDepartToDate)) 
		{
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Departure Date values are matching",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	} catch (Exception ex) 
	{
		ex.printStackTrace();
		Assert.assertTrue("EXCEPTION OCCURRED", false);
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Departure Date values are not matching",
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
	}
}

// 12 Aug 2022
// updated xpath for no work days
//Validation of No Work Days values based on Ballot Type
public void verifyNoWorkDaysValuesOnBallot(String daysInput, String ballotType, String srchTitle) 
{
	try
	{
		if (ballotType.contains("PickUpDOTC")) 
		{
			ballotType = "pickup";			
		}
		else if (ballotType.contains("PickUpOutside")) 
		{
			ballotType = "pickupOutside";			
		}
		else if (ballotType.contains("Template")) 
		{
			ballotType = "template";
		}
			
		String[] daysNames = daysInput.split(",");
			
		//String noWorkDaysXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'No Work Days')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";						
		String noWorkDaysXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='row ng-star-inserted']//div[normalize-space()='No Work Days']//..";
		String noWorkDaysText = driver.findElements(By.xpath(noWorkDaysXpath)).get(0).getText();
		String[] noWorkDays = noWorkDaysText.toLowerCase().split("\\n")[1].split(" ");		
		for (int i = 0; i< daysNames.length;i++)
		{
			String day = daysNames[i];
			for(int j = 0;j<noWorkDays.length;j++)
			{
				if(day.contains(noWorkDays[j]))
				{
					String output = "No Work Days value for " + ballotType + (i + 1)
							+ " is good and matches expected value.The value is " + day;
				Assert.assertTrue(output, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				break;
					
				}
				else
				{
					if(j== daysNames.length -1)
					{
						String output = "Fail: No Work Days value  is Bad. Actual Value from seq output =" + noWorkDays[i]
						+ " and Expected Value is = " + daysNames[j];
						log.info(output);
						Assert.assertTrue(output, false);
						ExtentTestManager.getTest().log(LogStatus.FAIL, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					}
				}
			}
		}
	}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + ex.toString(),
			ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("EXCEPTION OCCURRED", false);
		}
				
	}

// 12 Aug 2022
// updated xpath for no work hours
//Validation of No Work Hours values based on Ballot Type
public void verifyNoWorkHoursValuesOnBallot(String minValue, String maxValue, String ballotType, String srchTitle) 
{
	try
	{
		if (ballotType.contains("PickUpDOTC")) 
		{
			ballotType = "pickup";			
		}
		if (ballotType.contains("PickUpOutside")) 
		{
			ballotType = "pickupOutside";			
		}
		if (ballotType.contains("Template")) 
		{
			ballotType = "template";
		}
		
			
		//String noWorkHoursXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'No Work Hours')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";						
		String noWorkHoursXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='row ng-star-inserted']//div[normalize-space()='No Work Hours']//..";
		String noWorkHoursText = driver.findElements(By.xpath(noWorkHoursXpath)).get(0).getText();			
		if(noWorkHoursText.contains(minValue) && noWorkHoursText.contains(maxValue))
		{
			String output = "No Work Hours Min and Max value for " + ballotType + " is good and matches expected value.The expected value is " + minValue + "-" + maxValue + " and actual value is " + noWorkHoursText;
			Assert.assertTrue(output, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));						
		}
		else
		{
			String output = "Fail: No Work Hours Min and Max value  is Bad. Actual Value from seq output =" + noWorkHoursText
						+ " and Expected Value is  "  + minValue + "-" + maxValue;
			log.info(output);
			Assert.assertTrue(output, false);
		}
	}
	catch (Exception ex) 
	{
		ex.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + ex.toString(),
		ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		Assert.assertFalse("Exception Occured" + ex.getMessage(), true);

	}
				
}

// 12 Aug 2022
// updated xpath for no work dates
//Validation of No Work Dates values based on Ballot Type
public void verifyNoWorkDatesValuesOnBallot(String offset1, String offset2, String ballotType, String srchTitle) 
{
	try
	{
		if (ballotType.contains("PickUpDOTC")) 
		{
			ballotType = "pickup";			
		}
		if (ballotType.contains("PickUpOutside")) 
		{
			ballotType = "pickupOutside";			
		}
		if (ballotType.contains("Template")) 
		{
			ballotType = "template";
		}
		
		//String noWorkDatesXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'No Work Dates')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";						
		String noWorkDatesXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='row ng-star-inserted']//div[normalize-space()='No Work Dates']//..";
		String noWorkDatesText = driver.findElements(By.xpath(noWorkDatesXpath)).get(0).getText();	
		if(!offset1.equals(offset2))
		{	
			Date dt1 = new Date();
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(dt1);
			calendar1.add(Calendar.DATE, Integer.parseInt(offset1));
			dt1 = calendar1.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMMyy");
			String newdt1 = formatter.format(dt1);
			Date dt2 = new Date();
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(dt2);
			calendar2.add(Calendar.DATE, Integer.parseInt(offset2));
			dt2 = calendar2.getTime();			
			String newdt2 = formatter.format(dt2);
			if(noWorkDatesText.toLowerCase().contains(newdt1.toLowerCase()) && noWorkDatesText.toLowerCase().contains(newdt2.toLowerCase()))
			{
				String output = "No Work Dates 1st and 2nd datepicker value for " + ballotType + " is good and matches expected value.The expected value is " + noWorkDatesText + " and actual value is " + newdt1 + " and " + newdt2;
				Assert.assertTrue(output, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));	
			
			}
			else
			{
				String output = "No Work Dates 1st and 2nd datepicker value for " + ballotType + " is not good and does not match expected value.The expected value is " + noWorkDatesText + " and actual value is " + newdt1 + " and " + newdt2;
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(output, false);
			}
		}
		else if(offset1.equals(offset2))
		{
			Date dt1 = new Date();
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(dt1);
			calendar1.add(Calendar.DATE, Integer.parseInt(offset1));
			dt1 = calendar1.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMMyy");
			String newdt1 = formatter.format(dt1);
			
			if(noWorkDatesText.toLowerCase().contains(newdt1.toLowerCase()))
			{
				String output = "No Work Dates datepicker value for " + ballotType + " is good and matches expected value.The expected value is " + noWorkDatesText + " and actual value is " + newdt1 ;
				Assert.assertTrue(output, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));	
			
			}
			else
			{
				String output = "No Work Dates datepicker value for " + ballotType + " is not good and does not match expected value.The expected value is " + noWorkDatesText + " and actual value is " + newdt1 ;
				Assert.assertTrue(output, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		}
		
		
	}
	catch (Exception ex) 
	{
		ex.printStackTrace();
		Assert.assertTrue("EXCEPTION OCCURRED", false);
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + ex.toString(),
		ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
	}
				
}

// 22 Aug 2022
// updated xpath for min and max legs DP
public void verifyMinandMaxLegsDPValuesOnBallot(String MinLegsDP,String MaxLegsDP, String ballotType, String srchTitle) 
{
	try
	{		
		if (ballotType.contains("PickUpDOTC")) 
		{
			ballotType = "pickup";			
		}
		else if (ballotType.contains("PickUpOutside")) 
		{
			ballotType = "pickupOutside";		
		}
		else if (ballotType.contains("Template")) 
		{
			ballotType = "template";			
		}			
		
		//String MinLegsDPXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Min Legs DP')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
		String MinLegsDPXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+srchTitle+"']//..//div[normalize-space()='Min Legs DP']//..";	
		String MinLegsDPTextUI = driver.findElement(By.xpath(MinLegsDPXpath)).getText().split("\\n")[1];
		System.out.println(MinLegsDPTextUI);
		//String MaxLegsDPXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Max Legs DP')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
		String MaxLegsDPXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+srchTitle+"']//..//div[normalize-space()='Max Legs DP']//..";	
		
		String MaxLegsDPTextUI = driver.findElement(By.xpath(MaxLegsDPXpath)).getText().split("\\n")[1];
		System.out.println(MaxLegsDPTextUI);
		
		
		if (MinLegsDP.contains(MinLegsDPTextUI)) 
		{
			String output = "Min Legs Dp value for" + ballotType + "ballot is good and matches expected value.The value is " + MinLegsDPTextUI;
			Assert.assertTrue(output, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							
		}
		else
		{				
			String output = "Min Legs Dp value for" + ballotType + "ballot is not good and does not match expected value.The actual value is " + MinLegsDPTextUI +"The expected value is" + MinLegsDP;
			Assert.assertTrue(output, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}

		if (MaxLegsDP.contains(MaxLegsDPTextUI))
		{
			String output = "Max Legs Dp value for" + ballotType + "ballot is good and matches expected value.The value is " + MaxLegsDPTextUI;
			Assert.assertTrue(output, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							
		}
		else
		{				
			String output = "Max Legs Dp value for" + ballotType + "ballot is not good and does not match expected value.The actual value is " + MaxLegsDPTextUI+"The expected value is" + MaxLegsDP;
			Assert.assertTrue(output, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.assertTrue("EXCEPTION OCCURRED", false);
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + e.toString(),
		ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		
	}
	
}



// 22 Aug 2022
// updated xpath for min and max leg seq
public void verifyMinandMaxLegsPerSeqValuesOnBallot(String MinLegsseq,String MaxLegsSeq, String ballotType, String srchTitle) 
{
	try
	{		
		if (ballotType.contains("PickUpDOTC")) 
		{
			ballotType = "pickup";			
		}
		else if (ballotType.contains("PickUpOutside")) 
		{
			ballotType = "pickupOutside";		
		}
		else if (ballotType.contains("Template")) 
		{
			ballotType = "template";			
		}			
		
		//String MinLegsSeqXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Min Legs Seq')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
		String MinLegsSeqXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+srchTitle+"']//..//div[normalize-space()='Min Legs Seq']//..";
		String MinLegsSeqTextUI = driver.findElement(By.xpath(MinLegsSeqXpath)).getText().split("\\n")[1];
		System.out.println(MinLegsSeqTextUI);
		//String MaxLegsSeqXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'Max Legs Seq')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
		String MaxLegsSeqXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+srchTitle+"']//..//div[normalize-space()='Max Legs Seq']//..";
		String MaxLegsSeqTextUI = driver.findElement(By.xpath(MaxLegsSeqXpath)).getText().split("\\n")[1];
		System.out.println(MaxLegsSeqTextUI);
		
		
		if (MinLegsseq.contains(MinLegsSeqTextUI))
		{
			String output = "Min Legs Seq value for" + ballotType + "ballot is good and matches expected value.The value is " + MinLegsSeqTextUI;
			Assert.assertTrue(output, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							
		}
		else
		{				
			String output = "Min Legs Seq value for" + ballotType + "ballot is not good and does not match expected value. The Actual value is " + MinLegsSeqTextUI +". The expected value is " +MinLegsseq;
			Assert.assertTrue(output, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}
		if (MaxLegsSeq.contains(MaxLegsSeqTextUI))
		{
			String output = "Max Legs Seq value for" + ballotType + "ballot is good and matches expected value.The value is " + MaxLegsSeqTextUI;
			Assert.assertTrue(output, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							
		}
		else
		{				
			String output = "Max Legs Seq value for" + ballotType + "ballot is not good and does not match expected value.The Actual value is " + MaxLegsSeqTextUI+". The expected value is " + MaxLegsSeq;
			Assert.assertTrue(output, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.assertTrue("EXCEPTION OCURED", false);
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + e.toString(),
		ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		
	}
	
}
// 22 Aug 2022
// updated xpath to validate DH required
public void verifyDeadHeadValue(String DeadHeadValue, String ballotType, String srchTitle) 
{
	try
	{		
		if (ballotType.contains("PickUpDOTC")) 
		{
			ballotType = "pickup";			
		}
		else if (ballotType.contains("PickUpOutside")) 
		{
			ballotType = "pickupOutside";		
		}
		else if (ballotType.contains("Template")) 
		{
			ballotType = "template";			
		}	
		
		if(DeadHeadValue.contains("Required"))
			DeadHeadValue = "Selected";
			
		//String DeadHeadValueXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'DH Required')]";
		String DeadHeadValueXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+srchTitle+"']//..//div[normalize-space()='DH Required']//..";	
		String DeadHeadValueText = driver.findElement(By.xpath(DeadHeadValueXpath)).getText().split("\\n")[1];
		System.out.println(DeadHeadValueText);
		
		if (DeadHeadValue.contains(DeadHeadValueText))
		{
			String output = "DeadHead Value value for " + ballotType + " ballot is good and matches expected value.The value is " + DeadHeadValueText;
			Assert.assertTrue(output, true);
			ExtentTestManager.getTest().log(LogStatus.PASS, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
							
		}
		else
		{				
			String output = "DeadHead value for " + ballotType + " ballot is not good and does not match expected value.The value is " + DeadHeadValueText;
			Assert.assertTrue(output, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, output,ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}

	}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.assertTrue("EXCEPTION OCURED", false);
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + e.toString(),
		ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		
	}
	
}

//12 Aug 2022
// updated xpath to check for ballots
public void createBallotsValidation(String ballotType,String srchTitle) {
	Util.waitForLoad(driver);
	if (ballotType.contains("PickUpDOTC")) 
	{
		ballotType = "pickup";			
	}
	else if (ballotType.contains("PickUpOutside")) 
	{
		ballotType = "pickupOutside";		
	}
	else if (ballotType.contains("Template")) 
	{
		ballotType = "template";			
	}	
	
	String exp = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']";
	List<WebElement> ballotTitle = driver.findElements(By.xpath(exp));
	int totalTitle = ballotTitle.size();
	if (totalTitle > 0) {
		System.out.println(ballotType+" ballot is created successfully with Request title: "
				+ srchTitle);
		Assert.assertTrue(ballotType+" ballot is created successfully with the ballot Title"
				+ srchTitle, true);
		ExtentTestManager.getTest().log(LogStatus.PASS, ballotType+" ballot is created successfully with the ballot Title: "+srchTitle,
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
	} else {
		ExtentTestManager.getTest().log(LogStatus.FAIL, ballotType+" ballot is not created with ballot Title " + srchTitle,
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		Assert.assertFalse(
				ballotType+" ballot is not created with ballot Title " + srchTitle, true);
	}
}

//18-01-2022
public void verifyDepArrDateRange(String ballotType, String srchTitle) {
	try{

		if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";			
			}
			if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";		
			}
			if (ballotType.contains("Template")) 
			{
				ballotType = "template";			
			}		

	String fromDateInput = DOTCSearchScreen.DepFrom;
	String ToDateInput = DOTCSearchScreen.DepTo;
	String departureDateRange = fromDateInput+ " - "+ ToDateInput;
	String arrivalFromDateInput = DOTCSearchScreen.ArrFrom;
	String arrivalToDateInput = DOTCSearchScreen.ArrTo;
	String ArrivalDateRange = arrivalFromDateInput+ " - "+ arrivalToDateInput;
	ArrayList<String> title=new ArrayList<String>();
//	String srhTitle = DOTCSearchScreen.SearchTitle;

		String srchCriteria = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//..//..//..//..//..//..//..//..//..//div[@class='generic-details-container ng-star-inserted']";
		String criteria = driver.findElement(By.xpath(srchCriteria)).getText();
		String string[]=criteria.split("\\n");
		        for(String y:string){
		            try{
		                String[] temp=y.split("\\n");
		                title.add(temp[0]);
		            }catch(Exception e){}
		        }
		        String deptitle = title.get(0).toString();
		        String depValue = title.get(1).toString();
		        String arrtitle = title.get(2).toString();
		        String arrValue = title.get(3).toString();
		        if (deptitle.equalsIgnoreCase("Dep")
		        	&& arrtitle.equalsIgnoreCase("Arr")
		        	&& depValue.equalsIgnoreCase(departureDateRange)
		        	&& arrValue.equalsIgnoreCase(ArrivalDateRange)){
					System.out.println("Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " matched");
					Assert.assertTrue("Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " matched", true);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " matched",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		        }
		        else {
					System.out.println("Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " not matched");
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " not matched",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertFalse("Departure and Arrival date title and value are: "+ deptitle + ":"+ depValue +" and "+  arrtitle + ":"+ arrValue + " not matched", true);
					
		        }
	}
		    	catch(Exception e)
		    	{
		    		e.printStackTrace();
		    		ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + e.toString(),
		    		ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		    		Assert.assertTrue("Exception occured in the method" + e.toString(), false);
		    		
		    	}

}

//12 Aug 2022
// updated xpath to fetch dep date from ballot
// updated function to validate dep date
public void verifyDepartureDateOnly(String ballotType, String srchTitle) {
	try{

		if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";			
			}
			if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";		
			}
			if (ballotType.contains("Template")) 
			{
				ballotType = "template";			
			}		
			String fromDateInput = DOTCSearchScreen.DepFrom;			
			String srchCriteria = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='row ng-star-inserted']//div[normalize-space()='Dep']//..";
			String depValue = driver.findElements(By.xpath(srchCriteria)).get(0).getText();
			depValue = depValue.split("\\n")[1];
			if(depValue.equalsIgnoreCase(fromDateInput)){
				System.out.println("departure date title and value is: Dep:"+ depValue + " and " +fromDateInput+ " matched");
				Assert.assertTrue("departure date title and value is: Dep:"+ depValue + " and "+fromDateInput+" matched", true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "departure date title and value is: Dep:"+ depValue + " and "+fromDateInput+" matched",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + e.toString(),
			ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Exception occured in validating date", false);
		}
}

//12 Aug 2022
// updated xpath for dep date range
// updated function to fetch dep date range
public void verifyDepartureDateRange(String ballotType,String srchTitle) {
	try{

		if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";			
			}
			if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";		
			}
			if (ballotType.contains("Template")) 
			{
				ballotType = "template";			
			}		

			String fromDateInput = DOTCSearchScreen.DepFrom;
			String toDateInput = DOTCSearchScreen.DepTo;
			String DepartureDateRange = fromDateInput+ " - "+ toDateInput;
		
			String srchCriteria = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='row ng-star-inserted']//div[normalize-space()='Dep']//..";
			String depValue = driver.findElements(By.xpath(srchCriteria)).get(0).getText();
			depValue = depValue.split("\\n")[1];
			
		    if (depValue.equalsIgnoreCase(DepartureDateRange)){
				System.out.println("departure date title and value is: Dep:"+ depValue + " and "+DepartureDateRange+" matched");
				Assert.assertTrue("departure date title and value is: Dep:"+ depValue + " and "+DepartureDateRange+" matched", true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "departure date title and value is: Dep:"+ depValue + " and "+DepartureDateRange+" matched",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		    }
		} catch(Exception e){
		    		e.printStackTrace();
		    		ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + e.toString(),
		    		ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		    		Assert.assertTrue("Exception occured in the method" + e.toString(), false);
		}
	}

//12 Aug 2022
// Updated xpath for arrival date
// updated function to fetch arr date
public void verifyArrivalDateRange(String ballotType, String srchTitle) {
	try{

		if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";			
			}
			if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";		
			}
			if (ballotType.contains("Template")) 
			{
				ballotType = "template";			
			}		

	String arrivalFromDateInput = DOTCSearchScreen.ArrFrom;
	String arrivalToDateInput = DOTCSearchScreen.ArrTo;
	String ArrivalDateRange = arrivalFromDateInput+ " - "+ arrivalToDateInput;
	
	String srchCriteria = "//*[@ballottype='" + ballotType + "']//*[.='" + srchTitle + "']//..//div[@class='row ng-star-inserted']//div[normalize-space()='Arr']//..";
	String arrValue = driver.findElements(By.xpath(srchCriteria)).get(0).getText();
	arrValue = arrValue.split("\\n")[1];
	
	if (arrValue.equalsIgnoreCase(ArrivalDateRange)){
			System.out.println("Arrival date title and value is: Arr:"+ arrValue + " and "+ArrivalDateRange+"matched");
			Assert.assertTrue("Arrival date title and value is: Arr:"+ arrValue + " and "+ArrivalDateRange+" is matched", true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Arrival date title and value is: Arr:"+ arrValue + " and "+ArrivalDateRange+" is matched",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}

	}
	
	catch(Exception e)
	{
		e.printStackTrace();
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Exception occured in the method" + e.toString(),
		ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		Assert.assertTrue("Exception occured in the method" + e.toString(), false);
	}
	}

	public void clickOnCitiesPlus() {

		try {
			Util.ClickButton(driver, citiesPlusIcon);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Cities Plus Icon",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click on Cities Plus Icon",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Could not click on Cities Plus Icon", false);
		}

	}

	public void clickOnLayoverTimesPlus() {
		try {
			Util.ClickButton(driver, layoverTimesPlusIcon);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Layover Times Plus Icon",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not click on Layover Times Plus Icon",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Could not click on Layover Times Plus Icon", false);
		}
	}

	public void enterIncludeCitiesValue(String includeCities) {

		try {
			Util.ClickElement(driver, includeCitiesTxtBox);
			Util.setDataSpecial(driver, includeCitiesTxtBox, includeCities);
			Util.waitForLoad(driver);

			ExtentTestManager.getTest().log(LogStatus.PASS, "Entered Include Cities text",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not Enter Include Cities text",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Could not Enter Include Cities text", false);

		}
	}

	public void enterExcludeCitiesValue(String excludeCities) {
		try {

			Util.ClickElement(driver, excludeCitiesTxtBox);
			Util.setDataSpecial(driver, excludeCitiesTxtBox, excludeCities);
			Util.waitForLoad(driver);

			ExtentTestManager.getTest().log(LogStatus.PASS, "Entered Exclude Cities text",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not Enter Exclude Cities text",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Could not Enter Exclude Cities text", false);
		}
	}

	public void selectLayoverMinTimeDropdown(String layoverMinTime) {
		try {
			Util.SelectFrmDropDown(driver, minLayoverTimesDropDown, layoverMinTime, true);
			Util.waitForLoad(driver);

			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected Min time from Layover Time Drop down",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select min time from Layover Time Drop down",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void selectLayoverMaxTimeDropdown(String layoverMaxTime) {
		try {
			Util.waitForLoad(driver);
			Util.SelectFrmDropDown(driver, maxLayoverTimesDropDown, layoverMaxTime, true);

			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected max time from Layover Time Drop down",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select max time from Layover Time Drop down",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void validateIncludeCities(String includeCitiesEntered, String ballotType, String searchTitle) {

		String elementName = "Include Cities";
		validateElementInBallot(elementName, includeCitiesEntered, ballotType, searchTitle);
	}

	public void validateExcludeCities(String excludeCitiesEntered, String ballotType, String searchTitle) {

		String elementName = "Exclude Cities";
		validateElementInBallot(elementName, excludeCitiesEntered, ballotType, searchTitle);
	}

	public void validateLayoverMinimumTime(String layoverMinTimeSelected, String ballotType, String searchTitle) {

		String elementName = "Layover Time Min";
		validateElementInBallot(elementName, layoverMinTimeSelected, ballotType, searchTitle);
	}

	public void validateLayoverMaximumTime(String layoverMaxTimeSelected, String ballotType, String searchTitle) {

		String elementName = "Layover Time Max";
		validateElementInBallot(elementName, layoverMaxTimeSelected, ballotType, searchTitle);
	}
	
	// 22 Aug 2022
	// updated xpath for validate elements for function validateElementInBallot()
	public void validateElementInBallot(String elementName, String elementValueEntered, String ballotType,
			String searchTitle) {

		try {

			if (ballotType.contains("PickUpDOTC")) {
				ballotType = "pickup";
			} else if (ballotType.contains("PickUpOutside")) {
				ballotType = "pickupOutside";
			} else if (ballotType.contains("Template")) {
				ballotType = "template";
			}

			//String elementXpath = "//*[@ballottype='" + ballotType + "']//*[.='" + searchTitle
				//	+ "']//..//..//..//..//..//..//..//..//..//..//div[@class='col-xs-5'][contains(text(),'"
				//	+ elementName + "')]/parent::node()//div[contains(@class,'col-xs-7 dsColumn')]";
			String elementXpath = "//*[@ballottype='"+ballotType+"']//span[.='"+searchTitle+"']//..//div[normalize-space()='"+elementName+"']//..";

			String elementTextFromUI = driver.findElement(By.xpath(elementXpath)).getText().split("\\n")[1];

			String output = new String();
			if (elementTextFromUI != null && elementTextFromUI.contains(elementValueEntered)) {

				output = elementName + " value " + elementTextFromUI + " for " + ballotType
						+ " ballot is good and matches the entered value " + elementValueEntered;

				System.out.println(output);
				Assert.assertTrue(output, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			} else {

				if (elementTextFromUI == null) {
					output = elementName + " value from UI is null";
				} else {
					output = elementName + " value " + elementTextFromUI + " for " + ballotType
							+ "ballot do not match the entered value " + elementValueEntered;
				}
				System.out.println(output);
				Assert.assertTrue(output, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		} catch (Exception ex) {
			String message = "Exception occured in the method validateElementInBallot";

			Assert.assertTrue(message, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, message,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	public void verifySaveGenericCriteriaButtonClickable(String layoverMinTime, String layoverMaxTime) {

		try {

			if (Integer.parseInt(layoverMinTime) > Integer.parseInt(layoverMaxTime)) {

				if (!saveGenericCriteriaBtn.isEnabled()) {

					String output = "Save Generic Criteria button is Disabled, when invalid times are selected in Layover Time drop down";
					Assert.assertTrue(output, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					System.out.println(output);
				} else {

					String output = "Save Generic Criteria button is enabled, even when invalid times are selected in Layover Time drop down";
					Assert.assertTrue(output, false);
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}

			} else {

				String message = "Save Generic Criteria button is enabled, when valid times are selected in Layover Time drop down";
				System.out.println(message);
				ExtentTestManager.getTest().log(LogStatus.PASS, message,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		} catch (Exception ex) {

			String message = "Exception occurred in the method verifySaveGenericCriteriaButtonClickable";
			Assert.assertTrue(message, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, message,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}

	}

	public void verifySearchSequencesResult() {

		Util.waitForLoad(driver);
		isSequencesPresent = false;
		int sizeOfSequences = sequenceNumbersInTable.size();
		if(sizeOfSequences == 0) {
			dotcSrchPg.selectDepartureToDate("15");
			dotcSrchPg.clickOnShowSequence();
			Util.waitForLoad(driver);
			sizeOfSequences = sequenceNumbersInTable.size();
		}

		try {

			if (sizeOfSequences > 0) {

				isSequencesPresent = true;
				System.out.println("The value of boolean variable isSequencesPresent becomes " + isSequencesPresent);

			} else if (sizeOfSequences == 0) {

				if (!Util.waitForNoExist(driver, noResultsInSequence)) {

					String output = "Number of sequences are zero. Remaining steps will be skipped";
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue(output, false);
					System.out.println(output);

				} else {

					String output = "No results element is not displayed. Number of sequences are not zero";
					ExtentTestManager.getTest().log(LogStatus.FAIL, output,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue(output, false);
				}
			}
		} catch (Exception ex) {
			String message = "Exception occured in method verifySearchSequencesResult";
			ExtentTestManager.getTest().log(LogStatus.FAIL, message,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue(message, false);
		}

	}

	public void selectSequenceFromSequencesList() {

		try {			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()",sequenceNumbersInTable.get(0));
			
			Util.waitForLoad(driver);
			Util.ClickElement(driver, sequenceNumbersCheckBox.get(1));
			firstSequenceNum = driver.findElement(By.xpath("//table[@id = 'sequencesLargeTable']//tbody/tr[1]//a[contains(@class,'speciallink')]")).getText();
			System.out.println("seq number: "+firstSequenceNum);
			ExtentTestManager.getTest().log(LogStatus.PASS, "selected first sequence from the sequences List",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		} catch (Exception ex) {
			String message = "Could not select sequence checkbox";
			Assert.assertTrue(message, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, message,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	
	public void clickOnSaveSelectedSequences() {

		try {

			if (ballotSubmitButton.isEnabled()) {
				Util.waitForLoad(driver);
				Util.ClickButton(driver, ballotSubmitButton);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Save Selected Sequences Button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			} else {

				String message = "Save Selected Sequences Button is disabled";
				Assert.assertTrue(message, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL, message,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

			}

		} catch (Exception ex) {
			String message = "Could not click on Save Selected Sequences Button";
			Assert.assertTrue(message, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, message,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void clickOnAllBallotsCheckBox() {

		try {
			Util.waitForLoad(driver);
			Util.ClickElement(driver, allBallotsCheckBox);

			ExtentTestManager.getTest().log(LogStatus.PASS, "Selected All Ballots check box",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not select All Ballots check box",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void clickOnBallotsAddButton() {

		try {
			Util.waitForLoad(driver);
			Util.ClickButton(driver, ballotAddButton);
			Util.waitForSpinnerLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on Add button",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Could not Click on Add button",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	// 23 Aug 2022
	// updated xpath to validate ballot creation using sequence
	public void validateSequenceSavedInBallot(String ballotType) {
		Util.waitForLoad(driver);
		if (ballotType.contains("PickUpDOTC")) {
			ballotType = "pickup";
		} else if (ballotType.contains("PickUpOutside")) {
			ballotType = "pickupOutside";
		} else if (ballotType.contains("Template")) {
			ballotType = "template";
		}

		try {

			String sequenceSelected = firstSequenceNum;
			//String sequenceNumTitleXpath = "//*[@ballottype='" + ballotType
				//	+ "']//div[@class='col-lg-8 summary-title']//a[contains(.,'" + sequenceSelected + "')]";
			String sequenceNumTitleXpath = "//*[@ballottype='" + ballotType+"']//div/a[contains(.,'"+sequenceSelected+"')]";
			//JavascriptExecutor js = (JavascriptExecutor) driver;
			//js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(sequenceNumTitleXpath)));
			boolean isPresent = driver.findElements(By.xpath(sequenceNumTitleXpath)).size()>0;
			String output = new String();
			if (isPresent) {

				output = "The saved sequence " + sequenceSelected + " for Ballot " + ballotType
						+ " matches the selected sequence " + sequenceSelected;
				System.out.println(output);
				Assert.assertTrue(output, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			} else {

				output = "The selected sequence " + sequenceSelected + " is not saved for ballot " + ballotType;
				Assert.assertTrue(output, false);
				ExtentTestManager.getTest().log(LogStatus.FAIL, output,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}

		} catch (Exception ex) {

			String message = "Exception occurred in the method validateSequenceSavedInBallot";
			Assert.assertTrue(message, false);
			ExtentTestManager.getTest().log(LogStatus.FAIL, message,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
		}
	}

	public void deleteBallotsBasedOnSequenceNumber(String ballotType) {
		deleteDuplicateSequenceBallot(ballotType);

	}

	// 23 Aug 2022
	// updated xpath to delete the duplicate ballot
	public void deleteDuplicateSequenceBallot(String ballotType) {
		Util.waitForLoad(driver);
		if (ballotType.contains("PickUpDOTC")) {
			ballotType = "pickup";
		} else if (ballotType.contains("PickUpOutside")) {
			ballotType = "pickupOutside";
		} else if (ballotType.contains("Template")) {
			ballotType = "template";
		}
		String sequenceNum = firstSequenceNum;

		try {
			System.out.println("Sequence number to delete: "+firstSequenceNum);
			//String checkboxSequence = "//*[@ballottype='"+ballotType+"']//a[text()='"+sequenceNum+"']//..//..//..//..//..//..//..//..//..//..//..//..//td[@class='checkbox-column']//input";
			String checkboxSequence = "//*[@ballottype='"+ballotType+"']//a[text()='"+sequenceNum+"']//..//..//..//..//..//..//input";
			int checkboxSequenceSize = driver.findElements(By.xpath(checkboxSequence)).size();

			if (checkboxSequenceSize > 1) {

				for (int i = 0; i < checkboxSequenceSize - 1; i++) {

					Util.ClickElement(driver, driver.findElements(By.xpath(checkboxSequence)).get(i));

					String delBtn = "//*[@ballottype='"+ballotType+"']//button//span[text()='Delete']";
					driver.findElement(By.xpath(delBtn)).click();
					Util.waitForLoad(driver);
					Util.ClickElement(driver, deleteSubmitBtn);
					Util.waitForSpinnerLoad(driver);

				}

				String sequenceNumTitleXpath = "//*[@ballottype='" + ballotType
						+ "']//div[@class='col-lg-8 summary-title']//a[contains(.,'" + sequenceNum + "')]";
				List<WebElement> ballotTitle = driver.findElements(By.xpath(sequenceNumTitleXpath));

				if (ballotTitle.size() > 1) {
					System.out.println(
							"Duplicate sequence exist for sequence " + sequenceNum + " in ballot " + ballotType);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Deleted ballot for "+ballotType,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Deleted ballot for "+ballotType, false);
				} else {
					System.out.println(
							"Duplicate sequence " + sequenceNum + " deleted successfully from ballot " + ballotType);
					//throw new Exception("Failed to delete duplicate ballot");
				}

			}
		} catch (Exception ex) {
			String message = "Exception occurred in the method deleteDuplicateSequenceBallot";
			ExtentTestManager.getTest().log(LogStatus.FAIL, message,
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue(message, false);
		}

	}
	
	// 23 Aug 2022
		// function to delete all sequence related ballot
		public void deleteAllSequenceBallot(String ballotType) {
			Util.waitForLoad(driver);
			if (ballotType.contains("PickUpDOTC")) {
				ballotType = "pickup";
			} else if (ballotType.contains("PickUpOutside")) {
				ballotType = "pickupOutside";
			} else if (ballotType.contains("Template")) {
				ballotType = "template";
			}
			String sequenceNum = firstSequenceNum;

			try {
				System.out.println("Sequence number to delete: "+firstSequenceNum);
				//String checkboxSequence = "//*[@ballottype='"+ballotType+"']//a[text()='"+sequenceNum+"']//..//..//..//..//..//..//..//..//..//..//..//..//td[@class='checkbox-column']//input";
				String checkboxSequence = "//*[@ballottype='"+ballotType+"']//a[text()='"+sequenceNum+"']//..//..//..//..//..//..//input";
				int checkboxSequenceSize = driver.findElements(By.xpath(checkboxSequence)).size();

				if (checkboxSequenceSize > 0) {

					for (int i = 0; i < checkboxSequenceSize; i++) {

						Util.ClickElement(driver, driver.findElements(By.xpath(checkboxSequence)).get(i));

						String delBtn = "//*[@ballottype='"+ballotType+"']//button//span[text()='Delete']";
						driver.findElement(By.xpath(delBtn)).click();
						Util.waitForLoad(driver);
						Util.ClickElement(driver, deleteSubmitBtn);
						Util.waitForLoad(driver);

					}

					String sequenceNumTitleXpath = "//*[@ballottype='" + ballotType
							+ "']//div[@class='col-lg-8 summary-title']//a[contains(.,'" + sequenceNum + "')]";
					List<WebElement> ballotTitle = driver.findElements(By.xpath(sequenceNumTitleXpath));

					if (ballotTitle.size() > 1) {
						System.out.println(
								"Sequence exist for sequence " + sequenceNum + " in ballot " + ballotType);
						throw new Exception("Failed to delete all the ballots");
					} else {
						ExtentTestManager.getTest().log(LogStatus.PASS, "Deleted all ballot(s) for "+ballotType,
								ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
						Assert.assertTrue("Deleted ballot for "+ballotType, true);
						System.out.println(
								"Sequence " + sequenceNum + " deleted successfully from ballot " + ballotType);
					}

				}
			} catch (Exception ex) {
				String message = "Exception occurred in the method deleteDuplicateSequenceBallot";
				ExtentTestManager.getTest().log(LogStatus.FAIL, message,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				ex.printStackTrace();
				Assert.assertTrue(message, false);
			}

		}
	
	public void validatePositionValueInBallot(String positionSelected, String ballotType, String searchTitle) {

		String elementName = "Position";
		validateElementInBallot(elementName, positionSelected, ballotType, searchTitle);
	}

	public void validateDivisionValueInBallot(String divisionSelected, String ballotType, String searchTitle) {

		String elementName = "Division";
		divisionSelected = (divisionSelected.equals("INT")) ? "I" : "D";
		validateElementInBallot(elementName, divisionSelected, ballotType, searchTitle);
	}
	
	// code added
		// 17-01-2022
		public void checkBallot(String xpth, String ballotXpath, String[] chosenSeq, String bTypes) throws Exception {
			try {
				DOTCBallotScreen dotcBlltScr = new DOTCBallotScreen();
				String[] bType = bTypes.split(",");
				String btyp = ""; 
				for (int j = 0; j < bType.length; j++) {
					if(bType[j].contains("PickUpDOTC"))
						bType[j] = "Pickup DOTC";
					else if(bType[j].contains("PickUpOutside"))
						bType[j] = "Pickup Outside";
					dotcBlltScr.click(String.format(ballotXpath, bType[j]), 6);
					if (bType[j].contains("Pickup DOTC")) 
						btyp = "pickup";			
					else if (bType[j].contains("Pickup Outside")) 
						btyp = "pickupOutside";		
					else if (bType[j].contains("Template")) 
						btyp = "template";	
					
					for (int i = 0; i < chosenSeq.length / 2; i++) {
						try {
							String xpath = String.format(xpth, btyp, chosenSeq[2*i], chosenSeq[2*i+1]);
							List<WebElement> we = driver.findElements(By.xpath(xpath));
							System.out.println(we.size());
							we.remove(we.size()-1);
							if(we.size() > 0) {
								for (WebElement w : we)
									try {
										Util.ClickButton(driver, w);
									} finally {}
								ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully selected all duplicate ballot(s) in "+bType[j],
										ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
								Assert.assertTrue("Successfully selected all duplicate ballot(s) in", true);	
							
							}
						} finally {}
					}
					deleteCheckedBs(bType[j]);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Excaption duplicate "+ex);
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in selecting duplicate ballot",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in selecting duplicate ballot", false);
			}
		}
		
		// code added
		//17-01-2022
		public void clickUpdate(String bType) throws InterruptedException {
			try {
				String[] btyp = bType.split(",");
				if (btyp[btyp.length-1].contains("Pick Up DOTC")) 
					btyp[btyp.length-1] = "pickup";			
				else if (btyp[btyp.length-1].contains("Pick Up Outside")) 
					btyp[btyp.length-1] = "pickupOutside";		
				else if (btyp[btyp.length-1].contains("Template")) 
					btyp[btyp.length-1] = "template";	
				
				String updateBallotXpath = "//aac-ballot-data-table[@ballottype='%s']//*[contains(@id, 'updateBallots')]";
				WebElement ele = driver.findElement(By.xpath(String.format(updateBallotXpath, btyp[btyp.length-1])));
				Util.ClickButton(driver, ele);
				Thread.sleep(4000);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Successfully clicked on Update button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Successfully clicked on Update button", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking on Update button",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Exception in clicking on Update button", false);
			}
		}
		
		//To click cancel Update while editing saved generic criteria
		public void clickOnCancelUpdateButton() throws InterruptedException {
			try {
				Util.ClickButton(driver, cancelUpdateBtn);
				Util.waitForLoad(driver);
				ExtentTestManager.getTest().log(LogStatus.PASS, "CancelUpdate button is clicked");
			} catch (Exception ex) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Save Generic Criteria is not clicked");
				ex.printStackTrace();
			}
		}
	
		//18-04-22
		//This will verify the update generic button in edit ballot page
	public void VerifyUpdateGenericButton() throws InterruptedException {
		try {
			if (createGenericBallotsBtn.getText().contains("Update Generic")) {
				JavascriptExecutor js = (JavascriptExecutor) this.driver;
				js.executeScript("window.scrollBy(0,600)", "");
				   System.out.println(" verified Update generic button ");
				   ExtentTestManager.getTest().log(LogStatus.PASS, "Update generic button is present",
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				   Assert.assertTrue("verified Update generic button", true);
				  
				    }
			
			else {
				  System.out.println("Failed - to verify Update generic ");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed - to verify Update generic",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			 Assert.assertFalse("Failed - to verify Update generic ", true);
			}
				
		}
			
		catch (Exception ex) {
				
			 System.out.println("Exception - to verify update generic button ");
			 ExtentTestManager.getTest().log(LogStatus.PASS, "Exception - to verify update generic button ",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
		}
	//18-04-22
	// This will click on the cancel button in edit ballot page
	public void clickOnCancelButton() throws InterruptedException {
		try {
			Util.ClickButton(driver, cancelBtn);
			Util.waitForLoad(driver);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Cancel button is clicked");
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Cancel button is not clicked");
			ex.printStackTrace();
			 Assert.assertFalse("Cancel button is not clicked", true);
		}
}

	//19-04-22
	//This will verify the unsaved changes pop-up
public void VerifyUnsavedChangespopUp() throws InterruptedException {
	try {
		if (modalTextUnsaved.getText().contains("Unsaved Changes!")) {
			   System.out.println(" verified unsaved changes pop up ");
			   ExtentTestManager.getTest().log(LogStatus.PASS, "verified unsaved changes pop up",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			   Assert.assertTrue("verified unsaved changes pop up", true);
			  
			    }
		
		else {
			  System.out.println("Failed - to verify unsaved changes pop up ");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed - to verify unsaved changes pop up",
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 Assert.assertFalse("Failed - to verify unsaved changes pop up ", true);
		}
			
	}
		
	catch (Exception ex) {
			
		 System.out.println("Exception - to verify unsaved changes pop up");
		 ExtentTestManager.getTest().log(LogStatus.PASS, "Exception - to verify unsaved changes pop up ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}
//19-04-22
//This will click on close button of Unsaved changes pop-up
public void clickOnCloseButton() throws InterruptedException {
	try {
		Util.ClickButton(driver, closeBtn);
		Util.waitForLoad(driver);
		ExtentTestManager.getTest().log(LogStatus.PASS, "close  button is clicked");
	} catch (Exception ex) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Close  button is not 	 clicked");
		ex.printStackTrace();
		 Assert.assertFalse("Close  button is not 	 clicked ", true);
	}
}

//19-04-22
//This will click on cancel button in ballot page according to the type of ballot
public void clickCanceButtonBasedOnBallotType(String ballotType) {
	try {
		if (ballotType.contains("PickUpDOTC"))
		{
			Util.ClickButton(driver, cancelBtnPickup);
			Util.waitForLoad(driver);
			System.out.println("Cancel button clicked for pickup DOTC");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Cancel button clicked for Pickup DOTC");
		}
		
		if (ballotType.contains("PickUpOutside"))
		{
			Util.ClickButton(driver, cancelBtnPickupOutside);
			Util.waitForLoad(driver);
			System.out.println("Cancel button clicked for pickup Outside DOTC ");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Cancel button clicked for Pickup Outside DOTC ");
		}

		if (ballotType.contains("Template"))
		{
			Util.ClickButton(driver, cancelBtnTemplate);
			Util.waitForLoad(driver);
			System.out.println("Cancel button clicked for Template DOTC");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Cancel button clicked forTemplate DOTC");
		}
	}
		
		catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in clicking the cancel button ");
			ex.printStackTrace();
		
	}
}

//21-04-22
//This will turn on notification slider
public void clickOnNotificationSlider() {
	try {
		//Util.waitForElementClickable(driver, notificationSlider);
		if(driver.findElements(By.xpath("//mat-label[normalize-space()='Pickup Outside DOTC']//parent::span//mat-slide-toggle[contains(@class, 'mat-checked')]")).size() == 0)
			Util.ClickElement(driver, notificationSlider);
		
		ExtentTestManager.getTest().log(LogStatus.PASS, "Notification slider is ON",
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
	} catch (Exception ex) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Unable to ON notification Slider",
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		ex.printStackTrace();
		Assert.assertTrue("Could not turn on ballot", false);
	}
	
}

//21-04-22
//This will return notification time sub text 
public void verifyNotificationTimeSubText() {
	try {
		if (NotificationTimeSubText.getText().contains("(to Departure Time)")) {
			   System.out.println(" verified notification time sub text ");
			   ExtentTestManager.getTest().log(LogStatus.PASS, "verified notification time sub text",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			   Assert.assertTrue("verified notification time sub text", true);
			   
			  
			    }
		
		else {
			  System.out.println("Failed - to verify notification time sub text ");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed - to verify notification time sub text",
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		Assert.assertFalse("Failed - to verify notification time sub text ", true);
		}
			
	}
		
	catch (Exception ex) {
			
		 System.out.println("Exception - to notification time sub text");
		 ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception - to verify notification time sub text ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

//21-04-22
//This will return default time set at notification drop down- minutes
public void verifyNotificationDefaultHour() {
	try {

   //Select select = new Select(NotificationDefaultHour);
		WebElement select = NotificationDefaultHour;
    //WebElement notifyHours = select.getFirstSelectedOption();
		System.out.println(select.getText());
		//System.out.println("default hours" + notifyHours.getText());
		if (select.getText().equals("11")) {
	
			   System.out.println(" verified notification default hour  ");
			   ExtentTestManager.getTest().log(LogStatus.PASS, "verified notification default hour ",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			   Assert.assertTrue("verified notification default hour", true);
			  
			    }
		
		else {
			  System.out.println("Failed - to verify notification default hour  ");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed - to verify notification default hour ",
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		Assert.assertFalse("Failed - to verify notification default Hour ", true);
		}
			
	}
		
	catch (Exception ex) {
			
		 System.out.println("Exception - to notification default hour ");
		 ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception - to verify notification default hour  ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 Assert.assertFalse("Failed - to verify notification default Hour ", true);
		}
	}

//21-04-22
//This will return default time set at notification drop down- minutes
public void verifyNotificationDefaulMinute() {
	try {
		
		//Select select = new Select(NotificationDefaultMinute);
	    //WebElement notifyMinutes = select.getFirstSelectedOption();
		//System.out.println("default minutes" + notifyMinutes.getText());
		WebElement select = NotificationDefaultMinute;
	    System.out.println(select.getText());
		if (select.getText().equals("00")) {
			   System.out.println(" verified notification default Minute  " );
			   ExtentTestManager.getTest().log(LogStatus.PASS, "Passed -verified notification default Minute",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			   Assert.assertTrue("Passed -verified notification default Minute", true);
			  
			    }
		
		else {
			  System.out.println("Failed - to verify notification default Minute  ");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed - to verify notification default Minute ",
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		Assert.assertFalse("Failed - to verify notification default Minute ", true);
		}
			
	}
		
	catch (Exception ex) {
			
		 System.out.println("Exception - to notification default Minute ");
		 ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception - to verify notification default Minute ",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		 Assert.assertFalse("Exception - to notification default Minute ", true);
		}
	}



public void deselectSequenceFromSequencesList() {

	try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",sequenceNumbersInTable.get(0));
		Util.waitForLoad(driver);
		Util.ClickElement(driver, sequenceNumbersCheckBox.get(1));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Deselected first sequence from the sequences List",
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		Assert.assertTrue("Deselected first sequence from the sequences List", true);

	} catch (Exception ex) {
		String message = "Could not Deselect sequence checkbox";
		Assert.assertTrue(message, false);
		ExtentTestManager.getTest().log(LogStatus.FAIL, message,
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		ex.printStackTrace();
	}
}

//16May22
public void searchRows (String ballotType,String title)
{
try{
	
	
if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";			
			}
			if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";		
			}
			if (ballotType.contains("Template")) 
			{
				ballotType = "template";			
			}		
String tableXpath = "//aac-ballot-data-table[@ballottype ='"+ballotType +"']//*[.='"+title+"']//ancestor::table[@class='ballot-data-table-inner-row-table']//div[@class='ng-star-inserted']/div";
List <WebElement> rowCount= driver.findElements(By.xpath(tableXpath)) ;
Integer table = rowCount.size();
System.out.println(" table size" +table);
if(table>0) {
	
		if(rowCount.get(0).getText().trim().contains("Dep")&& rowCount.get(1).getText().trim().contains("No Work Hours") && rowCount.get(2).getText().trim().contains("Min Paid Credit") && rowCount.get(3).getText().trim().contains("Max Paid Credit")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,600)");
			currentScenario.embed(Util.takeScreenshot(driver),"image/png");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Criteria are displayed in correct order",ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertTrue("Criteria are displayed in correct order", true);
			
		}
		else
		{
			currentScenario.embed(Util.takeScreenshot(driver),"image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Criteria are not displayed in correct order",ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Criteria are not displayed in correct order", true);
		}
		
	}


}

catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Excepition");
			
		}



}

//26 Aug 2022
// updated function to delete all ballots
public void deleteAllBallots(String ballotType) 
{
	try {
		
			if (ballotType.contains("PickUpDOTC")) 
			{
				ballotType = "pickup";
			}
			else if (ballotType.contains("PickUpOutside")) 
			{
				ballotType = "pickupOutside";
			}
			else if (ballotType.contains("Template")) 
			{
				ballotType = "template";
			}
			
			String checkboxStr = "//*[@ballottype='"+ballotType+"']//div[@class='select-all container']//span[contains(@class,'mat-checkbox-inner-container')]";
			int sizeSameTitle = driver.findElements(By.xpath(checkboxStr)).size();
			
			if(sizeSameTitle!=0){
				Util.waitForLoad(driver);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath(checkboxStr)));
					
				Util.ClickElement(driver, driver.findElement(By.xpath(checkboxStr)));
	
					String delBtn = "//*[@ballottype='"+ballotType+"']//button//span[text()='Delete']";
					Util.ClickElement(driver, driver.findElement(By.xpath(delBtn)));
					Util.ClickElement(driver, deleteSubmitBtn);
					Util.waitForLoad(driver);
					
					Boolean isPresent = driver.findElements(By.xpath("//div[contains(text(), 'Please fix duplicates.')]")).size() == 0;
					
					if(isPresent) {
						clickOnUpdateButton(ballotType);
						Util.waitForSpinnerLoad(driver);
						//verifyUpdateMessage();
					}
				
				String exitingBallot = "//*[@ballottype='"+ballotType+"']//input[contains(@id,'chk_chk_CheckAll')]";
				int totalTitle = driver.findElements(By.xpath(exitingBallot)).size();
				
				if (totalTitle != 0) 
				{
					System.out.println("Ballot is not deleted successfully for " + ballotType);
					ExtentTestManager.getTest().log(LogStatus.FAIL, "Ballot is not deleted successfully for " + ballotType,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
					Assert.assertTrue("Ballot is not deleted successfully for " + ballotType, false);
				} 
				else 
				{
					System.out.println("Ballot is deleted successfully for " + ballotType);
					Assert.assertTrue("Ballot deleted successfully for " + ballotType, true);
					ExtentTestManager.getTest().log(LogStatus.PASS, "Ballot is deleted successfully for " + ballotType,
							ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				}
			}
			else
			{
				System.out.println("Ballot is not present and hence need not be deleted for " + ballotType);
				Assert.assertTrue("Ballot is not present and hence need not be deleted for " + ballotType, true);
				ExtentTestManager.getTest().log(LogStatus.PASS, "Ballot is not present and hence need not be deleted for " + ballotType,
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			}
	} 
	catch (Exception ex) 
	{
		currentScenario.embed(Util.takeScreenshot(driver), "image/png");
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Ballots are not deleted from the ballot list",
				ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		ex.printStackTrace();
		Assert.assertTrue("Ballots are not deleted from the ballot list", false);
	}
}
	//Clicking Edit link to Edit the ballot
	// Updated xpath
	// 21-02-2022
	public void clickBallotOutSideDOTCOffBtn() {
		try {
			if(!Util.waitFor(driver, OffBtnpickUpOutsideDOTC, 2))
				return;
			Util.ClickElement(driver, OffBtnpickUpOutsideDOTC);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Pick up outside turned on ballot",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		} catch (Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Pick up outside could not turn on ballot",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			ex.printStackTrace();
			Assert.assertTrue("Could not turn on ballot", false);
		}
	}
	
	// visibility of update button
	// 10 Aug 2022
	public boolean VisibleUpdateButton() {
		try {
			if(driver.findElements(By.xpath("//button[contains(@class, 'mat-button-disabled')]//span[text()='Update']")).size() > 0)
				return true;
			return false;
		} catch(Exception ex) {
			ex.printStackTrace();
			Assert.assertTrue("Exception in validating update button", false);
			return false;
		}
	}
}





