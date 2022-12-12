Feature: US1279769 Departure Station Validation in Sequence
  As a PilotDOTC user, I need a smoke test that sequences can be searched by departure station

  @Search @AA @DepartureStations  @SCH @US1279769 @US1279769_1 @AA @Medium @Smoke
  Scenario Outline: Validate LH Pilot can search Sequence with Departure Station as Input. Test Case Name:US1279769_1 Verify Departure Station value in Sequence when searched with few Departure Stations. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to click Airport and Layovers Down Arrow
    Then I should be able to click Departure Station Plus
    Then I should be able to select Departure Stations "<stationNames>"
    Then I should be able to click on Show Sequences
    Then I should be able to verify sequence results for Departure Station "<stationNames>"

    Examples: 
      | base | stationNames |
      | DFW  | MIA          |
      | DFW  | DFW          |

  @DepartureStations @Search @SCH @US1279769 @US1279769_2 @SatelliteStations @AA @Medium @Smoke
  Scenario Outline: Validate LH Pilot can search Sequence with Departure Station as pilot base . Test Case Name:US1279769_2 Verify Departure Station value in Sequence if no checkbox is checked. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    Then I should be able to click on Show Sequences
    Then I should be able to verify sequence results based on Pilot base "<base>"

    Examples: 
      | base |
      | DFW  |
      | LGA  |
