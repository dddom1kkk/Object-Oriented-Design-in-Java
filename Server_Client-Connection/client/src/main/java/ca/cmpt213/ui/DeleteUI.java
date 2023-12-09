package ca.cmpt213.ui;

import java.io.IOException;
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
 * DeleteUI - class for UI scene doing deletion of Superhuman
 */
public class DeleteUI {

    private static Text response = new Text("");

    public static void deleteScene(Stage stage) {

        BorderPane ui = new BorderPane();

        Text title = new Text("Deletion of new Superhuman Information");
        Button back = new Button("Go Back (Main Page)");
        Button delete = new Button("Delete");

        back.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        delete.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");

        HBox titleBox = new HBox(title);

        VBox top = new VBox(back, titleBox);

        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));

        title.setStyle("-fx-font-size: 35px");

        ui.setTop(top);

        Label sId = new Label("Enter ID to find Superhuman: ");

        TextField sIdField = new TextField();

        GridPane grid = new GridPane();

        response.setFill(Color.RED);
        HBox resBox = new HBox(response);
        resBox.setAlignment(Pos.CENTER);
        resBox.setPadding(new Insets(5));

        VBox box = new VBox(grid, resBox);

        grid.add(sId, 0, 0);
        grid.add(sIdField, 1, 0);
        grid.add(delete, 1, 1);

        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);

        ui.setCenter(box);
        ui.setStyle("-fx-font-family: Verdana");
        ui.setPadding(new Insets(20));

        buttonBackEvent(back, stage);
        buttonDeleteEvent(delete, stage, sIdField);

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

    private static void buttonDeleteEvent(Button delete, Stage stage, TextField sIdField) {

        delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                URL url;

                try {
                    int sId = Integer.parseInt(sIdField.getText());

                    url = new URL("http://localhost:8080/api/superhuman/" + sId);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("DELETE");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestMethod("DELETE");
                    connection.connect();

                    int responseCode = connection.getResponseCode();

                    System.out.println(responseCode);

                    if (responseCode == 204) {
                        response.setFill(Color.GREEN);
                        response.setText("Superhuman " + sId + " is deleted from database successfully!");
                    } else if (responseCode == 404) {
                        response.setFill(Color.RED);
                        response.setText("Superhuman Not Found (404)");
                    }

                    connection.disconnect();
                } catch (IOException e) {
                    response.setText("URL/Superhuman Not Found (404)");
                } catch (NumberFormatException e) {
                    response.setText("Put a valid ID");
                }

            }

        });

    }

}
