package com.project.ase_project.model.dto.match;

import com.project.ase_project.model.clean.match.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a MatchDto object from the Riot API (<a href="https://developer.riotgames.com/apis#match-v5/GET_getMatch/">...</a>)
 * This class is used to store all the information regarding a game, including the metadata and the info.
 * This class is the root of all other classes in this package.
 */

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MatchDto {
    private MetadataDto metadata;
    private InfoDto info;

    // TO MATCH
    public Match toMatch() {
        MetadataDto metadata = this.getMetadata();
        InfoDto info = this.getInfo();
        Match match = new Match();

        match.setMatchId(metadata.getMatchId());

        // METADATA
        match.setMetadata(getMetadata(info, metadata));

        // PARTICIPANTS
        List<Participant> cleanParticipants = new ArrayList<>();
        for (ParticipantDto participant : info.getParticipants()) {
            cleanParticipants.add(getParticipant(participant));
        }
        match.setParticipants(cleanParticipants);

        // TEAMS
        List<com.project.ase_project.model.clean.match.Team> cleanTeams = new ArrayList<>();
        for (TeamDto team : info.getTeams()) {
            cleanTeams.add(getTeam(team));
        }
        match.setTeams(cleanTeams);

        // DRAFTS
        List<com.project.ase_project.model.clean.match.Draft> cleanDrafts = new ArrayList<>();
        if (this.getInfo().getQueueId() == 400 ||  this.getInfo().getQueueId() == 420 || this.getInfo().getQueueId() == 440) {
            for (TeamDto team : info.getTeams()) {
                cleanDrafts.add(getDraft(team, info));
            }
            match.setDrafts(cleanDrafts);
        }


        return match;
    }

    private static Draft getDraft(TeamDto team, InfoDto info) {
        Draft cleanDraft = new Draft();
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
        return cleanDraft;
    }

    private static Team getTeam(TeamDto team) {
        Team cleanTeam = new Team();

        cleanTeam.setTeamId(team.getTeamId());
        cleanTeam.setWin(team.isWin());

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
        return cleanTeam;
    }

    private static Participant getParticipant(ParticipantDto participant) {
        Participant cleanParticipant = new Participant();

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
        cleanParticipant.setTeamPosition(participant.getTeamPosition());
        cleanParticipant.setIndividualPosition(participant.getIndividualPosition());
        cleanParticipant.setChampionId(participant.getChampionId());
        cleanParticipant.setChampionName(participant.getChampionName());

        cleanParticipant.setWin(participant.isWin());
        cleanParticipant.setKills(participant.getKills());
        cleanParticipant.setDeaths(participant.getDeaths());
        cleanParticipant.setAssists(participant.getAssists());
        cleanParticipant.setVisionScore(participant.getVisionScore());
        return cleanParticipant;
    }

    private static Metadata getMetadata(InfoDto info, MetadataDto metadata) {
        Metadata cleanMetadata = new Metadata();
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
        return cleanMetadata;
    }
}

