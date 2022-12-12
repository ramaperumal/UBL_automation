/**
 * 
 */
package com.DOTC.supportLibraries;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author 375669
 *
 */
public class Log {
	
	
	private static Logger logger = Logger.getLogger(Log.class.getName());
	
	public static void startTest(String sTestCaseName){
		
		Log.info("****************************************************************************************");
		 
		Log.info("****************************************************************************************");
	 
		Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
	 
		Log.info("****************************************************************************************");
	 
		Log.info("****************************************************************************************"); 
		
	}

	/**
	 * @param string
	 */
	public static void info(String message) {
		
		Log.info(message);
		
	}
	
	public static void warn(String message){
		
		Log.warn(message);
	}
	
	public static void error(String message){
		
		Log.error(message);
	}
	
	public static void fatal(String message){
		
		Log.fatal(message);
	}
	
	public static void debug(String message){
		
		Log.debug(message);
	}
	
}
