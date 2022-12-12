Feature: US1368460 Form validation in search page when min value > max value

 @Search @AA @US1368460 @US1368460_1 @SearchFormValidation @GoLive
 Scenario Outline: US1368460 Form validation in search page when min value > max value. Test Case Name: US1368460_1 Form validation in search page when min value > max value for Calendar Days, Duty Period, Paid Credit, Total Credit.

 	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    And I clicked on Days Dates and Hour down arrow
    And I should be able to click Caldendar Days Plus
    And I should be able to select Calendar Days from drop down "<cal_days_min>" and "<cal_days_max>"
    Then I should validate red border around "Calendar Days" textbox
    Then I should validate error message for "calendarDays" field
    Then I should validate Save Generic Criteria button is disabled
    Then I should validate Show Sequences button is disabled
    Then I should validate Clear button is enabled
    And I Clicked on Clear button
    And I should click Calendar Days Minus
    And I should be able to add Duty Periods in Search
    And I should be able to select "<duty_period_min>" and "<duty_period_max>" from Duty Periods Min and Max dropdown
    Then I should validate red border around "Duty Periods" textbox
    Then I should validate error message for "dutyPeriods" field
    Then I should validate Save Generic Criteria button is disabled
    Then I should validate Show Sequences button is disabled
    Then I should validate Clear button is enabled
    And I Clicked on Clear button
    And I click Duty Periods Minus
    And I should be able to click Pay and Credit Down Arrow
    And I should be able to click Paid Credit Plus
    And I should be able to select Paid Credit values "<paid_credit_max_hr>" "<paid_credit_max_min>" "<paid_credit_min_hr>" "<paid_credit_min_min>" 
 	Then I should validate red border around "Paid Credit" textbox
 	Then I should validate error message for "paidCredit" field
    Then I should validate Save Generic Criteria button is disabled
    Then I should validate Show Sequences button is disabled
    Then I should validate Clear button is enabled
    And I Clicked on Clear button
    And I click on Paid Credit Minus
    And I should be able to click Total Credit Plus
    Then I should be able to select Maximum Hours "<paid_credit_max_hr>"
    Then I should be able to select Maximum Minutes "<paid_credit_max_min>"
    Then I should be able to select Minimum Hours "<paid_credit_min_hr>"
    Then I should be able to select Minimum Minutes "<paid_credit_min_min>"
    Then I should validate red border around "Total Credit" textbox
    Then I should validate error message for "totalCredit" field
    Then I should validate Save Generic Criteria button is disabled
    Then I should validate Show Sequences button is disabled
    Then I should validate Clear button is enabled
    And I Clicked on Clear button
 	
    Examples:
    | base | cal_days_min | cal_days_max | duty_period_min | duty_period_max | paid_credit_min_hr | paid_credit_min_min | paid_credit_max_hr | paid_credit_max_min |
    | DFW  | 4  		  | 2            | 3 			   | 1				 | 13				  | 45					| 6				     | 45				   |
    
 
 @AA @US1368460 @US1368460_2 @SearchFormValidation @Search @GoLive
 Scenario Outline: US1368460 Form validation in search page when min value > max value. Test Case Name: US1368460_2 Form validation in search page when min value > max value for leg per dp, legs per seq, TAFB, Layover.
 	Given pilotDOTC application has been Launched
    When I login with the a LH pilot base at "<base>"
    Then I am at the DOTC/RAS landing page
    Then I should be able to click on Search button
    And I should be able to access Search page
    And I should be able to click Sequence Characteristics
    And I should be able to click on Legs Per DP
    And I should be able to enter the range for Legs Per DP "<leg_dp_min>" "<leg_dp_max>"
    Then I should validate red border around "Legs Per DP" textbox
    Then I should validate error message for "legsPerDP" field
    Then I should validate Save Generic Criteria button is disabled
    Then I should validate Show Sequences button is disabled
    Then I should validate Clear button is enabled
    And I Clicked on Clear button
    And I clicked Legs Per DP Minus
    And I should be able to click Legs Per Seq and choose "<leg_seq_min>" and "<leg_seq_max>" Legs Per Seq values
    Then I should validate red border around "Legs Per Seq" textbox
    Then I should validate error message for "legsPerSequence" field
    Then I should validate Save Generic Criteria button is disabled
    Then I should validate Show Sequences button is disabled
    Then I should validate Clear button is enabled
    And I Clicked on Clear button
    And I clicked Legs Per Sequences Minus
    Then I should be able to click TAFB and choose "<tafb_min>" and "<tafb_max>" TAFB values
    Then I should validate red border around "TAFB" textbox
    Then I should validate error message for "tAFB" field
    Then I should validate Save Generic Criteria button is disabled
    Then I should validate Show Sequences button is disabled
    Then I should validate Clear button is enabled
    And I Clicked on Clear button
    And I clicked TAFB Minus
    And I should be able to click Airport and Layovers Down Arrow
    And I should click on Layover Times Plus
    And I should be able to choose "<layover_min>" and "<layover_max>" for Layover Times
    Then I should validate red border around "Layover Times" textbox
    Then I should validate error message for "layoverTimes" field
    Then I should validate Save Generic Criteria button is disabled
    Then I should validate Show Sequences button is disabled
    Then I should validate Clear button is enabled
    And I Clicked on Clear button
    
   	Examples:
   	| base | leg_dp_min | leg_dp_max | leg_seq_min | leg_seq_max | tafb_min | tafb_max | layover_min | layover_max |
   	| DFW  | 4 			| 2			 | 4		   | 3			 | 100      | 3		   | 15			 | 10		   |
