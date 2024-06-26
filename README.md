# OP-GG like League Of Legends Stat viewer

![front-end](images/guardPage.png)

## Team
- Katia BRENAUT
- Tom FAVEREAU
- Alicia SHAO
- Alexandre TOMCZAK

## API Riot
Consumed API is RiotGames' API for League of Legends (documentation [here](https://developer.riotgames.com/docs/lol)).

**THE BY SUMMONER NAME ENDPOINT USED IN THIS PROJECT IS GOING TO BE DELETED ON THE 22/04/24.
THE PROJECT AS IT IS WILL NO LONGER WORK AFTER THIS DATE.**

## Running the project
Run `AseProjectApplication.java` with IntelliJ to launch the project on port 8080.
(`src/main/java/com/project/ase_project/AseProjectApplication.java`).

## Supabase limitations
Our external database is supplied by Supabase.

Do note that because of the limitations on requests imposed by Supabase, running the application
on more than 2 computers at the same time is difficult. 

Similarly, all the tests cannot be run at the same time, so either cucumber tests can be run, or unit tests can be run.

**Please notify us about the timing of the reviewing process, because if there are no requests made on the database for more
than a week, the database is automatically put in sleep mode by Supabase.**

## Documentation
Once it's launched, documentation is available at [http://localhost:8080/docs](http://localhost:8080/docs).

## Brief project description
### Frontend
Frontend is accessible at [http:/localhost:8080](http://localhost:8080).

UI drafts are in the `Brouillon UI.png` file.

### Features
- GET: summoner summary
- GET: statistics on champions played by a summoner
- GET: statistics on gamemodes played by a summoner
- POST: a rating (from 1 to 5) for a summoner

API endpoints are described in the documentation.

### Scenarii
Tested with Cucumber, feature scenarii are found in `src/test/resources/com/project/ase_project/cucumber`.

Briefly, for GET features:

- Happy path 1: GET with a no special characters
- Happy path 2: GET with special characters
- Sad path 1: GET with a summoner that doesn't exist
- Sad path 2: GET with an illegal request (missing argument)

For the POST feature:

- Happy path 1: GET with a no special characters
- Happy path 2: GET with special characters
- Sad path 1: GET with a summoner that doesn't exist
- Sad path 2: GET with an illegal request (rating not in [1, 5])


## Pipeline du projet
```mermaid
block-beta
    columns 3
    doc>"API Riot"]:3
    space down1<[" "]>(down) space

  block:e:3
          l["requête"]
          m("traitement")
          r["compilation"]
  end
    space down2<[" "]>(down) space
    calculs(("calculs")):3
    space:3
    db[("db")]
    db space APIWeb
    calculs --> db
    APIWeb --> calculs
    db --> APIWeb
    db --> calculs
    
```



