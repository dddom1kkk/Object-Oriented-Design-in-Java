package ca.cmpt213.as2;

import java.util.ArrayList;

public class TeamSorter {

    private JsonTTeam[] allTokimons;

    public TeamSorter(JsonTTeam[] allTokis) {
        this.allTokimons = allTokis;
    }

    public int countTeams() {
        int num = 0;
        ArrayList<Integer> check = new ArrayList<Integer>();
        String parse = "";
        String team = allTokimons[0].getTeam().get(0).getId();
        int i = 0, j = 0;
        for (int k = 0; k < team.length(); k++) {

            if (k < team.length() - 2 && team.charAt(k) == '-' && team.charAt(k + 1) == 't') {
                k = k + 2;

                while (team.charAt(k) >= '0' && team.charAt(k) <= '9') {
                    parse += team.charAt(k);
                    k++;
                }

                if (parse.isEmpty()) {
                    System.out.println("Error: No team identified");
                    return -1;
                } else {
                    num = Integer.parseInt(parse);
                    if (!check.contains(num))
                        check.add(num);
                    k = 0;
                    j++;
                    if (j == allTokimons[i].getTeam().size()) {
                        if (i == allTokimons.length - 1)
                            break;
                        i++;
                        j = 0;
                    }
                    team = allTokimons[i].getTeam().get(j).getId();
                    parse = "";
                }

            }
        }

        if (check.size() == 0) {
            System.out.println("Error: No teams identified");
            return -1;
        }
        return check.size();
    }

}
