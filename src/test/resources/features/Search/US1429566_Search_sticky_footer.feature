Feature: US1429566 For checking search page sticking footer
As a pilot , when I select a sequence save selected sequences should appear and when deselected it should not.

@Search @Smoke @AA @US1429566 @postGoLive @SeachStickFooter
 Scenario Outline: Validating search page save selected sequence footer. Test Case Name:US1429566 Search Sticky Footer.
   Given pilotDOTC application has been Launched
   When I login with the a LH pilot base at "<base>" 
   Then I am at the DOTC/RAS landing page
   Then I should be able to click on Search button
   And I should be able to access Search page
   Then I should be able to click on Show Sequences
   Then I Should be able to see the list of Sequences
   Then I should be able to select a sequence from the sequencesList
   And I should be able to verify search sticky footer "is present"
   Then I should be able to deselect a sequence from the sequencesList
   And I should be able to verify search sticky footer "is not present"
   #Then I should be able to select a sequence from the sequencesList
   #Then I should be able to click on Save Selected Sequences button
   #Then I should be able to select All Ballots Check Box
   #Then I should be able to click on Add button
   #Then I should be able to update "<ballotType1>" ballot
  Examples:
      |base| ballotType1 |                                        
      |MIA|PickUpDOTC  |