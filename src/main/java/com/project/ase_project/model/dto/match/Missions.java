package com.project.ase_project.model.dto.match;

/**
 * Represents a MissionDto object from the Riot API that is not actually identified or mentioned in the API documentation.
 * This class is used to store information regarding the missions accomplished in a game.
 * This class is used by the Participant class as an attribute.
 */
public class Missions {
    // JSON FIELDS
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

    // GETTERS AND SETTERS
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

    // TOSTRING
    @Override
    public String toString() {
        return "Missions{" +
                "playerScore0=" + playerScore0 +
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
                '}';
    }
}
