package com.DOTC.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

import com.DOTC.pageObjects.DOTCRestService;
import com.DOTC.supportLibraries.Browser;
import com.DOTC.supportLibraries.DriverFactory;
import com.DOTC.supportLibraries.DriverManager;
import com.DOTC.supportLibraries.ExecutionMode;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.PerfectoLabUtils;
import com.DOTC.supportLibraries.SeleniumTestParameters;
import com.DOTC.supportLibraries.Settings;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.TimeStamp;
import com.DOTC.supportLibraries.Util;
//import com.experitest.selenium.MobileWebDriver;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.google.common.collect.Iterables;
import com.google.common.io.CharStreams;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class DOTCCukeHooks extends DOTCMasterSteps {
	


	static Logger log;
	public static Properties properties = Settings.getInstance();
	public static SoftAssert softAssert = new SoftAssert();
	
	
	public static String scenarioName="";
	static {
		log = Logger.getLogger(DOTCCukeHooks.class);
	}
	
	

	@Before
	public void setUp(Scenario scenario) {
		try {
			System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
			currentScenario = scenario;
			ExtentTestManager.startTest(getScenarioDtl(scenario.getName(),"Test Case Name:"),scenario.getName());
			propertiesFileAccess = properties;
			Thread.sleep(2000);
			currentTestParameters = DriverManager.getTestParameters();
			currentTestParameters.setScenarioName( scenario.getSourceTagNames() + scenario.getName());
			scenarioName =scenario.getName();
			//***************************** Custom Modification - Start *************************************
			//System.out.println("*********************** Scenario Details: ***********************");
			//System.out.println("Scenario ID:  "+ scenario.getId());
			//System.out.println("Scenario Name:  "+ scenario.getName());
			//System.out.println("Scenario Tag Names:  "+ scenario.getSourceTagNames());
			//System.out.println("Scenario Status:  "+ scenario.getStatus());
			
			//***************************** Custom Modification - END *************************************
			log.info("Running the Scenario : " + scenario.getName());
			String t=scenario.getSourceTagNames().toString().toUpperCase(); 
			if ( t.contains(new String("IPHONE")) || t.contains("IPAD") || Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))) 
				invokeForMobileExecution(scenario);
			else 
				invokeForDesktopExecution(scenario);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error at Before Scenario " + e.getMessage());
		}
	}
	
	

	private void invokeForDesktopExecution(Scenario scenario) {
		switch (currentTestParameters.getExecutionMode()) {

		case LOCAL:
		case REMOTE:
		case SAUCELABS:
				log.info("Running the Scenario : " + scenario.getName() + " in "
						+ currentTestParameters.getExecutionMode());
				WebDriver driver = DriverFactory.createInstanceWebDriver(currentTestParameters);
				DriverManager.setWebDriver(driver);
			break;

		default:
			try {
				throw new Exception("Unhandled Execution Mode!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	@SuppressWarnings("rawtypes")
	private void invokeForMobileExecution(Scenario scenario) {
		AppiumDriver driver= null;
		switch (currentTestParameters.getExecutionMode()) {

		case MOBILE:
		case SAUCELABS:
			log.info(
					"Running the Scenario : " + scenario.getName() + " in " + currentTestParameters.getExecutionMode());
 			if (scenario.getSourceTagNames().toString().contains("iPad"))
				currentTestParameters.setDeviceName("iPad 13.2 Simulator");
			else 
				currentTestParameters.setDeviceName("iPhone 7");
			driver = DriverFactory.createInstance(currentTestParameters);
			DriverManager.setAppiumDriver(driver);
			break;
		case PERFECTO:
		case MINT:

			log.info(
					"Running the Scenario : " + scenario.getName() + " in " + currentTestParameters.getExecutionMode());
			driver = DriverFactory.createInstance(currentTestParameters);
			DriverManager.setAppiumDriver(driver);
			break;

		/*case SEETEST:

			log.info(
					"Running the Scenario : " + scenario.getName() + " in " + currentTestParameters.getExecutionMode());
			MobileWebDriver seetestDriver = DriverFactory.createInstanceSeetestDriver(currentTestParameters);
			DriverManager.setSeetestDriver(seetestDriver);
			break;*/

		default:
			try {
				throw new Exception("Unhandled Execution Mode!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@After
	public void embedScreenshot(Scenario scenario) {
		WebDriver driver = null;		
		try {
			driver = DriverManager.getAnyAvailableWebDriver();
			ExtentTestManager.endTest();			
			
			if (Boolean.valueOf(properties.getProperty("TrackTCInRally"))) {
				integrateRally(scenario,driver);
			}
			
			if (Boolean.valueOf(properties.getProperty("TrackIssuesInJira"))) {
				updateDefectInJira(scenario);
			}
			if (Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))
					&& Boolean.valueOf(properties.getProperty("PerfectoReportGeneration"))) {
				capturePerfectoReportsForMobile(scenario);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Problem while closing the Driver Object " + ex.getMessage());

		} finally {

			if (Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))) {
				if (currentTestParameters.getExecutionMode() == ExecutionMode.SEETEST) {
					/*MobileWebDriver driver = DriverManager.getSeetestDriver();
					driver.client.releaseDevice("*", true, false, true);
					driver.client.releaseClient();
					driver.quit();*/
				} else {
					
					if (driver != null) {
						if(currentTestParameters.getExecutionMode()==ExecutionMode.SAUCELABS) 
							((JavascriptExecutor)driver).executeScript("sauce:job-result=" + scenario.getStatus());
						driver.quit();
						//softAssert.assertAll();
					}
				}
			} else {
				driver = DriverManager.getAnyAvailableWebDriver();
				if (driver != null) {
					capturePerfectoReportForDesktop(scenario, currentTestParameters, driver);
					if(currentTestParameters.getExecutionMode()==ExecutionMode.SAUCELABS)
						((JavascriptExecutor)driver).executeScript("sauce:job-result=" + scenario.getStatus());
					driver.quit();
				}
			}
		}
	}
	
	
	/**
	 * Embed a screenshot in test report if test is marked as failed And Update
	 * Task in JIRA
	 * @throws IOException 
	 */
	private void updateDefectInJira(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			
			try {
				if (Boolean.parseBoolean(properties.getProperty("ExecuteInMobile"))) {
					if (currentTestParameters.getExecutionMode() == ExecutionMode.SEETEST) {
						
						/*byte[] screenshot = Util.takeScreenshot(DriverManager.getSeetestDriver());
						scenario.embed(screenshot, "image/png");*/
					} else {
						byte[] screenshot = Util.takeScreenshot(DriverManager.getAppiumDriver());
						scenario.embed(screenshot, "image/png");
					}
				} else {
					byte[] screenshot = Util.takeScreenshot(DriverManager.getWebDriver());
					scenario.embed(screenshot, "image/png");
				}

				File filePath = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
				//RestApiForJira.createLog(scenario.getName(), scenario.getName(), filePath.toString());

			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				somePlatformsDontSupportScreenshots.printStackTrace();
				log.error(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
	}

	private String getFileName(Browser browser, String deviceName) {
		String fileName = "";
		if (browser == null) {
			fileName = deviceName;
		} else if (deviceName == null) {
			fileName = browser.toString();
		} else {
			fileName = "report";
		}		
		return fileName;
	}

	@SuppressWarnings("rawtypes")
	private void capturePerfectoReportsForMobile(Scenario scenario) {
		try {
			AppiumDriver driver = DriverManager.getAppiumDriver();
			driver.close();
			String Udid;

			if (!(driver.getCapabilities().getCapability("model") == null)) {
				Udid = driver.getCapabilities().getCapability("model").toString();
			} else {
				Udid = driver.getCapabilities().getCapability("deviceName").toString();
			}
			PerfectoLabUtils.downloadReport(driver, "pdf",
					Util.getResultsPath() + Util.getFileSeparator() + scenario.getName() + "_" + Udid);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Problem while downloading Perfecto Report for " + scenario.getName());

		}
	}

	private void capturePerfectoReportForDesktop(Scenario scenario, SeleniumTestParameters testParametrs,
			WebDriver driver) {
		if (Boolean.valueOf(properties.getProperty("PerfectoReportGeneration"))) {
			driver.close();

			Map<String, Object> params = new HashMap<String, Object>();
			((RemoteWebDriver) driver).executeScript("mobile:execution:close", params);
			params.clear();

			try {
				PerfectoLabUtils.downloadReport((RemoteWebDriver) driver, "pdf",
						Util.getResultsPath() + Util.getFileSeparator() + scenario.getName() + "_"
								+ getFileName(testParametrs.getBrowser(), testParametrs.getDeviceName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//	public static void generateCustomReports() {
//
//		CucumberResultsOverview overviewReports = new CucumberResultsOverview();
//		overviewReports.setOutputDirectory("target");
//		overviewReports.setOutputName("cucumber-results");
//		overviewReports.setSourceFile("target/cucumber-report/Smoke/cucumber.json");
//		try {
//			overviewReports.executeFeaturesOverviewReport();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public static void copyReportsFolder() {
//
//		String timeStampResultPath = TimeStamp.getInstance();
//
//		File source = new File(Util.getTargetPath());
//		File source1 = new File(Util.getAllurePath());
//		File dest = new File(timeStampResultPath);
//		File dest1 = new File(timeStampResultPath + Util.getFileSeparator() + "Allure-reports");
//		if (!dest1.isDirectory()) {
//			dest1.mkdirs();
//		}
//		try {
//			FileUtils.copyDirectory(source, dest);
//			FileUtils.copyDirectory(source1, dest1);
//			try {
//				FileUtils.cleanDirectory(source);
//			} catch (Exception e) {
//
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		TimeStamp.reportPathWithTimeStamp = null;
//
//	}
//	
	public static String getScenarioDtl(String strScenarioOutline,String strTarget ) {
		String ScenarioDetails = "";
		try {
			ScenarioDetails = strScenarioOutline.substring(strScenarioOutline.indexOf(strTarget) + strTarget.length(),strScenarioOutline.indexOf(".",strScenarioOutline.indexOf(strTarget))).trim();
			
		}catch(Exception ex) {
			
		}
		return ScenarioDetails;
	}
	
	public static void integrateRally(Scenario scenario, WebDriver driver) {
		try {
			
			String strVerdictStatus = "";
			String tagName = "";
			String strStoryID = "";
			String storyRef = "";
			String testCaseRef = "";
			String scenarioDesc = scenario.getName();
			
			String strNote = "";
			
			strNote = strNote + "Results can be viewed from: " +System.getenv("BUILD_URL");
			
			String strTCNote= "Test Case created from Automation. Please refer the feature file in automation for detailed steps";
			if(scenario.getStatus().equalsIgnoreCase("passed")) {
				strVerdictStatus = "Pass";
			}else if(scenario.getStatus().equalsIgnoreCase("failed")) {
				strVerdictStatus = "Fail";
			}else if(scenario.getStatus().equalsIgnoreCase("skipped")) {
				strVerdictStatus = "Inconclusive";
			}else {
				strVerdictStatus = "Error";
			}
			//String strScenarioName = scenario.getName();
			Collection<String> strTagNames = scenario.getSourceTagNames();
			String strTCType = "";
			
			for(int k=0;k<strTagNames.size();k++) {
				tagName = Iterables.get(strTagNames, k);
				if(tagName.startsWith("@US")) {
					strStoryID = tagName.replace("@", "");
					//break;
				}else if(tagName.startsWith("@UI")) {
					strTCType = "User Interface";
				}else if(tagName.startsWith("@Smoke")) {
					strTCType = "Acceptance";
				}else if(tagName.startsWith("@Regression")) {
					strTCType = "Regression";
				}else{
					strTCType = "Functional";
				}
			}
			
			String RallyTestCaseName = getScenarioDtl(scenarioDesc,"Test Case Name:") ;
			
			String strPriority = getScenarioDtl(scenarioDesc,"Priority:") ;
			String strRisk = getScenarioDtl(scenarioDesc,"Risk:");
			scenarioDesc= scenarioDesc.substring(0,scenarioDesc.indexOf("Test Case Name:"));
			
			
			RallyRestApi restApi = new RallyRestApi(new URI(properties.getProperty("rallyHost")), properties.getProperty("rallyAPIKey"));
			
			//query to get the test case from name
			QueryRequest tcRequest = new QueryRequest("TestCases");
			tcRequest.setFetch(new Fetch("Name", "Name"));
			tcRequest.setQueryFilter(new QueryFilter("Name", "=",  RallyTestCaseName));
	        QueryResponse tcQueryResponse = restApi.query(tcRequest);
	        
	        if(tcQueryResponse.getResults().size()==0) {
				
		        
		        //query to get the user story
		        try {
			        QueryRequest storyRequest = new QueryRequest("HierarchicalRequirement");
			        storyRequest.setFetch(new Fetch("FormattedID", "Name"));
			        storyRequest.setQueryFilter(new QueryFilter("FormattedID", "=",  strStoryID));
			        QueryResponse storyQueryResponse = restApi.query(storyRequest); 
			        JsonObject storyJsonObject = storyQueryResponse.getResults().get(0).getAsJsonObject();
			        storyRef = storyJsonObject.get("_ref").getAsString();
		        
		        }catch(Exception Ex) {
		        	Ex.printStackTrace();
		        }
		        try {
					// Create test case
					JsonObject newTestCase = new JsonObject();
					newTestCase.addProperty("Name", RallyTestCaseName);
					//newTestCase.addProperty("Project", "/project/243399340860");
					newTestCase.addProperty("Project", "/project/356957867100");
					newTestCase.addProperty("Owner", "/user/285470368520");	// Saikat's profile 285470368520  
					//newTestCase.addProperty("Owner", "/user/285469326236");
					newTestCase.addProperty("Description", scenarioDesc);
					newTestCase.addProperty("Priority", strPriority);
					newTestCase.addProperty("Risk", strRisk);
					newTestCase.addProperty("Type", strTCType);
					newTestCase.addProperty("WorkProduct", storyRef);
					newTestCase.addProperty("Method", "Automated");
					newTestCase.addProperty("Notes", strTCNote);
					
					CreateRequest createRequest = new CreateRequest("testcase", newTestCase);
					CreateResponse createResponse = restApi.create(createRequest);
					testCaseRef = createResponse.getObject().get("_ref").getAsString();
					
	
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }else {
	        	JsonObject tcJsonObject = tcQueryResponse.getResults().get(0).getAsJsonObject();
		        testCaseRef = tcJsonObject.get("_ref").getAsString();
	        }
			// Add a Test Case Result
	        String buildNumber = getBuildNoActuator();
	        if(buildNumber==null) {
	        	buildNumber = "NA";
	        }
			JsonObject newTestCaseResult = new JsonObject();
			newTestCaseResult.addProperty("Verdict", strVerdictStatus);
			java.util.Date date = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String timestamp = sdf.format(date);

			newTestCaseResult.addProperty("Date", timestamp);
			//newTestCaseResult.addProperty("Build", System.getenv("BUILD_NUMBER").toString());
			newTestCaseResult.addProperty("Build", buildNumber);
			newTestCaseResult.addProperty("Notes", strNote);
			newTestCaseResult.addProperty("TestCase", testCaseRef);

			CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
			CreateResponse createResponse = restApi.create(createRequest);
			restApi.close();
			
			// Reseting firstLoginFlg to improve execution time.
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Function for getting CrewMember from CCS Services
	 */
	private static String getBuildNoActuator() {
		String strResponse = "";
		String buildNo = "";
		try {
			
			List<Header> headerlist = new ArrayList<Header>();
        	
        	headerlist.add(new Header("Content-Type", "application/json"));
        	
        	//headerlist.add(new Header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"));
        	Headers headers = new Headers(headerlist);
        	Response response = RestAssured.given()
        			.baseUri(TestData.strGlobalURL)
        			.basePath("/actuator/info")
        			.headers(headers).get();
        			
        	InputStream strm = response.getBody().asInputStream();
        	Reader reader = new InputStreamReader(strm);
        	strResponse = CharStreams.toString(reader);
        	buildNo = DOTCRestService.readJson("$.git.build.number", strResponse);
        	//System.out.println(strResponseIntext);
        	          
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return buildNo;
	}

}