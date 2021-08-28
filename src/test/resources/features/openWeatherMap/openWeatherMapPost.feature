Feature: OpenWeatherMapPost Tests
  This feature contains tests that verify the Open Weather Map Post API

  Background: Rest assured is installed

  @Test
  Scenario: Error response is received when attempting to register a station without API key
    Given the OpenWeatherMapPost API is available
    When I attempt to register a station with a value of api key as null
    Then the request response has error response


  @Test
  Scenario: Success response is received when attempting to register a station with API key
    Given the OpenWeatherMapPost API is available
    When I attempt to register a station with a value of 'DEMO_TEST001', 'Team Demo Test Station 001', 33.33, -122.43, 222
    Then the request response has success response

  @Test
  Scenario: Success response is received when attempting to register a station with API key
    Given the OpenWeatherMapPost API is available
    When I attempt to register a station with a value of 'DEMO_TEST002', 'Team Demo Test Station 002', 44.44, -122.44, 111
    Then the request response has success response