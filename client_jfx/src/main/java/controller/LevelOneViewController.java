package controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;


public class LevelOneViewController {

    @FXML
    public Group tankOne;


    @FXML
    public void moveUPClicked(){
           tankOne.setLayoutX(450.0);
           tankOne.setLayoutY(30.0);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Geklickt");

        alert.showAndWait();
    }
}