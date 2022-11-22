Feature: Logout
  Scenario: Successful logout
    Given The user is logged in to Byte.me
    When clicks Logout button
    Then Login page is visible