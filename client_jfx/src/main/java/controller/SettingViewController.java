package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.data.User;
import model.service.HttpRequest;


public class SettingViewController extends ViewController {

    HttpRequest httpRequest = new HttpRequest();
    User tempUser;

    @FXML
    private ToggleButton soundSettings;
    @FXML
    private ToggleButton musicSettings;
    @FXML
    private Slider soundVolumeSettings;
    @FXML
    private Slider musicVolumeSettings;
    @FXML
    private Button moveUpKey;
    @FXML
    private Button moveLeftKey;
    @FXML
    private Button moveDownKey;
    @FXML
    private Button moveRightKey;
    @FXML
    private Button fireMainKey;
    @FXML
    private Button fireSecondKey;

    public void changeSound(ActionEvent actionEvent) {
        if (soundSettings.isSelected()) {
            soundSettings.setText("An");
        } else {
            soundSettings.setText("Aus");
        }
    }

    public void changeMusic(ActionEvent actionEvent) {
        if (musicSettings.isSelected()) {
            musicSettings.setText("An");
        } else {
            musicSettings.setText("Aus");
        }
    }

    public void handleChangKey(Event e) {

        Button tempButton = (Button) e.getSource();

        tempButton.setStyle("-fx-background-color: grey");
        tempButton.setOnKeyReleased(event -> {
            tempButton.setText(event.getCode().toString());
            if(tempButton.getId().equals("moveUpKey")) {
                tempUser.getUserSettings().setMoveUpKey(event.getCode().toString());
            } else if(tempButton.getId().equals("moveDownKey")){
                tempUser.getUserSettings().setMoveDownKey(event.getCode().toString());
            }else if(tempButton.getId().equals("moveLeftKey")){
                tempUser.getUserSettings().setMoveLeftKey(event.getCode().toString());
            }else if(tempButton.getId().equals("moveRightKey")){
                tempUser.getUserSettings().setMoveRightKey(event.getCode().toString());
            }else if(tempButton.getId().equals("fireMainKey")){
                tempUser.getUserSettings().setFireMainWeaponKey(event.getCode().toString());
            }else if(tempButton.getId().equals("fireSecondKey")){
                tempUser.getUserSettings().setFireSecondaryWeaponKey(event.getCode().toString());
            }
            tempButton.setOnKeyReleased(null);
            tempButton.setStyle("");
        });

    }

    public void saveSettings(ActionEvent actionEvent) {
        if (musicSettings.isSelected()) {
            eTankApplication.getSignedUser().getUserSettings().setGameMusicOn(true);
        } else {
            eTankApplication.getSignedUser().getUserSettings().setGameMusicOn(false);
        }

        if (soundSettings.isSelected()) {
            eTankApplication.getSignedUser().getUserSettings().setGameSoundOn(true);
        } else {
            eTankApplication.getSignedUser().getUserSettings().setGameSoundOn(false);
        }

        eTankApplication.getSignedUser().getUserSettings().setGameMusicVolume((int) musicVolumeSettings.getValue());
        eTankApplication.getSignedUser().getUserSettings().setGameSoundVolume((int) soundVolumeSettings.getValue());
        eTankApplication.getSignedUser().getUserSettings().setMoveUpKey(tempUser.getUserSettings().getMoveUpKey());
        eTankApplication.getSignedUser().getUserSettings().setMoveDownKey(tempUser.getUserSettings().getMoveDownKey());
        eTankApplication.getSignedUser().getUserSettings().setMoveLeftKey(tempUser.getUserSettings().getMoveLeftKey());
        eTankApplication.getSignedUser().getUserSettings().setMoveRightKey(tempUser.getUserSettings().getMoveRightKey());
        eTankApplication.getSignedUser().getUserSettings().setFireMainWeaponKey(tempUser.getUserSettings().getFireMainWeaponKey());
        eTankApplication.getSignedUser().getUserSettings().setFireSecondaryWeaponKey(tempUser.getUserSettings().getFireSecondaryWeaponKey());

        httpRequest.setETankApplication(eTankApplication);

        if(httpRequest.saveSettings(eTankApplication.getSignedUser().getUserSettings())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deine Änderungen wurden erfolgreich gespeichert!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Hier ist ein Fehler aufgetreten");
            alert.showAndWait();
        }
    }

    public void initialiseUserData() {

        tempUser = new User();
        tempUser.setUserSettings(eTankApplication.getSignedUser().getUserSettings());

        if (eTankApplication.getSignedUser().getUserSettings().isGameMusicOn()) {
            musicSettings.setSelected(true);
            musicSettings.setText("An");
        } else {
            musicSettings.setSelected(false);
            musicSettings.setText("Aus");
        }

        if (eTankApplication.getSignedUser().getUserSettings().isGameSoundOn()) {
            soundSettings.setSelected(true);
            soundSettings.setText("An");
        } else {
            soundSettings.setSelected(false);
            soundSettings.setText("Aus");
        }

        moveUpKey.setText(eTankApplication.getSignedUser().getUserSettings().getMoveUpKey());
        moveDownKey.setText(eTankApplication.getSignedUser().getUserSettings().getMoveDownKey());
        moveLeftKey.setText(eTankApplication.getSignedUser().getUserSettings().getMoveLeftKey());
        moveRightKey.setText(eTankApplication.getSignedUser().getUserSettings().getMoveRightKey());
        fireMainKey.setText(eTankApplication.getSignedUser().getUserSettings().getFireMainWeaponKey());
        fireSecondKey.setText(eTankApplication.getSignedUser().getUserSettings().getFireSecondaryWeaponKey());

        musicVolumeSettings.setValue(eTankApplication.getSignedUser().getUserSettings().getGameMusicVolume());
        soundVolumeSettings.setValue(eTankApplication.getSignedUser().getUserSettings().getGameSoundVolume());
    }
}
