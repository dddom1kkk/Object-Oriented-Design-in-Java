package ca.cmpt213.as3.logic;

import java.util.ArrayList;

public class TokimonGridState {

    private class NpcPlace {

        private String row;
        private int column;

        public NpcPlace(String row, int column) {
            this.row = row;
            this.column = column;
        }

        // Getters & Setters
        public String retrieveRow() {
            return row;
        }

        public void changeRow(String letterPlace) {
            this.row = letterPlace;
        }

        public int retrieveColumn() {
            return column;
        }

        public void changeColumn(int numPlace) {
            this.column = numPlace;
        }

    }

    private Integer[] sizeNpc;
    private ArrayList<NpcPlace> tokimons;
    private ArrayList<NpcPlace> fokimons;
    private NpcPlace userPosition;
    private boolean cheating;

    public TokimonGridState() {
    }

    public TokimonGridState(Integer[] sizes, String row, int column) {
        sizeNpc = sizes;
        tokimons = new ArrayList<>();
        fokimons = new ArrayList<>();
        userPosition = new NpcPlace(row, column);
        if (sizes[0] == 1)
            cheating = true;
        else
            cheating = false;
    }

    public boolean isCheatOn() {
        return cheating;
    }

    public void generateNpcLocations() {

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

    public String retrieveRow() {
        return userPosition.retrieveRow();
    }

    public void changeRow(String letter) {
        userPosition.changeRow(letter);
    }

    public int retrieveColumn() {
        return userPosition.retrieveColumn();
    }

    public void changeColumn(int column) {
        userPosition.changeColumn(column);
    }

}
