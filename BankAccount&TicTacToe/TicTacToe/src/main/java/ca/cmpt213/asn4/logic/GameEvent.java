package ca.cmpt213.asn4.logic;

import ca.cmpt213.asn4.ui.GameUI;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * GameEvent - class with all the logic of the game.
 */
public class GameEvent {

    private enum Turn { FIRST, SECOND }

    private static Turn turn;
    private static final int GAME_SIZE = 4;
    private static int[][] logicGrid = new int[GAME_SIZE][GAME_SIZE];

    static public void initializeMenuButton(Button startGame, Button exit, Stage stage, Stage outcomeWindow) {

        startGame.setOnAction(event -> {

            if (!stage.isShowing())
                stage.show();
            if (outcomeWindow != null)
                outcomeWindow.close();

            resetGame(stage);

        });

        exit.setOnAction(event -> {

            if (outcomeWindow != null)
                outcomeWindow.close();

            stage.close();

        });

    }

    static public void gameplay(Label playerTurn, ImageView[] icons, Button newGame, Stage stage) {

            for (int i = 0; i < icons.length; i++) {
                int index = i;
                icons[i].addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

                    if (logicGrid[index / 4][index % 4] != 0)
                        return;

                    if (turn == Turn.FIRST) {
                        icons[index].setImage(new Image(GameEvent.class.getResourceAsStream("/ca/cmpt213/as4/ui/X.png")));
                        turn = Turn.SECOND;
                        logicGrid[index / 4][index % 4] = 1;
                        playerTurn.setText("Player O");

                        if (GameEvent.isGameEnded()) {
                            GameUI.gameOutcome(1, stage);

                            resetGame(stage);

                            return;
                        }
                    } else {
                        icons[index].setImage(new Image(GameEvent.class.getResourceAsStream("/ca/cmpt213/as4/ui/O.png")));
                        turn = Turn.FIRST;
                        logicGrid[index / 4][index % 4] = 2;
                        playerTurn.setText("Player X");

                        if (GameEvent.isGameEnded()) {
                            GameUI.gameOutcome(2, stage);

                            resetGame(stage);

                            return;
                        }
                    }

                    if (GameEvent.isGameDraw()) {
                        GameUI.gameOutcome(3, stage);

                        resetGame(stage);
                    }

                });
            }

            newGame.setOnAction(actionEvent -> {

                resetGame(stage);

            });

    }

    static private void resetGame(Stage stage) {

        resetLogicGrid();

        turn = Turn.FIRST;

        GameUI.initializeGame(stage);

    }

    private static void resetLogicGrid() {

        for (int i = 0 ; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                logicGrid[i][j] = 0;
            }
        }

    }

    static private boolean isGameEnded() {

        if (logicGrid[0][0] != 0) {
            if (logicGrid[0][0] == logicGrid[0][1] && logicGrid[0][1] == logicGrid[0][2] && logicGrid[0][2] == logicGrid[0][3])
                return true;
            if (logicGrid[0][0] == logicGrid[1][1] && logicGrid[1][1] == logicGrid[2][2] && logicGrid[2][2] == logicGrid[3][3])
                return true;
            if (logicGrid[0][0] == logicGrid[1][0] && logicGrid[1][0] == logicGrid[2][0] && logicGrid[2][0] == logicGrid[3][0])
                return true;
        }
        if (logicGrid[3][3] != 0) {
            if (logicGrid[3][3] == logicGrid[3][2] && logicGrid[3][2] == logicGrid[3][1] && logicGrid[3][1] == logicGrid[3][0])
                return true;
            if (logicGrid[3][3] == logicGrid[2][3] && logicGrid[2][3] == logicGrid[1][3] && logicGrid[1][3] == logicGrid[0][3])
                return true;
        }
        if (logicGrid[0][3] != 0)
            if (logicGrid[0][3] == logicGrid[1][2] && logicGrid[1][2] == logicGrid[2][1] && logicGrid[2][1] == logicGrid[3][0])
                return true;
        if (logicGrid[2][1] != 0) {
            if (logicGrid[2][1] == logicGrid[1][1] && logicGrid[0][1] == logicGrid[1][1] && logicGrid[2][1] == logicGrid[3][1])
                return true;
            if (logicGrid[2][1] == logicGrid[2][0] && logicGrid[2][1] == logicGrid[2][2] && logicGrid[2][2] == logicGrid[2][3])
                return true;
        }
        if (logicGrid[1][2] != 0) {
            if (logicGrid[1][2] == logicGrid[0][2] && logicGrid[1][2] == logicGrid[2][2] && logicGrid[2][2] == logicGrid[3][2])
                return true;
            if (logicGrid[1][2] == logicGrid[1][3] && logicGrid[1][0] == logicGrid[1][1] && logicGrid[1][1] == logicGrid[1][2])
                return true;
        }

        return false;

    }

    static public boolean isGameDraw() {

        for (int i = 0; i < GAME_SIZE; i++)
            for (int j = 0; j < GAME_SIZE; j++)
                if (logicGrid[i][j] == 0)
                    return false;

        return true;

    }

}
