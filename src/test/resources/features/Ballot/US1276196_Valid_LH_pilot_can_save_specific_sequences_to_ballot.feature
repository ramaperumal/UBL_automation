Feature: US1276196 Valid LH pilot can save specific sequences to ballot

@Ballot @saveSpecificSequences2ballot @AA @Smoke @MMD @US1276196 @US1276196_2 @itAdmin @uat @737 @pickUpDOTC @pickUpOutside @LH
  Scenario Outline: US1276196 Valid LH pilot can save specific sequences to ballot. Test Case Name:US1276196 LH pilot can save specific sequences to ballot.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
	Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
	Then I should be able to click on Show Sequences
	And  I check some "<howmanySeq>" top found sequences
	And I should click on save selected sequences button
	Then I should select ballot types "<ballotTypes>"
	And I should click on add button
	Then I should delete any duplicate ballots in ballot types "<ballotTypes>"
	Then I should be able to update "PickUpOutside" ballot 
	Then I verfy ballot creation in the ballot list
	
    Examples: 
    | base |equipment |   preTodayCount | postTodayCount |howmanySeq |ballotTypes             |offsetfromtoday_startdt|offsetfromtoday_enddt|
 	| DFW  |    737   |   10            |     10         | 1         |PickUpDOTC,PickUpOutside|1                      |10                   |
 		
 		