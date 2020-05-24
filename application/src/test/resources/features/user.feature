Feature: User operation
  Scenario: client makes call to GET /api/users/{userId}
    When the client calls 1
    Then the client receives user 1