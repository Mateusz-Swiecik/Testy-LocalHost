Feature: Comment -add,update,remove
  Scenario: Comment -add,update,remove
    Given i check if comment exist
    When  i try to add a comment
    When i again check if comment exist
    When i try to update comment
    When when i try to delete comment
    When i check if comment was deleted