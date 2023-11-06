package ca.cmpt213.as2;

import java.util.ArrayList;

public class TeamSorter {

    private ArrayList<JsonTokimonTeam> allTokimons;
    private int numOfTeams;

    public TeamSorter(ArrayList<JsonTokimonTeam> allTokis) {
        this.allTokimons = allTokis;
    }

    private ArrayList<ArrayList<String>> checkError() {
        ArrayList<ArrayList<String>> ids = new ArrayList<>();
        ArrayList<String> id;
        Tokimon swap;

        int check = 0, equal = 0;

        for (JsonTokimonTeam fileTeam : allTokimons) {

            for (int i = 1; i < fileTeam.getFileTeam().size(); i++) {

                for (int j = i + 1; j < fileTeam.getFileTeam().size(); j++) {

                    if (fileTeam.getFileTeam().get(j).getId()
                            .compareToIgnoreCase(fileTeam.getFileTeam().get(i).getId()) < 0) {
                        swap = fileTeam.getFileTeam().get(i);
                        fileTeam.getFileTeam().set(i, fileTeam.getFileTeam().get(j));
                        fileTeam.getFileTeam().set(j, swap);
                    }

                }

            }

            swap = fileTeam.getFileTeam().get(0);
            fileTeam.getFileTeam().set(0, fileTeam.getFileTeam().get(fileTeam.getFileTeam().size() - 1));
            fileTeam.getFileTeam().set(fileTeam.getFileTeam().size() - 1, swap);

        }

        for (JsonTokimonTeam fileTeam : allTokimons) {

            id = new ArrayList<>();

            for (Tokimon toki : fileTeam.getFileTeam()) {

                if (id.contains(toki.getId())) {
                    System.out.println("Error: Same identifier in one file");
                    System.exit(-1);
                }

                id.add(toki.getId());

            }

            for (ArrayList<String> checker : ids) {

                for (String toki : id) {
                    if (checker.contains(toki)) {
                        check++;
                    }
                }

                if (check != 0 && check != checker.size()) {
                    System.out.println(" Error: Wrong json files");
                    System.exit(-1);
                }

                if (check != 0)
                    equal++;

                check = 0;

            }

            if (equal == 0)
                ids.add(id);

            equal = 0;

        }

        int numFile;

        for (ArrayList<String> checker : ids) {

            check = 0;
            numFile = checker.size() * checker.size();

            for (JsonTokimonTeam fileTeam : allTokimons) {

                id = new ArrayList<>();

                for (Tokimon toki : fileTeam.getFileTeam()) {

                    id.add(toki.getId());

                }

                for (String each : id) {
                    for (String toki : checker) {

                        if (each.equalsIgnoreCase(toki))
                            check++;

                    }
                }

            }

            if (numFile != check) {
                System.out.println("Error: Not enough files for Tokemons");
                System.exit(-1);
            }

        }

        return ids;

    }

    public ArrayList<TokimonHandler> divideTeams() {

        ArrayList<ArrayList<String>> ids = checkError();
        ArrayList<TokimonHandler> teams = new ArrayList<>();
        TokimonHandler handler;

        for (ArrayList<String> team : ids) {

            handler = new TokimonHandler();

            for (JsonTokimonTeam each : allTokimons) {

                if (team.contains(each.getFileTeam().get(0).getId()))
                    handler.addToTeam(each);
            }

            handler.arrangeTokimons();
            teams.add(handler);

        }

        JsonTokimonTeam checker, each;

        for (TokimonHandler team : teams) {

            checker = team.getTeam().get(0);

            for (Tokimon toki : checker.getFileTeam()) {

                for (int i = 1; i < team.getTeam().size(); i++) {

                    each = team.getTeam().get(i);

                    for (int j = 0; j < each.getFileTeam().size(); j++) {

                        if (toki.getId().equalsIgnoreCase(each.getFileTeam().get(j).getId())
                                && (!toki.getName().equalsIgnoreCase(each.getFileTeam().get(j).getName())
                                        || !each.getFileTeam().get(j).getComment().contains(toki.getName()))
                                || !each.getFileTeam().get(j).getComment()
                                        .contains(each.getFileTeam().get(each.getFileTeam().size() - 1).getName())) {
                            System.out.println("Error: Wrong inputs in Tokimons " + toki.getId() + " "
                                    + each.getFileTeam().get(j).getId());
                            System.exit(-1);
                        }

                    }

                }

            }

            for (JsonTokimonTeam fileTeam : team.getTeam()) {

                for (Tokimon toki : fileTeam.getFileTeam()) {

                    if (toki.getScore() < 0) {
                        System.out.println("Error: Invalid Score");
                        System.exit(-1);
                    }

                }

            }

        }

        numOfTeams = teams.size();

        return teams;

    }

    // Getter
    public int getNumOfTeams() {
        return numOfTeams;
    }

}
