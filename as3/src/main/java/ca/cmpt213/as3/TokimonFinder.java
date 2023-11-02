package ca.cmpt213.as3;

import java.util.Scanner;

import ca.cmpt213.as3.logic.ErrorHandler;
import ca.cmpt213.as3.logic.TokimonGrid;
import ca.cmpt213.as3.logic.UserMoveInput;
import ca.cmpt213.as3.ui.GridInterface;

/**
 * TokimonFinder - main class for game start.
 *
 */

public class TokimonFinder {

    public static void main(String[] args) {

        ErrorHandler.checkArgs(args);

        ErrorHandler.checkGetSizes(args);

        GridInterface.showGrid(UserMoveInput.inputInitialLocation());

    }

}
