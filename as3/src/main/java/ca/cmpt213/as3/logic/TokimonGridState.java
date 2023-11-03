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

    private final int TOKI = 0, FOKI = 1;

    private Integer[] sizeNpc;
    private ArrayList<NpcPlace> collected;
    private ArrayList<NpcPlace> tokimons;
    private ArrayList<NpcPlace> fokimons;
    private NpcPlace userPosition;
    private boolean cheating;

    public TokimonGridState() {
    }

    public TokimonGridState(Integer[] sizes, String row, int column) {
        sizeNpc = sizes;
        collected = new ArrayList<>();
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
            System.out.println("Wakakaka" + sizeNpc.length);
            for (int j = 0; j < sizeNpc[i]; j++) {
                npc = new NpcPlace("", -1);

                while (countDiff != tokimons.size() + fokimons.size()) {
                    countDiff = 0;
                    letterNpc = "";

                    randomRow = (int) (Math.random() * 10 + 1);
                    letterNpc += (char) (randomRow + ('A' - 1));

                    randomColumn = (int) (Math.random() * 10 + 1);

                    for (NpcPlace toki : tokimons)
                        if ((!toki.retrieveRow().equals(letterNpc)
                                || toki.retrieveColumn() != randomColumn))
                            countDiff++;
                    for (NpcPlace foki : fokimons)
                        if ((!foki.retrieveRow().equals(letterNpc)
                                || foki.retrieveColumn() != randomColumn))
                            countDiff++;
                }

                npc.changeRow(letterNpc);
                npc.changeColumn(randomColumn);

                if (i == TOKI)
                    tokimons.add(npc);
                else if (i == FOKI)
                    fokimons.add(npc);
            }
        }

        System.out.println();
        for (NpcPlace toki : tokimons) {
            System.out.print(toki.retrieveRow());
            System.out.println(toki.retrieveColumn());
        }
        System.out.println();
        for (NpcPlace foki : fokimons) {
            System.out.print(foki.retrieveRow());
            System.out.println(foki.retrieveColumn());
        }

    }

    public ArrayList<NpcPlace> collectedNumTokis() {
        return collected;
    }

    public void isTokiCatched(NpcPlace catched) {
        collected.add(catched);
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
