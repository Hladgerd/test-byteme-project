Feature: Search user

  Background: The user is logged in

  Scenario Outline: Search for existing user
    Given the user is on Feed page
    When searches for "<name>"
    And selects the first result
    Then given "<name>" equals with the name on searched user's profile page
    Examples:
      | name        |
      | Bacso Vanda |
