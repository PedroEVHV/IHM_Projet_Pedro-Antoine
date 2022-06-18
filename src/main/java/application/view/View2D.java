package application.view;

import application.controller.Controller2D;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class View2D implements View2DInterface{

    Controller2D mainController;

    public View2D(Controller2D c) {
        this.mainController = c;
    }

    public View2D() {

    }

    @FXML
    HBox rootBox;


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
        @FXML Pane pane3D;
        @FXML HBox lowerSection;
            //Lower Section
            @FXML VBox speciesView;
                //Species View;
                @FXML Label speciesNames;
            @FXML VBox info;
                //Info
                @FXML Label labelInfo;

}
