package com.project.ase_project.model.dto.match;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Represents a ChallengesDto object from the Riot API that is not actually identified or mentioned in the API documentation.
 * This class is used to store information regarding the challenges accomplished in a game.
 * This class is used by the Participant class as an attribute.
 */
public class Challenges {
    // JSON FIELDS
    @JsonProperty("12AssistStreakCount")
    private int _12AssistStreakCount;
    private int abilityUses;
    private int acesBefore15Minutes;
    private int alliedJungleMonsterKills;
    private int baronBuffGoldAdvantageOverThreshold;
    private int baronTakedowns;
    private int blastConeOppositeOpponentCount;
    private int bountyGold;
    private int buffsStolen;
    private int completeSupportQuestInTime;
    private int controlWardsPlaced;
    private double damagePerMinute;
    private double damageTakenOnTeamPercentage;
    private int dancedWithRiftHerald;
    private int deathsByEnemyChamps;
    private int dodgeSkillShotsSmallWindow;
    private int doubleAces;
    private int dragonTakedowns;
    private double earliestBaron;
    private double earliestDragonTakedown;
    private int earlyLaningPhaseGoldExpAdvantage;
    private double effectiveHealAndShielding;
    private int elderDragonKillsWithOpposingSoul;
    private int elderDragonMultikills;
    private int enemyChampionImmobilizations;
    private int enemyJungleMonsterKills;
    private int epicMonsterKillsNearEnemyJungler;
    private int epicMonsterKillsWithin30SecondsOfSpawn;
    private int epicMonsterSteals;
    private int epicMonsterStolenWithoutSmite;
    private int firstTurretKilled;
    private int flawlessAces;
    private int fullTeamTakedown;
    private double gameLength;
    private int getTakedownsInAllLanesEarlyJungleAsLaner;
    private double goldPerMinute;
    private int hadOpenNexus;
    private int immobilizeAndKillWithAlly;
    private int initialBuffCount;
    private int initialCrabCount;
    private double jungleCsBefore10Minutes;
    private int junglerTakedownsNearDamagedEpicMonster;
    private int kTurretsDestroyedBeforePlatesFall;
    private double kda;
    private int killAfterHiddenWithAlly;
    private double killParticipation;
    private int killedChampTookFullTeamDamageSurvived;
    private int killingSprees;
    private int killsNearEnemyTurret;
    private int killsOnOtherLanesEarlyJungleAsLaner;
    private int killsOnRecentlyHealedByAramPack;
    private int killsUnderOwnTurret;
    private int killsWithHelpFromEpicMonster;
    private int knockEnemyIntoTeamAndKill;
    private int landSkillShotsEarlyGame;
    private int laneMinionsFirst10Minutes;
    private int laningPhaseGoldExpAdvantage;
    private int legendaryCount;
    private ArrayList<Integer> legendaryItemUsed;
    private int lostAnInhibitor;
    private double maxCsAdvantageOnLaneOpponent;
    private int maxKillDeficit;
    private int maxLevelLeadLaneOpponent;
    private int mejaisFullStackInTime;
    private double moreEnemyJungleThanOpponent;
    private int multiKillOneSpell;
    private int multiTurretRiftHeraldCount;
    private int multikills;
    private int multikillsAfterAggressiveFlash;
    private int outerTurretExecutesBefore10Minutes;
    private int outnumberedKills;
    private int outnumberedNexusKill;
    private int perfectDragonSoulsTaken;
    private int perfectGame;
    private int pickKillWithAlly;
    private int playedChampSelectPosition;
    private int poroExplosions;
    private int quickCleanse;
    private int quickFirstTurret;
    private int quickSoloKills;
    private int riftHeraldTakedowns;
    private int saveAllyFromDeath;
    private int scuttleCrabKills;
    private int skillshotsDodged;
    private int skillshotsHit;
    private int snowballsHit;
    private int soloBaronKills;
    private int soloKills;
    private int soloTurretsLategame;
    private int stealthWardsPlaced;
    private int survivedSingleDigitHpCount;
    private int survivedThreeImmobilizesInFight;
    private int takedownOnFirstTurret;
    private int takedowns;
    private int takedownsAfterGainingLevelAdvantage;
    private int takedownsBeforeJungleMinionSpawn;
    private int takedownsFirstXMinutes;
    private int takedownsInAlcove;
    private int takedownsInEnemyFountain;
    private int teamBaronKills;
    private double teamDamagePercentage;
    private int teamElderDragonKills;
    private int teamRiftHeraldKills;
    private int tookLargeDamageSurvived;
    private int turretPlatesTaken;
    private int turretTakedowns;
    private int turretsTakenWithRiftHerald;
    private int twentyMinionsIn3SecondsCount;
    private int twoWardsOneSweeperCount;
    private int unseenRecalls;
    private double visionScoreAdvantageLaneOpponent;
    private double visionScorePerMinute;
    private int wardTakedowns;
    private int wardTakedownsBefore20M;
    private int wardsGuarded;
    private int junglerKillsEarlyJungle;
    private int killsOnLanersEarlyJungleAsJungler;
    private double shortestTimeToAceFromFirstTakedown;
    private int teleportTakedowns;
    private double fastestLegendary;
    private double controlWardTimeCoverageInRiverOrEnemyHalf;
    private int highestWardKills;
    private double firstTurretKilledTime;
    private int highestChampionDamage;
    private int fasterSupportQuestCompletion;

    // GETTERS AND SETTERS
    public int get_12AssistStreakCount() {
        return _12AssistStreakCount;
    }
    public void set_12AssistStreakCount(int _12AssistStreakCount) {
        this._12AssistStreakCount = _12AssistStreakCount;
    }
    public int getAbilityUses() {
        return abilityUses;
    }
    public void setAbilityUses(int abilityUses) {
        this.abilityUses = abilityUses;
    }
    public int getAcesBefore15Minutes() {
        return acesBefore15Minutes;
    }
    public void setAcesBefore15Minutes(int acesBefore15Minutes) {
        this.acesBefore15Minutes = acesBefore15Minutes;
    }
    public int getAlliedJungleMonsterKills() {
        return alliedJungleMonsterKills;
    }
    public void setAlliedJungleMonsterKills(int alliedJungleMonsterKills) {
        this.alliedJungleMonsterKills = alliedJungleMonsterKills;
    }
    public int getBaronBuffGoldAdvantageOverThreshold() {
        return baronBuffGoldAdvantageOverThreshold;
    }
    public void setBaronBuffGoldAdvantageOverThreshold(int baronBuffGoldAdvantageOverThreshold) {
        this.baronBuffGoldAdvantageOverThreshold = baronBuffGoldAdvantageOverThreshold;
    }
    public int getBaronTakedowns() {
        return baronTakedowns;
    }
    public void setBaronTakedowns(int baronTakedowns) {
        this.baronTakedowns = baronTakedowns;
    }
    public int getBlastConeOppositeOpponentCount() {
        return blastConeOppositeOpponentCount;
    }
    public void setBlastConeOppositeOpponentCount(int blastConeOppositeOpponentCount) {
        this.blastConeOppositeOpponentCount = blastConeOppositeOpponentCount;
    }
    public int getBountyGold() {
        return bountyGold;
    }
    public void setBountyGold(int bountyGold) {
        this.bountyGold = bountyGold;
    }
    public int getBuffsStolen() {
        return buffsStolen;
    }
    public void setBuffsStolen(int buffsStolen) {
        this.buffsStolen = buffsStolen;
    }
    public int getCompleteSupportQuestInTime() {
        return completeSupportQuestInTime;
    }
    public void setCompleteSupportQuestInTime(int completeSupportQuestInTime) {
        this.completeSupportQuestInTime = completeSupportQuestInTime;
    }
    public int getControlWardsPlaced() {
        return controlWardsPlaced;
    }
    public void setControlWardsPlaced(int controlWardsPlaced) {
        this.controlWardsPlaced = controlWardsPlaced;
    }
    public double getDamagePerMinute() {
        return damagePerMinute;
    }
    public void setDamagePerMinute(double damagePerMinute) {
        this.damagePerMinute = damagePerMinute;
    }
    public double getDamageTakenOnTeamPercentage() {
        return damageTakenOnTeamPercentage;
    }
    public void setDamageTakenOnTeamPercentage(double damageTakenOnTeamPercentage) {
        this.damageTakenOnTeamPercentage = damageTakenOnTeamPercentage;
    }
    public int getDancedWithRiftHerald() {
        return dancedWithRiftHerald;
    }
    public void setDancedWithRiftHerald(int dancedWithRiftHerald) {
        this.dancedWithRiftHerald = dancedWithRiftHerald;
    }
    public int getDeathsByEnemyChamps() {
        return deathsByEnemyChamps;
    }
    public void setDeathsByEnemyChamps(int deathsByEnemyChamps) {
        this.deathsByEnemyChamps = deathsByEnemyChamps;
    }
    public int getDodgeSkillShotsSmallWindow() {
        return dodgeSkillShotsSmallWindow;
    }
    public void setDodgeSkillShotsSmallWindow(int dodgeSkillShotsSmallWindow) {
        this.dodgeSkillShotsSmallWindow = dodgeSkillShotsSmallWindow;
    }
    public int getDoubleAces() {
        return doubleAces;
    }
    public void setDoubleAces(int doubleAces) {
        this.doubleAces = doubleAces;
    }
    public int getDragonTakedowns() {
        return dragonTakedowns;
    }
    public void setDragonTakedowns(int dragonTakedowns) {
        this.dragonTakedowns = dragonTakedowns;
    }
    public double getEarliestBaron() {
        return earliestBaron;
    }
    public void setEarliestBaron(double earliestBaron) {
        this.earliestBaron = earliestBaron;
    }
    public double getEarliestDragonTakedown() {
        return earliestDragonTakedown;
    }
    public void setEarliestDragonTakedown(double earliestDragonTakedown) {
        this.earliestDragonTakedown = earliestDragonTakedown;
    }
    public int getEarlyLaningPhaseGoldExpAdvantage() {
        return earlyLaningPhaseGoldExpAdvantage;
    }
    public void setEarlyLaningPhaseGoldExpAdvantage(int earlyLaningPhaseGoldExpAdvantage) {
        this.earlyLaningPhaseGoldExpAdvantage = earlyLaningPhaseGoldExpAdvantage;
    }
    public double getEffectiveHealAndShielding() {
        return effectiveHealAndShielding;
    }
    public void setEffectiveHealAndShielding(double effectiveHealAndShielding) {
        this.effectiveHealAndShielding = effectiveHealAndShielding;
    }
    public int getElderDragonKillsWithOpposingSoul() {
        return elderDragonKillsWithOpposingSoul;
    }
    public void setElderDragonKillsWithOpposingSoul(int elderDragonKillsWithOpposingSoul) {
        this.elderDragonKillsWithOpposingSoul = elderDragonKillsWithOpposingSoul;
    }
    public int getElderDragonMultikills() {
        return elderDragonMultikills;
    }
    public void setElderDragonMultikills(int elderDragonMultikills) {
        this.elderDragonMultikills = elderDragonMultikills;
    }
    public int getEnemyChampionImmobilizations() {
        return enemyChampionImmobilizations;
    }
    public void setEnemyChampionImmobilizations(int enemyChampionImmobilizations) {
        this.enemyChampionImmobilizations = enemyChampionImmobilizations;
    }
    public int getEnemyJungleMonsterKills() {
        return enemyJungleMonsterKills;
    }
    public void setEnemyJungleMonsterKills(int enemyJungleMonsterKills) {
        this.enemyJungleMonsterKills = enemyJungleMonsterKills;
    }
    public int getEpicMonsterKillsNearEnemyJungler() {
        return epicMonsterKillsNearEnemyJungler;
    }
    public void setEpicMonsterKillsNearEnemyJungler(int epicMonsterKillsNearEnemyJungler) {
        this.epicMonsterKillsNearEnemyJungler = epicMonsterKillsNearEnemyJungler;
    }
    public int getEpicMonsterKillsWithin30SecondsOfSpawn() {
        return epicMonsterKillsWithin30SecondsOfSpawn;
    }
    public void setEpicMonsterKillsWithin30SecondsOfSpawn(int epicMonsterKillsWithin30SecondsOfSpawn) {
        this.epicMonsterKillsWithin30SecondsOfSpawn = epicMonsterKillsWithin30SecondsOfSpawn;
    }
    public int getEpicMonsterSteals() {
        return epicMonsterSteals;
    }
    public void setEpicMonsterSteals(int epicMonsterSteals) {
        this.epicMonsterSteals = epicMonsterSteals;
    }
    public int getEpicMonsterStolenWithoutSmite() {
        return epicMonsterStolenWithoutSmite;
    }
    public void setEpicMonsterStolenWithoutSmite(int epicMonsterStolenWithoutSmite) {
        this.epicMonsterStolenWithoutSmite = epicMonsterStolenWithoutSmite;
    }
    public int getFirstTurretKilled() {
        return firstTurretKilled;
    }
    public void setFirstTurretKilled(int firstTurretKilled) {
        this.firstTurretKilled = firstTurretKilled;
    }
    public int getFlawlessAces() {
        return flawlessAces;
    }
    public void setFlawlessAces(int flawlessAces) {
        this.flawlessAces = flawlessAces;
    }
    public int getFullTeamTakedown() {
        return fullTeamTakedown;
    }
    public void setFullTeamTakedown(int fullTeamTakedown) {
        this.fullTeamTakedown = fullTeamTakedown;
    }
    public double getGameLength() {
        return gameLength;
    }
    public void setGameLength(double gameLength) {
        this.gameLength = gameLength;
    }
    public int getGetTakedownsInAllLanesEarlyJungleAsLaner() {
        return getTakedownsInAllLanesEarlyJungleAsLaner;
    }
    public void setGetTakedownsInAllLanesEarlyJungleAsLaner(int getTakedownsInAllLanesEarlyJungleAsLaner) {
        this.getTakedownsInAllLanesEarlyJungleAsLaner = getTakedownsInAllLanesEarlyJungleAsLaner;
    }
    public double getGoldPerMinute() {
        return goldPerMinute;
    }
    public void setGoldPerMinute(double goldPerMinute) {
        this.goldPerMinute = goldPerMinute;
    }
    public int getHadOpenNexus() {
        return hadOpenNexus;
    }
    public void setHadOpenNexus(int hadOpenNexus) {
        this.hadOpenNexus = hadOpenNexus;
    }
    public int getImmobilizeAndKillWithAlly() {
        return immobilizeAndKillWithAlly;
    }
    public void setImmobilizeAndKillWithAlly(int immobilizeAndKillWithAlly) {
        this.immobilizeAndKillWithAlly = immobilizeAndKillWithAlly;
    }
    public int getInitialBuffCount() {
        return initialBuffCount;
    }
    public void setInitialBuffCount(int initialBuffCount) {
        this.initialBuffCount = initialBuffCount;
    }
    public int getInitialCrabCount() {
        return initialCrabCount;
    }
    public void setInitialCrabCount(int initialCrabCount) {
        this.initialCrabCount = initialCrabCount;
    }
    public double getJungleCsBefore10Minutes() {
        return jungleCsBefore10Minutes;
    }
    public void setJungleCsBefore10Minutes(double jungleCsBefore10Minutes) {
        this.jungleCsBefore10Minutes = jungleCsBefore10Minutes;
    }
    public int getJunglerTakedownsNearDamagedEpicMonster() {
        return junglerTakedownsNearDamagedEpicMonster;
    }
    public void setJunglerTakedownsNearDamagedEpicMonster(int junglerTakedownsNearDamagedEpicMonster) {
        this.junglerTakedownsNearDamagedEpicMonster = junglerTakedownsNearDamagedEpicMonster;
    }
    public int getkTurretsDestroyedBeforePlatesFall() {
        return kTurretsDestroyedBeforePlatesFall;
    }
    public void setkTurretsDestroyedBeforePlatesFall(int kTurretsDestroyedBeforePlatesFall) {
        this.kTurretsDestroyedBeforePlatesFall = kTurretsDestroyedBeforePlatesFall;
    }
    public double getKda() {
        return kda;
    }
    public void setKda(double kda) {
        this.kda = kda;
    }
    public int getKillAfterHiddenWithAlly() {
        return killAfterHiddenWithAlly;
    }
    public void setKillAfterHiddenWithAlly(int killAfterHiddenWithAlly) {
        this.killAfterHiddenWithAlly = killAfterHiddenWithAlly;
    }
    public double getKillParticipation() {
        return killParticipation;
    }
    public void setKillParticipation(double killParticipation) {
        this.killParticipation = killParticipation;
    }
    public int getKilledChampTookFullTeamDamageSurvived() {
        return killedChampTookFullTeamDamageSurvived;
    }
    public void setKilledChampTookFullTeamDamageSurvived(int killedChampTookFullTeamDamageSurvived) {
        this.killedChampTookFullTeamDamageSurvived = killedChampTookFullTeamDamageSurvived;
    }
    public int getKillingSprees() {
        return killingSprees;
    }
    public void setKillingSprees(int killingSprees) {
        this.killingSprees = killingSprees;
    }
    public int getKillsNearEnemyTurret() {
        return killsNearEnemyTurret;
    }
    public void setKillsNearEnemyTurret(int killsNearEnemyTurret) {
        this.killsNearEnemyTurret = killsNearEnemyTurret;
    }
    public int getKillsOnOtherLanesEarlyJungleAsLaner() {
        return killsOnOtherLanesEarlyJungleAsLaner;
    }
    public void setKillsOnOtherLanesEarlyJungleAsLaner(int killsOnOtherLanesEarlyJungleAsLaner) {
        this.killsOnOtherLanesEarlyJungleAsLaner = killsOnOtherLanesEarlyJungleAsLaner;
    }
    public int getKillsOnRecentlyHealedByAramPack() {
        return killsOnRecentlyHealedByAramPack;
    }
    public void setKillsOnRecentlyHealedByAramPack(int killsOnRecentlyHealedByAramPack) {
        this.killsOnRecentlyHealedByAramPack = killsOnRecentlyHealedByAramPack;
    }
    public int getKillsUnderOwnTurret() {
        return killsUnderOwnTurret;
    }
    public void setKillsUnderOwnTurret(int killsUnderOwnTurret) {
        this.killsUnderOwnTurret = killsUnderOwnTurret;
    }
    public int getKillsWithHelpFromEpicMonster() {
        return killsWithHelpFromEpicMonster;
    }
    public void setKillsWithHelpFromEpicMonster(int killsWithHelpFromEpicMonster) {
        this.killsWithHelpFromEpicMonster = killsWithHelpFromEpicMonster;
    }
    public int getKnockEnemyIntoTeamAndKill() {
        return knockEnemyIntoTeamAndKill;
    }
    public void setKnockEnemyIntoTeamAndKill(int knockEnemyIntoTeamAndKill) {
        this.knockEnemyIntoTeamAndKill = knockEnemyIntoTeamAndKill;
    }
    public int getLandSkillShotsEarlyGame() {
        return landSkillShotsEarlyGame;
    }
    public void setLandSkillShotsEarlyGame(int landSkillShotsEarlyGame) {
        this.landSkillShotsEarlyGame = landSkillShotsEarlyGame;
    }
    public int getLaneMinionsFirst10Minutes() {
        return laneMinionsFirst10Minutes;
    }
    public void setLaneMinionsFirst10Minutes(int laneMinionsFirst10Minutes) {
        this.laneMinionsFirst10Minutes = laneMinionsFirst10Minutes;
    }
    public int getLaningPhaseGoldExpAdvantage() {
        return laningPhaseGoldExpAdvantage;
    }
    public void setLaningPhaseGoldExpAdvantage(int laningPhaseGoldExpAdvantage) {
        this.laningPhaseGoldExpAdvantage = laningPhaseGoldExpAdvantage;
    }
    public int getLegendaryCount() {
        return legendaryCount;
    }
    public void setLegendaryCount(int legendaryCount) {
        this.legendaryCount = legendaryCount;
    }
    public ArrayList<Integer> getLegendaryItemUsed() {
        return legendaryItemUsed;
    }
    public void setLegendaryItemUsed(ArrayList<Integer> legendaryItemUsed) {
        this.legendaryItemUsed = legendaryItemUsed;
    }
    public int getLostAnInhibitor() {
        return lostAnInhibitor;
    }
    public void setLostAnInhibitor(int lostAnInhibitor) {
        this.lostAnInhibitor = lostAnInhibitor;
    }
    public double getMaxCsAdvantageOnLaneOpponent() {
        return maxCsAdvantageOnLaneOpponent;
    }
    public void setMaxCsAdvantageOnLaneOpponent(double maxCsAdvantageOnLaneOpponent) {
        this.maxCsAdvantageOnLaneOpponent = maxCsAdvantageOnLaneOpponent;
    }
    public int getMaxKillDeficit() {
        return maxKillDeficit;
    }
    public void setMaxKillDeficit(int maxKillDeficit) {
        this.maxKillDeficit = maxKillDeficit;
    }
    public int getMaxLevelLeadLaneOpponent() {
        return maxLevelLeadLaneOpponent;
    }
    public void setMaxLevelLeadLaneOpponent(int maxLevelLeadLaneOpponent) {
        this.maxLevelLeadLaneOpponent = maxLevelLeadLaneOpponent;
    }
    public int getMejaisFullStackInTime() {
        return mejaisFullStackInTime;
    }
    public void setMejaisFullStackInTime(int mejaisFullStackInTime) {
        this.mejaisFullStackInTime = mejaisFullStackInTime;
    }
    public double getMoreEnemyJungleThanOpponent() {
        return moreEnemyJungleThanOpponent;
    }
    public void setMoreEnemyJungleThanOpponent(double moreEnemyJungleThanOpponent) {
        this.moreEnemyJungleThanOpponent = moreEnemyJungleThanOpponent;
    }
    public int getMultiKillOneSpell() {
        return multiKillOneSpell;
    }
    public void setMultiKillOneSpell(int multiKillOneSpell) {
        this.multiKillOneSpell = multiKillOneSpell;
    }
    public int getMultiTurretRiftHeraldCount() {
        return multiTurretRiftHeraldCount;
    }
    public void setMultiTurretRiftHeraldCount(int multiTurretRiftHeraldCount) {
        this.multiTurretRiftHeraldCount = multiTurretRiftHeraldCount;
    }
    public int getMultikills() {
        return multikills;
    }
    public void setMultikills(int multikills) {
        this.multikills = multikills;
    }
    public int getMultikillsAfterAggressiveFlash() {
        return multikillsAfterAggressiveFlash;
    }
    public void setMultikillsAfterAggressiveFlash(int multikillsAfterAggressiveFlash) {
        this.multikillsAfterAggressiveFlash = multikillsAfterAggressiveFlash;
    }
    public int getOuterTurretExecutesBefore10Minutes() {
        return outerTurretExecutesBefore10Minutes;
    }
    public void setOuterTurretExecutesBefore10Minutes(int outerTurretExecutesBefore10Minutes) {
        this.outerTurretExecutesBefore10Minutes = outerTurretExecutesBefore10Minutes;
    }
    public int getOutnumberedKills() {
        return outnumberedKills;
    }
    public void setOutnumberedKills(int outnumberedKills) {
        this.outnumberedKills = outnumberedKills;
    }
    public int getOutnumberedNexusKill() {
        return outnumberedNexusKill;
    }
    public void setOutnumberedNexusKill(int outnumberedNexusKill) {
        this.outnumberedNexusKill = outnumberedNexusKill;
    }
    public int getPerfectDragonSoulsTaken() {
        return perfectDragonSoulsTaken;
    }
    public void setPerfectDragonSoulsTaken(int perfectDragonSoulsTaken) {
        this.perfectDragonSoulsTaken = perfectDragonSoulsTaken;
    }
    public int getPerfectGame() {
        return perfectGame;
    }
    public void setPerfectGame(int perfectGame) {
        this.perfectGame = perfectGame;
    }
    public int getPickKillWithAlly() {
        return pickKillWithAlly;
    }
    public void setPickKillWithAlly(int pickKillWithAlly) {
        this.pickKillWithAlly = pickKillWithAlly;
    }
    public int getPlayedChampSelectPosition() {
        return playedChampSelectPosition;
    }
    public void setPlayedChampSelectPosition(int playedChampSelectPosition) {
        this.playedChampSelectPosition = playedChampSelectPosition;
    }
    public int getPoroExplosions() {
        return poroExplosions;
    }
    public void setPoroExplosions(int poroExplosions) {
        this.poroExplosions = poroExplosions;
    }
    public int getQuickCleanse() {
        return quickCleanse;
    }
    public void setQuickCleanse(int quickCleanse) {
        this.quickCleanse = quickCleanse;
    }
    public int getQuickFirstTurret() {
        return quickFirstTurret;
    }
    public void setQuickFirstTurret(int quickFirstTurret) {
        this.quickFirstTurret = quickFirstTurret;
    }
    public int getQuickSoloKills() {
        return quickSoloKills;
    }
    public void setQuickSoloKills(int quickSoloKills) {
        this.quickSoloKills = quickSoloKills;
    }
    public int getRiftHeraldTakedowns() {
        return riftHeraldTakedowns;
    }
    public void setRiftHeraldTakedowns(int riftHeraldTakedowns) {
        this.riftHeraldTakedowns = riftHeraldTakedowns;
    }
    public int getSaveAllyFromDeath() {
        return saveAllyFromDeath;
    }
    public void setSaveAllyFromDeath(int saveAllyFromDeath) {
        this.saveAllyFromDeath = saveAllyFromDeath;
    }
    public int getScuttleCrabKills() {
        return scuttleCrabKills;
    }
    public void setScuttleCrabKills(int scuttleCrabKills) {
        this.scuttleCrabKills = scuttleCrabKills;
    }
    public int getSkillshotsDodged() {
        return skillshotsDodged;
    }
    public void setSkillshotsDodged(int skillshotsDodged) {
        this.skillshotsDodged = skillshotsDodged;
    }
    public int getSkillshotsHit() {
        return skillshotsHit;
    }
    public void setSkillshotsHit(int skillshotsHit) {
        this.skillshotsHit = skillshotsHit;
    }
    public int getSnowballsHit() {
        return snowballsHit;
    }
    public void setSnowballsHit(int snowballsHit) {
        this.snowballsHit = snowballsHit;
    }
    public int getSoloBaronKills() {
        return soloBaronKills;
    }
    public void setSoloBaronKills(int soloBaronKills) {
        this.soloBaronKills = soloBaronKills;
    }
    public int getSoloKills() {
        return soloKills;
    }
    public void setSoloKills(int soloKills) {
        this.soloKills = soloKills;
    }
    public int getSoloTurretsLategame() {
        return soloTurretsLategame;
    }
    public void setSoloTurretsLategame(int soloTurretsLategame) {
        this.soloTurretsLategame = soloTurretsLategame;
    }
    public int getStealthWardsPlaced() {
        return stealthWardsPlaced;
    }
    public void setStealthWardsPlaced(int stealthWardsPlaced) {
        this.stealthWardsPlaced = stealthWardsPlaced;
    }
    public int getSurvivedSingleDigitHpCount() {
        return survivedSingleDigitHpCount;
    }
    public void setSurvivedSingleDigitHpCount(int survivedSingleDigitHpCount) {
        this.survivedSingleDigitHpCount = survivedSingleDigitHpCount;
    }
    public int getSurvivedThreeImmobilizesInFight() {
        return survivedThreeImmobilizesInFight;
    }
    public void setSurvivedThreeImmobilizesInFight(int survivedThreeImmobilizesInFight) {
        this.survivedThreeImmobilizesInFight = survivedThreeImmobilizesInFight;
    }
    public int getTakedownOnFirstTurret() {
        return takedownOnFirstTurret;
    }
    public void setTakedownOnFirstTurret(int takedownOnFirstTurret) {
        this.takedownOnFirstTurret = takedownOnFirstTurret;
    }
    public int getTakedowns() {
        return takedowns;
    }
    public void setTakedowns(int takedowns) {
        this.takedowns = takedowns;
    }
    public int getTakedownsAfterGainingLevelAdvantage() {
        return takedownsAfterGainingLevelAdvantage;
    }
    public void setTakedownsAfterGainingLevelAdvantage(int takedownsAfterGainingLevelAdvantage) {
        this.takedownsAfterGainingLevelAdvantage = takedownsAfterGainingLevelAdvantage;
    }
    public int getTakedownsBeforeJungleMinionSpawn() {
        return takedownsBeforeJungleMinionSpawn;
    }
    public void setTakedownsBeforeJungleMinionSpawn(int takedownsBeforeJungleMinionSpawn) {
        this.takedownsBeforeJungleMinionSpawn = takedownsBeforeJungleMinionSpawn;
    }
    public int getTakedownsFirstXMinutes() {
        return takedownsFirstXMinutes;
    }
    public void setTakedownsFirstXMinutes(int takedownsFirstXMinutes) {
        this.takedownsFirstXMinutes = takedownsFirstXMinutes;
    }
    public int getTakedownsInAlcove() {
        return takedownsInAlcove;
    }
    public void setTakedownsInAlcove(int takedownsInAlcove) {
        this.takedownsInAlcove = takedownsInAlcove;
    }
    public int getTakedownsInEnemyFountain() {
        return takedownsInEnemyFountain;
    }
    public void setTakedownsInEnemyFountain(int takedownsInEnemyFountain) {
        this.takedownsInEnemyFountain = takedownsInEnemyFountain;
    }
    public int getTeamBaronKills() {
        return teamBaronKills;
    }
    public void setTeamBaronKills(int teamBaronKills) {
        this.teamBaronKills = teamBaronKills;
    }
    public double getTeamDamagePercentage() {
        return teamDamagePercentage;
    }
    public void setTeamDamagePercentage(double teamDamagePercentage) {
        this.teamDamagePercentage = teamDamagePercentage;
    }
    public int getTeamElderDragonKills() {
        return teamElderDragonKills;
    }
    public void setTeamElderDragonKills(int teamElderDragonKills) {
        this.teamElderDragonKills = teamElderDragonKills;
    }
    public int getTeamRiftHeraldKills() {
        return teamRiftHeraldKills;
    }
    public void setTeamRiftHeraldKills(int teamRiftHeraldKills) {
        this.teamRiftHeraldKills = teamRiftHeraldKills;
    }
    public int getTookLargeDamageSurvived() {
        return tookLargeDamageSurvived;
    }
    public void setTookLargeDamageSurvived(int tookLargeDamageSurvived) {
        this.tookLargeDamageSurvived = tookLargeDamageSurvived;
    }
    public int getTurretPlatesTaken() {
        return turretPlatesTaken;
    }
    public void setTurretPlatesTaken(int turretPlatesTaken) {
        this.turretPlatesTaken = turretPlatesTaken;
    }
    public int getTurretTakedowns() {
        return turretTakedowns;
    }
    public void setTurretTakedowns(int turretTakedowns) {
        this.turretTakedowns = turretTakedowns;
    }
    public int getTurretsTakenWithRiftHerald() {
        return turretsTakenWithRiftHerald;
    }
    public void setTurretsTakenWithRiftHerald(int turretsTakenWithRiftHerald) {
        this.turretsTakenWithRiftHerald = turretsTakenWithRiftHerald;
    }
    public int getTwentyMinionsIn3SecondsCount() {
        return twentyMinionsIn3SecondsCount;
    }
    public void setTwentyMinionsIn3SecondsCount(int twentyMinionsIn3SecondsCount) {
        this.twentyMinionsIn3SecondsCount = twentyMinionsIn3SecondsCount;
    }
    public int getTwoWardsOneSweeperCount() {
        return twoWardsOneSweeperCount;
    }
    public void setTwoWardsOneSweeperCount(int twoWardsOneSweeperCount) {
        this.twoWardsOneSweeperCount = twoWardsOneSweeperCount;
    }
    public int getUnseenRecalls() {
        return unseenRecalls;
    }
    public void setUnseenRecalls(int unseenRecalls) {
        this.unseenRecalls = unseenRecalls;
    }
    public double getVisionScoreAdvantageLaneOpponent() {
        return visionScoreAdvantageLaneOpponent;
    }
    public void setVisionScoreAdvantageLaneOpponent(double visionScoreAdvantageLaneOpponent) {
        this.visionScoreAdvantageLaneOpponent = visionScoreAdvantageLaneOpponent;
    }
    public double getVisionScorePerMinute() {
        return visionScorePerMinute;
    }
    public void setVisionScorePerMinute(double visionScorePerMinute) {
        this.visionScorePerMinute = visionScorePerMinute;
    }
    public int getWardTakedowns() {
        return wardTakedowns;
    }
    public void setWardTakedowns(int wardTakedowns) {
        this.wardTakedowns = wardTakedowns;
    }
    public int getWardTakedownsBefore20M() {
        return wardTakedownsBefore20M;
    }
    public void setWardTakedownsBefore20M(int wardTakedownsBefore20M) {
        this.wardTakedownsBefore20M = wardTakedownsBefore20M;
    }
    public int getWardsGuarded() {
        return wardsGuarded;
    }
    public void setWardsGuarded(int wardsGuarded) {
        this.wardsGuarded = wardsGuarded;
    }
    public int getJunglerKillsEarlyJungle() {
        return junglerKillsEarlyJungle;
    }
    public void setJunglerKillsEarlyJungle(int junglerKillsEarlyJungle) {
        this.junglerKillsEarlyJungle = junglerKillsEarlyJungle;
    }
    public int getKillsOnLanersEarlyJungleAsJungler() {
        return killsOnLanersEarlyJungleAsJungler;
    }
    public void setKillsOnLanersEarlyJungleAsJungler(int killsOnLanersEarlyJungleAsJungler) {
        this.killsOnLanersEarlyJungleAsJungler = killsOnLanersEarlyJungleAsJungler;
    }
    public double getShortestTimeToAceFromFirstTakedown() {
        return shortestTimeToAceFromFirstTakedown;
    }
    public void setShortestTimeToAceFromFirstTakedown(double shortestTimeToAceFromFirstTakedown) {
        this.shortestTimeToAceFromFirstTakedown = shortestTimeToAceFromFirstTakedown;
    }
    public int getTeleportTakedowns() {
        return teleportTakedowns;
    }
    public void setTeleportTakedowns(int teleportTakedowns) {
        this.teleportTakedowns = teleportTakedowns;
    }
    public double getFastestLegendary() {
        return fastestLegendary;
    }
    public void setFastestLegendary(double fastestLegendary) {
        this.fastestLegendary = fastestLegendary;
    }
    public double getControlWardTimeCoverageInRiverOrEnemyHalf() {
        return controlWardTimeCoverageInRiverOrEnemyHalf;
    }
    public void setControlWardTimeCoverageInRiverOrEnemyHalf(double controlWardTimeCoverageInRiverOrEnemyHalf) {
        this.controlWardTimeCoverageInRiverOrEnemyHalf = controlWardTimeCoverageInRiverOrEnemyHalf;
    }
    public int getHighestWardKills() {
        return highestWardKills;
    }
    public void setHighestWardKills(int highestWardKills) {
        this.highestWardKills = highestWardKills;
    }
    public double getFirstTurretKilledTime() {
        return firstTurretKilledTime;
    }
    public void setFirstTurretKilledTime(double firstTurretKilledTime) {
        this.firstTurretKilledTime = firstTurretKilledTime;
    }
    public int getHighestChampionDamage() {
        return highestChampionDamage;
    }
    public void setHighestChampionDamage(int highestChampionDamage) {
        this.highestChampionDamage = highestChampionDamage;
    }
    public int getFasterSupportQuestCompletion() {
        return fasterSupportQuestCompletion;
    }
    public void setFasterSupportQuestCompletion(int fasterSupportQuestCompletion) {
        this.fasterSupportQuestCompletion = fasterSupportQuestCompletion;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Challenges{" +
                "_12AssistStreakCount=" + _12AssistStreakCount +
                ", abilityUses=" + abilityUses +
                ", acesBefore15Minutes=" + acesBefore15Minutes +
                ", alliedJungleMonsterKills=" + alliedJungleMonsterKills +
                ", baronBuffGoldAdvantageOverThreshold=" + baronBuffGoldAdvantageOverThreshold +
                ", baronTakedowns=" + baronTakedowns +
                ", blastConeOppositeOpponentCount=" + blastConeOppositeOpponentCount +
                ", bountyGold=" + bountyGold +
                ", buffsStolen=" + buffsStolen +
                ", completeSupportQuestInTime=" + completeSupportQuestInTime +
                ", controlWardsPlaced=" + controlWardsPlaced +
                ", damagePerMinute=" + damagePerMinute +
                ", damageTakenOnTeamPercentage=" + damageTakenOnTeamPercentage +
                ", dancedWithRiftHerald=" + dancedWithRiftHerald +
                ", deathsByEnemyChamps=" + deathsByEnemyChamps +
                ", dodgeSkillShotsSmallWindow=" + dodgeSkillShotsSmallWindow +
                ", doubleAces=" + doubleAces +
                ", dragonTakedowns=" + dragonTakedowns +
                ", earliestBaron=" + earliestBaron +
                ", earliestDragonTakedown=" + earliestDragonTakedown +
                ", earlyLaningPhaseGoldExpAdvantage=" + earlyLaningPhaseGoldExpAdvantage +
                ", effectiveHealAndShielding=" + effectiveHealAndShielding +
                ", elderDragonKillsWithOpposingSoul=" + elderDragonKillsWithOpposingSoul +
                ", elderDragonMultikills=" + elderDragonMultikills +
                ", enemyChampionImmobilizations=" + enemyChampionImmobilizations +
                ", enemyJungleMonsterKills=" + enemyJungleMonsterKills +
                ", epicMonsterKillsNearEnemyJungler=" + epicMonsterKillsNearEnemyJungler +
                ", epicMonsterKillsWithin30SecondsOfSpawn=" + epicMonsterKillsWithin30SecondsOfSpawn +
                ", epicMonsterSteals=" + epicMonsterSteals +
                ", epicMonsterStolenWithoutSmite=" + epicMonsterStolenWithoutSmite +
                ", firstTurretKilled=" + firstTurretKilled +
                ", flawlessAces=" + flawlessAces +
                ", fullTeamTakedown=" + fullTeamTakedown +
                ", gameLength=" + gameLength +
                ", getTakedownsInAllLanesEarlyJungleAsLaner=" + getTakedownsInAllLanesEarlyJungleAsLaner +
                ", goldPerMinute=" + goldPerMinute +
                ", hadOpenNexus=" + hadOpenNexus +
                ", immobilizeAndKillWithAlly=" + immobilizeAndKillWithAlly +
                ", initialBuffCount=" + initialBuffCount +
                ", initialCrabCount=" + initialCrabCount +
                ", jungleCsBefore10Minutes=" + jungleCsBefore10Minutes +
                ", junglerTakedownsNearDamagedEpicMonster=" + junglerTakedownsNearDamagedEpicMonster +
                ", kTurretsDestroyedBeforePlatesFall=" + kTurretsDestroyedBeforePlatesFall +
                ", kda=" + kda +
                ", killAfterHiddenWithAlly=" + killAfterHiddenWithAlly +
                ", killParticipation=" + killParticipation +
                ", killedChampTookFullTeamDamageSurvived=" + killedChampTookFullTeamDamageSurvived +
                ", killingSprees=" + killingSprees +
                ", killsNearEnemyTurret=" + killsNearEnemyTurret +
                ", killsOnOtherLanesEarlyJungleAsLaner=" + killsOnOtherLanesEarlyJungleAsLaner +
                ", killsOnRecentlyHealedByAramPack=" + killsOnRecentlyHealedByAramPack +
                ", killsUnderOwnTurret=" + killsUnderOwnTurret +
                ", killsWithHelpFromEpicMonster=" + killsWithHelpFromEpicMonster +
                ", knockEnemyIntoTeamAndKill=" + knockEnemyIntoTeamAndKill +
                ", landSkillShotsEarlyGame=" + landSkillShotsEarlyGame +
                ", laneMinionsFirst10Minutes=" + laneMinionsFirst10Minutes +
                ", laningPhaseGoldExpAdvantage=" + laningPhaseGoldExpAdvantage +
                ", legendaryCount=" + legendaryCount +
                ", legendaryItemUsed=" + legendaryItemUsed +
                ", lostAnInhibitor=" + lostAnInhibitor +
                ", maxCsAdvantageOnLaneOpponent=" + maxCsAdvantageOnLaneOpponent +
                ", maxKillDeficit=" + maxKillDeficit +
                ", maxLevelLeadLaneOpponent=" + maxLevelLeadLaneOpponent +
                ", mejaisFullStackInTime=" + mejaisFullStackInTime +
                ", moreEnemyJungleThanOpponent=" + moreEnemyJungleThanOpponent +
                ", multiKillOneSpell=" + multiKillOneSpell +
                ", multiTurretRiftHeraldCount=" + multiTurretRiftHeraldCount +
                ", multikills=" + multikills +
                ", multikillsAfterAggressiveFlash=" + multikillsAfterAggressiveFlash +
                ", outerTurretExecutesBefore10Minutes=" + outerTurretExecutesBefore10Minutes +
                ", outnumberedKills=" + outnumberedKills +
                ", outnumberedNexusKill=" + outnumberedNexusKill +
                ", perfectDragonSoulsTaken=" + perfectDragonSoulsTaken +
                ", perfectGame=" + perfectGame +
                ", pickKillWithAlly=" + pickKillWithAlly +
                ", playedChampSelectPosition=" + playedChampSelectPosition +
                ", poroExplosions=" + poroExplosions +
                ", quickCleanse=" + quickCleanse +
                ", quickFirstTurret=" + quickFirstTurret +
                ", quickSoloKills=" + quickSoloKills +
                ", riftHeraldTakedowns=" + riftHeraldTakedowns +
                ", saveAllyFromDeath=" + saveAllyFromDeath +
                ", scuttleCrabKills=" + scuttleCrabKills +
                ", skillshotsDodged=" + skillshotsDodged +
                ", skillshotsHit=" + skillshotsHit +
                ", snowballsHit=" + snowballsHit +
                ", soloBaronKills=" + soloBaronKills +
                ", soloKills=" + soloKills +
                ", soloTurretsLategame=" + soloTurretsLategame +
                ", stealthWardsPlaced=" + stealthWardsPlaced +
                ", survivedSingleDigitHpCount=" + survivedSingleDigitHpCount +
                ", survivedThreeImmobilizesInFight=" + survivedThreeImmobilizesInFight +
                ", takedownOnFirstTurret=" + takedownOnFirstTurret +
                ", takedowns=" + takedowns +
                ", takedownsAfterGainingLevelAdvantage=" + takedownsAfterGainingLevelAdvantage +
                ", takedownsBeforeJungleMinionSpawn=" + takedownsBeforeJungleMinionSpawn +
                ", takedownsFirstXMinutes=" + takedownsFirstXMinutes +
                ", takedownsInAlcove=" + takedownsInAlcove +
                ", takedownsInEnemyFountain=" + takedownsInEnemyFountain +
                ", teamBaronKills=" + teamBaronKills +
                ", teamDamagePercentage=" + teamDamagePercentage +
                ", teamElderDragonKills=" + teamElderDragonKills +
                ", teamRiftHeraldKills=" + teamRiftHeraldKills +
                ", tookLargeDamageSurvived=" + tookLargeDamageSurvived +
                ", turretPlatesTaken=" + turretPlatesTaken +
                ", turretTakedowns=" + turretTakedowns +
                ", turretsTakenWithRiftHerald=" + turretsTakenWithRiftHerald +
                ", twentyMinionsIn3SecondsCount=" + twentyMinionsIn3SecondsCount +
                ", twoWardsOneSweeperCount=" + twoWardsOneSweeperCount +
                ", unseenRecalls=" + unseenRecalls +
                ", visionScoreAdvantageLaneOpponent=" + visionScoreAdvantageLaneOpponent +
                ", visionScorePerMinute=" + visionScorePerMinute +
                ", wardTakedowns=" + wardTakedowns +
                ", wardTakedownsBefore20M=" + wardTakedownsBefore20M +
                ", wardsGuarded=" + wardsGuarded +
                ", junglerKillsEarlyJungle=" + junglerKillsEarlyJungle +
                ", killsOnLanersEarlyJungleAsJungler=" + killsOnLanersEarlyJungleAsJungler +
                ", shortestTimeToAceFromFirstTakedown=" + shortestTimeToAceFromFirstTakedown +
                ", teleportTakedowns=" + teleportTakedowns +
                ", fastestLegendary=" + fastestLegendary +
                ", controlWardTimeCoverageInRiverOrEnemyHalf=" + controlWardTimeCoverageInRiverOrEnemyHalf +
                ", highestWardKills=" + highestWardKills +
                ", firstTurretKilledTime=" + firstTurretKilledTime +
                ", highestChampionDamage=" + highestChampionDamage +
                ", fasterSupportQuestCompletion=" + fasterSupportQuestCompletion +
                '}';
    }
}
