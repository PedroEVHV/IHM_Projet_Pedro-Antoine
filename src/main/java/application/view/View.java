//Test

package application.view;

import application.controller.Controller;
import application.util.CameraManager;
import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;

public class View implements ViewInterface {

    Controller mainController;

    private String currSearchString;

    private final Group geometry = new Group();
    private final Group root3D = new Group();

    public View(Controller c) {
        this.mainController = c;
    }

    public View() {

    }

    public @FXML HBox rootBox;


    @FXML
    VBox leftSection;
        //Left area
        @FXML VBox speciesSection;
            //Species
            @FXML Label speciesSLabel;
            @FXML TextField speciesSelectText;
            @FXML ListView<String> speciesList;
        @FXML VBox dateSection;
            //date
            @FXML Label dateSLabel;
            @FXML HBox datesBox;
                //Date pickers
                @FXML VBox startDateBox;
                    //Start date
                    @FXML Label startDateLabel;
                    @FXML DatePicker startDatePicker;
                @FXML VBox endDateBox;
                    //End date
                    @FXML Label endDateLabel;
                    @FXML DatePicker endDatePicker;

            @FXML Label intervalLabel;
            @FXML Spinner<Integer> intervalSet;

        @FXML VBox geoSection;
            //geoHash
            @FXML Label geoLabel;

        @FXML VBox positionSection;
            //position
            @FXML Label posLabel;
            @FXML HBox longBox;
                //longitude
                @FXML Label longLabel;
                @FXML Spinner<Float> longSpinner;
            @FXML HBox latBox;
                //latitude
                @FXML Label latLabel;
                @FXML Spinner<Float> latSpinner;

    @FXML VBox rightSection;
        //Right Area
        @FXML
        FlowPane pane3D;
            @FXML Pane view3D;
        @FXML HBox lowerSection;
            //Lower Section
            @FXML VBox speciesView;
                //Species View;
                @FXML Label speciesNames;
            @FXML VBox info;
                //Info
                @FXML Label labelInfo;

    public void initialize() {
        //Load CSS properties
        //background #BDD7EE
        //searchbar #8FAADC
        //Text #505F7B
        //Outlines #203864
        //cadre #A5A5A5
        //widget bg #B4C7E7

        //load earth
        loadEarth();

        //Setup species Section
        speciesSelectText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currSearchString = speciesSelectText.getText();
            }
        });
        speciesList.itemsProperty().addListener(new ChangeListener<ObservableList<String>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<String>> observableValue, ObservableList<String> strings, ObservableList<String> t1) {

            }
        });

        //Date setup

        //
    }

    public void loadEarth() {
        // Load geometry
        ObjModelImporter objImporter = new ObjModelImporter();
        try {
            //NOT WORKING ---> URL modelURL = this.getClass().getResource("C:\\Users\\pedro\\OneDrive\\Documents\\Java\\IHMprojet\\src\\main\\resources\\data\\Earth\\Earth\\earth.obj");
            objImporter.read("src/main/java/application/data/earth.obj");
        } catch (ImportException e) {
            System.out.println(e.getMessage());
        }
        MeshView[] meshViews = objImporter.getImport();
        Group earth = new Group(meshViews);
        geometry.getChildren().add(earth);

        root3D.getChildren().add(geometry);

        // Add a camera group
        PerspectiveCamera camera = new PerspectiveCamera(true);
        new CameraManager(camera, view3D, root3D);

        // Add point light
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(-180);
        light.setTranslateY(-90);
        light.setTranslateZ(-120);
        light.getScope().addAll(root3D);
        root3D.getChildren().add(light);

        // Add ambient light
        AmbientLight ambientLight = new AmbientLight(Color.WHITE);
        ambientLight.getScope().addAll(root3D);
        root3D.getChildren().add(ambientLight);

        // Create scene
        SubScene subscene = new SubScene(root3D, 750, 560
                , true, SceneAntialiasing.BALANCED);
        subscene.setCamera(camera);
        subscene.setFill(Color.BLACK);
        view3D.getChildren().addAll(subscene);

        }

    public String getCurrSearchString() {
        return currSearchString;
    }
}
