package com.project.ase_project.model.clean.match;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Match {
    // JSON FIELDS
    @Id
    private String matchId;
    @Embedded
    private Metadata metadata;
    @ElementCollection
    private List<Participant> participants;
    @ElementCollection
    private List<Team> teams;
    @ElementCollection
    private List<Draft> drafts;

    // GETTERS AND SETTERS
    public String getMatchId() {
        return matchId;
    }
    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
    public List<Participant> getParticipants() {
        return participants;
    }
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
    public List<Team> getTeams() {
        return teams;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    public List<Draft> getDrafts() {
        return drafts;
    }
    public void setDrafts(List<Draft> drafts) {
        this.drafts = drafts;
    }

    // TOSTRING

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", metadata=" + metadata +
                ", participants=" + participants +
                ", teams=" + teams +
                ", drafts=" + drafts +
                '}';
    }
}
