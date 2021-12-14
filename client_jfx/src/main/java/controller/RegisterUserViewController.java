package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.ETankApplication;
import model.data.Authorisation;
import model.data.User;
import model.service.HttpRequest;

import java.io.IOException;


public class RegisterUserViewController {

    ETankApplication eTankApplication;
    HttpRequest httpRequest = new HttpRequest();

    @FXML
    TextField usernameField;

    @FXML
    TextField pubNameField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button registerUserButton;

    public void initialize() {
        registerUserButton.setDisable(true);
    }

    public void keyRelased() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String pubName = pubNameField.getText();

        boolean isDisabled = (username.isEmpty() || password.isEmpty() || pubName.isEmpty());

        registerUserButton.setDisable(isDisabled);
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void switchToLogin() throws IOException {
        eTankApplication.showLoginView();
    }

    public void switchToMenu() throws IOException {
        eTankApplication.showMenuView();
    }

    public void registerUser() {
        Authorisation authorisation = new Authorisation(usernameField.getText(),pubNameField.getText(),passwordField.getText());

        if(httpRequest.registerUser(authorisation)){
            try {
                eTankApplication.showLoginView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Username bereits vergeben");
        }

    }


}
