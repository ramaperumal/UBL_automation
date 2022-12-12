package com.DOTC.stepDefinitions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.SkipException;

import com.DOTC.pageObjects.DOTCCalendarScreen;
import com.DOTC.pageObjects.DOTCCommon;
import com.DOTC.pageObjects.DOTCLogInScreen;
import com.DOTC.pageObjects.DOTCRestService;
import com.DOTC.pageObjects.DOTCSearchScreen;
import com.DOTC.pageObjects.SSOscreen;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DOTCLogInSteps {


	static Logger log = Logger.getLogger(DOTCCukeHooks.class);

	DOTCCommon dotcCmn = new DOTCCommon();
	DOTCRestService dotc = new DOTCRestService();
	DOTCLogInScreen dotcLogIn = new DOTCLogInScreen();
	DOTCCalendarScreen dotcCalendar = new DOTCCalendarScreen();
	DOTCSearchScreen dotcSearchScr = new DOTCSearchScreen();
	ArrayList<String> empBidStatus = null;
	SSOscreen ssoLogin = new SSOscreen();
	String environment=null;

	public static String employeeID="";

	@Given("^pilotDOTC application has been Launched$")
	public void pilotdotc_application_has_been_Launched() throws Throwable {
		dotcCmn.launchApplication();
		dotcLogIn.setCredential();
		dotcLogIn.clickRemindMeLater();
	}

	@Given("^CrewScheduler application has been Launched$")
	public void CrewScheduler_application_has_been_Launched() throws Throwable{
		dotcCmn.launchCSApplication();
		dotcLogIn.setCredential();
		dotcLogIn.clickRemindMeLater();
	}
	
	@When("^I login with the pilot ID: \"([^\"]*)\"$")
	public void i_login_with_the_pilot_ID(String employeeID) throws Throwable {
		if(dotcLogIn.login(employeeID))
			System.out.println(String.format("LH %s loged in!", employeeID));
		else {
			System.out.println("Invalid employee ID");
			Assert.assertFalse(true);
		}
	}

	@When("^I login with the next employeeID from crewService by base is \"([^\"]*)\"$")
	public void i_login_with_the_next_employeeID_from_crewService_by_base_is(String arg1) throws Throwable {
		dotcLogIn.loginMultipleEmployees(arg1);
	}

	@Then("^I am at the DOTC/RAS landing page$")
	public void i_am_at_the_DOTC_RAS_landing_page() throws Throwable {
		dotcCmn.verifyObjExistence(DOTCCalendarScreen.DOTC_RAS, "DOTC/RAS is displayed in the Landing page");
		System.out.println("info icon is displayed");
	}

	@And("^I should be able to see corret color for activity$")
	public void i_should_be_able_to_see_corret_color_for_activity() {

	}
	
	@When("^I login with the a LH pilot base at \"([^\"]*)\"$")
	public void i_login_with_the_a_LH_pilot_base_at(String base) throws Throwable {
		ArrayList<Integer> randomNos = new ArrayList<Integer>();
		//dotc.getLHids(base);
		List<String> emp = TestData.listOfEmpIds.get(base);
		if (emp.isEmpty()) {
			System.out.println(String.format("No lh found for base %s", base));
			assertTrue(false);
		}
		
		while (randomNos.size() < emp.size()) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, emp.size());
			if (!randomNos.contains(randomNum)) {
				randomNos.add(randomNum);

				if (dotcLogIn.login(emp.get(randomNum))) {
					System.out.println(String.format("LH %s loged in!", emp.get(randomNum)));
					employeeID = emp.get(randomNum);
					TestData.empID = employeeID;
					break;
				} else if (randomNos.size() == emp.size()) {
					System.out.println("No valid empIDs in the list");
					assertTrue(false);
				}
			}
		}
	}
	
	
	 @When("^I login with the a LH pilot base at \"([^\"]*)\" and equipment at \"([^\"]*)\" and position \"([^\"]*)\" and division \"([^\"]*)\"$")
	    public void i_login_with_the_a_lh_pilot_base_at_something_and_equipment_at_something(String base, String eqp,String pos,String div) throws Throwable {
		 DOTCRestService services = new DOTCRestService();
			Integer emp = services.getLHid(base,eqp,pos,div);
			if (emp == null || emp == -1) {
				System.out.println(String.format("No lh found for base %s and equioment %s", base,eqp));
				assertTrue(false);
			}
			System.out.println(String.format("LH %s loged in!", emp));
			dotcLogIn.login(Integer.toString(emp));
	    }
	
	@Then("^I Logout from the application$")
	public void i_Logout_from_the_application() throws Throwable {
		dotcLogIn.clickOnLogOutLink();
	}
	
	@Then("^I login with the next pilot ID with same BidStatus: \"([^\"]*)\"$")
	public void i_login_with_the_next_pilot_ID_with_same_BidStatus(String arg1) throws Throwable {
		dotcLogIn.login(arg1);
	}
	
	
	@When("^I login with the a LH pilot having no Calander Activities today with base at: \"([^\"]*)\"$")
	public void i_login_with_the_a_LH_pilot_having_no_Calander_Activities_today_with_base_at(String base) throws Throwable {
		DOTCRestService services = new DOTCRestService();
		empBidStatus = services.getLHSameBidStatus(base);
		if (empBidStatus == null) {
			System.out.println(String.format("No lh found for base %s", base));
			assertTrue(false);
		}
		System.out.println(String.format("LH %s loged in!", empBidStatus.get(0)));
		dotcLogIn.timeOutValidation();
		dotcLogIn.login(empBidStatus.get(0));
		employeeID = empBidStatus.get(0);

	}
	
	@When("^I login with the a LH pilot having no Calander Activities today with base at: \"([^\"]*)\" at \"([^\"]*)\"$")
	public void i_login_with_the_a_LH_pilot_having_no_Calander_Activities_today_with_base_at_at(String base, String index) throws Throwable {
		DOTCRestService services = new DOTCRestService();
		empBidStatus = services.getLHSameBidStatus(base);
		if (empBidStatus == null) {
			System.out.println(String.format("No lh found for base %s", base));
			assertTrue(false);
		}
		System.out.println(String.format("LH %s loged in!", empBidStatus.get(1)));
		dotcLogIn.login(empBidStatus.get(1));
	}
	
	@When("^I login with the a LH pilot of same BidStatus base at: \"([^\"]*)\"$")
	public void i_login_with_the_a_LH_pilot_of_same_BidStatus_base_at(String base) throws Throwable {
		DOTCRestService services = new DOTCRestService();
		empBidStatus = services.getLHSameBidStatus(base);
		if (empBidStatus == null) {
			System.out.println(String.format("No lh found for base %s", base));
			assertTrue(false);
		}
		System.out.println(String.format("LH %s loged in!", empBidStatus.get(0)));
		dotcLogIn.login(empBidStatus.get(0));
	}
	@When("^I login with the a another LH pilot of same BidStatus$")
	public void i_login_with_the_a_another_LH_pilot_of_same_BidStatus() throws Throwable {
		dotcLogIn.login(empBidStatus.get(1));
	}

	@Given("^pilotDOTC application has been Launched at env \"([^\"]*)\" with subdomain \"([^\"]*)\"$")
	public void pilotdotc_application_has_been_Launched_at_env_with_subdomain(String env, String subDomain) throws Throwable {
		environment = env;
		dotcCmn.launchApplication(env, subDomain);
		//dotcLogIn.setCredentials();
	}

	@Then("^I should be navigated to SSO \"([^\"]*)\"$")
	public void i_should_be_navigated_to_SSO(String env) throws Throwable {
		if (env.length() < 1) {
			log.info("DEV does not have sso, so will pass!");
			ExtentTestManager.getTest().log(LogStatus.PASS, "DEV does not have sso, so will pass!");
			return;
		}
		if (ssoLogin.isThisSSOpage(1)) {
			log.info("Navigated to SSO successfully!");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated to SSO successfully!");
			
			assertTrue(true);
		}
		else { 
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Navigation to SSO failed!");
			assertTrue("Navigation to SSO failed!", false);
		}
	}

	@When("^I do SSO login$")
	public void i_do_SSO_login() throws Throwable {
		if (environment.equals("qa") || environment.equals("uat"))
			if (ssoLogin.login(TestData.qaItAdminId, TestData.qaItAdminPass, 4)) {
				log.info("SSO been tried successfully!");
				assertTrue(true);
			}
			else 
				assertTrue("SSO login failed!", false);
	}

	@When("^I do SSO login as \"([^\"]*)\" at env \"([^\"]*)\"$")
	public void i_do_SSO_login_as(String role, String env) throws Throwable {
		
		env=env.toLowerCase();
		//ExtentTestManager.getTest().log(LogStatus.SKIP, "SSO login in successfully as Role -itadmin at ENV -QA");
		//throw new SkipException("message");
		if (env.contains("qa") || env.equals("cs"))
			switch (role.toLowerCase()) {
			case "itadmin":
				if (ssoLogin.login(TestData.qaItAdminId, TestData.qaItAdminPass, 4)) {
					log.info("SSO been tried successfully!");
					ExtentTestManager.getTest().log(LogStatus.PASS, "SSO login in successfully as Role -itadmin at ENV -QA");
					assertTrue(true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "SSO  unsuccessfull to login as Role- itadmin at ENV-QA");
					assertTrue("SSO login failed!", false);
				}
				break;
			case "pilotadmin":
				if (ssoLogin.login(TestData.qaPilotAdminId, TestData.qaPilotAdminPass, 4)) {
					log.info("SSO been tried successfully!");
					ExtentTestManager.getTest().log(LogStatus.PASS, "SSO login successfully as Role-pilotadmin at ENV-QA");
					assertTrue(true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "SSO unsuccessfully to login as Role-pilotadmin at ENV-QA");
				
					assertTrue("SSO login failed!", false);
				}
				break;
			default:
				log.info(String.format("env: %s or role: %s doesn't exist!", env, role));
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception- Env: " +env + " or Role" +role + "doesn't exist");
				assertTrue(false);
			}
		else if (env.contains("stage"))
			switch (role.toLowerCase()) {
			case "itadmin":
				if (ssoLogin.login(TestData.stageItAdminId, TestData.stageItAdminPass, 4)) {
					log.info("SSO been tried successfully!");
					ExtentTestManager.getTest().log(LogStatus.PASS, "SSO login successfully as Role -itadmin at ENV-UAT");
					assertTrue(true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "SSO  unsuccessfull to login  as Role-itadmin at ENV-UAT");
					assertTrue("SSO login failed!", false);
				}
				break;
			case "pilotadmin":
				if (ssoLogin.login(TestData.stagePilotAdminId, TestData.stagePilotAdminPass, 4)) {
					log.info("SSO been tried successfully!");
					ExtentTestManager.getTest().log(LogStatus.PASS, "SSO login successfully as Role-pilotadmin at ENV-UAT");
					assertTrue(true);
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, "SSO unsuccessfully to login as Role- pilotadmin at ENV-UAT");
					assertTrue("SSO login failed!", false);
				}
				break;
			default:
				log.info(String.format("env: %s or role: %s doesn't exist!", env, role));
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception - ENV" +env + "Or Role" +role + "Doesn't exist");
				assertTrue(false);
				break;
			}		
		else
			{
			log.info(String.format("env: %s or role: %s doesn't exist! will proceed.",env,role));
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception - ENV" +env + "Or Role" +role + "Doesn't exist");
			}
		
		//Util.waitForLoad(driver);
	}

	@When("^I do SSO login with wrong credentials$")
	public void i_do_SSO_login_with_wrong_credentials() throws Throwable {
		if (environment.equals("qa") || environment.equals("uat"))
			if (ssoLogin.login("HAHA", "12321", 2)) {
				log.info("SSO been tried successfully!");
				ExtentTestManager.getTest().log(LogStatus.PASS, "Login with wrong credentials");
				assertTrue(true);
			}
			else {
				assertTrue("SSO login failed!", false);
		       ExtentTestManager.getTest().log(LogStatus.FAIL, "Login with wrong credentials failed");
			}
		else log.info(String.format("wrong environment %s!",environment));
	}

	@Then("^I should see the DOTC login page$")
	public void i_should_see_the_DOTC_login_page() throws Throwable {
		try {
			dotcLogIn.clickRemindMeLater();
			if (dotcLogIn.isThisLoginPage()) {
				log.info("Navigated to DOTC login page successfully!");
				assertTrue(true);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Failed-Navigated to DOTC login page failed!");
				assertTrue("Navigation to  DOTC login page failed!", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception- Navigated to DOTC login page failed!");
			assertTrue("Navigation to  DOTC login page failed!", false);
		}
	}

	@Then("^I should not see the DOTC login page$")
	public void i_should_not_see_the_DOTC_login_page() throws Throwable {
		try {
			dotcLogIn.isThisLoginPage();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Navigated to  DOTC login page so failed!");
			assertTrue("Navigated to  DOTC login page so failed!", false);
		} catch (Exception e) {
			ExtentTestManager.getTest().log(LogStatus.PASS, " Passed - Didn't Navigated to DOTC login page");
			log.info("Not Navigated to DOTC login page, will pass!");
			assertTrue(true);
		}
	}
	
	@When("^I login with the a LH pilot base \"([^\"]*)\" and equipment \"([^\"]*)\"$")
	public void i_login_with_the_a_LH_pilot_base_and_equipment(String base, String eqp) throws Throwable {
		DOTCRestService services = new DOTCRestService();
		Integer emp= services.getLHid(base, eqp);
		if (emp == null || emp == -1) {
			System.out.println(String.format("No lh found for base %s", base));
			assertTrue(false);
		}
		System.out.println(String.format("LH %s loged in!", emp));
		dotcLogIn.login(Integer.toString(emp));
	}
	
	public static String seqNo = "";
	
	// added code
	// 07-03-2022
	// get employee ID which has sequence with pending sick
	@When("I login with the pilot ID having pending sick sequence with base \"([^\"]*)\"$")
	public void i_login_with_the_pilot_ID_having_pending_sick_sequence(String base) throws Throwable {
		String empNo = "";
		for(int i = 0; i<DOTCRestService.empIdFromService.size(); i++) {
			seqNo = dotcSearchScr.checkForPendingSickSequence(DOTCRestService.empIdFromService.get(i), DOTCRestService.bidStatusOfEmpIdFromService.get(i));
			if(!seqNo.isEmpty()) {
				empNo = DOTCRestService.empIdFromService.get(i);
				if(dotcLogIn.login(empNo))
					break;
			}
		}
		System.out.println(String.format("LH %s loged in!", empNo));
	}
	

	//step definition to get LH pilot ids and their bidstatus using base
	// 16-03-2022
	@When("^I should get list of all LH pilot with base at \"([^\"]*)\" with bid-status$")
	public void I_get_list_of_all_LH_pilot_with_base_at_with_bitstatus(String base) throws Throwable {
		dotc.getLHDifferentBidStatus(base, 5);
		if (DOTCRestService.empIdFromService == null ) {
			System.out.println(String.format("No lh found for base %s", base));
			assertTrue(false);
		} else {
			System.out.println("Employee ID are: "+DOTCRestService.empIdFromService);
			System.out.println("Bidstatus are: "+DOTCRestService.bidStatusOfEmpIdFromService);
		}
	}
	
	//step definition to validate if there are any sequence for the employee
	//16-03-2022
	@Then("^I should check if there are any sequence for search filter \"([^\"]*)\"$")
	public void i_should_check_if_there_are_any_sequence_for_the_following_search_filter(String sitTime) throws Throwable {
		ArrayList<String> checkedBidStatus = new ArrayList<String>();
		for(int i = 0; i < DOTCRestService.empIdFromService.size(); i++) {
			String bidStatus = DOTCRestService.bidStatusOfEmpIdFromService.get(i);
			String empNumber = DOTCRestService.empIdFromService.get(i);
			if(!checkedBidStatus.contains(bidStatus)) {
				checkedBidStatus.add(bidStatus);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 2020-05-03
				
				Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Using today's date
				c.add(Calendar.DATE, 1); // Adding 1 day
				String departureFromDate = dateFormat.format(c.getTime());
				
				//get the template payload from json file
				String templatePayload = dotc.initializeTestDataFiles("searchSequencePayload.json");
				
				// modify data in json file
				String payload = dotc.modifyJson(templatePayload, "$.employeeID", empNumber);
				payload = dotc.modifyJson(payload, "$.sitTimeLessThanHours", sitTime);
				payload = dotc.modifyJson(payload, "$.startDateFrom", departureFromDate);
				
				String jsonResponse = dotc.getSequence(payload);
				String sequences = DOTCRestService.readJson("$..sequenceNumber", jsonResponse);
				System.out.println("Sequences: "+sequences+" in empID: "+empNumber);
				if(sequences.split(",").length > 0)
					dotcLogIn.login(empNumber);
				break;
			}
		}
	}

	//for Reserve pilot  login
	//16-03-22
		@Then("^I login with reserve pilot for \"([^\"]*)\"$")
		public void i_login_with_reserve_pilot_for(String arg1) throws Throwable {
		  dotcLogIn.loginReservePilotForRap(arg1);
    }
		
	// To login with LH ID having Sequence on current day
	@When("^I login with a LH pilot having sequence activity today of bidstatus \"([^\"]*)\"$")
	public void i_login_with_a_LH_pilot_with_sequence_today(String bidStatus) throws Throwable {
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
		dotcLogIn.login(empOnActivity);

	}

	@When("^I refresh DOTC home$")
	public void i_refresh_DOTC_home() throws Throwable {
		Util.defaultwait(10000);
		dotcCmn.refreshApplication();

	}
	
	@And("^I get cookie data for service$")
	public void i_get_cookie_data_for_service()throws Throwable {
		dotcLogIn.getCookieData();
	}
	//17May22
		@Then("^I login with sick LH pilot for \"([^\"]*)\"$")
		public void i_login_with_LH_pilot_for(String arg1) throws Throwable {
		  dotcLogIn.loginLHPilotForSICK(arg1);
	}
	
	// 25 May 2022
	@When("^I login with the a LH pilot base at \"([^\"]*)\" having no activity on current day$")
	public void i_login_with_the_a_LH_pilot_base_at_having_no_activity_on_current_day(String base) throws Throwable {
		dotc.getLHidswithNoActivity(base);
		ArrayList<Integer> randomNos = new ArrayList<Integer>();
		//dotc.getLHids(base);
		List<String> emp = DOTCRestService.rsvForRAP;
		System.out.println(emp);
		List<String> bidcard = DOTCRestService.rsvBidcard;
		if (emp.isEmpty()) {
			System.out.println(String.format("No lh found for base %s", base));
			assertTrue(false);
		}
			while (randomNos.size() < emp.size()) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, emp.size());
			if (!randomNos.contains(randomNum)) {
				randomNos.add(randomNum);
					if (dotcLogIn.login(emp.get(randomNum))) {
					System.out.println(String.format("LH %s loged in!", emp.get(randomNum)));
					TestData.empID = emp.get(randomNum);
					TestData.bidStatus = bidcard.get(randomNum);
					break;
				} else if (randomNos.size() == emp.size()) {
					System.out.println("No valid empIDs in the list");
					assertTrue(false);
				}
			}
		}
	}
}
