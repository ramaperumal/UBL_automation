Feature: Search Feature
  As a PilotDOTC user, i should be able to Save search Criteria as Sit Time and Sequence Number successfully

  @Ballot @AA @Smoke @SCH @US1276225 @US1276225C1 @sitTime @genericCriteria  @Medium @US1386340 
  Scenario Outline: Validate PilotDOTC search is successful. Test Case Name:US1276225C1 Save Generic criteria as Sit Time. Priority: Useful. Risk: Medium.
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
    And I should be able to click Sit Time Plus
    Then I should be able to Select Value "<sitTime>" in Sit Time dropdown 
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to update "<ballotType3>" ballot
    And I verfy ballot creation in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | sitTime | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  |       2 | PickUpDOTC  | PickUpOutside | Template    | Test_US1278870 |

 @Ballot @AA @Smoke @SCH @US1276225 @US1276225C2  @sequenceNumber @genericCriteria @US1386340
  Scenario Outline: Validate PilotDOTC search is successful. Test Case Name:US1276225C2 Verify Departure Date and Sequence Number criteria as Sequence Number. Priority: Useful. Risk: Medium.
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
    Then I should be able to click Sequence Number
    And I should be able to click Sequence Number Plus
    Then I should be able to Enter Value in Sequence Number TextBox "<SequenceNumber>"
    And I should click on checkbox "<base>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType1>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType2>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType3>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType3>" "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType1>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType2>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType3>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType3>" "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | SequenceNumber | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  |           3455 | PickUpDOTC  | PickUpOutside | Template    | Test_US1276225 |

 @Ballot @AA @Smoke @SCH @US1276225 @US1276225C3  @sequenceNumber @genericCriteria @US1386340
  Scenario Outline: Validate PilotDOTC search is successful. Test Case Name:US1276225C3 If Sequence Number is chosen Sit Time is not displayed in Generic Criteria. Priority: Useful. Risk: Medium.
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
    And I should be able to click Sit Time Plus
    Then I should be able to Select Value "<sitTime>" in Sit Time dropdown
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I should be able to Edit the request for ballottype "<ballotType1>" "<title>"
    Then I should be able to click Sequence Number
    And I should be able to click Sequence Number Plus
    Then I should be able to Enter Value in Sequence Number TextBox "<SequenceNumber>"
    And I should click on checkbox "<base>"
    Then I should be able to check if Sit Time dropdown is disabled
    Then I should be able to click Create Generic button
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType1>" "<title>"

    Examples: 
      | base | SequenceNumber | sitTime | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  |           3455 |       3 | PickUpDOTC  | PickUpOutside | Template    | Test_US1276225 |

  @Ballot @AA @Smoke @SCH @US1276225 @US1276225C4 @sitTime  @sequenceNumber @genericCriteria @US1386340
  Scenario Outline: Validate PilotDOTC search is successful. Test Case Name:US1276225C4 If Sequence Number is chosen Sit Time is not displayed in Generic Criteria. Priority: Useful. Risk: Medium.
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
    And I should be able to click Sit Time Plus
    Then I should be able to Select Value "<sitTime>" in Sit Time dropdown
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Sit Time values "<sitTime>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I should be able to Edit the request for ballottype "<ballotType1>" "<title>"
    Then I should be able to click Sequence Number
    And I should be able to click Sequence Number Plus
    Then I should be able to Enter Value in Sequence Number TextBox "<SequenceNumber>"
    And I should click on checkbox "<base>"
    Then I should be able to check if Sit Time dropdown is disabled

    Examples: 
      | base | SequenceNumber | sitTime | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  |           3455 |       3 | PickUpDOTC  | PickUpOutside | Template    | Test_US1276225 |
      
  @Ballot @AA @Smoke @SCH @US1276225 @US1276225C5 @sitTime @ballot @sequenceNumber @genericCriteria @US1386340
  Scenario Outline: Validate PilotDOTC search is successful. Test Case Name:US1276225C5 Verify Departure Date and Sequence Number criteria as Sequence Number. Priority: Useful. Risk: Medium.
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
    Then I should be able to click Sequence Number
    And I should be able to click Sequence Number Plus
    Then I should be able to Enter Value in Sequence Number TextBox "<SequenceNumber>"
    And I should click on checkbox "<base>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType1>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType2>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType3>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType3>" "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I verfy ballot creation in the ballot list
    Then I should be able to click Pick Up DOTC link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType1>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType2>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to check the Sequence Number Entered "<SequenceNumber>" for ballot "<ballotType3>" "<title>"
    And I should be able to validate departure date for ballot "<ballotType3>" "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | SequenceNumber | ballotType1 | ballotType2   | ballotType3 | title          |
      | DFW  |           3455 | PickUpDOTC  | PickUpOutside | Template    | Test_US1276225 |
  
