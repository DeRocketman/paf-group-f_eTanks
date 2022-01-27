package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import main.ETankApplication;
import model.data.*;
import model.service.HttpRequest;
import java.io.IOException;

public class LoginViewController {

    ETankApplication eTankApplication;
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
        loginBtn.setDisable(true);
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void keyRelased() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean isDisabled = (username.isEmpty() || password.isEmpty());

        loginBtn.setDisable(isDisabled);
    }

    public void createUserView() throws IOException {
        eTankApplication.showCreateUserView();
    }

    //Anmelden Token erhalten
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

    //ETankapplication bekannt machen
    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }
}
