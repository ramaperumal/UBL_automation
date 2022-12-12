Feature: US1279732 Saving Min and Max Duty Period
  As a developer, I need a smoke test that requests with min and max Duty Periods can be saved to ballot as a generic request.

  @Ballot @Smoke @DutyPeriods  @SCH @US1279732 @AA @Medium
  Scenario Outline: Validate PilotDOTC search is successful when Duty Periods is selected. Test Case Name:US1279732 Verify when Duty Periods is selected as criteria in Search. Priority: Useful. Risk: Medium.
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
    Then I should be able to validate Minimun and Maximum Duty Periods selected "<minValue>" "<maxValue>" for "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Minimun and Maximum Duty Periods selected "<minValue>" "<maxValue>" for "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Minimun and Maximum Duty Periods selected "<minValue>" "<maxValue>" for "<ballotType3>" "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I verfy ballot creation in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate Minimun and Maximum Duty Periods selected "<minValue>" "<maxValue>" for "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Minimun and Maximum Duty Periods selected "<minValue>" "<maxValue>" for "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Minimun and Maximum Duty Periods selected "<minValue>" "<maxValue>" for "<ballotType3>" "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | minValue | maxValue | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  |        2 |        3 | PickUpDOTC  | PickUpOutside | Template    | Test_US1279732 |
   
