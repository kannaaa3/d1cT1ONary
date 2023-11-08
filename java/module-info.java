module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.commons;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}