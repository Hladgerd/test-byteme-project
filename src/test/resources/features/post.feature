Feature: Post

  Background: the user is logged in to Byte.me

  Scenario Outline: Add post successfully
    Given the user is on Feed page
    When fills up post "<title>" and "<body>" and clicks Add button
    Then the given "<title>" equals the title of the latest post
    And the given "<body>" equals the body of the latest post
    Examples:
      | title     | body          |
      | New title | New post body |
