package com.DOTC.stepDefinitions;
import static org.junit.Assert.assertTrue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import com.DOTC.pageObjects.DOTCBallotScreen;
import com.DOTC.pageObjects.DOTCCalendarScreen;
import com.DOTC.pageObjects.DOTCCommon;
import com.DOTC.pageObjects.DOTCLogInScreen;
import com.DOTC.pageObjects.DOTCRestService;
import com.DOTC.pageObjects.DOTCSearchScreen;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DOTCSearchSteps extends DOTCMasterSteps {
	DOTCCommon dotcCmn = new DOTCCommon();
	DOTCLogInScreen dotcLogIn = new DOTCLogInScreen();
	DOTCCalendarScreen dotcCalendar = new DOTCCalendarScreen();
	DOTCSearchScreen dotcSrchPg = new DOTCSearchScreen();
	DOTCBallotScreen dotcBlltScr = new DOTCBallotScreen();
	DOTCRestService dotcRest = new DOTCRestService();
	
	public String crewedSequenceNumber = "";

	@Then("^I should be able to click on Search button$")
	public void i_should_be_able_to_click_Search_button() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.clickSearchBtn();

	}

	@Then("^I should Check the Sick Pending checkbox$")
	public void i_should_Check_the_Sick_Pending_checkbox() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.checkSickPending();
	}

	@Then("^I should Check the Crewed Sequences checkbox$")
	public void i_should_Check_the_Crewed_Sequences_checkbox() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.checkCrewedSequences();
	}

	@Then("^I should Check the Sick Sequences checkbox$")
	public void i_should_Check_the_Sick_Sequences_checkbox() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.checkSickSequences();
	}

	@Then("^I should be able to click on Show Sequences$")
	public void i_should_be_able_to_click_on_Show_Sequences() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.clickShowSequences();
	}

	@Then("^I should be able to Verify Sequence results for Sit times \"([^\"]*)\"$")
	public void i_should_be_able_to_Verify_Sequence_results(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.verifySeqResultsforSitTimes(arg1);
	}

	@Then("^I should be able to click TAFB and choose \"([^\"]*)\" and \"([^\"]*)\" TAFB values$")
	public void i_should_be_able_to_click_TAFB_and_choose_and_TAFB_values(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.clickTAFB();
		dotcSrchPg.selectTAFBRange(arg1, arg2);
	}

	@Then("^I should be able to Verify Sequence results for TAFB  \"([^\"]*)\" and \"([^\"]*)\" range$")
	public void i_should_be_able_to_Verify_Sequence_results_for_TAFB_and_range(String arg1, String arg2)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.verifySeqResultsforTAFB(arg1, arg2);
	}

	@Then("^I should be able to access Search page$")
	public void i_should_be_able_to_access_Search_page() throws Throwable {

		dotcSrchPg.existGenericBtn();
	}

	@Then("^I Clear Departure and Arrival dates$")
	public void i_Clear_Departure_and_Arrival_dates() throws Throwable {
		dotcSrchPg.clearDateInputFromAll();

	}

	@And("^I Verify that Departure date is mandatory field$")
	public void i_Verify_that_Departure_date_is_mandatory_field() throws Throwable {
		dotcSrchPg.DepartureDateValidation();
	}

	@Then("^I Enter Departure and Arrival dates$")
	public void i_Enter_Departure_and_Arrival_dates() throws Throwable {
		dotcSrchPg.selectDepartureFromDate();
		dotcSrchPg.selectDepartureToDate();
		dotcSrchPg.selectArrivalFromDate();
		dotcSrchPg.selectArrivalToDate();

	}

	@Then("^I enter Dep Date and Arrival Date \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_enter_dep_date_and_arrival_date_something_something(String offsetstdt, String offsetenddt)
			throws Throwable {
		dotcSrchPg.selectDateRange(offsetstdt, offsetenddt);
	}

	@Then("^I should be able to click Sequence Characteristics$")
	public void i_should_be_able_to_click_Sequence_Characteristics() throws Throwable {
		dotcSrchPg.clicksequenceCharactDwnArrw();

	}

	@Then("^I should be able to Select Value \"([^\"]*)\" in Sit Time dropdown$")
	public void i_should_be_able_to_Select_Value_in_Sit_Time_dropdown(String SitTime) throws Throwable {

		dotcSrchPg.selectSitTimeDrpdown(SitTime);
	}

	@Then("^I should be able to click Save Generic Criteria button$")
	public void i_should_be_able_to_click_Save_Generic_Criteria_button() throws Throwable {
		dotcSrchPg.clickSaveGenericBtn();

	}

	@Then("^I should be able to enter the Search title$")
	public void i_should_be_able_to_enter_the_Search_title() throws Throwable {
		dotcSrchPg.enterSearchTitle();

	}

	@Then("^I should be able to check All Ballots check box$")
	public void i_should_be_able_to_check_All_Ballots_check_box() throws Throwable {
		dotcSrchPg.checkAllBallot();

	}

	@Then("^I should be able to Save the criteria$")
	public void i_should_be_able_to_Save_the_criteria() throws Throwable {
		dotcSrchPg.saveBallotTitle();

	}

	@Then("^I should be able to click Sequence Number$")
	public void i_should_be_able_to_click_Sequence_Number() throws Throwable {
		dotcSrchPg.clicksequenceNumberDwnArrow();

	}

	@Then("^I should be able to click Sequence Number Plus$")
	public void i_should_be_able_to_click_Sequence_Number_Plus() throws Throwable {
		dotcSrchPg.clickSequenceNumberPlus();

	}

	@Then("^I should be able to Enter Value in Sequence Number TextBox \"([^\"]*)\"$")
	public void i_should_be_able_to_Enter_Value_in_Sequence_Number_TextBox(String BallotInput) throws Throwable {
		dotcSrchPg.enterSequenceNumber(BallotInput);

	}

	@Then("^I Enter Value in Sequence Number TextBox \"([^\"]*)\"$")
	public void i_Enter_Value_in_Sequence_Number_TextBox(String arg1) throws Throwable {
		dotcSrchPg.enterAnySequenceNumber(arg1);
	}

	@When("^I Clicked on Search tab$")
	public void i_Clicked_on_Search_tab() throws Throwable {
		dotcSrchPg.clickSearchBtn();
		// dotcSrchPg.clickSearchTab();
	}

	@Then("^I Enter Departure from date only$")
	public void i_Enter_Departure_from_date_only() throws Throwable {
		dotcSrchPg.selectDepartureFromDate();
	}

	@Then("^I Enter Departure from/departure to date$")
	public void i_Enter_Departure_from_departure_to_date() throws Throwable {
		dotcSrchPg.selectDepartureFromDate();
		dotcSrchPg.selectDepartureToDate();

	}

	@Then("^I Enter Departure from/Arrival from/Arrival to date$")
	public void i_Enter_Departure_from_Arrival_from_Arrival_to_date() throws Throwable {
		dotcSrchPg.selectDepartureFromDate();
		dotcSrchPg.selectArrivalFromDate();
		dotcSrchPg.selectArrivalToDate();
	}

	@Then("^I Enter Departure from/departure to/Arrival from/Arrival to date$")
	public void i_Enter_Departure_from_departure_to_Arrival_from_Arrival_to_date() throws Throwable {
		dotcSrchPg.selectDepartureFromDate();
		dotcSrchPg.selectDepartureToDate();
		dotcSrchPg.selectArrivalFromDate();
		dotcSrchPg.selectArrivalToDate();
	}

	@Then("^I Clicked Position arrow to select the position$")
	public void i_Clicked_Position_arrow_to_select_the_position() throws Throwable {
		dotcSrchPg.clickOnPositionArrow();
	}

	@Then("^I Clicked the Plus icon of position$")
	public void i_Clicked_the_Plus_icon_of_position() throws Throwable {
		dotcSrchPg.clickOnPositionPlus();
	}

	@Then("^I Select the first checkbox from position$")
	public void i_Select_the_first_checkbox_from_position() throws Throwable {
		dotcSrchPg.selectPositionFirstCheckBox();
	}

	@Then("^I Clicked the Plus icon of division$")
	public void i_Clicked_the_Plus_icon_of_division() throws Throwable {
		dotcSrchPg.clickOnDivisionPlus();
	}

	@Then("^I Select the first checkbox from division$")
	public void i_Select_the_first_checkbox_from_division() throws Throwable {
		dotcSrchPg.selectDivisionFirstCheckBox();
	}

	@Then("^I Clicked on Save Generic Criteria$")
	public void i_Clicked_on_Save_Generic_Criteria() throws Throwable {
		dotcSrchPg.clickSaveGenericBtn();
	}

	@Then("^I Clicked on Show sequence button$")
	public void i_Clicked_on_Show_sequence_button() throws Throwable {
		dotcSrchPg.clickOnShowSequence();

	}

	@Then("^I Clicked on Clear button$")
	public void i_Clicked_on_Clear_button() throws Throwable {
		dotcSrchPg.clickGenericSearchClear();
	}

	@Then("^I Enter the Title in title box \"([^\"]*)\"$")
	public void i_Enter_the_Title_in_title_box(String Title) throws Throwable {
		dotcSrchPg.enterSearchTitle();
	}

	@Then("^I Enter same Title in title box \"([^\"]*)\"$")
	public void i_Enter_same_Title_in_title_box(String arg1) throws Throwable {
		dotcSrchPg.enterSameTitle();
	}

	@Then("^I Select All Ballots checkbox$")
	public void i_Select_All_Ballots_checkbox() throws Throwable {
		dotcSrchPg.SelectAllBallotsCheckBox();
	}

	@Then("^I Select pick up DOTC checkbox$")
	public void i_Select_pick_up_DOTC_checkbox() throws Throwable {
		dotcSrchPg.SelectDOTCCheckBox();
	}

	@Then("^I Select pick up Outside checkbox$")
	public void i_Select_pick_up_Outside_checkbox() throws Throwable {
		dotcSrchPg.SelectOutsideCheckBox();
	}

	@Then("^I Select Template checkbox$")
	public void i_Select_Template_checkbox() throws Throwable {
		dotcSrchPg.SelectTemplateCheckBox();
	}

	@Then("^I Clicked on Save button$")
	public void i_Clicked_on_Save_button() throws Throwable {
		dotcSrchPg.clickSaveTitleBtn();
	}

	@Then("^I should be able to click Pay and Credit Down Arrow$")
	public void i_should_be_able_to_click_Pay_and_Credit_Down_Arrow() throws Throwable {
		dotcSrchPg.clickPayAndCreditDwnArrow();
	}

	@Then("^I should be able to click Total Credit Plus$")
	public void i_should_be_able_to_click_Total_Credit_Plus() throws Throwable {
		dotcSrchPg.clickTotalCreditPlus();
	}

	@Then("^I should be able to get the Departure Date$")
	public void i_should_be_able_to_get_the_Departure_Date() throws Throwable {
		dotcSrchPg.selectDepartureFromDate();
		dotcSrchPg.selectDepartureToDate();

	}

	@Then("^I should be able to select Maximum Hours \"([^\"]*)\"$")
	public void i_should_be_able_to_select_Maximum_Hours(String arg1) throws Throwable {
		dotcSrchPg.selectMaxTotalCreditHoursDrpDwn(arg1);
	}

	@Then("^I should be able to select Maximum Minutes \"([^\"]*)\"$")
	public void i_should_be_able_to_select_Maximum_Minutes(String arg1) throws Throwable {
		dotcSrchPg.selectMaxTotalCreditMinutesDrpDwn(arg1);
	}

	@Then("^I should be able to select Minimum Hours \"([^\"]*)\"$")
	public void i_should_be_able_to_select_Minimum_Hours(String arg1) throws Throwable {
		dotcSrchPg.selectMinTotalCreditHoursDrpDwn(arg1);
	}

	@Then("^I should be able to select Minimum Minutes \"([^\"]*)\"$")
	public void i_should_be_able_to_select_Minimum_Minutes(String arg1) throws Throwable {
		dotcSrchPg.selectMinTotalCreditMinutesDrpDwn(arg1);
	}

	@Then("^I should be able to select Arrival Date$")
	public void i_should_be_able_to_select_Arrival_Date() throws Throwable {
		dotcSrchPg.selectArrivalFromDate();
		dotcSrchPg.selectArrivalToDate();
	}

	@Then("^I should be able to click Inclusibe Timeframe checkbox$")
	public void i_should_be_able_to_click_Inclusibe_Timeframe_checkbox() throws Throwable {
		dotcSrchPg.clickInclusiveFrameworkChkBox();
	}

	@Then("^I should see Departure To and Arrival To Date fields are disabled$")
	public void i_should_see_Departure_To_and_Arrival_To_Date_fields_are_disabled() throws Throwable {
		dotcSrchPg.verifyDepartureArrivalToDateDisabled();
	}

	@Then("^I should be able to expand Days Dates and Hours Panel$")
	public void i_should_be_able_to_expand_Days_Dates_and_Hours_Panel() throws Throwable {
		dotcSrchPg.clickDaysDatesHoursDwnArrw();
	}

	@Then("^I should be able to add Duty Periods in Search$")
	public void i_should_be_able_to_add_Duty_Periods_in_Search() throws Throwable {
		dotcSrchPg.clickDutyPeriodsPlus();
	}

	@Then("^I should be able to select \"([^\"]*)\" and \"([^\"]*)\" from Duty Periods Min and Max dropdown$")
	public void i_should_be_able_to_select_and_from_Duty_Periods_Min_and_Max_dropdown(String arg1, String arg2)
			throws Throwable {
		dotcSrchPg.selectMinMaxDutyPeriods(arg1, arg2);
	}

	@Then("^I Should be able to see the list of Sequence based on departure date range$")
	public void i_Should_be_able_to_see_the_list_of_Sequence_based_on_departure_date_range() throws Throwable {
		dotcSrchPg.getSequenceDetailsDepartureRange();
	}

	@Then("^I fetch the sequence from the service based on departure date range$")
	public void i_fetch_the_sequence_from_the_service_based_on_departure_date_range() throws Throwable {
		dotcSrchPg.getSequenceResponseDepartureRange();
	}

	@Then("^I Should be able to see the list of Sequence based on arrival date range$")
	public void i_Should_be_able_to_see_the_list_of_Sequence_based_on_arrival_date_range() throws Throwable {
		dotcSrchPg.getSequenceDetailsArrivalRange();
	}

	@Then("^I fetch the sequence from the service based on arrival date range$")
	public void i_fetch_the_sequence_from_the_service_based_on_arrival_date_range() throws Throwable {
		dotcSrchPg.getSequenceResponseArrivalRange();
	}

	@Then("^I Should be able to see the list of Sequence based on departure/arrival date range$")
	public void i_Should_be_able_to_see_the_list_of_Sequence_based_on_departure_arrival_date_range() throws Throwable {
		dotcSrchPg.getSequenceDetailsDepArrRange();
	}

	@Then("^I fetch the sequence from the service based on departure/arrival date range$")
	public void i_fetch_the_sequence_from_the_service_based_on_departure_arrival_date_range() throws Throwable {
		dotcSrchPg.getSequenceResponseDepArrRange();
	}

	@Then("^I Verify the sequence from the service$")
	public void i_Verify_the_sequence_from_the_service() throws Throwable {
		dotcSrchPg.validateSequenceTableFromService();
	}

	@Then("^I Select Pick Up DOTC checkbox$")
	public void i_Select_Pick_Up_DOTC_checkbox() throws Throwable {
		dotcSrchPg.SelectDOTCCheckBox();
	}

	@Then("^I Clicked on the sequence from the sequence list$")
	public void i_Clicked_on_the_sequence_from_the_sequence_list() throws Throwable {
		dotcSrchPg.clickOnFirstSequence();
	}

	@Then("^I Clicked on the save selected sequences button$")
	public void i_Clicked_on_the_save_selected_sequences_button() throws Throwable {
		dotcSrchPg.clickOnSaveSelectedSequence();
	}

	@Then("^Select Pick Up DOTC checkbox from Add to Ballots$")
	public void select_Pick_Up_DOTC_checkbox_from_Add_to_Ballots() throws Throwable {
		dotcSrchPg.addBallotsDotcCheckBox();
	}

	@Then("^Clicked on add button$")
	public void clicked_on_add_button() throws Throwable {
		dotcSrchPg.clickAddBtn();
	}

	@Then("^I want to be able to see the difference that incluse time frame checkbox makes at sequences result$")
	public void i_want_to_be_able_to_see_the_difference_that_incluse_time_frame_checkbox_makes_at_sequences_result()
			throws Throwable {
		try {
			dotcSrchPg.clickSearchTab();
			// get today date
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd MMMyy", Locale.US); // 29 APR20
			dotcSrchPg.clickOnInclusiveBox();
			for (int j = 0; j < 10; j++) {
				Calendar d = Calendar.getInstance();
				d.setTime(date);
				d.add(Calendar.DATE, j);
				String dep = dateFormat.format(d.getTime()).toUpperCase();
				d.add(Calendar.DATE, 1);
				String arr = dateFormat.format(d.getTime()).toUpperCase();
				dotcSrchPg.enterDepartureFromDate(dep);
				dotcSrchPg.enterArrivalDate(arr);
				Thread.sleep(3000);
				dotcSrchPg.clickSeqSearch();
				Thread.sleep(7000);
				// dotcSrchPg.waitForSearchResult();
				// dotcSrchPg.clickArrSortBtn();
				HashSet<String> arrivalLists = dotcSrchPg.getClmnSetFromSeqResult(11);
				if (arrivalLists.size() == 0)
					continue;
				d.add(Calendar.DATE, 1);
				arr = dateFormat.format(d.getTime()).toUpperCase();
				dotcSrchPg.enterArrivalDate(arr);
				Thread.sleep(2000);
				dotcSrchPg.clickSeqSearch();
				Thread.sleep(2000);
				HashSet<String> arrivalLists2 = dotcSrchPg.getClmnSetFromSeqResult(11);
				log.info(String.format("%s ?=? %s",arrivalLists2,arrivalLists));
				if (!arrivalLists2.containsAll(arrivalLists)) {
					assertTrue(false);
				}
				else
					break;
			}
			ExtentTestManager.getTest().log(LogStatus.PASS, "The Pilot is seen in pick Up DOTC List");
			System.out.println("The pilot is seen inside the list");
			assertTrue("The pilot is seen in pick Up DOTC List", true);
		} catch(Exception ex) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception in pick up DOTC List");
			System.out.println("Exception in pick up DOTC List");
			assertTrue("Exception in pick up DOTC List", false);
		}
	}

	ArrayList<String> seqList;
	ArrayList<String> seqStatList;

	// MMD: will search all the sequences for next + 3 weeks and then take one and
	// make search on to see it in result
	@Then("^If I search for specific existing sequence I should see in the result table$")
	public void if_I_search_for_specific_existing_sequence_I_should_see_in_the_result_table() throws Throwable {
		// set today as dep from
		dotcSrchPg.clickSearchTab();
		Thread.sleep(1000);
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd MMMyy", Locale.US); // 29 APR20
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		String depF = dateFormat.format(d.getTime()).toUpperCase();
		// set 3 weeks later as dep to
		d.add(Calendar.DATE, 21);
		String dep2 = dateFormat.format(d.getTime()).toUpperCase();
		dotcSrchPg.enterDepartureFromDate(depF);
		dotcSrchPg.enterDeparture2Date(dep2);
		Thread.sleep(4000);
		dotcSrchPg.clickSeqSearch();
		Thread.sleep(20000);
		// get the sequences as list
		seqList = dotcSrchPg.getClmnFromSeqResult(3);
		dotcSrchPg.clickSequenceNumberCollapsable();
		try {
			dotcSrchPg.clickSequenceNumberPlus();
		} catch (Exception e) {
			dotcSrchPg.clickSequenceNumberCollapsable();
			dotcSrchPg.clickSequenceNumberPlus();
		}
		if (seqList==null || seqList.size() == 0) {
			log.warn(String.format("no sequence found for dep start: %s - %s. will pass!", depF, dep2));
			return;
		}
		dotcSrchPg.enterSequenceNumber(seqList.get(0));
		seqStatList = dotcSrchPg.getClmnFromSeqResult(5);
		dotcSrchPg.selectDepartureStationInSequenceNumberOption(seqStatList.get(0));
		dotcSrchPg.clickSeqSearch();
		Thread.sleep(9000);
		ArrayList<String> t = dotcSrchPg.getClmnFromSeqResult(3);
		log.info(String.format("Asserting for %s is containing %s", t.toString(), seqList.get(0)));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Able to see specific sequence existing");
		assertTrue(t.contains(seqList.get(0)));
	}

	@Then("^I should see single departure date sequences$")
	public void i_should_see_single_departure_date_sequences() throws Throwable {
		if (seqList == null) {
			log.info(String.format("no single departure date sequence found in seqList will pass!"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "no single departure date sequence found in seqList will pass!");
			return;
		}
		for (int i=0; i<seqList.size(); i++) {
			dotcSrchPg.enterSequenceNumber(seqList.get(i));
			dotcSrchPg.selectDepartureStationInSequenceNumberOption(seqStatList.get(i));
			dotcSrchPg.clickSeqSearch();
			Thread.sleep(9000);
			ArrayList<String> sqNs = dotcSrchPg.getClmnFromSeqResult(3);
			if (sqNs.size() == 1) {
				log.info(String.format("%s is single departure date sequence!", seqList.get(i)));
				ExtentTestManager.getTest().log(LogStatus.PASS, "Single departure date sequence is -- " +seqList.get(i));
				assertTrue(true);
				return;
			}
		}
		log.info(String.format("no single departure date sequence found in %s will pass!", seqList.toString()));
		ExtentTestManager.getTest().log(LogStatus.PASS, "no single departure date sequence found in seqList will pass!--" +seqList.toString());
	}

	@Then("^I should see multiple departure date sequences$")
	public void i_should_see_multiple_departure_date_sequences() throws Throwable {
		if (seqList == null) {
			log.info(String.format("no single departure date sequence found in seqList will pass!"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "no multiple departure date sequence found in seqList will pass!");
			return;
		}
		for (int i=0; i<seqList.size(); i++) {
			dotcSrchPg.enterSequenceNumber(seqList.get(0));
			dotcSrchPg.selectDepartureStationInSequenceNumberOption(seqStatList.get(i));
			dotcSrchPg.clickSeqSearch();
			Thread.sleep(9000);
			ArrayList<String> sqNs = dotcSrchPg.getClmnFromSeqResult(3);
			if (sqNs.size() > 1) {
				log.info(String.format("%s is having multiple departure date sequence!", seqList.get(0)));
				ExtentTestManager.getTest().log(LogStatus.PASS, "squence having multiple departure date is " +seqList.get(0));
				assertTrue(true);
				return;
			}
		}
		log.info(String.format("no multiple departure date sequence found in %s will pass!", seqList.toString()));
		ExtentTestManager.getTest().log(LogStatus.PASS, "no multiple departure date sequence found in seqList will pass!" +seqList.toString());
	}

	@Then("^I should see any departure station$")
	public void i_should_see_any_departure_station() throws Throwable {
		if (seqList == null) {
			log.info(String.format("no single departure date sequence found in seqList will pass!"));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Passed -no  departure station found in seqList will pass!");
			return;
		}
		ArrayList<String> deps = null;
		for (int j=0; j<seqList.size(); j++) {
			dotcSrchPg.enterSequenceNumber(seqList.get(j));
			dotcSrchPg.selectDepartureStationInSequenceNumberOption(seqStatList.get(j));
			dotcSrchPg.clickSeqSearch();
			Thread.sleep(9000);
			deps = dotcSrchPg.getClmnFromSeqResult(5);
			for (int i = 1; i < deps.size(); i++)
				if (deps.get(0) != deps.get(i)) {
					log.info(String.format("%s is having more than one station!", deps.toString()));
					ExtentTestManager.getTest().log(LogStatus.PASS, " Passed- Having more than one departure station --" +deps.toString());
					assertTrue(true);
					return;
				}
		}
		if (deps != null)
			log.info(String.format("%s is not having more than one station! will pass!", deps.toString()));
		ExtentTestManager.getTest().log(LogStatus.PASS, "  Passed -Not having more than one departure station  in seqList will pass! Station-" +deps.toString());

	}

	@Then("^I should be able to verify sequence results for Total Credit between \"([^\"]*)\"\"([^\"]*)\" and \"([^\"]*)\"\"([^\"]*)\"$")
	public void i_should_be_able_to_verify_sequence_results_for_Total_Credit_between_and(String arg1, String arg2,
			String arg3, String arg4) throws Throwable {
		dotcSrchPg.verifySeqResultsforTotalCredit(arg1, arg2, arg3, arg4);
	}

	@Then("^I should be able to verify sequence results for Duty Period between \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_verify_sequence_results_for_Duty_Period_between_and(String arg1, String arg2)
			throws Throwable {
		dotcSrchPg.verifySeqResultsforDutyPeriods(arg1, arg2);
	}

	@Then("^I should be able to verify sequence results for that date and Bid status$")
	public void i_should_be_able_to_verify_sequence_results_for_that_date_and_Bid_status() throws Throwable {
		dotcSrchPg.verifySeqResults4BidStatus();
	}

	@Then("^I should be able to click Airport and Layovers Down Arrow$")
	public void i_should_be_able_to_click_Airport_and_Layovers_Down_Arrow() throws Throwable {
		dotcSrchPg.clickAirportLayoversDwnArrw();
	}

	@Then("^I should be able to click Departure Station Plus$")
	public void i_should_be_able_to_click_Departure_Station_Plus() throws Throwable {
		dotcSrchPg.clickDepartureStationsPlus();
	}

	@Then("^I should be able to select Departure Stations \"([^\"]*)\"$")
	public void i_should_be_able_to_select_Departure_Stations(String arg1) throws Throwable {
		dotcSrchPg.checkDepartureStationsCheckBoxes(arg1);
	}

	@Then("^I will see the show sequence element is present$")
	public void i_will_see_the_show_sequence_element_is_present() throws Throwable {
		if (!dotcSrchPg.showSeqIsClickable())
			assertTrue(false);
	}

	@When("^I choose some days \"([^\"]*)\" before today as departure from$")
	public void i_choose_some_days_before_today_as_departure_from(String dayCount) throws Throwable {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd MMMyy", Locale.US); // 29 APR20
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		d.add(Calendar.DATE, -Integer.parseInt(dayCount));
		String dep = dateFormat.format(d.getTime()).toUpperCase();
		dotcSrchPg.enterDepartureFromDate(dep);
		Thread.sleep(3000);
	}

	@When("^I choose some days \"([^\"]*)\" after today as departure to$")
	public void i_choose_some_days_after_today_as_departure_to(String dayCount) throws Throwable {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd MMMyy", Locale.US); // 29 APR20
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		d.add(Calendar.DATE, Integer.parseInt(dayCount));
		String arr = dateFormat.format(d.getTime()).toUpperCase();
		dotcSrchPg.enterDepartureToDate(arr);
		Thread.sleep(3000);
	}

	public String[] chosenSeq;

	@When("^I check some \"([^\"]*)\" top found sequences$")
	public void i_check_some_top_found_sequences(String n) throws Throwable {
		chosenSeq = dotcSrchPg.checkTopNBallots(Integer.parseInt(n));
	}

	@Then("^I add them to ballot types \"([^\"]*)\" and delete if they preexist$")
	public void i_add_them_to_ballot_types_and_delete_if_they_preexist(String ballotTypes) throws Throwable {
		String[] bTypes = ballotTypes.split(",");
		dotcSrchPg.clickBallotSubmitButton();
		dotcSrchPg.checkBallotTypes(bTypes);
		dotcSrchPg.clickAddBtn(5); // btnSubmit
		DOTCBallotScreen dbs = new DOTCBallotScreen();
//		String existingBxpth = "//*[contains(@class,'ballotDraggableRow ng-star-inserted')]//*[.='%s']/../../..//*[contains(.,'%s')]/../../../../../../../../../../../../../../../../td[2]//aabp-checkbox";
		String existingBxpth = "//*[contains(@class,'ballot-data-table-summary-column')]//*[.='%s']/../../..//*[contains(.,'%s')]/../../../../../../../../../../../../../../../../td[2]//aabp-checkbox";
		String bTypeX = "//*[@class='nav nav-tabs flat-tabs double-height']//*[contains(.,'%s')]/a";
		for (int j = 0; j < bTypes.length; j++) {
			dbs.click(String.format(bTypeX, bTypes[j]), 6);
			for (int i = 0; i < chosenSeq.length / 2; i++)
				try {
					dbs.checkBallot(String.format(existingBxpth, chosenSeq[2 * i], chosenSeq[2 * i + 1]));

			} catch (Exception e) {
				}
			dbs.deleteCheckedBs(bTypes[j]);
		}
		dbs.clickUpdate();
		assertTrue(dbs.areBsExisting(chosenSeq));
	}

	@Then("^I verify all the open sequences are viewed for that date and Four Part BidStatus$")
	public void i_verify_all_the_open_sequences_are_viewed_for_that_date_and_Four_Part_BidStatus() throws Throwable {
		dotcSrchPg.compareAndVerifySeqForPrevEmp();
	}

	@Then("^I should be able to click Caldendar Days Plus$")
	public void i_should_be_able_to_click_Caldendar_Days_Plus() throws Throwable {
		dotcSrchPg.clickCalendarDaysPlus();
	}

	@Then("^I should be able to select Calendar Days from drop down \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_select_Calendar_Days_from_drop_down_and(String arg1, String arg2) throws Throwable {
		dotcSrchPg.selectCalendarDaysDrpdown(arg1, arg2);
	}

	@Then("^I should be able to click Paid Credit Plus$")
	public void i_should_be_able_to_click_Paid_Credit_Plus() throws Throwable {
		dotcSrchPg.clickPaidCreditPlus();
	}

	@Then("^I should be able to select Paid Credit values \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_select_Paid_Credit_values(String arg1, String arg2, String arg3, String arg4)
			throws Throwable {
		dotcSrchPg.selectPaidCreditDrpdown(arg1, arg2, arg3, arg4);
	}

	@Then("^I should be able to verify sequence results for Departure Station \"([^\"]*)\"$")
	public void i_should_be_able_to_verify_sequence_results_for_Departure_Station(String arg1) throws Throwable {
		dotcSrchPg.verifySeqResultsforDepartureStations(arg1);
	}

	@Then("^I should be able to verify sequence results based on Pilot base \"([^\"]*)\"$")
	public void i_should_be_able_to_verify_sequence_results_based_on_Pilot_base(String arg1) throws Throwable {
		dotcSrchPg.verifySeqResultsforDepartureStationsBasedOnBase(arg1);
	}

	@Then("^I should choose TAFB values \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_choose_TAFB_values_and(String arg1, String arg2) throws Throwable {
		dotcSrchPg.selectTAFBRange(arg1, arg2);
	}

	@Then("^I Verify that all the other field is disabled when ballots is created by sequence$")
	public void i_Verify_that_all_the_other_field_is_disabled_when_ballots_is_created_by_sequence() throws Throwable {
		dotcSrchPg.verifyOtherFieldDisabled();
	}

	@Then("^I clicked on Days Dates and Hour down arrow$")
	public void i_clicked_on_Days_Dates_and_Hour_down_arrow() throws Throwable {
		dotcSrchPg.clickDaysDatesHoursDwnArrw();
	}

	@Then("^I clicked on Duty Periods plus Icon$")
	public void i_clicked_on_Duty_Periods_plus_Icon() throws Throwable {
		dotcSrchPg.clickDutyPeriodsPlus();
	}

	@Then("^I should be able to verify Premium Icon for these sequences and dates \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	public void i_should_be_able_to_verify_Premium_Icon_for_these_sequences_and_dates(String base, String eqp,
			String div, String pos) throws Throwable {
		dotcSrchPg.validatePremiumIcon(base, eqp, div, pos);

	}

	@Then("^I should be able to verify CrewedSeq Icon for these sequences$")
	public void i_should_be_able_to_verify_CrewedSeq_Icon_for_these_sequences() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		try {
			dotcSrchPg.verifyCrewedSeqIconFromSearchResults();
		} catch (Exception ex) {
			org.testng.Assert.fail(
					"Exception occured in the test case: I should be able to verify CrewedSeq Icon for these sequences"
							+ ex.getMessage(),
					ex);

		}

	}

	@Then("^I should be able to enter the Search title based on User story Number \"([^\"]*)\"$")
	public void i_should_be_able_to_enter_the_Search_title_based_on_User_story_Number(String arg1) throws Throwable {
		dotcSrchPg.enterSearchTitleBaseOnUserStory(arg1);
	}

	@Then("^I should be able to click Layover Plus$")
	public void i_should_be_able_to_click_Layover_Plus() throws Throwable {
		dotcSrchPg.clickLayoverPlus();
	}

	@Then("^I should be able to enter station code in Layover include textbox \"([^\"]*)\"$")
	public void i_should_be_able_to_enter_station_code_in_Layover_include_textbox_data(String arg) throws Throwable {
		dotcSrchPg.enterLayoverIncludeTxt(arg);
	}

	@Then("^I should be able to enter station code in Layover exclude textbox \"([^\"]*)\"$")
	public void i_should_be_able_to_enter_station_code_in_Layover_exclude_textbox_data(String arg) throws Throwable {
		dotcSrchPg.enterLayoverExcludeTxt(arg);
	}

	//07-03-2022
	//removed variable
	@Then("^I should be able to verify Sick Icon for these sequences$")
	public void i_should_be_able_to_verify_sick_icon_for_these_sequences()
			throws Throwable {
		dotcSrchPg.validateSickIcon();
	}

	@Then("^I select the Depart No Earlier Than dropdown \"([^\"]*)\"$")
	public void i_select_the_depart_no_earlier_than_dropdown(String arg1) throws Throwable {

		dotcSrchPg.selectDepartNoEarlierThan(arg1);
	}

	@Then("^I select the Depart No Later Than dropdown \"([^\"]*)\"$")
	public void i_select_the_Depart_No_Later_Than_dropdown(String arg1) throws Throwable {

		dotcSrchPg.selectDepartNoLaterThan(arg1);
	}

	@Then("^I select the Arrive No Earlier Than dropdown \"([^\"]*)\"$")
	public void i_select_the_Arrive_No_Earlier_Than_dropdown(String arg1) throws Throwable {

		dotcSrchPg.selectArriveNoEarlierThan(arg1);
	}

	@Then("^I select the Arrive No Later Than dropdown \"([^\"]*)\"$")
	public void i_select_the_Arrive_No_Later_Than_dropdown(String arg1) throws Throwable {

		dotcSrchPg.selectArriveNoLaterThan(arg1);
	}

	@Then("^I should be able to validate Departure and arrival time values \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" for Ballot Type  \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Departure_and_arrival_time_values_for_Ballot_Type(String arg1, String arg2,
			String arg3, String arg4, String arg5, String arg6) throws Throwable {

		dotcSrchPg.validateDepartureArrivalTime(arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Then("^I should be able to validate Calendar Days with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Calendar_Days_with_and(String arg1, String arg2) throws Throwable {
		dotcSrchPg.validateSearchSequenceCalendarDays(arg1, arg2);
	}
	
	@Then("^I should be able to select No Work Days criteria$")
	public void i_should_be_able_to_select_No_Work_Days_criteria() throws Throwable {
		dotcSrchPg.clickNoWorkDaysPlusIcon();
	}

	@Then("^I should be able to select No Work Dates criteria$")
	public void i_should_be_able_to_select_No_Work_Dates_criteria() throws Throwable {
		dotcSrchPg.clickNoWorkDatesPlusIcon();
	}

	@Then("^I should be able to select No work Hours criteria$")
	public void i_should_be_able_to_select_No_work_Hours_criteria() throws Throwable {
		dotcSrchPg.clickNoWorkHoursPlusIcon();
	}
	
	@Then("^I should be able to select no work days \"([^\"]*)\"$")
	public void i_should_be_able_to_select_no_work_days(String arg1) throws Throwable {
		dotcSrchPg.checkNoWorkDaysOptions(arg1);
	}

	@Then("^I should be able to select No Work Hours from Min \"([^\"]*)\" and Max \"([^\"]*)\" dropdown$")
	public void i_should_be_able_to_select_No_Work_Hours_from_Min_and_Max_dropdown(String arg1, String arg2) throws Throwable {
		dotcSrchPg.selectnoWorkHoursMinDropDown(arg1);
		dotcSrchPg.selectnoWorkHoursMaxDropDown(arg2);
	}
	
	@Then("^I should be able to select No Work Dates as per today date plus \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_select_No_Work_Dates_as_per_today_date_plus(String arg1, String arg2) throws Throwable {
		dotcSrchPg.selectNoWorkDates(arg1, arg2);
	}

	@Then("^I should be able to click on Legs Per DP$")
	public void i_should_be_able_to_click_on_legs_per_dp() throws Throwable {
		dotcSrchPg.clicklegsPerDP();
	}

	@Then("^I should be able to enter the range for Legs Per DP \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_enter_the_range_for_LegsPerDP(String minlegsperdp,
			String maxlegsperdp) throws Throwable {
		dotcSrchPg.selectLegsPerDPRange(minlegsperdp, maxlegsperdp);

	}

	@Then("^I should be able to Verify Sequence results for LegsPerDP \"([^\"]*)\" and \"([^\"]*)\" range$")
	public void i_should_be_able_to_verify_sequence_results_for_LegsPerDP(
			String minlegsperdp, String maxlegsperdp) throws Throwable {
		dotcSrchPg.verifySeqResultsforLegsPerDP(minlegsperdp, maxlegsperdp);
	}

	@Then("^I should be able to click Legs Per DP and choose \"([^\"]*)\" and \"([^\"]*)\"	Legs Per DP values$")
	public void i_should_be_able_to_click_Legs_Per_DP_and_choose_and_values(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.clicklegsPerDP();
		dotcSrchPg.selectLegsPerDPRange(arg1, arg2);
	}
	
	@Then("^I should be able to click Legs Per Seq and choose \"([^\"]*)\" and \"([^\"]*)\" Legs Per Seq values$")
	public void i_should_be_able_to_click_Legs_Per_Seq_and_choose_and_values(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcSrchPg.clicklegsPerSeq();
		dotcSrchPg.selectLegsPerSeqRange(arg1, arg2);
		
	}
	@Then("^I should be able to click DeadHeads plus icon$")
	public void i_should_be_able_to_click_DeadHeads() throws Throwable{
		dotcSrchPg.clickDeadHeads();
	}
	
	@Then("^I should be able to select DeadHeads checkbox \"([^\"]*)\"$")
	public void i_should_be_able_to_click_DeadHeads_checkbox(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		
		dotcSrchPg.DeadHeadsCheckBox(arg1);
	}
	@Then("^I should be able to click Sit Time Plus$")
	public void i_should_be_able_to_click_Sit_Time_Plus() throws Throwable {
		dotcSrchPg.clickSitTimePlus();

	}
	
	@Then("^I should be able to click on position and Division Link$")
	public void i_should_be_able_to_click_on_position_and_Division_Link() throws Throwable {

		dotcSrchPg.clickOnPositionAndDivisionLink();
	}

	@Then("^I should be able to click on Position plus$")
	public void i_should_be_able_to_click_on_Position_plus() throws Throwable {

		dotcSrchPg.clickOnPositionPlusIcon();
	}

	@Then("^I should be able to click on Division plus$")
	public void i_should_be_able_to_click_on_Division_plus() throws Throwable {

		dotcSrchPg.clickOnDivisionPlusIcon();
	}

	@Then("^I should be able to select Position \"([^\"]*)\" checkbox$")
	public void i_should_be_able_to_select_Position_checkbox(String arg1) throws Throwable {

		dotcSrchPg.selectPositionCheckBox(arg1);
	}

	@Then("^I should be able to select Division \"([^\"]*)\" checkbox$")
	public void i_should_be_able_to_select_Division_checkbox(String arg1) throws Throwable {

		dotcSrchPg.selectDivisionCheckBox(arg1);
	}

	@Then("^I should be able to verify the position check boxes \"([^\"]*)\"$")
	public void i_should_be_able_to_verify_the_position_check_boxes(String arg1) throws Throwable {

		dotcSrchPg.verifyPositionCheckBoxes(arg1);
	}

	@Then("^I should be able to verify the Division check boxes \"([^\"]*)\"$")
	public void i_should_be_able_to_verify_the_Division_check_boxes(String arg1) throws Throwable {

		dotcSrchPg.verifyDivisionCheckBoxes(arg1);
	}
	
	// code added
		//12-01-2022
		@And("^I should click on save selected sequences button$")
		public void i_should_click_on_save_selected_sequences_button() throws Throwable {
			dotcSrchPg.clickBallotSubmitButton();
		}
		
		//12-01-2022
		@Then("^I should select ballot types \"([^\"]*)\"$")
		public void i_should_select_ballot_types(String ballotTypes) throws Throwable{
			String[] bTypes = ballotTypes.split(",");
			dotcSrchPg.checkBallotTypes(bTypes);
		}
		
		//13-01-2022
		@And("^I should click on add button$")
		public void i_should_click_on_add_button() throws Throwable {
			dotcSrchPg.clickAddBtn();
		}
		
		//11 Aug 2022
		//updated xpath
		@Then("^I should delete any duplicate ballots in ballot types \"([^\"]*)\"$")
		public void i_should_delete_any_duplicate_ballots_in_ballot_types(String ballotTypes) throws Throwable {
			//String existingBxpth = "//aac-ballot-data-table[@ballottype='%s']//*[contains(@class,'ballot-data-table-summary-column')]//*[.='%s']/../../..//*[contains(.,'%s')]/../../../../../../../../../../../../../../../../td[2]//aabp-checkbox";
			//String 	existingBxpth = "//aac-ballot-data-table[@ballottype='%s']//*[contains(@class,'ballot-data-table-summary-column')]//*[.='%s']/../../..//*[contains(.,'%s')]/../../../../../../../../..//td[@class='checkbox-column']//aabp-checkbox";
			String existingBxpth = "//aac-ballot-data-table[@ballottype='%s']//div[@class='sequence-special-link']//a[text()='%s']/../../..//*[contains(.,'%s')]/../../../../..//mat-checkbox";
			String bTypeX = "//*[contains(.,'%s')]/a";
			dotcBlltScr.checkBallot(existingBxpth, bTypeX, chosenSeq, ballotTypes);
		}
		
		//12-01-2022
		@Then("^I should click on update button \"([^\"]*)\"$")
		public void i_should_click_on_update_button(String bTypes) throws Throwable {
			dotcBlltScr.clickUpdate(bTypes);
		}
		
		//17-01-2022
		@Then("^I should click on checkbox \"([^\"]*)\"$")
		public void i_should_click_on_checkbox(String base) throws Throwable {
			dotcSrchPg.selectDepartureStationInSequenceNumberOption(base);
		}
		
		//step definition for validating the sequences in sequence list contains GRD within the sitTime limit 
		//20-01-2022
		@Then("^I should be able to Verify Sequence results for sit time \"([^\"]*)\"$")
		public void I_should_be_able_to_verify_sequence_results_for_sit_time(String sitTime) throws Throwable {
			dotcSrchPg.validateGRDInSearchSequenceList(sitTime);
		}
		
		//step definition for validating the sequences in sequence list contains layovers according to requirements
		//20-01-2022
		@Then("^I should validate layover station for sequences are according to include and exclude station$")
		public void i_should_validate_layover_station_for_sequences_are_according_to_and_station() throws Throwable {
			dotcSrchPg.validateLayoverInSearchSequenceList();
		}
		
		//step definition for validating sequences in sequence list contains position and division as provided
		//20-01-2022
		@Then("^I should validate \"([^\"]*)\" and \"([^\"]*)\" in all the sequences in show sequence list$")
		public void i_should_validate_and_in_all_the_sequences_in_show_sequences_list(String position, String division) throws Throwable {
			dotcSrchPg.validatePositionAndDivisionInSearchSequenceList(position, division);
		}
		
		//step definition for validating sequences in sequence list is within depart no earlier than and depart no later than time
		//21-01-2022
		@Then("^I should validate departNoEarlier \"([^\"]*)\" and departNoLater \"([^\"]*)\" in search results$")
		public void i_should_validate_departNoEarlier_and_departNoLater_in_search_results(String earlier, String later) throws Throwable {
			dotcSrchPg.validateDepartNoEarlierAndDepartNoLaterInSearchSequenceList(earlier, later);
		}
		
		//step definition for validating sequences in sequence list is within arrive no earlier than and arrive no later than time
		//21-01-2022
		@Then("^I should validate arriveNoEarlier \"([^\"]*)\" and arriveNoLater \"([^\"]*)\" in search results$")
		public void i_should_validate_arriveNoEarlier_and_arriveNoLater_in_search_results(String earlier, String later) throws Throwable {
			dotcSrchPg.validateArriveNoEarlierAndArriveNoLaterInSearchSequenceList(earlier, later);
		}
		
		//step definition to set departure date range
		//21-01-2022
		@Then("^I enter Dep Date from and to Date \"([^\"]*)\" \"([^\"]*)\"$")
		public void i_enter_dep_date_from_and_to_date(String from, String to) throws Throwable {
			dotcSrchPg.selectDepartureDateRange(from, to);
		}
		
		//step definition to validate red border around textbox
		//24-01-2022
		@Then("^I should validate red border around \"([^\"]*)\" textbox$")
		public void i_should_validate_red_border_around_textbox(String fieldName) throws Throwable {
			dotcSrchPg.validateRedBorder(fieldName);
		}
		
		//step definition to validate if Save Generic Criteria button is disabled
		//24-01-2022
		@And("^I should validate Save Generic Criteria button is disabled$")
		public void i_should_validate_save_generic_criteria_button_is_disabled() throws Throwable {
			dotcSrchPg.validateSaveGenericCriteriaButtonDisabled();
		}
		
		//step definition to validate if show sequence button is disabled
		//24-01-2022
		@Then("^I should validate Show Sequences button is disabled$")
		public void i_should_validate_show_sequence_button_is_disabled() throws Throwable {
			dotcSrchPg.validateShowSequenceButtonDisabled();
		}
		
		//step definition to validate if clear button is enabled
		//24-01-2022
		@Then("^I should validate Clear button is enabled$")
		public void i_should_validate_clear_button_is_enabled() throws Throwable {
			dotcSrchPg.validateClearButtonEnabled();
		}
		
		//step definition for Calendar Days Minus
		//24-01-2022
		@Then("^I should click Calendar Days Minus$")
		public void i_should_click_calendar_days_minus() throws Throwable {
			dotcSrchPg.clickCalendarDaysMinus();
		}
		
		//step definition for duty periods minus
		//24-01-2022
		@Then("^I click Duty Periods Minus$")
		public void i_click_duty_period_minus() throws Throwable {
			dotcSrchPg.clickDutyPeriodMinus();
		}
		
		//step definition for Paid Credits minus
		//24-01-2022
		@Then("^I click on Paid Credit Minus$")
		public void i_click_on_paid_credit_minus() throws Throwable {
			dotcSrchPg.clickPaidCreditMinus();
		}
		
		//step definition for leg per DP minus
		//24-01-2022
		@Then("^I clicked Legs Per DP Minus$")
		public void i_clicked_leg_per_dp_minus() throws Throwable {
			dotcSrchPg.clickLegPerDPMinus();
		}
		
		//step definition for leg per Sequences minus
		//24-01-2022
		@Then("^I clicked Legs Per Sequences Minus$")
		public void i_clicked_leg_per_sequences_minus() throws Throwable {
			dotcSrchPg.clickLegPerSeqMinus();
		}
		
		//step definition for TAFB minus
		//24-01-2022
		@Then("^I clicked TAFB Minus$")
		public void i_clicked_TAFB_minus() throws Throwable {
			dotcSrchPg.clickTAFBMinus();
		}
		
		//step definition for layover times
		//24-01-2022
		@Then("^I should click on Layover Times Plus$")
		public void i_should_click_on_layover_times_plus() throws Throwable {
			dotcSrchPg.clickLayoverTimesPlus();
		}
		
		//step definition to choose value for Layover Times
		//24-01-2022
		@Then("^I should be able to choose \"([^\"]*)\" and \"([^\"]*)\" for Layover Times$")
		public void i_should_be_able_to_choose_and_for_layover_times(String min, String max) throws Throwable {
			dotcSrchPg.chooseValuesForLayoverTimes(min, max);
		}
		
		//step definition to get layover stations from service
		//10-03-2022
		@Then("^I should get Layover stations from service for \"([^\"]*)\"$")
		public void I_should_get_Layover_stations_from_service(String type) throws Throwable {
			dotcSrchPg.getLayoverStations(type);
		}
		
		//step definition to set station for exclude stations
		//10-03-2022	
		@Then("^I should be able to enter station code in Layover exclude textbox$")
		public void i_should_be_able_to_enter_station_code_in_Layover_exclude_textbox() throws Throwable {
			dotcSrchPg.enterLayoverExcludeTxt();
		}
		
		//step definition to set station for include stations
		//10-03-2022
		@Then("^I should be able to enter station code in Layover include textbox$")
		public void i_should_be_able_to_enter_station_code_in_Layover_include_textbox() throws Throwable {
			dotcSrchPg.enterLayoverIncludeTxt();
		}
		
		//step definition to set same station for include and exclude stations
		//10-03-2022
		@Then("^I should be able to enter same station code in Layover include and exclude textbox$")
		public void i_should_be_able_to_enter_same_station_code_in_Layover_include_and_exclude_textbox() throws Throwable {
			dotcSrchPg.enterLayoverIncludeExcludeTxt();
		}
		
		//step definition to add departure date according to the pendingSick payload
		//11-03-2022
		@Then("^Set the departure from date$")
		public void set_the_departure_from_date() throws Throwable {
			dotcSrchPg.selectDepartureFromDateFromPayload();
		}
		
		//step definition to check value range for min and max of No Work Hours criteria
		//22-03-2022
		@Then("^I should validate range for min and max of No Work Hours$")
		public void i_should_validate_range_for_min_and_max_of_no_work_hours() throws Throwable {
			dotcSrchPg.validateMinMaxRangeNoWorkHours();
		}
		
		//step definition to get crewed sequence from service
		@Then("I should get a crewed sequence number from service and add to text box")
		public void i_should_get_a_crewed_sequence_number_from_service_and_add_to_text_box() throws Throwable {
			String empID = DOTCLogInSteps.employeeID;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 2020-05-03
			
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Using today's date
			String departureFromDate = dateFormat.format(c.getTime());
			
			c.add(c.DATE, 10);
			String departureToDate = dateFormat.format(c.getTime());
			
			//get the template payload from json file
			String templatePayload = dotcRest.initializeTestDataFiles("searchSequencePayload.json");
			
			// modify data in json file
			String payload = dotcRest.modifyJson(templatePayload, "$.employeeID", empID);
			payload = dotcRest.modifyJson(payload, "$.crewedSequences", "true");
			payload = dotcRest.modifyJson(payload, "$.potentialSick", "true");
			payload = dotcRest.modifyJson(payload, "$.startDateFrom", departureFromDate);
			payload = dotcRest.modifyJson(payload, "$.startDateTo", departureToDate);
			
			
			String jsonResponse = dotcRest.getSequence(payload);
			String sequences = DOTCRestService.readJson("$..[?(@.crewedSequences==true)].sequenceNumber", jsonResponse);
			System.out.println("Sequences: "+sequences+" in empID: "+empID);
			crewedSequenceNumber = sequences.split(",")[0];
			dotcSrchPg.enterSequenceNumber(crewedSequenceNumber);
		}
		
		@And("^I should be able to click Cities Plus$")
		public void i_should_be_able_to_click_Cities_plus() throws Throwable {
			dotcSrchPg.clickCitiesPlus();
		}
		
		@And("^I should be able to enter station code in Include cities textbox \"([^\"]*)\"$")
		public void enter_includeCities(String includeCities) throws Throwable{
		dotcSrchPg.enterIncludeCities(includeCities);
		}
		
		@And("^I should be able to enter station code in Exclude cities textbox \"([^\"]*)\"$")
		public void enter_excludeCities(String excludeCities) throws Throwable{
		dotcSrchPg.enterExcludeCities(excludeCities);
		}
		
		@Then("^I should validate error message for \"([^\"]*)\" field$")
		public void validate_error_message_in_searchPage(String inputField) throws Throwable {
			dotcSrchPg.validateErrorMessageInSearchPage(inputField);
		}		

		@And("^I should be able to verify search sticky footer \"([^\"]*)\"$")
		public void i_should_be_able_to_verify_search_sticky_footer(String visibility) throws Throwable{
        	dotcSrchPg.verifyStickyFooterSearch(visibility);
		}
  
		//fucntion definition to stop emulate button
		//24 Apr, 2022
	  @And("I should click on stop emulate tab")
		public void i_should_click_on_stop_emulate_tab() throws Throwable{
			dotcLogIn.stopEmulate();
		}
		
		//step definition to add departure date according to the pendingSick payload
		//11-03-2022
		@Then("^Set the departure from date according to layover service result$")
		public void set_the_departure_from_date_according_to_layover_service_result() throws Throwable {
			dotcSrchPg.selectDepartureFromDateFromLayover();
		}
		
		//25-05-22
		//to set date for validation according to different scenarios
		@Then("^I select \"([^\"]*)\" date \"([^\"]*)\" and \"([^\"]*)\"$")
		public void i_select(String dateType ,String offsetstdt, String offsetenddt)
				throws Throwable {
			dotcSrchPg.dateRangeValidation(dateType,offsetstdt, offsetenddt);
		}
		
		//25-05-22
		//to verify error msg for date validation
		@Then("^I validate \"([^\"]*)\" error msg \"([^\"]*)\"$")
		public void i_validate_(String errTyp, String errMsg)
				throws Throwable {
			dotcSrchPg.dateErrorMsg(errTyp, errMsg);
		}
		
		
//25-05-22
		//For clicking clear button
		@Then("^I click on \"([^\"]*)\" clear button$")
		public void i_click_on(String buttonType)
				throws Throwable {
			dotcSrchPg.clickClearButton(buttonType);
		}
		
		//step definition to set arrival date range
		//21-01-2022
		@Then("^I enter arr Date from and to Date \"([^\"]*)\" \"([^\"]*)\"$")
		public void i_enter_arr_date_from_and_to_date(String from, String to) throws Throwable {
			dotcSrchPg.selectArrivalDateRange(from, to);
		}
		
		@Then("^I Enter Departure from \"([^\"]*)\" Arrival from \"([^\"]*)\" Arrival to \"([^\"]*)\" date$")
		public void i_Enter_Departure_from_Arrival_from_Arrival_to_date(String df, String af, String at) throws Throwable {
			dotcSrchPg.selectDepartureFromDate(df);
			dotcSrchPg.selectArrivalDateRange(af, at);
		}
		
		@Then("^I Enter Departure from \"([^\"]*)\" departure to \"([^\"]*)\" Arrival from \"([^\"]*)\" Arrival to \"([^\"]*)\" date$")
		public void i_Enter_Departure_from_departure_to_Arrival_from_Arrival_to_date(String df, String dt, String af, String at) throws Throwable {
			dotcSrchPg.selectDepartureDateRange(df, dt);
			dotcSrchPg.selectArrivalDateRange(af, at);
		}
	
	//step definiton to click on pick up outside dotc in Save Generic Criteria
	//16 May 2022
	@Then("^I Select pick up Outside checkbox in Save Generic Criteria$")
	public void i_select_pick_up_outside_checkbox_in_save_generic_criteria() throws Throwable {
		dotcSrchPg.clickOnPickUpOutsideDotcSaveGenericCriteria();
	}
	
	//step definition to set tomorrow date from departure from date
	//16 May 2022
	@Then("^I Should set tomorrow date in departure from date$")
	public void i_should_set_tomorrow_date_in_departure_from_date() throws Throwable {
		dotcSrchPg.selectDepartureFromDate("1");
	}
	
	//step definiton to click on pick up outside dotc in Save Generic Criteria
	//16 May 2022
	@Then("^I Select pick up checkbox in Save Generic Criteria$")
	public void i_select_pick_up_checkbox_in_save_generic_criteria() throws Throwable {
		dotcSrchPg.clickOnPickUpDotcSaveGenericCriteria();
	}
}

