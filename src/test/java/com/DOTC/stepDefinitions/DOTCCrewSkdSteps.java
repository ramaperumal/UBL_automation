package com.DOTC.stepDefinitions;

import com.DOTC.pageObjects.DOTCCalendarScreen;
import com.DOTC.pageObjects.DOTCCommon;
import com.DOTC.pageObjects.DOTCCrewSchedulerPage;
import com.DOTC.pageObjects.DOTCLogInScreen;
import com.DOTC.pageObjects.DOTCSearchScreen;

import cucumber.api.java.en.Then;

public class DOTCCrewSkdSteps extends DOTCMasterSteps {
	DOTCCommon dotcCmn = new DOTCCommon();
	DOTCLogInScreen dotcLogIn = new DOTCLogInScreen();
	DOTCCalendarScreen dotcCalendar = new DOTCCalendarScreen();
	DOTCSearchScreen dotcSrchPg = new DOTCSearchScreen();
	DOTCCrewSchedulerPage dotcCSPg = new DOTCCrewSchedulerPage();

	 @Then("^I click on the Admin link$")
	    public void i_click_on_the_admin_link() throws Throwable {
		 dotcCSPg.clickAdmin();
	    }
	 @Then("^I click on Designate Premium link$")
	    public void i_click_on_designate_premium_link() throws Throwable {
		 dotcCSPg.clickDesignatePremium();
	    }
	 @Then("^I select Start Date and End Date \"([^\"]*)\" \"([^\"]*)\"$")
	    public void i_select_start_date_and_end_date(String stdtoffset,String enddtoffset) throws Throwable {
		 dotcCSPg.selectDateRange(stdtoffset,enddtoffset);
	    }
	 @Then("^select the Four Part Bid Status to Search \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	    public void select_the_four_part_bid_status_to_search(String base, String equipment, String division, String seat) throws Throwable {
		 dotcCSPg.selectFourPartBidStatus(base,equipment,division,seat); 
	    }
	 @Then("^I click Search$")
	    public void i_click_search() throws Throwable {
		 dotcCSPg.clickSearch();
	    }
	 @Then("^choose sequences and mark it as Premium \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	    public void choose_sequences_and_mark_it_as_premium(String base, String equipment, String division, String seat) throws Throwable  {
		 dotcCSPg.markAsPremium(base,equipment,division,seat);
	    }

}