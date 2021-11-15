package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class SettingViewController extends ViewController  {

    @FXML
    private TextField publicName;
    @FXML
    private ToggleButton soundSettings;
    @FXML
    private ToggleButton musicSettings;
    @FXML
    private ToggleButton effectSettings;

    public void initialize(){

    }

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

    public void changeEffect(ActionEvent actionEvent) {
        if (effectSettings.isSelected()){
            effectSettings.setText ("An");
        }
        else {
            effectSettings.setText ("Aus");
        }
    }

    public void changeEffectVolume(ActionEvent actionEvent) {

    }

    public void changeKeys(ActionEvent actionEvent) {

    }

    public void saveSettings(ActionEvent actionEvent) {
    }

}
