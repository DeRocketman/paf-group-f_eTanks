package controller;

import javafx.event.ActionEvent;
import main.ETankApplication;

import java.io.IOException;

public class MenuViewController {

    ETankApplication eTankApplication;

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;

    }

    public void switchToLevelOne(ActionEvent actionEvent) throws IOException {
        eTankApplication.showLevelOneView();
    }

    public void switchToProfil(ActionEvent actionEvent) {
    }

    public void switchToSettings(ActionEvent actionEvent) {
    }
}
