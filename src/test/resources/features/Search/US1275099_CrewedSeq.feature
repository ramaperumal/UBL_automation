Feature: US1275099 Calendar Feature As a PilotDOTC user, a valid lineholder pilot can click on their name to view My Information

 @Search @AA @Smoke @BA @US1275099 @CrewedSeq
  	Scenario Outline: DOTC Automation: Smoke Test: Valid LH pilot can see sequence search results with Premium Property. Test Case Name:US1275099 Can see sequence search results with Premium Property.
     Given pilotDOTC application has been Launched
     When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    When I clicked on the pilot name at the right top
    Then I should be able to click on Search button 
    Then I should be able to click Sequence Characteristics
    Then I Enter Departure from/departure to date
    Then I should Check the Crewed Sequences checkbox
	Then I Clicked on Show sequence button
	Then I should be able to verify CrewedSeq Icon for these sequences
    
    Examples:
    |base|min|max|
    |PHL |44  |49|
    |DFW |44  |49|