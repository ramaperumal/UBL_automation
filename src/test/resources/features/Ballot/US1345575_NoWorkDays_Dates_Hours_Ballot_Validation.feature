Feature: US1345575 No Work Dates and No Work Days Search Feature
  As a PilotDOTC user, i should be able to Save search Criteria with No Work Days, No Work Dates and No Work Hours successfully

 @Ballot @Smoke @SCH @US1345575 @NoWorkDays @NoWorkDates @NoWorkHours  @AA @Medium
  Scenario Outline: Validate PilotDOTC search is successful. Test Case Name:US1345575 Save Generic criteria with No Work Days, No Work Dates and No Work Hours. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to expand Days Dates and Hours Panel
    Then I should be able to select No Work Days criteria
    Then I should be able to select no work days "<days>"
    Then I should be able to select No Work Dates criteria
    Then I should be able to select No Work Dates as per today date plus "<offset1>" "<offset2>"
    Then I should be able to select No work Hours criteria
    Then I should be able to select No Work Hours from Min "<minHours>" and Max "<maxHours>" dropdown
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate No Work Days values "<noWorkDays>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to validate No Work Hours values "<minHours>" "<maxHours>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to validate No Work Dates vales as per "<offset1>" "<offset2>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate No Work Days values "<noWorkDays>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to validate No Work Hours values "<minHours>" "<maxHours>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to validate No Work Dates vales as per "<offset1>" "<offset2>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate No Work Days values "<noWorkDays>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to validate No Work Hours values "<minHours>" "<maxHours>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to validate No Work Dates vales as per "<offset1>" "<offset2>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to update "<ballotType3>" ballot
    #Then I verfy ballot creation in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate No Work Days values "<noWorkDays>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to validate No Work Hours values "<minHours>" "<maxHours>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to validate No Work Dates vales as per "<offset1>" "<offset2>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate No Work Days values "<noWorkDays>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to validate No Work Hours values "<minHours>" "<maxHours>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to validate No Work Dates vales as per "<offset1>" "<offset2>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate No Work Days values "<noWorkDays>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to validate No Work Hours values "<minHours>" "<maxHours>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to validate No Work Dates vales as per "<offset1>" "<offset2>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | days              | minHours | maxHours | noWorkDays        | offset1 | offset2 | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  | sunday,monday     | 02:00    | 10:30    | sunday,monday     |       1 |       1 | PickUpDOTC  | PickUpOutside | Template    | Test_US1345575 |
      | DFW  | tuesday,wednesday | 04:00    | 11:00    | tuesday,wednesday |       3 |       4 | PickUpDOTC  | PickUpOutside | Template    | Test_US1345575 |
