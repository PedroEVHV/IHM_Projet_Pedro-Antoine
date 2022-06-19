package application;

import application.controller.Controller2D;
import application.view.View;
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
        View view2D = new View(c2D);
        stage.setScene(scene);


        //view2D.rootBox.getStylesheets().add(String.valueOf(this.getClass().getResource("interface.css")));


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
