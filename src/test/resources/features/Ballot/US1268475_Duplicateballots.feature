Feature: US1268475 Duplicate Ballot Feature
  As a PilotDOTC user, i should be able to check duplicate Ballots.

  @Ballot @Smoke @RSB @US1268475 @DuplicateBallot @BallotType @AA @Medium @DeleteBallot @US1358635
  Scenario Outline: Validate Duplicate ballots. Test Case Name:US1268475 Ballot_screen ballots create Duplicate Ballot. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I should be able to click on Search button
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I Clicked on Save Generic Criteria
    Then I should be able to enter the Search title based on User story Number "<title>"    
    Then I should be able to check All Ballots check box
    Then I Clicked on Save button
    Then I should be able to update "<ballotType1>" ballot      
    Then I should be able to click on Search button
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I Clicked on Save Generic Criteria
    Then I should be able to enter the Search title based on User story Number "<title>" 
    Then I Select All Ballots checkbox
    Then I Clicked on Save button
    Then I verify Duplicate ballots in the ballot list
    Then I should be able to click Pick Up Outside link
    Then I verify Duplicate ballots in the ballot list
    Then I should be able to click Template link
    Then I verify Duplicate ballots in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    
	Examples: 
		|base|ballotType1 |ballotType2   |ballotType3 |title 		 |offsetfromtoday_startdt|offsetfromtoday_enddt|
		|DFW |PickUpDOTC  |PickUpOutside |Template    |Test_US1268475|1                      |10                   |
