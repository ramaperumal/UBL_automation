Feature: US1276261 Ballots Feature
  As a PilotDOTC user, i should be able to update ballots based on Departure and Arrival date range

  @Ballot @Smoke @RSB @US1276261 @US1276261_1 @AA @US1368453 @SequenceNumber @UpdateBallot @DepartureFrom @DepartureTo @Medium
  Scenario Outline: Validate update ballots is successful. Test Case Name:US1276261_1 To verify ballots update for Generic ballots request. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    When I Click on Ballots tab
    Then I am Inside the Ballots Screen
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    When I Clicked on Search tab
    Then I should be able to click Sequence Number
    And I should be able to click Sequence Number Plus
    Then I should be able to Enter Value in Sequence Number TextBox "<SequenceNumber>"
    And I should click on checkbox "<base>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to update "<ballotType1>" ballot
    Then I verfy ballot creation in the ballot list
    Then I verfy Generic ballot is updated successfully "<title>"

    Examples: 
      | base |title          | ballotType1 | ballotType2   | ballotType3 | SequenceNumber |
      | DFW  |Test_US1276261 | PickUpDOTC  | PickUpOutside | Template    |       12345    |

  @Ballot @Smoke @RSB @US1276261 @US1276261_2 @AA @US1368453 @SequenceList @UpdateBallot @DepartureFrom @DepartureTo @Medium
  Scenario Outline: Validate update ballots is successful. Test Case Name:US1276261_2 To verify ballots update for Specific ballots request. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    When I Click on Ballots tab
    Then I am Inside the Ballots Screen
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    When I Clicked on Search tab
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I Clicked on Show sequence button
    Then I Should be able to see the list of Sequence based on departure date range
    And  I check some "<howmanySeq>" top found sequences
    Then I Clicked on the save selected sequences button
    Then Select Pick Up DOTC checkbox from Add to Ballots
    Then Clicked on add button
    Then I should delete any duplicate ballots in ballot types "<ballotType1>"
    Then I should be able to update "<ballotType1>" ballot
    #Then I verfy Specific ballot is updated successfully
    Then I delete Pick Up DOTC Ballots from the Ballots list "<ballotType1>"

    Examples: 
      | base | title          | ballotType1 | ballotType2   | ballotType3 | howmanySeq|offsetfromtoday_startdt|offsetfromtoday_enddt|
      | DFW  | Test_US1276261 | PickUpDOTC  | PickUpOutside | Template    |		  3   |1                      |10                   |

  @Ballot @Smoke @RSB @US1276261 @US1276261_3 @AA @SequenceNumber @UpdateBallot @SequenceList @DepartureFrom @DepartureTo @US1368453 @Medium
  Scenario Outline: Validate update ballots is successful. Test Case Name:US1276261_3 To verify ballots update for both Generic and Specific ballots request. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    When I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I Clicked on Search tab
    Then I should be able to click Sequence Number
    And I should be able to click Sequence Number Plus
    Then I Enter Value in Sequence Number TextBox "<ballotInput>"
    And I should click on checkbox "<base>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I Select Pick Up DOTC checkbox
    Then I Clicked on Save button
    Then I should be able to update "<ballotType1>" ballot
    When I Clicked on Search tab
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I Clicked on Show sequence button
    Then I Should be able to see the list of Sequence based on departure date range
    And  I check some "<howmanySeq>" top found sequences
    Then I Clicked on the save selected sequences button
    Then Select Pick Up DOTC checkbox from Add to Ballots
    Then Clicked on add button
    Then I should delete any duplicate ballots in ballot types "<ballotType1>"
    Then I should be able to update "<ballotType1>" ballot
    Then I verfy both Generic and Specific ballot is updated successfully "<title>"

    Examples: 
      | base | ballotInput | title          | ballotType1 | ballotType2   | ballotType3 | howmanySeq|offsetfromtoday_startdt|offsetfromtoday_enddt|
      | DFW  |       12345 | Test_US1276261 | PickUpDOTC  | PickUpOutside | Template    |      	1   |1                      |10                   |
