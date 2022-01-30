package controller;

import main.ETankApplication;
import model.service.HttpRequest;

import java.io.IOException;

public class ViewController {

    ETankApplication eTankApplication;
    HttpRequest httpRequest = new HttpRequest();

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void switchToProfil() throws IOException {
        eTankApplication.showProfilView();
        System.out.println(eTankApplication.getSignedUser().getUserName());
    }

    public void switchToStatistics() throws IOException {
        eTankApplication.showStatisticsView();
    }

    public void switchToSettings() throws IOException {
        eTankApplication.showSettingsView();
    }

    public void switchToMenu() throws IOException {
        eTankApplication.showMenuView();
    }

    public void switchToGameCreator() throws IOException {
        eTankApplication.showGameCreatorView();
    }

    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }
}
