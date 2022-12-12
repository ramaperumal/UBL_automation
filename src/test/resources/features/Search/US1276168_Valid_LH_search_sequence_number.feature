Feature: US1276168 Searching by specific sequence number that is valid will return that sequence number on the departure dates provided 
 
@Search @AA @Smoke @US1276168 @MMD @SingleDeparture @MultipleDeparture @DepartureStation @Short
Scenario Outline: 	Searching by specific sequence number that is valid will return that sequence number on the departure dates provided. Test Case Name:US1276168 Searching by specific sequence number that is valid will return that sequence number. 
	Given pilotDOTC application has been Launched
  When I login with the a LH pilot base at "<base>"
	Then If I search for specific existing sequence I should see in the result table
	And I should see single departure date sequences
	And I should see multiple departure date sequences 
	And I should see any departure station
	
	Examples: 
		|base|
		|DFW |

