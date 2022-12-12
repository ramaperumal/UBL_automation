Feature: US1272281 Calendar Feature 
	As a PilotDOTC user, a valid lineholder pilot can emulate his ID successfully

@Calendar @Smoke @RSB @US1272281 @ValidateEmulate @AA @Short @US1358635
Scenario Outline: 	Validate Pilot name . Test Case Name:US1272281 Name validation. 
	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
	Then I verify the valid LH is emulated successfully
	
				
	Examples: 
		|base|
		|DFW|
							