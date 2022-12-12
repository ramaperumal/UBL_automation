## Inclusive Timeframe feature not available 
#Feature: US1275092 Inclusive Time frame Validation in Search
#  As a developer, I need a smoke test created to test that inclusive timeframe generic requests can be saved to ballot.
#
#  @InclusiveTimeframe @SCHDontInclude @US1275092 @Medium
#  Scenario Outline: Validate PilotDOTC search is successful when Inclusive Timeframe. Test Case Name:Verify if Inclusive Timeframe is selected as search criteria. Priority: Useful. Risk: Medium.
#    Given pilotDOTC application has been Launched
#    #When I login with the pilot ID: "<employeeID>"
#    When I login with the a LH pilot base at "<base>"
#    Then I am at the DOTC/RAS landing page
#    Then I Click on Ballots tab
#    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
#    Then I should be able to click Pick Up Outside link
#    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
#    Then I should be able to click Template link
#    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
#    Then I should be able to click on Search button
#    And I should be able to access Search page
#    Then I Enter Departure and Arrival dates
#    Then I should be able to click Inclusibe Timeframe checkbox
#    Then I should see Departure To and Arrival To Date fields are disabled
#    Then I should be able to click Save Generic Criteria button
#    Then I should be able to enter the Search title based on User story Number "<title>"
#    Then I should be able to check All Ballots check box
#    And I should be able to Save the criteria
#    Then I should able to validate Inclusive Timeframe for "<ballotType1>" "<title>"
#    Then I should be able to click Pick Up Outside link
#    Then I should able to validate Inclusive Timeframe for "<ballotType2>" "<title>"
#    Then I should be able to click Template link
#    Then I should able to validate Inclusive Timeframe for "<ballotType3>" "<title>"
#    Then I should be able to update ballot
#    Then I should be able to click Pick Up DOTC link
#    Then I should able to validate Inclusive Timeframe for "<ballotType1>" "<title>"
#    Then I should be able to click Pick Up Outside link
#    Then I should able to validate Inclusive Timeframe for "<ballotType2>" "<title>"
#    Then I should be able to click Template link
#    Then I should able to validate Inclusive Timeframe for "<ballotType3>" "<title>"
#    Then I should be able to click Pick Up DOTC link
#    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
#    Then I should be able to click Pick Up Outside link
#    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
#    Then I should be able to click Template link
#    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
#
#    Examples: 
#      | base | ballotType1 | ballotType2   | ballotType3 | title          |
#      | DFW  | PickUpDOTC  | PickUpOutside | Template    | Test_US1275092 |
  
