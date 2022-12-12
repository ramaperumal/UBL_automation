Feature: US1272303 Calendar Feature
  As a PilotDOTC user, a valid lineholder pilot can click on their name to view My Information

 @Calendar @Smoke @AA @RSB @US1272303 @US1272303_1 @MyInfo @CurrentmonthInfo @Medium @US1358635
  Scenario Outline: Validate my information. Test Case Name:US1272303_1 My information validation.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I verify the current date is the first day of the month
    When I clicked on the pilot name at the right top
    Then I should be able to see my information Popup
    Then I shuld be able to see my information and Qualification
    Then I validate pilots information for the Current Contractual month

    Examples: 
      | base |
      | DFW  |

 @Calendar  @Smoke @AA @RSB @US1272303 @US1272303_2 @MyInfo @PreviousorNextmonthInfo @Medium @US1358635
  Scenario Outline: Validate my information. Test Case Name:US1272303_2 My information validation for next month.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I clicked on Calendar previous/next arrow
    When I clicked on the pilot name at the right top
    Then I should be able to see my information Popup
    Then I shuld be able to see my information and Qualification
    And I validate pilots information for previous/next contractual month

    Examples: 
      | base |
      | DFW  |
