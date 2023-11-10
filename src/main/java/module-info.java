module java {
    requires org.json;
    requires org.apache.commons.io;
    requires junit;
    requires org.slf4j;
    requires org.xerial.sqlitejdbc;

    opens unittest to org.junit.jupiter.api, org.slf4j.simple;
    opens model to org.slf4j.simple;

    exports model;
    exports unittest;
}