module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires jimObjModelImporterJFX;


    opens application to javafx.fxml;
    opens application.controller to javafx.fxml;
    opens application.model to javafx.fxml;
    opens application.view to javafx.fxml;

    exports application;
    exports application.controller;
    exports application.view;
    exports application.model;
}