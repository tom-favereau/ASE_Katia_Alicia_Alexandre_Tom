Feature: the game modes played can be retrieved
  #Happy Path 1
  Scenario: user makes call to GET /riot/gameModesPlayed/Belugafurtif
    When queue the user calls "/riot/gameModesPlayed/Belugafurtif"
    Then queue the user receives "Belugafurtif"'s queue
    And queue the response code status for "Belugafurtif" is 200

  #Happy Path 2
  Scenario: user makes call to GET /riot/gameModesPlayed/Razørk Activoo
    When queue the user calls "/riot/gameModesPlayed/Razørk Activoo"
    Then queue the user receives "Razørk Activoo"'s queue
    And queue the response code status for "Razørk Activoo" is 200

  #Sad Path 1
  Scenario: user makes call to GET /riot/gameModesPlayed/A (Summoner does not exist)
    When queue a GET request is made to the endpoint "/riot/queue/A" (where A does not exist)
    Then queue the API should respond with a status code 404 (Not Found)
    And queue the response body should contain an error message indicating that the summoner was not found

  #Sad Path 2
  Scenario: user makes call to GET /riot/gameModesPlayed/ (Summoner is missing)
    When queue an invalid request is made to the endpoint "/riot/gameModesPlayed/" (where the summoner Name is missing)
    Then queue the API should respond with a status code 400 (Bad Request)
    And queue the response body should contain an error message indicating that the request is invalid