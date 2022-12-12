Feature:  US1391557
As a Pilot , when I go to PickUp Outside ballot there's to departure and default notification time already set.

@Ballot @Smoke @AA @US1391557 @NotificationTimeCheck @postGoLive 
   Scenario Outline:Validate notification time.Test Case Name:US1391557 Modify "Notification Time".
   Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab 
    Then I delete all the user story from "<ballotType1>" 
    Then I should be able to click Pick Up Outside link
    Then I delete all the user story from "<ballotType2>"
    Then I should be able to click Template link
    Then I delete all the user story from "<ballotType3>"
     Then I Clicked on Search tab 
    And I should be able to access Search page
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
     And I should be able to Save the criteria
    Then I should be able to update "<ballotType1>" ballot
     Then I should be able to click Pick Up Outside link
     And I should be able to click on the notification time slider
     Then I should be able to verify notification time sub text
    Then I should be able to verify default hour value
    And I should be able to verify default minutes value
     
     
      Examples: 
      | base | minValue | maxValue | title          | ballotInput | ballotType1 | ballotType2   | ballotType3 |
      | DFW  |        2 |        3 | Test_US1329471 |       12345 | PickUpDOTC  | PickUpOutside | Template    |    
    
    