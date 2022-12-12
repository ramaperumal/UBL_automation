Feature: US1274720 Calendar Feature 
	As a PilotDOTC user, a valid lineholder pilot can click previous and next Calander activity

#@Smoke @YBA @US1274720 
#Scenario Outline: 	Validate Calendar Current month. Test Case Name:Month info. Priority: Useful. Risk: Medium. 
#	Given pilotDOTC application has been Launched
#    #	When I login with the a LH pilot base at "<base>"
#	When I login with the pilot ID: "<employeeID>"
#	Then I am at the DOTC/RAS landing page
#	Then I validate current calander contractual month
#
##	Examples: 
##		|base|
##		|DFW |

@Calendar @Smoke @RSB @US1274720  @ContracualMonth @AA @CalenderArrowVAlidation @US1358635 @Long 
Scenario Outline: 	Validate Calendar Previous and next month. Test Case Name:US1274720 Month info. Priority: Useful. Risk: Medium. 
	Given pilotDOTC application has been Launched
  When I login with the a LH pilot base at "<base>"
	Then I am at the DOTC/RAS landing page
	Then I clicked on Calendar previous/next arrow
	Then I validate calendar previous/next contractual month
	
	Examples: 
		|base|
		|MIA |

				