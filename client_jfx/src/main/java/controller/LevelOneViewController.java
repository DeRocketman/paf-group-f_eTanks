package controller;

import javafx.fxml.Initializable;
import main.ETankApplication;

import java.net.URL;
import java.util.ResourceBundle;

//TODO refactorn!
public class LevelOneViewController implements Initializable {

    ETankApplication eTankApplication;

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }
}