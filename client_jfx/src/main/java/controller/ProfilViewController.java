package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.data.User;
import model.service.HttpRequest;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;


public class ProfilViewController extends ViewController {

    User tempUser;

    @FXML
    ImageView userImage;
    @FXML
    Button editName;
    @FXML
    private TextField publicName;
    @FXML
    private TextField password;
    boolean passwordChanged = false;

    HttpRequest httpRequest = new HttpRequest();

    @FXML
    public void initialize() {
        publicName.setDisable(true);
        password.setPromptText("Passwort");
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
        if(!password.getText().isEmpty()){
            eTankApplication.getSignedUser().setPassword(password.getText());
        }

        setHttpRequestETankapplication();

        if(httpRequest.saveUser(eTankApplication.getSignedUser())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deine Änderungen wurden erfolgreich gespeichert!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deine Änderungen konnten nicht gespeichert werden!");
            alert.showAndWait();
        }
    }

    public void initialiseUserData(){
        publicName.setText(eTankApplication.getSignedUser().getPublicName());
        setUserImage();
    }

    private void setUserImage() {
        if(eTankApplication.getSignedUser().getUserImage().equals("default")){
            userImage = new ImageView(String.valueOf(getClass().getResource("../img/images/default-user-image.png")));
            System.out.println("Default  Bild geladen");
        } else {
            userImage = new ImageView(new Image(Arrays.toString(Base64.getDecoder().decode(eTankApplication.getSignedUser().getUserImage()))));
        }
    }

    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }
}
