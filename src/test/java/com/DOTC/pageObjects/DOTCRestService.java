package com.DOTC.pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.KeyStore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;

import com.DOTC.stepDefinitions.DOTCMasterSteps;
import com.DOTC.supportLibraries.ExtentTestManager;
import com.DOTC.supportLibraries.TestData;
import com.DOTC.supportLibraries.Util;
import com.google.common.io.CharStreams;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class DOTCRestService extends DOTCMasterSteps {	
	

	public static String wrapperBaseURL= "http://htappd01682.qcorpaa.aa.com:8090";
	public static String wrapperBaseURL_CCS = "https://aa-ch-test-atm-ccs.trafficmanager.net/ccsce";
	private final String ccsServiceBaseURL = "https://aaflight.qa.esoa.qcorpaa.aa.com/api/";

	//private final String ccsServiceBaseURLNew = "https://aa-oh-test-atm-ccs.trafficmanager.net/ccs/";

	private final String ccsServiceBaseURLNew = "https://aa-ch-xp-test-atm-ccs.trafficmanager.net/ccsxp";
	private static String tokenURL = "https://api.test.aa.com/edgemicro-auth/token";	
	private final String getCrewMember = "/CrewMemberService/getCrewMember";
	private final String getSequence = "/sequenceSearch";
	private final String getCrewMemberBallotsOn = "/PDOTC/updateBallotOnOffInd";
	private final String getBaseAirportList = "/CrewStaticDataService/getBaseAirportList";
	private final String getPickUpDOTC = "/getPickupDotcPilots";
	private final String getPickUpOutsideDOTC = "/getPickupOutsidePilots";
	public static final String searchSequencesForFA = "/CrewSequenceService/v4/searchSequencesForFA";
	public static final String srchSeqFAEndPoint = "/api/CrewSequenceService/v4/searchSequencesForFA";
	public static String strResponseIntext = "";
	private static String strModifiedJson = "";
	public static List<String> empIdFromService = null;
	public static List<String> bidStatusOfEmpIdFromService = new ArrayList<String>();
	public static List<String> sequencesFromService = null;
	public static String contractMonth = "";
	public static String currentContractMonth;
	public static String contractMonthEndDate;
	public static String nxtCntrctMnth;
	public static String nxtCntrctMonthStartDate;
	public static String nxtCntrctMonthEndDate;
	public static List<String> rsvempIdFromService = null;
	public static List<String> rsvForRAP = new ArrayList<String>();
	public static List<String> rsvBidcard = new ArrayList<String>();
	public static String SMSESSION = null;
	

	public static String base;    

	String employeeID;

		
	public String initializeTestDataFiles(String jsonFileName) {
		String strFileContent = "";
		System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "");
		try {
			String relativePath = new File(System.getProperty("user.dir")).getAbsolutePath();
			relativePath = relativePath + Util.getFileSeparator() + "src" + Util.getFileSeparator() + "test"
					+ Util.getFileSeparator() + "resources" + Util.getFileSeparator() + "WS";

			String jsonTestDataFile = relativePath + Util.getFileSeparator() + jsonFileName;
			strFileContent = readFile(jsonTestDataFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strFileContent;
		// strModifiedJson = modifyJson(strFileContent, strFileContent, strFileContent);
		// String strUser =
		// TTSRestServices.readJson("$..testdata.data..Test_Mileage_Account.User",
		// strFileContent);

	}

	private String readFile(String pathname) {

		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int) file.length());

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileContents.toString();
	}

	public String modifyJson(String jsonInput, String jayPath, String tagValue) {
		String strModifiedJson = "";
		try {
			DocumentContext json = JsonPath.parse(jsonInput);
			strModifiedJson = json.set(jayPath, tagValue).jsonString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strModifiedJson;

	}

	public void sendSoapReq() {

		try {
			FileInputStream fileInputStream = new FileInputStream(new File(".\\SOAPRequest\\SoapRequestFile.xml"));
			RestAssured.baseURI = "http://currencyconverter.kowabunga.net";

			Response response = RestAssured.given().header("Content-Type", "text/xml").and()
					.body(IOUtils.toString(fileInputStream, "UTF-8")).when().post("/converter.asmx").then()
					.statusCode(200).and().log().all().extract().response();

			XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
			String rate = jsXpath.getString("GetConversionRateResult");
			System.out.println("rate returned is: " + rate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	 public String modifyXml(String xmlInput, String nodeValue) {
	        String strModifiedXml = "";
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = null;
	        try {

	            docBuilder = docFactory.newDocumentBuilder();
	            Document doc = docBuilder.parse(new InputSource(new StringReader(xmlInput)));

	            NodeList list = doc.getElementsByTagName("Entry");
	            Node entry = list.item(0);
	            entry.setTextContent(nodeValue);
	            strModifiedXml = DomToString(doc);
	            //System.out.println(strModifiedXml);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return strModifiedXml;
	    }
	 
	 
	    public static String DomToString(Document doc) {
	        try {
	            StringWriter sw = new StringWriter();
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer transformer = tf.newTransformer();
	            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	            transformer.transform(new DOMSource(doc), new StreamResult(sw));
	            return sw.toString();
	        } catch (Exception ex) {
	            throw new RuntimeException("Error converting to String", ex);
	        }
	    }
	    
	    public String postFOSrequest(String payload) {
	        String reply = "";
	        try {
	        	KeyStore keyStore = null;
                SSLConfig config = null;
                keyStore = KeyStore.getInstance("PKCS12");
                String password = "RBER*OQQDWM7H";
                keyStore.load(new FileInputStream("src/test/resources/certificate/S1087672.pfx"), password.toCharArray());

                if (keyStore != null) {
                    org.apache.http.conn.ssl.SSLSocketFactory clientAuthFactory = new org.apache.http.conn.ssl.SSLSocketFactory(
                            keyStore, password);
                    // set the config in rest assured
                    config = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
            }

                RestAssured.config = RestAssured.config.sslConfig(config);
	            Response response = RestAssured.given()
	                    .baseUri("https://aacloud.qa.esoa.qcorpaa.aa.com/GenericFosRequestService/V2").body(payload)
	                    .header("Content-Type", "application/xml").post();

	            XmlPath jsXpath = new XmlPath(response.asString());// Converting
	            
	            reply = jsXpath.getString("Reply");
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return reply;

	    }
	    
	/**
	 * Function for getting CrewMember from CCS Services
	 */
	public String postESOAService(String strEndPoint, String strJSON) {
		String strResponse = "";
		try {

			List<Header> headerlist = new ArrayList<Header>();
			headerlist.add(new Header("Authorization", TestData.strBasicAuth));
			headerlist.add(new Header("Content-Type", "application/json"));
			headerlist.add(new Header("source", TestData.strSource));
			// headerlist.add(new Header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64;
			// x64)"));
			Headers headers = new Headers(headerlist);
			Response response = RestAssured.given().baseUri("https://aaflight.qa.esoa.qcorpaa.aa.com/api/").body(strJSON).basePath(strEndPoint)
					.headers(headers).post();

			InputStream strm = response.getBody().asInputStream();
			Reader reader = new InputStreamReader(strm);
			strResponse = CharStreams.toString(reader);
			// System.out.println(strResponseIntext);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strResponse;
	}


	public String getToken(String env) {
		String strResponse = "";
		String token = "";
		String auth = "";
		tokenURL = "https://api.test.aa.com/edgemicro-auth/token";
		if(env.equals("")) {
			auth = "MFlkSE9HQWR2RjJLcTcxS2tkRldaR0FEWGFLY0d6WHk6cUVSNE1sQ0NpTndka2Q3OA==";
		}
		else if(env.equals("qa")) {
			auth = "bUdFWFk1MnVYU0ZXMzNjVlM3bFRIcjdOZ0lVZUFVUUs6ejNOdFFsWThyWjByV2RHRA==";
		}
		else if(env.equals("stage")) {
			auth = "QjZmNEtHUURkUkRTM09Pa0xYeUdNQWxTSUJNM3paNmI6blhiMmdKVkR2S1VScUh5dg==";
			tokenURL = "https://api.stage.aa.com/edgemicro-auth/token";
		}
		try {

			List<Header> headerlist = new ArrayList<Header>();
			headerlist.add(new Header("Content-Type", "application/x-www-form-urlencoded"));
			headerlist.add(new Header("source", TestData.strSource));
			headerlist.add(new Header("Authorization",
					"Basic "+auth));
			Map<String, String> reqObject = new HashMap<>();

			// headerlist.add(new Header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64;
			// x64)"));
			Headers headers = new Headers(headerlist);
			Response response = RestAssured.given().baseUri(tokenURL).formParam("grant_type", "client_credentials")
					.headers(headers).post();
			
			String responseCode = String.valueOf(response.getStatusCode());
			if(!responseCode.equals("200"))
				System.out.println(tokenURL+" Response Code: "+responseCode);
			
			InputStream strm = response.getBody().asInputStream();
			Reader reader = new InputStreamReader(strm);
			strResponse = CharStreams.toString(reader);
			token = readJson("$.token", strResponse);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return token;
	}
	
	/**
	 * Function for Language API
	 */
//	public void getCrewMember(String strJson) {
//		try {			
//			strResponseIntext = ccsService(strJson,searchSequencesForFA);			
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	
	public static void setWrapperURL() {
		if (TestData.strGlobalURL.contains("stg")) {
			wrapperBaseURL = "https://pilot-dotc.stg.aa.com/PDOTC";
		} else if (TestData.strGlobalURL.contains("qa")) {
			wrapperBaseURL = "https://pilot-dotc.qa.qcorpaa.aa.com/PDOTC";
		} else if (TestData.strGlobalURL.equals("https://pilot-dotc-nextgen-test.aa.com")) {
			wrapperBaseURL = "https://pcs-dotcras-webapp-test.cloud.aa.com/DOTCCS";
		} else if (TestData.strGlobalURL.contains("stage.aa.com")) {
			wrapperBaseURL = "https://pcs-dotcras-webapp-stage.cloud.aa.com/DOTCCS";
		}
	}

	/**
	 * Function to get the sequence from the service
	 * 
	 * @param jsonQuery
	 * @param strResponseText
	 * @return
	 */

	public String getSequence(String strJson) {
		String strResponse = "";
		KeyStore keyStore = null;
		SSLConfig configs = null;
		Response response=null;
		try {

			setWrapperURL();
			List<Header> headerlist = new ArrayList<Header>();
			DOTCLogInScreen lis = new DOTCLogInScreen();
			if(!wrapperBaseURL.contains("cloud.aa.com/DOTCCS")){
				if(!wrapperBaseURL.equals("http://htappd01682.qcorpaa.aa.com:8090")) {
					lis.getCookieData();
					headerlist.add(new Header("Cookie", "SMSESSION="+SMSESSION));
				}
			headerlist.add(new Header("Content-Type", "application/json"));
			headerlist.add(new Header("source", TestData.strSource));
			
			Headers headers = new Headers(headerlist);
			
			configs = new SSLConfig().relaxedHTTPSValidation();
			RestAssured.config = RestAssured.config().sslConfig(configs);
			//long start = System.currentTimeMillis();
			response = RestAssured.given().baseUri(wrapperBaseURL).body(strJson).basePath(getSequence)
					.headers(headers).post();
			//long end = System.currentTimeMillis();
			//System.out.println("Time taken by serivce "+wrapperBaseURL+getSequence+" is: "+(end-start)+" millisecond");
			}
			else {			
				String token = "";
				if(wrapperBaseURL.contains("stage"))
					token = getToken("stage");
				else 
					token = getToken("qa");
				
				List<Header> headerList = new ArrayList<Header>();
				headerList.add(new Header("Authorization", "Bearer "+token));
				headerList.add(new Header("Content-Type", "application/json"));
				headerList.add(new Header("source", TestData.strSource));
				Headers headers = new Headers(headerList);
				
				keyStore = KeyStore.getInstance("PKCS12");
				String password = "RBER*OQQDWM7H";
				keyStore.load(new FileInputStream("src/test/resources/certificate/T1033572.pfx"), password.toCharArray());
				
				if(keyStore != null) {
					org.apache.http.conn.ssl.SSLSocketFactory clientAuthFactory = new org.apache.http.conn.ssl.SSLSocketFactory(keyStore, password);
					configs = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
				}
				//long start = System.currentTimeMillis();
				try {
					RestAssured.config = RestAssured.config().sslConfig(configs);
					response = RestAssured.given().proxy("http://inetgw.aa.com:9091/").baseUri(wrapperBaseURL)
							.body(strJson).basePath(getSequence).headers(headers).post();
				} catch (Exception ex) {
					response = RestAssured.given().baseUri(wrapperBaseURL).body(strJson).basePath(getSequence).headers(headers).post();
				}				
				//long end = System.currentTimeMillis();
				//System.out.println("Time taken by serivce "+wrapperBaseURL+getSequence+" is: "+(end-start)+" millisecond");
			}

			InputStream strm = response.getBody().asInputStream();
			Reader reader = new InputStreamReader(strm);
			strResponse = CharStreams.toString(reader);
			if(response.getStatusCode() != 200)
				System.out.println(wrapperBaseURL+getSequence+" : "+response.getStatusCode());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strResponse;

	}

	/**
	 * Function to read Json response text based on json Query
	 * 
	 * @param jsonQuery
	 * @param strResponseText
	 * @return
	 */
	public static String readJson(String jsonQuery, String strResponseText) {
		String jsonVal = "1";
		try {

			String readFile = com.jayway.jsonpath.JsonPath.parse(strResponseText).read(jsonQuery).toString();
			if (readFile != null) {
				jsonVal = readFile.replace("[", "").replace("]", "").replace("\"", "").replace("\\", "").trim();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"Something went wrong in readJson(String jsonQuery,String strResponseText) function in CMEAppServices.java. Message: "
							+ ex.getMessage());
		}
		return jsonVal;
	}

	/**
	 * identifying the last flight based on sorting of estDepTime epoch time
	 * 
	 * @param map
	 * @return
	 */
	public static String sortbykey(HashMap<String, String> map) {

		String lastKey = "";
		try {

			// TreeMap to store values of HashMap
			TreeMap<String, String> sorted = new TreeMap<>(map);

			// Display the TreeMap which is naturally sorted
			for (@SuppressWarnings("unused")
			Map.Entry<String, String> entry : sorted.entrySet())
				;

			for (Map.Entry<String, String> entry : sorted.entrySet()) {
				lastKey = entry.getKey();
			}
		} catch (Exception ex) {

		}
		return lastKey;
	}


	public void getLHids(String base) throws InterruptedException, ParseException {
		String employeeList = "";
		String query = "{\"airlineCode\":\"AA\",\"base\":\"%s\",\r\n"
				+ "\"contractMonth\":\"%s\",\"crewType\":\"P\",\"selectionType\":\"REGL\",\r\n"
				+ "\"gets\":[\"BID_STATUS\"]}";
		Map<String, String> CMD = contractMonthDetails();
		String cm = CMD.get("currentCntrctMnth");
		String lhs = ccsService(String.format(query, base, cm), "/CrewMemberService/getCrewMembersByBase");
    
		if(!lhs.equals("")) {
			employeeList = DOTCRestService.readJson("$[?(@.bidStatuses[0].currentBase=='"+base+"')].employeeID", lhs);
			TestData.listOfEmpIds.put(base, Arrays.asList(employeeList.split(",")).subList(0, 10));
		}
	}
	
//Returns the emp ID of the employee matching the equipment type
	public Integer getLHid(String base, String eqp) throws ParseException {
		String query = "{\"airlineCode\":\"AA\",\"base\":\"%s\",\r\n"
				+ "\"contractMonth\":\"%s\",\"crewType\":\"P\",\"selectionType\":\"REGL\",\r\n"
				+ "\"gets\":[\"BID_STATUS\"]}";
		Map<String, String> CMD = contractMonthDetails();
		String cm = CMD.get("currentCntrctMnth");
		String lhs = ccsService(String.format(query, base, cm), "CrewMemberService/getCrewMembersByBase");
		System.out.println(lhs);
		String empKey = "\"employeeID\":";
		String eq= String.format("\"currentEquipment\":\"%s\"", eqp);
		if (lhs.length() < 1)
			return null;
		int st = lhs.indexOf(eq , 0);
		if(st<0)
			return null;
		lhs=lhs.substring(Math.max(0, st-300), st);
		st = lhs.indexOf(empKey, 0);;
		return Integer.parseInt(lhs.substring(st + empKey.length(), lhs.indexOf(",", st)));
	}
	//Returns the emp ID of the employee matching a specific 4 Part Bid Status:Base,Equipment,Position,Division
		public Integer getLHid(String base, String eqp,String pos, String div) throws ParseException {
			String query = "{\"airlineCode\":\"AA\",\"base\":\"%s\",\r\n"
					+ "\"contractMonth\":\"%s\",\"crewType\":\"P\",\"selectionType\":\"REGL\",\r\n"
					+ "\"gets\":[\"BID_STATUS\"]}";
			
			Map<String, String> CMD = contractMonthDetails();
			String cm = CMD.get("currentCntrctMnth");
			String lhs = ccsService(String.format(query, base, cm), "CrewMemberService/getCrewMembersByBase");
			//System.out.println(lhs);
			String empKey = "\"employeeID\":";
			String eq= String.format("\"currentDivision\":\"%s\",\"currentEquipment\":\"%s\",\"currentPosition\":\"%s\",\"currentBase\":\"%s\"",div,eqp,pos,base);
			System.out.println(eq);
			if (lhs.length() < 1)
				return null;
			int st = lhs.indexOf(eq , 0);
			if(st<0)
				return null;
			lhs=lhs.substring(Math.max(0, st-300), st);
			st = lhs.indexOf(empKey, 0);;
			return Integer.parseInt(lhs.substring(st + empKey.length(), lhs.indexOf(",", st)));
		}
	
	public ArrayList<String> nonAbsntAndSameBid(String base, int howmany) throws ParseException {
		ArrayList<String> people = new ArrayList<String>();
		String query = "{\"airlineCode\":\"AA\",\"base\":\"%s\",\r\n"
				+ "\"contractMonth\":\"%s\",\"crewType\":\"P\",\"selectionType\":\"REGL\",\r\n"
				+ "\"gets\":[\"BID_STATUS\"]}";
		
		Map<String, String> CMD = contractMonthDetails();
		String cm = CMD.get("currentCntrctMnth");
		String lhs = ccsService(String.format(query, base, cm), "CrewMemberService/getCrewMembersByBase");
		String employeeList = DOTCRestService.readJson(DOTCJsonXmlPath.EmployeeID, lhs);
		saveEmpID(employeeList.split("\\,"));
		int totalpilots = empIdFromService.size();
		int pp = 0;
		String bidstatus = null;
		for (int i = 0; i < totalpilots && pp < 2; i++) { // i2
			// wrapperbase url
			String payload = "{\"employeeNumber\": " + empIdFromService.get(i) + "}";
			String jsonresponse = getCrewmember(payload);
			System.out.println("wrapper base response" + jsonresponse);
			bidstatus = DOTCRestService.readJson("$..myInfo[0].bidStatus", jsonresponse);
			
			if (bidstatus.contains("DFW/777/CA/I")) {
				String calendarEvents = DOTCRestService.readJson("$..calendarEvents", jsonresponse);
				if(!calendarEvents.contains("2020-05-26"))
				people.add(empIdFromService.get(i));
				pp++;
			}
		}

		System.out.println("the people is: " + people);
		return people;
	}

	private String bidOf(String strResponseIntext2) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean notAbs4todayAndTomCrew(String strResponseIntext2) {
		// TODO Auto-generated method stub
		return false;
	}


	// updated getCrewMember URL
	public String getCrewmember(String strJson) {
		String strResponse = "";
		Response response = null;
		KeyStore keyStore = null;
		SSLConfig configs = null;
		
		try {
			String token = getToken("");
			List<Header> headerList = new ArrayList<Header>();
			headerList.add(new Header("Authorization", "Bearer "+token));
			headerList.add(new Header("Content-Type", "application/json"));
			headerList.add(new Header("source", TestData.strSource));
			Headers headers = new Headers(headerList);
			
			keyStore = KeyStore.getInstance("PKCS12");
			String password = "RBER*OQQDWM7H";
			keyStore.load(new FileInputStream("src/test/resources/certificate/T1087672.pfx"), password.toCharArray());
			
			if(keyStore != null) {
				org.apache.http.conn.ssl.SSLSocketFactory clientAuthFactory = new org.apache.http.conn.ssl.SSLSocketFactory(keyStore, password);
				configs = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
			}
			//long start = System.currentTimeMillis();
			try {
				RestAssured.config = RestAssured.config().sslConfig(configs);
				response = RestAssured.given().proxy("http://inetgw.aa.com:9091/").baseUri(ccsServiceBaseURLNew)
						.body(strJson).basePath(getCrewMember).headers(headers).post();
			} catch (Exception ex) {
				response = RestAssured.given().baseUri(ccsServiceBaseURLNew).body(strJson).basePath(getCrewMember)
						.headers(headers).post();
			}
			//long end = System.currentTimeMillis();
			//System.out.println("Time taken by service "+ccsServiceBaseURLNew+getCrewMember+" is: "+(end-start)+" milli second");
			InputStream strm = response.getBody().asInputStream();
			Reader reader = new InputStreamReader(strm);
			strResponse = CharStreams.toString(reader);
			strResponseIntext = strResponse;
			String responseCode = String.valueOf(response.getStatusCode());
			if(!responseCode.equals("200"))
				System.out.println(ccsServiceBaseURLNew+getCrewMember+" Response Code: "+responseCode);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return strResponseIntext;
		
		/*String strResponse = "";		
		SSLConfig configs = null;
		Response response=null;
		try {
			

			setWrapperURL();
			List<Header> headerlist = new ArrayList<Header>();
			DOTCLogInScreen lis = new DOTCLogInScreen();
			//if(!wrapperBaseURL.equals("http://htappd01682.qcorpaa.aa.com:8090")) {
			//lis.getCookieData();
			//System.out.println(wrapperBaseURL);
			//headerlist.add(new Header("Cookie", "SMSESSION="+SMSESSION));}
			headerlist.add(new Header("Content-Type", "application/json"));
			headerlist.add(new Header("source", TestData.strSource));
			
			
			Headers headers = new Headers(headerlist);
						
			configs = new SSLConfig().relaxedHTTPSValidation();
			
			RestAssured.config = RestAssured.config().sslConfig(configs);
			 response = RestAssured.given().baseUri(wrapperBaseURL_CCS).body(strJson).basePath(getCrewMember)
					.headers(headers).post();
           
			InputStream strm = response.getBody().asInputStream();
			Reader reader = new InputStreamReader(strm);
			strResponse = CharStreams.toString(reader);
			
			if(response.getStatusCode() != 200)
				System.out.println(wrapperBaseURL_CCS+getCrewMember+" : "+response.getStatusCode());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strResponse;*/

	}

	public String getCrewmemberDOTCOn(String strJson) {
		String strResponse = "";
		try {
			List<Header> headerlist = new ArrayList<Header>();
			headerlist.add(new Header("Content-Type", "application/json"));
			headerlist.add(new Header("source", TestData.strSource));
			Headers headers = new Headers(headerlist);
			Response response = RestAssured.given().baseUri(wrapperBaseURL).body(strJson)
					.basePath(getCrewMemberBallotsOn).headers(headers).post();

			InputStream strm = response.getBody().asInputStream();
			Reader reader = new InputStreamReader(strm);
			strResponse = CharStreams.toString(reader);
			// strResponseIntext = CharStreams.toString(reader);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strResponse;

	}

	// This method is used to save the sequence from the service
	public static void saveEmpID(String[] empId) {
		empIdFromService = Arrays.asList(empId).stream().filter(e -> !e.equals("0")).collect(Collectors.toList());
		String tmp=empIdFromService.toString();
		if(tmp.length()>6)
			System.out.println(String.format("Employee id from service are: %s ..." , tmp.substring(0, Math.min(tmp.length(), 100))));
	}
	
	public ArrayList<String> getLHSameBidStatus(String base) throws ParseException {
		ArrayList<String> people = new ArrayList<String>();
				
		try {
			getLHidswithNoActivity(base);
			System.out.println("No activity emp ids: "+rsvForRAP);
			System.out.println("No activity bitStatus: "+rsvBidcard);
			people.add(rsvForRAP.get(0));
			System.out.println(people);
			String query = "{\"airlineCode\":\"AA\",\"base\":\"%s\",\r\n"
				+ "\"contractMonth\":\"%s\",\"crewType\":\"P\",\"selectionType\":\"REGL\",\r\n"
				+ "\"gets\":[\"BID_STATUS\"]}";
		
			Map<String, String> CMD = contractMonthDetails();
			String cm = CMD.get("currentCntrctMnth");
			String lhs = ccsService(String.format(query, base, cm), "CrewMemberService/getCrewMembersByBase");
			//String lhs = ccsService(String.format(query, base, cm), "CrewMemberService/getCrewMembersByBase");
			String employeeList = DOTCRestService.readJson("$..employeeID", lhs);
			saveEmpID(employeeList.split("\\,"));
			int totalpilots = empIdFromService.size();
			String bd = null;
			ArrayList<String> bidStatus = new ArrayList<String>();
			for (int i = 0; i < totalpilots; i++) { 
				String payload = "{\r\n"
					+ "    \"contractMonths\": [\r\n"
					+ "                \""+cm+"\"\r\n"
					+ "    ],\r\n"
					+ "    \"crewMemberKeyDTO\": {\r\n"
					+ "        \"airlineCode\": \"AA\",\r\n"
					+ "        \"employeeNumber\":"+empIdFromService.get(i)+"\r\n"
					+ "    }\r\n"
					+ "}";
				String jsonresponse = getCrewmember(payload);
				String div = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentDivision", jsonresponse);
				String eqp = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentEquipment", jsonresponse);
				String pos = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentPosition", jsonresponse);
				String ba = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentBase", jsonresponse);
				bd = ba+"/"+eqp+"/"+pos+"/"+div;
			
				bidStatus.add(bd);
				if(bd.contains(rsvBidcard.get(0)) && !people.contains(empIdFromService.get(i))) {
					people.add(empIdFromService.get(i));
					break;
				}
			}	
			System.out.println("valid LH with same 4 part BidStatus: " + people);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return people;
	}
	
//	public Boolean SameBidStatusLH(String BidStatus) {
//		ArrayList<String> people = new ArrayList<String>();
//		String bd = null;
//		int totalpilots = empIdFromService.size();
//		for (int i = 0; i < totalpilots; i++) {
//			ArrayList<String> bid = new ArrayList<String>();
//			String payload = "{\"employeeNumber\": " + empIdFromService.get(i) + "}";
//			String jsonresponse = getCrewmember(payload);
//			bd = DOTCRestService.readJson("$..myInfo[0].bidStatus", jsonresponse);
//			bid.add(empIdFromService.get(i));
//			if(bd == bid.get(0)) {
//			people.add(empIdFromService.get(i));
//			}
//		}
//		return null;
//	}


	public ArrayList<String> getLHNoCalEvents(String base, int howmany) throws ParseException {
		ArrayList<String> people = new ArrayList<String>();
		String query = "{\"airlineCode\":\"AA\",\"base\":\"%s\",\r\n"
				+ "\"contractMonth\":\"%s\",\"crewType\":\"P\",\"selectionType\":\"REGL\",\r\n"
				+ "\"gets\":[\"BID_STATUS\"]}";

		Map<String, String> CMD = contractMonthDetails();
		String cm = CMD.get("currentCntrctMnth");
		String lhs = ccsService(String.format(query, base, cm), "CrewMemberService/getCrewMembersByBase");
		String employeeList = DOTCRestService.readJson("$..employeeID", lhs);
		saveEmpID(employeeList.split("\\,"));
		int totalpilots = empIdFromService.size();
		int pp = 0;
		String bd = null;
		ArrayList<String> bidStatus = new ArrayList<String>();
		for (int i = 0; i < totalpilots && pp < 2; i++) {
			String payload = "{\"employeeNumber\": " + empIdFromService.get(i) + "}";
			String jsonresponse = getCrewmember(payload);
			System.out.println("WrapperURL response: "+jsonresponse);
			bd = DOTCRestService.readJson("$..myInfo[0].bidStatus", jsonresponse);
			System.out.println("Bid Status is: "+ bd);
			bidStatus.add(bd);
			if(bd.equalsIgnoreCase(bidStatus.get(0))) {
			String calEvents = DOTCRestService.readJson("$...start", jsonresponse);
			System.out.println("ffff"+ calEvents);
//			String calEventsssss = DOTCRestService.readJson("$.start", calEvents);
//			ArrayList<String> startDate = new ArrayList<String>();
//			ArrayList<String> endDate = new ArrayList<String>();
//			System.out.println("dddddddddd"+ calEventsssss);
			pp++;
		}
		}
		System.out.println("valid LH with same 4 part BidStatus: " + people);
		return people;
	}
		
		
		
	// This method is used to save the sequence from the service
	public static void seqNumber(String[] sequenceNumbers) {
		sequencesFromService = Arrays.asList(sequenceNumbers).stream().filter(e -> !e.equals("0"))
				.collect(Collectors.toList());
		System.out.println("sequences from service are: " + sequencesFromService);

	}
	
	// added code
	// 07-03-2022
	// this method is use to return all the employee IDs with bidstatus using various bid status
	public void getLHDifferentBidStatus(String base, int howmany) throws ParseException {
		ArrayList<String> people = new ArrayList<String>();
		String query = "{\"airlineCode\":\"AA\",\"base\":\"%s\",\r\n"
				+ "\"contractMonth\":\"%s\",\"crewType\":\"P\",\"selectionType\":\"REGL\",\r\n"
				+ "\"gets\":[\"BID_STATUS\"]}";
		
		Map<String, String> CMD = contractMonthDetails();
		String cm = CMD.get("currentCntrctMnth");
		String lhs = ccsService(String.format(query, base, cm), "/CrewMemberService/getCrewMembersByBase");
		String employeeList = DOTCRestService.readJson("$..employeeID", lhs);
		saveEmpID(employeeList.split("\\,"));
		int pp = 0;
		String bd = null;
		ArrayList<String> bidStatus = new ArrayList<String>();
		for (int i = 0; i < empIdFromService.size(); i++) { // i2
			if(pp == howmany) {
				empIdFromService = new ArrayList<String> (empIdFromService.subList(0, i));
				break;
			}
			
			// wrapperbase url
			String payload = "{\r\n"
					+ "    \"contractMonths\": [\r\n"
					+ "                \""+cm+"\"\r\n"
					+ "    ],\r\n"
					+ "    \"crewMemberKeyDTO\": {\r\n"
					+ "        \"airlineCode\": \"AA\",\r\n"
					+ "        \"employeeNumber\":"+empIdFromService.get(i)+"\r\n"
					+ "    }\r\n"
					+ "}";
			//System.out.println(payload);
			String jsonresponse = getCrewmember(payload);
			String div = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentDivision", jsonresponse);
			String eqp = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentEquipment", jsonresponse);
			String pos = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentPosition", jsonresponse);
			String ba = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentBase", jsonresponse);
			String bidstatus = ba+"/"+eqp+"/"+pos+"/"+div;
			
			if(!bidstatus.contains(base) || bidStatusOfEmpIdFromService.contains(bidstatus)) {
				empIdFromService.remove(empIdFromService.get(i));
				i--;
				continue;
			}
			bidStatusOfEmpIdFromService.add(bidstatus);
			pp++;
		}
	}
	
	//09-03-2022
	//created new function to access CCS services as new certificate need to be use for the response
	public String ccsService(String strJSON, String service) {
		String strResponse = "";
		Response response = null;
		KeyStore keyStore = null;
		SSLConfig configs = null;
		
		try {
			String token = getToken("");
			List<Header> headerList = new ArrayList<Header>();
			headerList.add(new Header("Authorization", "Bearer "+token));
			headerList.add(new Header("Content-Type", "application/json"));
			headerList.add(new Header("source", TestData.strSource));
			Headers headers = new Headers(headerList);
			
			keyStore = KeyStore.getInstance("PKCS12");
			String password = "RBER*OQQDWM7H";
			keyStore.load(new FileInputStream("src/test/resources/certificate/T1087672.pfx"), password.toCharArray());
			
			if(keyStore != null) {
				org.apache.http.conn.ssl.SSLSocketFactory clientAuthFactory = new org.apache.http.conn.ssl.SSLSocketFactory(keyStore, password);
				configs = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
			}
			//long start = System.currentTimeMillis();
			try {
				
				RestAssured.config = RestAssured.config().sslConfig(configs);
				response = RestAssured.given().proxy("http://inetgw.aa.com:9091/").baseUri(ccsServiceBaseURLNew)
						.body(strJSON).basePath(service).headers(headers).post();
			} catch (Exception ex) {
				response = RestAssured.given().baseUri(ccsServiceBaseURLNew).body(strJSON).basePath(service)
						.headers(headers).post();
			}
			//long end = System.currentTimeMillis();
			//System.out.println("Time take for servie "+ccsServiceBaseURLNew+service+" is: "+(end-start)+" millisecond");
			
			InputStream strm = response.getBody().asInputStream();
			Reader reader = new InputStreamReader(strm);
			strResponse = CharStreams.toString(reader);
			strResponseIntext = strResponse;
			String responseCode = String.valueOf(response.getStatusCode());
			if(!responseCode.equals("200"))
				System.out.println(ccsServiceBaseURLNew+service+" Response Code: "+responseCode);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return strResponseIntext;
	}
		
	//method to return base initialized status for next contract month
	public String checkBaseInitialized(String base) throws ParseException {
				
		String query = "{\r\n" + 
				"  \"airlineCode\": \"AA\",\r\n" + 
				"  \"airportCode\": \"%s\",\r\n" + 
				"  \"contractMonth\": \"%s\",\r\n" + 
				"  \"crewType\": \"P\"\r\n" + 
				"}";
		
		Month curmonth = Util.getCurrentMonth();
		Month nextMonth = curmonth.plus(1);
		Integer currentYear = Util.getCurrentYear();
		String strNextMonth = nextMonth.toString().substring(0, 3) + currentYear.toString();
		
		String airportInfo = ccsService(String.format(query, base, strNextMonth), "/CrewStaticDataService/getBaseAirportInfo");
		String baseInitialized = readJson("$..baseInitializationStatus", airportInfo);
		System.out.println(baseInitialized);
		return baseInitialized;
		
	}
	
	//method to return current and next contract month info
	public Map<String, String> contractMonthDetails() throws ParseException {
		
		Map<String, String> CMObj = new HashMap<>();
		String TwelveContractMonth = ccsService("{\"airlineCode\":\"AA\"}","/CrewStaticDataService/getTwelveContractMonths");		
		currentContractMonth = readJson("$..[?(@.type=='C')].contractMonth", TwelveContractMonth);
		contractMonthEndDate = readJson("$..[?(@.type=='C')].endDate", TwelveContractMonth);
		nxtCntrctMnth = readJson("$..[?(@.type=='O')].contractMonth", TwelveContractMonth);
		nxtCntrctMonthStartDate = readJson("$..[?(@.type=='O')].startDate", TwelveContractMonth);
		nxtCntrctMonthEndDate = readJson("$..[?(@.type=='O')].endDate", TwelveContractMonth);

		CMObj.put("currentCntrctMnth", currentContractMonth);
		CMObj.put("cntrctMnthEndDate", contractMonthEndDate);
		CMObj.put("nxtCntrctMnth", nxtCntrctMnth);
		CMObj.put("nxtCntrctMnthStartDate", nxtCntrctMonthStartDate);
		CMObj.put("nxtCntrctMnthEndDate", nxtCntrctMonthEndDate);

		return CMObj;
	}
	
	//TO  get reserve pilot Id's with no activity on current day
	//28-03-22
	public void  getRESVidswithNoActivity(String base) throws InterruptedException, ParseException {
		try {
				
				String query = "{\"airlineCode\":\"AA\",\"base\":\"%s\",\r\n"
						+ "\"contractMonth\":\"%s\",\"crewType\":\"P\",\"selectionType\":\"RESV\",\r\n"
						+ "\"gets\":[\"BID_STATUS\"]}";
				
				Map<String, String> CMD = contractMonthDetails();
				String cm = CMD.get("currentCntrctMnth");
				String lhs = ccsService(String.format(query, base, cm), "/CrewMemberService/getCrewMembersByBase");
				String employeeList = DOTCRestService.readJson(DOTCJsonXmlPath.EmployeeID, lhs);
				String[] r = null;
				r= employeeList.split("\\,");
				saveResvEmpID(r);
				//String currentDate = Util.getCurrentFormattedTime("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// fetch current date
				String currentDate = Util.getCurrentFormattedTime("yyyy-MM-dd")+"T00:00";
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(currentDate));
				
				//fetch tomorrow date
				calendar.add(Calendar.DATE, 1);
				String tomorrowDate = sdf.format(calendar.getTime())+"T00:00";
		
				//fetch current date +2
				calendar.add(Calendar.DATE, 2);
				String nextDate = sdf.format(calendar.getTime())+"T00:00";
				rsvForRAP.clear();
				rsvBidcard.clear();
				int n = 0;
				int totalpilots = rsvempIdFromService.size();
				for (int i = 0; i < totalpilots/2 && n<=4 ; i++) { // i2
					// wrapperbase url
					String payload = "{\r\n"
							+ "    \"contractMonths\": [\r\n"
							+ "                \""+cm+"\"\r\n"
							+ "    ],\r\n"
							+ "    \"crewMemberKeyDTO\": {\r\n"
							+ "        \"airlineCode\": \"AA\",\r\n"
							+ "        \"employeeNumber\":"+rsvempIdFromService.get(i)+"\r\n"
							+ "    }\r\n"
							+ "}";
					String jsonresponse = getCrewmember(payload);
					//String bidStatus = DOTCRestService.readJson("$..myInfo[?(@.contractMonthType==\"C\")].bidStatus", jsonresponse);
					String div = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentDivision", jsonresponse);
					String eqp = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentEquipment", jsonresponse);
					String pos = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentPosition", jsonresponse);
					String ba = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentBase", jsonresponse);
					String bidStatus = ba+"/"+eqp+"/"+pos+"/"+div;
					/*Date calendarEventsStart = sdf.parse(DOTCRestService.readJson("$..calendarEvents..start", jsonresponse));
					Date calendarEventsEnd = sdf.parse(DOTCRestService.readJson("$..calendarEvents..end", jsonresponse));
					if(!calendarEvents.contains(currentDate)) {
						rsvForRAP.add(rsvempIdFromService.get(i));
						rsvBidcard.add(bidStatus);
						n++;
					}*/	
					List<String> listOfStartDateActivities = new ArrayList<String>();
					List<String> listOfStartDateSequences = new ArrayList<String>();
					List<String> listOfStartDateReserve = new ArrayList<String>();
					try {
						if(jsonresponse.contains("\"employeeActivities\":["))
							listOfStartDateActivities = DOTCRestService.readJsonList("$.employeeActivities[?(((@.startDateTime<='"+currentDate+"') && (@.endDateTime>='"+currentDate+"')) || ((@.startDateTime>='"+currentDate+"') && (@.startDateTime<='"+nextDate+"')))].startDateTime", jsonresponse);
					} catch(Exception e) {}
					try {
						if(jsonresponse.contains("\"sequences\":["))
							listOfStartDateSequences = DOTCRestService.readJsonList("$.sequences[?(((@.sequenceStartDateTime.localTime<='"+currentDate+"') && (@.sequenceEndDateTime.localTime>='"+currentDate+"')) || ((@.sequenceStartDateTime.localTime>='"+currentDate+"') && (@.sequenceStartDateTime.localTime<='"+nextDate+"')))].sequenceStartDateTime.localTime", jsonresponse);
					} catch(Exception e) {}
					try {
						if(jsonresponse.contains("\"reserveAvailabilities\":["))
							listOfStartDateReserve = DOTCRestService.readJsonList("$.reserveAvailabilities[?(((@.startDateTime<='"+currentDate+"') && (@.endDateTime>='"+currentDate+"')) || ((@.startDateTime>='"+currentDate+"') && (@.startDateTime<='"+nextDate+"')))].startDateTime", jsonresponse);
					} catch(Exception e) {}
					
					if(listOfStartDateActivities.isEmpty() && listOfStartDateSequences.isEmpty() && listOfStartDateReserve.isEmpty()) {
						System.out.println(n+" "+rsvempIdFromService.get(i));
						rsvForRAP.add(rsvempIdFromService.get(i));
						rsvBidcard.add(bidStatus);
						n++;
						System.out.println(n+" "+rsvempIdFromService.get(i));
					}
						
				}
				System.out.println("Emp IDs with no activity: "+rsvForRAP);
				if(rsvForRAP.isEmpty())
					throw new Exception("No pilot with no activity on current date");
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Couldn't fetch reserve pilot ID with no activity on current date");
				Assert.assertFalse("Couldn't fetch reserve pilot ID with no activity on current date", true);
				
			}
		}
			
		//Used to save reserve pilots
			//16-03-22
			public static void saveResvEmpID(String[] empId) {
				rsvempIdFromService = Arrays.asList(empId).stream().filter(e -> !e.equals("0")).collect(Collectors.toList());
				String tmp=rsvempIdFromService.toString();
				if(tmp.length()>6)
					System.out.println(String.format("Reserve Employee id from service are: %s ..." , tmp.substring(0, Math.min(tmp.length(), 100))));
			}
		
			//to get the reserve pilot bidStatus
			//16/03/22
		public String getRESVidBidStatus (String reserveEmpID) throws InterruptedException {
			Integer index = Integer.parseInt(reserveEmpID);
			  String reserveEmp = DOTCRestService.rsvempIdFromService.get(index);
			  
			  String payload = "{\"employeeNumber\": " +reserveEmp + "}";
			  String jsonresponse = getCrewmember(payload);
			  String bidStatus = DOTCRestService.readJson("$..myInfo[?(@.contractMonthType==\"C\")].bidStatus", jsonresponse);
		  System.out.println("bid  status" +bidStatus);
			return bidStatus;
			  
			  
		}
		
		public void getLHidsWithSeq(String bidStatus) {

			try {

				String[] bidcard = bidStatus.split("/");
				base = bidcard[0];
				String includeEquipments = bidcard[1];
				String includePositions = bidcard[2];
				String pureDomestic = "";
				if (bidcard[3].equals("I")) {
					pureDomestic = "false";
				}
				String currentDateTdy = Util.getCurrentFormattedTime("yyyy-MM-dd");			
				
				Month curmonth = Util.getCurrentMonth();
				Integer currentYear = Util.getCurrentYear();
				contractMonth = curmonth.toString().substring(0, 3) + currentYear.toString();
				
				String strSeqJson = initializeTestDataFiles(DOTCJsonXmlPath.searchSequences);

				String strModifiedJson = modifyJson(strSeqJson, DOTCJsonXmlPath.SrchSeqFABase, base);
				strModifiedJson = modifyJson(strModifiedJson, DOTCJsonXmlPath.includeEquipments, includeEquipments);
				strModifiedJson = modifyJson(strModifiedJson, DOTCJsonXmlPath.includePositions, includePositions);
				strModifiedJson = modifyJson(strModifiedJson, DOTCJsonXmlPath.pureDomestic, pureDomestic);
				strModifiedJson = modifyJson(strModifiedJson, DOTCJsonXmlPath.SrchSeqFAContractMonth, contractMonth);
				strModifiedJson = modifyJson(strModifiedJson, DOTCJsonXmlPath.SrchSeqFAStart, currentDateTdy + "T00:00:00");
				System.out.println("Request Payload:" + strModifiedJson);

				String seqResponse = ccsService(strModifiedJson, "/CrewSequenceService/searchSequences");
				TestData.searchSequencesResponse=seqResponse;
				saveSearchSequenceResults(seqResponse);

			} catch (Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Exception occured while getting employee list from service");
				
			}

			
		} 
		
		 public static void saveSearchSequenceResults(String strResponseTxt) {
		        try {
		            TestData.searchSequencesResponse = strResponseTxt;
		            String employeeIDList = readJson(DOTCJsonXmlPath.EmployeeID, strResponseTxt);
		            String sequenceList = readJson(DOTCJsonXmlPath.SequenceNumbers, strResponseTxt);
		            TestData.saveEmployeeIDs(employeeIDList.split("\\,"));	           
		            TestData.saveSequenceNumbers(sequenceList.split("\\,"));
		            
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }

	//fucntion to fetch list of base airport list
	//13 Apr 2022
	public void getBaseAirportList() {
		try {
			Map<String, String> CMD = contractMonthDetails();
			String cm = CMD.get("currentCntrctMnth");
			String query = "{\r\n"
				+ "  \"airlineCode\": \"AA\",\r\n"
				+ "  \"airportCodes\": [],\r\n"
				+ "  \"contractMonth\": \""+cm+"\",\r\n"
				+ "  \"crewType\": \"P\",\r\n"
				+ "  \"gets\": [\r\n"
				+ "    \"CREW_BASE_STATUS\"\r\n"
				+ "  ]\r\n"
				+ "}";
			String lhs = ccsService(query, getBaseAirportList);
			String baseList = DOTCRestService.readJson(DOTCJsonXmlPath.getBaseAirport, lhs);
			TestData.listOfBaseAirport = Arrays.asList(baseList.split(","));
		} catch(Exception ex) {
			ex.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Couldn't fetch base airport list");
			Assert.assertFalse("Couldn't fetch base airport list", true);
		}
	}
	
	//function to create a list of layover station
	//27 Apr 2022
	public static List<String> readJsonList(String jsonQuery, String strResponseText) {
		List<String> readFile = new ArrayList<String>();
		try {
			readFile = com.jayway.jsonpath.JsonPath.parse(strResponseText).read(jsonQuery);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(
					"Something went wrong in readJsonList(String jsonQuery,String strResponseText) function in CMEAppServices.java. Message: "
							+ ex.getMessage());
		}
		return readFile;
	}
	
	//TO  get LH pilot Id's with no activity on current day
	//30 May 2022
	public void  getLHidswithNoActivity(String base) throws InterruptedException, ParseException {
		try {
				
				String query = "{\"airlineCode\":\"AA\",\"base\":\"%s\",\r\n"
						+ "\"contractMonth\":\"%s\",\"crewType\":\"P\",\"selectionType\":\"REGL\",\r\n"
						+ "\"gets\":[\"BID_STATUS\"]}";
				
				Map<String, String> CMD = contractMonthDetails();
				String cm = CMD.get("currentCntrctMnth");
				String lhs = ccsService(String.format(query, base, cm), "/CrewMemberService/getCrewMembersByBase");
				String employeeList = DOTCRestService.readJson(DOTCJsonXmlPath.EmployeeID, lhs);
				String[] r = null;
				r= employeeList.split("\\,");
				saveResvEmpID(r);
				//String currentDate = Util.getCurrentFormattedTime("yyyy-MM-dd");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// fetch current date
				String currentDate = Util.getCurrentFormattedTime("yyyy-MM-dd")+"T00:00";
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(currentDate));
				
				//fetch current date +2
				calendar.add(Calendar.DATE, 2);
				String nextDate = sdf.format(calendar.getTime())+"T00:00";
				
				rsvForRAP.clear();
				rsvBidcard.clear();
				
				int n = 0;
				int totalpilots = rsvempIdFromService.size();
				for (int i = 0; i < totalpilots/2 && n<=4 ; i++) { // i2
					// wrapperbase url
					String payload = "{\r\n"
							+ "    \"contractMonths\": [\r\n"
							+ "                \""+cm+"\"\r\n"
							+ "    ],\r\n"
							+ "    \"crewMemberKeyDTO\": {\r\n"
							+ "        \"airlineCode\": \"AA\",\r\n"
							+ "        \"employeeNumber\":"+rsvempIdFromService.get(i)+"\r\n"
							+ "    }\r\n"
							+ "}";
					
					
					String jsonresponse = getCrewmember(payload);
					
					String div = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentDivision", jsonresponse);
					String eqp = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentEquipment", jsonresponse);
					String pos = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentPosition", jsonresponse);
					String ba = DOTCRestService.readJson("$.bidStatuses[?(@.contractMonthType=='C')].currentBase", jsonresponse);
					String bidStatus = ba+"/"+eqp+"/"+pos+"/"+div;
					
					List<String> listOfStartDateActivities = new ArrayList<String>();
					List<String> listOfStartDateSequences = new ArrayList<String>();
					List<String> listOfStartDateReserve = new ArrayList<String>();
					try {
						if(jsonresponse.contains("\"employeeActivities\":["))
							listOfStartDateActivities = DOTCRestService.readJsonList("$.employeeActivities[?(((@.startDateTime<='"+currentDate+"') && (@.endDateTime>='"+currentDate+"')) || ((@.startDateTime>='"+currentDate+"') && (@.startDateTime<='"+nextDate+"')))].startDateTime", jsonresponse);
					} catch(Exception e) {}
					try {
						if(jsonresponse.contains("\"sequences\":["))
							listOfStartDateSequences = DOTCRestService.readJsonList("$.sequences[?(((@.sequenceStartDateTime.localTime<='"+currentDate+"') && (@.sequenceEndDateTime.localTime>='"+currentDate+"')) || ((@.sequenceStartDateTime.localTime>='"+currentDate+"') && (@.sequenceStartDateTime.localTime<='"+nextDate+"')))].sequenceStartDateTime.localTime", jsonresponse);
					} catch(Exception e) {}
					try {
						if(jsonresponse.contains("\"reserveAvailabilities\":["))
							listOfStartDateReserve = DOTCRestService.readJsonList("$.reserveAvailabilities[?(((@.startDateTime<='"+currentDate+"') && (@.endDateTime>='"+currentDate+"')) || ((@.startDateTime>='"+currentDate+"') && (@.startDateTime<='"+nextDate+"')))].startDateTime", jsonresponse);
					} catch(Exception e) {}
					
					if(listOfStartDateActivities.isEmpty() && listOfStartDateSequences.isEmpty() && listOfStartDateReserve.isEmpty()) {
						if(!DOTCRestService.readJsonList("$..temporaryDutyActivities", jsonresponse).isEmpty()) {
							System.out.println(rsvempIdFromService.get(i));
							continue;
						}
						rsvForRAP.add(rsvempIdFromService.get(i));
						rsvBidcard.add(bidStatus);
						n++;
					}		
				}
				
			if(rsvForRAP.isEmpty())
					throw new Exception("No pilot with no activity on current date");
			} catch(Exception ex) {
				ex.printStackTrace();
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Couldn't fetch reserve pilot ID with no activity on current date");
				Assert.assertFalse("Couldn't fetch reserve pilot ID with no activity on current date", true);
				
			}
		}
	
	//function to hit QA service
	//17 May 2022
	//Modified on 12 Sep 2022 to include cloud endpoint
	public String qaService(String strJson, String path) {
		
		String strResponse = "";
		KeyStore keyStore = null;
		SSLConfig configs = null;
		Response response=null;
		
		try {			
			
		setWrapperURL();
		List<Header> headerlist = new ArrayList<Header>();
		DOTCLogInScreen lis = new DOTCLogInScreen();
		
		if(!wrapperBaseURL.contains("cloud.aa.com/DOTCCS")) {		
			if(!wrapperBaseURL.equals("http://htappd01682.qcorpaa.aa.com:8090")) {
				lis.getCookieData();
				headerlist.add(new Header("Cookie", "SMSESSION="+SMSESSION));}
			headerlist.add(new Header("Content-Type", "application/json"));
			headerlist.add(new Header("source", TestData.strSource));
			
	        Headers headers = new Headers(headerlist);
						
			configs = new SSLConfig().relaxedHTTPSValidation();
			RestAssured.config = RestAssured.config().sslConfig(configs);
			response = RestAssured.given().baseUri(wrapperBaseURL).body(strJson).basePath(path)
					.headers(headers).post();
		}
		
		String token = "";
		if(wrapperBaseURL.contains("stage"))
			token = getToken("stage");
		else 
			token = getToken("qa");
		
		List<Header> headerList = new ArrayList<Header>();
		headerList.add(new Header("Authorization", "Bearer "+token));
		headerList.add(new Header("Content-Type", "application/json"));
		headerList.add(new Header("source", TestData.strSource));
		Headers headers = new Headers(headerList);
		
		keyStore = KeyStore.getInstance("PKCS12");
		String password = "RBER*OQQDWM7H";
		keyStore.load(new FileInputStream("src/test/resources/certificate/T1033572.pfx"), password.toCharArray());
		
		if(keyStore != null) {
			org.apache.http.conn.ssl.SSLSocketFactory clientAuthFactory = new org.apache.http.conn.ssl.SSLSocketFactory(keyStore, password);
			configs = new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
		}
		
		try {
			RestAssured.config = RestAssured.config().sslConfig(configs);
			response = RestAssured.given().proxy("http://inetgw.aa.com:9091/").baseUri(wrapperBaseURL)
					.body(strJson).basePath(path).headers(headers).post();
		} catch (Exception ex) {
			response = RestAssured.given().baseUri(wrapperBaseURL).body(strJson).basePath(path).headers(headers).post();
		}				
          
			InputStream strm = response.getBody().asInputStream();
			Reader reader = new InputStreamReader(strm);
			strResponse = CharStreams.toString(reader);
			if(response.getStatusCode() != 200) {
				System.out.println(wrapperBaseURL+path+" : "+response.getStatusCode());
				throw new Exception("Service error");
			}
			System.out.println(strResponse);
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.assertFalse(true);
		}
		return strResponse;
	}

	//function to fetch data for pick up dotc from qa service
	// 16 May 2022
	public String pickUpDotcService(String servicePath, String base, String division, String equipment, String seat, String date) {
		String lhs = "";
		try {
			
			String query = "{"
				    +"\"airlineCode\": \"AA\","
				    +"\"base\": \""+base+"\","
				    +"\"division\": [\""+division+"\"],"
				    +"\"equipmentCode\": \""+equipment+"\","
				    +"\"seat\": \""+seat+"\","
				    +"\"startDate\": \""+date+"\""
				+"}";
			if(servicePath.equalsIgnoreCase("pickUpDOTC"))
				lhs = qaService(query, getPickUpDOTC);
			else if(servicePath.equalsIgnoreCase("pickUpOutside"))
				lhs = qaService(query,getPickUpOutsideDOTC);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return lhs;
	}
}
