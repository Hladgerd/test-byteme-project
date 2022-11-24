Feature: Registration

  Scenario Outline: Register user with correct credentials
    Given the user is on Registration page
    When fills up "<full name>", email, "<age>", "<password>" and submits registration
    Then logs in successfully with registered email and "<password>"
    And the given "<full name>" equals with the name on Profile page
    Examples:
      | full name      | age | password      |
      | Csacska Macska | 120 | k@csk0_M@csk0 |