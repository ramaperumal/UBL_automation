Feature: US1345582 Cities and Layover Times Ballot Feature
  As a developer, I need a Smoke test that saves ballots with Cities and Layover Times.

  @SaveBallot @Smoke @MN @AA @US1345582 @US1345582-1 @Medium @LayoverTimes @Cities
  Scenario Outline: Smoke test that saves ballots with Cities and Layover Times. Test Case Name:US1345582-1 Validate if the ballots can be saved with Include Cities, Exclude Cities and LayoverMinTime, LayoverMaxTime.
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
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click on Cities plus
    Then I should be able to click on Layover Times plus
    Then I should be able to enter Include Cities value "<IncludeCities>"
    Then I should be able to enter Exclude Cities value "<ExcludeCities>"
    Then I should be able to select Layover Min Time from drop down "<minTime>"
    Then I should be able to select Layover Max Time from drop down "<maxTime>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | IncludeCities | ExcludeCities | minTime | maxTime | title          | ballotType1 | ballotType2   | ballotType3 |
      | DFW  | DFW,CLT       | PHL,PHX       |      10 |      19 | Test_US1345582 | PickUpDOTC  | PickUpOutside | Template    |
     

 @SaveBallot @Smoke @MN @AA @US1345582 @US1345582-2 @Medium @LayoverTimes @Cities
  Scenario Outline: Smoke test that saves ballots with Cities and Layover Times. Test Case Name:US1345582-2 Validate if the ballots can be saved with Include Cities and LayoverMinTime.
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
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click on Cities plus
    Then I should be able to click on Layover Times plus
    Then I should be able to enter Include Cities value "<IncludeCities>"
    Then I should be able to select Layover Min Time from drop down "<minTime>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Include Cities "<IncludeCities>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Layover Time Min "<minTime>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | IncludeCities | minTime | title          | ballotType1 | ballotType2   | ballotType3 |
      | DFW  | DFW,DCA       |      11 | Test_US1345582 | PickUpDOTC  | PickUpOutside | Template    |
      

  @SaveBallot @Smoke @MN @AA @US1345582 @US1345582-3 @Medium  @LayoverTimes @Cities
  Scenario Outline: Smoke test that saves ballots with Cities and Layover Times. Test Case Name:US1345582-3 Validate if the ballots can be saved with Exclude Cities and LayoverMaxTime.
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
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click on Cities plus
    Then I should be able to click on Layover Times plus
    Then I should be able to enter Exclude Cities value "<ExcludeCities>"
    Then I should be able to select Layover Max Time from drop down "<maxTime>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType1>" and title "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType2>" and title "<title>"
    Then I should be able to click Template link
    Then I should be able to validate Exclude Cities "<ExcludeCities>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to validate Layover Time Max "<maxTime>" value for Ballot Type "<ballotType3>" and title "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | ExcludeCities | maxTime | title          | ballotType1 | ballotType2   | ballotType3 |
      | DFW  | LAX,MIA       |      20 | Test_US1345582 | PickUpDOTC  | PickUpOutside | Template    |
      

  @SaveBallot @Smoke @AA @MN @US1345582 @US1345582-4 @Short @LayoverTimes @Cities
  Scenario Outline: Smoke test that saves ballots with Cities and Layover Times. Test Case Name:US1345582-4 Validate if Save Generic Criteria button is disabled, when invalid Layover times selected.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click on Cities plus
    Then I should be able to click on Layover Times plus
    Then I should be able to enter Include Cities value "<IncludeCities>"
    Then I should be able to enter Exclude Cities value "<ExcludeCities>"
    Then I should be able to select Layover Min Time from drop down "<minTime>"
    Then I should be able to select Layover Max Time from drop down "<maxTime>"
    When I enter invalid Layover Times value, Save Generic Criteria button should be disabled "<minTime>" and "<maxTime>"

    Examples: 
      | base | IncludeCities | ExcludeCities | minTime | maxTime |
      | DFW  | DFW           | LAX           |      15 |      12 |
