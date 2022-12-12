package com.DOTC.supportLibraries;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class MintDriverFactory {

	private static Properties mobileProperties;
	static Logger log = Logger.getLogger(MintDriverFactory.class);


	private MintDriverFactory() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the appropriate {@link AppiumDriver} object based on
	 * the parameters passed
	 * 
	 * @param MobileExecutionPlatform
	 *            Mobile platform to be used for Test execution
	 * @param deviceName
	 *            The device name or ID of mint cloud to be used for test
	 *            execution
	 * @param host
	 *            Host Url of mint to be used for test execution
	 * @return The corresponding {@link AppiumDriver} object
	 */
	@SuppressWarnings("rawtypes")
	public static AppiumDriver getmintAppiumDriver(MobileExecutionPlatform executionPlatform, String deviceName,
			String mintHost, String mobileVersion) {

		AppiumDriver driver = null;
		mobileProperties = Settings.getInstance();
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("username", mobileProperties.getProperty("mintUsername"));
		desiredCapabilities.setCapability("password", mobileProperties.getProperty("mintPassword"));

		try {
			switch (executionPlatform) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", mobileVersion);
				desiredCapabilities.setCapability("app", mobileProperties.getProperty("mintAndroidApplicationName"));

				try {
					driver = new AndroidDriver(new URL(mintHost), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
					}
				break;

			case IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("platformVersion", mobileVersion);
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("app", mobileProperties.getProperty("mintiOSApplicationName"));

				desiredCapabilities.setCapability("newCommandTimeout", 120);
				try {
					driver = new IOSDriver(new URL(mintHost), desiredCapabilities);

				} catch (MalformedURLException e) {
					e.printStackTrace();
					}
				break;

			case WEB_ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", mobileVersion);
				desiredCapabilities.setCapability("browserName", "Chrome");
				try {
					driver = new AndroidDriver(new URL(mintHost), desiredCapabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
					}
				break;

			case WEB_IOS:

				desiredCapabilities.setCapability("platformName", "ios");
				desiredCapabilities.setCapability("platformVersion", mobileVersion);
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("automationName", "Appium");
				desiredCapabilities.setCapability("browserName", "Safari");
				try {
					driver = new IOSDriver(new URL(mintHost), desiredCapabilities);

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

	public static WebDriver getAppiumRemoteWebDriver(MobileExecutionPlatform executionPlatform, String deviceName,
			String version, Boolean installApp, String appiumURL) {

		WebDriver driver = null;
		mobileProperties = Settings.getInstance();
		String appPath = installApplication(installApp);
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		try {
			switch (executionPlatform) {

			case ANDROID:

				desiredCapabilities.setCapability("platformName", "Android");
				desiredCapabilities.setCapability("deviceName", deviceName);
				// desiredCapabilities.setCapability("udid",deviceName);
				desiredCapabilities.setCapability("platformVersion", version);
				desiredCapabilities.setCapability("app", appPath);
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

	private static String installApplication(Boolean installApp) {
		String appPath = "";

		try {
			if (installApp) {
				File path = new File(mobileProperties.getProperty("AndroidApplicationPath"));
				appPath = path.getAbsolutePath();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return appPath;
	}
}
