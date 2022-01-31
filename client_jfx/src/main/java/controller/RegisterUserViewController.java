package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

import model.data.Authorisation;
import model.service.HttpRequest;

public class RegisterUserViewController extends ViewController {

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

    /**
     * Disables the login-button when key released and username or password fields are not filled
     * or else activates it
     */
    public void keyRelased() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String pubName = pubNameField.getText();

        boolean isDisabled = (username.isEmpty() || password.isEmpty() || pubName.isEmpty());

        registerUserButton.setDisable(isDisabled);
    }

    /**
     * Calls the method showLoginView in class eTankApplication
     *
     * @throws IOException the io exception
     */
    public void switchToLogin() throws IOException {
        eTankApplication.showLoginView();
    }

    /**
     * Sends a request to register the user
     * and shows loginVie afterwards
     */
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
