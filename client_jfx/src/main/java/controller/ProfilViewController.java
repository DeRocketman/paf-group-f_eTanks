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

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;


public class ProfilViewController extends ViewController {

    @FXML
    ImageView userImage;
    @FXML
    Button editName;
    @FXML
    private TextField publicName;
    @FXML
    private TextField password;

    User tempUser;

    boolean userImageEdited = false;
    String changedImagePath;

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
        if(userImageEdited){
            eTankApplication.getSignedUser().setUserImage(decodeImage(changedImagePath));
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

    public void initialiseUserData() throws IOException {
        setUserImage();
        publicName.setText(eTankApplication.getSignedUser().getPublicName());
        //TODO: hidden Passwort
        password.setText(eTankApplication.getSignedUser().getPassword());
    }

    public void setUserImage() throws IOException {
        if(eTankApplication.getSignedUser().getUserImage().equals("default")){
            userImage.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("img/images/default-user-image.png"))));
            System.out.println("Default Bild geladen");
        } else {
            userImage.setImage(getImageFromBase64String(eTankApplication.getSignedUser().getUserImage()));
        }
    }

    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }

    @FXML
    private void editImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setTitle("Bitte neues Bild auswählen");
        File file = fileChooser.showOpenDialog(eTankApplication.getPrimaryStage());
        String filePath = file.getAbsolutePath();
        userImageEdited = true;
        changedImagePath = filePath;
        userImage.setImage(getImageFromBase64String(decodeImage(filePath)));
    }

    private Image getImageFromBase64String(String newValue) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(newValue));
        Image img = new Image(inputStream);
        return img;
    }

    private String decodeImage(String imagePath) {

        String base64Image = null;
        try {
            base64Image = DatatypeConverter.printBase64Binary(Files.readAllBytes(
                    Paths.get(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return base64Image;
    }
}
