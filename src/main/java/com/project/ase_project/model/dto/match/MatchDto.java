package com.project.ase_project.model.dto.match;

import com.project.ase_project.model.clean.match.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a MatchDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store all the information regarding a game, including the metadata and the info.
 * This class is the root of all other classes in this package.
 */
public class MatchDto {
    // JSON FIELDS
    private Metadata metadata;
    private Info info;

    // GETTERS AND SETTERS
    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
    public Info getInfo() {
        return info;
    }
    public void setInfo(Info info) {
        this.info = info;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Match{" +
                "metadata=" + metadata +
                ", info=" + info +
                '}';
    }

    // TO MATCH
    public static Match toMatch(MatchDto matchDto) {
        Metadata metadata = matchDto.getMetadata();
        Info info = matchDto.getInfo();
        Match match = new Match();

        match.setMatchId(metadata.getMatchId());

        // METADATA
        com.project.ase_project.model.clean.match.Metadata cleanMetadata = new com.project.ase_project.model.clean.match.Metadata();
        cleanMetadata.setGameCreation(info.getGameCreation());
        cleanMetadata.setGameDuration(info.getGameDuration());
        cleanMetadata.setGameEndTimestamp(info.getGameEndTimestamp());

        cleanMetadata.setDataVersion(metadata.getDataVersion());
        cleanMetadata.setGameId(info.getGameId());
        cleanMetadata.setPlatformId(info.getPlatformId());

        cleanMetadata.setGameMode(info.getGameMode());
        cleanMetadata.setGameName(info.getGameName());
        cleanMetadata.setGameType(info.getGameType());
        cleanMetadata.setGameVersion(info.getGameVersion());
        cleanMetadata.setMapId(info.getMapId());
        cleanMetadata.setQueueId(info.getQueueId());

        match.setMetadata(cleanMetadata);

        // PARTICIPANTS
        List<com.project.ase_project.model.clean.match.Participant> cleanParticipants = new ArrayList<>();
        for (Participant participant : info.getParticipants()) {
            com.project.ase_project.model.clean.match.Participant cleanParticipant = new com.project.ase_project.model.clean.match.Participant();

            cleanParticipant.setPuuid(participant.getPuuid());
            cleanParticipant.setSummonerId(participant.getSummonerId());
            cleanParticipant.setSummonerName(participant.getSummonerName());
            cleanParticipant.setSummonerLevel(participant.getSummonerLevel());
            cleanParticipant.setParticipantId(participant.getParticipantId());
            cleanParticipant.setRiotIdGameName(participant.getRiotIdGameName());
            cleanParticipant.setRiotIdTagLine(participant.getRiotIdTagline());

            cleanParticipant.setTeamId(participant.getTeamId());
            cleanParticipant.setRole(participant.getRole());
            cleanParticipant.setLane(participant.getLane());
            cleanParticipant.setChampionId(participant.getChampionId());
            cleanParticipant.setChampionName(participant.getChampionName());

            cleanParticipant.setWin(participant.getWin());
            cleanParticipant.setKills(participant.getKills());
            cleanParticipant.setDeaths(participant.getDeaths());
            cleanParticipant.setAssists(participant.getAssists());
            cleanParticipant.setVisionScore(participant.getVisionScore());

            cleanParticipants.add(cleanParticipant);
        }
        match.setParticipants(cleanParticipants);

        // TEAMS
        List<com.project.ase_project.model.clean.match.Team> cleanTeams = new ArrayList<>();
        for (Team team : info.getTeams()) {
            com.project.ase_project.model.clean.match.Team cleanTeam = new com.project.ase_project.model.clean.match.Team();

            cleanTeam.setTeamId(team.getTeamId());
            cleanTeam.setWin(team.getWin());

            cleanTeam.setFirstChampion(team.getObjectives().getChampion().isFirst());
            cleanTeam.setFirstTower(team.getObjectives().getTower().isFirst());
            cleanTeam.setFirstInhibitor(team.getObjectives().getInhibitor().isFirst());
            cleanTeam.setFirstBaron(team.getObjectives().getBaron().isFirst());
            cleanTeam.setFirstDragon(team.getObjectives().getDragon().isFirst());
            cleanTeam.setFirstRiftHerald(team.getObjectives().getRiftHerald().isFirst());
            cleanTeam.setFirstHorde(team.getObjectives().getHorde().isFirst());

            cleanTeam.setTowerKills(team.getObjectives().getTower().getKills());
            cleanTeam.setInhibitorKills(team.getObjectives().getInhibitor().getKills());
            cleanTeam.setBaronKills(team.getObjectives().getBaron().getKills());
            cleanTeam.setDragonKills(team.getObjectives().getDragon().getKills());
            cleanTeam.setHordeKills(team.getObjectives().getHorde().getKills());
            cleanTeam.setRiftHeraldKills(team.getObjectives().getRiftHerald().getKills());
            cleanTeam.setChampionKills(team.getObjectives().getChampion().getKills());

            cleanTeams.add(cleanTeam);
        }
        match.setTeams(cleanTeams);

        // DRAFTS
        List<com.project.ase_project.model.clean.match.Draft> cleanDrafts = new ArrayList<>();
        for (Team team : info.getTeams()) {
            com.project.ase_project.model.clean.match.Draft cleanDraft = new com.project.ase_project.model.clean.match.Draft();
            cleanDraft.setTeamId(team.getTeamId());

            cleanDraft.setFirstBan(team.getBans().get(0).getChampionId());
            cleanDraft.setSecondBan(team.getBans().get(1).getChampionId());
            cleanDraft.setThirdBan(team.getBans().get(2).getChampionId());
            cleanDraft.setFourthBan(team.getBans().get(3).getChampionId());
            cleanDraft.setFifthBan(team.getBans().get(4).getChampionId());

            if (team.getTeamId() == 100) {
                cleanDraft.setFirstPick(info.getParticipants().get(0).getChampionId());
                cleanDraft.setSecondPick(info.getParticipants().get(1).getChampionId());
                cleanDraft.setThirdPick(info.getParticipants().get(2).getChampionId());
                cleanDraft.setFourthPick(info.getParticipants().get(3).getChampionId());
                cleanDraft.setFifthPick(info.getParticipants().get(4).getChampionId());
            } else {
                cleanDraft.setFirstPick(info.getParticipants().get(5).getChampionId());
                cleanDraft.setSecondPick(info.getParticipants().get(6).getChampionId());
                cleanDraft.setThirdPick(info.getParticipants().get(7).getChampionId());
                cleanDraft.setFourthPick(info.getParticipants().get(8).getChampionId());
                cleanDraft.setFifthPick(info.getParticipants().get(9).getChampionId());
            }

            cleanDrafts.add(cleanDraft);
        }
        match.setDrafts(cleanDrafts);

        return match;
    }
}

