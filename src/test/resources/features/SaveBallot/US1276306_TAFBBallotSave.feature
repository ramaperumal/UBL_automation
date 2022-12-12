Feature: US1276306 Search Feature
  As a developer, I need a smoke test created to test that when I choose to search seq based on specific legs per duty period then I get sequences which match my expected criteria

 @SaveBallot @Smoke @US1276306 @AA @Short @BA @TAFBBallotSave @Test
  Scenario Outline: DOTC Automation: Smoke Test: Valid LH pilot search by TAFB. Test Case Name:US1276306 Valid LH pilot search by TAFB and create ballot.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    When I clicked on the pilot name at the right top
    Then I Click on Ballots tab
    Then I delete all the user story from "<ballotType1>"
    Then I should be able to click Pick Up Outside link
    Then I delete all the user story from "<ballotType2>"
    Then I should be able to click Template link
    Then I delete all the user story from "<ballotType3>"
    Then I should be able to click on Search button
    Then I should be able to click Sequence Characteristics
    Then I should be able to click TAFB and choose "<min>" and "<max>" TAFB values
    Then I Clicked on Save Generic Criteria
    Then I should be able to enter the Search title
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to verify TAFB values "<min>" and "<max>" on the Saved Ballot
	Then I should be able to update "<ballotType3>" ballot
	#Then I verfy ballot creation in the ballot list
	Then I should be able to click Pick Up DOTC link
	Then I should be able to verify TAFB values "<min>" and "<max>" on the Saved Ballot
	
	
    Examples: 
      | base | min | max | ballotType1 | ballotType2   | ballotType3 |
      | DFW  |   3 | 100 | PickUpDOTC  | PickUpOutside | Template    |
