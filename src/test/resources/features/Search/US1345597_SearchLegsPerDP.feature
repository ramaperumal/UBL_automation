Feature: US1345597 Legs Per DP Search Feature
  As a developer, I need a smoke test created to test that when I choose to search seq based on specific legs per duty period then I get sequences which match my expected criteria

  @Search @Smoke @US1345597 @AA @Short @BA @LegsPerDP @Test
  Scenario Outline: DOTC Automation: Smoke Test:Validate when the pilot searches for a specific Legs Per Duty Period returned sequences match the input criteria. Test Case Name:US1345597 Searches for a specific Legs Per Duty Period.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    Then I enter Dep Date and Arrival Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I should be able to click Sequence Characteristics
    Then I should be able to click on Legs Per DP
    Then I should be able to enter the range for Legs Per DP "<minLegsPerDP>" "<maxLegsPerDP>"
    Then I should be able to click on Show Sequences
    Then I should be able to Verify Sequence results for LegsPerDP "<minLegsPerDP>" and "<maxLegsPerDP>" range

    Examples: 
  | minLegsPerDP | maxLegsPerDP | offsetfromtoday_startdt | offsetfromtoday_enddt | base |
  |            1 |            4 |                       2 |                     4 | DFW  |
