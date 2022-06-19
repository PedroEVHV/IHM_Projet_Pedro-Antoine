package application.view;

public interface ViewInterface {
    /**
     * Sets up the GUI by linking the FXML id's and loads the 3D view.
     */
    void initialize();

    /**
     * Method specific for loading the Earth model in a 3D view.
     */
    public void loadEarth();
}
