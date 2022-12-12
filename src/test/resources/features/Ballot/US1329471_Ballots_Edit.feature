Feature: US1329471 Ballots Feature
  As a PilotDOTC user, i should be able to see all disable field when the ballots is created by sequence

 @Ballot @Smoke @SCH @US1329471 @US1329471-1 @EditBallot  @Medium @AA
  Scenario Outline: Validate Edit ballots. Test Case Name:US1329471-1 To verify other input field is disabled while editing the ballots with Sequence. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I Clicked on Search tab
    And I should be able to access Search page
    Then I should be able to click Sequence Number
    And I should be able to click Sequence Number Plus
    Then I should be able to Enter Value in Sequence Number TextBox "<ballotInput>"
    And I should click on checkbox "<base>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to update "<ballotType1>" ballot
    Then I should be able to Edit the request for ballottype "<ballotType1>" "<title>"
    Then I Verify that all the other field is disabled when ballots is created by sequence
    Then I should be able to click Create Generic button
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | ballotInput | title          | ballotType1 | ballotType2   | ballotType3 |
      | DFW  |       12345 | Test_US1329471 | PickUpDOTC  | PickUpOutside | Template    |

  @Smoke @SCH @US1329471 @US1329471-2 @EditBallot @Ballot @Medium @AA
  Scenario Outline: Validate Edit ballots. Test Case Name:US1329471-2 To verify other fields is replaced with sequence while editing the ballots with Sequence number. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I Click on Ballots tab
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"
    Then I Clicked on Search tab
    Then I should be able to expand Days Dates and Hours Panel
    Then I should be able to add Duty Periods in Search
    Then I should be able to select "<minValue>" and "<maxValue>" from Duty Periods Min and Max dropdown
    Then I Clicked on Save Generic Criteria
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to update "<ballotType1>" ballot
    Then I should be able to validate Minimun and Maximum Duty Periods selected "<minValue>" "<maxValue>" for "<ballotType1>" "<title>"
    Then I should be able to Edit the request for ballottype "<ballotType1>" "<title>"
    Then I should be able to click Sequence Number
    And I should be able to click Sequence Number Plus
    Then I should be able to Enter Value in Sequence Number TextBox "<ballotInput>"
    And I should click on checkbox "<base>"
    Then I should be able to click Create Generic button
    Then I should be able to update ballot
    Then I should be able to check the Sequence Number Entered "<ballotInput>" for ballot "<ballotType1>" "<title>"

    Examples: 
      | base | minValue | maxValue | title          | ballotInput | ballotType1 | ballotType2   | ballotType3 |
      | DFW  |        2 |        3 | Test_US1329471 |       12345 | PickUpDOTC  | PickUpOutside | Template    |
      
 
  

 