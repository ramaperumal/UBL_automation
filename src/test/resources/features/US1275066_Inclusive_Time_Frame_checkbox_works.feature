## Inclusive Timeframe feature not available 
#Feature: US1275066 Inclusive Time frame Validation in Search As a developer, I need a smoke test created to test that inclusive timeframe generic requests can be saved to ballot.
#
#   @InclusiveTimeFrame @Smoke @MMDontInclude @US1275066 @Short 
#  Scenario Outline: Validate PilotDOTC search is successful when Inclusive Timeframe is on or off. Test Case 
#  Name:Verify some sequences will be exlcude when inclusive box is off. Priority: Useful. Risk: Medium.
#    Given pilotDOTC application has been Launched
##    When I login with the pilot ID: "<employeeID>"
#    When I login with the a LH pilot base at "<base>"
#	  Then I want to be able to see the difference that incluse time frame checkbox makes at sequences result
#
#    Examples: 
#    |base|employeeID|  
# 	|DFW |     10038|