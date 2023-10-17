package ca.cmpt213.as2;

import java.util.ArrayList;

public class TeamSorter {

    private JsonTTeam[] allTokimons;
    private int numOfTeams;

    public TeamSorter(JsonTTeam[] allTokis) {
        this.allTokimons = allTokis;
    }

    private void checkError() {
        ArrayList<ArrayList<String>> ids = new ArrayList<>();
        ArrayList<String> id;

        int check = 0, equal = 0;

        for (JsonTTeam fileTeam : allTokimons) {

            id = new ArrayList<>();

            for (Tokimon toki : fileTeam.getTeam()) {

                if (id.contains(toki.getId())) {
                    System.out.println("Error: Same identifier in one file");
                    System.exit(-1);
                }

                id.add(toki.getId());

            }

            for (ArrayList<String> checker : ids) {

                for (String toki : id) {
                    if (checker.contains(toki))
                        check++;
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

            for (JsonTTeam fileTeam : allTokimons) {

                id = new ArrayList<>();

                for (Tokimon toki : fileTeam.getTeam()) {

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

        numOfTeams = ids.size();

    }

    // Getter
    public int getNumOfTeams() {
        checkError();
        return numOfTeams;
    }

}
