package application;

import application.controller.Controller;
import application.model.Model;
import application.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {


    public static Controller controller;

    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        controller = new Controller(model);

        System.out.println(getClass().getResource("https://github.com/PedroEVHV/IHM_Projet_Pedro-Antoine/blob/8ca3b79f6e2a910fbd27c0bfcbaaa68933f0deac/src/main/java/application/view.fxml"));
        System.out.println(getClass().getResource("src/main/java/application/view.fxml"));
        System.out.println(getClass().getResource("application/view.fxml"));
        System.out.println(getClass().getResource("D:\\Java\\JavaFX projects\\IHM_Projet_Pedro-Antoine\\src\\main\\java\\application\\view.fxml"));
        System.out.println(getClass().getResource("D:\\Java\\JavaFX projects\\IHM_Projet_Pedro-Antoine\\src\\main\\java\\application\\interface.css"));
        System.out.println(getClass().getResource("https://github.com/PedroEVHV/IHM_Projet_Pedro-Antoine/blob/8ca3b79f6e2a910fbd27c0bfcbaaa68933f0deac/src/main/java/application/interface.css"));
        System.out.println(getClass().getResource("src/main/java/application/interface.css"));
        System.out.println(getClass().getResource("interface.css"));
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("view.fxml"))));

        stage.setTitle("OBIS 3D");
        Scene scene = new Scene(root);



        stage.setScene(scene);


        //view2D.rootBox.getStylesheets().add(String.valueOf(this.getClass().getResource("interface.css")));




        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
