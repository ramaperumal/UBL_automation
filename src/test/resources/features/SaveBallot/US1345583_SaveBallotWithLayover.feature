Feature: US1345583 Layover Ballot Feature
  As a PilotDOTC user, i should be able to Save search Criteria as Layover successfully

 @SaveBallot @Smoke @SCH @US1345583 @Layover  @AA @Medium
  Scenario Outline: Validate PilotDOTC search is successful. Test Case Name:US1345583 Save Generic criteria as Layovers. Priority: Useful. Risk: Medium.
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
    Then I should be able to click Layover Plus
    Then I should be able to enter station code in Layover include textbox "<includeValue>"
    Then I should be able to enter station code in Layover exclude textbox "<excludeValue>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Layover values "<includeValue>" "<excludeValue>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Layover values "<includeValue>" "<excludeValue>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Layover values "<includeValue>" "<excludeValue>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate Layover values "<includeValue>" "<excludeValue>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Layover values "<includeValue>" "<excludeValue>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Layover values "<includeValue>" "<excludeValue>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | includeValue | excludeValue | ballotType1 | ballotType2   | ballotType3 | title          | base |
      | DFW,PHL      | MIA          | PickUpDOTC  | PickUpOutside | Template    | Test_US1345583 |  DFW |
      | DFW          | MIA,DAL      | PickUpDOTC  | PickUpOutside | Template    | Test_US1345583 |  DFW |
