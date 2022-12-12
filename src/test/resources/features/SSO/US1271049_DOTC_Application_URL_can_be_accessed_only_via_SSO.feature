Feature: US1271049 DOTC Application URL can be accessed only via SSO

 @SSO @AA @Health @US1271049
  Scenario Outline: DOTC Application URL can be accessed only via SSO Test Case Name:US1271049 URL can be accessed only via SSO.  Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched at env "<env>" with subdomain "<subDomain>"
    Then I should be navigated to SSO "<env>"
    When I do SSO login as "<role>" at env "<env>"
    Then I should see the DOTC login page

    Examples: 
        |env     |subDomain | role  |
 		|qa cloud|          |itAdmin|
 		#|uat  |          |itAdmin|

  @SSO @SubDomain @Qa @US1271049+ @Short @MMD @DE96952  @AA @Smoke
  Scenario Outline: DOTC Application URL can be accessed only via SSO Test Case Name:US1271049+ URL can be accessed only via SSO. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched at env "<env>" with subdomain "<subDomain>"
    Then I should be navigated to SSO "<env>"
    When I do SSO login as "<role>" at env "<env>"
    Then I should see the DOTC login page

    Examples: 
    |env     |subDomain | role  |
 	|qa cloud|          |itAdmin|
 		#|qa   |search    |pilotadmin|
 		
 # Removing second example as "Feedback" page is not available in UAT application	
  @SSO @SubDomain @FeedbackTab @Short @Uat @US1271049+ @Short @MMD @DE96952  @AA @Smoke 		
  Scenario Outline: DOTC Application URL can be accessed only via SSO Test Case Name:US1271049+ URL can be accessed only via SSO. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched at env "<env>" with subdomain "<subDomain>"
    Then I should be navigated to SSO "<env>"
    When I do SSO login as "<role>" at env "<env>"
    Then I should see the DOTC login page

    Examples: 
    |env  |subDomain | role  |
 	|stage cloud |          |itadmin|
 		#|uat  |feedback  |itAdmin|
 		
  @SSO @AA @Smoke @MMD @US1271049- @DE96952 @SubDomain @SearchTab @Qa @Short
  Scenario Outline: DOTC Application URL can be accessed only via SSO Test Case Name:US1271049- URL can be accessed only via SSO. Priority: Useful. Risk: Medium.
    Given pilotDOTC application has been Launched at env "<env>" with subdomain "<subDomain>"
    Then I should be navigated to SSO "<env>"
    When I do SSO login with wrong credentials
    Then I should not see the DOTC login page

    Examples: 
    |env     |subDomain |role   |
 	|qa cloud|          |itAdmin|
