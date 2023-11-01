package ca.cmpt213.as3;

import ca.cmpt213.as3.logic.ErrorHandler;
import ca.cmpt213.as3.ui.GridInterface;

/**
 * TokimonFinder - main class for game start.
 *
 */

public class TokimonFinder {

    public static void main(String[] args) {

        ErrorHandler.checkArgs(args);

        GridInterface.showGrid(null);

        ErrorHandler.checkGetSizes(args);

    }

}
