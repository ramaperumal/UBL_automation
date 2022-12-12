Feature: US1278923 Total Credit Validation in Sequence
  As a PilotDOTC user, I need a smoke test that sequences can be searched by min and max total credit

  @Search @TotalCredit @SCH @US1278923 @AA @Medium @Smoke
  Scenario Outline: Validate LH Pilot can search Sequence with Total Credit. Test Case Name:US1278923 Verify Total Credit value in Sequence. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to click Pay and Credit Down Arrow
    Then I should be able to click Total Credit Plus
    Then I should be able to select Maximum Hours "<MaxHours>"
    Then I should be able to select Maximum Minutes "<MaxMins>"
    Then I should be able to select Minimum Hours "<MinHours>"
    Then I should be able to select Minimum Minutes "<MinMins>"
    Then I should be able to click on Show Sequences
    Then I should be able to verify sequence results for Total Credit between "<MinHours>""<MinMins>" and "<MaxHours>""<MaxMins>"

    Examples: 
      | base | MinHours | MinMins | MaxHours | MaxMins |
      | DFW  |        5 |      45 |       22 |      10 |
      | LGA  |        5 |       5 |       23 |      55 |
