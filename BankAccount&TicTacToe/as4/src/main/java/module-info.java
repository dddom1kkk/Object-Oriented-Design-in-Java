module ca.cmpt213.as4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.cmpt213.asn4 to javafx.fxml;
    exports ca.cmpt213.asn4;
}