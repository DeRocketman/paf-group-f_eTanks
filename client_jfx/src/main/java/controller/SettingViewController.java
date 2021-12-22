package controller;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import main.ETankApplication;
import model.service.HttpRequest;
import org.boon.core.Sys;

import java.lang.annotation.Target;

import static javafx.scene.input.KeyEvent.KEY_RELEASED;

public class SettingViewController extends ViewController {

    HttpRequest httpRequest = new HttpRequest();

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

    public void changeMusicVolume(ActionEvent actionEvent) {

    }

    public void changeSoundVolume(ActionEvent actionEvent) {}

    public void handleChangKey(Event e) {

        Button tempButton = (Button) e.getSource();

        tempButton.setStyle("-fx-background-color: grey");
        tempButton.setOnKeyReleased(event -> {
            tempButton.setText(event.getCode().toString());
            if(tempButton.getId().equals("moveUpKey")) {
                eTankApplication.getSignedUser().getUserSettings().setMoveUpKey(event.getCode().toString());
            } else if(tempButton.getId().equals("moveDownKey")){
                eTankApplication.getSignedUser().getUserSettings().setMoveDownKey(event.getCode().toString());
            }else if(tempButton.getId().equals("moveLeftKey")){
                eTankApplication.getSignedUser().getUserSettings().setMoveLeftKey(event.getCode().toString());
            }else if(tempButton.getId().equals("moveRightKey")){
                eTankApplication.getSignedUser().getUserSettings().setMoveRightKey(event.getCode().toString());
            }else if(tempButton.getId().equals("fireMainKey")){
                eTankApplication.getSignedUser().getUserSettings().setFireMainWeaponKey(event.getCode().toString());
            }else if(tempButton.getId().equals("fireSecondKey")){
                eTankApplication.getSignedUser().getUserSettings().setFireSecondaryWeaponKey(event.getCode().toString());
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
