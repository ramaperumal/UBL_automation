Feature: US1368462 Saved Sequence Ballot Feature
  As a developer, I need a smoke test that should be able to Save a Sequence from Search.

  @SaveBallot @Smoke @MN @AA @US1368462 @Medium   @Sequences
  Scenario Outline: Smoke test that saves a selected sequence from search. Test Case Name:US1368462 Validate if a sequence from search can be saved.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    When I Clicked on Show sequence button
    Then I Should be able to see the list of Sequences
    Then I should be able to select a sequence from the sequencesList
    Then I should be able to click on Save Selected Sequences button
    Then I should be able to select All Ballots Check Box
    Then I should be able to click on Add button
    Then I delete the selected sequence related ballots if present from "<ballotType1>"
    Then I should be able to verify the saved sequence for Ballot Type "<ballotType1>"
    Then I should be able to click Pick Up Outside link for sequences
    Then I delete the selected sequence related ballots if present from "<ballotType2>"
    Then I should be able to verify the saved sequence for Ballot Type "<ballotType2>"
    Then I should be able to click Template link for sequences
    Then I delete the selected sequence related ballots if present from "<ballotType3>"
    Then I should be able to verify the saved sequence for Ballot Type "<ballotType3>"
    Then I should be able to update "<ballotType3>" ballot for sequences
    Then I should be able to click Pick Up DOTC link for sequences
    Then I should be able to verify the saved sequence for Ballot Type "<ballotType1>"
    Then I should be able to click Pick Up Outside link for sequences
    Then I should be able to verify the saved sequence for Ballot Type "<ballotType2>"
    Then I should be able to click Template link for sequences
    Then I should be able to verify the saved sequence for Ballot Type "<ballotType3>"
    Then I should be able to click Pick Up DOTC link for sequences
    Then I should be able to delete the saved sequence related ballot for "<ballotType1>"
    Then I should be able to click Pick Up Outside link for sequences
    Then I should be able to delete the saved sequence related ballot for "<ballotType2>"
    Then I should be able to click Template link for sequences
    Then I should be able to delete the saved sequence related ballot for "<ballotType3>"
	Then I should be able to update "<ballotType3>" ballot for sequences
	
    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 |
      | DFW  | PickUpDOTC  | PickUpOutside | Template    |
      | MIA  | PickUpDOTC  | PickUpOutside | Template    |
