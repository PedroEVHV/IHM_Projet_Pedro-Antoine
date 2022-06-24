package application.view;

import application.Main;
import application.controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class View implements ViewInterface {

    Controller controller;

    private String currSearchString;

    public View() {
        this.controller = Main.controller;
    }
    
    PlaneteView planete = new PlaneteView();

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
        planete.loadEarth(view3D);

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
        pane3D.pressedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {

            }
        });
        loadJSONData("D:\\Java\\JavaFX projects\\IHM_Projet_Pedro-Antoine\\src\\main\\java\\application\\Delphinidae.json");

        //Date setup

    }

    public String getCurrSearchString() {
        return currSearchString;
    }

    public void loadJSONData(String file) {
        planete.occForGeoHashList = controller.loadDataFromJSON(file);
        planete.legend = controller.loadLegend();
        planete.displayOccOnEarth2D();


    }
}
