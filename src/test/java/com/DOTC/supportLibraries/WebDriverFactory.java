package com.DOTC.supportLibraries;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.*;

/**
 * Factory class for creating the {@link WebDriver} object as required
 * 
 * @author Cognizant
 */
public class WebDriverFactory {
	private static Properties properties;
	static Logger log = Logger.getLogger(WebDriverFactory.class);

	private WebDriverFactory() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the appropriate {@link WebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @return The corresponding {@link WebDriver} object
	 */
	@SuppressWarnings("deprecation")
	public static WebDriver getWebDriver(Browser browser) {
		WebDriver driver = null;
		properties = Settings.getInstance();
		boolean proxyRequired = Boolean.parseBoolean(properties
				.getProperty("ProxyRequired"));
		try {
			switch (browser) {
			case CHROME:
				// Takes the system proxy settings automatically
				System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
				ChromeOptions options = new ChromeOptions();
                options.addArguments("--ignore-certificate-errors");
               System.setProperty("webdriver.chrome.driver",
                       System.getProperty("user.dir") + properties.getProperty("ChromeDriverPath"));
               driver = new ChromeDriver(options);
				break;

			case FIREFOX:
				// Takes the system proxy settings automatically
				System.setProperty("webdriver.gecko.driver", properties.getProperty("GeckoDriverPath"));
				driver = new FirefoxDriver();
				break;

			case INTERNET_EXPLORER:
				// Takes the system proxy settings automatically

				System.setProperty("webdriver.ie.driver",
						properties.getProperty("InternetExplorerDriverPath"));
				driver = new InternetExplorerDriver();
				break;
				
			case EDGE:
				// Takes the system proxy settings automatically

				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + properties.getProperty("EdgeDriverPath"));
				driver = new EdgeDriver();
				break;

			case OPERA:
				// Does not take the system proxy settings automatically!
				// NTLM authentication for proxy NOT supported

				if (proxyRequired) {
					DesiredCapabilities desiredCapabilities = getProxyCapabilities();
					driver = new OperaDriver(desiredCapabilities);
				} else {
					driver = new OperaDriver();
				}

				break;

			case SAFARI:
				// Takes the system proxy settings automatically

				driver = new SafariDriver();
				break;

			default:
				throw new Exception("Unhandled browser!");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			ex.printStackTrace();
		}

		return driver;
	}

	private static DesiredCapabilities getProxyCapabilities() {
		properties = Settings.getInstance();
		String proxyUrl = properties.getProperty("ProxyHost") + ":"
				+ properties.getProperty("ProxyPort");

		Proxy proxy = new Proxy();
		proxy.setProxyType(ProxyType.MANUAL);
		proxy.setHttpProxy(proxyUrl);
		proxy.setFtpProxy(proxyUrl);
		proxy.setSslProxy(proxyUrl);

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);

		return desiredCapabilities;
	}

	/**
	 * Function to return the {@link RemoteWebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param browserVersion
	 *            The browser version to be used for the test execution
	 * @param platform
	 *            The {@link Platform} to be used for the test execution
	 * @param remoteUrl
	 *            The URL of the remote machine to be used for the test
	 *            execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getRemoteWebDriver(Browser browser,
			String browserVersion, Platform platform, String remoteUrl) {

		properties = Settings.getInstance();
		boolean proxyRequired = Boolean.parseBoolean(properties
				.getProperty("ProxyRequired"));

		DesiredCapabilities desiredCapabilities = null;
		if (browser.equals(Browser.OPERA) && proxyRequired) {
			desiredCapabilities = getProxyCapabilities();
		} else {
			desiredCapabilities = new DesiredCapabilities();
		}

		desiredCapabilities.setBrowserName(browser.getValue());

		if (browserVersion != null) {
			desiredCapabilities.setVersion(browserVersion);
		}
		if (platform != null) {
			desiredCapabilities.setPlatform(platform);
		}

		desiredCapabilities.setJavascriptEnabled(true); // Pre-requisite for
														// remote execution

		URL url = getUrl(remoteUrl);

		return new RemoteWebDriver(url, desiredCapabilities);
	}

	private static URL getUrl(String remoteUrl) {
		URL url = null;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();

		}
		return url;
	}

	/**
	 * Function to return the {@link RemoteWebDriver} object based on the
	 * parameters passed
	 * 
	 * @param browser
	 *            The {@link Browser} to be used for the test execution
	 * @param remoteUrl
	 *            The URL of the remote machine to be used for the test
	 *            execution
	 * @return The corresponding {@link RemoteWebDriver} object
	 */
	public static WebDriver getRemoteWebDriver(Browser browser, String remoteUrl) {
		return getRemoteWebDriver(browser, null, null, remoteUrl);
	}
	
	/**
	 * This method will return driver path for local execution
	 * @return driverPath
	 */
	public static String getDriverPath(String browserDriverPath){
		
		String driverPath = properties.getProperty(browserDriverPath);
		if(driverPath != null){
			return driverPath;
		}
		else throw new RuntimeException("Driver Path not specified in "
				+ "the Configuration.properties file for the Key:driverPath");
	}
		
	
	/**
	 * This method returns implicit wait time for execution 
	 * @return wait time
	 */
	public static long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) {
			try{
				return Long.parseLong(implicitlyWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;		
	}
 
	
	/**
	 * This method will return application URL 
	 * @return application URL
	 */  
	public static String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) 
			return url;
		else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
	}
 
	
	
	
 
	
	/**
	 * This method will maximize browser window before execution 
	 * @return
	 */
	public static Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if(windowSize != null) return Boolean.valueOf(windowSize);
		return true;
	}
	
	
	/**
	 * This method returns Browserstack UserName
	 * @return User name
	 */
	public static String getUserName(){
		
		String username = properties.getProperty("username");
		if(username != null) 
			return username;
		else throw new RuntimeException("User name not specified in the Configuration.properties ");
	}
	
	
	/**
	 * This method returns Browserstack Access Keys
	 * @return Access Keys
	 */
	public static String getAccessKey(){
		
		String accessKey = properties.getProperty("accessKey");
		if(accessKey != null) 
			return accessKey;
		else throw new RuntimeException("Access Key not specified in the Configuration.properties ");
		
	}

	

}