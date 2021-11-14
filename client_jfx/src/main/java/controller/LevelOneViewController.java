package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;


public class LevelOneViewController implements Initializable {

    @FXML
    public Group tankOne;

    @FXML
    public Group tankTwo;

    @FXML
    public Group tankThree;

    @FXML
    public Group tankFour;

    public void moveUp() {
        System.out.println("Movin UP!");
    }

    public void moveDown() {
        System.out.println("Movin Down!");
    }

    public void moveLeft() {
        System.out.println("Movin Left!");
    }

    public void moveRight() {
        System.out.println("Movin Right!");
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            tankOne.setLayoutY(tankOne.getLayoutY()-10.0);
        }
        if (keyEvent.getCode() == KeyCode.S) {

            tankOne.setLayoutY(tankOne.getLayoutY()+10.0);
        }
        if (keyEvent.getCode() == KeyCode.D) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void initialize(){

    }
}