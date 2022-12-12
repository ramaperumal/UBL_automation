Feature: US1460461 Validate list page Aircraft or Position and their search results.

 @AA @US1460461 @US1460461_1 @PostGoLive @List
 Scenario Outline: US1460461 Validate list page Aircraft or Position values. Test Case Name: US1460461_1 Validate list page Aircraft or Position values.
 	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I clicked on List tab in landing page
    And I should validate options in Aircraft dropdown is "<aircraftOptions>"
    And I should validate options in Positions dropdown is "<positionsOptions>"
    
 Examples:
 |base|aircraftOptions|positionsOptions|
 |DFW |737,320,777,787|Captain,First Officer|
 
 @AA @US1460461 @US1460461_2 @PostGoLive @List
 Scenario Outline: US1460461 Validate list page search results. Test Case Name: US1460461_2 Validate list page search results.
 	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>" having no activity on current day
    Then I am at the DOTC/RAS landing page
    #deleting existing ballot if present
    And I Click on Ballots tab
    And I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    And I should be able to click Pick Up Outside link
    And I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    And I should be able to click Template link
    And I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    #creating ballots for pickup outside DOTC
    And I should be able to click on Search button
    And I should be able to access Search page
    And I should be able to click Save Generic Criteria button
    And I should be able to enter the Search title based on User story Number "<title>" 
    And I Select pick up Outside checkbox in Save Generic Criteria
    And I should be able to Save the criteria
    And I should be able to click Pick Up Outside link
    And I should be able to update "<ballotType2>" ballot
    And I verfy ballot creation in the ballot list
    And I turn On the Pick Up Outside DOTC button from ballots list in Ballots Screen
    #creating ballot for pickup DOTC
    And I should be able to click on Search button
    And I should be able to access Search page
    And I Should set tomorrow date in departure from date 
    And I should be able to click Save Generic Criteria button
    And I should be able to enter the Search title based on User story Number "<title>"
    And I Select pick up checkbox in Save Generic Criteria
    And I should be able to Save the criteria
    And I should be able to update "<ballotType1>" ballot
    And I verfy ballot creation in the ballot list
    And I turn On the Pick Up DOTC button from ballots list in Ballots Screen
    And I clicked on List tab in landing page
    And I clicked on Submit button
    Then I should validate Pick up DOTC list
    And I should click on Pick up outside tab  
    Then I should validate Pick up Outside DOTC list
    And I should click on Open Time tab
    And I should validate Open time list
    And I should select different combination for "position"
   	And I clicked on Submit button
    And I should click on Open Time tab
    Then I should validate Open time list
    
 Examples:
 |base|title		 |ballotType2  |ballotType1|ballotType3|
 |DFW |Test_US1460461|PickUpOutside|PickUpDOTC |Template   |
 
  @AA @US1460461 @US1460461_3 @PostGoLive @List
  Scenario Outline: US1460461 Validate Aircraft group and Position in open time. Test Case Name: US1460461_3 Validate Aircraft group and Position in open time.
 	Given pilotDOTC application has been Launched
 	When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I clicked on List tab in landing page
    And I clicked on Submit button
    And I should click on Open Time tab
    And I should validate Aircraft and position group in open time
  
 Examples:
 |base|
 |DFW |