package com.DOTC.stepDefinitions;

import com.DOTC.pageObjects.DOTCCommon;
import com.DOTC.pageObjects.DOTCListScreen;
import com.DOTC.pageObjects.DOTCLogInScreen;

import cucumber.api.java.en.Then;

public class DOTCListSteps extends DOTCMasterSteps {

	DOTCCommon dotcCmn = new DOTCCommon();
	DOTCLogInScreen dotcLogIn = new DOTCLogInScreen();
	DOTCListScreen dotcList = new DOTCListScreen();

	@Then("^I clicked on List tab in landing page$")
	public void i_clicked_on_List_tab_in_landing_page() throws Throwable {
		dotcList.clickOnListTab();
	}

	@Then("^I clicked on Submit button$")
	public void i_clicked_on_Submit_button() throws Throwable {
		dotcList.clickOnSubmitBtn();
	}
//
//	@Then("^I Logout from the application$")
//	public void i_Logout_from_the_application() throws Throwable {
//		dotcList.clickOnLogOutLink();
//	}
	@Then("^I Verify that the pilot is seen in Pick Up DOTC list when ballots are turned On$")
	public void i_Verify_that_the_pilot_is_seen_in_Pick_Up_DOTC_list_when_ballots_are_turned_On() throws Throwable {
		dotcList.verifyExistanceOfPilotInList();
	}
	
	@Then("^I Verify that the old pilot id is seen in Pick Up DOTC list when ballots are turned On$")
	public void i_Verify_that_the_old_pilot_id_is_seen_in_Pick_Up_DOTC_list_when_ballots_are_turned_On() throws Throwable {
		dotcList.verifyExistanceOfOldPilotInList();
	}

	@Then("^I Verify that the pilot is not seen in Pick Up DOTC list when ballots are turned Off$")
	public void i_Verify_that_the_pilot_is_not_seen_in_Pick_Up_DOTC_list_when_ballots_are_turned_Off() throws Throwable {
		dotcList.verifyRemovalOfPilotFromList();
	}
	
	// code added
	// step definition to validate if Date filedname is replaced by Award Date filedname
	@Then("^I should validate if Award Date field is present$")
	public void i_should_validate_if_Award_Date_field_is_present() throws Throwable {
		dotcList.validateAwardDateField();
	}
	
	@Then("^I should validate options in Aircraft dropdown is \"([^\"]*)\"$")
	public void i_should_validate_options_in_aircraft_dropdown_is(String eqpIds) throws Throwable {
		dotcList.validateAircraftOptions(eqpIds);
	}
	
	@Then("^I should validate options in Positions dropdown is \"([^\"]*)\"$")
	public void i_should_validate_options_in_positions_dropdown_is(String eqpIds) throws Throwable {
		dotcList.validatePositionsOptions(eqpIds);
	}
	
	// step definition to validate data for service and UI for pick up dotc
	@Then("^I should validate Pick up DOTC list$")
	public void i_should_validate_pick_up_dotc_list() throws Throwable {
		dotcList.validatePickUpDOTC();
	}
	
	// step definition to click on pick up outside dotc tab
	@Then("^I should click on Pick up outside tab$")
	public void i_should_click_on_pick_up_outside_tab() throws Throwable {
		dotcList.clickPickUpOutsideTab();
	}
	
	// step definition to validate data for service and UI for pick up dotc
	@Then("^I should validate Pick up Outside DOTC list$")
	public void i_should_validate_pick_up_outside_dotc_list() throws Throwable {
		dotcList.validatePickUpOutsideDOTC();
	}
	
	// step definition to click on pick up outside dotc tab
	@Then("^I should click on Open Time tab$")
	public void i_should_click_on_open_time_tab() throws Throwable {
		dotcList.clickOpenTimeTab();
	}
	
	// step definition to validate Open time list
	@Then("^I should validate Open time list$")
	public void i_should_validate_open_time_list() throws Throwable {
		dotcList.validateOpenTime();
	}
	
	// step definition to change combination
	@Then("^I should select different combination for \"([^\"]*)\"$")
	public void i_should_select_different_combination_for(String parameters) throws Throwable {
		dotcList.changeValues(parameters);
	}
	
	// step definition to validate position and aircraft group
	@Then("^I should validate Aircraft and position group in open time$")
	public void i_should_validate_aircraft_and_position_group_in_open_time() throws Throwable {
		dotcList.validateAircraftAndPositionGroup();
	}
}
