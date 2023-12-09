package ca.cmpt213.ui;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * PostUI - class for UI scene doing creating a Superhuman
 */
public class PostUI {

    private static Text response = new Text("");

    public static void createScene(Stage stage) {

        BorderPane ui = new BorderPane();

        Text title = new Text("Creation of new Superhuman Information");
        Button back = new Button("Go Back (Main Page)");

        HBox titleBox = new HBox(title);

        VBox top = new VBox(back, titleBox);

        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));

        title.setStyle("-fx-font-size: 35px");

        ui.setTop(top);

        Label sName = new Label("Superhuman Name:");
        Label sWeight = new Label("Superhuman Weight:");
        Label sHeight = new Label("Superhuman Height:");
        Label sPicture = new Label("Superhuman Picture (URL):");
        Label sCategory = new Label("Superhuman Category:");
        Label sAbility = new Label("Superhuman Ability:");

        sName.setPadding(new Insets(5));
        sName.setStyle("-fx-background-color: black; -fx-background-radius: 5px; -fx-text-fill: #FF0000");
        sWeight.setPadding(new Insets(5));
        sWeight.setStyle("-fx-background-color: black; -fx-background-radius: 5px; -fx-text-fill: #FF0000");
        sHeight.setPadding(new Insets(5));
        sHeight.setStyle("-fx-background-color: black; -fx-background-radius: 5px; -fx-text-fill: #FF0000");
        sPicture.setPadding(new Insets(5));
        sPicture.setStyle("-fx-background-color: black; -fx-background-radius: 5px; -fx-text-fill: #FF0000");
        sCategory.setPadding(new Insets(5));
        sCategory.setStyle("-fx-background-color: black; -fx-background-radius: 5px; -fx-text-fill: #FF0000");
        sAbility.setPadding(new Insets(5));
        sAbility.setStyle("-fx-background-color: black; -fx-background-radius: 5px; -fx-text-fill: #FF0000");

        TextField sNameField = new TextField();
        TextField sWeightField = new TextField();
        TextField sHeightField = new TextField();
        TextField sPictureField = new TextField();
        TextField sCategoryField = new TextField();
        TextField sAbilityField = new TextField();

        Button addButton = new Button("Create Superhuman");

        GridPane grid = new GridPane();

        response.setFill(Color.GREEN);

        grid.add(sName, 0, 0);
        grid.add(sWeight, 0, 1);
        grid.add(sHeight, 0, 2);
        grid.add(sPicture, 0, 3);
        grid.add(sCategory, 0, 4);
        grid.add(sAbility, 0, 5);

        grid.add(sNameField, 1, 0);
        grid.add(sWeightField, 1, 1);
        grid.add(sHeightField, 1, 2);
        grid.add(sPictureField, 1, 3);
        grid.add(sCategoryField, 1, 4);
        grid.add(sAbilityField, 1, 5);

        grid.add(addButton, 0, 6);
        grid.add(response, 0, 7);

        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(3);

        ui.setCenter(grid);
        ui.setStyle("-fx-font-family: Verdana");
        ui.setPadding(new Insets(20));

        back.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        addButton.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");

        buttonBackEvent(back, stage);
        addButtonEvent(addButton, grid);

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

    private static void buttonBackEvent(Button back, Stage stage) {

        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                response.setText("");

                MainUI.mainScene(stage);

            }

        });

    }

    private static void addButtonEvent(Button addButton, GridPane textFields) {

        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                TextField sNameField = (TextField) textFields.getChildren().get(6);
                TextField sWeightField = (TextField) textFields.getChildren().get(7);
                TextField sHeightField = (TextField) textFields.getChildren().get(8);
                TextField sPictureField = (TextField) textFields.getChildren().get(9);
                TextField sCategoryField = (TextField) textFields.getChildren().get(10);
                TextField sAbilityField = (TextField) textFields.getChildren().get(11);

                try {
                    URL url = new URL("http://localhost:8080/api/superhuman/add");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");

                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());

                    writer.write("{\"name\":\"" + sNameField.getText()
                            + "\",\"weight\":" + sWeightField.getText()
                            + ",\"height\":" + sHeightField.getText()
                            + ",\"picture\":\"" + sPictureField.getText()
                            + "\",\"category\":\"" + sCategoryField.getText()
                            + "\",\"ability\":\"" + sAbilityField.getText() + "\"}");
                    writer.flush();
                    writer.close();
                    connection.connect();

                    int responseCode = connection.getResponseCode();

                    System.out.println(responseCode);

                    if (responseCode == 403) {
                        response.setFill(Color.RED);
                        response.setText("Creation failed.\nResponse status: 403 (Forbidden)");
                    } else if (responseCode == 400) {
                        response.setFill(Color.RED);
                        response.setText(
                                "Creation failed, weight and/or height field(s) cannot empty.\nResponse status: 400 (Bad Request)");
                    } else if (responseCode == 201) {
                        response.setFill(Color.GREEN);
                        response.setText("Creation successful!");
                    }

                    connection.disconnect();
                } catch (IOException e) {
                    response.setFill(Color.RED);
                    response.setText("URL Not Found (404)");
                }
            }

        });

    }

}
