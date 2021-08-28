Feature: OpenWeatherMapGet Tests
  This feature contains tests that verify the Open Weather Map Get API

  Background: Rest assured is installed

@Test
  Scenario: Correct station details are received while verifying the station details
    Given the OpenWeatherMap API is available
    When I search for a station with a value of '6128a8c509e7430001b9fc37'
    Then the requests response with a value of '6128a8c509e7430001b9fc37' will contain the correct json data

