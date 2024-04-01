Feature: the summary can be retrieved
  #Happy Path 1
  Scenario: user makes call to GET /riot/summary/Belugafurtif
    When the user calls "/riot/summary/Belugafurtif"
    Then the user receives "Belugafurtif"'s summary
    And the response code status for "Belugafurtif" is 200

  #Happy Path 2
  Scenario: user makes call to GET /riot/summary/Razørk Activoo
    When the user calls "/riot/summary/Razørk Activoo"
    Then the user receives "Razørk Activoo"'s summary
    And the response code status for "Razørk Activoo" is 200

  #Sad Path 1
  Scenario: user makes call to GET /riot/summary/A (Summoner does not exist)
    When a GET request is made to the endpoint "/riot/summary/A" (where A does not exist)
    Then the API should respond with a status code 404 (Not Found)
    And the response body should contain an error message indicating that the summoner was not found

  #Sad Path 2
  Scenario: user makes call to GET /riot/summary/ (Summoner is missing)
    When an invalid request is made to the endpoint "/riot/summary/" (where the summoner Name is missing)
    Then the API should respond with a status code 400 (Bad Request)
    And the response body should contain an error message indicating that the request is invalid