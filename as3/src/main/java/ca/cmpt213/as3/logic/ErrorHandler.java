package ca.cmpt213.as3.logic;

public class ErrorHandler {

    private final static String[] input = { "--numToki=", "--numFoki=", "--cheat" };

    public static void checkArgs(String[] args) {
        if (args.length > 0) {
            checkInvalidOne(args);
            checkInvalidTwo(args);
            checkInvalidThree(args);
        }
    }

    public static int checkGetInitPosition(String in) {

        int colNum;

        if (in.length() >= 2 && in.length() <= 3) {
            if (in.charAt(0) >= 'A' && in.charAt(0) <= 'A' + 9) {
                try {
                    colNum = Integer.parseInt(in.substring(1));

                    if (colNum > 0 && colNum < 11)
                        return colNum;
                } catch (NumberFormatException e) {
                    System.out.println(
                            "\nWrong place specified.\nEnter a letter (row) from 'A' to 'J' following with an integer (column) from 1 to 10.\n");
                    return -1;
                }
            }
        }

        System.out.println(
                "\nWrong place specified.\nEnter a letter (row) from 'A' to 'J' following with an integer (column) from 1 to 10.\n");

        return -1;

    }

    public static boolean checkValidMove(TokimonGridState initial, String in) {

        if (in.length() != 1 || (in.charAt(0) != 'W' && in.charAt(0) != 'A' && in.charAt(0) != 'S'
                && in.charAt(0) != 'D')) {
            System.out.println(
                    "\nWrong input move key specified. Enter W to move up, A to move left, S to move down, D to move right.\n");
            return false;
        }

        if (initial.retrieveRow().charAt(0) == 'A' && in.charAt(0) == 'W') {
            System.out.println(
                    "\nWrong input move key specified. Cannot move up.\nEnter W to move up, A to move left, S to move down, D to move right.\n");
            return false;
        }

        if (initial.retrieveRow().charAt(0) == 'A' + 9 && in.charAt(0) == 'S') {
            System.out.println(
                    "\nWrong input move key specified. Cannot move down.\nEnter W to move up, A to move left, S to move down, D to move right.\n");
            return false;
        }

        if (initial.retrieveColumn() == 1 && in.charAt(0) == 'A') {
            System.out.println(
                    "\nWrong input move key specified. Cannot move left.\nEnter W to move up, A to move left, S to move down, D to move right.\n");
            return false;
        }

        if (initial.retrieveColumn() == 10 && in.charAt(0) == 'D') {
            System.out.println(
                    "\nWrong input move key specified. Cannot move right.\nEnter W to move up, A to move left, S to move down, D to move right.\n");
            return false;
        }

        return true;

    }

    public static int checkMakeChoices(String choice) {

        int decision;

        if (choice.length() != 1) {
            System.out.println(
                    "\nWrong option input. Choose '1' to make a move, '2' to cast a spell, '0' to leave the game.\n");
            return -1;
        }

        try {
            decision = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println(
                    "\nWrong option input. Choose '1' to make a move, '2' to cast a spell, '0' to leave the game.\n");
            return -1;
        }

        if (decision < 0 || decision > 2) {
            System.out.println(
                    "\nWrong option input. Choose '1' to make a move, '2' to cast a spell, '0' to leave the game.\n");
            return -1;
        }

        return decision;

    }

    public static void fillBothKnown(Integer[] sizes, String[] args) {

        int total = 0;

        for (int i = 0; i < sizes.length - 1; i++) {
            try {

                sizes[i] = Integer.parseInt(args[i].substring(input[i].length()));

                if (sizes[i] > 95 || sizes[i] < 5) {
                    System.out.println(
                            "Error: Invalid argument input quantity. Expected quantity from 5 to 95: 1.--numToki=<quantity> | 2.--numFoki=<quantity>");
                    System.exit(-1);
                }

                total += sizes[i];

            } catch (NumberFormatException e) {
                System.out.println(
                        "Error: Invalid argument input quantity. Expected quantity from 5 to 95: 1.--numToki=<quantity> | 2.--numFoki=<quantity>");
                System.exit(-1);
            }
        }

        if (total > 100) {
            System.out.println(
                    "Error: Invalid argument input quantity. Expected total quantity from both '--numToki=' and '--numFoki=' should be at most 100: 1.--numToki=<quantity> | 2.--numFoki=<quantity>");
            System.exit(-1);
        }

    }

    public static int checkParsing(String arg, int minEdge, int maxEdge) {

        int ans = 0;

        try {
            ans = Integer.parseInt(arg.substring(ErrorHandler.getTokiChecker().length()));

            if (ans > maxEdge || ans < minEdge) {
                System.out.println(
                        "Error: Invalid argument input quantity. Expected quantity from 5 to 95: 1.--numToki=<quantity> | 2.--numFoki=<quantity>");
                System.exit(-1);
            }
        } catch (NumberFormatException e) {
            System.out.println(
                    "Error: Invalid argument input quantity. Expected quantity from 5 to 95: 1.--numToki=<quantity> | 2.--numFoki=<quantity>");
            System.exit(-1);
        }

        return ans;

    }

    // Getters for final attributes
    public static String getTokiChecker() {
        return input[0];
    }

    public static String getFokiChecker() {
        return input[1];
    }

    public static String getCheatChecker() {
        return input[2];
    }

    // Helper functions for error checking
    private static void checkInvalidOne(String[] args) {

        if (args.length == 1)
            return;

        int check = 0;

        // Check duplicates for "--numToki="
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith(input[0]))
                check++;
            if (check > 1) {
                System.out.println(
                        "Error: Invalid argument input duplicates. Expected sequence: 1.--numToki=<quantity> | 2.--numFoki=<quantity> | 3.--check");
                System.exit(-1);
            }
        }

        check = 0;

        // Check duplicates for "--numFoki="
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith(input[1]))
                check++;
            if (check > 1) {
                System.out.println(
                        "Error: Invalid argument input duplicates. Expected sequence: 1.--numToki=<quantity> | 2.--numFoki=<quantity> | 3.--check");
                System.exit(-1);
            }
        }

        check = 0;

        // Check duplicates for "--cheat"
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith(input[2]))
                check++;
            if (check > 1) {
                System.out.println(
                        "Error: Invalid argument input duplicates. Expected sequence: 1.--numToki=<quantity> | 2.--numFoki=<quantity> | 3.--check");
                System.exit(-1);
            }
        }

    }

    private static void checkInvalidTwo(String[] args) {

        if (args.length > 3) {
            System.out.println("Error: Excessive number of arguments. Expected 3");
            System.exit(-1);
        }

        if (args.length == 3) {
            for (int i = 0; i < args.length; i++) {
                if (!args[i].startsWith(input[i]) || !args[i].contains(input[i])) {
                    System.out.println(
                            "Error: Invalid argument input sequence. Expected: --numToki=<quantity>, --numFoki=<quantity>, --check");
                    System.exit(-1);
                }
            }
            return;
        }

        if (args.length == 2) {
            for (int i = 0; i < args.length; i++) {
                if (!(args[i].startsWith(input[i]) || args[i].startsWith(input[i + 1]))) {
                    System.out.println(
                            "Error: Invalid argument input sequence. Expected sequence: 1.--numToki=<quantity> | 2.--numFoki=<quantity> | 3.--check");
                    System.exit(-1);
                }
            }
            return;
        }

        boolean checker = false;
        if (args.length == 1) {
            for (int i = 0; i < input.length; i++) {
                if (args[0].startsWith(input[i])) {
                    checker = !checker;
                }
            }
            if (!checker) {
                System.out.println(
                        "Error: Invalid argument input sequence. Expected: 1.--numToki=<quantity> | 2.--numFoki=<quantity> | 3.--check");
                System.exit(-1);
            }
            return;
        }

    }

    private static void checkInvalidThree(String[] args) {

        if (args.length == 3) {
            for (int i = 0; i < input.length - 1; i++) {
                if (args[i].startsWith(input[i])) {
                    if (args[i].length() > input[0].length() + 2 || args[i].length() < input[0].length() + 1) {
                        System.out.println(
                                "Error: Invalid argument input sequence. Expected: 1.--numToki=<quantity> | 2.--numFoki=<quantity> | 3.--check\nQuantity should be maximum 95");
                        System.exit(-1);
                    }
                }
            }
            if (args[2].length() != input[2].length()) {
                System.out.println("Error: Invalid argument input. Expected: '--check' as a 3 argument");
                System.exit(-1);
            }
            return;
        }

        if (args.length == 2) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith(input[0]) || args[i].startsWith(input[1])) {
                    if (args[i].length() > input[0].length() + 2 || args[i].length() < input[0].length() + 1) {
                        System.out.println(
                                "Error: Invalid argument input sequence. Expected: 1.--numToki=<quantity> | 2.--numFoki=<quantity> | 3.--check\nQuantity should be maximum 95");
                        System.exit(-1);
                    }
                }
                if (args[i].startsWith(input[2])) {
                    if (args[i].length() != input[2].length()) {
                        System.out
                                .println("Error: Invalid argument input. Expected: '--check' as a " + i + " argument");
                        System.exit(-1);
                    }
                }
            }
            return;
        }

        if (args.length == 1) {
            for (int i = 0; i < input.length - 1; i++) {
                if (args[0].startsWith(input[i])) {
                    if (args[0].length() > input[i].length() + 2 || args[0].length() < input[i].length() + 1) {
                        System.out.println(
                                "Error: Invalid argument input sequence. Expected: 1.--numToki=<quantity> | 2.--numFoki=<quantity> | 3.--check\nQuantity should be maximum 95");
                        System.exit(-1);
                    }
                }
            }
            if (args[0].startsWith(input[2])) {
                if (args[0].length() != input[2].length()) {
                    System.out.println(
                            "Error: Invalid argument input sequence. Expected: 1.--numToki=<quantity> | 2.--numFoki=<quantity> | 3.--check\nQuantity should be maximum 95");
                    System.exit(-1);
                }
            }
            return;
        }

    }

}
