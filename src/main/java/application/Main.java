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
        System.out.println(getClass().getResource("src/main/resources/application/view.fxml"));
        System.out.println(getClass().getResource("ressources/application/view.fxml"));
        System.out.println(getClass().getResource("C:\\Users\\pedro\\OneDrive\\Documents\\Java\\IHM_Projet_Pedro-Antoine\\src\\main\\resources\\application\\view.fxml"));
        System.out.println(getClass().getResource("https://github.com/PedroEVHV/IHM_Projet_Pedro-Antoine/blob/0e3a2f26fba9f2818e7f1b1577d20b16854355eb/src/main/resources/application/view.fxml"));
        System.out.println(getClass().getResource("src/main/resources/application/view.fxml"));
        System.out.println(getClass().getResource("test.fxml"));
        System.out.println(getClass().getResource("jetbrains://idea/navigate/reference?project=IHM_Projet_Pedro-Antoine&path=src/main/resources/application/view.fxml"));
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("view.fxml"))));
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
