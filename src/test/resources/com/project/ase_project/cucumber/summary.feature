Feature: the summary can be retrieved
  Scenario: user makes call to GET /riot/summary/Belugafurtif
    When the user calls /riot/summary/Belugafurtif
    Then the user receives summoner summary
    And the response code status is 200
