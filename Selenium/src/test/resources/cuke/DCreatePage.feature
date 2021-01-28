Feature: Create Page

Background:
	Given I have logged in as admin
	
Scenario: Album Create 
	When I create an album 
	Then I can see created album
	 
Scenario: Artist Create  
	When I create an artist 
	Then I can see the created artist 
	
Scenario: Genre Create  
	When I create a genre 
	Then I can see the created genre 
	
Scenario: Track Create  
	When I create a track 
	Then I can see the created track 	
	