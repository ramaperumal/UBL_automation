Feature: US1398716 . Ballot:Generic request
As a pilot , when I save generic request . I should be able to see that No work hours are in right place.

@Ballot @AA @Smoke  @US1398716 @genericCriteria
Scenario Outline: Validating place of No work hhours in ballot page.Test Case Name: US1398716  No Work Hours misplaced on criteria list.
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
 Then I should be able to select No work Hours criteria
 Then I should be able to select No Work Hours from Min "<minhours>" and Max "<maxhours>" dropdown
  Then I should be able to click Pay and Credit Down Arrow
 Then I should be able to click Paid Credit Plus
  Then I should be able to select Paid Credit values "<maxHours>" "<maxMins>" "<minHours>" "<minMins>"
  Then I should be able to click Save Generic Criteria button
  Then I should be able to enter the Search title based on User story Number "<title>"
  Then I should be able to check All Ballots check box
  And I should be able to Save the criteria
  Then I should be able to update "<ballotType1>" ballot  
  Then I should be able to validate generic criteria order for "<ballotType1>" with title "<title>" 
  Then I should be able to click Pick Up Outside link
  Then I should be able to validate generic criteria order for "<ballotType2>" with title "<title>"
  Then I should be able to click Template link
  Then I should be able to validate generic criteria order for "<ballotType3>" with title "<title>"
  
   Examples: 
      | base   | minhours | maxhours | ballotType1 | ballotType2   | ballotType3 | title           |maxHours | maxMins | minHours | minMins |
      | DFW    | 02:00    | 10:30    | PickUpDOTC  | PickUpOutside | Template    | Test            |    10   |      10 |        6 |      35 |
     

