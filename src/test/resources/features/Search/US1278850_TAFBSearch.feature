Feature: US1278850 Search Feature
  As a developer, I need a smoke test created to test that when I choose to search seq based on specific legs per duty period then I get sequences which match my expected criteria

  @Search @Smoke @US1278850 @AA @Short @BA @TAFBBallotSearch @Test
  Scenario Outline: DOTC Automation: Smoke Test: Valid LH pilot search by TAFB. Test Case Name:US1278850 Valid LH pilot search by TAFB.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    When I clicked on the pilot name at the right top
    Then I should be able to click on Search button
    Then I should be able to click Sequence Characteristics
    Then I should be able to click TAFB and choose "<min>" and "<max>" TAFB values
    Then I should be able to click on Show Sequences
    Then I should be able to Verify Sequence results for TAFB  "<min>" and "<max>" range

    Examples: 
      | base | min | max |
      | DFW  |   3 | 100 |
