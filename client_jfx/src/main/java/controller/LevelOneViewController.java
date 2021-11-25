package controller;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import main.ETankApplication;
import model.BulletMainWeapon;
import model.Tank;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

//TODO refactorn!
public class LevelOneViewController implements Initializable {

    ETankApplication eTankApplication;

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }
}