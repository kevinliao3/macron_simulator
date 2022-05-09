module com.example.macron_simulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.media;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.almasb.fxgl.all;

    opens com.example.macron_simulator to javafx.fxml;
    exports com.example.macron_simulator;
}