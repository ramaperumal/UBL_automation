package com.DOTC.supportLibraries;

import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

import com.DOTC.stepDefinitions.DOTCCukeHooks;

/**
 * Will be called before every TestNG Method * @author Cognizant
 */
public class WebDriverListener implements IInvokedMethodListener {

	static Logger log = Logger.getLogger(WebDriverListener.class);

	DOTCCukeHooks cukeHooks = new DOTCCukeHooks();

	private static Properties properties = Settings.getInstance();;

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

		SeleniumTestParameters testParameters = new SeleniumTestParameters();

		log.debug("BEGINNING: com.UA.supportLibraries.WebDriverListener-beforeInvocation");

		if (method.isTestMethod()) {

			try {
				setDefaultTestParameters(method, testParameters);
				DriverManager.setTestParameters(testParameters);

			} catch (Exception ex) {
				log.error(ex.getMessage());
				ex.printStackTrace();
			}

		} else {
			// log.warn("Provided method is NOT a TestNG testMethod!!!");
		}
		log.debug("END: org.stng.jbehave.LocalWebDriverListener.beforeInvocation");

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		log.debug("BEGINNING: WebDriverListener.afterInvocation");
		/*
		 * change the name of the test method that will appear in the report to one that
		 * will contain very handy when analysing results.
		 */
		if (method.isTestMethod()) {
			try {
				BaseTestMethod bm = (BaseTestMethod) testResult.getMethod();
				Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
				f.setAccessible(true);

				String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - "
						+ bm.getMethodName() + " - ";

				log.info("Renaming test method name from: '" + bm.getMethodName() + "' to: '" + newTestName);
				f.set(bm, newTestName);

			} catch (Exception ex) {
				System.out.println("afterInvocation exception:\n" + ex.getMessage());
				ex.printStackTrace();
			}
		}
		log.debug("END: WebDriverListener.afterInvocation");
	}

	private void setDefaultTestParameters(IInvokedMethod method, SeleniumTestParameters testParameters) {
		try {
			String executionMode = method.getTestMethod().getXmlTest().getLocalParameters().get("ExecutionMode");

			switch (executionMode) {

			case "LOCAL":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

				} else {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				}

				break;

			case "REMOTE":

				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

				} else {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserVersion") == null) {
					testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));

				} else {
					testParameters.setBrowserVersion(
							method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserVersion"));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("Platform") == null) {
					testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));

				} else {
					testParameters.setBrowserVersion(
							method.getTestMethod().getXmlTest().getLocalParameters().get("Platform"));
				}

				break;
			case "MOBILE":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileToolName") == null) {
					testParameters
							.setMobileToolName(MobileToolName.valueOf(properties.getProperty("DefaultMobileToolName")));
				} else {
					String mobileToolName = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileToolName");
					testParameters.setMobileToolName((MobileToolName.valueOf(mobileToolName)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
					testParameters.setMobileExecutionPlatform(
							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
				} else {
					String mobileExecutionPlatform = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileExecutionPlatform");
					testParameters
							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName") == null) {
					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

				} else {
					testParameters
							.setDeviceName(method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName"));
				}

				break;
			case "PERFECTO":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileToolName") == null) {
					testParameters
							.setMobileToolName(MobileToolName.valueOf(properties.getProperty("DefaultMobileToolName")));
				} else {
					String mobileToolName = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileToolName");
					testParameters.setMobileToolName((MobileToolName.valueOf(mobileToolName)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
					testParameters.setMobileExecutionPlatform(
							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
				} else {
					String mobileExecutionPlatform = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileExecutionPlatform");
					testParameters
							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
				}

				if (testParameters.getIsDeviceUdid()) {

					testParameters
							.setDeviceName(method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName"));

				} else {
					testParameters
							.setModelName(method.getTestMethod().getXmlTest().getLocalParameters().get("ModelName"));
					testParameters.setManuFacturerName(
							method.getTestMethod().getXmlTest().getLocalParameters().get("ManufacturerName"));

				}

				break;

			case "SEETEST":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
					testParameters.setMobileExecutionPlatform(
							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
				} else {
					String mobileExecutionPlatform = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileExecutionPlatform");
					testParameters
							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName") == null) {
					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

				} else {
					testParameters
							.setDeviceName(method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName"));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("SeeTestPort") == null) {
					testParameters.setSeeTestPort(properties.getProperty("SeeTestDefaultPort"));

				} else {
					testParameters.setSeeTestPort(
							method.getTestMethod().getXmlTest().getLocalParameters().get("SeeTestPort"));
				}

				break;

			case "SAUCELABS":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				testParameters.setSauceAccessKey(properties.getProperty("SauceKey"));
				testParameters.setSauceUsername(properties.getProperty("SauceUsername"));
				testParameters.setSauceTunnelId(properties.getProperty("SauceTunnelId"));
				testParameters.setSauceParentTunnel(properties.getProperty("SauceParentTunnel"));
				testParameters.setSauceScreenResolution(properties.getProperty("ScreenResolution"));
				testParameters.setSauceidleTimeout(Integer.parseInt(properties.getProperty("SauceidleTimeout")));
				testParameters.setMobileExecutionPlatform(MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
				testParameters.setMobileOSVersion(properties.getProperty("DefautMobileOsVersion"));
				testParameters.setMobiletBrowser(properties.getProperty("mobileBrowser"));
				testParameters.setMobileToolName(MobileToolName.valueOf(properties.getProperty("DefaultMobileToolName")));
				testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName") == null) {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));

				} else {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserName")));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("OS") == null) {
					testParameters.setDesktopOSVersion(properties.getProperty("OS"));

				}
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileToolName") == null) {
					testParameters
							.setMobileToolName(MobileToolName.valueOf(properties.getProperty("DefaultMobileToolName")));
				} else {
					String mobileToolName = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileToolName");
					testParameters.setMobileToolName((MobileToolName.valueOf(mobileToolName)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
					testParameters.setMobileExecutionPlatform(
							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
				} else {
					String mobileExecutionPlatform = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileExecutionPlatform");
					testParameters
							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName") == null) {
					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

				} else {
					testParameters
							.setDeviceName(method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName"));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserVersion") == null) {
					testParameters.setBrowserVersion(properties.getProperty("DefaultBrowserVersion"));

				} else {
					testParameters.setBrowserVersion(
							method.getTestMethod().getXmlTest().getLocalParameters().get("BrowserVersion"));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("Platform") == null) {
					testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));

				} else {
					testParameters.setBrowserVersion(
							method.getTestMethod().getXmlTest().getLocalParameters().get("Platform"));
				}
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileOsVersion") == null) {
					testParameters.setMobileOSVersion(properties.getProperty("DefautMobileOsVersion"));

				} else {
					testParameters.setMobileOSVersion(
							method.getTestMethod().getXmlTest().getLocalParameters().get("MobileOsVersion"));
				}

				break;

			case "MINT":
				testParameters.setExecutionMode(ExecutionMode.valueOf(executionMode));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileToolName") == null) {
					testParameters
							.setMobileToolName(MobileToolName.valueOf(properties.getProperty("DefaultMobileToolName")));
				} else {
					String mobileToolName = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileToolName");
					testParameters.setMobileToolName((MobileToolName.valueOf(mobileToolName)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileExecutionPlatform") == null) {
					testParameters.setMobileExecutionPlatform(
							MobileExecutionPlatform.valueOf(properties.getProperty("DefaultMobileExecutionPlatform")));
				} else {
					String mobileExecutionPlatform = method.getTestMethod().getXmlTest().getLocalParameters()
							.get("MobileExecutionPlatform");
					testParameters
							.setMobileExecutionPlatform((MobileExecutionPlatform.valueOf(mobileExecutionPlatform)));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName") == null) {
					testParameters.setDeviceName(properties.getProperty("DefaultDeviceName"));

				} else {
					testParameters
							.setDeviceName(method.getTestMethod().getXmlTest().getLocalParameters().get("DeviceName"));
				}

				if (method.getTestMethod().getXmlTest().getLocalParameters().get("MobileOsVersion") == null) {
					testParameters.setMobileOSVersion(properties.getProperty("DefautMobileOsVersion"));

				} else {
					testParameters.setMobileOSVersion(
							method.getTestMethod().getXmlTest().getLocalParameters().get("MobileOsVersion"));
				}
				break;

			default:
				testParameters.setExecutionMode(ExecutionMode.valueOf(properties.getProperty("DefaultExecutionMode")));
				if (method.getTestMethod().getXmlTest().getLocalParameters().get("BrowerName") == null) {
					testParameters.setBrowser(Browser
							.valueOf(method.getTestMethod().getXmlTest().getLocalParameters().get("BrowerName")));
				} else {
					testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
				}
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

}