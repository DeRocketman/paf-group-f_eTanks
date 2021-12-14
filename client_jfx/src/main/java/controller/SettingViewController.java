package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class SettingViewController extends ViewController  {

    @FXML
    private ToggleButton soundSettings;
    @FXML
    private ToggleButton musicSettings;
    @FXML
    private Slider soundVolumeSettings;
    @FXML
    private Slider musicVolumeSettings;


    public void changeSound(ActionEvent actionEvent) {
        if (soundSettings.isSelected()){
            soundSettings.setText ("An");
        }
        else {
            soundSettings.setText ("Aus");
        }
    }

    public void changeMusic(ActionEvent actionEvent) {
        if (musicSettings.isSelected()){
            musicSettings.setText ("An");
        }
        else {
            musicSettings.setText ("Aus");
        }
    }

    public void changeMusicVolume(ActionEvent actionEvent) {

    }

    public void changeSoundVolume(ActionEvent actionEvent) {

    }

    public void changeKeys(ActionEvent actionEvent) {

    }

    public void saveSettings(ActionEvent actionEvent) {
        if(musicSettings.isSelected()){
            eTankApplication.getSignedUser().getUserSettings().setGameMusicOn(true);
        } else {
            eTankApplication.getSignedUser().getUserSettings().setGameMusicOn(false);
        }

        if(soundSettings.isSelected()){
            eTankApplication.getSignedUser().getUserSettings().setGameSoundOn(true);
        } else {
            eTankApplication.getSignedUser().getUserSettings().setGameSoundOn(false);
        }

        eTankApplication.getSignedUser().getUserSettings().setGameMusicVolume((int) musicVolumeSettings.getValue());
        eTankApplication.getSignedUser().getUserSettings().setGameSoundVolume((int) soundVolumeSettings.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Deine Änderungen wurden erfolgreich gespeichert!");
        alert.showAndWait();
    }

    public void initialiseUserData(){
        if(eTankApplication.getSignedUser().getUserSettings().isGameMusicOn() == true){
            musicSettings.setSelected(true);
            musicSettings.setText ("An");
        } else {
            musicSettings.setSelected(false);
            musicSettings.setText ("Aus");
        }

        if(eTankApplication.getSignedUser().getUserSettings().isGameSoundOn() == true){
            soundSettings.setSelected(true);
            soundSettings.setText ("An");
        } else {
            soundSettings.setSelected(false);
            soundSettings.setText ("Aus");
        }

        musicVolumeSettings.setValue(eTankApplication.getSignedUser().getUserSettings().getGameMusicVolume());
        soundVolumeSettings.setValue(eTankApplication.getSignedUser().getUserSettings().getGameSoundVolume());
    }
}
