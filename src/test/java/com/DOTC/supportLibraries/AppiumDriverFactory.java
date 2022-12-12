package com.DOTC.supportLibraries;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppiumDriverFactory {


	private static Properties mobileProperties;
	static Logger log = Logger.getLogger(AppiumDriverFactory.class);


	private AppiumDriverFactory() {
		// To prevent external instantiation of this class
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getAppiumDriver(SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;
		String env = "";
		String apiKey = "";
		mobileProperties = Settings.getInstance();
		String deviceName = System.getenv("device_Name");
		String devicePlatformVrsion = System.getenv("platform_version");
		String appApkPath = System.getenv("app_apk_path");
		String appiumURL = mobileProperties.getProperty("AppiumURL");

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (testParameters.getMobileExecutionPlatform()) {

			case ANDROID:

				if(appiumURL.contains("127.0.0.1:4723")) {
					cleanAllureFolder();
				}

				if(deviceName==null) {
					deviceName = testParameters.getDeviceName();
				}

				if(devicePlatformVrsion==null) {
					devicePlatformVrsion = "10";
				}
				if(appApkPath==null) {
					appApkPath=mobileProperties.getProperty("Android_apk");
				}
				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("app", appApkPath);
				desiredCapabilities.setCapability("deviceName",deviceName );
				desiredCapabilities.setCapability("platformVersion", devicePlatformVrsion);
				env = System.getenv("app_build_channel");
				if(env!=null) {
					if(env.equalsIgnoreCase("QA")) {
						apiKey = "C570822F2CB74DE5B2CA10A7A487A9E0";
					}else if(env.equalsIgnoreCase("Dev")) {
						apiKey = "8A2A2C1534CA4C3B8FC78F86E79E4320";
					}else if(env.equalsIgnoreCase("UAT")) {
						apiKey = "5456142B163E4E52B56D230561CCFFD4";
					}else {
						apiKey = "C570822F2CB74DE5B2CA10A7A487A9E0";
					}
				}else {
					apiKey = "C570822F2CB74DE5B2CA10A7A487A9E0";
				}
				desiredCapabilities.setCapability("automationName","uiautomator2");
				desiredCapabilities.setCapability("testobject_api_key", apiKey);
				desiredCapabilities.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties.getProperty("Application_MainActivity_Name"));
				desiredCapabilities.setCapability("newCommandTimeout", 120);
				desiredCapabilities.setCapability("noReset",true);
				desiredCapabilities.setCapability("resetKeyboard",true);
				//desiredCapabilities.setCapability("autoGrantPermissions", true);
				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case IOS:

				/*This is to reduce the dependency of changing TestNGRunRegressionTestiOS & TestNGRunSmokeTestiOS during execution of scripts on emulator
				 */

				if(appiumURL.contains("127.0.0.1:4723")) {
					deviceName = "iPhone XR";
					devicePlatformVrsion = "12.2";
					appApkPath=mobileProperties.getProperty("iOS_app_path");
					cleanAllureFolder();

				}
				if(deviceName==null) {
					deviceName = testParameters.getDeviceName();
				}

				if(devicePlatformVrsion==null) {

					devicePlatformVrsion = "12.4.1";
					//devicePlatformVrsion = "12.2";

				}
				if(appApkPath==null) {
					appApkPath=mobileProperties.getProperty("iOS_app_path");

				}
				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("deviceName", deviceName);
				desiredCapabilities.setCapability("platformVersion", devicePlatformVrsion);
				env = System.getenv("app_build_channel");
				if(env!=null) {
					if(env.equalsIgnoreCase("QA")) {
						apiKey = "0FE46F48FB9C45279BEF11C35A9ABA98";
					}else if(env.equalsIgnoreCase("Dev")) {
						apiKey = "8638E5B8022643DAB10F10A8E4B67C67";
					}else if(env.equalsIgnoreCase("UAT")) {
						apiKey = "1F4514C2B3B842A5909696AD7B97AE9A";
					}else {
						apiKey = "0FE46F48FB9C45279BEF11C35A9ABA98";
					}
				}else {
					apiKey = "0FE46F48FB9C45279BEF11C35A9ABA98";
				}

				desiredCapabilities.setCapability("testobject_api_key", apiKey);
				desiredCapabilities.setCapability("app", appApkPath);

				desiredCapabilities.setCapability("automationName", "XCUITest");
				desiredCapabilities.setCapability("bundleId", mobileProperties.getProperty("IosBundleID"));
				desiredCapabilities.setCapability("newCommandTimeout", 120);
				//desiredCapabilities.setCapability("fullReset", true);
				desiredCapabilities.setCapability("noReset",true);
				desiredCapabilities.setCapability("resetKeyboard",true);
				//desiredCapabilities.setCapability("noSign",true);

				//desiredCapabilities.setCapability("xcodeOrgId", "N6RW8FW79B" );
				//desiredCapabilities.setCapability("xcodeSigningId","iPhone Developer" );
				//desiredCapabilities.setCapability("autoAcceptAlerts", true);
				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				// desiredCapabilities.setCapability("udid",deviceName);

				desiredCapabilities.setCapability("browserName", "chrome");
				try {
					driver = new AndroidDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");
				try {
					driver = new IOSDriver(new URL(mobileProperties.getProperty("AppiumURL")), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			default:
				throw new Exception("Unhandled Execution Mode!");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}

		return driver;

	}

	public static WebDriver getAppiumRemoteWebDriver(MobileExecutionPlatform executionPlatform, String deviceName,
			String version, Boolean installApp, String appiumURL) {

		WebDriver driver = null;
		mobileProperties = Settings.getInstance();
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (executionPlatform) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("appPackage",
						mobileProperties.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties.getProperty("Application_MainActivity_Name"));
				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();

				}
				break;

			case IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				// desiredCapabilities.setCapability("app",
				// properties.getProperty("iPhoneApplicationPath"));
				desiredCapabilities.setCapability("bundleId", mobileProperties.getProperty("iPhoneBundleID"));
				desiredCapabilities.setCapability("newCommandTimeout", 120);

				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("browserName", "Chrome");

				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");
				try {
					driver = new RemoteWebDriver(new URL(appiumURL), desiredCapabilities);

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

	/**
	 * Function to clean Allure Folder, when executed locally
	 */
	public static void cleanAllureFolder() {

		File targetPath = new File(Util.getAbsolutePath()
				+ Util.getFileSeparator() + "target");

		File deletePath = new File(targetPath +Util.getFileSeparator() +"allure-results");
		

		try {
			FileUtils.cleanDirectory(deletePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
