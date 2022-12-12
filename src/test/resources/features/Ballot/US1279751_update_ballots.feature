Feature: US1279751 Ballots Feature
  As a PilotDOTC user, i should be able to update ballots based on Departure and Arrival date range

  @Ballot @Smoke @RSB @US1279751 @US1279751_1 @AA @UpdateBallot @DepartureFromDate @US1368453 @Medium
  Scenario Outline: Validate update ballots is successful. Test Case Name:US1279751_1 Departure date without dash. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    When I Clicked on Search tab
    Then I Enter Departure from date only
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I Select Pick Up DOTC checkbox
    Then I Clicked on Save button
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Departure from date without dash for Ballot Type "<ballotType1>" with "<title>"
    Then I should be able to update "<ballotType1>" ballot 
    Then I verfy ballot creation in the ballot list
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Departure from date without dash for Ballot Type "<ballotType1>" with "<title>"
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | Test_US1279751 |

  @Ballot @Smoke @RSB @US1279751 @AA @UpdateBallot @DepartureFromDate @DepartureToDate @US1279751_2 @US1368453 @Medium
  Scenario Outline: Validate update ballots is successful. Test Case Name:US1279751_2 Departure date range separated by dash. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    When I Clicked on Search tab
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I Select Pick Up DOTC checkbox
    Then I Clicked on Save button
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Departure from date and Departure to date separated by dash for Ballot Type "<ballotType1>" with "<title>"
    Then I should be able to update "<ballotType1>" ballot
    Then I verfy ballot creation in the ballot list
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Departure from date and Departure to date separated by dash for Ballot Type "<ballotType1>" with "<title>"
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | title          |offsetfromtoday_startdt|offsetfromtoday_enddt|
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | Test_US1279751 |1                      |10                   |

  @Ballot @Smoke @RSB @US1279751 @AA @UpdateBallot @DepartureFromAndToDate @DArrivalToDate @US1279751_3 @US1368453 @Medium
  Scenario Outline: Validate update ballots is successful. Test Case Name:US1279751_3 Arrival date range separated by dash. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    When I Clicked on Search tab
    Then I Enter Departure from "<departure_from_offset>" Arrival from "<arrival_from_offset>" Arrival to "<arrival_to_offset>" date
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I Select Pick Up DOTC checkbox
    Then I Clicked on Save button
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Departure from date without dash for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Arrival from date and Arrival to date separated by dash for Ballot Type "<ballotType1>" with "<title>"
    Then I should be able to update "<ballotType1>" ballot
    Then I verfy ballot creation in the ballot list
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Departure from date without dash for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Arrival from date and Arrival to date separated by dash for Ballot Type "<ballotType1>" with "<title>"
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | title          |departure_from_offset|arrival_from_offset|arrival_to_offset|
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | Test_US1279751 |0					|1				    |10				  |

 @Ballot @Smoke @RSB @US1279751 @AA @UpdateBallot @DepartureFromAndToDate @DArrivalFromAndToDate @US1279751_4 @US1368453 @Medium
  Scenario Outline: Validate update ballots is successful. Test Case Name:US1279751_4 Departure date range and Arrival date range are separated by dash. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    When I Clicked on Search tab
    Then I Enter Departure from "<departure_from_offset>" departure to "<departure_to_offset>" Arrival from "<arrival_from_offset>" Arrival to "<arrival_to_offset>" date
	Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I Select Pick Up DOTC checkbox
    Then I Clicked on Save button
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Departure date range and Arrival date range are separated by dash for Ballot Type "<ballotType1>" with "<title>"
    Then I should be able to update "<ballotType1>" ballot
    Then I verfy ballot creation in the ballot list
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I verify Departure date range and Arrival date range are separated by dash for Ballot Type "<ballotType1>" with "<title>"
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | title          |departure_from_offset|departure_to_offset|arrival_from_offset|arrival_to_offset|
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | Test_US1279751 |0					|10                 |1				    |10				  |
