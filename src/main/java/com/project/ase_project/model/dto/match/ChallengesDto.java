package com.project.ase_project.model.dto.match;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * Represents a ChallengesDto object from the Riot API that is not actually identified or mentioned in the API documentation.
 * This class is used to store information regarding the challenges accomplished in a game.
 * This class is used by the Participant class as an attribute.
 */

@Data
@Getter
@Setter
@ToString
public class ChallengesDto {
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
}
