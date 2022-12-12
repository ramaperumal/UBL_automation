# Debrief feature not available
Feature: Calendar Feature 
	As a PilotDOTC user, i should be able to check Pilots debrief time..
 
 @Regression @US1268416
 Scenario Outline: 	Validate Pilot Deprecated time. Test Case Name:US1268416 Pilots deprecated time. Priority: Useful. Risk: Medium. 
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
	Then I am at the DOTC/RAS landing page
	When I clicked on the given sequence number
	Then I am at the sequence page
	And I validates the pilots debrief times
	
	Examples:
	|base|