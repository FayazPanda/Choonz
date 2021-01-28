Feature: Not Logged in Browse

  Background:
    Given the correct web address

  Scenario: Homepage
    When I am on the homepage
    Then I can see top albums

  Scenario: Browse Genre
    When I click on Genre
    Then I can see all genres

  Scenario: Browse Albums
    When I click on Albums
    Then I can see all albums

  Scenario: Browse Artists
    When I click on Artists
    Then I can see all artists

  Scenario: Browse Playlists
    When I click on All Playlists
    Then I can see all playlists

  Scenario: Search
    When I type a term into the search bar
    Then I can see the correct result

  Scenario: Register
    When I register
    Then I can see my new username