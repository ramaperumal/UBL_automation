Feature: US1464706 Specific sequence number search should be allowed for crewed sequences

@Search @AA @US1464706 @Crewed @PostGoLive
Scenario Outline: Specific sequence number search should be allowed for crewed sequences. Test Case Name: US1464706 Verify specific sequence number search should be allowed for crewed sequences.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    Then I should be able to access Search page
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I should be able to click Sequence Number
    Then I should be able to click Sequence Number Plus
    Then I should get a crewed sequence number from service and add to text box
    Then I should click on checkbox "<base>"
    Then I should Check the Crewed Sequences checkbox
	Then I Clicked on Show sequence button
	Then I should be able to verify CrewedSeq Icon for these sequences
    
Examples:
	|base|offsetfromtoday_startdt|offsetfromtoday_enddt|
	| DFW|0					     |10				   |