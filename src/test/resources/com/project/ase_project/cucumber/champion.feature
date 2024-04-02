Feature: the champions played can be retrieved
  #Happy Path 1
  Scenario: user makes call to GET /riot/championsPlayed/Belugafurtif
    When champion the user calls "/riot/gameModesPlayed/Belugafurtif"
    Then champion the user receives "Belugafurtif"'s queue
    And champion the response code status for "Belugafurtif" is 200

  #Happy Path 2
  Scenario: user makes call to GET /riot/championsPlayed/Razørk Activoo
    When champion the user calls "/riot/gameModesPlayed/Razørk Activoo"
    Then champion the user receives "Razørk Activoo"'s queue
    And champion the response code status for "Razørk Activoo" is 200

  #Sad Path 1
  Scenario: user makes call to GET /riot/championsPlayed/A (Summoner does not exist)
    When champion a GET request is made to the endpoint "/riot/championsPlayed/A" (where A does not exist)
    Then champion the API should respond with a status code 404 (Not Found)
    And champion the response body should contain an error message indicating that the summoner was not found

  #Sad Path 2
  Scenario: user makes call to GET /riot/championsPlayed/ (Summoner is missing)
    When champion an invalid request is made to the endpoint "/riot/championsPlayed/" (where the summoner Name is missing)
    Then champion the API should respond with a status code 400 (Bad Request)
    And champion the response body should contain an error message indicating that the request is invalid