Feature: Registration

  Scenario Outline: Register user with correct credentials
    Given the user is on Registration page
    When fills up "<full name>", email, "<age>", "<password>" and submits registration
    Then logs in successfully with registered email and "<password>"
    And the given "<full name>" equals with the name on Profile page
    Examples:
      | full name      | age | password      |
      | Csacska Macska | 120 | k@csk0_M@csk0 |

  Scenario: Register user with empty credentials
    Given the user is on Registration page
    When do not fills up fields and submits registration
    Then user remains on Registration page

  Scenario Outline: Register user with existing credentials
    Given the user is on Registration page
    When fills up "<full name>", email, "<age>", "<password>" and submits registration
    And opens Registration page again
    And fills up same "<full name>", email, "<age>", "<password>" and submits registration
    Then receives error message
    Examples:
      | full name   | age | password |
      | Kacska Kacsa | 6   | KACSKA   |

  Scenario Outline: Register user with age in non-integer format
    Given the user is on Registration page
    When fills up "<full name>", email, "<age>", "<password>" and submits registration
    Then logs in successfully with registered email and "<password>"
    Examples:
      | full name    | age | password |
      | Kecske Kenez | ten | 12345    |

