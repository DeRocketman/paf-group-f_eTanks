package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.ETankApplication;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

public class ProfilViewController extends ViewController {

    @FXML
    private TextField publicName;

    public void initialize(){
        publicName.setText("Test Name");
        publicName.setDisable(true);
    }

    public void editName(ActionEvent actionEvent) {
        publicName.setDisable(false);
    }

    public void saveProfil(ActionEvent actionEvent) {
    }
}
