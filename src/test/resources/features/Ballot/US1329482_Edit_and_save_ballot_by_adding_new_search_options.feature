Feature: US1329482 Ballots Feature
  As a PilotDOTC user, i should be able to add new Search Options with the existing criteria while editing the ballot

  @Ballot @Smoke @RSB @US1329482 @sitTime @TotalCredit @Edit @Medium @AA
  Scenario Outline: Validate if both old and new search options are displayed in ballot page. Test Case Name:US1329482 Validate if LH pilot is able to add new Search options while editing a ballot.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I Clicked on Search tab
    And I should be able to access Search page
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I should be able to click Sequence Characteristics
    And I should be able to click Sit Time Plus
    Then I should be able to Select Value "<sitTime>" in Sit Time dropdown
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I should be able to Edit the request for ballottype "<ballotType1>" "<title>"
    Then I should be able to click Pay and Credit Down Arrow
    Then I should be able to click Total Credit Plus
    Then I should be able to select Maximum Hours "<MaxHours>"
    Then I should be able to select Maximum Minutes "<MaxMins>"
    Then I should be able to select Minimum Hours "<MinHours>"
    Then I should be able to select Minimum Minutes "<MinMins>"
    Then I should be able to click Create Generic button
    Then I should be able to validate the Min "<MinHours>" "<MinMins>" and Max "<MaxHours>" "<MaxMins>" Total Credit in Ballot "<ballotType1>" for "<title>"
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate the Min "<MinHours>" "<MinMins>" and Max "<MaxHours>" "<MaxMins>" Total Credit in Ballot "<ballotType1>" for "<title>"
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | ballotInput | sitTime | MaxHours | MaxMins | MinHours | MinMins | ballotType1 | ballotType2   | ballotType3 | title         | base |offsetfromtoday_startdt|offsetfromtoday_enddt|
      |       12345 |       5 |       10 |      30 |        7 |      20 | PickUpDOTC  | PickUpOutside | Template    | Test_US132948 | DFW  |1                      |10                   |
