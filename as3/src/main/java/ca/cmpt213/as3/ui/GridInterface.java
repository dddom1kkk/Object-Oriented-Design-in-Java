package ca.cmpt213.as3.ui;

import java.util.Scanner;

import ca.cmpt213.as3.logic.ErrorHandler;
import ca.cmpt213.as3.logic.TokimonGridState;
import ca.cmpt213.as3.logic.TokimonGridState.NpcPlace;

public class GridInterface {

    public static void startGame(String[] args) {

        Scanner scan = new Scanner(System.in);
        int choice;

        ErrorHandler.checkArgs(args);

        TokimonGridState npcs = new TokimonGridState(UserMoveInput.organizeArgumentInputs(args), "", 0);
        npcs.generateNpcLocations();

        if (npcs.isCheatOn())
            showCheatGrid(npcs, 0);
        UserMoveInput.inputInitialLocation(scan, npcs, args);

        while (true) {

            if (showGrid(npcs) == 1)
                break;

            choice = UserMoveInput.makeChoice(scan);

            if (choice == 0)
                break;
            if (choice == 1)
                UserMoveInput.moveInGrid(scan, npcs);
            if (choice == 2)
                UserMoveInput.spellInGrid(scan, npcs);

        }

        scan.close();

    }

    private static int showGrid(TokimonGridState npc) {

        char letter = 'A';
        int visit = 0;
        boolean onFoki = npc.isOnFoki();

        System.out.println("\nCurrent Game Grid:");

        System.out.print("   "); // column numeration
        for (int i = 1; i <= 10; i++) {
            if (i < 9)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 1; i <= 10; i++) {
            System.out.print(letter++ + "  "); // row numeration
            for (int j = 1; j <= 10; j++) {
                if (i == npc.retrieveRow().charAt(0) - ('A' - 1) && j == npc.retrieveColumn()) {
                    for (NpcPlace toki : npc.retrieveCollectedTokis())
                        if (i == toki.retrieveRow().charAt(0) - ('A' - 1) && j == toki.retrieveColumn())
                            visit = 1;

                    if (visit == 1)
                        System.out.print("@$ ");
                    else {
                        if (onFoki)
                            System.out.print("@X ");
                        else
                            System.out.print("@  ");
                    }
                    visit = 0;
                } else {
                    for (NpcPlace checked : npc.retrieveCheckedLocations())
                        if (i == checked.retrieveRow().charAt(0) - ('A' - 1) && j == checked.retrieveColumn())
                            visit = 1;
                    for (NpcPlace tokiChecked : npc.retrieveCollectedTokis())
                        if (i == tokiChecked.retrieveRow().charAt(0) - ('A' - 1) && j == tokiChecked.retrieveColumn())
                            visit = 2;
                    for (NpcPlace fokiRevealed : npc.retrieveRevealedFokis())
                        if (i == fokiRevealed.retrieveRow().charAt(0) - ('A' - 1) && j == fokiRevealed.retrieveColumn())
                            visit = 1;

                    if (visit == 1)
                        System.out.print("   ");
                    else if (visit == 2)
                        System.out.print("$  ");
                    else
                        System.out.print("~  ");
                    visit = 0;
                }
            }
            System.out.println();
        }

        System.out.println();

        System.out.println("Tokimons collected: " + npc.retrieveCollectedTokis().size() + "\nTokimons remaining: "
                + npc.getTokimons().size() + "\nSpell usage remaining: "
                + UserMoveInput.getNumSpellsLeft() + "\n");

        if (isGameOver(onFoki, npc) == 1)
            return 1;

        if (isGameWin(npc.getTokimons().isEmpty(), npc) == 1)
            return 1;

        return 0;

    }

    private static void showCheatGrid(TokimonGridState npc, int outcome) {

        char letter = 'A';
        int visit = 0;

        if (outcome == 0)
            System.out.println("\nCurrent Game Grid in Cheat Mode:");
        else if (outcome == -1)
            System.out.println("\nRevealed Game Grid after Game is Over:");
        else
            System.out.println("\nRevealed Game Grid after Winning the Game:");

        System.out.print("   "); // column numeration
        for (int i = 1; i <= 10; i++) {
            if (i < 9)
                System.out.print(i + "  ");
            else
                System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 1; i <= 10; i++) {
            System.out.print(letter++ + "  "); // row numeration
            for (int j = 1; j <= 10; j++) {
                if (outcome != 0 && i == npc.retrieveRow().charAt(0) - ('A' - 1) && j == npc.retrieveColumn()) {
                    if (outcome == -1)
                        System.out.print("@X ");
                    else if (outcome == 1)
                        System.out.print("@$ ");
                    visit = 3;
                }
                if (visit == 0) {
                    for (NpcPlace toki : npc.getTokimons())
                        if (i == toki.retrieveRow().charAt(0) - ('A' - 1) && j == toki.retrieveColumn())
                            visit = 1;
                    for (NpcPlace tokiChecked : npc.retrieveCollectedTokis())
                        if (i == tokiChecked.retrieveRow().charAt(0) - ('A' - 1) && j == tokiChecked.retrieveColumn())
                            visit = 1;
                    for (NpcPlace fokiRevealed : npc.getFokimons())
                        if (i == fokiRevealed.retrieveRow().charAt(0) - ('A' - 1) && j == fokiRevealed.retrieveColumn())
                            visit = 2;
                }

                if (visit == 0)
                    System.out.print("   ");
                else if (visit == 1)
                    System.out.print("$  ");
                else if (visit == 2)
                    System.out.print("X  ");
                visit = 0;
            }
            System.out.println();
        }

    }

    private static int isGameOver(boolean onFoki, TokimonGridState npc) {

        if (onFoki) {
            System.out.println(
                    "OOPS! You have encountered a Fokimon. You cannot become a Master of Tokimons. Try next time!\nGame over!\n");
            showCheatGrid(npc, -1);
            return 1;
        }

        return 0;

    }

    private static int isGameWin(boolean onToki, TokimonGridState npc) {

        if (onToki) {
            System.out.println("CONGRATULATIONS!!! You won!\n---You are honored Master of Tokimons---\n");
            showCheatGrid(npc, 1);
            return 1;
        }

        return 0;

    }

}
