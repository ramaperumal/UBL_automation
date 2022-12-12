Feature: US1278870 Search Feature
  As a PilotDOTC user, i should be able to Save search Criteria as Sit Time successfully

 @Ballot @Smoke @SCH @US1278870 @Sittime @AA @Medium
  Scenario Outline: Validate PilotDOTC search is successful. Test Case Name:US1278870 Save Generic criteria as Sit Time. Priority: Useful. Risk: Medium.
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
    Then I verfy ballot creation in the ballot list
    Then I should be able to click Pick Up DOTC link
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
      | base | sitTime | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  |       2 | PickUpDOTC  | PickUpOutside | Template    | Test_US1278870 |
     
