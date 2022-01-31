package controller;

import model.data.*;
import model.service.HttpRequest;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class LoginViewController extends  ViewController {

    HttpRequest httpRequest = new HttpRequest();

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

    public void initialize() {
        //Disables the login-button
        loginBtn.setDisable(true);
    }

    /**
     * Disables the login-button when key released and username or password fields are not filled
     * or else activates it
     */
    public void keyRelased() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isDisabled = (username.isEmpty() || password.isEmpty());

        loginBtn.setDisable(isDisabled);
    }

    /**
     * Calls the method showCreateUserView in class eTankApplication
     *
     * @throws IOException
     */
    public void switchToCreateUser() throws IOException {
        eTankApplication.showCreateUserView();
    }

    /**
     * Gets the login token with request to authorize the user
     */
    public void login() {

        setHttpRequestETankapplication();

        Authorisation authorisation = new Authorisation(usernameField.getText(),passwordField.getText());

        if(httpRequest.login(authorisation)){
            try {
                eTankApplication.getSignedUser().setPassword(passwordField.getText());
                eTankApplication.showMenuView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login fehlgeschlagen");
        }
    }

    /**
     * Sets the eTankApplication in the class httpRequest
     */
    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }
}
