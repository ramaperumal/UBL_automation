Feature: US1279731 Duty Period Search Sequence
  As a PilotDOTC user, I need a smoke test that sequences can be searched by Duty Periods


 @Search @Smoke @SCH @US1279731 @DutyPeriod @Medium @AA @minValue @maxValue
  Scenario Outline: Validate LH Pilot can search Sequence with Duty Periods.Test Case Name:US1279731 Verify Sequence can be Searched with min and max Duty Periods. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to expand Days Dates and Hours Panel
    Then I should be able to add Duty Periods in Search
    Then I should be able to select "<minValue>" and "<maxValue>" from Duty Periods Min and Max dropdown
    Then I Clicked on Show sequence button
    Then I should be able to verify sequence results for Duty Period between "<minValue>" and "<maxValue>"

    Examples: 
      | base | minValue | maxValue |
      | DFW  |        2 |        4 |
