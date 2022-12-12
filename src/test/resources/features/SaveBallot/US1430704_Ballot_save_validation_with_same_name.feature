Feature: US1430704 Ballot with same name and at least one other criteria should not mark as duplicate.

@SaveBallot @AA @US1430704 @US1430704_1  @PostGoLive
 Scenario Outline: Ballot with same name and at least one other criteria should not mark as duplicate. Test Case Name:US1430704 Ballot saved with same name and different criteria should not mark as duplicate.
 	Given pilotDOTC application has been Launched
 	When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I should be able to click on Search button
    Then I should be able to access Search page
    Then I Enter Departure from/departure to date
    Then I Clicked on Save Generic Criteria
    Then I should be able to enter the Search title based on User story Number "<title>"    
    Then I should be able to check All Ballots check box
    Then I Clicked on Save button
    Then I should be able to update "<ballotType1>" ballot 
    And I verfy ballot creation in the ballot list
    Then I should be able to click on Search button
    Then I Enter Departure and Arrival dates
    Then I Clicked on Save Generic Criteria
    Then I should be able to enter the Search title based on User story Number "<title>" 
    Then I Select All Ballots checkbox
    Then I Clicked on Save button
    Then I should be able to update "<ballotType1>" ballot 
    #And I verfy ballot creation in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    
 Examples:
  |base|ballotType1 |ballotType2   |ballotType3 |title |
  |DFW |PickUpDOTC  |PickUpOutside |Template    |Test_US1430704|
  

@SaveBallot @AA @US1430704 @US1430704_2 @PostGoLive
 Scenario Outline: Ballot with same name and at least one other criteria should not mark as duplicate. Test Case Name:US1430704 Ballot saved with same name and same criteria should mark as duplicate.
 	Given pilotDOTC application has been Launched
 	When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I should be able to click on Search button
    Then I should be able to access Search page
    Then I Enter Departure from/departure to date
    Then I Clicked on Save Generic Criteria
    Then I should be able to enter the Search title based on User story Number "<title>"    
    Then I should be able to check All Ballots check box
    Then I Clicked on Save button
    Then I should be able to update "<ballotType1>" ballot 
    And I verfy ballot creation in the ballot list
    Then I should be able to click on Search button
    Then I Enter Departure from/departure to date
    Then I Clicked on Save Generic Criteria
    Then I should be able to enter the Search title based on User story Number "<title>" 
    Then I Select All Ballots checkbox
    Then I Clicked on Save button
    Then I verify Duplicate ballots in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    
 Examples:
  |base|ballotType1 |ballotType2   |ballotType3 |title 		   |
  |DFW |PickUpDOTC  |PickUpOutside |Template    |Test_US1430704|