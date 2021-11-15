package controller;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
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
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Label forgotPwLabel;
    @FXML
    Label newUserLabel;
    @FXML
    Button loginBtn;

    public void initialize(){
        loginBtn.setDisable(true);
    }

    public void keyRelased () {
        String userName = usernameField.getText();
        String password = passwordField.getText();

        boolean isDisabled = (userName.isEmpty() || password.isEmpty() );

        loginBtn.setDisable(isDisabled);
    }

    public void changeView() throws IOException {
        eTankApplication.showMenuView();
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;

    }


}
