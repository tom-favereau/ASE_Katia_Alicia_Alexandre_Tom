package com.project.ase_project.model.dto.match;

/**
 * Represents an PerkStyleSelectionDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store the information on a certain rune.
 * This class is used by the Style class as an element of an ArrayList.
 */
public class Selection {
    // JSON FIELDS
    private int perk;
    private int var1;
    private int var2;
    private int var3;

    // GETTERS AND SETTERS
    public int getPerk() {
        return perk;
    }
    public void setPerk(int perk) {
        this.perk = perk;
    }
    public int getVar1() {
        return var1;
    }
    public void setVar1(int var1) {
        this.var1 = var1;
    }
    public int getVar2() {
        return var2;
    }
    public void setVar2(int var2) {
        this.var2 = var2;
    }
    public int getVar3() {
        return var3;
    }
    public void setVar3(int var3) {
        this.var3 = var3;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Selection{" +
                "perk=" + perk +
                ", var1=" + var1 +
                ", var2=" + var2 +
                ", var3=" + var3 +
                '}';
    }
}
