package com.DOTC.stepDefinitions;

import com.DOTC.pageObjects.DOTCCalendarScreen;
import com.DOTC.pageObjects.DOTCCommon;
import com.DOTC.pageObjects.DOTCFOSTransactions;
import com.DOTC.pageObjects.DOTCLogInScreen;
import com.DOTC.pageObjects.DOTCMyInfoScreen;
import com.DOTC.pageObjects.DOTCRestService;
import com.DOTC.pageObjects.DOTCSearchScreen;
import com.DOTC.supportLibraries.Util;

import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.relevantcodes.extentreports.LogStatus;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class DOTCCalendarSteps extends DOTCMasterSteps {
	DOTCCommon dotcCmn = new DOTCCommon();
	DOTCLogInScreen dotcLogIn = new DOTCLogInScreen();
	DOTCCalendarScreen dotcCalendar = new DOTCCalendarScreen();
	DOTCSearchScreen dotcSrchPg = new DOTCSearchScreen();
	DOTCMyInfoScreen calmyInfo = new DOTCMyInfoScreen();
	DOTCRestService dotcRest = new DOTCRestService();
	DOTCFOSTransactions dotcfos =new DOTCFOSTransactions();
	public String seqNumCurrent="";
	public String seqDate="";
	public String dateVacation="";
	public String seqEndDate="";
	public String seqEndDatee="";

	@When("^I Click on Calendar tab$")
	public void i_Click_on_Calendar_tab() throws Throwable {
		dotcCalendar.clickOnCalendar();
	}
	
	@Then("^I verify the valid LH is emulated successfully$")
	public void i_verify_the_valid_LH_is_emulated_successfully() throws Throwable {
	    dotcCalendar.emulateLH_Validation();
	}
	@Then("^I verify the current date is the first day of the month$")
	public void i_verify_the_current_date_is_the_first_day_of_the_month() throws Throwable {
		if (dotcCalendar.verifyCurrentDateAsFirstDateOfTheMonth()) {
			dotcCalendar.navigateToNextMonth();
		}
	}

	@When("^I clicked on the pilot name at the right top$")
	public void i_clicked_on_the_pilot_name_at_the_right_top() throws Throwable {
		dotcCalendar.clickOnPilotName();
	}

	@Then("^I should be able to see my information Popup$")
	public void i_should_be_able_to_see_my_information_Popup() throws Throwable {
		dotcCalendar.PilotInfoDisplay();
	}

	@Then("^I shuld be able to see my information and Qualification$")
	public void i_shuld_be_able_to_see_my_information_and_Qualification() throws Throwable {
		// calmyInfo.validatePilotMyInfo();
		calmyInfo.validatePilotQualInfo();

	}

	@Then("^I validate current calander contractual month$")
	public void i_validate_current_calander_contractual_month() throws Throwable {
		dotcCalendar.validateCalInformationForCurrentMonth();

	}

	@Then("^I validate pilots information for the Current Contractual month$")
	public void i_validate_pilots_information_for_the_Current_Contractual_month() throws Throwable {
		calmyInfo.validateMyInformationForCurrentMonth();
	}

	@Then("^I validate calendar previous/next contractual month$")
	public void i_validate_calendar_previous_next_contractual_month() throws Throwable {
		/*Calendar cal = Calendar.getInstance();
		int date1 = cal.get(Calendar.DAY_OF_MONTH);
		if (date1 >= 16) {
			dotcCalendar.validateCalInformationForNextMonth();
		}
		if (date1 < 16) {
			dotcCalendar.validateCalInformationForPrevMonth();
		}*/
		Map<String, String> CMD = dotcRest.contractMonthDetails();
		String cm = CMD.get("currentCntrctMnth");
		
		String payload = "{\r\n"
				+ "    \"contractMonths\": [\r\n"
				+ "                \""+cm+"\"\r\n"
				+ "    ],\r\n"
				+ "    \"crewMemberKeyDTO\": {\r\n"
				+ "        \"airlineCode\": \"AA\",\r\n"
				+ "        \"employeeNumber\":"+TestData.empID+"\r\n"
				+ "    }\r\n"
				+ "}";
		
		
		String jsonresponse = dotcRest.getCrewmember(payload);
		
		String div = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentDivision", jsonresponse);
		String eqp = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentEquipment", jsonresponse);
		String pos = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentPosition", jsonresponse);
		String ba = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentBase", jsonresponse);
		String bidStatus = ba+"/"+eqp+"/"+pos+"/"+div;
		
		System.out.println(bidStatus);
		
		if(dotcRest.checkBaseInitialized(bidStatus.split("/")[0]).equalsIgnoreCase("true")) {
			dotcCalendar.validateCalInformationForNextMonth();
		} else {
			System.out.println("Next month data is not available");
		}
	}

	// 
	@Then("^I clicked on Calendar previous/next arrow$")
	public void i_clicked_on_Calendar_previous_next_arrow() throws Throwable {
		/*Calendar cal = Calendar.getInstance();
		int date1 = cal.get(Calendar.DAY_OF_MONTH);
		
		if (date1 >= 16) {
			dotcCalendar.navigateToNextMonth();
		}
		if (date1 < 16) {
			if (dotcCalendar.verifyCurrentDateAsFirstDateOfTheMonth()) {
				System.out.println("Today is the first day of the month");
			}
			else {
			dotcCalendar.navigateToPrevMonth();
			}
		}*/
		Map<String, String> CMD = dotcRest.contractMonthDetails();
		String cm = CMD.get("currentCntrctMnth");
		
		String payload = "{\r\n"
				+ "    \"contractMonths\": [\r\n"
				+ "                \""+cm+"\"\r\n"
				+ "    ],\r\n"
				+ "    \"crewMemberKeyDTO\": {\r\n"
				+ "        \"airlineCode\": \"AA\",\r\n"
				+ "        \"employeeNumber\":"+TestData.empID+"\r\n"
				+ "    }\r\n"
				+ "}";
		
		
		String jsonresponse = dotcRest.getCrewmember(payload);
		
		String div = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentDivision", jsonresponse);
		String eqp = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentEquipment", jsonresponse);
		String pos = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentPosition", jsonresponse);
		String ba = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentBase", jsonresponse);
		String bidStatus = ba+"/"+eqp+"/"+pos+"/"+div;
		
		System.out.println(bidStatus);
		
		if(dotcRest.checkBaseInitialized(bidStatus.split("/")[0]).equalsIgnoreCase("true")) {
			dotcCalendar.navigateToNextMonth();
		} else {
			System.out.println("Next month data is not available");
		}
	}

	@Then("^I validate pilots information for previous/next contractual month$")
	public void i_validate_pilots_information_for_previous_next_contractual_month() throws Throwable {
		/*Calendar cal = Calendar.getInstance();
		int date1 = cal.get(Calendar.DAY_OF_MONTH);
		if (date1 >= 16) {
			calmyInfo.validateMyInformationForNextMonth();
		} 
		if (date1 < 16) {
			calmyInfo.validateMyInformationForPrevMonth();
		}*/
		Map<String, String> CMD = dotcRest.contractMonthDetails();
		String cm = CMD.get("currentCntrctMnth");
		
		String payload = "{\r\n"
				+ "    \"contractMonths\": [\r\n"
				+ "                \""+cm+"\"\r\n"
				+ "    ],\r\n"
				+ "    \"crewMemberKeyDTO\": {\r\n"
				+ "        \"airlineCode\": \"AA\",\r\n"
				+ "        \"employeeNumber\":"+TestData.empID+"\r\n"
				+ "    }\r\n"
				+ "}";
		
		
		String jsonresponse = dotcRest.getCrewmember(payload);
		
		String div = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentDivision", jsonresponse);
		String eqp = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentEquipment", jsonresponse);
		String pos = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentPosition", jsonresponse);
		String ba = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentBase", jsonresponse);
		String bidStatus = ba+"/"+eqp+"/"+pos+"/"+div;
		
		System.out.println(bidStatus);
		
		if(dotcRest.checkBaseInitialized(bidStatus.split("/")[0]).equalsIgnoreCase("true")) {
			calmyInfo.validateMyInformationForNextMonth();
		} else {
			System.out.print("Next month data is not available");
		}
	}

	@When("^I clicked on the given sequence number$")
	public void i_clicked_on_the_given_sequence_number() throws Throwable {
		dotcCalendar.clickOnMySequence();
	}

	@Then("^I am at the sequence page$")
	public void i_am_at_the_sequence_page() throws Throwable {
		dotcCalendar.validateSequencePage();
	}

	@Then("^I validates the pilots debrief times$")
	public void i_validates_the_pilots_debrief_times() throws Throwable {
		dotcCalendar.ValidateDepartureDate(null);
		dotcCalendar.ValidateArrivalDate(null);
	}

	@Then("^I should be able to see current month and year$")
	public void i_should_be_able_to_see_current_month_and_year() throws Throwable {
		Util.defaultwait(10000);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		int coef=+1;
		if(date.getDate()<20)
			coef=-1;
		c.add(Calendar.DATE, coef*5); 
		Date dateM5=c.getTime();
		DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);
		String formattedDate = dateFormat.format(date);
		String formattedDateM5= dateFormat.format(dateM5);
		log.info(String.format("checking if '%s/%s' is there",formattedDate,formattedDateM5));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Current Month and year displayed correctly");
		if (!dotcCalendar.elementApear(By.xpath(String.format("//*[.='%s']", formattedDate))) && !dotcCalendar.elementApear(By.xpath(String.format("//*[.='%s']", formattedDateM5))))
			Assert.fail();
	}

	@Then("^I should be able to see the calendar grid$")
	public void i_should_be_able_to_see_the_calendar_grid() throws Throwable {
		if (!dotcCalendar.isUp4Calendar())
			Assert.fail();
	}

	@Then("^Pickup DOTC and Pickup Outside DOTC ballot selections appear$")
	public void pickup_DOTC_and_Pickup_Outside_DOTC_ballot_selections_appear() throws Throwable {
		System.out.println("calling isUp4PickUpDOTC");
    
		if (!dotcCalendar.isUp4PickUpDOTC())
			Assert.fail();
		System.out.println("calling  validatePickupDotcToggle");
		if (!dotcCalendar.validatePickupDotcToggle())
			Assert.fail();
		
		System.out.println("calling isUp4PickUpOutsideDOTC");
		if (!dotcCalendar.isUp4PickUpOutsideDOTC())
			Assert.fail();
		
		System.out.println("calling validatePickupOutsideDotcToggle");
		if (!dotcCalendar.validatePickupOutsideDotcToggle())
			Assert.fail();
		//System.out.println("calling isUp4OffOutDOTC");
		//if (!dotcCalendar.isUp4OffOutDOTC())
		//	Assert.fail();
	}
	
	@And("^I click on Search tab$")
	public void i_click_on_Search_tab() throws Throwable {
		dotcCalendar.clickOnSearchTab();
	}
	

	@When("^I assign RAP activity for \"([^\"]*)\" date to \"([^\"]*)\" of \"([^\"]*)\"$")
	public void i_assign_RAP_activity_for_date_to_of(String date, String reserveEmpID, String base) throws Throwable {	    
		dotcRest.getRESVidswithNoActivity(base);
		String dateAct= dotcCalendar.getRapActivitydate(date);
	     dotcfos.createRAPactivity( dateAct, reserveEmpID);
	}
	
	@Then("^I validate color of RAP activity at \"([^\"]*)\" date$")
	public void i_validate_color_of_RAP_activity_at_date(String date) throws Throwable {
		//String dateAct= dotcCalendar.getRapActivitydate(date);
		dotcCalendar.validateRAPActivityColor(date);
		
}

@And("^I assign training activity for \"([^\"]*)\" date to \"([^\"]*)\" of \"([^\"]*)\"$")
public void i_assign_training_activity_for_date_to_of(String date, String reserveEmpID, String base) throws Throwable {
	//dotcRest.getRESVids(base);
	String bidcard = DOTCRestService.rsvBidcard.get(Integer.parseInt(reserveEmpID));
	dotcfos.createTrainingActivity(date, reserveEmpID , bidcard);
}
@Then("^I validate color of Training activity at \"([^\"]*)\" date$")
public void i_validate_color_of_Training_activity_at_date(String date) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    dotcCalendar.validateTrainingActivity(date);
}

@And("^I should be able to verify sequence color code in calendar$")
public void i_should_be_able_to_verify_sequence_color_code_in_calendar() throws Throwable {
	
	String empID = DOTCLogInScreen.employeeID;
	seqNumCurrent = (DOTCRestService.readJson("$..[?(@.employeeID=="+empID+")].sequenceNumber",TestData.searchSequencesResponse)).split(",")[0];
	seqDate = (DOTCRestService.readJson("$..[?(@.employeeID=="+empID+")].sequenceOriginDate",TestData.searchSequencesResponse)).split(",")[0];
	dotcCalendar.validateSeqColor(seqNumCurrent, seqDate);
}

@And("^I assign DFP for LH on current day$")
public void i_assign_DFP_for_LH_on_current_day() throws Throwable {
	
	String empID = DOTCLogInScreen.employeeID;
	DateFormat dateFormat = new SimpleDateFormat("dd");
	Calendar cal = Calendar.getInstance();
	String date = dateFormat.format(cal.getTime());
	dotcfos.createDFP(empID,date);
}
@And("^I assign absence for LH on current day$")
public void i_assign_absence_for_LH_on_current_day() throws Throwable {
	
	String empID = DOTCLogInScreen.employeeID;
	DateFormat dateFormat = new SimpleDateFormat("dd");
	Calendar cal = Calendar.getInstance();
	String date = dateFormat.format(cal.getTime());
	dotcfos.createAbsence(empID,date);
}

@And("^I assign vacation for LH on next day$")
public void i_assign_vacation_for_LH_on_next_day() throws Throwable {
	
	String empID = DOTCLogInScreen.employeeID;
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(new Date());
	calendar.add(Calendar.DATE, 1); 
	DateFormat dateFormat = new SimpleDateFormat("dd");
	dateVacation = dateFormat.format(calendar.getTime());
	dotcfos.createVacation(empID,dateVacation);
}


@And("^I should be able to validate credited removal color code in calendar$")
public void i_should_be_able_to_verify_credited_removal_color_code_in_calendar() throws Throwable {
	
	dotcCalendar.validateSeqRemovalColor(seqNumCurrent, seqDate);
}

@And("^I should be able to validate DFP color code in calendar$")
public void i_should_be_able_to_verify_DFP_color_code_in_calendar() throws Throwable {
	
	dotcCalendar.validateDFPColor(dotcfos.dfpDate);
}

@And("^I should be able to validate vacation color code in calendar$")
public void i_should_be_able_to_verify_vacation_color_code_in_calendar() throws Throwable {

	dotcCalendar.validateVacationColor(dotcfos.vacationStartDate);
}

@And("^I should be able to validate absence color code in calendar$")
public void i_should_be_able_to_verify_absence_color_code_in_calendar() throws Throwable {
	
	dotcCalendar.validateAbsenceColor(dotcfos.absStartDate);
	
}

	//step definition to click on history tab
	//23-03-2022
	@Then("I should click on History tab")
	public void i_should_click_on_history_tab() throws Throwable {
		dotcCalendar.clickOnHistoryTab();
	}
	
	//17May22
	@When("^I assign sick activity for \"([^\"]*)\" date to \"([^\"]*)\" of \"([^\"]*)\"$")
	public void i_assign_sick_activity_for_date_to_of(String date, String lhEmpID, String base) throws Throwable {	    
		
		dotcfos.createSickPuckInFOSwithDate(date, lhEmpID , base);
		
		
	}
	//17May22
	@Then("I validate sick header msg")
	public void i_validate_sick_header_msg()  throws Throwable {
		dotcCalendar.sickMsgHeader();
	}
	
	@When("^I assign RO Window for LH on current day of bidstatus \"([^\"]*)\" and login$")
	public void i_assign_RO_window(String bidStatus) throws Throwable{
		DOTCRestService services = new DOTCRestService();
		services.getLHidsWithSeq(bidStatus);
		String[] emp = (DOTCRestService.readJson("$.[?(@.durationInDays!=1 && @.employeeID!=0)].employeeID",
				TestData.searchSequencesResponse)).split(",");

		if (emp == null) {
			System.out.println(String.format("No lh found for base %s", bidStatus));
			assertTrue(false);
		}
		int randomNum = ThreadLocalRandom.current().nextInt(0, emp.length - 1);
		System.out.println(String.format("LH %s loged in!", emp[randomNum]));
		String empOnActivity = emp[randomNum];
		
		seqNumCurrent = (DOTCRestService.readJson("$..[?(@.employeeID=="+empOnActivity+")].sequenceNumber",TestData.searchSequencesResponse)).split(",")[0];
		seqDate = (DOTCRestService.readJson("$..[?(@.employeeID=="+empOnActivity+")].sequenceOriginDate",TestData.searchSequencesResponse)).split(",")[0];
		seqEndDate = (DOTCRestService.readJson("$..[?(@.employeeID=="+empOnActivity+")].sequenceEndDateTime.baseTime",TestData.searchSequencesResponse)).split(",")[0];
		seqEndDatee = seqEndDate.split("T")[0];
		System.out.println("end date of seq " +seqEndDatee);
		System.out.println("emp id :  "+empOnActivity+ "Seq Number :" +seqNumCurrent+ "seq date : " +seqDate);
		dotcfos.createROWindow(empOnActivity, seqNumCurrent, seqDate);
		dotcLogIn.login(empOnActivity);
	}
	
	@Then("^I validate the RO Window$")
	public void i_validate_RO_window() {
		
		dotcCalendar.validateROWindow(seqDate,seqEndDatee);
	}
	
	
	
	

}
