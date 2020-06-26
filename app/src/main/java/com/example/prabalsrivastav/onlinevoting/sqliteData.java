package com.example.prabalsrivastav.onlinevoting;

/**
 * Created by Prabal Srivastav on 20-06-2020.
 */

public class sqliteData {

    //constructor(necessary)
    public sqliteData (String name ,String party ,String city ,String vote) {
        this.name = name;
        this.party = party;
        this.city = city;
        this.vote = vote;
    }

    private String name , party ,city ,vote;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
