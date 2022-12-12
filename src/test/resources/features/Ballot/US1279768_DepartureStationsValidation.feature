Feature: US1279768 DepartureStations Validation
  As a PilotDOTC user, I need a smoke test that I can save ballot with Departure Stations

 @Ballot @AA @DepartureStations @R1Iteration6 @SCH @US1279768 @Smoke  @DepartureStations
  Scenario Outline: Validate LH Pilot can save ballot with Departure Stations. Test Case Name:US1279768 Verify Ballot can be saved with Departure Stations. Priority: Useful. Risk: Medium.
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
    And I should be able to access Search page
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click Departure Station Plus
    Then I should be able to select Departure Stations "<stationNames>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType3>" "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I verfy ballot creation in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType3>" "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | stationNames        | title          |
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | BOS,DFW,ORD,CLT,PHL | Test_US1279768 |
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | MIA,LGA,DAL         | Test_US1279768 |
