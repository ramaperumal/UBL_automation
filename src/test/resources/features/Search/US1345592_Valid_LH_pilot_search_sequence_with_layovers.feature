Feature: US1345592 Seach sequences using layover stations

@Search @AA @US1345592_1 @US1345592 @LayoverStation  @GoLive 
Scenario Outline: Search sequences using layover stations and validate if sequences have layover stations within the provided list. Test Case Name:US1345592_1 Search sequences using layover stations and validate if sequences have layover stations with differnt include and exclude station.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    And I should get Layover stations from service for "include,exclude"
    Then I should be able to click on Search button
    And I should be able to access Search page
    And Set the departure from date according to layover service result
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click Layover Plus
    Then I should be able to enter station code in Layover include textbox
    Then I should be able to enter station code in Layover exclude textbox
    Then I should be able to click on Show Sequences
    Then I should validate layover station for sequences are according to include and exclude station
    
    Examples:
    | base | 
    | MIA  |
    
@AA @US1345592_2 @US1345592 @LayoverStation @Search @GoLive
Scenario Outline: Search sequences using layover stations and validate if sequences have layover stations within the provided list. Test Case Name:US1345592_2 Search sequences using layover stations and validate if sequences have layover stations with only exclude station.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    And I should get Layover stations from service for "exclude"
    Then I should be able to click on Search button
    And I should be able to access Search page
    And Set the departure from date according to layover service result
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click Layover Plus
    Then I should be able to enter station code in Layover exclude textbox
    Then I should be able to click on Show Sequences
    Then I should validate layover station for sequences are according to include and exclude station
    
    Examples:
    | base | 
    | DFW  |
    
@AA @US1345592_3 @US1345592 @LayoverStation @Search @GoLive
Scenario Outline: Search sequences using layover stations and validate if sequences have layover stations within the provided list. Test Case Name:US1345592_3 Search sequences using layover stations and validate if sequences have layover stations with only include station.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    And I should get Layover stations from service for "include"
    Then I should be able to click on Search button
    And I should be able to access Search page
    And Set the departure from date according to layover service result
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click Layover Plus
    Then I should be able to enter station code in Layover include textbox
    Then I should be able to click on Show Sequences
    Then I should validate layover station for sequences are according to include and exclude station
    
    Examples:
    | base | 
    | DFW  |
    
@AA @US1345592_4 @US1345592 @LayoverStation @Search @GoLive
Scenario Outline: Search sequences using layover stations and validate if sequences have layover stations within the provided list. Test Case Name:US1345592_4 Search sequences using layover stations and validate if sequences have layover stations with same include and exclude station.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    And I should get Layover stations from service for "include"
    Then I should be able to click on Search button
    And I should be able to access Search page
    And Set the departure from date according to layover service result
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click Layover Plus
    Then I should be able to enter same station code in Layover include and exclude textbox
    Then I should be able to click on Show Sequences
    Then I should validate layover station for sequences are according to include and exclude station
    
    Examples:
    | base | 
    | DFW  |