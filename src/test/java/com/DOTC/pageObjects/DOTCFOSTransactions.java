package com.DOTC.pageObjects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;


public class DOTCFOSTransactions extends DOTCCommon{

    DOTCRestService dotc = new DOTCRestService();
    public String dfpDate;
    public String absStartDate;
    public String vacationStartDate;
    public static String employeeIDForSick = "";
    public static boolean sickAlreadyPresent = false;
    public String dateSeq;
    
	public DOTCFOSTransactions() {
		this.driver = super.driver;
				
	}
	
	DOTCCalendarScreen dotcCal= new DOTCCalendarScreen();
	
	public void FOSTransaction(String strFOSCommand) {
		try {

			String strFosXML = dotc.initializeTestDataFiles("FOSRequest.xml");
			
				String strModifiedFosXML = dotc.modifyXml(strFosXML, strFOSCommand);
				String replyForCommand = dotc.postFOSrequest(strModifiedFosXML);
				System.out.println("Reply from FOS for command: " + strFOSCommand + " is: " + replyForCommand);

				if (replyForCommand.contains("OK")) {

					System.out.println("Data Creation is successful");
					ExtentTestManager.getTest().log(LogStatus.PASS,
							"Data Creation is successful for command : " + strFOSCommand);
					Assert.assertTrue("Data Creation is successful", true);					

				}
				
				else if (replyForCommand.contains("RPT") || replyForCommand.contains("FIN OR IG OR KIG")
						|| replyForCommand.contains("CLOSE ENTRY") || replyForCommand.contains("HZ  NOT REQUIRED")
						|| replyForCommand.contains("NOT DETAIL MODE")) {
					

						ExtentTestManager.getTest().log(LogStatus.FAIL,
								"Data Creation is not successful for command : " + strFOSCommand);
						
							Assert.fail("Data Creation is not successful for command : " + strFOSCommand);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//to create Rap activity for reserve pilot on current date
	//16/03/22
	public void createRAPactivity( String date,String reserveEmpID) {
		
		Integer index = Integer.parseInt(reserveEmpID);
		
		String reserveEmp = DOTCRestService.rsvForRAP.get(index);
		
		String FOSCommandForRAP = "IG$EC$HYR/" + reserveEmp + "/" +date+ "/2000$EC$ET";
		FOSTransaction(FOSCommandForRAP);
		//Util.defaultwait(120000);
		 System.out.println("reserve id " +reserveEmp);
		ExtentTestManager.getTest().log(LogStatus.PASS, "Data creation is successful for Rap Activity");
		
	}
	
	
	//to create training activity for reserve pilot from current date to next date
	//16/03/22
	public void createTrainingActivity (String date, String reserveEmpID , String bidcard) {
		String[] dateArr = dotcCal.getTrainingActivityDate(date);
		String startDate= dateArr[0];
		String endDate= dateArr[1];
		
          Integer index = Integer.parseInt(reserveEmpID);
		  String reserveEmp = DOTCRestService.rsvForRAP.get(index);
		  
          String[] bidStatus = bidcard.split("/");
          
          String equipment = bidStatus[1];
          String seat = bidStatus[2];
          
		   
		  String FOSCommandForTraining= "IG$EC$A4T/" + reserveEmp + "/1R/" +startDate+ "/" +endDate+ "/" +equipment+ "/" +seat+ "/0000" + "/2359$EC$ET";
		  FOSTransaction(FOSCommandForTraining);
		  System.out.println("reserve id " +reserveEmp);
			Util.defaultwait(120000);
		  ExtentTestManager.getTest().log(LogStatus.PASS, "Data creation is successful for Training Activity");
	}
	
	// To create DFP for provided emp and date
	// 17/03/22
	public void createDFP(String emp, String date) throws ParseException {

		String month = DOTCRestService.contractMonth.substring(0, 3);
		dfpDate = date + month;
		String FOSCommandForDFP = "IG$EC$ND*/" + emp + "/" + month + "$EC$NHA/24/" + date + month + "$EC$HZ$EC$ET";
		System.out.println("DFP cmnd: " + FOSCommandForDFP);
		FOSTransaction(FOSCommandForDFP);

	}

	// To create one day absence(RL) for provided emp and date
	// 17/03/22

	public void createAbsence(String emp, String date) throws ParseException {

		String month = DOTCRestService.contractMonth.substring(0, 3);
		String year = DOTCRestService.contractMonth.substring(5, 7);
		absStartDate = date + month + year;
		String FOSCommandForAbsence = "IG$EC$A4/" + emp + "/RL/" + absStartDate + "/" + absStartDate + "$EC$ET";
		System.out.println("Abs cmnd: " + FOSCommandForAbsence);
		FOSTransaction(FOSCommandForAbsence);

	}

	// To create one day vacation(VC) for provided emp and date
	// 17/03/22
	public void createVacation(String emp, String date) throws ParseException {

		String month = DOTCRestService.contractMonth.substring(0, 3);
		String year = DOTCRestService.contractMonth.substring(5, 7);
		vacationStartDate = date + month + year;
		String FOSCommandForAbsence = "IG$EC$A4/" + emp + "/VC/" + vacationStartDate + "/" + vacationStartDate
				+ "$EC$ET";
		System.out.println("Vacation cmnd: " + FOSCommandForAbsence);
		FOSTransaction(FOSCommandForAbsence);
		System.out.println("Waiting for FOS and CCS sync");
		Util.defaultwait(120000);

	}
	
	//17May22
	//To create sick for LH emp id
	public void createSickPuckInFOSwithDate( String plannedDate, String empID , String base) {
		try {
		String[] dateArr = dotcCal.getSickDate(plannedDate);
		String sickPuckStDate = dateArr[0];
		String sickPuckEndDate = dateArr[1];
	
		Integer index = Integer.parseInt(empID);
		dotc.getLHidswithNoActivity(base);
		List<String> emp = DOTCRestService.rsvForRAP;
		employeeIDForSick =emp.get(index);
		String FOSCommandForSick = "IG$EC$A4/" + employeeIDForSick + "/SK/" + sickPuckStDate + "/" + sickPuckEndDate+ "$EC$ET" ;
		FOSTransactionForSick(FOSCommandForSick,true);
		System.out.println("LH employee id " +employeeIDForSick);
		
		
		if(DOTCFOSTransactions.sickAlreadyPresent) {
			
		    index++;
		    String empArr1 = String.valueOf(index);
		    createSickPuckInFOSwithDate(plannedDate, empArr1, base);
		    
		}
		} catch(Exception ex) {
			
		}
}
	
	//17may
	//For checking sick command conditions
		public void FOSTransactionForSick(String strFOSCommand, boolean assertRequired) {
			try {

				String strFosXML = dotc.initializeTestDataFiles("FOSRequest.xml");
				int reTryCount = 3;
				for (int i = 0; i < reTryCount; i++) {

					String strModifiedFosXML = dotc.modifyXml(strFosXML, strFOSCommand);
					String replyForCommand = dotc.postFOSrequest(strModifiedFosXML);
					System.out.println("Reply from FOS for command: " + strFOSCommand + "is: " + replyForCommand);

					if (replyForCommand.contains("CONFLICT") || replyForCommand.contains("OPEN ABS ALREADY")|| replyForCommand.contains("ZERO SK AVBL. USE UNPAID REMOVAL CODE")) {
						sickAlreadyPresent = true;
						System.out.println("Sick Data is already present for the dates mentioned or zero SK available for the Employee");
						break;
					}
					/*
					 * End of Conditions for existing sick puck
					 */

					if (replyForCommand.contains("OK")) {
						sickAlreadyPresent = false;
						ExtentTestManager.getTest().log(LogStatus.PASS,
								"Data Creation is successful for command : " + strFOSCommand);
						Assert.assertTrue("Sick Data Creation is successful", true);
						System.out.println("Waiting for FOS and CCS integration...");
						Util.defaultwait(120000);
						break;

					}

					// Additional check for sick puck creation
					else if (replyForCommand.contains("RPT") || replyForCommand.contains("FIN OR IG OR KIG")
							|| replyForCommand.contains("ET IN PROGRESS")
							|| replyForCommand.contains("CONTINUITY ERROR SEQ")) {

						if (i == reTryCount - 1) {

							ExtentTestManager.getTest().log(LogStatus.FAIL,
									"Data Creation is not successful for command : " + strFOSCommand);
							if (assertRequired) {
								Assert.fail("Data Creation is not successful for command : " + strFOSCommand);
							}
						}
						continue;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//21July22		
public void createROWindow(String empOnActivity,String seqNumCurrent, String seqDate) throws ParseException{
	 SimpleDateFormat seqDateORG = new SimpleDateFormat("yyyy-MM-dd");
	 SimpleDateFormat seqDtNeed = new SimpleDateFormat("ddMMM");
	  dateSeq = seqDtNeed.format(seqDateORG.parse(seqDate)); 
	 System.out.println("date needed " +dateSeq);
	 
	 String FOSCommandForRO = "IG$EC$2G/" +empOnActivity + "/" +seqNumCurrent+ "/" +dateSeq+ "/" + "25" +"$EC$ET" ;
	 System.out.println("RO Window  cmnd: " +FOSCommandForRO );
		FOSTransaction(FOSCommandForRO);
		Util.defaultwait(120000);

}

}

	
	