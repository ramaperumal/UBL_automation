package com.DOTC.supportLibraries;

import org.openqa.selenium.Platform;

/**
 * Class to encapsulate various input parameters required for each test script
 * 
 * @author Cognizant
 */
public class SeleniumTestParameters {

	private ExecutionMode executionMode;
	private Browser browser;
	private String browserVersion;
	private Platform platform;
	private String sauceAccessKey;
	private String sauceUsername;
	private String deviceName;
	private MobileExecutionPlatform mobileExecutionPlatform;
	private MobileToolName mobileToolName;
	private String mobileOsVersion;
	private String desktopOsVersion;
	private String manufacturer;
	private String modelName;
	private boolean isDeviceUdid;
	private String scenarioName;
	private String environMent;
	private String seeTestPort;
	private String sauceParentTunnel;
	private String sauceScreenResolution;
	private String mobileBrowser;
	private int sauceIdleTimeout;
	private String sauceTunnelParent;

	public SeleniumTestParameters() {

	}
	
	public ExecutionMode getExecutionMode() {
		return executionMode;
	}
	
	

	/**
	 * Function to set the {@link ExecutionMode} for the test being executed
	 * 
	 * @param executionMode
	 *            The {@link ExecutionMode} for the test being executed
	 */
	public void setExecutionMode(ExecutionMode executionMode) {
		this.executionMode = executionMode;
	}

	/**
	 * Function to get the {@link MobileExecutionPlatform} for the test being
	 * executed
	 * 
	 * @return The {@link MobileExecutionPlatform} for the test being executed
	 */
	public MobileExecutionPlatform getMobileExecutionPlatform() {
		return mobileExecutionPlatform;
	}

	/**
	 * Function to set the {@link MobileExecutionPlatform} for the test being
	 * executed
	 * 
	 * @param executionPlatform
	 *            The {@link MobileExecutionPlatform} for the test being
	 *            executed
	 */
	public void setMobileExecutionPlatform(
			MobileExecutionPlatform mobileExecutionPlatform) {
		this.mobileExecutionPlatform = mobileExecutionPlatform;
	}

	/**
	 * Function to get the {@link MobileToolName} for the test being executed
	 * 
	 * @return The {@link MobileToolName} for the test being executed
	 */
	public MobileToolName getMobileToolName() {
		return mobileToolName;
	}

	/**
	 * Function to set the {@link MobileToolName} for the test being executed
	 * 
	 * @param toolName
	 *            The {@link MobileToolName} for the test being executed
	 */
	public void setMobileToolName(MobileToolName mobileToolName) {
		this.mobileToolName = mobileToolName;
	}

	/**
	 * Function to get the {@link Browser} on which the test is to be executed
	 * 
	 * @return The {@link Browser} on which the test is to be executed
	 */
	public Browser getBrowser() {
		return browser;
	}

	/**
	 * Function to set the {@link Browser} on which the test is to be executed
	 * 
	 * @param browser
	 *            The {@link Browser} on which the test is to be executed
	 */
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	/**
	 * Function to get the OS Version of device on which the test is to be
	 * executed
	 * 
	 * @return The OS Version of device Version on which the test is to be
	 *         executed
	 */
	public String getMobileOSVersion() {
		return mobileOsVersion;
	}

	/**
	 * Function to set the OS Version of device Version on which the test is to
	 * be executed
	 * 
	 * @param version
	 *            The OS Version of device Version on which the test is to be
	 *            executed
	 */
	public void setDesktopOSVersion(String OsVersion) {
		this.desktopOsVersion= OsVersion;
	}
	/**
	 * Function to get the OS Version of device on which the test is to be
	 * executed
	 * 
	 * @return The OS Version of device Version on which the test is to be
	 *         executed
	 */
	public String getDesktopOSVersion() {
		return desktopOsVersion;
	}

	/**
	 * Function to set the OS Version of device Version on which the test is to
	 * be executed
	 * 
	 * @param version
	 *            The OS Version of device Version on which the test is to be
	 *            executed
	 */
	public void setMobileOSVersion(String mobileOsVersion) {
		this.mobileOsVersion = mobileOsVersion;
	}

	/**
	 * Function to get the name of the mobile device on which the test is to be
	 * executed
	 * 
	 * @return The name of the mobile device on which the test is to be executed
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * 
	 * @param deviceName
	 *            The name of the mobile device on which the test is to be
	 *            executed
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * Function to get the name of the manufacturer Name on which the test is to
	 * be executed
	 * 
	 * @return The name of the manufacturer Name on which the test is to be
	 *         executed
	 */
	public String getManuFacturerName() {
		return manufacturer;
	}

	/**
	 * 
	 * @param manufacturer
	 *            The name of the manufacturer Name on which the test is to be
	 *            executed
	 */
	public void setManuFacturerName(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Function to get the name of the modelName on which the test is to be
	 * executed
	 * 
	 * @return The name of the modelName on which the test is to be executed
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * 
	 * @param modelName
	 *            The name of the modelName on which the test is to be executed
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * Function to get the boolean if Device is UDID
	 * 
	 * @return boolean if Device is UDID
	 */
	public boolean getIsDeviceUdid() {
		return isDeviceUdid;
	}

	/**
	 * set the boolean if the device is UDID
	 */
	public void setIsDeviceUdid(boolean isDeviceUdid) {
		this.isDeviceUdid = isDeviceUdid;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getScenarioName() {
		return scenarioName;
	}
	
	/**
	 * Function to get the See Test Port on which the test is to be executed
	 * 
	 * @return The See Test Port on which the test is to be executed
	 */
	public String getSeeTestPort() {
		return seeTestPort;
	}

	/**
	 * Function to set the See Test Port on which the test is to be executed
	 * 
	 * @param version
	 *            The See Test Port on which the test is to be executed
	 */
	public void setSeeTestPort(String seeTestPort) {
		this.seeTestPort = seeTestPort;
	}
	
	
	/**
	 * Function to get the Browser Version on which the test is to be executed
	 * 
	 * @return The Browser Version on which the test is to be executed
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}

	/**
	 * Function to set the Browser Version on which the test is to be executed
	 * 
	 * @param version
	 *            The Browser Version on which the test is to be executed
	 */
	public void setBrowserVersion(String version) {
		this.browserVersion = version;
	}

	/**
	 * Function to get the {@link Platform} on which the test is to be executed
	 * 
	 * @return The {@link Platform} on which the test is to be executed
	 */
	public Platform getPlatform() {
		return platform;
	}

	/**
	 * Function to set the {@link Platform} on which the test is to be executed
	 * 
	 * @param platform
	 *            The {@link Platform} on which the test is to be executed
	 */
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public String getSauceAccessKey() {
		return sauceAccessKey;
	}

	public void setSauceAccessKey(String key) {
		this.sauceAccessKey=key;
		
	}

	public String getSauceUsername() {
		return sauceUsername;
	}

	public void setSauceUsername(String sauceUsr) {
		this.sauceUsername = sauceUsr;
	}

	public String getSauceTunnelId() {
		return sauceParentTunnel;
	}

	public void setSauceTunnelId(String tunnelId) {
		this.sauceParentTunnel = tunnelId;
	}

	public String getSauceScreenResolution() {
		return sauceScreenResolution;
	}

	public void setSauceScreenResolution(String screenRes) {
		this.sauceScreenResolution = screenRes;
	}

	public String getMobiletBrowser() {
		return mobileBrowser;
	}

	public void setMobiletBrowser(String browser) {
		this.mobileBrowser = browser;
	}

	public int getSauceidleTimeout() {
		return sauceIdleTimeout;
	}

	public void setSauceidleTimeout(int time) {
		this.sauceIdleTimeout = time;
	}

	public void setSauceParentTunnel(String tunnelParent) {
		this.sauceTunnelParent=tunnelParent;		
	}
	public String getSauceParentTunnel() {
		return sauceTunnelParent;		
	}

}