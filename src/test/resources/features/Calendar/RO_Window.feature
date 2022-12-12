Feature:  Recovery Obligation (RO) feature

@Calendar @Smoke  @RO-Window @US1540116
Scenario Outline: Validate RO window in calendar page . Test Case Name:Validate_RO_window_in_calendar_page.
 Given pilotDOTC application has been Launched
 When  I assign RO Window for LH on current day of bidstatus "<BidStatus>" and login  
 Then I validate the RO Window 
 
 Examples:
		|BidStatus|
		|DFW/737/CA/I |