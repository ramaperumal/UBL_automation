Feature: US1345585 Position Division Ballot Feature
  As a developer, I need a Smoke test that saves ballots with Position and Division.

 @SaveBallot @Smoke @MN @AA @US1345585 @Medium  @Position @Division
  Scenario Outline: Smoke test that saves ballots with Position and Division. Test Case Name:US1345585 Validate if the ballots can be saved with Position and Division.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>" and equipment at "<equipment>" and position "<pos>" and division "<div>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to click on position and Division Link
    Then I should be able to click on Position plus
    Then I should be able to verify the position check boxes "<pos>"
    Then I should be able to select Position "<position>" checkbox
    Then I should be able to click on Division plus
    Then I should be able to verify the Division check boxes "DOM,INT"
    Then I should be able to select Division "<division>" checkbox
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Position "<position>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Division "<division>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Position "<position>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Division "<division>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Position "<position>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Division "<division>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate Position "<position>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Division "<division>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Position "<position>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Division "<division>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Position "<position>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Division "<division>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | equipment | pos | div | title          | ballotType1 | ballotType2   | ballotType3 | position | division |
      | DFW  |       787 | CA  | I   | Test_US1345585 | PickUpDOTC  | PickUpOutside | Template    | CA       | INT      |
