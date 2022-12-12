package com.DOTC.supportLibraries;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SauceLabsDriverFactory {

	private static Properties mobileProperties = Settings.getInstance();
	static Logger log = Logger.getLogger(SauceLabsDriverFactory.class);

	private SauceLabsDriverFactory() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the Saucelabs DesktopCloud {@link RemoteWebDriver} object
	 * based on the parameters passed
	 * 
	 * @param platformName The platform to be used for the test execution (Windows,
	 *                     Mac, etc.)
	 * @param version      The browser version to be used for the test execution
	 * @param browserName  The {@link Browser} to be used for the test execution
	 * @param sauceUrl     The Saucelabs URL to be used for the test execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
/*	public static WebDriver getSauceRemoteWebDriver(String sauceURL, Browser browser, String browserVersion,
			Platform platformName, SeleniumTestParameters testParameters) {

		mobileProperties.getProperty("SauceUsername"), 
		mobileProperties.getProperty("SauceKey"), mobileProperties.getProperty("SauceHost"), mobileProperties.getProperty("SauceTunnelId"),
		mobileProperties.getProperty("SauceParentTunnel"),mobileProperties.getProperty("SauceidleTimeout"),mobileProperties.getProperty("screenResolution"),
		
		WebDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platform", platformName);
		desiredCapabilities.setCapability("version", browserVersion);
		desiredCapabilities.setCapability("browserName", browser);
		// desiredCapabilities.setCapability("screen-resolution","800x600");
		desiredCapabilities.setCapability("name", testParameters.getScenarioName());
		try {
			driver = new RemoteWebDriver(new URL(sauceURL), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}*/

	public static WebDriver getSauceRemoteWebDriver(SeleniumTestParameters testParameters ) {
		String jenkinsBuildNumber = "LOCAL";
		String branch = "";
		if(System.getenv("BUILD_NUMBER")!=null) {
    		jenkinsBuildNumber = System.getenv("BUILD_NUMBER");
		}
    		
    		if(System.getenv("branch")!=null){
        		branch = System.getenv("branch");
    	}
    	String setBuildNumber = System.getenv("GITHUB_RUN_NUMBER");
		WebDriver driver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("name", testParameters.getScenarioName());
		String tmp=System.getenv("SauceKey");
		if(tmp==null || tmp.length()<1)
			tmp =testParameters.getSauceAccessKey();
		desiredCapabilities.setCapability("accessKey", tmp);
		tmp=System.getenv("SauceUsername");
		log.info("ENVsauceuser: "+ tmp);
		if(tmp==null || tmp.length()<1)
			tmp =testParameters.getSauceUsername();
		
		desiredCapabilities.setCapability("username", tmp);
		desiredCapabilities.setCapability("tunnelIdentifier", testParameters.getSauceTunnelId());
		desiredCapabilities.setCapability("parentTunnel",testParameters.getSauceParentTunnel());
		desiredCapabilities.setCapability("screenResolution", testParameters.getSauceScreenResolution());
		//desiredCapabilities.setCapability("browserName",testParameters.getBrowser());
		desiredCapabilities.setCapability("version", testParameters.getBrowserVersion());
		desiredCapabilities.setCapability("platform", testParameters.getDesktopOSVersion());
		desiredCapabilities.setCapability("idleTimeout",testParameters.getSauceidleTimeout());
		desiredCapabilities.setCapability("build", branch+"_"+setBuildNumber);
		MutableCapabilities capabilities;
		switch(testParameters.getBrowser()) {
			case FIREFOX:
				capabilities = new FirefoxOptions();
				break;
			case EDGE:
				capabilities = new EdgeOptions();
				break;
			case SAFARI:
				capabilities = new SafariOptions();
				break;
			case OPERA:
				capabilities = new OperaOptions();
				break;
			default:
				capabilities = new ChromeOptions();
		}
		capabilities.setCapability("sauce:options", desiredCapabilities);
		System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
		/*if (!System.getProperty("user.dir").contains("opt") || !System.getProperty("user.dir").contains("partition")) {
			log.info("Setting proxy setting for Saucelabs!");
			System.getProperties().put("http.proxyHost", "inetgw.aa.com");
			System.getProperties().put("http.proxyPort", "9093");		
			System.getProperties().put("https.proxyHost", "inetgw.aa.com");
			System.getProperties().put("https.proxyPort", "9093");
			System.getProperties().put("http.nonProxyHosts", "localhost|127.*|[::1]|10.*|*.qcorpaa.aa.com|*.aa.com");
			System.getProperties().put("https.nonProxyHosts", "localhost|127.*|[::1]|10.*|*.qcorpaa.aa.com|*.aa.com");
		}*/
		/*System.getProperties().put("http.proxyHost", "pzen.zsproxy.aa.com");
		System.getProperties().put("http.proxyPort", "9400");
		System.getProperties().put("https.proxyHost", "pzen.zsproxy.aa.com");
		System.getProperties().put("https.proxyPort", "9400");
		System.getProperties().put("http.nonProxyHosts", "localhost|127.*|[::1]|10.*|*.qcorpaa.aa.com|*.aa.com");
		System.getProperties().put("https.nonProxyHosts", "localhost|127.*|[::1]|10.*|*.qcorpaa.aa.com|*.aa.com");*/
		try {
			driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("SauceHost")), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

	
	public static AppiumDriver getSauceAppiumDriver(SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;

		mobileProperties = Settings.getInstance();

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			desiredCapabilities.setCapability("name", testParameters.getScenarioName());
			desiredCapabilities.setCapability("accessKey", mobileProperties.getProperty("SauceKey"));
			desiredCapabilities.setCapability("username", mobileProperties.getProperty("SauceUsername"));
			desiredCapabilities.setCapability("tunnelIdentifier", mobileProperties.getProperty("SauceTunnelId"));
			desiredCapabilities.setCapability("parentTunnel", mobileProperties.getProperty("SauceParentTunnel"));
			//desiredCapabilities.setCapability("screenResolution", mobileProperties.getProperty("screenResolution"));
			
			switch (testParameters.getMobileExecutionPlatform()) {
			case ANDROID:
				desiredCapabilities.setCapability("appiumVersion",
						mobileProperties.getProperty("SaucelabAppiumDriverVersion"));
				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				desiredCapabilities.setCapability("platformVersion", testParameters.getMobileOSVersion());
				desiredCapabilities.setCapability("app", mobileProperties.getProperty("SauceAndroidIdentifier"));
				desiredCapabilities.setCapability("name", testParameters.getScenarioName());
				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("SauceHost")), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				break;

			case IOS:
				desiredCapabilities.setCapability("appiumVersion",
						mobileProperties.getProperty("SaucelabAppiumDriverVersion"));
				desiredCapabilities.setCapability("deviceOrientation", "portrait");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				desiredCapabilities.setCapability("browserName", "Safari");
				desiredCapabilities.setCapability("name", testParameters.getScenarioName());
				desiredCapabilities.setCapability("platformName", "iOS");
				desiredCapabilities.setCapability("platformVersion", "12.3"); // testParameters.getMobileOSVersion());
				// desiredCapabilities.setCapability("app",
				// mobileProperties.getProperty("SauceIosBundleID"));
				desiredCapabilities.setCapability("idleTimeout", mobileProperties.getProperty("SauceidleTimeout"));
				if (!System.getProperty("user.dir").contains("opt")
						|| !System.getProperty("user.dir").contains("partition")) {
					log.info("Setting proxy setting for Saucelabs!");
					System.getProperties().put("http.proxyHost", "server-inet.aa.com");
					System.getProperties().put("http.proxyPort", "9096");// 9093
					System.getProperties().put("https.proxyHost", "server-inet.aa.com");
					System.getProperties().put("https.proxyPort", "9096"); // 9093
					System.getProperties().put("http.nonProxyHosts",
							"localhost|127.*|[::1]|10.*|*.qcorpaa.aa.com|*.aa.com");
					System.getProperties().put("https.nonProxyHosts",
							"localhost|127.*|[::1]|10.*|*.qcorpaa.aa.com|*.aa.com");
				}
				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("SauceHost")), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_ANDROID:
				desiredCapabilities.setCapability("appiumVersion",
						mobileProperties.getProperty("SaucelabAppiumDriverVersion"));
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				desiredCapabilities.setCapability("deviceOrientation", "portrait");
				desiredCapabilities.setCapability("browserName", "chrome");
				desiredCapabilities.setCapability("platformVersion", testParameters.getMobileOSVersion());
				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("name", testParameters.getScenarioName());

				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("SauceHost")), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_IOS:
				desiredCapabilities.setCapability("appiumVersion",
						mobileProperties.getProperty("SaucelabAppiumDriverVersion"));
				desiredCapabilities.setCapability("deviceOrientation", "portrait");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				desiredCapabilities.setCapability("browserName", "Safari");
				desiredCapabilities.setCapability("name", testParameters.getScenarioName());
				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("platformVersion", "13.2"); // testParameters.getMobileOSVersion());
				// desiredCapabilities.setCapability("app",
				// mobileProperties.getProperty("SauceIosBundleID"));
				desiredCapabilities.setCapability("idleTimeout", mobileProperties.getProperty("SauceidleTimeout"));
				if (!System.getProperty("user.dir").contains("opt")
						|| !System.getProperty("user.dir").contains("partition")) {
					log.info("Setting proxy setting for Saucelabs!");
					System.getProperties().put("http.proxyHost", "server-inet.aa.com");
					System.getProperties().put("http.proxyPort", "9096");// 9093
					System.getProperties().put("https.proxyHost", "server-inet.aa.com");
					System.getProperties().put("https.proxyPort", "9096"); // 9093
					System.getProperties().put("http.nonProxyHosts",
							"localhost|127.*|[::1]|10.*|*.qcorpaa.aa.com|*.aa.com");
					System.getProperties().put("https.nonProxyHosts",
							"localhost|127.*|[::1]|10.*|*.qcorpaa.aa.com|*.aa.com");
				}
				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("SauceHost")), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
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


}
