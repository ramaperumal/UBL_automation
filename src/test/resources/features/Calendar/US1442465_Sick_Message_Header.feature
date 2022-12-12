Feature: US1442465 , Sick message header
As a pilot , if I'm sick then I should be able to see the sick message header in calendar screen

 @Calendar @Smoke  @US1442465 @Sick 
Scenario Outline: Validating Sick mesage header in calendar screen. Test Case Name: US1442465  Currently Sick message in header.
Given pilotDOTC application has been Launched
When I assign sick activity for "current" date to "<lh_pilot>" of "<base>" 
Then I login with sick LH pilot for "<lh_pilot>"
Then I am at the DOTC/RAS landing page
Then I validate sick header msg

Examples: 
      | base |lh_pilot|
      | LAX  |       0|   
