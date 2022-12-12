 Feature: US1483995 
 As a Pilot , when I modify ballot will be able to see popup 
 
    @Ballot @Smoke @AA @US1483995 @popUpCheck  @postGoLive 
    Scenario Outline: Validate unsaved changes pop-up .  Test Case Name:US1483995 Modify "Are you sure?" pop up for save.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I Clicked on Search tab
    Then I should be able to expand Days Dates and Hours Panel
    Then I should be able to add Duty Periods in Search
    Then I should be able to select "<minValue>" and "<maxValue>" from Duty Periods Min and Max dropdown
    Then I Clicked on Save Generic Criteria
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I click on cancel button for "<ballotType1>"
    And I verify the unsaved changes pop up
    Then I click on close icon 
   Then I should be able to click Pick Up Outside link
    Then I click on cancel button for "<ballotType2>"
    And I verify the unsaved changes pop up
    Then I click on close icon 
    Then I should be able to click Template link
    Then I click on cancel button for "<ballotType3>"
    And I verify the unsaved changes pop up
    Then I click on close icon 
  Then I should be able to update "<ballotType3>" ballot
    
  Examples: 
      | base | minValue | maxValue | title          | ballotInput | ballotType1 | ballotType2   | ballotType3 |
      | DFW  |        2 |        3 | Test_US1329471 |       12345 | PickUpDOTC  | PickUpOutside | Template    |       