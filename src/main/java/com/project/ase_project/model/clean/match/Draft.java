package com.project.ase_project.model.clean.match;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Draft {
    @Column(insertable=false, updatable=false)
    private int teamId;
    private int firstBan;
    private int secondBan;
    private int thirdBan;
    private int fourthBan;
    private int fifthBan;
    private int firstPick;
    private int secondPick;
    private int thirdPick;
    private int fourthPick;
    private int fifthPick;

    // GETTERS AND SETTERS

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getFirstBan() {
        return firstBan;
    }

    public void setFirstBan(int firstBan) {
        this.firstBan = firstBan;
    }

    public int getSecondBan() {
        return secondBan;
    }

    public void setSecondBan(int secondBan) {
        this.secondBan = secondBan;
    }

    public int getThirdBan() {
        return thirdBan;
    }

    public void setThirdBan(int thirdBan) {
        this.thirdBan = thirdBan;
    }

    public int getFourthBan() {
        return fourthBan;
    }

    public void setFourthBan(int fourthBan) {
        this.fourthBan = fourthBan;
    }

    public int getFifthBan() {
        return fifthBan;
    }

    public void setFifthBan(int fifthBan) {
        this.fifthBan = fifthBan;
    }

    public int getFirstPick() {
        return firstPick;
    }

    public void setFirstPick(int firstPick) {
        this.firstPick = firstPick;
    }

    public int getSecondPick() {
        return secondPick;
    }

    public void setSecondPick(int secondPick) {
        this.secondPick = secondPick;
    }

    public int getThirdPick() {
        return thirdPick;
    }

    public void setThirdPick(int thirdPick) {
        this.thirdPick = thirdPick;
    }

    public int getFourthPick() {
        return fourthPick;
    }

    public void setFourthPick(int fourthPick) {
        this.fourthPick = fourthPick;
    }

    public int getFifthPick() {
        return fifthPick;
    }

    public void setFifthPick(int fifthPick) {
        this.fifthPick = fifthPick;
    }
}
