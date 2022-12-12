 Feature:  US1483705 
 As a Pilot , when I edit ballot will see the update generic instead of create generic button and cancel button will take me to ballot page back. 
 
  @Ballot  @Smoke @AA  @US1483705  @postGoLive @UpdateGenericButton 
   Scenario Outline: Validate Update generic button .  Test Case Name: US1483705 Change "Create Generic" to "Update Generic" when editing a Generic request.
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
    Then I should be able to update "<ballotType1>" ballot
    Then I should be able to Edit the request for ballottype "<ballotType1>" "<title>"
    Then I should be able to verify update generic button
    Then I should be able to click on cancel update button
    Then I should be able to click Pick Up Outside link
    Then I should be able to Edit the request for ballottype "<ballotType2>" "<title>"
    Then I should be able to verify update generic button
    Then I should be able to click on cancel update button
    Then I should be able to click Template link
    Then I should be able to Edit the request for ballottype "<ballotType3>" "<title>"
    Then I should be able to verify update generic button
    Then I should be able to click on cancel update button
    
      Examples: 
      | base | minValue | maxValue | title          | ballotInput | ballotType1 | ballotType2   | ballotType3 |
      | DFW  |        2 |        3 | Test_US1329471 |       12345 | PickUpDOTC  | PickUpOutside | Template    |
     