Feature: Logged In

  Background:
    Given the correct web address and logged in

  Scenario: Log In
    When I go to homepage
    Then I can see my username

  Scenario: Browse my playlists
    When I click on My Playlists
    Then I can see all my playlists

  Scenario: Create Playlists
    When I click on create playlist and fill in the form
    Then I can see my created playlist

  Scenario: Add to playlist
    When I add a song to a playlist
    Then I can see my correctly updated playlist


  Scenario: Remove from playlist
    When I remove a song from a playlist
    Then I can see the correctly updated playlist