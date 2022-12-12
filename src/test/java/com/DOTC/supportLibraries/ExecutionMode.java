package com.DOTC.supportLibraries;

/**
 * Enumeration to represent the mode of execution
 * 
 * @author Cognizant
 */
public enum ExecutionMode {
	LOCAL("local"),
	REMOTE("remote"),
	PERFECTO("perfecto"), 
	MOBILE("appium"),

	/**
	 * Execute on a mobile device using Appium
	 */

	SEETEST("seetest"),
	/**
	 * Execute on a mobile device using SeeTest
	 */
	MINT("mint"),

	/**
	 * Execute on a mobile device using mint
	 */
	
	SAUCELABS("saucelabs"),

	/**
	 * Execute on a mobile device using SauceLabs
	 */
	
	BROWSERSTACK("browserstack"),
	
	/**
	 * Execute on a mobile device using MobileCentre
	 */
	
	;

	private String value;

	ExecutionMode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}