package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.ETankApplication;

public class CreateUserViewController {

    ETankApplication eTankApplication;

    @FXML
    TextField emailField;

    @FXML
    TextField usernameField;

    @FXML
    TextField pubNameField;

    @FXML
    PasswordField passwordField;

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void createUser(){

    }


}
