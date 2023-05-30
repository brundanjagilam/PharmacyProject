module com.example.barthing {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;

    opens com.example.barthing to javafx.fxml;
    exports com.example.barthing;
}