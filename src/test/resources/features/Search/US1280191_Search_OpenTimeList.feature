Feature: Search Sequence
  As a PilotDOTC user, I need a smoke test that all open sequences will return for that date and 4 part bid status

@Search @AA @Smoke @RSB @US1280191 
Scenario Outline: Validate LH Pilot can see all open Sequence. Test Case Name:US1280191 Verify all open sequences will return for that date and 4 part bid status. Priority: Useful. Risk: Medium.
	Given pilotDOTC application has been Launched
	When I login with the a LH pilot of same BidStatus base at: "<base>" 
    Then I am at the DOTC/RAS landing page
	When I Clicked on Search tab 
	Then I Enter Departure from/departure to date
	Then I Clicked on Show sequence button
	Then I Should be able to see the list of Sequence based on departure date range
	Then I should be able to verify sequence results for that date and Bid status 
	Then I should click on stop emulate tab	
	When I login with the a another LH pilot of same BidStatus 
	When I Clicked on Search tab 
	Then I Enter Departure from/departure to date
	Then I Clicked on Show sequence button
	Then I verify all the open sequences are viewed for that date and Four Part BidStatus 

Examples: 
		|base|
		|DFW |
			