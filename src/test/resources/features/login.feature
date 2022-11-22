Feature: Login

  Background: The user is already registered to the website

  Scenario Outline: Successful login
    Given The user is on Byte.me login page
    When enters "<email>" and clicks the Login button
    Then logout button is visible
    And registered name "<fullname>" equals with name on Profile page
    Examples:
      | email        | fullname      |
      | zeno@byte.me | Fergencs Zeno |

  Scenario Outline: Incorrect login
    Given The user is on Byte.me login page
    When enters "<incorrect email>" and clicks the Login button
    Then feed page is not visible
    Examples:
      | incorrect email |
      | noname@byte.me  |

  Scenario: Login with empty credentials
    Given The user is on Byte.me login page
    When doesn't fill up credentials, just clicks the Login button
    Then alert message received
