Feature: US1499835 Lists tab rename Date to Award Date.

 @AA @US1499835 @List @PostGoLive 
 Scenario Outline: List tab rename Date to Award Date. Test Case Name: US1499835 Validate if the fieldname is Award Date for Lists tab.
 	Given pilotDOTC application has been Launched
 	When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I clicked on List tab in landing page
    And I should validate if Award Date field is present
    
  Examples:
  |base|
  | DFW|