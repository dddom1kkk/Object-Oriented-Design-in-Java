package ca.cmpt213.as3.logic;

import java.util.ArrayList;

public class TokimonGrid {

    private class NpcPlace {
        private String place;

        public void putPlace(String letterPlace, int numPlace) {
            place = letterPlace + numPlace;
        }
    }

    private ArrayList<NpcPlace> tokimons;
    private ArrayList<NpcPlace> fokimons;

    public TokimonGrid() {
        tokimons = new ArrayList<>();
        fokimons = new ArrayList<>();
    }

    public void generateNpcLocations(String[] args) {
        Integer[] npcQuantity = ErrorHandler.checkGetSizes(args);

        // for () LAST HERE STOPPED
    }

    // Getters & Setters
    public ArrayList<NpcPlace> getTokimons() {
        return tokimons;
    }

    public void setTokimons(ArrayList<NpcPlace> tokimons) {
        this.tokimons = tokimons;
    }

    public ArrayList<NpcPlace> getFokimons() {
        return fokimons;
    }

    public void setFokimons(ArrayList<NpcPlace> fokimons) {
        this.fokimons = fokimons;
    }

}
