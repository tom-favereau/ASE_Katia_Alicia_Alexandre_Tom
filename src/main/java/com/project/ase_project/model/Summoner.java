package com.project.ase_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Here is our Model (from MVC) for players.
 * We annotate with @Entity (from jakarta persistence)
 * Jakarta Persistence defines a standard for
 * management of persistence and object/relational mapping
 * in Java(R) environments.
 * see https://jakarta.ee/specifications/persistence/
 */

@Entity
public class Summoner {
    /**
     * The following annotation allow to identify
     * the variable as the ID for the database
     * and the strategy to generate ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ppuid;
    private String username;

    public Summoner() {
    }

    public Summoner(Long id, String ppuid, String username) {
        this.id = id;
        this.ppuid = ppuid;
        this.username = username;
    }

    public Long getId(){
        return id;
    }

    public String getPpuid() {
        return ppuid;
    }

    public String getUsername(){
        return username;
    }

    public void setPpuid(String ppuid) {
        this.ppuid = ppuid;
    }

    public void setUsername(String username){
        this.username = username;
    }
}

