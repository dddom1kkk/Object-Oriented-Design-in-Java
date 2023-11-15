package ca.cmpt213.asn4.ui;

import ca.cmpt213.asn4.logic.GameEvent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameUI extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("| 4x4 TicTacToe |");

        initializeApplication(stage);

        stage.show();

    }

    static private void initializeApplication(Stage stage) {

        Text gameName = new Text("| 4x4 TicTacToe |");
        Button startGame = new Button("Start The Game");
        Button exit = new Button("Exit");

        gameName.resize(20, 20);
        startGame.setPrefSize(150, 20);
        exit.setPrefSize(150, 20);

        gameName.setFont(Font.font(15));
        startGame.setFont(Font.font(15));
        exit.setFont(Font.font(15));

        GameEvent.initializeMenuButton(startGame, exit, stage, null);

        VBox menu = new VBox(gameName, startGame, exit);
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(100));
        menu.setSpacing(20);

        Scene scene = new Scene(menu);

        stage.setScene(scene);

    }

    static public void initializeGame(Stage stage) {

        BorderPane gameUI = new BorderPane();
        Label playerTurn = new Label("Player X");
        GridPane gameGrid = new GridPane();
        Button newGame = new Button("Start New Game");
        Image unknownIcon = new Image(GameUI.class.getResourceAsStream("/ca/cmpt213/as4/ui/closed.jpg"));
        ImageView[] uIcons = new ImageView[16];

        for (int i = 0; i < uIcons.length; i++) {

                uIcons[i] = new ImageView(unknownIcon);
                uIcons[i].setFitHeight(100);
                uIcons[i].setPreserveRatio(true);

                gameGrid.add(uIcons[i], i % 4, i / 4);
        }

        playerTurn.setFont(Font.font(15));
        playerTurn.setMaxWidth(Double.MAX_VALUE);
        playerTurn.setAlignment(Pos.BASELINE_CENTER);
        HBox box = new HBox(playerTurn);
        box.setPadding(new Insets(10));

        gameGrid.setVgap(10);
        gameGrid.setHgap(10);
        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setPadding(new Insets(10));

        newGame.setPrefSize(150, 20);
        newGame.setFont(Font.font(15));
        box = new HBox(newGame);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));

        gameUI.setTop(playerTurn);
        gameUI.setCenter(gameGrid);
        gameUI.setBottom(box);
        gameUI.setPadding(new Insets(20));

        GameEvent.gameplay(playerTurn, uIcons, newGame, stage);

        Scene scene = new Scene(gameUI);

        stage.setScene(scene);

    }

    static public void gameOutcome(int outcome, Stage stage) {

        Stage outcomeWindow = new Stage();

        Label outcomeText = new Label();

        if (outcome == 1)
            outcomeText.setText("Player X won! Congratulations!!!\nPlayer O don't worry and try to win next time :)");
        else if (outcome == 2)
            outcomeText.setText("Player O won! Congratulations!!!\nPlayer X don't worry and try to win next time :)");
        else
            outcomeText.setText("It's a DRAW!!! You, players, are smart :o");

        outcomeText.setAlignment(Pos.BASELINE_CENTER);
        outcomeText.setFont(Font.font(15));

        Button startGame = new Button("Start The Game");
        Button exit = new Button("Exit");

        startGame.setPrefSize(150, 20);
        exit.setPrefSize(150, 20);

        startGame.setFont(Font.font(15));
        exit.setFont(Font.font(15));

        GameEvent.initializeMenuButton(startGame, exit, stage, outcomeWindow);

        VBox menu = new VBox(outcomeText, startGame, exit);
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(100));
        menu.setSpacing(20);

        Scene scene = new Scene(menu);

        stage.hide();

        outcomeWindow.setScene(scene);
        outcomeWindow.showAndWait();

    }

}
