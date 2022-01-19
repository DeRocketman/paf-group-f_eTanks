package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.data.UserSettings;
import model.service.HttpRequest;


public class SettingViewController extends ViewController {

    HttpRequest httpRequest = new HttpRequest();
    UserSettings tempUserSettings = new UserSettings();

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
                tempUserSettings.setMoveUpKey(event.getCode());
            } else if(tempButton.getId().equals("moveDownKey")){
                tempUserSettings.setMoveDownKey(event.getCode());
            }else if(tempButton.getId().equals("moveLeftKey")){
                tempUserSettings.setMoveLeftKey(event.getCode());
            }else if(tempButton.getId().equals("moveRightKey")){
                tempUserSettings.setMoveRightKey(event.getCode());
            }else if(tempButton.getId().equals("fireMainKey")){
                tempUserSettings.setFireMainWeaponKey(event.getCode());
            }else if(tempButton.getId().equals("fireSecondKey")){
                tempUserSettings.setFireSecondaryWeaponKey(event.getCode());
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
        eTankApplication.getSignedUser().getUserSettings().setMoveUpKey(tempUserSettings.getMoveUpKey());
        eTankApplication.getSignedUser().getUserSettings().setMoveDownKey(tempUserSettings.getMoveDownKey());
        eTankApplication.getSignedUser().getUserSettings().setMoveLeftKey(tempUserSettings.getMoveLeftKey());
        eTankApplication.getSignedUser().getUserSettings().setMoveRightKey(tempUserSettings.getMoveRightKey());
        eTankApplication.getSignedUser().getUserSettings().setFireMainWeaponKey(tempUserSettings.getFireMainWeaponKey());
        eTankApplication.getSignedUser().getUserSettings().setFireSecondaryWeaponKey(tempUserSettings.getFireSecondaryWeaponKey());

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

        moveUpKey.setText(eTankApplication.getSignedUser().getUserSettings().getMoveUpKey().toString());
        moveDownKey.setText(eTankApplication.getSignedUser().getUserSettings().getMoveDownKey().toString());
        moveLeftKey.setText(eTankApplication.getSignedUser().getUserSettings().getMoveLeftKey().toString());
        moveRightKey.setText(eTankApplication.getSignedUser().getUserSettings().getMoveRightKey().toString());
        fireMainKey.setText(eTankApplication.getSignedUser().getUserSettings().getFireMainWeaponKey().toString());
        fireSecondKey.setText(eTankApplication.getSignedUser().getUserSettings().getFireSecondaryWeaponKey().toString());

        musicVolumeSettings.setValue(eTankApplication.getSignedUser().getUserSettings().getGameMusicVolume());
        soundVolumeSettings.setValue(eTankApplication.getSignedUser().getUserSettings().getGameSoundVolume());
    }
}
