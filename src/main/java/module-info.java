module java {
    requires org.json;
    requires org.apache.commons.io;
    requires junit;
    requires org.slf4j;
    requires org.xerial.sqlitejdbc;
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.commons;

    opens unittest to org.junit.jupiter.api, org.slf4j.simple;
    opens model to org.slf4j.simple;
    opens view to javafx.fxml;

    exports model.datastructure;
    exports model.dictionary;
    exports model.user;
    exports model.util;
    exports model.word;

    exports unittest;
    exports controller;
    opens controller to javafx.fxml;
}