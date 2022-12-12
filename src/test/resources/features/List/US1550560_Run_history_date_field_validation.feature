Feature: US1550560 Run history date field validation

@List @AA @US1550560 @PostGoLive @History
Scenario Outline: Validate default start_date and end_date in history tab. Test Case Name: US1550560 Validate default start_date and end_date in history tab.
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should click on History tab
    Then I should validate History page
    And I should validate default values for start and end date
    
Examples:
	|base|
	| DFW|