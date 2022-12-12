Feature: US1280161 Save Ballot with number of criteria
  As a developer, I need a smoke test created to test that generic ballot requests can be saved with a number of criteria.

  @SaveBallot @AA @BallotNumberOFCriteria @R2Iteration1 @SCH @US1280161 @Smoke 
  Scenario Outline: Validate LH Pilot can save ballot with a number of criteria.Test Case Name:US1280161 Verify Ballot can be saved with number of criteria. Priority: Useful. Risk: Medium.
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
    Then I should be able to click Caldendar Days Plus
    Then I should be able to select Calendar Days from drop down "<minCalendar>" and "<maxCalendar>"
    Then I should be able to click Pay and Credit Down Arrow
    Then I should be able to click Paid Credit Plus
    Then I should be able to select Paid Credit values "<maxHours>" "<maxMins>" "<minHours>" "<minMins>"
    Then I should be able to click Sequence Characteristics
    Then I should be able to click TAFB and choose "<minTAFB>" and "<maxTAFB>" TAFB values
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click Departure Station Plus
    Then I should be able to select Departure Stations "<stationNames>"
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    Then I should be able to validate calendar days values "<minCalendar>" and "<maxCalendar>" for Ballot Type "<ballotType1>" "<title>"
    Then I should be able to verify TAFB values for ballottype "<ballotType1>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType1>" "<title>"
    Then I should click more options down arrow for ballottype "<ballotType1>" "<title>"
    Then I should be able to validate Paid Credit values "<minHours>" "<minMins>" "<maxHours>" "<maxMins>" for Ballot Type  "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate calendar days values "<minCalendar>" and "<maxCalendar>" for Ballot Type "<ballotType2>" "<title>"
    Then I should be able to verify TAFB values for ballottype "<ballotType2>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType2>" "<title>"
    Then I should click more options down arrow for ballottype "<ballotType2>" "<title>"
    Then I should be able to validate Paid Credit values "<minHours>" "<minMins>" "<maxHours>" "<maxMins>" for Ballot Type  "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to validate calendar days values "<minCalendar>" and "<maxCalendar>" for Ballot Type "<ballotType3>" "<title>"
    Then I should be able to verify TAFB values for ballottype "<ballotType3>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType3>" "<title>"
    Then I should click more options down arrow for ballottype "<ballotType3>" "<title>"
    Then I should be able to validate Paid Credit values "<minHours>" "<minMins>" "<maxHours>" "<maxMins>" for Ballot Type  "<ballotType3>" "<title>"
    Then I should be able to update "<ballotType3>" ballot
    Then I should be able to click Pick Up DOTC link
    Then I should be able to validate calendar days values "<minCalendar>" and "<maxCalendar>" for Ballot Type "<ballotType1>" "<title>"
    Then I should be able to verify TAFB values for ballottype "<ballotType1>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType1>" "<title>"
    Then I should click more options down arrow for ballottype "<ballotType1>" "<title>"
    Then I should be able to validate Paid Credit values "<minHours>" "<minMins>" "<maxHours>" "<maxMins>" for Ballot Type  "<ballotType1>" "<title>"
    Then I should be able to click Pick Up Outside link
    Then I should be able to validate calendar days values "<minCalendar>" and "<maxCalendar>" for Ballot Type "<ballotType2>" "<title>"
    Then I should be able to verify TAFB values for ballottype "<ballotType2>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType2>" "<title>"
    Then I should click more options down arrow for ballottype "<ballotType2>" "<title>"
    Then I should be able to validate Paid Credit values "<minHours>" "<minMins>" "<maxHours>" "<maxMins>" for Ballot Type  "<ballotType2>" "<title>"
    Then I should be able to click Template link
    Then I should be able to validate calendar days values "<minCalendar>" and "<maxCalendar>" for Ballot Type "<ballotType3>" "<title>"
    Then I should be able to verify TAFB values for ballottype "<ballotType3>" "<minTAFB>" and "<maxTAFB>" on the Saved Ballot "<title>"
    Then I should be able to validate departure stations "<stationNames>" for Ballot Type "<ballotType3>" "<title>"
    Then I should click more options down arrow for ballottype "<ballotType3>" "<title>"
    Then I should be able to validate Paid Credit values "<minHours>" "<minMins>" "<maxHours>" "<maxMins>" for Ballot Type  "<ballotType3>" "<title>"
    Then I should be able to click Pick Up DOTC link
    Then I delete the user story related ballots if present from "<ballotType1>" for "<title>"
    Then I should be able to click Pick Up Outside link
    Then I delete the user story related ballots if present from "<ballotType2>" for "<title>"
    Then I should be able to click Template link
    Then I delete the user story related ballots if present from "<ballotType3>" for "<title>"

    Examples: 
      | base | ballotType1 | ballotType2   | ballotType3 | stationNames | minTAFB | maxTAFB | maxHours | maxMins | minHours | minMins | minCalendar | maxCalendar | title          |
      | DFW  | PickUpDOTC  | PickUpOutside | Template    | BOS,DFW,ORD  |       3 |       5 |       10 |      10 |        6 |      35 |           2 |           4 | Test_US1280161 |
