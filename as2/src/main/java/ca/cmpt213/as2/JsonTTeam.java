package ca.cmpt213.as2;

import java.util.ArrayList;

public class JsonTTeam {

    private ArrayList<Tokimon> team;
    private String extra_comments;

    public JsonTTeam() {
        team = new ArrayList<Tokimon>();
    }

    public JsonTTeam(ArrayList<Tokimon> team) {
        this.team = team;
    }

    // Getters & Setters
    public ArrayList<Tokimon> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Tokimon> team) {
        this.team = team;
    }

    public String getExtCom() {
        return extra_comments;
    }

    public void setExtCom(String extCom) {
        this.extra_comments = extCom;
    }

}
