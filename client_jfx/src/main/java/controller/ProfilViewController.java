package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.ETankApplication;
import model.User;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;

public class ProfilViewController extends ViewController {

    ETankApplication eTankApplication;

    @FXML
    private TextField publicName;

    @FXML
    public void initialize() {
        publicName.setText("Name");
        publicName.setDisable(true);
    }


    public void editName(ActionEvent actionEvent) {
        publicName.setDisable(false);
    }

    public void saveProfil(ActionEvent actionEvent) {
       // System.out.println(eTankApplication.getSignedUser().getUserName());
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void initialiseUserData(){
        publicName.setText(eTankApplication.getSignedUser().getUserName());
    }

}
