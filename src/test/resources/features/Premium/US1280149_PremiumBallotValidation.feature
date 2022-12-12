Feature: US1280149 Premium Ballot Validation
  As a PilotDOTC user, I need a smoke test created to test that any ballot request can be marked with premium option.

  @Premium @AA @R2Iteration1 @SCH @US1280149 @Smoke @PremiumBallot
  Scenario Outline: Validate LH Pilot can save ballot with Premium.Test Case Name:US1280149 Verify Ballot can be saved with Premium. Priority: Useful. Risk: Medium.
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
    Then I should be able to expand Days Dates and Hours Panel
    Then I should be able to add Duty Periods in Search
    Then I should be able to select "<minValue>" and "<maxValue>" from Duty Periods Min and Max dropdown
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to click Opt link for "<ballotType1>" and "<title>"
    Then I should be able to click Premium checkbox
    Then I should be able to click Pick Up Outside link
    #Then I should be able to click Submit button in Pop Up
    Then I should be able to verify Premium symbol in ballot for Ballot Type "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to click Opt link for "<ballotType2>" and "<title>"
    Then I should be able to click Premium checkbox
    Then I should be able to click Template link
    #Then I should be able to click Submit button in Pop Up
    Then I should be able to verify Premium symbol in ballot for Ballot Type "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I should be able to click Opt link for "<ballotType3>" and "<title>"
    Then I should be able to click Premium checkbox
    Then I should be able to click Template link
    #Then I should be able to click Submit button in Pop Up
    Then I should be able to verify Premium symbol in ballot for Ballot Type "<ballotType3>" for "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I verfy ballot creation in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I should be able to verify Premium symbol in ballot for Ballot Type "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to verify Premium symbol in ballot for Ballot Type "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I should be able to verify Premium symbol in ballot for Ballot Type "<ballotType3>" for "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | minValue | maxValue | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  |        2 |        3 | PickUpDOTC  | PickUpOutside | Template    | Test_US1280149 |
