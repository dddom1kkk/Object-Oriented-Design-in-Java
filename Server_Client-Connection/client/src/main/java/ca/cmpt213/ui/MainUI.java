package ca.cmpt213.ui;

import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * MainUI - class for UI scene showing main menu for Superhuman information
 * manipulation
 */
public class MainUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        mainScene(stage);

    }

    public static void mainScene(Stage stage) {

        BorderPane ui = new BorderPane();

        Text title = new Text("Superhuman Database");

        HBox titleBox = new HBox(title);

        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));

        title.setStyle("-fx-font-size: 35px");

        Button buttonPost = new Button("Add New Superhuman");
        Button buttonGetAll = new Button("Show Superhuman Database");
        Button buttonGet = new Button("Find a Superhuman");
        Button buttonDelete = new Button("Delete Superhuman");

        buttonPost.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        buttonGetAll.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        buttonGet.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        buttonDelete.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");

        HBox buttonPostBox = new HBox(buttonPost);
        HBox buttonGetAllBox = new HBox(buttonGetAll);
        HBox buttonGetBox = new HBox(buttonGet);
        HBox buttonDeleteBox = new HBox(buttonDelete);

        buttonPostBox.setAlignment(Pos.CENTER);
        buttonGetBox.setAlignment(Pos.CENTER);
        buttonGetAllBox.setAlignment(Pos.CENTER);
        buttonDeleteBox.setAlignment(Pos.CENTER);

        VBox mainScene = new VBox(buttonGetAllBox, buttonGetBox, buttonPostBox, buttonDeleteBox);

        ui.setTop(titleBox);
        ui.setCenter(mainScene);
        ui.setStyle("-fx-font-family: Verdana;");
        ui.setPadding(new Insets(20));

        mainScene.setSpacing(20);
        mainScene.setAlignment(Pos.CENTER);

        buttonPostEvent(buttonPost, stage);
        buttonGetAllEvent(buttonGetAll, stage);
        buttonGetEvent(buttonGet, stage);
        buttonDeleteEvent(buttonDelete, stage);

        Image image = null;
        try {
            image = new Image(MainUI.class.getResource("backgrounds/secret.jpg").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));

        Background background = new Background(backgroundImage);

        ui.setBackground(background);

        Scene scene = new Scene(ui, 1000, 700);

        stage.setScene(scene);

        stage.show();

    }

    private static void buttonPostEvent(Button buttonPost, Stage stage) {

        buttonPost.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                PostUI.createScene(stage);

            }

        });

    }

    private static void buttonGetAllEvent(Button buttonGetAll, Stage stage) {

        buttonGetAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                GetAllUI.showScene(stage);
            }

        });

    }

    private static void buttonGetEvent(Button buttonGet, Stage stage) {

        buttonGet.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                GetUI.findScene(stage);
            }

        });

    }

    private static void buttonDeleteEvent(Button buttonDelete, Stage stage) {

        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                DeleteUI.deleteScene(stage);
            }

        });

    }

}
