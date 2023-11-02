package ca.cmpt213.as3.logic;

import java.util.Scanner;

public class UserMoveInput {

    public static TokimonGrid inputInitialLocation() {

        Scanner inScan = new Scanner(System.in);
        String in;
        int colNum;

        while (true) {
            System.out.print("Enter initial position on the grid: ");
            in = inScan.next();

            colNum = ErrorHandler.checkGetInitPosition(in);

            if (colNum != -1)
                break;
        }
        inScan.close();

        return new TokimonGrid(in.substring(0, 1), colNum);

    }

    public static TokimonGrid moveInGrid(TokimonGrid initial) {

        Scanner inScan = new Scanner(System.in);
        String in;
        int check;

        while (true) {

            System.out.print("Move (W, A, S, D): ");
            in = inScan.next();

            if (ErrorHandler.checkValidMove(initial, in))
                break;

        }
        inScan.close();

        String move = "";

        if (in.charAt(0) == 'W') {
            move += initial.getLetter().charAt(0) - 1;
            return new TokimonGrid(move, initial.getNumber());
        }

    }

}
