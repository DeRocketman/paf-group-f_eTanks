package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.ETankApplication;

import java.io.IOException;

public class CreateUserViewController extends ViewController{

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

    public void switchToLogin() throws IOException {
        eTankApplication.showLoginView();
    }

    public void switchToMenu() throws IOException {
        eTankApplication.showMenuView();
    }

    public void createUser(){

    }


}
