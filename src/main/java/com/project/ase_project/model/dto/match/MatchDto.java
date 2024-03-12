package com.project.ase_project.model.dto.match;

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
}

