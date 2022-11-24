Feature: Edit profile

  Background: The user is logged in

  Scenario Outline: Edit name
    Given edit form is opened
    When user deletes current name, enters "<new name>", saves field and saves changes
    Then name on profile page equals with the given "<new name>"
    Examples:
      | new name |
      | New name |

  Scenario Outline: Edit age
    Given edit form is opened
    When user deletes current age, enters "<new age>", saves field and saves changes
    Then age on profile page equals with the given "<new age>"
    Examples:
      | new age |
      | 33      |


