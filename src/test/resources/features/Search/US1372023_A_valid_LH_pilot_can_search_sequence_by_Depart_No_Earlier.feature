Feature: US1274743 Search sequence by Depart No Earlier/Later than and validate the search sequence results

 @Search @AA @US1372023  @GoLive @departNoEarlier
 Scenario Outline: Search sequence by Depart No Earlier/Later than and validate the search sequence results. Test Case Name:US1372023 Search sequence by Depart No Earlier/Later than.
 	Given pilotDOTC application has been Launched
 	When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I select the Depart No Earlier Than dropdown "<departNOEarlier>"
    And I select the Depart No Later Than dropdown "<departNOLater>"
    And I should be able to click on Show Sequences
    Then I should validate departNoEarlier "<departNOEarlier>" and departNoLater "<departNOLater>" in search results
    
    Examples:
    | base | departNOEarlier | departNOLater |
    | DFW  | 09:00  		 | 18:00 		 |
    
    