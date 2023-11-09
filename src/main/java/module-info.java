module java {
    requires org.json;
    requires org.apache.commons.io;
    requires junit;

    opens unittest to org.junit.jupiter.api;
    exports unittest;
}