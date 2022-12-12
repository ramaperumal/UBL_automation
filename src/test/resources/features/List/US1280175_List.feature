Feature: US1280175 List Feature
  As a PilotDOTC user, i should be able to see the list of pilots who have Pick Up DOTC On in the ballot list

  @List @Smoke @RSB @US1280175 @US1280175_1 @AA @ListTab @PickUpDOTCTurnOn @US1368453 @Long
  Scenario Outline: Validate Pilots is seen in the List. Test Case Name:US1280175_1 To verify Pilots are seen in Pick Up DOTC List who turn on the ballots. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot having no Calander Activities today with base at: "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    When I Clicked on Search tab
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I Select Pick Up DOTC checkbox
    Then I Clicked on Save button
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I should be able to update "<ballotType1>" ballot  
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I turn On the Pick Up DOTC button from ballots list in Ballots Screen
    Then I clicked on List tab in landing page
    Then I clicked on Submit button
    Then I Verify that the pilot is seen in Pick Up DOTC list when ballots are turned On
    Then I Click on Ballots tab
    Then I turn Off the Pick Up DOTC button from ballots list in Ballots Screen
    Then I clicked on List tab in landing page
    Then I clicked on Submit button
    Then I Verify that the pilot is not seen in Pick Up DOTC list when ballots are turned Off
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | title          |offsetfromtoday_startdt|offsetfromtoday_enddt|
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | Test_US1280175 |1                      |10                   |

  @List @Smoke @RSB @US1280175 @US1280175_2 @AA @US1368453 @ListTab @PickUpDOTCTurnOn @LogOut @Long
  Scenario Outline: Validate Pilots is seen in the List. Test Case Name:US1280175_2 To verify Pilots are seen in Pick Up DOTC List who turn on the ballots. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot having no Calander Activities today with base at: "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    When I Clicked on Search tab
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I Select Pick Up DOTC checkbox
    Then I Clicked on Save button
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I should be able to update "<ballotType1>" ballot  
    Then I should be able to validate ballot is created for Ballot Type "<ballotType1>" with "<title>"
    Then I turn On the Pick Up DOTC button from ballots list in Ballots Screen
    Then I should click on stop emulate tab
    When I login with the a LH pilot having no Calander Activities today with base at: "<base>" at "<index>"
    Then I clicked on List tab in landing page
    Then I clicked on Submit button
    Then I Verify that the old pilot id is seen in Pick Up DOTC list when ballots are turned On
    
    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | title          | index |offsetfromtoday_startdt|offsetfromtoday_enddt|
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | Test_US1280175 |     1 |1                      |10                   |
