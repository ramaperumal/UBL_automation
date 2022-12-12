Feature: US1274743 Login Feature  As a PilotDOTC user, i should be able to search sequences based on departure date and arrival date range

@Search @Smoke @RSB @US1274743 @US1274743_1 @DepatureDate @MandatoryField @AA @Short @US1368453
Scenario Outline: Validate search sequence is successful. Test Case Name:US1274743_1 Mandatory_DepartureDate. Priority: Useful. Risk: Medium.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
	When I Clicked on Search tab 
	Then I Enter Departure and Arrival dates 
	Then I Clear Departure and Arrival dates
	And I Verify that Departure date is mandatory field

Examples: 
		|base|
		|DFW |
		

@Search @Smoke @RSB @US1274743 @US1274743_2 @Sequence @AA @Medium @US1368453
Scenario Outline: Validate search sequence is successful. Test Case Name:US1274743_2 Departure_DateRange. Priority: Useful. Risk: Medium.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
	When I Clicked on Search tab 
	Then I Enter Departure from/departure to date
	Then I Clicked on Show sequence button
	Then I Should be able to see the list of Sequence based on departure date range
	Then I fetch the sequence from the service based on departure date range
	Then I Verify the sequence from the service

Examples: 
		|base|
		|DFW |
		
			
@Search @Smoke @RSB @US1274743 @US1274743_3 @Sequence @AA @Medium @US1368453  
Scenario Outline: Validate search sequence is successful. Test Case Name:US1274743_3 Arrival_DateRange. Priority: Useful. Risk: Medium.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
	When I Clicked on Search tab 
	Then I Enter Departure from/Arrival from/Arrival to date
	Then I Clicked on Show sequence button
	Then I Should be able to see the list of Sequence based on arrival date range
	Then I fetch the sequence from the service based on arrival date range
	Then I Verify the sequence from the service

Examples: 
	|base|
	|DFW |
		
		
@Search @Smoke @RSB @US1274743 @US1274743_4  @AA @Sequence @Medium @US1368453
Scenario Outline: Validate search sequence is successful. Test Case Name:US1274743_4 Departure_Arrival_date. Priority: Useful. Risk: Medium.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
	When I Clicked on Search tab 
	Then I Enter Departure from/departure to/Arrival from/Arrival to date
	Then I Clicked on Show sequence button
	Then I Should be able to see the list of Sequence based on departure/arrival date range
	Then I fetch the sequence from the service based on departure/arrival date range
	Then I Verify the sequence from the service

Examples: 
		|base|
		|DFW |
		
