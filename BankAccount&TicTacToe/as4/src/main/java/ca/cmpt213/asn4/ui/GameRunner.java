package ca.cmpt213.asn4.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameRunner extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("| 4x4 TicTacToe |");

        setShowStage(stage);

        stage.show();

    }

    private void setShowStage(Stage stage) {

        VBox design = new VBox();
        GridPane gameGrid = new GridPane();
        Text sample1 = new Text("X");
        Text sample2 = new Text("O");
        Text sample3 = new Text("X");
        Text sample4 = new Text("O");
        Text sample5 = new Text("O");
        Text sample6 = new Text("X");
        Text sample7 = new Text("X");
        Text sample8 = new Text("O");
        Text sample9 = new Text("X");
        Text sample10 = new Text("O");
        Text sample11 = new Text("X");
        Text sample12 = new Text("O");
        Text sample13 = new Text("X");
        Text sample14 = new Text("O");
        Text sample15 = new Text("O");
        Text sample16 = new Text("X");

//        gameGrid.setMinSize(400, 400);

        gameGrid.setVgap(1);
        gameGrid.setHgap(1);

        gameGrid.getColumnConstraints().add(new ColumnConstraints(14.5));
        gameGrid.getColumnConstraints().add(new ColumnConstraints(14.5));
        gameGrid.getColumnConstraints().add(new ColumnConstraints(14.5));

        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setPadding(new Insets(10, 10, 10, 10));

        gameGrid.add(sample1, 0, 0);
        gameGrid.add(sample2, 1, 0);
        gameGrid.add(sample3, 2, 0);
        gameGrid.add(sample4, 3, 0);
        gameGrid.add(sample5, 0, 1);
        gameGrid.add(sample6, 1, 1);
        gameGrid.add(sample7, 2, 1);
        gameGrid.add(sample8, 3, 1);
        gameGrid.add(sample9, 0, 2);
        gameGrid.add(sample10, 1, 2);
        gameGrid.add(sample11, 2, 2);
        gameGrid.add(sample12, 3, 2);
        gameGrid.add(sample13, 0, 3);
        gameGrid.add(sample14, 1, 3);
        gameGrid.add(sample15, 2, 3);
        gameGrid.add(sample16, 3, 3);

        design.setSpacing(400);
        design.getChildren().addAll(new Label("TicTacToe Started..."), gameGrid);

        stage.setScene(new Scene(design, 500, 300));
        stage.setFullScreen(true);

    }

}
