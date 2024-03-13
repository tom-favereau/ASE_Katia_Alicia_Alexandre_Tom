package com.project.ase_project.model;

import jakarta.persistence.*;

@Entity
public class Summoner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long summoner_id;
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private int profileIconId;
    private long revisionDate;
    private long summonerLevel;

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getPuuid() {
        return puuid;
    }

    public String getName() {
        return name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public void summonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getProfileIconAddress() {
        return "https://ddragon.leagueoflegends.com/cdn/14.5.1/img/profileicon/" + this.getProfileIconId() + ".png";
    }

    @Override
    public String toString() {
        return "Summoner{" +
                "summoner_id=" + summoner_id +
                ", id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", puuid='" + puuid + '\'' +
                ", name='" + name + '\'' +
                ", profileIconId=" + profileIconId +
                ", revisionDate=" + revisionDate +
                ", summonerLevel=" + summonerLevel +
                '}';
    }
}
