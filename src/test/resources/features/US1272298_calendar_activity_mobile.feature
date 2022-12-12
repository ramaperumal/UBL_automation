#Feature: US1272298 Calendar-Mobile Feature
#		
#@Smoke @MMD @US1272298_1  @iPhone @CalendarPageElementCheck
#Scenario Outline: LH sees schedule. Test Case Name:LH_see_schedule. Priority: Useful. Risk: Medium.
#    Given pilotDOTC application has been Launched at env "<env>" with subdomain "<subDomain>"
#    When I do SSO login as "<role>" at env "<env>"
#    When  I login with the pilot ID: "<employeeID>"
#    Then I should be able to see current month and year
#    And I should be able to see the calendar grid
#    And Pickup DOTC and Pickup Outside DOTC ballot selections appear
#
#Examples:
#      |env  |subDomain | role  | employeeID|
# 			|uat  |          |itAdmin| 3993 |
# 			
# 					
#@Schedule @Smoke @MMD @US1272298_2  @Short
#Scenario Outline: LH sees schedule. Test Case Name:LH_see_schedule. Priority: Useful. Risk: Medium.
#    Given pilotDOTC application has been Launched
#    When I login with the a LH pilot base at "<base>"
#    Then I should be able to see current month and year
#    And I should be able to see the calendar grid
#    And Pickup DOTC and Pickup Outside DOTC ballot selections appear
#
#Examples:
#      |base|                                           
#      |BOS|
#      |DFW|