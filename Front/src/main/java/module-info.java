module com.example.displine {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.displine to javafx.fxml, com.google.gson;
    exports com.example.displine;
}