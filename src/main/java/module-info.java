module org.example.dungeonexplorerfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires javafx.media;


    opens org.example.dungeonexplorerfx to javafx.fxml;
    exports org.example.dungeonexplorerfx;
    exports org.example.dungeonexplorerfx.controllers;
    opens org.example.dungeonexplorerfx.controllers to javafx.fxml;
}