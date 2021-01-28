Feature: Update Page

Background:
	Given I have logged in as admin
	
Scenario: Album Update 
	When I update an album 
	Then I can see updated album page
	 
Scenario: Artist Update  
	When I update an artist 
	Then I can see the updated artist page 
	
Scenario: Genre Update  
	When I update on genre 
	Then I can see the updated genre page 
	
Scenario: Track Update  
	When I update on a track 
	Then I can see the updated track page 	
	