package com.project.ase_project.model.dto.summoner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.ase_project.model.clean.summoner.Summoner;

/**
 * Represents a SummonerDto object from the Riot API (https://developer.riotgames.com/apis#summoner-v4/GET_getBySummonerName)
 * This class is used to store all the information regarding a summoner.
 * This class is the root of all other classes in this package.
 */
public class SummonerDto {
    // JSON FIELDS
    @JsonProperty("id")
    private String id;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("puuid")
    private String puuid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("profileIconId")
    private int profileIconId;
    @JsonProperty("revisionDate")
    private long revisionDate;
    @JsonProperty("summonerLevel")
    private long summonerLevel;

    // GETTERS AND SETTERS
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getPuuid() {
        return puuid;
    }
    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getProfileIconId() {
        return profileIconId;
    }
    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }
    public long getRevisionDate() {
        return revisionDate;
    }
    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }
    public long getSummonerLevel() {
        return summonerLevel;
    }
    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "SummonerDto{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", puuid='" + puuid + '\'' +
                ", name='" + name + '\'' +
                ", profileIconId=" + profileIconId +
                ", revisionDate=" + revisionDate +
                ", summonerLevel=" + summonerLevel +
                '}';
    }

    // TO SUMMONER
    public static Summoner toSummoner(SummonerDto summonerDto) {
        Summoner summoner = new Summoner();
        summoner.setName(summonerDto.getName());
        summoner.setProfileIconId(summonerDto.getProfileIconId());
        summoner.setSummonerLevel(summonerDto.getSummonerLevel());
        summoner.setId(summonerDto.getId());
        return summoner;
    }
}
