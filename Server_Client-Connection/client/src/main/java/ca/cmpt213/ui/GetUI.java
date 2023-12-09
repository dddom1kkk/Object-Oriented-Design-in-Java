package ca.cmpt213.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * GetUI - class for UI scene fetching specified Superhuman by ID
 */
public class GetUI {

    private static CheckBox idC = new CheckBox("Show ID");
    private static CheckBox nameC = new CheckBox("Show name");
    private static CheckBox weightC = new CheckBox("Show weight");
    private static CheckBox heightC = new CheckBox("Show height");
    private static CheckBox pictureC = new CheckBox("Show picture");
    private static CheckBox categoryC = new CheckBox("Show category");
    private static CheckBox abilityC = new CheckBox("Show ability");

    private static Text response = new Text("");

    public static void findScene(Stage stage) {

        BorderPane ui = new BorderPane();

        ScrollPane scroll = new ScrollPane(ui);

        Text title = new Text("Find Superhuman");
        Button back = new Button("Go Back (Main Page)");
        Button find = new Button("Find");

        back.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        find.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");

        title.setStyle("-fx-font-size: 35px");

        Label sId = new Label("Enter ID to find Superhuman: ");

        TextField sIdField = new TextField();

        GridPane grid = new GridPane();

        response.setFill(Color.RED);

        grid.add(sId, 0, 0);
        grid.add(sIdField, 1, 0);
        grid.add(find, 1, 1);

        HBox titleBox = new HBox(title);
        HBox resBox = new HBox(response);
        HBox checks = new HBox(idC, nameC, weightC, heightC, pictureC, categoryC, abilityC);

        for (Node check : checks.getChildren()) {

            ((CheckBox) check).setSelected(false);

            ((CheckBox) check).setPadding(new Insets(5));
            ((CheckBox) check)
                    .setStyle("-fx-background-color: black; -fx-background-radius: 5px; -fx-text-fill: #FF0000");

        }

        titleBox.setAlignment(Pos.CENTER);
        resBox.setAlignment(Pos.CENTER);

        VBox top = new VBox(back, titleBox, grid, checks, resBox);

        ui.setTop(top);

        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(5);
        grid.setPadding(new Insets(30));
        checks.setAlignment(Pos.CENTER);
        checks.setPadding(new Insets(20));
        checks.setSpacing(10);

        buttonBackEvent(back, stage);
        buttonFindEvent(find, grid, ui, sIdField);
        checkboxActions(sIdField, grid, ui);

        ui.setStyle("-fx-font-family: Verdana");
        scroll.setPadding(new Insets(20));
        scroll.setFitToWidth(true);

        Scene scene = new Scene(scroll, 1000, 700);

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

    private static void findSuperhuman(String sId, GridPane grid, BorderPane ui) {

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        URL url;

        try {
            Integer.parseInt(sId);

            url = new URL("http://localhost:8080/api/superhuman/" + sId);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.getInputStream();

            int responseCode = connection.getResponseCode();

            System.out.println(responseCode);

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                String json = "";

                while (line != null) {

                    json += line;
                    line = reader.readLine();

                }

                reader.close();

                System.out.println(json);

                Gson gson = new Gson();

                Superhuman superhuman = gson.fromJson(json, Superhuman.class);

                VBox sHuman = new VBox();

                sHuman.setSpacing(10);
                sHuman.setPadding(new Insets(5));
                sHuman.setMinWidth(150);

                ImageView imgView = new ImageView();

                int borderCount = 0;

                if (pictureC.isSelected()) {
                    borderCount++;

                    imgView.setFitWidth(150);
                    imgView.setFitHeight(150);
                    imgView.setPreserveRatio(true);

                    try {
                        imgView.setImage(new Image(superhuman.getPicture()));
                    } catch (IllegalArgumentException e) {
                        imgView.setImage(new Image(
                                "https://images.rawpixel.com/image_1300/czNmcy1wcml2YXRlL3Jhd3BpeGVsX2ltYWdlcy93ZWJzaXRlX2NvbnRlbnQvbHIvam9iNDAyLWhlaW4tc2lsdmVyLTA1Ny5qcGc.jpg"));
                    }

                    sHuman.getChildren().add(imgView);
                }

                Text text;

                if (idC.isSelected()) {
                    borderCount++;

                    text = new Text("ID: " + Long.toString(superhuman.getSid()));
                    text.setWrappingWidth(150);
                    sHuman.getChildren().add(text);
                }

                if (nameC.isSelected()) {
                    borderCount++;

                    text = new Text("Name: " + superhuman.getName());
                    text.setWrappingWidth(150);
                    sHuman.getChildren().add(text);
                }

                if (weightC.isSelected()) {
                    borderCount++;

                    text = new Text("Weight: " + Double.toString(superhuman.getWeight()));
                    text.setWrappingWidth(150);
                    sHuman.getChildren().add(text);
                }

                if (heightC.isSelected()) {
                    borderCount++;

                    text = new Text("Height: " + Integer.toString(superhuman.getHeight()));
                    text.setWrappingWidth(150);
                    sHuman.getChildren().add(text);
                }

                if (categoryC.isSelected()) {
                    borderCount++;

                    text = new Text("Category: " + superhuman.getCategory());
                    text.setWrappingWidth(150);
                    sHuman.getChildren().add(text);
                }

                if (abilityC.isSelected()) {
                    borderCount++;

                    text = new Text("Ability: " + superhuman.getAbility());
                    text.setWrappingWidth(150);
                    sHuman.getChildren().add(text);
                }

                grid.add(sHuman, 0, 0);

                if (borderCount > 0) {
                    sHuman.setStyle("-fx-border-color: #750E21; -fx-border-width: 2; -fx-border-style: solid;");
                }

                response.setText("");

            } else if (responseCode == 404) {
                response.setText("Superhuman Not Found (404)");
            }

            connection.disconnect();

        } catch (IOException e) {
            response.setText("URL/Superhuman Not Found (404)");
        } catch (NumberFormatException e) {
            response.setText("Put a valid ID");
        }

        ui.setCenter(grid);

    }

    private static void buttonFindEvent(Button find, GridPane grid, BorderPane ui, TextField sIdField) {

        find.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                findSuperhuman(sIdField.getText(), grid, ui);

            }

        });

    }

    private static void checkboxActions(TextField sIdField, GridPane grid, BorderPane ui) {

        idC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                findSuperhuman(sIdField.getText(), grid, ui);

            }

        });

        nameC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                findSuperhuman(sIdField.getText(), grid, ui);

            }

        });

        abilityC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                findSuperhuman(sIdField.getText(), grid, ui);

            }

        });

        categoryC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                findSuperhuman(sIdField.getText(), grid, ui);

            }

        });

        heightC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                findSuperhuman(sIdField.getText(), grid, ui);

            }

        });

        weightC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                findSuperhuman(sIdField.getText(), grid, ui);

            }

        });

        pictureC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                findSuperhuman(sIdField.getText(), grid, ui);

            }

        });

    }

}
