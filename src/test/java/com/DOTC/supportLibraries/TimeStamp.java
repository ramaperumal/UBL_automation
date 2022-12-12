package com.DOTC.supportLibraries;

import java.io.File;
import java.util.Properties;

/**
 * Singleton class which manages the creation of timestamped result folders for
 * every test batch execution
 * 
 * @author Cognizant
 */
public class TimeStamp {
	public static volatile String reportPathWithTimeStamp;
	public static volatile String OldreportPathWithTimeStamp;
	public static volatile String allureReportPathWithTimeStamp;
	private TimeStamp() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to return the timestamped result folder path
	 * 
	 * @return The timestamped result folder path
	 */
	public static String getInstance() {
		if (reportPathWithTimeStamp == null) {
			synchronized (TimeStamp.class) {
				if (reportPathWithTimeStamp == null) { // Double-checked locking
					Properties properties = Settings.getInstance();
					String timeStamp = "Run_"
							+ Util.getCurrentFormattedTime(
									properties.getProperty("DateFormatString"))
									.replace(" ", "_").replace(":", "-");

					reportPathWithTimeStamp = Util.getResultsPath()
							+ Util.getFileSeparator() + timeStamp;

					new File(reportPathWithTimeStamp).mkdirs();
				}
			}
		}

		return reportPathWithTimeStamp;
	}

	/**
	 * Function to return the timestamped result folder path
	 * 
	 * @return The timestamped result folder path
	 */
	public static String getOldReportInstance() {
		if (OldreportPathWithTimeStamp == null) {
			synchronized (TimeStamp.class) {
				if (OldreportPathWithTimeStamp == null) { // Double-checked
															// locking
					Properties properties = Settings.getInstance();
					String timeStamp = "Run_"
							+ Util.getCurrentFormattedTime(
									properties.getProperty("DateFormatString"))
									.replace(" ", "_").replace(":", "-");

					OldreportPathWithTimeStamp = Util.getOldResultPath()
							+ Util.getFileSeparator() + timeStamp;

					new File(OldreportPathWithTimeStamp).mkdirs();
				}
			}
		}

		return OldreportPathWithTimeStamp;
	}
	
	public static String getInstanceAllure() {
		if (allureReportPathWithTimeStamp == null) {
			synchronized (TimeStamp.class) {
				if (allureReportPathWithTimeStamp == null) { // Double-checked locking
					Properties properties = Settings.getInstance();
					String timeStamp = "Allure-Result_Run_"
							+ Util.getCurrentFormattedTime(
									properties.getProperty("DateFormatString"))
									.replace(" ", "_").replace(":", "-");

					allureReportPathWithTimeStamp = Util.getResultsPath()
							+ Util.getFileSeparator() + timeStamp;

					new File(allureReportPathWithTimeStamp).mkdirs();
				}
			}
		}

		return allureReportPathWithTimeStamp;
	}


	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}