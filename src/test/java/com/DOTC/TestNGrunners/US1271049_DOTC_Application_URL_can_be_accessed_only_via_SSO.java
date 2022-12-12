package com.DOTC.TestNGrunners;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import com.DOTC.pageObjects.DOTCJsonXmlPath;
import com.DOTC.pageObjects.DOTCRestService;
import com.DOTC.supportLibraries.ExtentManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.TimeStamp;
import com.DOTC.supportLibraries.Util;
import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@ExtendedCucumberOptions(

		jsonReport = "target/cucumber-report/Smoke/cucumber.json", jsonUsageReport = "target/cucumber-report/Smoke/cucumber-usage.json", outputFolder = "target/cucumber-report/Smoke", detailedReport = true, detailedAggregatedReport = true, overviewReport = true, usageReport = true,retryCount = 2,toPDF=true)

/**
 * Please notice that com.CucumberCraft.stepDefinations.CukeHooks class is in
 * the same package as the steps definitions. It has two methods that are
 * executed before or after scenario. I'm using it to delete cookies and take a
 * screenshot if scenario fails.
 */


@CucumberOptions(
		 features = "src/test/resources/features/US1271049_DOTC_Application_URL_can_be_accessed_only_via_SSO.feature", glue = { "com.DOTC.stepDefinitions" },
						monochrome = true,  plugin = { "pretty", "pretty:target/cucumber-report/Smoke/pretty.txt",
						"html:target/cucumber-report/Smoke", "json:target/cucumber-report/Smoke/cucumber.json",
						"junit:target/cucumber-report/Smoke/cucumber-junitreport.xml",
						//"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"})
						"ru.yandex.qatools.allure.cucumberjvm.AllureReporter"})
		

public class US1271049_DOTC_Application_URL_can_be_accessed_only_via_SSO extends AbstractTestNGCucumberTests {
	public static String[] arrEmployee = null;
	
	boolean isExecuted=true;  // sometimes filter makes this not being executed and no need to generate report
	@AfterTest(alwaysRun=true)
	private void test() {
		if (isExecuted) {
			generateCustomReports();
			copyReportsFolder();
		}
	}
	
	@Override
    @DataProvider(parallel = true)
	public Object[][] features() {
		Object[][] ret = super.features();
		if (ret == null || ret.length < 1)
			isExecuted = false;
		return ret;
    }
	
	private void generateCustomReports() {

		CucumberResultsOverview overviewReports = new CucumberResultsOverview();
		overviewReports.setOutputDirectory("target");
		overviewReports.setOutputName("cucumber-results");
		overviewReports.setSourceFile("target/cucumber-report/Smoke/cucumber.json");
		try {
			overviewReports.executeFeaturesOverviewReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CucumberDetailedResults detailedResults = new CucumberDetailedResults();
        detailedResults.setOutputDirectory("target");
        detailedResults.setOutputName("cucumber-results");
        detailedResults.setSourceFile("target/cucumber-report/Smoke/cucumber.json");
        detailedResults.setScreenShotLocation("./screenshot");
        try {
               detailedResults.executeDetailedResultsReport(false, true);
        } catch (Exception e) {

              // e.printStackTrace();
        }

	}

	private void copyReportsFolder() {

		String timeStampResultPath = TimeStamp.getInstance();

		File sourceCucumber = new File(Util.getTargetPath());

		File destCucumber = new File(timeStampResultPath);
		File destCucumberLatest = new File("Latest");

		try {
			FileUtils.copyDirectory(sourceCucumber, destCucumberLatest);
			FileUtils.copyDirectory(sourceCucumber, destCucumber);
			try {
				//FileUtils.cleanDirectory(sourceCucumber);
			} catch (Exception e) {

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		TimeStamp.reportPathWithTimeStamp = null;

	}

	private void getEmployeeDetails() {
		// Any customizations before execution can be added here
		DOTCRestService tts = new DOTCRestService();
		TestData testData = new TestData();
		//DBConnection dbconnect = new DBConnection();
		try {
			/*Connection cnct = dbconnect.AzureDBConnect();
			ResultSet rs = dbconnect.executeQuery(cnct, "select * from [TTS_ADM].[BATCH_RUN] where contract_month='FEB2020' ");
			rs.next();*/
			String contractMonth = Util.getCurrentFormattedTime("MMMyyyy");
			String seqStartDate = Util.getCurrentFormattedTime("yyyy-MM-dd") + "T00:00:00";

			String strSeqJson = tts.initializeTestDataFiles(DOTCJsonXmlPath.strSrchSeqFileName);

			String strModifiedJson = tts.modifyJson(strSeqJson, DOTCJsonXmlPath.SrchSeqFABase, TestData.strBase);
			strModifiedJson = tts.modifyJson(strModifiedJson, DOTCJsonXmlPath.SrchSeqFAContractMonth, contractMonth);
			strModifiedJson = tts.modifyJson(strModifiedJson, DOTCJsonXmlPath.SrchSeqFAStart, seqStartDate);

			String strResponseTxt = tts.postESOAService(DOTCRestService.srchSeqFAEndPoint,strModifiedJson);

			String EmployeeIDList = DOTCRestService.readJson(DOTCJsonXmlPath.EmployeeIDSrchSeqFA, strResponseTxt);
			arrEmployee = EmployeeIDList.split("\\,");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	@AfterSuite
	private void copyStoredReports() {
		try {
			String strFilePath = ExtentManager.getReportPath();
			String workingDir = System.getProperty("user.dir");
			String destFile = "";
			System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				destFile = workingDir + "\\ExtentReports\\ExtentReport.html";
			}
			else {
				destFile = workingDir + "/ExtentReports/ExtentReport.html";
			}
			FileUtils.copyFile(new File(strFilePath), new File(destFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
