package controller;

import model.service.HttpRequest;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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

    boolean userImageEdited = false;
    String changedImagePath;

    HttpRequest httpRequest = new HttpRequest();

    @FXML
    public void initialize() {
        publicName.setDisable(true);
        password.setPromptText("Passwort");
        password.setDisable(true);
    }

    /**
     * Disables or activates the publicName field
     */
    public void editName() {
        publicName.setDisable(!publicName.isDisable());
    }

    /**
     * Disables or activates the password field
     */
    public void editPassword() {
        password.setDisable(!password.isDisable());
    }

    /**
     * Changes the data of the signedUser and
     * sends a httpRequest to save the changes
     */
    public void saveProfil() {
        eTankApplication.getSignedUser().setPublicName(publicName.getText());
        if(!password.getText().isEmpty()){
            eTankApplication.getSignedUser().setPassword(password.getText());
        }
        if(userImageEdited){
            eTankApplication.getSignedUser().setUserImage(encodeImage(changedImagePath));
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

    /**
     * Initializes the fields with the data from signedUser
     */
    public void initialiseUserData() {
        setUserImage();
        publicName.setText(eTankApplication.getSignedUser().getPublicName());
        password.setText(eTankApplication.getSignedUser().getPassword());
    }

    /**
     * Sets the User Image from the image in signedUser
     */
    public void setUserImage() {
        if(eTankApplication.getSignedUser().getUserImage().equals("default")){
            userImage.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("img/images/default-user-image.png"))));
            System.out.println("Default Bild geladen");
        } else {
            userImage.setImage(decodeImage(eTankApplication.getSignedUser().getUserImage()));
        }
    }

    @FXML
    private void editImage() {
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
        userImage.setImage(decodeImage(encodeImage(filePath)));
    }

    /**
     * Decodes the base64Image to an Image
     *
     * @param base64Image   readable String of an image
     * @return              new Image from the String
     */
    private Image decodeImage(String base64Image) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(base64Image));
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(inputStream.toString())));
    }

    /**
     * Encodes the Image from the path to an base64Image
     *
     * @param imagePath     local path of the image
     * @return base64Image  readable String of the image
     */
    private String encodeImage(String imagePath) {

        String base64Image = null;
        try {
            base64Image = DatatypeConverter.printBase64Binary(Files.readAllBytes(
                    Paths.get(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return base64Image;
    }

    /**
     * Sets the eTankApplication in the class httpRequest
     */
    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }
}
