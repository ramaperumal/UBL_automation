package com.DOTC.supportLibraries;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {

	private static ExtentReports extent;
	private static String extentReportPath;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			// Set HTML reporting file location

			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				extent = new ExtentReports(getReportPath(), true);
			} else {
				extent = new ExtentReports(getReportPath(), true);
			}

			// extent.loadConfig(new File( workingDir +
			// "\\src\\test\\resources\\extent-config.xml"));
		}
		return extent;
	}

	public synchronized static String getReportPath() {
		String workingDir = System.getProperty("user.dir");
		String timeAppend = Util.getFormattedTime(new Date(), "ddMMMYY_hhmmss");
		if (extentReportPath == null) {
			// Set HTML reporting file location

			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				extentReportPath = workingDir + "\\ExtentReports\\ExtentReportResults_" + timeAppend + ".html";
			} else {
				extentReportPath = workingDir + "/ExtentReports/ExtentReportResults_" + timeAppend + ".html";
			}

			// extent.loadConfig(new File( workingDir +
			// "\\src\\test\\resources\\extent-config.xml"));
		}
		return extentReportPath;
	}
}
