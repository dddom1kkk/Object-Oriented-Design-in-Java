package ca.cmpt213.as3.ui;

import java.util.Scanner;

import ca.cmpt213.as3.logic.ErrorHandler;
import ca.cmpt213.as3.logic.TokimonGridState;

/**
 * UserMoveInput - class handling inputs from the User playing this game.
 */
public class UserMoveInput {

    private static int spellCount = 3;

    public static void inputInitialLocation(Scanner inScan, TokimonGridState initial, String[] args) {

        String in;
        int colNum;

        while (true) {
            System.out.print("\nEnter initial position on the grid: ");
            in = inScan.next();

            colNum = ErrorHandler.checkGetInitPosition(in);

            if (colNum != -1)
                break;
        }

        initial.changeRow(in.substring(0, 1));
        initial.changeColumn(colNum);

        initial.visitedLocation();

    }

    public static void moveInGrid(Scanner inScan, TokimonGridState state) {

        String in;

        while (true) {

            System.out.print("Move (W, A, S, D): ");
            in = inScan.next();

            if (ErrorHandler.checkValidMove(state, in))
                break;

        }

        String move = "";

        if (in.charAt(0) == 'W') {
            move += (char) (state.retrieveRow().charAt(0) - 1);
            state.changeRow(move);
        }

        if (in.charAt(0) == 'A')
            state.changeColumn(state.retrieveColumn() - 1);

        if (in.charAt(0) == 'S') {
            move += (char) (state.retrieveRow().charAt(0) + 1);
            state.changeRow(move);
        }

        if (in.charAt(0) == 'D')
            state.changeColumn(state.retrieveColumn() + 1);

        state.visitedLocation();

    }

    public static void spellInGrid(Scanner inScan, TokimonGridState state) {

        if (spellCount == 0) {
            System.out.println("\nNo spells left to use");
            return;
        }

        String in;
        int check;

        while (true) {
            System.out.print(
                    "\nChoose what spell do you want to use:\n1. Jump the player to another grid location.\n2. Randomly reveal the location of one of the Tokimons.\n3. Randomly eliminate one of the Fokimons.\n\nSpell: ");
            in = inScan.next();

            check = ErrorHandler.checkValidSpell(in);
            if (check != -1)
                break;
        }

        spellCount--;

        if (check == 1) {
            int randRow = (int) (Math.random() * 10 + 1);
            int randColumn = (int) (Math.random() * 10 + 1);
            String row = "";
            row += (char) (randRow + ('A' - 1));

            state.changeRow(row);
            state.changeColumn(randColumn);

            state.visitedLocation();

            return;
        }

        if (check == 2) {

            int randToki = (int) (Math.random() * state.getTokimons().size());
            String saveRow = state.retrieveRow();
            int saveColumn = state.retrieveColumn();

            state.changeRow(state.getTokimons().get(randToki).retrieveRow());
            state.changeColumn(state.getTokimons().get(randToki).retrieveColumn());

            state.visitedLocation();

            state.changeRow(saveRow);
            state.changeColumn(saveColumn);

            return;

        }

        if (check == 3) {

            int randFoki = (int) (Math.random() * state.getFokimons().size());

            System.out.println("\nFokimon eliminated at location :" + state.getFokimons().get(randFoki).retrieveRow()
                    + state.getFokimons().get(randFoki).retrieveColumn());
            state.changeFokiToRevealed(randFoki);

            return;

        }

    }

    public static int makeChoice(Scanner inScan) {

        String choice;
        int check;

        while (true) {
            System.out.print(
                    "Choose an option:\n1. Move up, down, left, or right from their current position (using keys W, A, S, or D).\n2. Use a spell.\n\n0. Exit the Game\n\nDecision: ");

            choice = inScan.next();

            check = ErrorHandler.checkMakeChoices(choice);

            if (check != -1)
                break;

        }

        return check;

    }

    public static Integer[] organizeArgumentInputs(String[] args) {
        Integer[] sizes = new Integer[3]; // Details: index 0 = size of Tokimons, index 1 = size of Fokimons, index 2 =
                                          // = cheat is on/off

        if (args.length == 0) {

            sizes[0] = 10;
            sizes[1] = 5;
            sizes[2] = 0;
        }

        ErrorHandler.fillBothKnown(sizes, args);

        return sizes;

    }

    public static int getNumSpellsLeft() {
        return spellCount;
    }

}
