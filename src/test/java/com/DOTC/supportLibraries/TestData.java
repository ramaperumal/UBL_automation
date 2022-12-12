package com.DOTC.supportLibraries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import com.DOTC.stepDefinitions.DOTCCukeHooks;
public class TestData {

	public Object testDataObject;
	public JSONArray testDataJsonArray;
	public static HashMap<String, Object> testDataMap = new HashMap<String, Object>();
	public static HashMap<String, Object> testDataSetMap = new HashMap<String, Object>();
	
	public static String strGlobalURL = "";
	public String jsonTestDataFile ="";
	public static String strBase ="";
	public static String strBasicAuth ="";
	public static String strSource ="";
	public String csvTestDataFile ="";
	public String customTestDataFile="";
	public static String strUsername = "";
	public static String strPassword = "";
	public static List<String> employees = null;
	public static List<String> sequences = null;
	public static List<String> positionCode = null;
	public  String dropSequence="";
	public static String searchSequencesResponse ="";
	public static String qaURL=null;
	public static String uatURL=null;
	public static String qaPilotAdminId= null;
	public static String qaPilotAdminPass= null;
	public static String qaItAdminId=null;
	public static String qaItAdminPass=null;
	public static String csURL=null;
	public static String stageItAdminId=null;
	public static String stageItAdminPass=null;
	public static String stagePilotAdminId=null;
	public static String qaCloudAdminId= null;
	public static String qaCloudAdminPass= null;
	public static String stagePilotAdminPass=null;
	public static String strGlobalAdd;
	public static String uatAdd;
	public static String stgAdd;
	public static String qaAdd;
	public static String qaCloudAdd;
	public static String stageCloudAdd;
	public static String empID="";
	public static String bidStatus="";
	
	public static Hashtable<String, List<String>> listOfEmpIds = new Hashtable<String, List<String>>();
	public static List<String> listOfBaseAirport;
	
	public static HashMap<String, List<String>> departrueStations = new HashMap<String, List<String>>();
	public static HashMap<String, List<String>> positions = new HashMap<String, List<String>>();
	public static HashMap<String, List<String>> equipments = new HashMap<String, List<String>>();
	
	public static List<String> listOfQuals = new ArrayList<String>(Arrays.asList("US", "GUC", "JAC", "MEX", "UIO", "MSO", "OAX", "XPL"));
	
	public TestData() {
		initializeTestDataFiles();
		//loadTestDataFromCSVFile();
		loadTestDataFromJSONFile();	
		TestData.departrueStations.put("DFW", Arrays.asList("DAL", "DFW"));
		TestData.departrueStations.put("ORD", Arrays.asList("MDW", "ORD"));
		TestData.departrueStations.put("PHX", Arrays.asList("PHX"));
		TestData.departrueStations.put("LAX", Arrays.asList("SAN", "ONT", "LGB", "SNA", "LAX"));
		TestData.departrueStations.put("DCA", Arrays.asList("DCA", "IAD", "BWI"));
		TestData.departrueStations.put("MIA", Arrays.asList("FLL", "PBI", "MIA"));
		TestData.departrueStations.put("PHL", Arrays.asList("PHL"));
		TestData.departrueStations.put("BOS", Arrays.asList("BOS"));
		TestData.departrueStations.put("LGA", Arrays.asList("EWR", "JFK", "LGA"));
		TestData.departrueStations.put("CLT", Arrays.asList("CLT"));
		
		TestData.positions.put("CA", Arrays.asList("CA", "RC"));
		TestData.positions.put("FO", Arrays.asList("FO", "FB", "FC"));
		
		TestData.equipments.put("S80", Arrays.asList("M83N", "SP80"));
		TestData.equipments.put("757", Arrays.asList("75WP", "75EL", "B75W", "75ER", "757P", "757X"));
		TestData.equipments.put("767", Arrays.asList("763P", "76RP"));
		TestData.equipments.put("737", Arrays.asList("738D", "738A", "738M", "738K", "738R"));
		TestData.equipments.put("320", Arrays.asList("321T", "319S", "321S", "321H", "H319", "319W", "A320", "H205", "A321", "321E", "321K", "321R"));
		TestData.equipments.put("777", Arrays.asList("773W", "B772"));
		TestData.equipments.put("330", Arrays.asList("A330", "A332"));
		TestData.equipments.put("787", Arrays.asList("7878", "B772", "7879"));
		TestData.equipments.put("190", Arrays.asList("E190"));
	}

	public static void saveEmployeeIDs(String[] employeeIDList) {
		employees = Arrays.asList(employeeIDList).stream().filter(e -> !e.equals("0")).collect(Collectors.toList());
		System.out.println("employees are: "+employees);
	}

	public static void saveSequenceNumbers(String[] sequenceNumbers) {
		 sequences = Arrays.asList(sequenceNumbers).stream().filter(e -> !e.equals("0")).collect(Collectors.toList());
		System.out.println("sequences are: "+sequences);

	}

	public static void savePositionCodes(String[] positionCodes) {
		positionCode = Arrays.asList(positionCodes).stream().filter(e -> !e.equals("0")).collect(Collectors.toList());
	}

	public void initializeTestDataFiles() {
		System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
		String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		relativePath = relativePath + Util.getFileSeparator() + "src"
				+ Util.getFileSeparator() + "test" + Util.getFileSeparator()
				+ "resources";
		
		jsonTestDataFile=relativePath+Util.getFileSeparator()+"testdata.json";
		
		csvTestDataFile=relativePath+Util.getFileSeparator()+"testdata.csv";
	}
	
	public void loadTestDataFromJSONFile() {
		loadTestDataFromJSONFile(jsonTestDataFile,"json");
	}

	@SuppressWarnings("unchecked")
	public static void loadTestDataFromJSONFile(String fileName,String strKey) {
		JSONParser jsonParser = new JSONParser();
		Object testDataObject = null;
	    try (FileReader reader = new FileReader(fileName))
        {
	    	
            //Read JSON file
            testDataObject = jsonParser.parse(reader);
            JSONArray testDataJsonArray = (JSONArray) testDataObject;
            testDataMap.put(strKey, testDataJsonArray);
            if(strKey.equalsIgnoreCase("json")){
            	testDataJsonArray.forEach( data -> parseTestDataObject( (JSONObject) data ) );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }		
	}

	/*Read test data row*/
	@SuppressWarnings({ "unchecked", "unused" })
	public static void parseTestDataObject(JSONObject testDataRow) {
    	
	    JSONObject testDataObject = (JSONObject) testDataRow.get("testdata");
	    HashMap<String, String> testCaseData = new HashMap<String, String>();
	    
	    presetEnv(DOTCCukeHooks.properties.getProperty("Environment"),testDataRow);
	    
	    
	    if(System.getenv("Environment")!=null) {
	    presetEnv(System.getenv("Environment"), testDataRow);
	    
	    }	    

		strGlobalAdd = (String) testDataObject.get("URL");
		qaCloudAdd = (String) testDataObject.get("qaCloudURL");
		stageCloudAdd = (String) testDataObject.get("stageCloudURL");
		qaAdd = (String) testDataObject.get("qaURL");
		uatAdd = (String) testDataObject.get("uatURL");
		stgAdd = (String) testDataObject.get("stageURL");
		strBase = (String) testDataObject.get("Base");
		strSource = (String) testDataObject.get("source");
	    strBasicAuth = (String) testDataObject.get("basicAuth");
	    strUsername = (String) testDataObject.get("userName");
	    strPassword = (String) testDataObject.get("password");
	    String userStory = (String) testDataObject.get("userStory");   
	    String testCaseId = (String) testDataObject.get("testCaseId");
	    
	    //csqaURL = (String) testDataObject.get("csqaURL");
	    qaPilotAdminId= (String) testDataObject.get("qaPilotAdminId");
	    qaPilotAdminPass= (String) testDataObject.get("qaPilotAdminPass");
	    qaItAdminId= (String) testDataObject.get("qaItAdminId");
	    qaItAdminPass= (String) testDataObject.get("qaItAdminPass");
	    stageItAdminId= (String) testDataObject.get("stageItAdminId");
	    stageItAdminPass= (String) testDataObject.get("stageItAdminPass");
	    stagePilotAdminId= (String) testDataObject.get("stagePilotAdminId");
	    stagePilotAdminPass= (String) testDataObject.get("stagePilotAdminPass");
	    qaCloudAdminId= (String) testDataObject.get("qaCloudItAdminId");
	    qaCloudAdminPass= (String) testDataObject.get("qaCloudItAdminPass");
	    /*testDataMap.put(userStory+"_"+testCaseId, testDataObject);
	    testDataMap.put(testCaseId, testDataObject);
        JSONArray testDataSetArray = (JSONArray) testDataObject.get("data");
	    testDataSetArray.forEach( data -> parseTestDataSetObject( (JSONObject) data, userStory, testCaseId ) );*/
    }
	
	public static void presetEnv(String env, JSONObject testDataRow) {
		try {
			JSONObject testDataObject = (JSONObject) testDataRow.get("testdata");
			switch (env.toUpperCase()) {

			case "QA":
				strGlobalURL = (String) testDataObject.get("qaURL");				
				csURL = (String) testDataObject.get("csqaURL");
				break;

			case "UAT":
				strGlobalURL = (String) testDataObject.get("uatURL");
				csURL = (String) testDataObject.get("csUatURL");
				break;

			case "STAGE":
				strGlobalURL = (String) testDataObject.get("stageURL");
				csURL = (String) testDataObject.get("csStageURL");
				break;
				
			case "DEV":
				strGlobalURL = (String) testDataObject.get("URL");
				break;
				
			case "QA CLOUD":
				strGlobalURL = (String) testDataObject.get("qaCloudURL");
				break;
				
			case "STAGE CLOUD":
				strGlobalURL = (String) testDataObject.get("stageCloudURL");
				csURL = (String) testDataObject.get("csStageURL");
				break;
				
			default:
				strGlobalURL = (String) testDataObject.get("URL");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/*Read test dataset for each test*/
    @SuppressWarnings("unused")
	public void parseTestDataSetObject(JSONObject testDataSet, String userStory, String testCaseId) {
	    
    	JSONObject testDataObject = (JSONObject) testDataSet.get("dataset");
	    HashMap<String, String> testCaseData = new HashMap<String, String>();
	    //String dataSetId = (String) testDataObject.get("id");   
	    testDataSetMap.put(testCaseId+"_", testDataObject);
    }
    
    /* This section of code brings in test data from csv file */
    public void loadTestDataFromCSVFile() {
    	loadTestDataFromCSVFile(csvTestDataFile);
    }    
    
    @SuppressWarnings("unchecked")
	public void loadTestDataFromCSVFile(String fileName) {
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
        String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		relativePath = relativePath + Util.getFileSeparator() + "src"
				+ Util.getFileSeparator() + "test" + Util.getFileSeparator()
				+ "resources";
		String testDataCSVFile = relativePath+Util.getFileSeparator()+"testdata.csv";
        try {

            br = new BufferedReader(new FileReader(testDataCSVFile));
            String[] columnHeaders=br.readLine().split(csvSplitBy);
            int columnCount=columnHeaders.length;
            JSONObject jsonTestData = new JSONObject(); 
            while ((line = br.readLine()) != null) {

            	String[] testData = line.split(csvSplitBy);
            	String mapKey=testData[0]+"_"+testData[1]+"_"+testData[2];
            	String mapKeyOnlyTestId=testData[1]+"_"+testData[2];
            	for(int i=0;i<columnCount;i++) {
            		jsonTestData.put(columnHeaders[i], testData[i]);	
            	}
            	
            	testDataSetMap.put(mapKey,jsonTestData);
            	testDataSetMap.put(mapKeyOnlyTestId,jsonTestData);
            	System.out.println("map: " +testDataSetMap);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* Get test data from default test data file */
    public Object getDataValue(String testCaseId, String dataSetId, String dataKey) {
    	JSONObject testDataRow=(JSONObject) testDataSetMap.get(testCaseId+"_"+dataSetId);
    	String dataValue=(String) testDataRow.get(dataKey);
    	return dataValue;    	
    }

    /* Get test data from custom data file*/
    public Object getDataValue(String fileName, String testCaseId, String dataSetId, String dataKey) {
    	String[] fileNameParts=fileName.split("\\.");
    	
    	String fileExtension=fileNameParts[fileNameParts.length-1];
    	if (fileExtension.equalsIgnoreCase("json")) {
    		loadTestDataFromJSONFile(fileName,"json");
    	} else if(fileExtension.equalsIgnoreCase("csv")) {
    		loadTestDataFromCSVFile(fileName);
    	} 
    	
    	JSONObject testDataRow=(JSONObject) testDataSetMap.get(testCaseId+"_"+dataSetId);
    	String dataValue=(String) testDataRow.get(dataKey);
    	return dataValue;    	
    }

 }

