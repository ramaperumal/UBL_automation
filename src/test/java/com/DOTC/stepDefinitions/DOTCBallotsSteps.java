package com.DOTC.stepDefinitions;

import com.DOTC.pageObjects.DOTCCalendarScreen;
import com.DOTC.pageObjects.DOTCCommon;
import com.DOTC.pageObjects.DOTCCommonMethods;
import com.DOTC.pageObjects.DOTCLogInScreen;
import com.DOTC.pageObjects.DOTCSearchScreen;

import static org.testng.Assert.assertTrue;

import com.DOTC.pageObjects.DOTCBallotScreen;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DOTCBallotsSteps extends DOTCMasterSteps {

	DOTCCommon dotcCmn = new DOTCCommon();
	DOTCLogInScreen dotcLogIn = new DOTCLogInScreen();
	DOTCCalendarScreen dotcCalendar = new DOTCCalendarScreen();
	DOTCSearchScreen dotcSrchPg = new DOTCSearchScreen();
	DOTCBallotScreen dotcBlltPg = new DOTCBallotScreen();
	DOTCCommonMethods dotcCommon = new DOTCCommonMethods();



	@Then("^I should be able to check the Sequence Number Entered \"([^\"]*)\" for ballot \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_check_the_Sequence_Number_Entered_for_ballot(String arg1, String arg2, String arg3) throws Throwable {
		dotcBlltPg.validateSequenceNumberinBallot(arg1, arg2, arg3);
	}

	// @Then("^I should be able to check the edited Sequence Number Entered
	// \"([^\"]*)\"$")
	// public void
	// i_should_be_able_to_check_the_edited_Sequence_Number_Entered(String ballot)
	// throws Throwable {
	// dotcBlltPg.validateSequenceNumberEditText(ballot);

	// }

	@Then("^I should be able to Edit the request$")
	public void i_should_be_able_to_Edit_the_request() throws Throwable {
		// dotcBlltPg.clickEditBallotLink();
		// Util.defaultwait(10000);
		// System.out.println("Wait Time is over");
		dotcBlltPg.clickEditBallotLink();

	}

	@Then("^I should be able to check if Sit Time dropdown is disabled$")
	public void i_should_be_able_to_check_if_Sit_Time_dropdown_is_disabled() throws Throwable {
		dotcSrchPg.verifySitTimeDrpDwnDisabled();

	}

	@Then("^I should be able to click Create GenericButton$")
	public void i_should_be_able_to_click_Create_GenericButton() throws Throwable {
		dotcSrchPg.clickSaveGenericBtn();

	}

	@When("^I Click on Ballots tab$")
	public void i_Click_on_Ballots_tab() throws Throwable {
		dotcBlltPg.clickOnBallotsTab();

	}

	@Then("^I am Inside the Ballots Screen$")
	public void i_am_Inside_the_Ballots_Screen() throws Throwable {

	}

	@Then("^I Select Ballots type$")
	public void i_Select_Ballots_type() throws Throwable {
		dotcBlltPg.clickOnPickUpDOTCLink();
	}
	
	@Then("^I Select Ballots type as Pick Up Outside$")
	public void i_Select_Ballots_type_as_Pick_Up_Outside() throws Throwable {
		dotcBlltPg.clickOnPickUpOutsideLink();
	}

	@Then("^I Select Ballots type as Template$")
	public void i_Select_Ballots_type_as_Template() throws Throwable {
		dotcBlltPg.clickOnPickUpTemplateLink();
	}

	@Then("^I delete Pick Up DOTC Ballots from the Ballots list \"([^\"]*)\"$")
	public void i_delete_Pick_Up_DOTC_Ballots_from_the_Ballots_list(String arg1) throws Throwable {
		dotcBlltPg.DeleteDOTCBallots(arg1);
	}

	@Then("^I delete Pick Up Outside Ballots from the Ballots list \"([^\"]*)\"$")
	public void i_delete_Pick_Up_Outside_Ballots_from_the_Ballots_list(String arg1) throws Throwable {
		dotcBlltPg.DeleteOutsideBallots(arg1);
	}

	@Then("^I delete Template Ballots from the Ballots list \"([^\"]*)\"$")
	public void i_delete_Template_Ballots_from_the_Ballots_list(String arg1) throws Throwable {
		dotcBlltPg.DeleteTemplateBallots(arg1);
	}
	
//	@And("^I Verify ballot deletion in the ballot list$")
//	public void i_Verify_ballot_deletion_in_the_ballot_list() throws Throwable {
//		dotcBlltPg.VerifyDeleteDOTCBallots();
//	}
//	
//	@Then("^I Verify Pick Up Outside ballots are deleted from the list$")
//	public void i_Verify_Pick_Up_Outside_ballots_are_deleted_from_the_list() throws Throwable {
//		dotcBlltPg.VerifyDeleteOutsideBallots();
//	}
//
//	@Then("^I Verify Template ballots are deleted from the list$")
//	public void i_Verify_Template_ballots_are_deleted_from_the_list() throws Throwable {
//		dotcBlltPg.VerifyDeleteTemplateBallots();
//	}

	@And("^I Clicked on Update Button$")
	public void i_Clicked_on_Update_Button() throws Throwable {
		dotcBlltPg.clickOnUpdateButton();
		//dotcBlltPg.updateMessage();
	}

	@Then("^I verfy ballot creation in the ballot list$")
	public void i_verfy_ballot_creation_in_the_ballot_list() throws Throwable {
		//dotcBlltPg.verifyUpdateMessage();

	}

	@Then("^I verify Duplicate ballots in the ballot list$")
	public void i_verify_Duplicate_ballots_in_the_ballot_list() throws Throwable {
		//dotcBlltPg.DisabledUpdateButton();
		if(dotcBlltPg.VisibleUpdateButton())
			dotcCommon.setMessageAndScreenShot(true, "Update button visible");
		else
			dotcCommon.setMessageAndScreenShot(false, "Update button not-visible");
		dotcBlltPg.DuplicateErrorMessage();
		//dotcBlltPg.verifyDuplicateColorCode();

	}

	
	@Then("^I should be able to click Ballots tab$")
	public void i_should_be_able_to_click_Ballots_tab() throws Throwable {
		dotcBlltPg.clickOnBallotsTab();
	}

	@Then("^I should be able to validate departure date$")
	public void i_should_be_able_to_validate_departure_date() throws Throwable {
		dotcBlltPg.validateDepartureFromToDateEntered();
	}

	@Then("^I should able to validate Inclusive Timeframe for \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_able_to_validate_Inclusive_Timeframe_for(String arg1, String arg2) throws Throwable {
		dotcBlltPg.validationInclusiveTimeframeonBallot(arg1,arg2);
	}


	@Then("^I should be able to validate arrival date \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_arrival_date(String arg1) throws Throwable {
		dotcBlltPg.validateArrivalFromToDateEntered(arg1);
	}

	@Then("^I should be able to validate departure from date$")
	public void i_should_be_able_to_validate_departure_from_date() throws Throwable {
		dotcBlltPg.validateDepartureFromDateEntered();
	}

	@Then("^I should be able to validate Minimun and Maximum Duty Periods selected \"([^\"]*)\" \"([^\"]*)\" for \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Minimun_and_Maximum_Duty_Periods_selected_for(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		dotcBlltPg.getDutyperiodvalueOnBallot(arg1, arg2, arg3, arg4);
	}

	@Then("^I verify Departure from date without dash for Ballot Type \"([^\"]*)\"$")
	public void i_verify_Departure_from_date_without_dash_for_Ballot_Type(String arg1) throws Throwable {
		dotcBlltPg.verifyDepartureDateOnly(arg1);
	}
	@Then("^I verify Departure from date and Departure to date separated by dash for Ballot Type \"([^\"]*)\"$")
	public void i_verify_Departure_from_date_and_Departure_to_date_separated_by_dash_for_Ballot_Type(String arg1) throws Throwable {
		dotcBlltPg.verifyDepartureDateRange(arg1);
	}
	@Then("^I verify Arrival from date and Arrival to date separated by dash for Ballot Type \"([^\"]*)\"$")
	public void i_verify_Arrival_from_date_and_Arrival_to_date_separated_by_dash_for_Ballot_Type(String arg1) throws Throwable {
		dotcBlltPg.verifyArrivalDateRange(arg1);
	}
	
	@Then("^I verify Departure date range and Arrival date range are separated by dash for Ballot Type \"([^\"]*)\"$")
	public void i_verify_Departure_date_range_and_Arrival_date_range_are_separated_by_dash_for_Ballot_Type(String arg1) throws Throwable {
		dotcBlltPg.verifyDepArrDateRange(arg1);
	}

	
	@Then("^I verfy Generic ballot is updated successfully \"([^\"]*)\"$")
	public void i_verfy_Generic_ballot_is_updated_successfully(String arg1) throws Throwable {
		dotcBlltPg.verifyGenericRequestTitle(arg1);
	}

	@Then("^I verfy Specific ballot is updated successfully$")
	public void i_verfy_Specific_ballot_is_updated_successfully() throws Throwable {
		dotcBlltPg.verifySequenceNumber();
	}
	
	@Then("^I verfy both Generic and Specific ballot is updated successfully \"([^\"]*)\"$")
	public void i_verfy_both_Generic_and_Specific_ballot_is_updated_successfully(String arg1) throws Throwable {
		dotcBlltPg.verifyGenSpecificTitle(arg1);
	}
	
	
	@Then("^I should be able to verify TAFB values \"([^\"]*)\" and \"([^\"]*)\" on the Saved Ballot$")
	public void i_should_be_able_to_verify_TAFB_values_and_on_the_Saved_Ballot(String arg1, String arg2)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dotcBlltPg.verifyTAFBRangeOnBallot(arg1,arg2);
	}
	
	@Then("^I turn On the Pick Up DOTC button from ballots list in Ballots Screen$")
	public void i_turn_On_the_Pick_Up_DOTC_button_from_ballots_list_in_Ballots_Screen() throws Throwable {
		dotcBlltPg.clickBallotDOTCOffBtn();
	}
	
	@Then("^I turn Off the Pick Up DOTC button from ballots list in Ballots Screen$")
	public void i_turn_Off_the_Pick_Up_DOTC_button_from_ballots_list_in_Ballots_Screen() throws Throwable {
		dotcBlltPg.clickBallotDOTCOnBtn();
	}

	@Then("^I should be able to click Opt link$")
	public void i_should_be_able_to_click_Opt_link() throws Throwable {
		dotcBlltPg.clickOptBallotLink();
	}
	
	@Then("^I should be able to click Premium checkbox$")
	public void i_should_be_able_to_click_Premium_checkbox() throws Throwable {
		dotcBlltPg.clickPremiumChkBx();
	}

	@Then("^I should be able to click Submit button in Pop Up$")
	public void i_should_be_able_to_click_Submit_button_in_Pop_Up() throws Throwable {
		dotcBlltPg.clickPopUpSubmitBtn();
	}
	
	
	@Then("^I should be able to verify Premium symbol in ballot for Ballot Type \"([^\"]*)\" for \"([^\"]*)\"$")
	public void i_should_be_able_to_verify_Premium_symbol_in_ballot_for_Ballot_Type_for(String arg1, String arg2) throws Throwable {
		dotcBlltPg.verifyPremiumValuesOnBallot(arg1, arg2);
	}

	@Then("^I should be able to validate departure stations \"([^\"]*)\" for Ballot Type \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_departure_stations_for_Ballot_Type(String arg1, String arg2, String arg3) throws Throwable {
		dotcBlltPg.verifyDepStationValuesOnBallot(arg1,arg2,arg3);
	}

	
	@Then("^I should be able to click Pick Up Outside link$")
	public void i_should_be_able_to_click_Pick_Up_Outside_link() throws Throwable {
		dotcBlltPg.clickPicUpOutsideLink();
	}
	
	@Then("^I should be able to click Template link$")
	public void i_should_be_able_to_click_Template_link() throws Throwable {
		dotcBlltPg.clickTemplateLink();
	}
	
	@Then("^I should be able to validate calendar days values \"([^\"]*)\" and \"([^\"]*)\" for Ballot Type \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_calendar_days_values_and_for_Ballot_Type(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		dotcBlltPg.getCalendarDaysDisplayStatusOnBallot(arg1, arg2, arg3, arg4);
	}
	
	@Then("^I should be able to validate Paid Credit values \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" for Ballot Type  \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Paid_Credit_values_for_Ballot_Type(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
		dotcBlltPg.getPaidCreditDisplayStatusOnBallot(arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@Then("^I should be able to verify TAFB values for ballottype \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\" on the Saved Ballot \"([^\"]*)\"$")
	public void i_should_be_able_to_verify_TAFB_values_for_ballottype_and_on_the_Saved_Ballot(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		dotcBlltPg.getTAFBDisplayStatusOnBallot(arg1, arg2, arg3, arg4);
	}
	
	@Then("^I should click more options down arrow for ballottype \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_click_more_options_down_arrow_for_ballottype(String arg1, String arg2) throws Throwable {
		dotcBlltPg.clickMoreParameterArrow(arg1,arg2);
	}

	
	@Then("^I Clicked the edit button from the ballots list$")
	public void i_Clicked_the_edit_button_from_the_ballots_list() throws Throwable {
		dotcBlltPg.ClickOnEditButton();
	}
	
	@Then("^I should be able to click Pick Up DOTC link$")
	public void i_should_be_able_to_click_Pick_Up_DOTC_link() throws Throwable {
		dotcBlltPg.clickOnPickUpDOTCLink();
	}

	@Then("^I should be able to Edit the request for ballottype \"([^\"]*)\"$")
	public void i_should_be_able_to_Edit_the_request_for_ballottype(String arg1) throws Throwable {
		dotcBlltPg.clickEditBallotLinkBasedonBallotType(arg1);
	}
	@Then("^I should be able to click Create Generic button$")
	public void i_should_be_able_to_click_Create_Generic_button() throws Throwable {
		dotcBlltPg.clickCreateGenericBtn();
	}
	@Then("^I Verify that only sequence number is present in the search criteria$")
	public void i_Verify_that_only_sequence_number_is_present_in_the_search_criteria() throws Throwable {
		dotcBlltPg.verifySeqSrchCriteria();
	}
	@Then("^I should be able to validate ballot is created for Ballot Type \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_ballot_is_created_for_Ballot_Type(String arg1) throws Throwable {
		dotcBlltPg.createBallotsValidation(arg1);
	}
	@Then("^I should be able to validate ballot is deleted for Ballot Type \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_ballot_is_deleted_for_Ballot_Type(String arg1) throws Throwable {
		dotcBlltPg.deleteBallotsValidation(arg1);
	}
	@Then("^I delete the user story related ballots if present from \"([^\"]*)\" for \"([^\"]*)\"$")
	public void i_delete_the_user_story_related_ballots_if_present_from_for(String arg1, String arg2) throws Throwable {
		dotcBlltPg.deleteBallotsBaseOnBallotTitle(arg1,arg2);
	}
	
	@Then("^I should be able to validate Sit Time values \"([^\"]*)\" for Ballot Type \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Sit_Time_values_for_Ballot_Type_and(String arg1, String arg2, String arg3) throws Throwable {
		dotcBlltPg.verifySitTimeValuesOnBallot(arg1, arg2, arg3);
	}
	
	@Then("^I should be able to update ballot$")
	public void i_should_be_able_to_update_ballot() throws Throwable {
		dotcBlltPg.clickOnUpdateButton();
	}
	
	@Then("^I should be able to update \"([^\"]*)\" ballot$")
	public void i_should_be_able_to_update_ballot(String arg1) throws Throwable {
		dotcBlltPg.clickOnUpdateButton(arg1);
	}
	
	@Then("^I should be able to validate Layover values \"([^\"]*)\" \"([^\"]*)\" for Ballot Type \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Layover_values_for_Ballot_Type_and(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		dotcBlltPg.verifyLayoverValuesOnBallot(arg1, arg2, arg3, arg4);
	}
	
	@Then("^I should be able to click Opt link for \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_click_Opt_link_for_and(String arg1, String arg2) throws Throwable {
		dotcBlltPg.clickOptBallotLinkBasedonBallotType(arg1, arg2);
	}
	
	@Then("^I should be able to Edit the request for ballottype \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_Edit_the_request_for_ballottype(String arg1, String arg2) throws Throwable {
		dotcBlltPg.clickEditBallotLinkBasedonBallotType(arg1, arg2);
	}
	
	@Then("^I should be able to validate the Min \"([^\"]*)\" \"([^\"]*)\" and Max \"([^\"]*)\" \"([^\"]*)\" Total Credit in Ballot \"([^\"]*)\" for \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_the_Min_and_Max_Total_Credit_in_Ballot_for(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6) throws Throwable {
		dotcBlltPg.getTotalCreditDisplayStatusOnBallot(arg1, arg2, arg3, arg4, arg5, arg6);
	}
	
	@Then("^I should be able to validate departure date for ballot \"([^\"]*)\" \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_departure_date_for_ballot(String arg1, String arg2) throws Throwable {
		dotcBlltPg.validateDefaultDepartureDateEnteredinBallot(arg1, arg2);
	}
	
	@Then("^I should be able to validate No Work Days values \"([^\"]*)\" for Ballot Type \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_No_Work_Days_values_for_Ballot_Type_and(String arg1, String arg2, String arg3) throws Throwable {
		dotcBlltPg.verifyNoWorkDaysValuesOnBallot(arg1, arg2, arg3);
	}
	
	@Then("^I should be able to validate No Work Hours values \"([^\"]*)\" \"([^\"]*)\" for Ballot Type \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_No_Work_Hours_values_for_Ballot_Type_and(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		dotcBlltPg.verifyNoWorkHoursValuesOnBallot(arg1, arg2, arg3, arg4);
	}

	@Then("^I should be able to validate No Work Dates vales as per \"([^\"]*)\" \"([^\"]*)\" for Ballot Type \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_No_Work_Dates_vales_as_per_for_Ballot_Type_and(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		dotcBlltPg.verifyNoWorkDatesValuesOnBallot(arg1, arg2, arg3, arg4);
	}
	
	@Then("^I should be able to validate Legs Per DP values \"([^\"]*)\" and \"([^\"]*)\"	for Ballot Type \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_should_be_able_to_validate_Legs_Per_DP_values(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		dotcBlltPg.verifyMinandMaxLegsDPValuesOnBallot(arg1, arg2, arg3, arg4);
	}
	
	@Then("^I should be able to validate Legs Per Seq values \"([^\"]*)\" and \"([^\"]*)\"	for Ballot Type \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_should_be_able_to_validate_Legs_Per_Seq_values(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		dotcBlltPg.verifyMinandMaxLegsPerSeqValuesOnBallot(arg1, arg2, arg3, arg4);
	}
	
	@Then("^I should be able to validate DeadHeads \"([^\"]*)\" for Ballot Type \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_Should_be_able_to_validate_DeadHeadValue(String arg1, String arg2, String arg3) throws Throwable {
		dotcBlltPg.verifyDeadHeadValue(arg1, arg2, arg3);
	}
	
	@Then("^I should be able to validate ballot is created for Ballot Type \"([^\"]*)\" with \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_ballot_is_created_for_Ballot_Type(String arg1,String arg2) throws Throwable {
		dotcBlltPg.createBallotsValidation(arg1,arg2);
	}
	
	// 12 Aug 2022
	// udpated function
	@Then("^I verify Departure date range and Arrival date range are separated by dash for Ballot Type \"([^\"]*)\" with \"([^\"]*)\"$")
	public void i_verify_Departure_date_range_and_Arrival_date_range_are_separated_by_dash_for_Ballot_Type(String arg1,String arg2) throws Throwable {
		//dotcBlltPg.verifyDepArrDateRange(arg1,arg2);
		dotcBlltPg.verifyDepartureDateRange(arg1, arg2);
		dotcBlltPg.verifyArrivalDateRange(arg1, arg2);
	}
	
	@Then("^I verify Departure from date without dash for Ballot Type \"([^\"]*)\" with \"([^\"]*)\"$")
	public void i_verify_Departure_from_date_without_dash_for_Ballot_Type(String arg1, String arg2) throws Throwable {
		dotcBlltPg.verifyDepartureDateOnly(arg1,arg2);
	}
	
	@Then("^I verify Departure from date and Departure to date separated by dash for Ballot Type \"([^\"]*)\" with \"([^\"]*)\"$")
	public void i_verify_Departure_from_date_and_Departure_to_date_separated_by_dash_for_Ballot_Type(String arg1,String arg2) throws Throwable {
		dotcBlltPg.verifyDepartureDateRange(arg1,arg2);
	}
	
	@Then("^I verify Arrival from date and Arrival to date separated by dash for Ballot Type \"([^\"]*)\" with \"([^\"]*)\"$")
	public void i_verify_Arrival_from_date_and_Arrival_to_date_separated_by_dash_for_Ballot_Type(String arg1,String arg2) throws Throwable {
		dotcBlltPg.verifyArrivalDateRange(arg1,arg2);
	}
	
	@Then("^I should be able to click on Cities plus$")
	public void i_should_be_able_to_click_on_Cities_plus() throws Throwable {

		dotcBlltPg.clickOnCitiesPlus();
	}

	@Then("^I should be able to click on Layover Times plus$")
	public void i_should_be_able_to_click_on_Layover_Times_plus() throws Throwable {

		dotcBlltPg.clickOnLayoverTimesPlus();
	}

	@Then("^I should be able to enter Include Cities value \"([^\"]*)\"$")
	public void i_should_be_able_to_enter_Include_Cities_value(String arg1) throws Throwable {

		dotcBlltPg.enterIncludeCitiesValue(arg1);
	}

	@Then("^I should be able to enter Exclude Cities value \"([^\"]*)\"$")
	public void i_should_be_able_to_enter_Exclude_Cities_value(String arg1) throws Throwable {
		dotcBlltPg.enterExcludeCitiesValue(arg1);
	}

	@Then("^I should be able to select Layover Min Time from drop down \"([^\"]*)\"$")
	public void i_should_be_able_to_select_Layover_Min_Time_from_drop_down(String arg1) throws Throwable {

		dotcBlltPg.selectLayoverMinTimeDropdown(arg1);
	}

	@Then("^I should be able to select Layover Max Time from drop down \"([^\"]*)\"$")
	public void i_should_be_able_to_select_Layover_Max_Time_from_drop_down(String arg1) throws Throwable {

		dotcBlltPg.selectLayoverMaxTimeDropdown(arg1);
	}

	@Then("^I should be able to validate Include Cities \"([^\"]*)\" value for Ballot Type \"([^\"]*)\" and title \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Include_Cities_value_for_Ballot_Type_and_title(String arg1, String arg2,
			String arg3) throws Throwable {

		dotcBlltPg.validateIncludeCities(arg1, arg2, arg3);
	}

	@Then("^I should be able to validate Exclude Cities \"([^\"]*)\" value for Ballot Type \"([^\"]*)\" and title \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Exclude_Cities_value_for_Ballot_Type_and_title(String arg1, String arg2,
			String arg3) throws Throwable {

		dotcBlltPg.validateExcludeCities(arg1, arg2, arg3);
	}

	@Then("^I should be able to validate Layover Time Min \"([^\"]*)\" value for Ballot Type \"([^\"]*)\" and title \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Layover_Time_Min_value_for_Ballot_Type_and_title(String arg1, String arg2,
			String arg3) throws Throwable {

		dotcBlltPg.validateLayoverMinimumTime(arg1, arg2, arg3);
	}

	@Then("^I should be able to validate Layover Time Max \"([^\"]*)\" value for Ballot Type \"([^\"]*)\" and title \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Layover_Time_Max_value_for_Ballot_Type_and_title(String arg1, String arg2,
			String arg3) throws Throwable {

		dotcBlltPg.validateLayoverMaximumTime(arg1, arg2, arg3);

	}

	@When("^I enter invalid Layover Times value, Save Generic Criteria button should be disabled \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_enter_invalid_Layover_Times_value_Save_Generic_Criteria_button_should_be_disabled_and(String arg1,
			String arg2) throws Throwable {

		dotcBlltPg.verifySaveGenericCriteriaButtonClickable(arg1, arg2);
	}
	
	@Then("^I Should be able to see the list of Sequences$")
	public void i_Should_be_able_to_see_the_list_of_Sequences() throws Throwable {

		dotcBlltPg.verifySearchSequencesResult();
	}

	@Then("^I should be able to select a sequence from the sequencesList$")
	public void i_should_be_able_to_select_a_sequence_from_the_sequencesList() throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.selectSequenceFromSequencesList();
		}
	}

	@Then("^I should be able to click on Save Selected Sequences button$")
	public void i_should_be_able_to_click_on_Save_Selected_Sequences_button() throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.clickOnSaveSelectedSequences();
		}
	}

	@Then("^I should be able to select All Ballots Check Box$")
	public void i_should_be_able_to_select_All_Ballots_Check_Box() throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.clickOnAllBallotsCheckBox();
		}
	}

	@Then("^I should be able to click on Add button$")
	public void i_should_be_able_to_click_on_Add_button() throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.clickOnBallotsAddButton();
		}
	}

	@Then("^I should be able to verify the saved sequence for Ballot Type \"([^\"]*)\"$")
	public void i_should_be_able_to_verify_the_saved_sequence_for_Ballot_Type(String arg1) throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.validateSequenceSavedInBallot(arg1);
		}
	}

	@Then("^I should be able to click Pick Up DOTC link for sequences$")
	public void i_should_be_able_to_click_Pick_Up_DOTC_link_for_sequences() throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.clickOnPickUpDOTCLink();
		}
	}

	@Then("^I should be able to click Pick Up Outside link for sequences$")
	public void i_should_be_able_to_click_Pick_Up_Outside_link_for_sequences() throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.clickPicUpOutsideLink();
		}
	}

	@Then("^I should be able to click Template link for sequences$")
	public void i_should_be_able_to_click_Template_link_for_sequences() throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.clickTemplateLink();
		}
	}

	@Then("^I should be able to delete the saved sequence related ballot for \"([^\"]*)\"$")
	public void i_should_be_able_to_delete_the_saved_sequence_related_ballot_for(String arg1) throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.deleteAllSequenceBallot(arg1);
		}
	}

	
	@Then("^I should be able to update \"([^\"]*)\" ballot for sequences$")
	public void i_should_be_able_to_update_ballot_for_sequences(String arg1) throws Throwable {
		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.clickOnUpdateButton(arg1);
		}
	}


	@Then("^I delete the selected sequence related ballots if present from \"([^\"]*)\"$")
	public void i_delete_the_selected_sequence_related_ballots_if_present_from(String arg1) throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.deleteDuplicateSequenceBallot(arg1);
		}
	}
	
	@Then("^I should be able to validate Position \"([^\"]*)\" value for Ballot Type \"([^\"]*)\" and title \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Position_value_for_Ballot_Type_and_title(String arg1, String arg2,
			String arg3) throws Throwable {

		dotcBlltPg.validatePositionValueInBallot(arg1, arg2, arg3);
	}

	@Then("^I should be able to validate Division \"([^\"]*)\" value for Ballot Type \"([^\"]*)\" and title \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_Division_value_for_Ballot_Type_and_title(String arg1, String arg2,
			String arg3) throws Throwable {

		dotcBlltPg.validateDivisionValueInBallot(arg1, arg2, arg3);
	}
	
	@Then("^I should be able to click on cancel update button$")
	public void i_should_be_able_to_click_on_cancel_update_button() throws Throwable {
		dotcBlltPg.clickOnCancelUpdateButton();
	}
	
	@Then("^I should be able to verify update generic button$")
	public void i_should_be_able_to_verify_update_generic_button()throws Throwable{
		dotcBlltPg.VerifyUpdateGenericButton();
	}
	
	@Then("^I click on cancel button for \"([^\"]*)\"$")
	public void  i_click_on_cancel_button ( String arg1) throws Throwable{
		dotcBlltPg.clickCanceButtonBasedOnBallotType(arg1);
	}
	@And("^I verify the unsaved changes pop up$") 
	public void i_verify_the_unsaved_changes_pop_up() throws Throwable{
		dotcBlltPg.VerifyUnsavedChangespopUp();
	}
	@Then("^I click on close icon$")
	public void i_click_on_close_icon ()throws Throwable{
		dotcBlltPg.clickOnCloseButton();
		
	}
	@Then("^I should be able to click on the notification time slider$") 
	public void i_should_be_able_to_click_on_the_notification_time_slider() {
		dotcBlltPg.clickOnNotificationSlider();
	}
	
	@Then("I should be able to verify notification time sub text$")
	public void i_should_be_able_to_verify_notification_time_sub_text() {
		dotcBlltPg.verifyNotificationTimeSubText();
	}
	@Then("I should be able to verify default hour value$")
	public void i_should_be_able_to_verify_default_hour_value() {
		dotcBlltPg.verifyNotificationDefaultHour();
	}
	
	@Then("I should be able to verify default minutes value$")
	public void i_should_be_able_to_verify_default_minutes_value() {
		dotcBlltPg.verifyNotificationDefaulMinute();
	}
	
	
	@Then("^I should be able to deselect a sequence from the sequencesList$")
	public void i_should_be_able_to_deselect_a_sequence_from_the_sequencesList() throws Throwable {

		if (dotcBlltPg.isSequencesPresent) {
			dotcBlltPg.deselectSequenceFromSequencesList();
		}
	}
	
//16May22
	@Then("^I should be able to validate generic criteria order for \"([^\"]*)\" with title \"([^\"]*)\"$")
	public void i_should_be_able_to_validate_generic_criteria_order(String agr1, String arg2) {
		dotcBlltPg.searchRows(agr1, arg2);
	}
	
	// 29 June 2022
	@Then("^I delete all the user story from \"([^\"]*)\"$")
	public void i_delete_all_user_story_from_ballot(String ballot) {
		dotcBlltPg.deleteAllBallots(ballot);
	}
	
	@Then("^I turn On the Pick Up Outside DOTC button from ballots list in Ballots Screen$")
	public void i_turn_On_the_Pick_Up_Outside_DOTC_button_from_ballots_list_in_Ballots_Screen() throws Throwable {
		dotcBlltPg.clickBallotOutSideDOTCOffBtn();
	}
}