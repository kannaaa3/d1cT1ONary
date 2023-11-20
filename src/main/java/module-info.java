module java {
    // API dependencies
    requires org.json;
    requires org.apache.commons.io;
    requires freetts;

    // Unit test dependencies
    requires junit;
    requires javafx.fxml;

    requires org.slf4j;

    // Local database dependencies
    requires org.xerial.sqlitejdbc;

    // AWS dependencies
    requires aws.crt;
    requires aws.iot.device.sdk;

    opens unittest to org.junit.jupiter.api, org.slf4j.simple;
    exports unittest;
}