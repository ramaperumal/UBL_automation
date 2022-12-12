package com.DOTC.pageObjects;

public class DOTCJsonXmlPath {
	
	//keep all the xml and json filenames here
	public static final String searchSequencesDFW787 = "searchSequencesForFA_DFW_787.json";
	public static final String searchSequencesDCA320 = "searchSequencesForFA_DCA_320.json";
	public static final String searchSequencesLGA320 = "searchSequencesForFA_LGA_320.json";
	public static final String searchSequencesMIA777 = "searchSequencesForFA_MIA_777.json";
	public static final String searchSequencesORD787 = "searchSequencesForFA_ORD_787.json";
	public static final String strSrchSeqFileName = "searchSeqFA.json";
	public static final String searchSequences = "searchSequencesForFA.json";
	public static final String EmployeeIDSrchSeqFA = "$..employeeID";
	public static final String strFOSReqFileName = "FOSRequest.xml";
	
	//keep all the static json paths here
	public static final String EmployeeID= "$..employeeID";//List of valid LH
	public static final String EmployeeIDBidStatus= "$..employeeID";//List of valid LH with bid status
	public static final String SequenceNumbers = "$..sequenceNumber";
	public static final String SeqNumSameBidStatus = "$..sequenceNumber";//List of seq for that bid status
	public static final String equipmentID = "$.bidStatuses[0].currentEquipment";
	public static final String positionCodes = "$.bidStatuses.currentEquipment";
	public static final String divisionCodes = "$..divisionCodes";
	public static final String pureDomestic = "$.pureDomestic";
	public static final String includeEquipments = "$.includeEquipments[0]";
	public static final String includePositions = "$.includePositions[0]";
	public static final String strStartDateTime = "$..sequenceStartDateTime.localTime";
	
	
	
	
	public static final String SrchSeqFABase = "$.base";
	public static final String SrchSeqFAContractMonth = "$.contractMonth";
	public static final String SrchSeqFAStart = "$.seqStartFrom";
	public static final String SeniorityNum = "$.myInfo.seniorityNumber";
	public static  final String baseDFW = "$..testdata.data..base_data..Base_DFW.Base";
	public static final String getBaseAirport = "$..iataCode";
	
	//keep all the static xpaths here
	

}
