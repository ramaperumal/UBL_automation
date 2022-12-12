Feature: US1344609 Ballot Feature
  As a PilotDOTC user, i should be able to save a ballot with Departure time and Arrival time.

  @SaveBallot @Smoke @MN @US1344609  @AA @Medium @DepartNoEarlierThan @DepartNoLaterThan @ArriveNoEarlierThan @ArriveNoLaterThan
  Scenario Outline: validate if LH pilot can save a ballot with Depart No Earlier, Later Than and Arrival No earlier Than and Later Than fields. Test Case Name:US1344609 Validate if LH pilot can save a ballot with Departure and Arrival times.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I Clicked on Search tab
    And I should be able to access Search page
    Then I select the Depart No Earlier Than dropdown "<Depart_No_Earlier_Than_Time>"
    Then I select the Depart No Later Than dropdown "<Depart_No_Later_Than_Time>"
    Then I select the Arrive No Earlier Than dropdown "<Arrive_No_Earlier_Than_Time>"
    Then I select the Arrive No Later Than dropdown "<Arrive_No_Later_Than_Time>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Departure and arrival time values "<Depart_No_Earlier_Than_Time>" "<Depart_No_Later_Than_Time>" "<Arrive_No_Earlier_Than_Time>" "<Arrive_No_Later_Than_Time>" for Ballot Type  "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Departure and arrival time values "<Depart_No_Earlier_Than_Time>" "<Depart_No_Later_Than_Time>" "<Arrive_No_Earlier_Than_Time>" "<Arrive_No_Later_Than_Time>" for Ballot Type  "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Departure and arrival time values "<Depart_No_Earlier_Than_Time>" "<Depart_No_Later_Than_Time>" "<Arrive_No_Earlier_Than_Time>" "<Arrive_No_Later_Than_Time>" for Ballot Type  "<ballotType3>" "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate Departure and arrival time values "<Depart_No_Earlier_Than_Time>" "<Depart_No_Later_Than_Time>" "<Arrive_No_Earlier_Than_Time>" "<Arrive_No_Later_Than_Time>" for Ballot Type  "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Departure and arrival time values "<Depart_No_Earlier_Than_Time>" "<Depart_No_Later_Than_Time>" "<Arrive_No_Earlier_Than_Time>" "<Arrive_No_Later_Than_Time>" for Ballot Type  "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Departure and arrival time values "<Depart_No_Earlier_Than_Time>" "<Depart_No_Later_Than_Time>" "<Arrive_No_Earlier_Than_Time>" "<Arrive_No_Later_Than_Time>" for Ballot Type  "<ballotType3>" "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | Depart_No_Earlier_Than_Time | Depart_No_Later_Than_Time | Arrive_No_Earlier_Than_Time | Arrive_No_Later_Than_Time | ballotType1 | ballotType2   | ballotType3 | title          | base |
      | 09:30                       | 10:00                     | 10:30                       | 11:00                     | PickUpDOTC  | PickUpOutside | Template    | Test_US1344609 | DFW  |
