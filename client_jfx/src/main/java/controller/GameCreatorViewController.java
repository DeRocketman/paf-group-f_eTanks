package controller;

import javafx.fxml.FXML;
import main.ETankApplication;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;

public class GameCreatorViewController {
    ETankApplication eTankApplication;

    @FXML
    private Label labelUserName1;

    @FXML
    private Label labelUserName2;

    @FXML
    private Label labelUserName3;
    @FXML
    private Label labelUserName4;

    @FXML
    public void hostGame() {

    }

    @FXML
    public void joinGame() {

    }

    @FXML
    public void switchBack() {

    }

    @FXML
    public void switchToGameView() throws IOException{
        //TODO: Muss gefixt werden, da Views Übereinander liegen!
        eTankApplication.showGameView();
        eTankApplication.showLevelOneView();
    }

    @FXML
    public void joinAsMember1() {

    }

    @FXML
    public void joinAsMember2() {

    }

    @FXML
    public void joinAsMember3() {

    }


    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }
}
