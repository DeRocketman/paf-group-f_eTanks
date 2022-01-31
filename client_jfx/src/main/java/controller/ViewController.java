package controller;

import main.ETankApplication;
import model.service.HttpRequest;

import java.io.IOException;

public class ViewController {

    ETankApplication eTankApplication;
    HttpRequest httpRequest = new HttpRequest();

    /**
     * Sets the eTankApplication
     *
     * @param eTankApplication
     */
    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    /**
     * Sets the eTankApplication in the class httpRequest
     */
    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }

    /**
     * Calls the method showProfilView in class eTankApplication
     *
     * @throws IOException
     */
    public void switchToProfil() throws IOException {
        eTankApplication.showProfilView();
        //TODO: Ausgabe entfernen
        System.out.println(eTankApplication.getSignedUser().getUserName());
    }

    /**
     * Calls the method showStatisticsView in class eTankApplication
     *
     * @throws IOException
     */
    public void switchToStatistics() throws IOException {
        eTankApplication.showStatisticsView();
    }

    /**
     * Calls the method showSettingsView in class eTankApplication
     *
     * @throws IOException
     */
    public void switchToSettings() throws IOException {
        eTankApplication.showSettingsView();
    }

    /**
     * Calls the method showMenuView in class eTankApplication
     *
     * @throws IOException
     */
    public void switchToMenu() throws IOException {
        eTankApplication.showMenuView();
    }
    /**
     * Calls the method showLobbyView in class eTankApplication
     *
     * @throws IOException
     */
    public void switchToLobby() throws IOException {
        eTankApplication.showLobbyView();
    }
}
