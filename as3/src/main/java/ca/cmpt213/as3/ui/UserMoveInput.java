package ca.cmpt213.as3.ui;

import java.util.Scanner;

import ca.cmpt213.as3.logic.ErrorHandler;
import ca.cmpt213.as3.logic.TokimonGridState;

public class UserMoveInput {

    public static TokimonGridState inputInitialLocation(Scanner inScan, String[] args) {

        TokimonGridState initial = new TokimonGridState(organizeArgumentInputs(args), "", 0);
        String in;
        int colNum;

        while (true) {
            System.out.print("Enter initial position on the grid: ");
            in = inScan.next();

            colNum = ErrorHandler.checkGetInitPosition(in);

            if (colNum != -1)
                break;
        }

        initial.changeRow(in.substring(0, 1));
        initial.changeColumn(colNum);

        return initial;

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
            System.out.println("Show: " + move);
            state.changeRow(move);
        }

        if (in.charAt(0) == 'D')
            state.changeColumn(state.retrieveColumn() + 1);

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

            for (int i = 0; i < sizes.length; i++) {
                System.out.println(sizes[i]);
            }

        }

        else if (args.length == 3) {

            sizes[2] = 1;
            ErrorHandler.fillBothKnown(sizes, args);

            for (int i = 0; i < sizes.length; i++) {
                System.out.println(sizes[i]);
            }

            return sizes;

        }

        else if (args.length == 2) {

            int cheatOn = 0;
            if (args[1].equalsIgnoreCase(ErrorHandler.getCheatChecker()))
                cheatOn = 1;

            sizes[2] = cheatOn;

            if (cheatOn == 0) {

                ErrorHandler.fillBothKnown(sizes, args);

                for (int i = 0; i < sizes.length; i++) {
                    System.out.println(sizes[i]);
                }

            }

            if (cheatOn == 1) {

                if (args[0].startsWith(ErrorHandler.getTokiChecker())) {
                    sizes[1] = 5;

                    sizes[0] = ErrorHandler.checkParsing(args[0], 5, 95);

                    for (int i = 0; i < sizes.length; i++) {
                        System.out.println(sizes[i]);
                    }

                }

                if (args[0].startsWith(ErrorHandler.getFokiChecker())) {
                    sizes[0] = 10;

                    sizes[1] = ErrorHandler.checkParsing(args[0], 5, 90);

                    for (int i = 0; i < sizes.length; i++) {
                        System.out.println(sizes[i]);
                    }

                }

            }

        }

        if (args.length == 1) {

            if (args[0].equalsIgnoreCase(ErrorHandler.getCheatChecker())) {
                sizes[0] = 10;
                sizes[1] = 5;
                sizes[2] = 1;

                for (int i = 0; i < sizes.length; i++) {
                    System.out.println(sizes[i]);
                }
            }

            if (args[0].startsWith(ErrorHandler.getTokiChecker())) {
                sizes[1] = 5;
                sizes[2] = 0;

                sizes[0] = ErrorHandler.checkParsing(args[0], 5, 95);

                for (int i = 0; i < sizes.length; i++) {
                    System.out.println(sizes[i]);
                }
            }

            if (args[0].startsWith(ErrorHandler.getFokiChecker())) {
                sizes[0] = 10;
                sizes[2] = 0;

                sizes[1] = ErrorHandler.checkParsing(args[0], 5, 90);

                for (int i = 0; i < sizes.length; i++) {
                    System.out.println(sizes[i]);
                }
            }

        }

        return sizes;

    }

}
