package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import main.ETankApplication;
import model.UserDataCreator;
import model.User;

import java.io.IOException;

public class LoginViewController {

    ETankApplication eTankApplication;
    UserDataCreator eudc = new UserDataCreator();

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
        String userName = usernameField.getText();
        String password = passwordField.getText();

        boolean isDisabled = (userName.isEmpty() || password.isEmpty());

        loginBtn.setDisable(isDisabled);
    }

    public void changeView() throws IOException {

        int counter = 0;
        for (User u : eudc.getUserlist()) {

            if (u.getUserName().equals(this.usernameField.getText()) && u.getPassword().equals(this.passwordField.getText())) {
                eTankApplication.setSignedUser(u);
                eTankApplication.showMenuView();
                System.out.println(eTankApplication.getSignedUser().getUserName());
            } else {
                counter += 1;
            }
            if(counter == eudc.getUserlist().size()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Falsches Passwort oder Username");
                alert.setContentText("Rekrut, bitte prüfe Passwort und/oder Username");
                alert.showAndWait();
            }
        }
    }


}
