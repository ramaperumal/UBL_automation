Feature: US1280093 Crew Schedule DOTC Feature
  As a PilotDOTC Crew Scheduler, they can designate specific trips associated with 4 Part Bid Status as Premium
#@Premium @US1280093 @AA @Short @US1280093_1 @Smoke 
 @US1280093 @AA @Short @US1280093_1 @Smoke 
  	Scenario Outline: DOTC Automation: Smoke Test: Valid Admin can designate premium sequence in the CS app. Test Case Name:US1280093_1 Admin can designate premium sequence.
	Given CrewScheduler application has been Launched    
    Then I click on the Admin link
    Then I click on Designate Premium link
    Then I select Start Date and End Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then select the Four Part Bid Status to Search "<base>""<equipment>""<division>""<seat>"
    Then I click Search
    Then choose sequences and mark it as Premium "<base>""<equipment>""<division>""<seat>"

   
    Examples: 
    |base|	equipment|	division|	seat|	offsetfromtoday_startdt|	offsetfromtoday_enddt|
    |MIA|	320		|	I		|	CA	|	1	|	2|
    
   # @US1280093_2 @Premium @US1280093 @AA @Short @Smoke 
   @US1280093_2 @US1280093 @AA @Short @Smoke
    Scenario Outline: DOTC Automation: Smoke Test:Validate when the pilot searches for premium sequences a valid premium sequence shows the $ icon in the search results. Test Case Name:US1280093_2 Searches for premium sequences a valid premium sequence shows the $ icon.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>" and equipment at "<eqp>" and position "<pos>" and division "<div>"
    Then I am at the DOTC/RAS landing page
    When I clicked on the pilot name at the right top
    Then I should be able to click on Search button
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I should be able to click on Show Sequences
    Then I should be able to verify Premium Icon for these sequences and dates "<base>""<eqp>""<div>""<pos>"
   
    Examples: 
    |env|  subDomain |	role|    employeeID|	base|	eqp|	pos|	div|	offsetfromtoday_startdt|	offsetfromtoday_enddt|
    |qa |       	 |	itAdmin| 	17033|		MIA|	320|	CA|		  I|		       		1	|						2|
    #|uat |       	 |	itAdmin| 	17033|		MIA|	320|	CA|		  I|		       		1	|						2|