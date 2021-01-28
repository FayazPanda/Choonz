Feature: Delete Page

Background:
	Given I have logged in as admin
	
Scenario: Playlist Delete  
	When I delete a playlist
	Then I can see the playlist is not there 	
	
Scenario: Album Delete 
	When I delete an album 
	Then I can see the album is not there
	 
Scenario: Artist Delete  
	When I delete an artist 
	Then I can see the artist is not there
	
Scenario: Genre Delete  
	When I delete on genre 
	Then I can see the genre is not there 
	
Scenario: Track Delete  
	When I delete on a track 
	Then I can see the track is not there 	
	