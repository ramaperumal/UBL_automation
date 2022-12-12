Feature: US1275107 Search Feature
  As a developer, I need a smoke test created to test that "pending sick" checkbox searches for crewed sequences where that crewmember is pending sick within a date range.
@Search @Smoke @US1275107 @AA @Short @BA @PendingSick @UAT
Scenario Outline: DOTC Automation: Smoke Test:Validate when the pilot searches for pending sick sequences a valid sick icon shows in the search results for the applicable sequences. Test Case Name: US1275107 Searches for pending sick sequences.
    Given pilotDOTC application has been Launched
    Then I should get list of all LH pilot with base at "<base>" with bid-status
    When I login with the pilot ID having pending sick sequence with base "<base>"
    Then I am at the DOTC/RAS landing page
    When I clicked on the pilot name at the right top
    Then I should be able to click on Search button
    Then Set the departure from date
    Then I should Check the Sick Sequences checkbox
    Then I should be able to click on Show Sequences
    Then I should be able to verify Sick Icon for these sequences

   
    Examples: 
    | base|	seqlist|offsetfromtoday_startdt|offsetfromtoday_enddt|
    | DFW | 5247   |            		0  |					1|
    