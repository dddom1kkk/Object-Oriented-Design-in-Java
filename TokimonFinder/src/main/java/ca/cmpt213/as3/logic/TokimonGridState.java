package ca.cmpt213.as3.logic;

import java.util.ArrayList;

/**
 * TokimonGridState - class representing state of User, Tokimon, Fokimon
 * location. Randomly generates Tokimons and Fokimons on the grid. Logic state
 * of the Grid Interface.
 */
public class TokimonGridState {

    /**
     * NpcPlace - class representing location consisting of row and column. Used for
     * TokimonGridState for NPC and User locations.
     */
    public class NpcPlace {

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

    private final int TOKI = 0, FOKI = 1;

    private Integer[] sizeNpc;
    private ArrayList<NpcPlace> collectedTokis;
    private ArrayList<NpcPlace> revealedFokis;
    private static ArrayList<NpcPlace> checkedLocations;
    private ArrayList<NpcPlace> tokimons;
    private ArrayList<NpcPlace> fokimons;
    private NpcPlace userPosition;
    private boolean cheating;

    public TokimonGridState() {
    }

    public TokimonGridState(Integer[] sizes, String row, int column) {
        sizeNpc = sizes;
        collectedTokis = new ArrayList<>();
        revealedFokis = new ArrayList<>();
        checkedLocations = new ArrayList<>();
        tokimons = new ArrayList<>();
        fokimons = new ArrayList<>();
        userPosition = new NpcPlace(row, column);
        if (sizes[2] == 1)
            cheating = true;
        else
            cheating = false;
    }

    public boolean isCheatOn() {
        return cheating;
    }

    public void generateNpcLocations() {

        int randomRow, randomColumn = -1, countDiff = -1;
        String letterNpc = null;
        NpcPlace npc;

        for (int i = 0; i < sizeNpc.length - 1; i++) {
            for (int j = 0; j < sizeNpc[i]; j++) {
                npc = new NpcPlace("", -1);

                while (countDiff != tokimons.size() + fokimons.size()) {
                    countDiff = 0;
                    letterNpc = "";

                    randomRow = (int) (Math.random() * 10 + 1);
                    letterNpc += (char) (randomRow + ('A' - 1));

                    randomColumn = (int) (Math.random() * 10 + 1);

                    for (NpcPlace toki : tokimons)
                        if ((!toki.row.equals(letterNpc)
                                || toki.column != randomColumn))
                            countDiff++;
                    for (NpcPlace foki : fokimons)
                        if ((!foki.row.equals(letterNpc)
                                || foki.column != randomColumn))
                            countDiff++;
                }

                npc.row = letterNpc;
                npc.column = randomColumn;

                if (i == TOKI)
                    tokimons.add(npc);
                else if (i == FOKI)
                    fokimons.add(npc);
            }
        }

    }

    public boolean isOnFoki() {

        for (NpcPlace foki : fokimons)
            if (userPosition.row.equals(foki.row) && userPosition.column == foki.column)
                return true;

        return false;

    }

    public void visitedLocation() {
        for (NpcPlace toki : tokimons) {
            if (toki.row.equals(userPosition.row) && toki.column == userPosition.column) {
                isTokiCatched(userPosition);
                tokimons.remove(toki);
                return;
            }
        }
        checkedLocations.add(new NpcPlace(userPosition.row, userPosition.column));
    }

    public void changeFokiToRevealed(int indexFoki) {
        revealedFokis.add(fokimons.remove(indexFoki));
    }

    private void isTokiCatched(NpcPlace catched) {
        collectedTokis.add(new NpcPlace(catched.row, catched.column));
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

    public NpcPlace revealUserPosition() {
        return userPosition;
    }

    public ArrayList<NpcPlace> retrieveCheckedLocations() {
        return checkedLocations;
    }

    public ArrayList<NpcPlace> retrieveCollectedTokis() {
        return collectedTokis;
    }

    public ArrayList<NpcPlace> retrieveRevealedFokis() {
        return revealedFokis;
    }

}
