Feature: US1278924 Total Credit Validation in Search
  As a PilotDOTC user, i should be able to validate if LH pilot can save ballots with Total Credit

  @Ballot @TotalCredit @SCH @US1278924 @AA @Smoke @Medium @US1278924_1
  Scenario Outline: Validate LH Pilot can save Search with Total Credit. Test Case Name:US1278924 Total Credit values selected as Min and Max values. Priority: Useful. Risk: Medium.
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
    Then I should be able to click Pay and Credit Down Arrow
    Then I should be able to click Total Credit Plus
    Then I should be able to select Maximum Hours "<MaxHours>"
    Then I should be able to select Maximum Minutes "<MaxMins>"
    Then I should be able to select Minimum Hours "<MinHours>"
    Then I should be able to select Minimum Minutes "<MinMins>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate the Min "<MinHours>" "<MinMins>" and Max "<MaxHours>" "<MaxMins>" Total Credit in Ballot "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate the Min "<MinHours>" "<MinMins>" and Max "<MaxHours>" "<MaxMins>" Total Credit in Ballot "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I should be able to validate the Min "<MinHours>" "<MinMins>" and Max "<MaxHours>" "<MaxMins>" Total Credit in Ballot "<ballotType3>" for "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I verfy ballot creation in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate the Min "<MinHours>" "<MinMins>" and Max "<MaxHours>" "<MaxMins>" Total Credit in Ballot "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate the Min "<MinHours>" "<MinMins>" and Max "<MaxHours>" "<MaxMins>" Total Credit in Ballot "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I should be able to validate the Min "<MinHours>" "<MinMins>" and Max "<MaxHours>" "<MaxMins>" Total Credit in Ballot "<ballotType3>" for "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | MaxHours | MaxMins | MinHours | MinMins | title          | ballotType1 | ballotType2   | ballotType3 |
      | DFW  |       22 |      25 |       14 |      45 | Test_US1278924 | PickUpDOTC  | PickUpOutside | Template    |
