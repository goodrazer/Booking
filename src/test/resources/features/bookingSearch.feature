Feature: Search in booking
  Scenario: Looking for 'OKKO Hotels Paris Rosa Parks'
    Given Booking search page is opened
    When user searches for "OKKO Hotels Paris Rosa Parks"
    Then "OKKO Hotels Paris Rosa Parks" hotel is shown