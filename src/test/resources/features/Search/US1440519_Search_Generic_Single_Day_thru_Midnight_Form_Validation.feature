Feature: US1440519 Search & Generic: Single Day thru Midnight Form Validation

 @AA @US1440519 @US1440519_1 @Search @GoLive @departNoEarlier
 Scenario Outline: Search Single Day thru Midnight Form Validation for departure and arrival date. Test Case Name: US1440519_1 Search- Single Day thru Midnight Form Validation for departure and arrival date.
 	Given pilotDOTC application has been Launched
 	When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    # to validate request thru mid-night error message for departure date
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I select the Depart No Earlier Than dropdown "<departNOEarlier>"
    And I select the Depart No Later Than dropdown "<departNOLater>"
    Then I validate "depMidNight" error msg "<err_msg>"
    Then I should validate red border around "Departure To" textbox
    And I click on "depEndDate" clear button
    Then I validate "depMidNight" error msg "<err_msg>"
    Then I should validate red border around "Departure To" textbox
    And I Clicked on Clear button
    # to validate request thru mid-night error message for arrival date
    Then I enter arr Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I select the Arrive No Earlier Than dropdown "<arriveNOEarlier>"
    And I select the Arrive No Later Than dropdown "<arriveNOLater>"
    Then I validate "arrMidNight" error msg "<err_msg>"
    Then I should validate red border around "Arrival To" textbox
    And I click on "arrEndDate" clear button
    Then I validate "arrMidNight" error msg "<err_msg>"
    Then I should validate red border around "Arrival To" textbox
    
    Examples:
    | base | departNOEarlier | departNOLater | arriveNOEarlier | arriveNOLater |offsetfromtoday_startdt|offsetfromtoday_enddt|						   				 err_msg|
    | DFW  | 22:00  		 | 02:00 		 | 			  21:00|		  04:00|				      1|				    1|Date range required for thru midnight requests|
    
 @AA @US1440519 @US1440519_2 @Search @GoLive @departNoEarlier
 Scenario Outline: Search Single Day thru Midnight Form Validation for departure and arrival date. Test Case Name: US1440519_2 Search- Single Day thru Midnight Form Validation for departure and arrival date.
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
    Then I should be able to click Save Generic Criteria button
    Then I should be able to enter the Search title based on User story Number "<title>"
    Then I should be able to check All Ballots check box
    And I should be able to Save the criteria
    
    # to validate pick up ballot
    Then I should be able to update "<ballotType1>" ballot
    Then I should be able to Edit the request for ballottype "<ballotType1>" "<title>"
    # to validate request thru mid-night error message for departure date for pick up ballot
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I select the Depart No Earlier Than dropdown "<departNOEarlier>"
    And I select the Depart No Later Than dropdown "<departNOLater>"
    Then I validate "depMidNight" error msg "<err_msg>"
    Then I should validate red border around "Departure To" textbox
    And I click on "depEndDate" clear button
    Then I validate "depMidNight" error msg "<err_msg>"
    Then I should validate red border around "Departure To" textbox
    Then I select the Depart No Earlier Than dropdown ""
    And I select the Depart No Later Than dropdown ""
    # to validate request thru mid-night error message for arrival date for pick up ballot
    Then I enter arr Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I select the Arrive No Earlier Than dropdown "<arriveNOEarlier>"
    And I select the Arrive No Later Than dropdown "<arriveNOLater>"
    Then I validate "arrMidNight" error msg "<err_msg>"
    Then I should validate red border around "Arrival To" textbox
    And I click on "arrEndDate" clear button
    Then I validate "arrMidNight" error msg "<err_msg>"
    Then I should validate red border around "Arrival To" textbox
    Then I select the Arrive No Earlier Than dropdown ""
    And I select the Arrive No Later Than dropdown ""
    Then I should be able to click Create Generic button
    Then I should be able to update "<ballotType1>" ballot
    
    # to validate pick up outside ballot
    Then I should be able to click Pick Up Outside link
    Then I should be able to Edit the request for ballottype "<ballotType2>" "<title>"
    # to validate request thru mid-night error message for departure date for pick up outside ballot
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I select the Depart No Earlier Than dropdown "<departNOEarlier>"
    And I select the Depart No Later Than dropdown "<departNOLater>"
    Then I validate "depMidNight" error msg "<err_msg>"
    Then I should validate red border around "Departure To" textbox
    And I click on "depEndDate" clear button
    Then I validate "depMidNight" error msg "<err_msg>"
    Then I should validate red border around "Departure To" textbox
    Then I select the Depart No Earlier Than dropdown ""
    And I select the Depart No Later Than dropdown ""
    # to validate request thru mid-night error message for arrival date for pick up outside ballot
    Then I enter arr Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I select the Arrive No Earlier Than dropdown "<arriveNOEarlier>"
    And I select the Arrive No Later Than dropdown "<arriveNOLater>"
    Then I validate "arrMidNight" error msg "<err_msg>"
    Then I should validate red border around "Arrival To" textbox
    And I click on "arrEndDate" clear button
    Then I validate "arrMidNight" error msg "<err_msg>"
    Then I should validate red border around "Arrival To" textbox
    Then I select the Arrive No Earlier Than dropdown ""
    And I select the Arrive No Later Than dropdown ""
    Then I should be able to click Create Generic button
    Then I should be able to update "<ballotType2>" ballot
    
    # to validate template ballot
    Then I should be able to click Template link
    Then I should be able to Edit the request for ballottype "<ballotType3>" "<title>"
    # to validate request thru mid-night error message for departure date for pick up outside ballot
    Then I enter Dep Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I select the Depart No Earlier Than dropdown "<departNOEarlier>"
    And I select the Depart No Later Than dropdown "<departNOLater>"
    Then I validate "depMidNight" error msg "<err_msg>"
    Then I should validate red border around "Departure To" textbox
    And I click on "depEndDate" clear button
    Then I validate "depMidNight" error msg "<err_msg>"
    Then I should validate red border around "Departure To" textbox
    Then I select the Depart No Earlier Than dropdown ""
    And I select the Depart No Later Than dropdown ""
    # to validate request thru mid-night error message for arrival date for pick up outside ballot
    Then I enter arr Date from and to Date "<offsetfromtoday_startdt>" "<offsetfromtoday_enddt>"
    Then I select the Arrive No Earlier Than dropdown "<arriveNOEarlier>"
    And I select the Arrive No Later Than dropdown "<arriveNOLater>"
    Then I validate "arrMidNight" error msg "<err_msg>"
    Then I should validate red border around "Arrival To" textbox
    And I click on "arrEndDate" clear button
    Then I validate "arrMidNight" error msg "<err_msg>"
    Then I should validate red border around "Arrival To" textbox
    Then I select the Arrive No Earlier Than dropdown ""
    And I select the Arrive No Later Than dropdown ""
    Then I should be able to click Create Generic button
    Then I should be able to update "<ballotType3>" ballot
    
    Examples:
    | base |title         |ballotType1 | ballotType2   | ballotType3 |departNOEarlier | departNOLater | arriveNOEarlier | arriveNOLater |offsetfromtoday_startdt|offsetfromtoday_enddt|						   			      err_msg|
    | DFW  |Test_US1440519|PickUpDOTC  | PickUpOutside | Template    |22:00  		  | 02:00 		  | 		   21:00|	       04:00|		   	  	       1|				     1|Date range required for thru midnight requests|
    
  