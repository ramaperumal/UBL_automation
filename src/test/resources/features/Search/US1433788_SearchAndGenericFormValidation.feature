Feature: US1433788 Form validation in search page and on edit generic criteria
  	
# Form validation in search page for remaining fields are covered in US1368460  	
 @Search @AA @US1433788 @US1433788_1 @SearchFormValidation
  Scenario Outline: US1433788_1 Form validation in search page when min value > max value. Test Case Name:US1433788_1 Form validation in search page for No Work Hours, Layovers, Cities.
    Given pilotDOTC application has been Launched 
	When I login with the a LH pilot base at "<base>" 
	Then I am at the DOTC/RAS landing page 
	Then I should be able to click on Search button 
	And I should be able to access Search page 
	And I clicked on Days Dates and Hour down arrow 
	And I should be able to select No work Hours criteria 
	And I should be able to select No Work Hours from Min "<workHrs_min>" and Max "<workHrs_max>" dropdown 
	Then I should validate red border around "No Work Hours" textbox 
	Then I should validate error message for "noWorkHours" field
	And I should be able to click Airport and Layovers Down Arrow 
	And I should be able to click Layover Plus
	And I should be able to enter station code in Layover include textbox "<includeLayover>"
	Then I should validate red border around "Include Layovers" textbox
	And I should be able to enter station code in Layover exclude textbox "<excludeLayover>"
	Then I should validate red border around "Exclude Layovers" textbox  
	And I should be able to click Cities Plus
    And I should be able to enter station code in Include cities textbox "<includeCity>"
	Then I should validate red border around "Include Cities" textbox
	And I should be able to enter station code in Exclude cities textbox "<excludeCity>"
	Then I should validate red border around "Exclude Cities" textbox    
    
   	Examples:
   	| base | workHrs_min| workHrs_max| includeLayover | excludeLayover | includeCity | excludeCity| 
   	| DFW  |07:00		|03:00		 | DALLAS		  | DALLAS		   | SEATTLE     |SEATTLE     |
   	
 @Search @AA @US1433788 @US1433788_2 @SearchFormValidation 
Scenario Outline: US1433788_2 Form validation in save generic criteria when min value > max value. Test Case Name:US1433788_2 Form validation in generic criteria for all fields. 
	Given pilotDOTC application has been Launched 
	When I login with the a LH pilot base at "<base>" 
	Then I am at the DOTC/RAS landing page 
	Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "PickUpOutside" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "Template" for "<title>"
	Then I should be able to click on Search button 
	And I should be able to access Search page 
	Then I should be able to click Save Generic Criteria button 
	Then I should be able to enter the Search title based on User story Number "<title>" 
	Then I should be able to check All Ballots check box 
	And I should be able to Save the criteria 
	Then I should be able to update "<ballotType1>" ballot 
	And I should be able to Edit the request for ballottype "<ballotType1>" "<title>" 
	And I clicked on Days Dates and Hour down arrow 
	And I should be able to click Caldendar Days Plus 
	And I should be able to select Calendar Days from drop down "<cal_days_min>" and "<cal_days_max>" 
	Then I should validate red border around "Calendar Days" textbox 
	Then I should validate error message for "calendarDays" field 
	And I should be able to add Duty Periods in Search 
	And I should be able to select "<duty_period_min>" and "<duty_period_max>" from Duty Periods Min and Max dropdown 
	Then I should validate red border around "Duty Periods" textbox 
	Then I should validate error message for "dutyPeriods" field 
	And I should be able to select No work Hours criteria 
	And I should be able to select No Work Hours from Min "<workHrs_min>" and Max "<workHrs_max>" dropdown 
	Then I should validate red border around "No Work Hours" textbox 
	Then I should validate error message for "noWorkHours" field 
	And I should be able to click Pay and Credit Down Arrow 
	And I should be able to click Paid Credit Plus 
	And I should be able to select Paid Credit values "<paid_credit_max_hr>" "<paid_credit_max_min>" "<paid_credit_min_hr>" "<paid_credit_min_min>" 
	Then I should validate red border around "Paid Credit" textbox 
	Then I should validate error message for "paidCredit" field 
	And I should be able to click Total Credit Plus 
	Then I should be able to select Maximum Hours "<paid_credit_max_hr>" 
	Then I should be able to select Maximum Minutes "<paid_credit_max_min>" 
	Then I should be able to select Minimum Hours "<paid_credit_min_hr>" 
	Then I should be able to select Minimum Minutes "<paid_credit_min_min>" 
	Then I should validate red border around "Total Credit" textbox 
	Then I should validate error message for "totalCredit" field 
	And I should be able to click Airport and Layovers Down Arrow 
	And I should be able to click Layover Plus 
	And I should be able to enter station code in Layover include textbox "<includeLayover>" 
	Then I should validate red border around "Include Layovers" textbox 
	And I should be able to enter station code in Layover exclude textbox "<excludeLayover>" 
	Then I should validate red border around "Exclude Layovers" textbox 
	And I should be able to click Cities Plus 
	And I should be able to enter station code in Include cities textbox "<includeCity>" 
	Then I should validate red border around "Include Cities" textbox 
	And I should be able to enter station code in Exclude cities textbox "<excludeCity>" 
	Then I should validate red border around "Exclude Cities" textbox 
	And I should click on Layover Times Plus 
	And I should be able to choose "<layover_min>" and "<layover_max>" for Layover Times 
	Then I should validate red border around "Layover Times" textbox 
	Then I should validate error message for "layoverTimes" field
	Then I should be able to click on cancel update button 
	
	
	Examples: 
		| base | cal_days_min | cal_days_max | duty_period_min | duty_period_max | paid_credit_min_hr | paid_credit_min_min | paid_credit_max_hr | paid_credit_max_min |leg_dp_min | leg_dp_max | leg_seq_min | leg_seq_max | tafb_min | tafb_max | layover_min | layover_max |workHrs_min| workHrs_max| includeLayover | excludeLayover | includeCity | excludeCity|ballotType1|title| 
		| DFW  | 4  		  | 2            | 3 			   | 1				 | 13				  | 45					| 6				  | 45					   |4 		   | 2			 | 4		   | 3			 | 100      | 3		   | 15			 | 10		   |07:00		|03:00		 | DALLAS		| DALLAS		 | SEATTLE     |SEATTLE     |PickUpDOTC|US1433788_2|
		
@Search @AA @US1433788 @US1433788_3 @SearchFormValidation @DateValidation
Scenario Outline: US1433788_3 Form validation for departure and arrival dates. Test Case Name:US1433788_3 Departure and Arrival date in search page.
Given pilotDOTC application has been Launched 
When I login with the a LH pilot base at "<base>" 
Then I am at the DOTC/RAS landing page 
Then I should be able to click on Search button 
And I should be able to access Search page
#Departure To  date ealier than  Departure From date	
Then I select "earlierDep" date "<from_future>" and "<to_earlier>" 
Then I validate "earlierDeparture" error msg "<earlier_dep_err_msg>"

#Departure To date exceeding  31 days than Departure From date
Then I select "exceedDep" date "<from_future>" and "<to_exceed>"
Then I validate "exceedDaysDep" error msg "<exceed_err_msg>"
Then I click on "depEndDate" clear button

#Arrival To date earlier than Arrival From date
Then I select "arrivalEarlier" date "<from_future>" and "<to_earlier>"
Then I validate "arrivalEarlier" error msg "<earlier_arr_err_msg>" 

#Arrival To date exceeding 31 days than Arrival From date
Then I select "arrivalExceed" date "<from_future>" and "<to_exceed>"
Then I validate "arrivalExceed" error msg "<exceed_err_msg>" 
Then I click on "arrEndDate" clear button

#Arrival From date earlier than Departure From date
Then I select "arrEarlierDep" date "<from_future>" and "<to_earlier>"
Then I validate "arrEarlierDep" error msg "<earlier_arrDep_err_msg>" 

#Arrival From date exceeding 31 days than Departure From date
Then I select "arrExceedDep" date "<from_future>" and "<to_exceed>"
Then I validate "arrExceedDep" error msg "<exceed_err_msg>" 


Examples:
| base |from_future|to_earlier| to_exceed |earlier_dep_err_msg                                            |exceed_err_msg                         |earlier_arr_err_msg                                      |earlier_arrDep_err_msg|
| DFW  | 2         |1         | 33        | Departure End date must be same or after Departure Start date |Dates cannot be more than 31 days apart|Arrival End date must be same or after Arrival Start date|Arrival Start date must be same or after Departure Start date|
	