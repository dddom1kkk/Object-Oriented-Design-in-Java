module ca.cmpt213.ui {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens ca.cmpt213.ui to javafx.fxml, com.google.gson;

    exports ca.cmpt213.ui;
}
