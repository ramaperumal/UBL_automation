Feature: Login Feature As a PilotDOTC user, i should be able to login to PilotDOTC UI successfully

@Calendar @Smoke @SampleDemo @colorCodeValidation @DFP @AA
Scenario Outline: Validate PilotDOTC login is successful. Test Case Name:DFP HomeScreen_PilotTTS_LogIn_Verify_Color_Code. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with a LH pilot having sequence activity today of bidstatus "<BidStatus>"
    Then I am at the DOTC/RAS landing page
    And I should be able to verify sequence color code in calendar
    And I assign DFP for LH on current day
    And I assign absence for LH on current day
    And I assign vacation for LH on next day
    When I refresh DOTC home
    Then I should be able to validate DFP color code in calendar
    Then I should be able to validate absence color code in calendar
    Then I should be able to validate credited removal color code in calendar
    Then I should be able to validate vacation color code in calendar

Examples:
		|BidStatus|
		|DFW/320/CA/I |

 
@Calendar @Health @AA
Scenario Outline: LH sees schedule. Test Case Name:LH_see_schedule. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I should be able to see current month and year
    And I should be able to see the calendar grid
    And Pickup DOTC and Pickup Outside DOTC ballot selections appear

Examples:
      |base|                                           
      |BOS|
      
 @Calendar @colorCodeValidation @RAP @AA
Scenario Outline: Validate the Color Activty for Reserve Pilot. Test Case Name:RAP Validate the calendar color activity for Reserve pilot.Priority: Useful. Risk: Medium.
	Given pilotDOTC application has been Launched
	When I assign RAP activity for "current" date to "<reserve_pilot>" of "<base>"
	And I assign training activity for "current" date to "<reserve_pilot>" of "<base>"
	Then I login with reserve pilot for "<reserve_pilot>"
	Then I am at the DOTC/RAS landing page
	Then I validate color of RAP activity at "current" date 
	Then I validate color of Training activity at "current" date
	 
Examples: 
      | base |reserve_pilot|
      | DFW  |            0|     