package ca.cmpt213.as2;

import java.util.ArrayList;

/**
 * TokimonHandler
 */
public class TokimonHandler {
    /**
     * HandlerTeam
     */
    private ArrayList<JsonTokimonTeam> Tokimons;

    public TokimonHandler() {
        Tokimons = new ArrayList<JsonTokimonTeam>();
    }

    public void addToTeam(JsonTokimonTeam Toki) {
        Tokimons.add(Toki);
    }

    public void arrangeTokimons() {

        String checker;
        String swapper;
        JsonTokimonTeam swap;

        for (int i = 0; i < Tokimons.size(); i++) {

            checker = Tokimons.get(i).getFileTeam().get(Tokimons.get(i).getFileTeam().size() - 1).getId();

            for (int j = i + 1; j < Tokimons.size(); j++) {

                swapper = Tokimons.get(j).getFileTeam().get(Tokimons.get(j).getFileTeam().size() - 1).getId();

                if (swapper.compareToIgnoreCase(checker) < 0) {
                    swap = Tokimons.get(i);
                    Tokimons.set(i, Tokimons.get(j));
                    Tokimons.set(j, swap);
                    checker = Tokimons.get(i).getFileTeam().get(Tokimons.get(i).getFileTeam().size() - 1).getId();
                }

            }

        }

    }

    // Getters & Setters
    public ArrayList<JsonTokimonTeam> getTeam() {
        return Tokimons;
    }

    public void setTeam(ArrayList<JsonTokimonTeam> Tokimons) {
        this.Tokimons = Tokimons;
    }

}
