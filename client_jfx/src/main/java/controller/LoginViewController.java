package controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import main.ETankApplication;
import model.data.GameStatistic;
import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;
import model.service.HttpRequest;
import model.service.UserDataCreator;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;


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

        eTankApplication.getSignedUser().setUserName(usernameField.getText());
        eTankApplication.getSignedUser().setPassword(passwordField.getText());

        if(httpRequest.loginByUser()){
            try {
                eTankApplication.showMenuView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login fehlgeschlagen");
        }
        setHttpRequestETankapplication();
    }

    //ETankapplication bekannt machen
    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }
}
