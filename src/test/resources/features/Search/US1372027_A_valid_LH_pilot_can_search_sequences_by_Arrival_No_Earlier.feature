Feature: US1372027 Search sequences by Arrival No Earlier/Later than textbox and validate search result.

 @Search @AA @US1372027 @GoLive @arrivalNoEarlier
 Scenario Outline: Search sequences by Arrival No Earlier/Later than textbox and validate search result. Test Case Name:US1372027 Search sequences by Arrival No Earlier/Later than textbox.
 	Given pilotDOTC application has been Launched
 	When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I select the Arrive No Earlier Than dropdown "<arriveNOEarlier>"
    And I select the Arrive No Later Than dropdown "<arriveNOLater>"
    And I should be able to click on Show Sequences
    Then I should validate arriveNoEarlier "<arriveNOEarlier>" and arriveNoLater "<arriveNOLater>" in search results
    
    Examples:
    | base | arriveNOEarlier | arriveNOLater |
    | DFW  | 09:00  		 | 18:00 		 |
