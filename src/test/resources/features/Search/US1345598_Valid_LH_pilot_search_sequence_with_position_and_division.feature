Feature: US1345598 Search sequences with positioin and division, validate search sequence list.

 @Search @AA @US1345598 @Position&Division @GoLive
 Scenario Outline: Search sequence with positioin and division, validate search sequence list. Test Case Name:US1345598 Search sequence with positioin and division, validate search sequence list.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>" and equipment at "<equipment>" and position "<pos>" and division "<div>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to click on position and Division Link
    Then I should be able to click on Position plus
    Then I should be able to verify the position check boxes "<position>"
    Then I should be able to select Position "<position>" checkbox
    Then I should be able to click on Division plus
    Then I should be able to verify the Division check boxes "DOM,INT"
    Then I should be able to select Division "<division>" checkbox
    Then I should be able to click on Show Sequences
    Then I should validate "<position>" and "<division>" in all the sequences in show sequence list
    
    Examples:
    | base | equipment | pos | div | position | division |
    | DFW  | 787       | FO  | I   | FO 	  | INT		 |