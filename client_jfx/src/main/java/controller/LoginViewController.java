package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import controller.ViewController;
import main.ETankApplication;

import java.io.IOException;

public class LoginViewController {

    ETankApplication eTankApplication;

    @FXML
    TextField UsernameField;
    @FXML
    PasswordField pwField;
    @FXML
    Label forgotPwLabel;
    @FXML
    Label newUserLabel;
    @FXML
    Button loginBtn;

    @FXML
    public void changeView() throws IOException {
        eTankApplication.showMenuView();
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;

    }


}
