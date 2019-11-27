
Feature: SoundCloud play/pause song

  @PlaySong
  Scenario: Verify that a song can be either paused or playing in SoundCloud
    Given I launch ChromeDriver
    When I open the SoundCloud song URL
    And I click the pause_play button
    Then I verify if the song is either paused or playing
    Then I close the browser