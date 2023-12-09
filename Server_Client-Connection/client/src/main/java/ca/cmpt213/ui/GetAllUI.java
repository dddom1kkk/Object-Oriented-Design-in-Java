package ca.cmpt213.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
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
 * GetAllUI - class for UI scene fetching all Superhumans
 */
public class GetAllUI {

    private static CheckBox idC = new CheckBox("Show ID");
    private static CheckBox nameC = new CheckBox("Show name");
    private static CheckBox weightC = new CheckBox("Show weight");
    private static CheckBox heightC = new CheckBox("Show height");
    private static CheckBox pictureC = new CheckBox("Show picture");
    private static CheckBox categoryC = new CheckBox("Show category");
    private static CheckBox abilityC = new CheckBox("Show ability");

    private static Text response = new Text("");

    public static void showScene(Stage stage) {

        BorderPane ui = new BorderPane();

        ScrollPane scroll = new ScrollPane(ui);
        scroll.setFitToWidth(true);

        Text title = new Text("Database of Superhumans");
        Button back = new Button("Go Back (Main Page)");

        back.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");

        HBox titleBox = new HBox(title);
        HBox resBox = new HBox(response);
        HBox checks = new HBox(idC, nameC, weightC, heightC, pictureC, categoryC, abilityC);

        VBox top = new VBox(back, titleBox, checks, resBox);

        response.setFill(Color.RED);
        resBox.setAlignment(Pos.CENTER);

        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));
        checks.setAlignment(Pos.CENTER);
        checks.setPadding(new Insets(20));
        checks.setSpacing(10);
        back.setPadding(new Insets(5));

        for (Node check : checks.getChildren()) {

            ((CheckBox) check).setSelected(false);

            ((CheckBox) check).setPadding(new Insets(5));
            ((CheckBox) check)
                    .setStyle("-fx-background-color: black; -fx-background-radius: 5px; -fx-text-fill: #FF0000");

        }

        title.setStyle("-fx-font-size: 35px");

        ui.setTop(top);

        GridPane superhumans = new GridPane();
        showSuperhumans(superhumans, ui);
        checkboxActions(superhumans, ui);

        ui.setCenter(superhumans);
        ui.setStyle("-fx-font-family: Verdana");
        ui.setPadding(new Insets(20));

        buttonBackEvent(back, stage);

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

    private static void showSuperhumans(GridPane superhumans, BorderPane ui) {

        superhumans = new GridPane();
        superhumans.setAlignment(Pos.CENTER);
        superhumans.setVgap(10);
        superhumans.setHgap(10);

        URL url;
        try {
            url = new URL("http://localhost:8080/api/superhuman/all");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.getInputStream();

            int responseCode = connection.getResponseCode();

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

                Superhuman[] sHumans = gson.fromJson(json, Superhuman[].class);

                ArrayList<Superhuman> sHList = new ArrayList<>();

                for (Superhuman s : sHumans) {

                    sHList.add(s);

                }

                int size = sHList.size();

                int row = size / 4;
                int col = size % 4;
                int rIndex = 0, cIndex = 0;

                int borderCount = 0;

                for (Superhuman superhuman : sHList) {

                    VBox sHuman = new VBox();

                    sHuman.setSpacing(10);
                    sHuman.setPadding(new Insets(5));
                    sHuman.setMinWidth(150);

                    ImageView imgView = new ImageView();

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

                    superhumans.add(sHuman, col - (col - cIndex), row - (row - rIndex));

                    if (cIndex == 3) {
                        rIndex++;
                        cIndex = -1;
                    }

                    cIndex++;

                    if (borderCount > 0) {
                        sHuman.setStyle("-fx-border-color: #750E21; -fx-border-width: 2; -fx-border-style: solid;");
                    }

                }
            }
            System.out.println(responseCode);
            connection.disconnect();
        } catch (IOException e) {
            response.setText("URL not found (404)");
        }

        ui.setCenter(superhumans);

    }

    private static void checkboxActions(GridPane superhumans, BorderPane ui) {

        idC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showSuperhumans(superhumans, ui);

            }

        });

        nameC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showSuperhumans(superhumans, ui);

            }

        });

        abilityC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showSuperhumans(superhumans, ui);

            }

        });

        categoryC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showSuperhumans(superhumans, ui);

            }

        });

        heightC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showSuperhumans(superhumans, ui);

            }

        });

        weightC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showSuperhumans(superhumans, ui);

            }

        });

        pictureC.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                showSuperhumans(superhumans, ui);

            }

        });

    }

}
