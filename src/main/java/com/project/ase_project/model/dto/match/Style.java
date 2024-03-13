package com.project.ase_project.model.dto.match;

import java.util.ArrayList;

/**
 * Represents an PerkStyleDto object from the Riot API (https://developer.riotgames.com/apis#match-v5/GET_getMatch/)
 * This class is used to store the information on the primary and secondary runes chosen by a player.
 * This class is used by the Perks class as an element of an ArrayList.
 */
public class Style {
    // JSON FIELDS
    private String description;
    private ArrayList<Selection> selections;
    private int style;

    // GETTERS AND SETTERS
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<Selection> getSelections() {
        return selections;
    }
    public void setSelections(ArrayList<Selection> selections) {
        this.selections = selections;
    }
    public int getStyle() {
        return style;
    }
    public void setStyle(int style) {
        this.style = style;
    }

    // TOSTRING
    @Override
    public String toString() {
        return "Style{" +
                "description='" + description + '\'' +
                ", selections=" + selections +
                ", style=" + style +
                '}';
    }
}
