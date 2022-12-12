Feature: US1276273 Searching by sit time, will return list of sequences within the sit time provided

@Search @AA @US1276273 @Sittime @GoLive
Scenario Outline: Searching by sit time will return list of sequences within the sit time provided. Test Case Name:US1276273 Searching by sit time will return list of sequences within the sit time provided.
	Given pilotDOTC application has been Launched
	When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to click Sequence Characteristics
    And I should be able to click Sit Time Plus
    Then I should be able to Select Value "<sitTime>" in Sit Time dropdown
    Then I should be able to click on Show Sequences
    Then I should be able to Verify Sequence results for sit time "<sitTime>"
    
    Examples:
    | base | sitTime |
    | DFW  | 2       |