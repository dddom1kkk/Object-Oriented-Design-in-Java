package ca.cmpt213.as3.ui;

import java.util.Scanner;

import ca.cmpt213.as3.logic.ErrorHandler;
import ca.cmpt213.as3.logic.TokimonGridState;

public class GridInterface {

    public static void startGame(String[] args) {

        Scanner scan = new Scanner(System.in);
        int choice;

        ErrorHandler.checkArgs(args);

        TokimonGridState npcs = UserMoveInput.inputInitialLocation(scan, args);
        npcs.generateNpcLocations();

        while (true) {

            showGrid(npcs);

            choice = UserMoveInput.makeChoice(scan);

            if (choice == 0)
                break;
            UserMoveInput.moveInGrid(scan, npcs);

        }

        scan.close();

    }

    private static void showGrid(TokimonGridState npc) {

        char letter = 'A';

        System.out.println("\nCurrent Game Grid:");

        System.out.print(" "); // column numeration
        for (int i = 1; i <= 10; i++) {
            if (i < 10)
                System.out.print("  " + i);
            else
                System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 1; i <= 10; i++) {
            System.out.print(letter++); // row numeration
            for (int j = 1; j <= 10; j++) {
                if (i == npc.retrieveRow().charAt(0) - ('A' - 1) && j == npc.retrieveColumn())
                    System.out.print("  @");
                else
                    System.out.print("  ~");
            }
            System.out.println();
        }

        System.out.println();

    }

}
