package application;

import application.controller.Controller2D;
import application.view.View2D;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view.fxml")));
        stage.setTitle("Ocean view");
        Scene scene = new Scene(root);

        Controller2D c2D = new Controller2D();
        View2D view2D = new View2D(c2D);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
