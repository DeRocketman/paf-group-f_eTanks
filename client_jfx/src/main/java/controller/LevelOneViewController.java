package controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;


public class LevelOneViewController {

    @FXML
    public Group tankOne;

    @FXML
    public Group tankTwo;

    @FXML
    public Group tankThree;

    @FXML
    public Group tankFour;
    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {

            tankOne.setLayoutY(tankOne.getLayoutY()-10.0);
        }
        if (keyEvent.getCode() == KeyCode.S) {

            tankOne.setLayoutY(tankOne.getLayoutY()+10.0);
        }
    }
    @FXML
    public void moveUPClicked(){
        tankOne.setLayoutY(tankOne.getLayoutY()+10.0);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Geklickt");

        alert.showAndWait();
    }

    @FXML
    private void initialize(){
        tankOne.setLayoutY(tankOne.getLayoutY()+240);
    }
}