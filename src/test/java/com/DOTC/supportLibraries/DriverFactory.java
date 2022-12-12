package com.DOTC.supportLibraries;

import io.appium.java_client.AppiumDriver;

import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

//import com.experitest.selenium.MobileWebDriver;

/**
 * DriverFactory which will create respective driver Object
 * 
 * @author Cognizant
 */
public class DriverFactory {

	static Logger log = Logger.getLogger(DriverFactory.class);
	private static Properties mobileProperties = Settings.getInstance();

//	@SuppressWarnings("rawtypes")
	public static AppiumDriver createInstance(
			SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;
		try {
			switch (testParameters.getExecutionMode()) {

			case MOBILE:

				driver = AppiumDriverFactory.getAppiumDriver(testParameters);
				break;

			case PERFECTO:

				driver = PerfectoDriverFactory
						.getPerfectoAppiumDriver(testParameters);

				break;

			case SAUCELABS:
				driver = SauceLabsDriverFactory.getSauceAppiumDriver(testParameters);
				break;

			case MINT:

				driver = MintDriverFactory.getmintAppiumDriver(
						testParameters.getMobileExecutionPlatform(),
						testParameters.getDeviceName(),
						mobileProperties.getProperty("mintHost"),
						testParameters.getMobileOSVersion());
				break;

			default:
				throw new Exception("Unhandled Execution Mode!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return driver;
	}

	public static WebDriver createInstanceWebDriver(SeleniumTestParameters testParameters) {
		WebDriver driver = null;
		try {
			switch (testParameters.getExecutionMode()) {

			case LOCAL:
				driver = WebDriverFactory.getWebDriver(testParameters.getBrowser());
				break;
				
			case REMOTE:
				driver = WebDriverFactory.getRemoteWebDriver(
						testParameters.getBrowser(),
						testParameters.getBrowserVersion(),
						testParameters.getPlatform(),
						mobileProperties.getProperty("RemoteUrl"));

			case PERFECTO:
				driver = PerfectoDriverFactory.getPerfectoRemoteDriver(testParameters);
				break;

			case SAUCELABS:
				driver = SauceLabsDriverFactory.getSauceRemoteWebDriver(testParameters);
				break;

			default:
				throw new Exception("Unhandled Execution Mode!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return driver;
	}
	
	private WebDriver createRemoteDriver() throws Exception{
		WebDriver driver = null;
		try{
		
			DesiredCapabilities caps = new DesiredCapabilities();
			final String URL = "https://@hub-cloud.browserstack.com/wd/hub";
			
					
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browserName", "Chrome");
			caps.setCapability("browserVersion", "75.0");
			driver = new RemoteWebDriver(new java.net.URL(URL), caps);
			
			if(WebDriverFactory.getBrowserWindowSize()) driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(
	        		WebDriverFactory.getImplicitlyWait(), TimeUnit.SECONDS);
		}catch(Exception ex){
			ex.printStackTrace();
		}
			return driver;
			
		}
	

	/**
	 * Seetest Driver initialization
	 *
	 */
	/*public static MobileWebDriver createInstanceSeetestDriver(
			SeleniumTestParameters testParameters) {
		MobileWebDriver driver = null;
		try {
			switch (testParameters.getExecutionMode()) {

			case SEETEST:
				driver = SeeTestDriverFactory
						.getSeeTestDriver(
								mobileProperties.getProperty("SeeTestHost",
										"localhost"),
								Integer.parseInt(testParameters
										.getSeeTestPort()),
								mobileProperties
										.getProperty("SeeTestProjectBaseDirectory"),
								mobileProperties.getProperty(
										"SeeTestReportType", "xml"),
								"report",
								"Test Name from Driver Init",
								testParameters.getMobileExecutionPlatform(),
								mobileProperties
										.getProperty("SeeTestAndroidApplicationName"),
								mobileProperties
										.getProperty("SeeTestiOSApplicationName"),
								mobileProperties
										.getProperty("SeeTestAndroidWebApplicationName"),
								mobileProperties
										.getProperty("SeeTestiOSWebApplicationName"),
								testParameters.getDeviceName());
				break;

			default:
				throw new Exception("Unhandled Execution Mode!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
		}
		return driver;
	}*/

}