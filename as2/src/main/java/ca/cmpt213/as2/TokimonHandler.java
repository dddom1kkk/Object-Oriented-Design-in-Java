package ca.cmpt213.as2;

import java.util.ArrayList;
import java.util.List;

/**
 * TokimonHandler
 */
public class TokimonHandler {
    /**
     * HandlerTeam
     */
    private List<Tokimon> Tokimons;

    public TokimonHandler() {
        Tokimons = new ArrayList<Tokimon>();
    }

    public void addToTeam(Tokimon Toki) {
        Tokimons.add(Toki);
    }

    // Getters & Setters
    public List<Tokimon> getTeam() {
        return Tokimons;
    }

    public void setTeam(List<Tokimon> Tokimons) {
        this.Tokimons = Tokimons;
    }

}
