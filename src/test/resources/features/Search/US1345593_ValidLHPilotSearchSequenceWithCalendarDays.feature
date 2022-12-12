Feature: US1345593 Calendar Days Search Feature
  As a PilotDOTC user, As a developer, I need a smoke test that searches sequences with Calendar Days

 @Search @Smoke @MN @US1345593 @Short @AA @CalendarDays
  Scenario Outline: Smoke test that searches sequences with Calendar Days. Test Case Name:US1345593 Validate the Sequence Calendar days with MinCalendar and MaxCalendar.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to expand Days Dates and Hours Panel
    Then I should be able to click Caldendar Days Plus
    Then I should be able to select Calendar Days from drop down "<minCalendar>" and "<maxCalendar>"
    Then I should be able to click on Show Sequences
    Then I should be able to validate Calendar Days with "<minCalendar>" and "<maxCalendar>"

    Examples: 
      | minCalendar | maxCalendar | base |
      |           1 |           5 | DFW  |
