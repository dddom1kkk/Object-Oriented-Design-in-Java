package ca.cmpt213.as3.logic;

public class ErrorHandler {

    private static String[] input = { "--numToki=", "--numFoki=", "--cheat" };

    public static void checkArgs(String[] args) {
        if (args.length > 0) {
            checkInvalidOne(args);
            checkInvalidTwo(args);
            checkInvalidThree(args);
        }
    }

    public static Integer[] checkGetSizes(String[] args) {
        Integer[] sizes = new Integer[3]; // Details: index 0 = size of Tokimons, index 1 = size of Fokimons, index 2 =
                                          // = cheat is on or off
        int total = 0;

        if (args.length == 0) {
            sizes[0] = 10;
            sizes[1] = 5;
            sizes[2] = 0;
            return sizes;
        }

        if (args.length == 3) {

            sizes[2] = 1;
            fillBothKnown(sizes, args);
            return sizes;

        }

        else if (args.length == 2) {

            int cheatOn = 0;

            for (String option : args) {
                if (option.equalsIgnoreCase(input[2]))
                    cheatOn = 1;
            }

            sizes[2] = cheatOn;

            if (cheatOn == 0) {

                fillBothKnown(sizes, args);

                return sizes;

            }

        }

        for (int i = 0; i < sizes.length; i++) {
            System.out.println(sizes[i]);
        }

        return sizes;

    }

    private static void fillBothKnown(Integer[] sizes, String[] args) {

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
