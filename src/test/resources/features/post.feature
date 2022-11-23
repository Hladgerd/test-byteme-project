Feature: Post

  Background: the user is logged in to Byte.me

  Scenario: Add post successfully
    Given the user is on Feed page
    When fills up post title and body and clicks Add button
    Then the given title equals the title of the latest post
    And the given body equals the body of the latest post

  Scenario: Delete post successfully
    Given a new post has been created
    When user deletes the new post
    Then the created post title is different from last post's title
    And the created post body is different from last post's body
