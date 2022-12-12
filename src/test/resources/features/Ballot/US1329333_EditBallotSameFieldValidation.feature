Feature: US1329333 Edit Ballot
  As a developer, I need a smoke test created to test that generic ballot requests can be edited.

 @Ballot @AA  @R2Iteration2 @SCH @US1329333 @US1329333SC1 @Smoke @Edit 
  Scenario Outline: Valid LH pilot can click edit to change the value of the chosen field in the ballot.Test Case Name:US1329333SC1 Valid LH pilot can click edit to change the value of the chosen field in the Pick Up DOTC ballot. Priority: Useful. Risk: Medium.
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
    Then I should be able to click Sequence Characteristics
    Then I should be able to click TAFB and choose "<minTAFB>" and "<maxTAFB>" TAFB values
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to verify TAFB values for ballottype "<ballotType1>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to verify TAFB values for ballottype "<ballotType2>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Template link
    Then I should be able to verify TAFB values for ballottype "<ballotType3>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to Edit the request for ballottype "<ballotType1>" "<title>"
    Then I should choose TAFB values "<minTAFBNew>" and "<maxTAFBNew>"
    Then I should be able to click Create Generic button
     Then I should be able to update "<ballotType1>" ballot
    Then I should be able to verify TAFB values for ballottype "<ballotType1>" "<minTAFBNew>" and "<maxTAFBNew>" on the Saved Ballot "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to verify TAFB values for ballottype "<ballotType2>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Template link
    Then I should be able to verify TAFB values for ballottype "<ballotType3>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | minTAFB | maxTAFB | minTAFBNew | maxTAFBNew | title          |
      | DFW  | PickUpDOTC  | PickUpOutside | Template    |       3 |       5 |          6 |          8 | Test_US1329333 |

 @Ballot  @AA @R2Iteration2 @SCH @US1329333 @US1329333SC2 @Smoke @Edit
  Scenario Outline: Valid LH pilot can click edit to change the value of the chosen field in the ballot.Test Case Name:US1329333SC2 Valid LH pilot can click edit to change the value of the chosen field in the Pick Up Outside ballot. Priority: Useful. Risk: Medium.
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
    Then I should be able to click Sequence Characteristics
    Then I should be able to click TAFB and choose "<minTAFB>" and "<maxTAFB>" TAFB values
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to verify TAFB values for ballottype "<ballotType1>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to verify TAFB values for ballottype "<ballotType2>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Template link
    Then I should be able to verify TAFB values for ballottype "<ballotType3>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up Outside link
    Then I should be able to Edit the request for ballottype "<ballotType2>" "<title>"
    Then I should choose TAFB values "<minTAFBNew>" and "<maxTAFBNew>"
    Then I should be able to click Create Generic button
    Then I should be able to update "<ballotType2>" ballot
    Then I should be able to verify TAFB values for ballottype "<ballotType2>" "<minTAFBNew>" and "<maxTAFBNew>" on the Saved Ballot "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I should be able to verify TAFB values for ballottype "<ballotType1>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Template link
    Then I should be able to verify TAFB values for ballottype "<ballotType3>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | minTAFB | maxTAFB | minTAFBNew | maxTAFBNew | title          |
      | DFW  | PickUpDOTC  | PickUpOutside | Template    |       3 |       5 |          6 |          8 | Test_US1329333 |

   @Ballot @AA @R2Iteration2 @SCH @US1329333 @US1329333SC3 @Smoke @Edit 
  Scenario Outline: Valid LH pilot can click edit to change the value of the chosen field in the ballot.Test Case Name:US1329333SC3 Valid LH pilot can click edit to change the value of the chosen field in the Template ballot. Priority: Useful. Risk: Medium.
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
    Then I should be able to click Sequence Characteristics
    Then I should be able to click TAFB and choose "<minTAFB>" and "<maxTAFB>" TAFB values
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to verify TAFB values for ballottype "<ballotType1>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to verify TAFB values for ballottype "<ballotType2>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Template link
    Then I should be able to verify TAFB values for ballottype "<ballotType3>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to Edit the request for ballottype "<ballotType3>" "<title>"
    Then I should choose TAFB values "<minTAFBNew>" and "<maxTAFBNew>"
    Then I should be able to click Create Generic button
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to verify TAFB values for ballottype "<ballotType3>" "<minTAFBNew>" and "<maxTAFBNew>" on the Saved Ballot "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to verify TAFB values for ballottype "<ballotType2>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I should be able to verify TAFB values for ballottype "<ballotType1>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | minTAFB | maxTAFB | minTAFBNew | maxTAFBNew | title          |
      | DFW  | PickUpDOTC  | PickUpOutside | Template    |       3 |       5 |          6 |          8 | Test_US1329333 |
