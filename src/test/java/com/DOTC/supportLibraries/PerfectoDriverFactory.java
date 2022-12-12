package com.DOTC.supportLibraries;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class PerfectoDriverFactory {

	private static Properties mobileProperties = Settings.getInstance();

	static Logger log = Logger.getLogger(PerfectoDriverFactory.class);

	private PerfectoDriverFactory() {
		// To prevent external instantiation of this class
	}

	private static URL getUrl(String remoteUrl) {
		URL url = null;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			log.error(e.getMessage());

		}
		return url;
	}

	private static DesiredCapabilities getPerfectoExecutionCapabilities() {

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setPlatform(Platform.ANY);
		desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
														// remote execution

		mobileProperties = Settings.getInstance();
		desiredCapabilities.setCapability("user",
				mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password",
				mobileProperties.getProperty("PerfectoPassword"));

		return desiredCapabilities;
	}

	/**
	 * Function to return the Perfecto MobileCloud {@link RemoteWebDriver}
	 * object based on the parameters passed
	 * 
	 * @param platformName
	 *            The device platform to be used for the test execution (iOS,
	 *            Android, etc.)
	 * @param platformVersion
	 *            The device platform version to be used for the test execution
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param remoteUrl
	 *            The Perfecto MobileCloud URL to be used for the test execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getPerfectoRemoteWebDriverByDevicePlatform(
			String deviceId, String osVersionVersion, Browser browser,
			String remoteUrl, MobileExecutionPlatform executionPlatform) {
		String platformName = "";
		if (executionPlatform.equals("ANDROID")) {
			platformName = "Android";
		} else if (executionPlatform.equals("IOS")) {
			platformName = "ios";
		}
		DesiredCapabilities desiredCapabilities = getPerfectoExecutionCapabilities();
		desiredCapabilities.setBrowserName(browser.getValue());
		desiredCapabilities.setCapability("platformName", platformName);
		desiredCapabilities.setCapability("platformVersion", osVersionVersion);

		URL url = getUrl(remoteUrl);

		return new RemoteWebDriver(url, desiredCapabilities);
	}

	/**
	 * Function to return the Perfecto MobileCloud {@link RemoteWebDriver}
	 * object based on the parameters passed
	 * 
	 * @param manufacturer
	 *            The manufacturer of the device to be used for the test
	 *            execution (Samsung, Apple, etc.)
	 * @param model
	 *            The device model to be used for the test execution (Galaxy S6,
	 *            iPad Air, etc.)
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param remoteUrl
	 *            The Perfecto MobileCloud URL to be used for the test execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getPerfectoRemoteWebDriverByDeviceModel(
			String manufacturer, String model, Browser browser, String remoteUrl) {
		DesiredCapabilities desiredCapabilities = getPerfectoExecutionCapabilities();
		desiredCapabilities.setCapability("manufacturer", manufacturer);
		desiredCapabilities.setCapability("model", model);

		URL url = getUrl(remoteUrl);

		return new RemoteWebDriver(url, desiredCapabilities);
	}

	@SuppressWarnings("rawtypes")
	public static AppiumDriver getPerfectoAppiumDriver(
			SeleniumTestParameters testParameters) {

		AppiumDriver driver = null;

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("user",
				mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password",
				mobileProperties.getProperty("PerfectoPassword"));
		try {
			switch (testParameters.getMobileExecutionPlatform()) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName",
							testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer",
							testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model",
							testParameters.getModelName());
				}
				desiredCapabilities.setCapability("appPackage",
						mobileProperties
								.getProperty("Application_Package_Name"));
				desiredCapabilities.setCapability("appActivity",
						mobileProperties
								.getProperty("Application_MainActivity_Name"));
				// desiredCapabilities.setCapability("app",
				// "PUBLIC:appium/apiDemos.apk");
				try {
					driver = new AndroidDriver(new URL(
							mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}

				break;

			case IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName",
							testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer",
							testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model",
							testParameters.getModelName());
				}
				desiredCapabilities.setCapability("newCommandTimeout", 120);
				desiredCapabilities.setCapability("bundleId",
						mobileProperties.getProperty("IosBundleID"));
				// desiredCapabilities.setCapability("app",
				// "PUBLIC:appium/apiDemos.ipa");

				try {
					driver = new IOSDriver(new URL(
							mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);

				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName",
							testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer",
							testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model",
							testParameters.getModelName());
				}
				desiredCapabilities.setCapability("browserName", "Chrome");

				try {
					driver = new AndroidDriver(new URL(
							mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName",
							testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer",
							testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model",
							testParameters.getModelName());
				}
				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");

				try {
					driver = new IOSDriver(new URL(
							mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);

				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			default:
				throw new Exception("Unhandled Execution Mode!");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());

		}
		return driver;

	}
	
	public static WebDriver getPerfectoRemoteDriver(SeleniumTestParameters testParameters) {

		RemoteWebDriver driver = null;

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("user", mobileProperties.getProperty("PerfectoUser"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("PerfectoPassword"));
		try {
			switch (testParameters.getMobileExecutionPlatform()) {

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				desiredCapabilities.setCapability("browserName", "Chrome");
				desiredCapabilities.setCapability("takesScreenshot", true);

				try {
					driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);
				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				if (testParameters.getIsDeviceUdid()) {
					desiredCapabilities.setCapability("deviceName", testParameters.getDeviceName());
				} else {
					desiredCapabilities.setCapability("manufacturer", testParameters.getManuFacturerName());
					desiredCapabilities.setCapability("model", testParameters.getModelName());
				}
				// desiredCapabilities.setCapability("automationName",
				// "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");
				desiredCapabilities.setCapability("takesScreenshot", true);
				try {
					driver = new RemoteWebDriver(new URL(mobileProperties.getProperty("PerfectoHost")),
							desiredCapabilities);

				} catch (MalformedURLException e) {
					log.error(e.getMessage());
				}
				break;

			default:
				throw new Exception("Unhandled Execution Mode!");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());

		}
		return driver;

	}
	
}
