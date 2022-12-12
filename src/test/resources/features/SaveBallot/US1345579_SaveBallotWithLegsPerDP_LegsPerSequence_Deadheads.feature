Feature: US1345579 Legs Per DP and Legs Per Seq Search Feature
  As a PilotDOTC user, i should be able to Save the ballot with Legs Per DP,Legs Per Sequence and Deadheads

 @SaveBallot @Smoke @RSB @US1345579   @AA @LegsPerDP @LegsPerSeq @DeadHeads @UpdateBallot 
  Scenario Outline: Validate PilotDOTC search is successful. Test Case Name:US1345579 Save the ballot with Legs Per DP,Legs Per Sequence and Deadheads. Priority: Useful. Risk: Medium.
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
    Then I should be able to click Legs Per DP and choose "<MinValue>" and "<MaxValue>"	Legs Per DP values
    Then I should be able to click Legs Per Seq and choose "<MinValue>" and "<MaxValue>" Legs Per Seq values
    Then I should be able to click DeadHeads plus icon
    Then I should be able to select DeadHeads checkbox "<Value>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to click Pick Up DOTC link
    Then I should click more options down arrow for ballottype "<ballotType1>" "<title>"
    Then I should be able to validate Legs Per DP values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to validate Legs Per Seq values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to validate DeadHeads "<Value>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should click more options down arrow for ballottype "<ballotType2>" "<title>"
    Then I should be able to validate Legs Per DP values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to validate Legs Per Seq values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to validate DeadHeads "<Value>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should click more options down arrow for ballottype "<ballotType3>" "<title>"
    Then I should be able to validate Legs Per DP values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to validate Legs Per Seq values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to validate DeadHeads "<Value>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should click more options down arrow for ballottype "<ballotType1>" "<title>"
    Then I should be able to validate Legs Per DP values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to validate Legs Per Seq values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to validate DeadHeads "<Value>" for Ballot Type "<ballotType1>" and "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should click more options down arrow for ballottype "<ballotType2>" "<title>"
    Then I should be able to validate Legs Per DP values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to validate Legs Per Seq values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to validate DeadHeads "<Value>" for Ballot Type "<ballotType2>" and "<title>"
    Then I should be able to click Template link
    Then I should click more options down arrow for ballottype "<ballotType3>" "<title>"
    Then I should be able to validate Legs Per DP values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to validate Legs Per Seq values "<MinValue>" and "<MaxValue>"	for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to validate DeadHeads "<Value>" for Ballot Type "<ballotType3>" and "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | title          | MinValue | MaxValue | Value       |
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | Test_US1345579 |        1 |        2 | DH Required |
