Feature: US1473830 Validate No Work Hours dropdown range is from 00:00 to 23:59.

 @Search @AA @US1473830 @noWorkHours @PostGoLive
 Scenario Outline: Validate No Work Hours dropdown range is from 00:00 to 23:59. Test Case Name: US1473830 Validate that No Work Hours dropdown range is from 00:00 to 23:59.
 	Given pilotDOTC application has been Launched
 	When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    Then I should be able to access Search page
    Then I should be able to expand Days Dates and Hours Panel
    Then I should be able to select No work Hours criteria
    Then I should validate range for min and max of No Work Hours
    
  Examples:
  |base|
  | DFW|