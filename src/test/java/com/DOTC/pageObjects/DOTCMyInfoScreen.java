package com.DOTC.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;
import junit.framework.Assert;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DOTCMyInfoScreen extends DOTCCommon {

	DOTCRestService services = new DOTCRestService();
	DOTCLogInScreen dotclogin = new DOTCLogInScreen();
	public String jsonRespMonthwise = "";
	String employeeID;

	// :::::::::::::::::::::: Elements Used in My Information Page

	@FindAll({ @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[contains(@class,'today')]")

	})
	WebElement calCurrentDate;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class='fc-right']/button")

	})
	WebElement calNextNavigationButton;
	
	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'fc-center']//h2[contains(.,'2020')]") })

	WebElement calMonthHeader;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'col-sm-12 header-pilot-info']//a[1]") })

	WebElement pilotName;

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
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(.,'Employee ID:')]//parent::div//span[2]") })

	WebElement myInfoEmpId;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(.,'SEN:')]//parent::div//span[2]")

	})
	WebElement myInfoSEN;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(.,'Bid Status:')]//parent::div//span[2]")

	})
	WebElement myInfoBidStatus;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'pilot-info-popup']//div//span[contains(.,'PPROJ:')]")

	})
	WebElement myInfoPProj;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[@class = 'pilot-info-popup']//div[2]//span[contains(.,'PROJ:')]//parent::div//span[2]")

	})
	WebElement myInfoProj;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'pilot-info-popup']//div//span[contains(.,'VMAX:')]")

	})
	WebElement myInfoVMax;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div//span[contains(.,'IMAX:')]//parent::div//span[2]")

	})
	WebElement myInfoIMax;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(.,'VCBANK:')]//parent::div//span[2]")

	})
	WebElement myInfoVCBank;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(.,'PVD:')]//parent::div//span[2]")

	})
	WebElement myInfoPVD;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class = 'pilot-info-popup']//div//span[contains(.,'SICK:')]")

	})
	WebElement myInfoSICK;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(.,'QUALS:')]//parent::div//span[2]")

	})
	WebElement myInfoQUALS;

	@CacheLookup
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[contains(.,'LNDG EXP:')]//parent::div//span[2]")

	})
	WebElement myInfoLandingExp;

	@CacheLookup
	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[@class = 'pilot-info-popup']//div//span[contains(.,'PSPT EXP:')]")

	})
	WebElement myInfoPSPTExp;

	public DOTCMyInfoScreen() {
		this.driver = super.driver;
		PageFactory.initElements(driver, this);

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
	


	public void validatePilotMyInfo() {
		try {

			if (pilotInfoHeader.getText().startsWith("My Information For")) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "My Information info header",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Valid My Information Header", true);

			}

			else {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "My Information info",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Valid My Information Header", false);
			}

			Util.waitForLoad(driver);

		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	public void validatePilotQualInfo() {
		try {
			if (pilotQualHeader.getText().equalsIgnoreCase("My Qualifications")) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "My Qualification info",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Valid My Qualification Header", true);

			}

			else {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "My Qualification info",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Invalid My Qualification Header", false);
			}

			Util.waitForLoad(driver);

		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	/**
	 * Function for Fetching Pilot information from CCS service
	 * "CrewMemberService/v4/getCrewMember"
	 */

	public String getCrewMemberJsonResponse(String month) {
		String jsonresponse = "";
		try {

			this.employeeID = DOTCLogInScreen.employeeID;
			String employeeNum = employeeID;
			System.out.println("The pilot id is:  " + employeeID);
			//String payloadJson = "{ \"employeeNumber\":" + employeeNum + "}";
			String payloadJson = "{\r\n"
			+ "    \"contractMonths\": [\r\n"
			+ "                \""+month+"\"\r\n"
			+ "    ],\r\n"
			+ "    \"crewMemberKeyDTO\": {\r\n"
			+ "        \"airlineCode\": \"AA\",\r\n"
			+ "        \"employeeNumber\":"+employeeNum+"\r\n"
			+ "    }\r\n"
			+ "}";
			System.out.println("Employee number for getCrewMemeber is: "+employeeNum);
			//System.out.println(payloadJson);
			jsonresponse = services.getCrewmember(payloadJson);
			jsonRespMonthwise = jsonresponse;
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return jsonresponse;
	}

	/**
	 * Function for Validating Pilots information for current month
	 */

	public void validateMyInformationForCurrentMonth() {
		System.out.println("====== Current month My info ========");
		try {

			// ******* Reading from Service

			Month curmonth = Util.getCurrentMonth();
			Integer currentYear = Util.getCurrentYear();
			String strCurrentMonth = curmonth.toString().substring(0, 3) + currentYear.toString();
			validateMyInfo(strCurrentMonth, "C");
		} catch (Exception ex) {
			Assert.assertFalse("Exception occurred in method validateMyInformationForCurrentMonth", true);
			ex.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "My Info Validation for current month",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}

	public void validateMyInfo(String strMonth, String monType) {
		try {
			// ******* Reading from UI
			String empIdOnlyFromUI = myInfoEmpId.getText().trim();
			String senOnlyFromUI = myInfoSEN.getText().trim();
			String bidStatusOnlyFromUI = myInfoBidStatus.getText().trim();
			String projOnlyFromUI = myInfoProj.getText().trim();
			String IMaxOnlyFromUI = myInfoIMax.getText().trim();
			String qualsFromUI = myInfoQUALS.getText();
			String landingExpFromUI = myInfoLandingExp.getText();
			String onlvBank = myInfoVCBank.getText();
			String onlypvd = myInfoPVD.getText();
			
			String jsonresponse = getCrewMemberJsonResponse(strMonth);
			String employeeIdFromService = DOTCRestService.readJson("$.employeeID", jsonresponse);
			System.out.println("The Employee ID from service is: " + employeeIdFromService+", employee ID from UI is: "+empIdOnlyFromUI);
			String SENFromService = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='"+monType+"')].seniorityNumber", jsonresponse);
			System.out.println("The SEN number from service is: " + SENFromService+", SEN number from UI is: "+senOnlyFromUI);
			
			String div = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentDivision", jsonresponse);
			String eqp = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentEquipment", jsonresponse);
			String pos = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentPosition", jsonresponse);
			String ba = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentBase", jsonresponse);
			String bidStatusFromService = ba+"/"+eqp+"/"+pos+"/"+div;
			
			System.out.println("The bidStatus from service is: " + bidStatusFromService+", UI is: "+bidStatusOnlyFromUI);
			
			String projInHoursFromService = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='"+monType+"')].actualPayProjection", jsonresponse);
			projInHoursFromService = String.format("%.2f", (int)(Float.parseFloat(projInHoursFromService)/60)+(Float.parseFloat(projInHoursFromService)%60)/100);
			System.out.println("The PROJ value from service is: " + projInHoursFromService+", PROJ value from UI is: "+projOnlyFromUI);

			String iMaxInHoursFromService = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='"+monType+"')].ipMax", jsonresponse);
			iMaxInHoursFromService = String.format("%.2f", (int)(Float.parseFloat(iMaxInHoursFromService)/60)+(Float.parseFloat(iMaxInHoursFromService)%60)/100);
			System.out.println("The IMAX value from service is: " + iMaxInHoursFromService+", IMAX value from UI is: "+IMaxOnlyFromUI);
			
			String cockpitQualDivisionalQual = DOTCRestService.readJson("$.cockpitQualifications[?(@.contractMonth=='"+strMonth+"')].divisionQuals",
					jsonresponse);
			//String strqualsFromService = cockpitQualDivisionalQual.replace("US,", "").replace("GUC,", "")
			//		.replace("JAC,", "").replace("MEX,", "").replace("UIO,", "").trim();
			System.out.println("The QUALS value from service is: " + cockpitQualDivisionalQual+", QUALS value from UI is: "+qualsFromUI);
			String[] cockpitQualLandingexpDt = (DOTCRestService
					.readJson("$.cockpitQualifications[?(@.contractMonth=='"+strMonth+"')].landingExpirationDate", jsonresponse)).split(",");
			List<String> landingDates = new ArrayList<String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			for(int i = 0; i < cockpitQualLandingexpDt.length; i++) {
				calendar.setTime(sdf.parse(cockpitQualLandingexpDt[i]));
				landingDates.add(new SimpleDateFormat("dd/MMM/yyyy").format(calendar.getTime()).toUpperCase());
			}
			
			System.out.println("Landing expiration date from service is: "+cockpitQualLandingexpDt+", Ui is: "+landingExpFromUI);
			boolean vbankFlag = false;
			boolean pvdFlag = false;
			
			int vcAccrNextYear = Integer.parseInt(DOTCRestService.readJson("$.sickTime.vcAccrNextYear", jsonresponse));
			int pvdUsed = Integer.parseInt(DOTCRestService.readJson("$.sickTime.pvdUsed", jsonresponse));
			String pvdFromService = String.valueOf((int)(vcAccrNextYear-pvdUsed));
			
			Float rmngHrsVbankTripDropEvn = Float.parseFloat(DOTCRestService.readJson("$.sickTime.rmngHrsVbankTripDropEvn", jsonresponse));
			String vcBankFromService = String.format("%.2f", (int)(rmngHrsVbankTripDropEvn/60)+(rmngHrsVbankTripDropEvn%60)/100);

			if (onlvBank.equalsIgnoreCase(vcBankFromService))
				vbankFlag = true;
			System.out.println("The VCBANK value from service is: " + vcBankFromService+", UI is: "+onlvBank);
			if (pvdFromService.equalsIgnoreCase(onlypvd))
				pvdFlag = true;
			System.out.println("The PVD value from service is: " + pvdFromService+", UI is: "+onlypvd);

			// comparing from Service and UI
			if (employeeIdFromService.equalsIgnoreCase(empIdOnlyFromUI)
					&& SENFromService.equalsIgnoreCase(senOnlyFromUI)
					&& bidStatusFromService.equalsIgnoreCase(bidStatusOnlyFromUI)
					&& projInHoursFromService.equalsIgnoreCase(projOnlyFromUI)
					&& iMaxInHoursFromService.equalsIgnoreCase(IMaxOnlyFromUI)) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "My Info Validation for current month",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue(
						"Service and UI employee Ids, sen, bid status, proj, vmax and imax values are identical for current month",
						true);
			} else
				Assert.assertFalse(
						"Service and UI employee Ids, sen, bid status, proj, vmax and imax values are not identical for current month",
						true);
			
			List<String> strqualsFromServiceList = Arrays.asList(cockpitQualDivisionalQual.split(","));
			List<String> qualsFromUIList = Arrays.asList(qualsFromUI.split(","));
			
			if (strqualsFromServiceList.containsAll(qualsFromUIList)
					&& landingDates.contains(landingExpFromUI)){
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "My Info Qualification Validation",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("Qualifications and landing expiration dates are same in service and UI", true);
			} else
				Assert.assertFalse("Qualifications and landing expiration dates are not same in service and UI", true);
			if (vbankFlag == true && pvdFlag == true) {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.PASS, "VCBank and PVD validation",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertTrue("VBank and PVD values are identical in service and UI", true);
			} else {
				currentScenario.embed(Util.takeScreenshot(driver), "image/png");
				ExtentTestManager.getTest().log(LogStatus.FAIL, "VCBank and PVD validation",
						ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
				Assert.assertFalse("VBank and PVD values are not identical in service and UI", true);
			}
		} catch (Exception ex) {
			Assert.assertFalse("Exception occurred in method validateMyInformation", true);
			ex.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "My Info Validation failed",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
		}
	}
	/**
	 * Function for Validating Pilots information for next month
	 */

	public void validateMyInformationForNextMonth() {
		System.out.println("====== Next month My info ========");
		Util.waitForLoad(driver);
		try {

						// ******* Reading from Service
			Month curmonth = Util.getCurrentMonth();
			Month nextContractualMonth = curmonth.plus(1);
			Integer currentYear = Util.getCurrentYear();
			String strNextMonth = nextContractualMonth.toString().substring(0, 3) + currentYear.toString();
			validateMyInfo(strNextMonth, "O");
		} catch (Exception ex) {
			ex.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "My Info Validation for current month",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));
			Assert.assertFalse("Exception occurred in method validateMyInformationForNextMonth", true);
		}

	}

	/**
	 * Function for Validating Pilots information for previous month
	 */
	public void validateMyInformationForPrevMonth() {
		System.out.println("====== Previous month My info ========");
		try {
			// ******* Reading from Service
			Month curmonth = Util.getCurrentMonth();
			Month previousContractualMonth = curmonth.minus(1);
			Integer currentYear = Util.getCurrentYear();
			String strPrevMonth = previousContractualMonth.toString().substring(0, 3) + currentYear.toString();
			validateMyInfo(strPrevMonth, "R");
		} catch (Exception ex) {
			Assert.assertFalse("Exception occurred in method validateMyInformationForPrevMonth", true);
			ex.printStackTrace();
			currentScenario.embed(Util.takeScreenshot(driver), "image/png");
			ExtentTestManager.getTest().log(LogStatus.FAIL, "My Info Validation for current month",
					ExtentTestManager.getTest().addBase64ScreenShot(Util.base64Screenshot(driver)));

		}

	}
}
