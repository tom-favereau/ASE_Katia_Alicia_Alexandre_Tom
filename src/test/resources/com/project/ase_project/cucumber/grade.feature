Feature: the grade can be post
  #Happy Path 1
  Scenario: user makes call to POST /grade/Belugafurtif/1
    When grade the user calls "/grade/Belugafurtif/1"
    Then grade the user post "Belugafurtif"'s grade
    And grade the response code status for "Belugafurtif" is 200

  #Happy Path 2
  Scenario: user makes call to POST /grade/Razørk/1 Activoo
    When grade the user calls "/grade/Razørk Activoo/1"
    Then grade the user post "Razørk Activoo"'s grade
    And grade the response code status for "Razørk Activoo" is 200

  #Sad Path 1
  Scenario: user makes call to POST /grade/A/1 (Summoner does not exist)
    When grade a GET request is made to the endpoint "/grade/A/1" (where A does not exist)
    Then grade the API should respond with a status code 404 (Not Found)
    And grade the response body should contain an error message indicating that the summoner was not found

  #Sad Path 2
  Scenario: user makes call to POST /grade/Belugafurtif/6 (grade is more than 5)
    When grade an invalid request is made to the endpoint "/grade/Belugafurtif/6" (where the summoner Name is missing)
    Then grade the API should respond with a status code 400 (Bad Request)
    And grade the response body should contain an error message indicating that the request is invalid