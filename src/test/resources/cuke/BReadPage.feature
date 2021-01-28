Feature: Read Page

  Background:
    Given the correct web address

  Scenario: Album Read
    When I click an album
    Then I can see album page

  Scenario: Artist Read
    When I click an artist
    Then I can see the artist page

  Scenario: Genre Read
    When I click on genre
    Then I can see the genre page

  Scenario: Track Read
    When I click on a track
    Then I can see the track page
	