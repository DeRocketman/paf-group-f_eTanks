package controller;

import javafx.fxml.FXML;
import main.ETankApplication;

import java.io.IOException;

public class GameCreatorViewController {
    ETankApplication eTankApplication;

    @FXML
    public void changeView() throws IOException {
        //TODO: Muss gefixt werden, da Views Übereinander liegen!
        eTankApplication.showGameView();
        eTankApplication.showLevelOneView();
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }
}
