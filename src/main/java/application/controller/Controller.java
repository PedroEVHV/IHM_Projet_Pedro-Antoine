package application.controller;

import application.model.Model;

import java.util.HashMap;

public class Controller implements ControllerInterface {
    private Model model;

    public Controller(Model model)
    {
        this.model = model;
    }

    public HashMap<String, Integer> loadDataFromJSON(String file) {
        return model.coordFromJSONObject(model.fileToJSONObject(file));
    }

    public int[] loadLegend() {
        return model.legendCouleur();
    }
}
