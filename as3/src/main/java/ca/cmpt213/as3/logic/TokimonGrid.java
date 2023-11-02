package ca.cmpt213.as3.logic;

import java.util.ArrayList;

public class TokimonGrid {

    private class NpcPlace {

        private String row;
        private int column;

        public NpcPlace(String row, int column) {
            this.row = row;
            this.column = column;
        }

        public void changePosition(String letterPlace, int numPlace) {
            new NpcPlace(letterPlace, numPlace);
        }

    }

    private ArrayList<NpcPlace> tokimons;
    private ArrayList<NpcPlace> fokimons;
    private NpcPlace userPosition;

    public TokimonGrid(String row, int column) {
        tokimons = new ArrayList<>();
        fokimons = new ArrayList<>();
        userPosition = new NpcPlace(row, column);
    }

    public void generateNpcLocations(String[] args) {
        Integer[] npcQuantity = ErrorHandler.checkGetSizes(args);

        // for ()
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

    public NpcPlace getUserPosition() {
        return userPosition;
    }

    public String getLetter() {
        return userPosition.row;
    }

    public int getNumber() {
        return userPosition.column;
    }

}
