package com.DOTC.stepDefinitions;

import com.DOTC.pageObjects.DOTCHistoryScreen;

import cucumber.api.java.en.Then;

public class DOTCHistorySteps extends DOTCMasterSteps {
	
	DOTCHistoryScreen dotcHistScr = new DOTCHistoryScreen();
	
	// step definition to validate history page
	//23-03-2022
	@Then("I should validate History page")
	public void i_should_validate_history_page() throws Throwable {
		dotcHistScr.validateHistoryPage();
	}
	
	// step definition to validate default value for start date
	//23-03-2022
	@Then("I should validate default values for start and end date")
	public void i_should_validate_default_values_for_start_and_end_date() throws Throwable {
		dotcHistScr.validateHistoryStartDateHasYestedayDate();
		dotcHistScr.validateHistoryEndDateHasYestedayDate();
	}
}