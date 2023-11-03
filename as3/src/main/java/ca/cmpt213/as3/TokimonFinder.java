package ca.cmpt213.as3;

import ca.cmpt213.as3.ui.GridInterface;
import ca.cmpt213.as3.ui.UserMoveInput;
import ca.cmpt213.as3.logic.ErrorHandler;
import ca.cmpt213.as3.logic.TokimonGridState;

/**
 * TokimonFinder - main class for game start.
 *
 */

public class TokimonFinder {

    public static void main(String[] args) {

        TokimonGridState createTokiGridClass = new TokimonGridState();
        ErrorHandler createErrorClass = new ErrorHandler();
        UserMoveInput createCompileClass = new UserMoveInput();
        GridInterface.startGame(args);

    }

}
