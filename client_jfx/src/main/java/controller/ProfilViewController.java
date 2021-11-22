package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.ETankApplication;
import model.User;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

public class ProfilViewController extends ViewController {

    @FXML
    private TextField publicName;
    @FXML
    private TextField password;

    @FXML
    public void initialize() {
        publicName.setDisable(true);
        password.setText("********");
        password.setDisable(true);
    }

    public void editName(ActionEvent actionEvent) {
        if(publicName.isDisable()){
            publicName.setDisable(false);
        }else{
            publicName.setDisable(true);
        }
    }

    public void editPassword(ActionEvent actionEvent) {
        if(password.isDisable()){
            password.setDisable(false);
        }else{
            password.setDisable(true);
        }
    }

    public void saveProfil(ActionEvent actionEvent) {
        eTankApplication.getSignedUser().setPublicName(publicName.getText());
        //eTankApplication.getSignedUser().setPassword(password.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Deine Änderungen wurden erfolgreich gespeichert!");
        alert.showAndWait();
    }

    public void initialiseUserData(){
        publicName.setText(eTankApplication.getSignedUser().getPublicName());
    }
}
