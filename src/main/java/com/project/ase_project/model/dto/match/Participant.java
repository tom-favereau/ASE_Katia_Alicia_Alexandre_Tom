package com.project.ase_project.model.dto.match;

/**
 * Represents a ParticipantDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store the personal information of every player.
 * This class is used by the Info class as an element in an ArrayList.
 */
public class Participant {
    // JSON FIELDS
    private int allInPings;
    private int assistMePings;
    private int assists;
    private int baronKills;
    private int basicPings;
    private int bountyLevel;
    private Challenges challenges;
    private int champExperience;
    private int champLevel;
    private int championId;
    private String championName;
    private int championTransform;
    private int commandPings;
    private int consumablesPurchased;
    private int damageDealtToBuildings;
    private int damageDealtToObjectives;
    private int damageDealtToTurrets;
    private int damageSelfMitigated;
    private int dangerPings;
    private int deaths;
    private int detectorWardsPlaced;
    private int doubleKills;
    private int dragonKills;
    private boolean eligibleForProgression;
    private int enemyMissingPings;
    private int enemyVisionPings;
    private boolean firstBloodAssist;
    private boolean firstBloodKill;
    private boolean firstTowerAssist;
    private boolean firstTowerKill;
    private boolean gameEndedInEarlySurrender;
    private boolean gameEndedInSurrender;
    private int getBackPings;
    private int goldEarned;
    private int goldSpent;
    private int holdPings;
    private String individualPosition;
    private int inhibitorKills;
    private int inhibitorTakedowns;
    private int inhibitorsLost;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int itemsPurchased;
    private int killingSprees;
    private int kills;
    private String lane;
    private int largestCriticalStrike;
    private int largestKillingSpree;
    private int largestMultiKill;
    private int longestTimeSpentLiving;
    private int magicDamageDealt;
    private int magicDamageDealtToChampions;
    private int magicDamageTaken;
    private Missions missions;
    private int needVisionPings;
    private int neutralMinionsKilled;
    private int nexusKills;
    private int nexusLost;
    private int nexusTakedowns;
    private int objectivesStolen;
    private int objectivesStolenAssists;
    private int onMyWayPings;
    private int participantId;
    private int pentaKills;
    private Perks perks;
    private int physicalDamageDealt;
    private int physicalDamageDealtToChampions;
    private int physicalDamageTaken;
    private int placement;
    private int playerAugment1;
    private int playerAugment2;
    private int playerAugment3;
    private int playerAugment4;
    private int playerScore0;
    private int playerScore1;
    private int playerScore10;
    private int playerScore11;
    private int playerScore2;
    private int playerScore3;
    private int playerScore4;
    private int playerScore5;
    private int playerScore6;
    private int playerScore7;
    private int playerScore8;
    private int playerScore9;
    private int playerSubteamId;
    private int profileIcon;
    private int pushPings;
    private String puuid;
    private int quadraKills;
    private String riotIdGameName;
    private String riotIdTagline;
    private String role;
    private int sightWardsBoughtInGame;
    private int spell1Casts;
    private int spell2Casts;
    private int spell3Casts;
    private int spell4Casts;
    private int subteamPlacement;
    private int summoner1Casts;
    private int summoner1Id;
    private int summoner2Casts;
    private int summoner2Id;
    private String summonerId;
    private int summonerLevel;
    private String summonerName;
    private boolean teamEarlySurrendered;
    private int teamId;
    private String teamPosition;
    private int timeCCingOthers;
    private int timePlayed;
    private int totalAllyJungleMinionsKilled;
    private int totalDamageDealt;
    private int totalDamageDealtToChampions;
    private int totalDamageShieldedOnTeammates;
    private int totalDamageTaken;
    private int totalEnemyJungleMinionsKilled;
    private int totalHeal;
    private int totalHealsOnTeammates;
    private int totalMinionsKilled;
    private int totalTimeCCDealt;
    private int totalTimeSpentDead;
    private int totalUnitsHealed;
    private int tripleKills;
    private int trueDamageDealt;
    private int trueDamageDealtToChampions;
    private int trueDamageTaken;
    private int turretKills;
    private int turretTakedowns;
    private int turretsLost;
    private int unrealKills;
    private int visionClearedPings;
    private int visionScore;
    private int visionWardsBoughtInGame;
    private int wardsKilled;
    private int wardsPlaced;
    private boolean win;

    // GETTERS AND SETTERS
    public int getAllInPings() {
        return allInPings;
    }
    public void setAllInPings(int allInPings) {
        this.allInPings = allInPings;
    }
    public int getAssistMePings() {
        return assistMePings;
    }
    public void setAssistMePings(int assistMePings) {
        this.assistMePings = assistMePings;
    }
    public int getAssists() {
        return assists;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }
    public int getBaronKills() {
        return baronKills;
    }
    public void setBaronKills(int baronKills) {
        this.baronKills = baronKills;
    }
    public int getBasicPings() {
        return basicPings;
    }
    public void setBasicPings(int basicPings) {
        this.basicPings = basicPings;
    }
    public int getBountyLevel() {
        return bountyLevel;
    }
    public void setBountyLevel(int bountyLevel) {
        this.bountyLevel = bountyLevel;
    }
    public Challenges getChallenges() {
        return challenges;
    }
    public void setChallenges(Challenges challenges) {
        this.challenges = challenges;
    }
    public int getChampExperience() {
        return champExperience;
    }
    public void setChampExperience(int champExperience) {
        this.champExperience = champExperience;
    }
    public int getChampLevel() {
        return champLevel;
    }
    public void setChampLevel(int champLevel) {
        this.champLevel = champLevel;
    }
    public int getChampionId() {
        return championId;
    }
    public void setChampionId(int championId) {
        this.championId = championId;
    }
    public String getChampionName() {
        return championName;
    }
    public void setChampionName(String championName) {
        this.championName = championName;
    }
    public int getChampionTransform() {
        return championTransform;
    }
    public void setChampionTransform(int championTransform) {
        this.championTransform = championTransform;
    }
    public int getCommandPings() {
        return commandPings;
    }
    public void setCommandPings(int commandPings) {
        this.commandPings = commandPings;
    }
    public int getConsumablesPurchased() {
        return consumablesPurchased;
    }
    public void setConsumablesPurchased(int consumablesPurchased) {
        this.consumablesPurchased = consumablesPurchased;
    }
    public int getDamageDealtToBuildings() {
        return damageDealtToBuildings;
    }
    public void setDamageDealtToBuildings(int damageDealtToBuildings) {
        this.damageDealtToBuildings = damageDealtToBuildings;
    }
    public int getDamageDealtToObjectives() {
        return damageDealtToObjectives;
    }
    public void setDamageDealtToObjectives(int damageDealtToObjectives) {
        this.damageDealtToObjectives = damageDealtToObjectives;
    }
    public int getDamageDealtToTurrets() {
        return damageDealtToTurrets;
    }
    public void setDamageDealtToTurrets(int damageDealtToTurrets) {
        this.damageDealtToTurrets = damageDealtToTurrets;
    }
    public int getDamageSelfMitigated() {
        return damageSelfMitigated;
    }
    public void setDamageSelfMitigated(int damageSelfMitigated) {
        this.damageSelfMitigated = damageSelfMitigated;
    }
    public int getDangerPings() {
        return dangerPings;
    }
    public void setDangerPings(int dangerPings) {
        this.dangerPings = dangerPings;
    }
    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    public int getDetectorWardsPlaced() {
        return detectorWardsPlaced;
    }
    public void setDetectorWardsPlaced(int detectorWardsPlaced) {
        this.detectorWardsPlaced = detectorWardsPlaced;
    }
    public int getDoubleKills() {
        return doubleKills;
    }
    public void setDoubleKills(int doubleKills) {
        this.doubleKills = doubleKills;
    }
    public int getDragonKills() {
        return dragonKills;
    }
    public void setDragonKills(int dragonKills) {
        this.dragonKills = dragonKills;
    }
    public boolean isEligibleForProgression() {
        return eligibleForProgression;
    }
    public void setEligibleForProgression(boolean eligibleForProgression) {
        this.eligibleForProgression = eligibleForProgression;
    }
    public int getEnemyMissingPings() {
        return enemyMissingPings;
    }
    public void setEnemyMissingPings(int enemyMissingPings) {
        this.enemyMissingPings = enemyMissingPings;
    }
    public int getEnemyVisionPings() {
        return enemyVisionPings;
    }
    public void setEnemyVisionPings(int enemyVisionPings) {
        this.enemyVisionPings = enemyVisionPings;
    }
    public boolean isFirstBloodAssist() {
        return firstBloodAssist;
    }
    public void setFirstBloodAssist(boolean firstBloodAssist) {
        this.firstBloodAssist = firstBloodAssist;
    }
    public boolean isFirstBloodKill() {
        return firstBloodKill;
    }
    public void setFirstBloodKill(boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }
    public boolean isFirstTowerAssist() {
        return firstTowerAssist;
    }
    public void setFirstTowerAssist(boolean firstTowerAssist) {
        this.firstTowerAssist = firstTowerAssist;
    }
    public boolean isFirstTowerKill() {
        return firstTowerKill;
    }
    public void setFirstTowerKill(boolean firstTowerKill) {
        this.firstTowerKill = firstTowerKill;
    }
    public boolean isGameEndedInEarlySurrender() {
        return gameEndedInEarlySurrender;
    }
    public void setGameEndedInEarlySurrender(boolean gameEndedInEarlySurrender) {
        this.gameEndedInEarlySurrender = gameEndedInEarlySurrender;
    }
    public boolean isGameEndedInSurrender() {
        return gameEndedInSurrender;
    }
    public void setGameEndedInSurrender(boolean gameEndedInSurrender) {
        this.gameEndedInSurrender = gameEndedInSurrender;
    }
    public int getGetBackPings() {
        return getBackPings;
    }
    public void setGetBackPings(int getBackPings) {
        this.getBackPings = getBackPings;
    }
    public int getGoldEarned() {
        return goldEarned;
    }
    public void setGoldEarned(int goldEarned) {
        this.goldEarned = goldEarned;
    }
    public int getGoldSpent() {
        return goldSpent;
    }
    public void setGoldSpent(int goldSpent) {
        this.goldSpent = goldSpent;
    }
    public int getHoldPings() {
        return holdPings;
    }
    public void setHoldPings(int holdPings) {
        this.holdPings = holdPings;
    }
    public String getIndividualPosition() {
        return individualPosition;
    }
    public void setIndividualPosition(String individualPosition) {
        this.individualPosition = individualPosition;
    }
    public int getInhibitorKills() {
        return inhibitorKills;
    }
    public void setInhibitorKills(int inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }
    public int getInhibitorTakedowns() {
        return inhibitorTakedowns;
    }
    public void setInhibitorTakedowns(int inhibitorTakedowns) {
        this.inhibitorTakedowns = inhibitorTakedowns;
    }
    public int getInhibitorsLost() {
        return inhibitorsLost;
    }
    public void setInhibitorsLost(int inhibitorsLost) {
        this.inhibitorsLost = inhibitorsLost;
    }
    public int getItem0() {
        return item0;
    }
    public void setItem0(int item0) {
        this.item0 = item0;
    }
    public int getItem1() {
        return item1;
    }
    public void setItem1(int item1) {
        this.item1 = item1;
    }
    public int getItem2() {
        return item2;
    }
    public void setItem2(int item2) {
        this.item2 = item2;
    }
    public int getItem3() {
        return item3;
    }
    public void setItem3(int item3) {
        this.item3 = item3;
    }
    public int getItem4() {
        return item4;
    }
    public void setItem4(int item4) {
        this.item4 = item4;
    }
    public int getItem5() {
        return item5;
    }
    public void setItem5(int item5) {
        this.item5 = item5;
    }
    public int getItem6() {
        return item6;
    }
    public void setItem6(int item6) {
        this.item6 = item6;
    }
    public int getItemsPurchased() {
        return itemsPurchased;
    }
    public void setItemsPurchased(int itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }
    public int getKillingSprees() {
        return killingSprees;
    }
    public void setKillingSprees(int killingSprees) {
        this.killingSprees = killingSprees;
    }
    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }
    public String getLane() {
        return lane;
    }
    public void setLane(String lane) {
        this.lane = lane;
    }
    public int getLargestCriticalStrike() {
        return largestCriticalStrike;
    }
    public void setLargestCriticalStrike(int largestCriticalStrike) {
        this.largestCriticalStrike = largestCriticalStrike;
    }
    public int getLargestKillingSpree() {
        return largestKillingSpree;
    }
    public void setLargestKillingSpree(int largestKillingSpree) {
        this.largestKillingSpree = largestKillingSpree;
    }
    public int getLargestMultiKill() {
        return largestMultiKill;
    }
    public void setLargestMultiKill(int largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }
    public int getLongestTimeSpentLiving() {
        return longestTimeSpentLiving;
    }
    public void setLongestTimeSpentLiving(int longestTimeSpentLiving) {
        this.longestTimeSpentLiving = longestTimeSpentLiving;
    }
    public int getMagicDamageDealt() {
        return magicDamageDealt;
    }
    public void setMagicDamageDealt(int magicDamageDealt) {
        this.magicDamageDealt = magicDamageDealt;
    }
    public int getMagicDamageDealtToChampions() {
        return magicDamageDealtToChampions;
    }
    public void setMagicDamageDealtToChampions(int magicDamageDealtToChampions) {
        this.magicDamageDealtToChampions = magicDamageDealtToChampions;
    }
    public int getMagicDamageTaken() {
        return magicDamageTaken;
    }
    public void setMagicDamageTaken(int magicDamageTaken) {
        this.magicDamageTaken = magicDamageTaken;
    }
    public Missions getMissions() {
        return missions;
    }
    public void setMissions(Missions missions) {
        this.missions = missions;
    }
    public int getNeedVisionPings() {
        return needVisionPings;
    }
    public void setNeedVisionPings(int needVisionPings) {
        this.needVisionPings = needVisionPings;
    }
    public int getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }
    public void setNeutralMinionsKilled(int neutralMinionsKilled) {
        this.neutralMinionsKilled = neutralMinionsKilled;
    }
    public int getNexusKills() {
        return nexusKills;
    }
    public void setNexusKills(int nexusKills) {
        this.nexusKills = nexusKills;
    }
    public int getNexusLost() {
        return nexusLost;
    }
    public void setNexusLost(int nexusLost) {
        this.nexusLost = nexusLost;
    }
    public int getNexusTakedowns() {
        return nexusTakedowns;
    }
    public void setNexusTakedowns(int nexusTakedowns) {
        this.nexusTakedowns = nexusTakedowns;
    }
    public int getObjectivesStolen() {
        return objectivesStolen;
    }
    public void setObjectivesStolen(int objectivesStolen) {
        this.objectivesStolen = objectivesStolen;
    }
    public int getObjectivesStolenAssists() {
        return objectivesStolenAssists;
    }
    public void setObjectivesStolenAssists(int objectivesStolenAssists) {
        this.objectivesStolenAssists = objectivesStolenAssists;
    }
    public int getOnMyWayPings() {
        return onMyWayPings;
    }
    public void setOnMyWayPings(int onMyWayPings) {
        this.onMyWayPings = onMyWayPings;
    }
    public int getParticipantId() {
        return participantId;
    }
    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }
    public int getPentaKills() {
        return pentaKills;
    }
    public void setPentaKills(int pentaKills) {
        this.pentaKills = pentaKills;
    }
    public Perks getPerks() {
        return perks;
    }
    public void setPerks(Perks perks) {
        this.perks = perks;
    }
    public int getPhysicalDamageDealt() {
        return physicalDamageDealt;
    }
    public void setPhysicalDamageDealt(int physicalDamageDealt) {
        this.physicalDamageDealt = physicalDamageDealt;
    }
    public int getPhysicalDamageDealtToChampions() {
        return physicalDamageDealtToChampions;
    }
    public void setPhysicalDamageDealtToChampions(int physicalDamageDealtToChampions) {
        this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
    }
    public int getPhysicalDamageTaken() {
        return physicalDamageTaken;
    }
    public void setPhysicalDamageTaken(int physicalDamageTaken) {
        this.physicalDamageTaken = physicalDamageTaken;
    }
    public int getPlacement() {
        return placement;
    }
    public void setPlacement(int placement) {
        this.placement = placement;
    }
    public int getPlayerAugment1() {
        return playerAugment1;
    }
    public void setPlayerAugment1(int playerAugment1) {
        this.playerAugment1 = playerAugment1;
    }
    public int getPlayerAugment2() {
        return playerAugment2;
    }
    public void setPlayerAugment2(int playerAugment2) {
        this.playerAugment2 = playerAugment2;
    }
    public int getPlayerAugment3() {
        return playerAugment3;
    }
    public void setPlayerAugment3(int playerAugment3) {
        this.playerAugment3 = playerAugment3;
    }
    public int getPlayerAugment4() {
        return playerAugment4;
    }
    public void setPlayerAugment4(int playerAugment4) {
        this.playerAugment4 = playerAugment4;
    }
    public int getPlayerScore0() {
        return playerScore0;
    }
    public void setPlayerScore0(int playerScore0) {
        this.playerScore0 = playerScore0;
    }
    public int getPlayerScore1() {
        return playerScore1;
    }
    public void setPlayerScore1(int playerScore1) {
        this.playerScore1 = playerScore1;
    }
    public int getPlayerScore10() {
        return playerScore10;
    }
    public void setPlayerScore10(int playerScore10) {
        this.playerScore10 = playerScore10;
    }
    public int getPlayerScore11() {
        return playerScore11;
    }
    public void setPlayerScore11(int playerScore11) {
        this.playerScore11 = playerScore11;
    }
    public int getPlayerScore2() {
        return playerScore2;
    }
    public void setPlayerScore2(int playerScore2) {
        this.playerScore2 = playerScore2;
    }
    public int getPlayerScore3() {
        return playerScore3;
    }
    public void setPlayerScore3(int playerScore3) {
        this.playerScore3 = playerScore3;
    }
    public int getPlayerScore4() {
        return playerScore4;
    }
    public void setPlayerScore4(int playerScore4) {
        this.playerScore4 = playerScore4;
    }
    public int getPlayerScore5() {
        return playerScore5;
    }
    public void setPlayerScore5(int playerScore5) {
        this.playerScore5 = playerScore5;
    }
    public int getPlayerScore6() {
        return playerScore6;
    }
    public void setPlayerScore6(int playerScore6) {
        this.playerScore6 = playerScore6;
    }
    public int getPlayerScore7() {
        return playerScore7;
    }
    public void setPlayerScore7(int playerScore7) {
        this.playerScore7 = playerScore7;
    }
    public int getPlayerScore8() {
        return playerScore8;
    }
    public void setPlayerScore8(int playerScore8) {
        this.playerScore8 = playerScore8;
    }
    public int getPlayerScore9() {
        return playerScore9;
    }
    public void setPlayerScore9(int playerScore9) {
        this.playerScore9 = playerScore9;
    }
    public int getPlayerSubteamId() {
        return playerSubteamId;
    }
    public void setPlayerSubteamId(int playerSubteamId) {
        this.playerSubteamId = playerSubteamId;
    }
    public int getProfileIcon() {
        return profileIcon;
    }
    public void setProfileIcon(int profileIcon) {
        this.profileIcon = profileIcon;
    }
    public int getPushPings() {
        return pushPings;
    }
    public void setPushPings(int pushPings) {
        this.pushPings = pushPings;
    }
    public String getPuuid() {
        return puuid;
    }
    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }
    public int getQuadraKills() {
        return quadraKills;
    }
    public void setQuadraKills(int quadraKills) {
        this.quadraKills = quadraKills;
    }
    public String getRiotIdGameName() {
        return riotIdGameName;
    }
    public void setRiotIdGameName(String riotIdGameName) {
        this.riotIdGameName = riotIdGameName;
    }
    public String getRiotIdTagline() {
        return riotIdTagline;
    }
    public void setRiotIdTagline(String riotIdTagline) {
        this.riotIdTagline = riotIdTagline;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public int getSightWardsBoughtInGame() {
        return sightWardsBoughtInGame;
    }
    public void setSightWardsBoughtInGame(int sightWardsBoughtInGame) {
        this.sightWardsBoughtInGame = sightWardsBoughtInGame;
    }
    public int getSpell1Casts() {
        return spell1Casts;
    }
    public void setSpell1Casts(int spell1Casts) {
        this.spell1Casts = spell1Casts;
    }
    public int getSpell2Casts() {
        return spell2Casts;
    }
    public void setSpell2Casts(int spell2Casts) {
        this.spell2Casts = spell2Casts;
    }
    public int getSpell3Casts() {
        return spell3Casts;
    }
    public void setSpell3Casts(int spell3Casts) {
        this.spell3Casts = spell3Casts;
    }
    public int getSpell4Casts() {
        return spell4Casts;
    }
    public void setSpell4Casts(int spell4Casts) {
        this.spell4Casts = spell4Casts;
    }
    public int getSubteamPlacement() {
        return subteamPlacement;
    }
    public void setSubteamPlacement(int subteamPlacement) {
        this.subteamPlacement = subteamPlacement;
    }
    public int getSummoner1Casts() {
        return summoner1Casts;
    }
    public void setSummoner1Casts(int summoner1Casts) {
        this.summoner1Casts = summoner1Casts;
    }
    public int getSummoner1Id() {
        return summoner1Id;
    }
    public void setSummoner1Id(int summoner1Id) {
        this.summoner1Id = summoner1Id;
    }
    public int getSummoner2Casts() {
        return summoner2Casts;
    }
    public void setSummoner2Casts(int summoner2Casts) {
        this.summoner2Casts = summoner2Casts;
    }
    public int getSummoner2Id() {
        return summoner2Id;
    }
    public void setSummoner2Id(int summoner2Id) {
        this.summoner2Id = summoner2Id;
    }
    public String getSummonerId() {
        return summonerId;
    }
    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
    public int getSummonerLevel() {
        return summonerLevel;
    }
    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
    public String getSummonerName() {
        return summonerName;
    }
    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }
    public boolean isTeamEarlySurrendered() {
        return teamEarlySurrendered;
    }
    public void setTeamEarlySurrendered(boolean teamEarlySurrendered) {
        this.teamEarlySurrendered = teamEarlySurrendered;
    }
    public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
    public String getTeamPosition() {
        return teamPosition;
    }
    public void setTeamPosition(String teamPosition) {
        this.teamPosition = teamPosition;
    }
    public int getTimeCCingOthers() {
        return timeCCingOthers;
    }
    public void setTimeCCingOthers(int timeCCingOthers) {
        this.timeCCingOthers = timeCCingOthers;
    }
    public int getTimePlayed() {
        return timePlayed;
    }
    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }
    public int getTotalAllyJungleMinionsKilled() {
        return totalAllyJungleMinionsKilled;
    }
    public void setTotalAllyJungleMinionsKilled(int totalAllyJungleMinionsKilled) {
        this.totalAllyJungleMinionsKilled = totalAllyJungleMinionsKilled;
    }
    public int getTotalDamageDealt() {
        return totalDamageDealt;
    }
    public void setTotalDamageDealt(int totalDamageDealt) {
        this.totalDamageDealt = totalDamageDealt;
    }
    public int getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }
    public void setTotalDamageDealtToChampions(int totalDamageDealtToChampions) {
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
    }
    public int getTotalDamageShieldedOnTeammates() {
        return totalDamageShieldedOnTeammates;
    }
    public void setTotalDamageShieldedOnTeammates(int totalDamageShieldedOnTeammates) {
        this.totalDamageShieldedOnTeammates = totalDamageShieldedOnTeammates;
    }
    public int getTotalDamageTaken() {
        return totalDamageTaken;
    }
    public void setTotalDamageTaken(int totalDamageTaken) {
        this.totalDamageTaken = totalDamageTaken;
    }
    public int getTotalEnemyJungleMinionsKilled() {
        return totalEnemyJungleMinionsKilled;
    }
    public void setTotalEnemyJungleMinionsKilled(int totalEnemyJungleMinionsKilled) {
        this.totalEnemyJungleMinionsKilled = totalEnemyJungleMinionsKilled;
    }
    public int getTotalHeal() {
        return totalHeal;
    }
    public void setTotalHeal(int totalHeal) {
        this.totalHeal = totalHeal;
    }
    public int getTotalHealsOnTeammates() {
        return totalHealsOnTeammates;
    }
    public void setTotalHealsOnTeammates(int totalHealsOnTeammates) {
        this.totalHealsOnTeammates = totalHealsOnTeammates;
    }
    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }
    public void setTotalMinionsKilled(int totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }
    public int getTotalTimeCCDealt() {
        return totalTimeCCDealt;
    }
    public void setTotalTimeCCDealt(int totalTimeCCDealt) {
        this.totalTimeCCDealt = totalTimeCCDealt;
    }
    public int getTotalTimeSpentDead() {
        return totalTimeSpentDead;
    }
    public void setTotalTimeSpentDead(int totalTimeSpentDead) {
        this.totalTimeSpentDead = totalTimeSpentDead;
    }
    public int getTotalUnitsHealed() {
        return totalUnitsHealed;
    }
    public void setTotalUnitsHealed(int totalUnitsHealed) {
        this.totalUnitsHealed = totalUnitsHealed;
    }
    public int getTripleKills() {
        return tripleKills;
    }
    public void setTripleKills(int tripleKills) {
        this.tripleKills = tripleKills;
    }
    public int getTrueDamageDealt() {
        return trueDamageDealt;
    }
    public void setTrueDamageDealt(int trueDamageDealt) {
        this.trueDamageDealt = trueDamageDealt;
    }
    public int getTrueDamageDealtToChampions() {
        return trueDamageDealtToChampions;
    }
    public void setTrueDamageDealtToChampions(int trueDamageDealtToChampions) {
        this.trueDamageDealtToChampions = trueDamageDealtToChampions;
    }
    public int getTrueDamageTaken() {
        return trueDamageTaken;
    }
    public void setTrueDamageTaken(int trueDamageTaken) {
        this.trueDamageTaken = trueDamageTaken;
    }
    public int getTurretKills() {
        return turretKills;
    }
    public void setTurretKills(int turretKills) {
        this.turretKills = turretKills;
    }
    public int getTurretTakedowns() {
        return turretTakedowns;
    }
    public void setTurretTakedowns(int turretTakedowns) {
        this.turretTakedowns = turretTakedowns;
    }
    public int getTurretsLost() {
        return turretsLost;
    }
    public void setTurretsLost(int turretsLost) {
        this.turretsLost = turretsLost;
    }
    public int getUnrealKills() {
        return unrealKills;
    }
    public void setUnrealKills(int unrealKills) {
        this.unrealKills = unrealKills;
    }
    public int getVisionClearedPings() {
        return visionClearedPings;
    }
    public void setVisionClearedPings(int visionClearedPings) {
        this.visionClearedPings = visionClearedPings;
    }
    public int getVisionScore() {
        return visionScore;
    }
    public void setVisionScore(int visionScore) {
        this.visionScore = visionScore;
    }
    public int getVisionWardsBoughtInGame() {
        return visionWardsBoughtInGame;
    }
    public void setVisionWardsBoughtInGame(int visionWardsBoughtInGame) {
        this.visionWardsBoughtInGame = visionWardsBoughtInGame;
    }
    public int getWardsKilled() {
        return wardsKilled;
    }
    public void setWardsKilled(int wardsKilled) {
        this.wardsKilled = wardsKilled;
    }
    public int getWardsPlaced() {
        return wardsPlaced;
    }
    public void setWardsPlaced(int wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }
    public boolean getWin() {
        return win;
    }
    public void setWin(boolean win) {
        this.win = win;
    }

    // TO STRING

    @Override
    public String toString() {
        return "Participant{" +
                "allInPings=" + allInPings +
                ", assistMePings=" + assistMePings +
                ", assists=" + assists +
                ", baronKills=" + baronKills +
                ", basicPings=" + basicPings +
                ", bountyLevel=" + bountyLevel +
                ", challenges=" + challenges +
                ", champExperience=" + champExperience +
                ", champLevel=" + champLevel +
                ", championId=" + championId +
                ", championName='" + championName + '\'' +
                ", championTransform=" + championTransform +
                ", commandPings=" + commandPings +
                ", consumablesPurchased=" + consumablesPurchased +
                ", damageDealtToBuildings=" + damageDealtToBuildings +
                ", damageDealtToObjectives=" + damageDealtToObjectives +
                ", damageDealtToTurrets=" + damageDealtToTurrets +
                ", damageSelfMitigated=" + damageSelfMitigated +
                ", dangerPings=" + dangerPings +
                ", deaths=" + deaths +
                ", detectorWardsPlaced=" + detectorWardsPlaced +
                ", doubleKills=" + doubleKills +
                ", dragonKills=" + dragonKills +
                ", eligibleForProgression=" + eligibleForProgression +
                ", enemyMissingPings=" + enemyMissingPings +
                ", enemyVisionPings=" + enemyVisionPings +
                ", firstBloodAssist=" + firstBloodAssist +
                ", firstBloodKill=" + firstBloodKill +
                ", firstTowerAssist=" + firstTowerAssist +
                ", firstTowerKill=" + firstTowerKill +
                ", gameEndedInEarlySurrender=" + gameEndedInEarlySurrender +
                ", gameEndedInSurrender=" + gameEndedInSurrender +
                ", getBackPings=" + getBackPings +
                ", goldEarned=" + goldEarned +
                ", goldSpent=" + goldSpent +
                ", holdPings=" + holdPings +
                ", individualPosition='" + individualPosition + '\'' +
                ", inhibitorKills=" + inhibitorKills +
                ", inhibitorTakedowns=" + inhibitorTakedowns +
                ", inhibitorsLost=" + inhibitorsLost +
                ", item0=" + item0 +
                ", item1=" + item1 +
                ", item2=" + item2 +
                ", item3=" + item3 +
                ", item4=" + item4 +
                ", item5=" + item5 +
                ", item6=" + item6 +
                ", itemsPurchased=" + itemsPurchased +
                ", killingSprees=" + killingSprees +
                ", kills=" + kills +
                ", lane='" + lane + '\'' +
                ", largestCriticalStrike=" + largestCriticalStrike +
                ", largestKillingSpree=" + largestKillingSpree +
                ", largestMultiKill=" + largestMultiKill +
                ", longestTimeSpentLiving=" + longestTimeSpentLiving +
                ", magicDamageDealt=" + magicDamageDealt +
                ", magicDamageDealtToChampions=" + magicDamageDealtToChampions +
                ", magicDamageTaken=" + magicDamageTaken +
                ", missions=" + missions +
                ", needVisionPings=" + needVisionPings +
                ", neutralMinionsKilled=" + neutralMinionsKilled +
                ", nexusKills=" + nexusKills +
                ", nexusLost=" + nexusLost +
                ", nexusTakedowns=" + nexusTakedowns +
                ", objectivesStolen=" + objectivesStolen +
                ", objectivesStolenAssists=" + objectivesStolenAssists +
                ", onMyWayPings=" + onMyWayPings +
                ", participantId=" + participantId +
                ", pentaKills=" + pentaKills +
                ", perks=" + perks +
                ", physicalDamageDealt=" + physicalDamageDealt +
                ", physicalDamageDealtToChampions=" + physicalDamageDealtToChampions +
                ", physicalDamageTaken=" + physicalDamageTaken +
                ", placement=" + placement +
                ", playerAugment1=" + playerAugment1 +
                ", playerAugment2=" + playerAugment2 +
                ", playerAugment3=" + playerAugment3 +
                ", playerAugment4=" + playerAugment4 +
                ", playerScore0=" + playerScore0 +
                ", playerScore1=" + playerScore1 +
                ", playerScore10=" + playerScore10 +
                ", playerScore11=" + playerScore11 +
                ", playerScore2=" + playerScore2 +
                ", playerScore3=" + playerScore3 +
                ", playerScore4=" + playerScore4 +
                ", playerScore5=" + playerScore5 +
                ", playerScore6=" + playerScore6 +
                ", playerScore7=" + playerScore7 +
                ", playerScore8=" + playerScore8 +
                ", playerScore9=" + playerScore9 +
                ", playerSubteamId=" + playerSubteamId +
                ", profileIcon=" + profileIcon +
                ", pushPings=" + pushPings +
                ", puuid='" + puuid + '\'' +
                ", quadraKills=" + quadraKills +
                ", riotIdGameName='" + riotIdGameName + '\'' +
                ", riotIdTagline='" + riotIdTagline + '\'' +
                ", role='" + role + '\'' +
                ", sightWardsBoughtInGame=" + sightWardsBoughtInGame +
                ", spell1Casts=" + spell1Casts +
                ", spell2Casts=" + spell2Casts +
                ", spell3Casts=" + spell3Casts +
                ", spell4Casts=" + spell4Casts +
                ", subteamPlacement=" + subteamPlacement +
                ", summoner1Casts=" + summoner1Casts +
                ", summoner1Id=" + summoner1Id +
                ", summoner2Casts=" + summoner2Casts +
                ", summoner2Id=" + summoner2Id +
                ", summonerId='" + summonerId + '\'' +
                ", summonerLevel=" + summonerLevel +
                ", summonerName='" + summonerName + '\'' +
                ", teamEarlySurrendered=" + teamEarlySurrendered +
                ", teamId=" + teamId +
                ", teamPosition='" + teamPosition + '\'' +
                ", timeCCingOthers=" + timeCCingOthers +
                ", timePlayed=" + timePlayed +
                ", totalAllyJungleMinionsKilled=" + totalAllyJungleMinionsKilled +
                ", totalDamageDealt=" + totalDamageDealt +
                ", totalDamageDealtToChampions=" + totalDamageDealtToChampions +
                ", totalDamageShieldedOnTeammates=" + totalDamageShieldedOnTeammates +
                ", totalDamageTaken=" + totalDamageTaken +
                ", totalEnemyJungleMinionsKilled=" + totalEnemyJungleMinionsKilled +
                ", totalHeal=" + totalHeal +
                ", totalHealsOnTeammates=" + totalHealsOnTeammates +
                ", totalMinionsKilled=" + totalMinionsKilled +
                ", totalTimeCCDealt=" + totalTimeCCDealt +
                ", totalTimeSpentDead=" + totalTimeSpentDead +
                ", totalUnitsHealed=" + totalUnitsHealed +
                ", tripleKills=" + tripleKills +
                ", trueDamageDealt=" + trueDamageDealt +
                ", trueDamageDealtToChampions=" + trueDamageDealtToChampions +
                ", trueDamageTaken=" + trueDamageTaken +
                ", turretKills=" + turretKills +
                ", turretTakedowns=" + turretTakedowns +
                ", turretsLost=" + turretsLost +
                ", unrealKills=" + unrealKills +
                ", visionClearedPings=" + visionClearedPings +
                ", visionScore=" + visionScore +
                ", visionWardsBoughtInGame=" + visionWardsBoughtInGame +
                ", wardsKilled=" + wardsKilled +
                ", wardsPlaced=" + wardsPlaced +
                ", win=" + win +
                '}';
    }
}